package com.is.client_personmap;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ComparisonChain;
import com.is.client_personmap.model.*;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;

import com.is.ISLogger;
import com.is.base.Dao;
import com.is.base.utils.FieldHandler;
import com.is.base.utils.Util;
import com.is.base.utils.ZkUtils;
import com.is.clients.utils.ClientUtil;
import com.is.customer_.core.model.Customer;
import com.is.searchSap.SearchResponse;
import com.is.search_clients.model.UserInput;

public class PersonMapUtil {
    public static final String SAP_PERSON_ATTR = "sapPerson";
    public static final String PERSONKIND_IP = "4";
    public static final String PERSONKIND_FOUNDER = "3";
    public static final String PERSONKIND_DIR = "1";
    public static final String PERSONKIND_DIR_ACC = "5";
    public static final String PERSONKIND_ACC = "2";
    public static final String PERSONKIND_BENEFICIARY = "6";
    
    public static final String PERSONTYPE_J = "J";
    public static final String PERSONTYPE_P = "P";

    private static String[] UNCHANGEABLE_FIELDS =
            {"id", "idSap", "branch", "client_id", "state", "kod_err", "file_name", "union_id"};

    public static Customer mapToBusinessPartner(UserInput userInput) {
        Customer businessPartner = new Customer();
        businessPartner.setP_type_document(userInput.getType_document());
        businessPartner.setP_passport_number(userInput.getPass_num());
        businessPartner.setP_passport_serial(userInput.getPass_ser());
        return businessPartner;
    }

    public static List<Person> makePersonList(List<SearchResponse> bpList) {
        List<Person> personList = new ArrayList<Person>();
        for (SearchResponse bp : bpList) {
            personList.add(makePerson(bp));
        }
        return personList;
    }

    public static List<PersonMap> pullFounders(List<PersonMap> list) {
        List<PersonMap> founders = new ArrayList<PersonMap>();
        for (PersonMap pMap : list) {
            if (pMap.getPerson_kind().equals(PERSONKIND_FOUNDER)) {
                founders.add(pMap);
            }
        }
        return founders;
    }

    public static List<PersonMap> pullContactPersons(List<PersonMap> list) {
        List<PersonMap> persons = new ArrayList<PersonMap>();
        for (PersonMap pMap : list) {
            if (Util.inStrings(pMap.getPerson_kind(), PERSONKIND_DIR, PERSONKIND_ACC)) {
                persons.add(pMap);
            }
        }
        return persons;
    }

    public static List<PersonMap> pullBeneficiaries(List<PersonMap> list) {
        List<PersonMap> beneficiaries = new ArrayList<PersonMap>();
        for (PersonMap pMap : list) {
            if (pMap.getPerson_kind().equals(PERSONKIND_BENEFICIARY)) {
            	beneficiaries.add(pMap);
            }
        }
        return beneficiaries;
    }
    
    public static String getIdsString(List<PersonMap> list) {
        StringBuilder ids = new StringBuilder();
        for (PersonMap pMap : list) {
            if (ids.length() != 0) {
                ids.append(",");
            }
            ids.append(pMap.getPerson_id());
        }
        return ids.toString();
    }

    public static BigDecimal getFounderPercentageRemainder(String clientId, String branch, Dao<PersonMap> personMapDao) {
        PersonMapFilter filter = new PersonMapFilter();
        filter.setClient_id(clientId);
        filter.setBranch(branch);
        personMapDao.setFilter(filter);

        List<PersonMap> founders = personMapDao.getList();
        personMapDao.setFilter(null);
        if (founders.size() == 0) {
            return new BigDecimal(100);
        }
        BigDecimal sum = BigDecimal.ZERO;
        for (PersonMap pm : founders) {
            if (pm.getCapital() != null && pm.getCapital().getPart_of_capital() != null) {
                sum = sum.add(pm.getCapital().getPart_of_capital());
                //ISLogger.getLogger().error("::::::::::::::::::::::::::::::::::::::::: capital item = " + pm.getCapital().getPart_of_capital());
            }
        }
        //ISLogger.getLogger().error("::::::::::::::::::::::::::::::::::::::::: capital sum = " + sum);
        return new BigDecimal(100).subtract(sum);
    }

    public static BigDecimal makeDecimalFromStringIfPossible(String str) {
        BigDecimal tmp = BigDecimal.ZERO;
        try {
            tmp = new BigDecimal(str.replaceAll("\\%", "").replaceAll("\\s+", "").replaceAll(",", "."));
        } catch (Exception e) {
            tmp = BigDecimal.ZERO;
        }
        return tmp;
    }

    private static Person makePerson(SearchResponse sr) {
        Person person = new Person();
        person.setFamily(sr.getLastNameGlobal());
        person.setFirst_name(sr.getFirstNameGlobal());
        person.setPatronymic(sr.getMiddleNameGlobal());
        person.setFamily_local(sr.getLastNameLocal());
        person.setFirst_name_local(sr.getFirstNameLocal());
        person.setPatronymic_local(sr.getMiddleNameLocal());
        person.setBirthday(sr.getBirthDay());
        person.setId(sr.getNciId());
        person.setIdSap(sr.getSapId());
        person.setBranch(sr.getBranch());
        return person;
    }

    private static boolean allDiffrent(Object curObject, Object sapObject, Object ebpObject) {
        return !(
                curObject != null && sapObject != null && ebpObject != null &&
                        curObject.equals(sapObject) && curObject.equals(ebpObject) && sapObject.equals(ebpObject)
                        || (curObject == null && sapObject == null && ebpObject == null));
    }

    public static void setSapDataIfNull(Person local, final Person sap) {
        Util.interateThroughFields(Person.class, local, new FieldHandler() {

            @Override
            public void eval(Field f, Object local) throws IllegalAccessException {
                Object sapObject = f.get(sap);
                Object localObj = f.get(local);
                if (localObj == null) {
                    f.set(local, sapObject);
                }
            }
        });
    }

    public static void setSapData(Person local, final Person sap, List<String> checkedFields) {

        Util.interateThroughFields(Person.class, local, new FieldHandler() {

            @Override
            public void eval(Field f, Object local) throws IllegalAccessException {
                Object sapObject = f.get(sap);
                if (sapObject != null) {
                    f.set(local, sapObject);
                }
            }
        });

    }

    public static void fillRowsIfThereChanges(
            LegalEntity local,
            final LegalEntity sap,
            final LegalEntity ebpClient,
            final Rows rows) {
        Util.interateThroughFields(LegalEntity.class, local, new FieldHandler() {

            @Override
            public void eval(Field f, Object local)
                    throws IllegalAccessException {
                Object curObject = f.get(local);
                Object sapObject = null;
                if (sap != null) {
                    sapObject = f.get(sap);
                }
                Object ebpObject = null;
                if (ebpClient != null) {
                    ebpObject = f.get(ebpClient);
                }

                if (allDiffrent(curObject, sapObject, ebpObject)
                        && !Util.inStrings(f.getName(), UNCHANGEABLE_FIELDS)) {//accounter
                    Row row = new Row();
                    Checkbox checkbox = null;
                    String fieldName = ClientUtil.getClientLabels().get("client." + f.getName());

                    row.appendChild(new Label(fieldName != null ? fieldName : f.getName()));
                    row.appendChild(ZkUtils.makeInputElement(curObject));

                    row.appendChild(ZkUtils.makeInputElement(sapObject));
                    checkbox = new Checkbox();
                    checkbox.setAttribute(ClientUtil.COLUMN_ATTR, ClientUtil.SAP_COLUMN);
                    row.appendChild(checkbox);

                    row.appendChild(ZkUtils.makeInputElement(ebpObject));
                    checkbox = new Checkbox();
                    checkbox.setAttribute(ClientUtil.COLUMN_ATTR, ClientUtil.EBP_COLUMN);
                    row.appendChild(checkbox);

                    row.setAttribute(ClientUtil.FIELD_ATTR, f.getName());
                    rows.appendChild(row);
                }
            }


        });
    }

    public static void showChangesForPerson(Person localPerson, final Person otherPerson, final Rows rows) {
        Util.interateThroughFields(Person.class, localPerson, new FieldHandler() {

            @Override
            public void eval(Field field, Object local) throws IllegalAccessException {
                if (Modifier.isStatic(field.getModifiers())) {
                    ISLogger.getLogger().error("Field is Static");
                    return;
                }
                Object localFieldValue = field.get(local);
                Object otherFieldValue = field.get(otherPerson);
                if (Util.hasChanges(localFieldValue, otherFieldValue)
                        && !Util.inStrings(field.getName(), UNCHANGEABLE_FIELDS)) {
                    Row row = new Row();
                    String fieldName = ClientUtil.getClientLabels().get("client." + field.getName());

                    row.appendChild(new Label(fieldName != null ? fieldName : field.getName()));
                    row.appendChild(ZkUtils.makeInputElement(localFieldValue));

                    row.appendChild(ZkUtils.makeInputElement(otherFieldValue));
                    row.appendChild(new Checkbox());

                    rows.appendChild(row);
                }

            }
        });

    }


    public static void main(String[] args) {
        List<PersonMap> list = new ArrayList<PersonMap>();
        PersonMap pm = new PersonMap();
        pm.setPerson_id("1");
        list.add(pm);
        pm = new PersonMap();
        pm.setPerson_id("2");
        list.add(pm);
        pm = new PersonMap();
        pm.setPerson_id("3");
        list.add(pm);
        pm = new PersonMap();
        pm.setPerson_id("4");
        list.add(pm);

//		List<PersonMap> founders = personMapDao.getList(0, 0);
//		List<FounderCapital> capitals = new ArrayList<FounderCapital>();
//		capitals.add(new FounderCapital(1, new BigDecimal(50), null, null, null, null, null));
        BigDecimal sum = BigDecimal.ZERO;
//		for(FounderCapital c : capitals) {
//			sum = sum.add(c.getPart_of_capital());
//		}

        String str = "8,04";
        sum = makeDecimalFromStringIfPossible(str);
        System.out.println(sum);
        System.out.println(new BigDecimal(101).subtract(sum));
    }

    public static void setSapData(LegalEntity local, final LegalEntity sap, final List<String> changedFields) {
        Util.interateThroughFields(LegalEntity.class, local, new FieldHandler() {

            @Override
            public void eval(Field field, Object local)
                    throws IllegalAccessException {
                if (changedFields.contains(field.getName())) {
                    Object sapObject = field.get(sap);
                    if (sapObject != null) {
                        field.set(local, sapObject);
                    }
                }
            }
        });
    }

    public static void setSapOrEbpData(LegalEntity current, final LegalEntity sap,
                                       final LegalEntity ebp, final List<String> sapChangedFields, final List<String> ebpChangedFields) {
        Util.interateThroughFields(LegalEntity.class, current, new FieldHandler() {

            @Override
            public void eval(Field field, Object local)
                    throws IllegalAccessException {
                if ((sapChangedFields.contains(field.getName()) && ebpChangedFields.contains(field.getName()))
                        || sapChangedFields.contains(field.getName())) {
                    Object sapObject = field.get(sap);
                    if (sapObject != null) {
                        field.set(local, sapObject);
                    }
                } else if (ebpChangedFields.contains(field.getName())) {
                    Object ebpObject = field.get(ebp);
                    if (ebpObject != null) {
                        field.set(local, ebpObject);
                    }
                }
            }
        });
    }


    public static void fillRowsIfThereChanges(
            final FounderCapital currentCapital,
            final FounderCapital comparedCapital,
            final Rows rows) {
        Util.interateThroughFields(FounderCapital.class, currentCapital, new FieldHandler() {
            @Override
            public void eval(Field f, Object local)
                    throws IllegalAccessException {

                Object currentObject = f.get(local);
                Object comparedObject = null;
                if (comparedCapital != null) {
                    comparedObject = f.get(comparedCapital);
                }

                if (isCapitalDifferent
                        ((FounderCapital)currentCapital,(FounderCapital)comparedCapital)
                        && isFieldUnchanged(f.getName())) {
                    Row row = new Row();
                    Checkbox checkbox = null;
                    String fieldName = ClientUtil.getClientLabels().get("client." + f.getName());

                    row.appendChild(new Label(fieldName != null ? fieldName : f.getName()));
                    row.appendChild(ZkUtils.makeInputElement(currentObject));
                    row.appendChild(ZkUtils.makeInputElement(comparedObject));

                    checkbox = new Checkbox();
                    checkbox.setAttribute(ClientUtil.COLUMN_ATTR, ClientUtil.SAP_COLUMN);
                    row.appendChild(checkbox);

                    row.setAttribute(ClientUtil.FIELD_ATTR, f.getName());
                    rows.appendChild(row);
                }
            }

            private boolean isFieldUnchanged(String field) {
                if (field.equalsIgnoreCase("idPersonMap"))
                    return false;
                if (field.equalsIgnoreCase("part_of_capital_old"))
                    return false;
                if (field.equalsIgnoreCase("sum_b"))
                    return false;
                if (field.equalsIgnoreCase("is_director"))
                    return false;
                if (field.equalsIgnoreCase("shares_number"))
                    return false;
                if (field.equalsIgnoreCase("founder_type"))
                    return false;
                return true;
            }

            private boolean isCapitalDifferent(FounderCapital currentObject, FounderCapital comparedObject) {
                int result = ComparisonChain.start().
                        compare(currentObject.getCurrency(),comparedObject.getCurrency() == null ? "" : comparedObject.getCurrency())
                        .compare(currentObject.getPart_of_capital(), comparedObject.getPart_of_capital() == null ? 0 : comparedObject.getPart_of_capital())
                        .compare(currentObject.getSum_a(),comparedObject.getSum_a() == null ? 0 : comparedObject.getSum_a()).result();
                /*int result = ComparisonChain.start()
                                .compare(currentObject.getCurrency(),comparedObject.getCurrency())
                                .compare(currentObject.getPart_of_capital(), comparedObject.getPart_of_capital())
                                .compare(currentObject.getSum_a(),comparedObject.getSum_a()).result();*/
                return result == 0 ? false : true;
            }
        });
    }
}
