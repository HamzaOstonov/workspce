package com.is.client_personmap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.is.ConnectionPool;
import com.is.base.Dao;
import com.is.base.utils.DbUtils;
import com.is.client_personmap.model.Person;
import com.is.client_sap.SapFactory;
import com.is.client_sap.exceptions.SapException;
import com.is.clients.models.SapLogger;
import com.is.customer_.core.utils.CustomerUtils;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;

public class PersonDao implements Dao<Person> {
    private static Logger logger = Logger.getLogger(PersonDao.class);

    private static final String SELECT = "select * from client_addinfo_person";
    private static final String SELECT_BY_ID = "select * from client_addinfo_person where id=?";

    private String alias;
    private Person filter;

    private PersonDao(String alias) {
        super();
        this.alias = alias;
    }

    public static PersonDao getInStance(String alias) {
        return new PersonDao(alias);
    }


    @Override
    public List<FilterField> getFilterFields() {
        List<FilterField> flFields = new ArrayList<FilterField>();
        if (!CheckNull.isEmpty(filter.getFamily()))
            flFields.add(new FilterField(DbUtils.getCond(flFields) + "family like ?", filter.getFamily().toUpperCase()));
        if (!CheckNull.isEmpty(filter.getFirst_name()))
            flFields.add(new FilterField(DbUtils.getCond(flFields) + "first_name like ?", filter.getFirst_name().toUpperCase()));
        if (!CheckNull.isEmpty(filter.getPatronymic()))
            flFields.add(new FilterField(DbUtils.getCond(flFields) + "patronymic like ?",filter.getPatronymic().toUpperCase()));
        if (!CheckNull.isEmpty(filter.getType_document()))
            flFields.add(new FilterField(DbUtils.getCond(flFields) + "type_document=?",filter.getType_document()));
        if (!CheckNull.isEmpty(filter.getPassport_serial()))
            flFields.add(new FilterField(DbUtils.getCond(flFields) + "passport_serial=?",filter.getPassport_serial()));
        if (!CheckNull.isEmpty(filter.getPassport_number()))
            flFields.add(new FilterField(DbUtils.getCond(flFields) + "passport_number=?",filter.getPassport_number()));

        return flFields;
    }

    @Override
    public int getCount() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Person> getList() {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        PreparedStatement capital = null;
        List<Person> list = new ArrayList<Person>();
        Person person = null;

        int params;
        List<FilterField> flFields = getFilterFields();
        StringBuffer sql = new StringBuffer();
        sql.append(SELECT);
        if (flFields.size() > 0) {
            for (int i = 0; i < flFields.size(); i++) {
                sql.append(flFields.get(i).getSqlwhere());
            }
        }
        try {
            c = ConnectionPool.getConnection(alias);
            ps = c.prepareStatement(sql.toString());
            for (params = 0; params < flFields.size(); params++) {
                ps.setObject(params + 1, flFields.get(params).getColobject());
            }
            params++;
            rs = ps.executeQuery();
            //logger.error("PreparedStatement " + sql.toString());
            while (rs.next()) {
                person = new Person(
                        rs.getString("branch"),
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getDate("birthday"),
                        rs.getString("post_address"),
                        rs.getString("passport_type"),
                        rs.getString("passport_serial"),
                        rs.getString("passport_number"),
                        rs.getString("passport_place_registration"),
                        rs.getDate("passport_date_registration"),
                        rs.getString("code_place_regist"),
                        rs.getDate("date_registration"),
                        rs.getString("number_registration_doc"),
                        rs.getString("code_tax_org"),
                        rs.getString("number_tax_registration"),
                        rs.getInt("state"),
                        rs.getString("code_citizenship"),
                        rs.getString("birth_place"),
                        rs.getString("code_capacity"),
                        rs.getDate("capacity_status_date"),
                        rs.getString("capacity_status_place"),
                        rs.getString("num_certif_capacity"),
                        rs.getString("phone_home"),
                        rs.getString("phone_mobile"),
                        rs.getString("email_address"),
                        rs.getString("pension_sertif_serial"),
                        rs.getString("code_gender"),
                        rs.getString("code_nation"),
                        rs.getString("code_birth_region"),
                        rs.getString("code_birth_distr"),
                        rs.getString("type_document"),
                        rs.getDate("passport_date_expiration"),
                        rs.getString("code_adr_region"),
                        rs.getString("code_adr_distr"),
                        rs.getString("inps"),
                        rs.getString("family"),
                        rs.getString("first_name"),
                        rs.getString("patronymic"),
                        rs.getString("code_country"),
                        rs.getString("code_resident"),
                        rs.getString("family_local"),
                        rs.getString("first_name_local"),
                        rs.getString("patronymic_local"),
                        rs.getString("pass_place_region"),
                        rs.getString("pass_place_district"),
                        rs.getString("union_id")
                );
                person.setPinfl(rs.getString("pinfl"));
                if(CustomerUtils.isAtaccamaOn()) {
                person.setSign_public_official(rs.getString("sign_public_official"));
                person.setPo_job_title(rs.getString("po_job_title"));
                person.setPo_welfare_source(rs.getString("po_welfare_source"));
                person.setPo_other_data(rs.getString("po_other_data"));
                }
                list.add(person);
            }

        } catch (Exception e) {
            logger.error(CheckNull.getPstr(e));
        } finally {
            ConnectionPool.close(c);
            DbUtils.closeResultSet(rs);
            DbUtils.closeStmt(ps);
            DbUtils.closeResultSet(rs);
            DbUtils.closeStmt(capital);
        }
        return list;
    }

    @Override
    public List<Person> getListWithPaging(int pageIndex, int pageSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Person getItemByLongId(String branch, long itemId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Person getItemByStringId(String branch, String id) {
        Connection c = null;
        Person person = null;
        try {
            if (alias != null) {
                c = ConnectionPool.getConnection(alias);
            } else {
                c = ConnectionPool.getConnection();
            }
            person = getItemByStringId(c, null, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.close(c);
        }
        return person;
    }

    @Override
    public Person getItemByLongId(Connection c, String branch, long itemId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Person getItemByStringId(Connection c, String branch, String itemId) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Person person = null;
        try {
            ps = c.prepareStatement(SELECT_BY_ID);
            ps.setString(1, itemId);
            rs = ps.executeQuery();
            if (rs.next()) {
                person = new Person(
                        rs.getString("branch"),
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getDate("birthday"),
                        rs.getString("post_address"),
                        rs.getString("passport_type"),
                        rs.getString("passport_serial"),
                        rs.getString("passport_number"),
                        rs.getString("passport_place_registration"),
                        rs.getDate("passport_date_registration"),
                        rs.getString("code_place_regist"),
                        rs.getDate("date_registration"),
                        rs.getString("number_registration_doc"),
                        rs.getString("code_tax_org"),
                        rs.getString("number_tax_registration"),
                        rs.getInt("state"),
                        rs.getString("code_citizenship"),
                        rs.getString("birth_place"),
                        rs.getString("code_capacity"),
                        rs.getDate("capacity_status_date"),
                        rs.getString("capacity_status_place"),
                        rs.getString("num_certif_capacity"),
                        rs.getString("phone_home"),
                        rs.getString("phone_mobile"),
                        rs.getString("email_address"),
                        rs.getString("pension_sertif_serial"),
                        rs.getString("code_gender"),
                        rs.getString("code_nation"),
                        rs.getString("code_birth_region"),
                        rs.getString("code_birth_distr"),
                        rs.getString("type_document"),
                        rs.getDate("passport_date_expiration"),
                        rs.getString("code_adr_region"),
                        rs.getString("code_adr_distr"),
                        rs.getString("inps"),
                        rs.getString("family"),
                        rs.getString("first_name"),
                        rs.getString("patronymic"),
                        rs.getString("code_country"),
                        rs.getString("code_resident"),
                        rs.getString("family_local"),
                        rs.getString("first_name_local"),
                        rs.getString("patronymic_local"),
                        rs.getString("pass_place_region"),
                        rs.getString("pass_place_district"),
                        rs.getString("union_id")
                );
                person.setPinfl(rs.getString("pinfl"));
                if(CustomerUtils.isAtaccamaOn()) {
                person.setSign_public_official(rs.getString("sign_public_official"));
                person.setPo_job_title(rs.getString("po_job_title"));
                person.setPo_welfare_source(rs.getString("po_welfare_source"));
                person.setPo_other_data(rs.getString("po_other_data"));
                }
            }
        } catch (Exception e) {
            logger.error(CheckNull.getPstr(e));
        } finally {
            DbUtils.closeResultSet(rs);
            DbUtils.closeStmt(ps);
        }
        return person;
    }

    @Override
    public Person create(Person person) throws Exception {
        Connection c = null;
        Person createdPerson = null;
        try {
            c = ConnectionPool.getConnection(alias);
            createdPerson = create(c, person);

            if (createdPerson.getIdSap() == null) {
                SapFactory.instance().getCustomerService().createPerson(createdPerson);
            }
            c.commit();
        } catch (SapException e) {
            logger.error(CheckNull.getPstr(e));
            new SapLogger.Builder().alias(alias).createPerson(createdPerson).state(SapLogger.STATE_ERROR).message(e.getMessage())
                    .build().log();
            DbUtils.rollback(c);
            throw new Exception(e.getMessage());
        } catch (Exception e) {
            logger.error(CheckNull.getPstr(e));
            DbUtils.rollback(c);
            throw new Exception(e.getMessage());
        } finally {
            ConnectionPool.close(c);
        }

        return createdPerson;
    }

    @Override
    public Person create(Connection c, Person person) throws SapException, Exception {
        PreparedStatement ps = null;
        try {
            if (person.getId() == null) {
                ps = c.prepareStatement("select SEQ_CLIENT_ADDINFO_PERSON.NEXTVAL id from dual");
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    person.setId(rs.getString("id"));
                }
                ps.close();
                //String sqltext="";
                if(CustomerUtils.isAtaccamaOn()) {
                    ps = c.prepareStatement("INSERT INTO client_addinfo_person (id, name, " +
                            "birthday, post_address, passport_type, passport_serial, " +
                            "passport_number, passport_place_registration, " +
                            "passport_date_registration, code_place_regist, " +
                            "date_registration, number_registration_doc, " +
                            "code_tax_org, number_tax_registration, code_sector, " +
                            "code_organ_direct, code_bank, account, code_class_credit, " +
                            "state, kod_err, file_name, code_citizenship, " +
                            "birth_place, code_capacity, capacity_status_date, " +
                            "capacity_status_place, num_certif_capacity, phone_home, " +
                            "phone_mobile, email_address, pension_sertif_serial, " +
                            "code_gender, code_nation, code_birth_region, code_birth_distr, " +
                            "type_document, passport_date_expiration, code_adr_region, " +
                            "code_adr_distr, inps, family, first_name, patronymic, " +
                            "code_country, code_resident, pass_place_region, pass_place_district, "+
                            "family_local, first_name_local, patronymic_local, pinfl, "+
                            "sign_public_official, po_job_title, po_welfare_source, po_other_data) " +
                             
                            "VALUES (?,?,?,?,?" +//5
                            ",?,?,?,?,?" +//10
                            ",?,?,?,?,?" +//15
                            ",?,?,?,?,?" +//20
                            ",?,?,?,?,?" +//25
                            ",?,?,?,?,?" +//30
                            ",?,?,?,?,?" +//35
                            ",?,?,?,?,?" +//40
                            ",?,?,?,?,?" +//45
                            ",?,?,?,?,?" +//50
                            ",?,?,?,?,?,?)");//
                	
                } else {
                    ps = c.prepareStatement("INSERT INTO client_addinfo_person (id, name, " +
                            "birthday, post_address, passport_type, passport_serial, " +
                            "passport_number, passport_place_registration, " +
                            "passport_date_registration, code_place_regist, " +
                            "date_registration, number_registration_doc, " +
                            "code_tax_org, number_tax_registration, code_sector, " +
                            "code_organ_direct, code_bank, account, code_class_credit, " +
                            "state, kod_err, file_name, code_citizenship, " +
                            "birth_place, code_capacity, capacity_status_date, " +
                            "capacity_status_place, num_certif_capacity, phone_home, " +
                            "phone_mobile, email_address, pension_sertif_serial, " +
                            "code_gender, code_nation, code_birth_region, code_birth_distr, " +
                            "type_document, passport_date_expiration, code_adr_region, " +
                            "code_adr_distr, inps, family, first_name, patronymic, " +
                            "code_country, code_resident, pass_place_region, pass_place_district, "+
                            "family_local, first_name_local, patronymic_local, pinfl) " +
                             
                            "VALUES (?,?,?,?,?" +//5
                            ",?,?,?,?,?" +//10
                            ",?,?,?,?,?" +//15
                            ",?,?,?,?,?" +//20
                            ",?,?,?,?,?" +//25
                            ",?,?,?,?,?" +//30
                            ",?,?,?,?,?" +//35
                            ",?,?,?,?,?" +//40
                            ",?,?,?,?,?" +//45
                            ",?,?,?,?,?" +//50
                            ",?,?)");//
                }
                	

                ps.setString(1, person.getId());
                ps.setString(2, person.personName());
                ps.setDate(3, person.getBirthday() != null ? new java.sql.Date(person.getBirthday().getTime()) : null);
                ps.setString(4, person.getPost_address());
                ps.setString(5, person.getPassport_type());
                ps.setString(6, person.getPassport_serial());
                ps.setString(7, person.getPassport_number());
                ps.setString(8, person.getPassport_place_registration());
                ps.setDate(9, person.getPassport_date_registration() != null ? new java.sql.Date(person.getPassport_date_registration().getTime()) : null);
                ps.setString(10, person.getCode_place_regist());
                ps.setDate(11, person.getDate_registration() != null ? new java.sql.Date(person.getDate_registration().getTime()) : null);
                ps.setString(12, person.getNumber_registration_doc());
                ps.setString(13, person.getCode_tax_org());
                ps.setString(14, person.getNumber_tax_registration());
                ps.setString(15, null);
                ps.setString(16, null);
                ps.setString(17, null);
                ps.setString(18, null);
                ps.setString(19, null);
                ps.setInt(20, person.getState());
                ps.setInt(21, 0);
                ps.setString(22, null);
                ps.setString(23, person.getCode_citizenship());
                ps.setString(24, person.getBirth_place());
                ps.setString(25, person.getCode_capacity());
                ps.setDate(26, person.getCapacity_status_date() != null ? new java.sql.Date(person.getCapacity_status_date().getTime()) : null);
                ps.setString(27, person.getCapacity_status_place());
                ps.setString(28, person.getNum_certif_capacity());
                ps.setString(29, person.getPhone_home());
                ps.setString(30, person.getPhone_mobile());
                ps.setString(31, person.getEmail_address());
                ps.setString(32, person.getPension_sertif_serial());
                ps.setString(33, person.getCode_gender());
                ps.setString(34, person.getCode_nation());
                ps.setString(35, person.getCode_birth_region());
                ps.setString(36, person.getCode_birth_distr());
                ps.setString(37, person.getType_document());
                ps.setDate(38, person.getPassport_date_expiration() != null ? new java.sql.Date(person.getPassport_date_expiration().getTime()) : null);
                ps.setString(39, person.getCode_adr_region());
                ps.setString(40, person.getCode_adr_distr());
                ps.setString(41, person.getInps());
                ps.setString(42, person.getFamily());
                ps.setString(43, person.getFirst_name());
                ps.setString(44, person.getPatronymic());
                ps.setString(45, person.getCode_country());
                ps.setString(46, person.getCode_resident());
                ps.setString(47, person.getPass_place_region());
                ps.setString(48, person.getPass_place_district());
                ps.setString(49, person.getFamily_local());
                ps.setString(50, person.getFirst_name_local());
                ps.setString(51, person.getPatronymic_local());
                ps.setString(52, person.getPinfl());
                if(CustomerUtils.isAtaccamaOn()) {
                ps.setString(53, person.getSign_public_official());
                ps.setString(54, person.getPo_job_title());
                ps.setString(55, person.getPo_welfare_source());
                ps.setString(56, person.getPo_other_data());
                }
                ps.executeUpdate();
            }


        } finally {
            DbUtils.closeStmt(ps);
        }

        return person;
    }

    @Override
    public int update(Person person) throws Exception {
        Connection c = null;
        int count = 0;
        try {
            c = ConnectionPool.getConnection(alias);
            update(c, person);
//			new SapLogger.Builder()
//					.alias(alias)
//					.editPerson(person)
//					.state(SapLogger.STATE_INSERTED)
//			.build()
//			.log();
            c.commit();
        }
//		catch (SapException e) {
//			logger.error(CheckNull.getPstr(e));
//			int state = SapLogger.STATE_ERROR;
//			boolean isEmergencyModeOn = PartnerUtils.isEmergencyModeOn(); 
//			if(isEmergencyModeOn) {
//				state = SapLogger.STATE_EMM_MODE;
//			}
//			new SapLogger.Builder()
//					.alias(alias)
//					.editPerson(person)
//					.state(state)
//					.message(e.getMessage())
//			.build()
//			.log();
//			if(!isEmergencyModeOn) {
//				DbUtils.rollback(c);
//				throw new Exception(e.getMessage());
//			}
//			
//		}
        catch (Exception e) {
            logger.error(CheckNull.getPstr(e));
            throw new Exception(e.getMessage());
        } finally {
            ConnectionPool.close(c);
        }
        return count;
    }

    @Override
    public int update(Connection c, Person person) throws SapException, Exception {
        PreparedStatement ps = null;
        int count = 0;
        try {
        	if(CustomerUtils.isAtaccamaOn()) {
            ps = c.prepareStatement("update client_addinfo_person set name=?, " +
                    "birthday=?, post_address=?, passport_type=?, passport_serial=?, " +
                    "passport_number=?, passport_place_registration=?, " +
                    "passport_date_registration=?, code_place_regist=?, " +
                    "date_registration=?, number_registration_doc=?, " +
                    "code_tax_org=?, number_tax_registration=?, code_sector=?, " +
                    "code_organ_direct=?, code_bank=?, account=?, code_class_credit=?, " +
                    "code_citizenship=?, " +
                    "birth_place=?, code_capacity=?, capacity_status_date=?, " +
                    "capacity_status_place=?, num_certif_capacity=?, phone_home=?, " +
                    "phone_mobile=?, email_address=?, pension_sertif_serial=?, " +
                    "code_gender=?, code_nation=?, code_birth_region=?, code_birth_distr=?, " +
                    "type_document=?, passport_date_expiration=?, code_adr_region=?, " +
                    "code_adr_distr=?, inps=?, family=?, first_name=?, patronymic=?, " +
                    "code_country=?, code_resident=?, pass_place_region=?, pass_place_district=?, " +
                    "family_local=?, first_name_local=?, patronymic_local=?, pinfl=?, sign_public_official=?, " +
                    "po_job_title=?, po_welfare_source=?, po_other_data=? " +
                    "where id=?");
        	} else {
        		ps = c.prepareStatement("update client_addinfo_person set name=?, " +
                        "birthday=?, post_address=?, passport_type=?, passport_serial=?, " +
                        "passport_number=?, passport_place_registration=?, " +
                        "passport_date_registration=?, code_place_regist=?, " +
                        "date_registration=?, number_registration_doc=?, " +
                        "code_tax_org=?, number_tax_registration=?, code_sector=?, " +
                        "code_organ_direct=?, code_bank=?, account=?, code_class_credit=?, " +
                        "code_citizenship=?, " +
                        "birth_place=?, code_capacity=?, capacity_status_date=?, " +
                        "capacity_status_place=?, num_certif_capacity=?, phone_home=?, " +
                        "phone_mobile=?, email_address=?, pension_sertif_serial=?, " +
                        "code_gender=?, code_nation=?, code_birth_region=?, code_birth_distr=?, " +
                        "type_document=?, passport_date_expiration=?, code_adr_region=?, " +
                        "code_adr_distr=?, inps=?, family=?, first_name=?, patronymic=?, " +
                        "code_country=?, code_resident=?, pass_place_region=?, pass_place_district=?, " +
                        "family_local=?, first_name_local=?, patronymic_local=?, pinfl=? " +
                        "where id=?");
        	}
            ps.setString(1, person.personName());
            ps.setDate(2, person.getBirthday() != null ? new java.sql.Date(person.getBirthday().getTime()) : null);
            ps.setString(3, person.getPost_address());
            ps.setString(4, person.getPassport_type());
            ps.setString(5, person.getPassport_serial());
            ps.setString(6, person.getPassport_number());
            ps.setString(7, person.getPassport_place_registration());
            ps.setDate(8, person.getPassport_date_registration() != null ? new java.sql.Date(person.getPassport_date_registration().getTime()) : null);
            ps.setString(9, person.getCode_place_regist());
            ps.setDate(10, person.getDate_registration() != null ? new java.sql.Date(person.getDate_registration().getTime()) : null);
            ps.setString(11, person.getNumber_registration_doc());
            ps.setString(12, person.getCode_tax_org());
            ps.setString(13, person.getNumber_tax_registration());
            ps.setString(14, null);
            ps.setString(15, null);
            ps.setString(16, null);
            ps.setString(17, null);
            ps.setString(18, null);

            ps.setString(19, person.getCode_citizenship());
            ps.setString(20, person.getBirth_place());
            ps.setString(21, person.getCode_capacity());
            ps.setDate(22, person.getCapacity_status_date() != null ? new java.sql.Date(person.getCapacity_status_date().getTime()) : null);
            ps.setString(23, person.getCapacity_status_place());
            ps.setString(24, person.getNum_certif_capacity());
            ps.setString(25, person.getPhone_home());
            ps.setString(26, person.getPhone_mobile());
            ps.setString(27, person.getEmail_address());
            ps.setString(28, person.getPension_sertif_serial());
            ps.setString(29, person.getCode_gender());
            ps.setString(30, person.getCode_nation());
            ps.setString(31, person.getCode_birth_region());
            ps.setString(32, person.getCode_birth_distr());
            ps.setString(33, person.getType_document());
            ps.setDate(34, person.getPassport_date_expiration() != null ? new java.sql.Date(person.getPassport_date_expiration().getTime()) : null);
            ps.setString(35, person.getCode_adr_region());
            ps.setString(36, person.getCode_adr_distr());
            ps.setString(37, person.getInps());
            ps.setString(38, person.getFamily());
            ps.setString(39, person.getFirst_name());
            ps.setString(40, person.getPatronymic());
            ps.setString(41, person.getCode_country());
            ps.setString(42, person.getCode_resident());
            ps.setString(43, person.getPass_place_region());
            ps.setString(44, person.getPass_place_district());
            ps.setString(45, person.getFamily_local());
            ps.setString(46, person.getFirst_name_local());
            ps.setString(47, person.getPatronymic_local());
            ps.setString(48, person.getPinfl());
            if(CustomerUtils.isAtaccamaOn()) {
            ps.setString(49, person.getSign_public_official());
            ps.setString(50, person.getPo_job_title());
            ps.setString(51, person.getPo_welfare_source());
            ps.setString(52, person.getPo_other_data());
            ps.setString(53, person.getId());
            } else {
            	ps.setString(49, person.getId());
            }
            count = ps.executeUpdate();

            //SapFactory.instance().getCustomerService().updatePartner(Mappers.mapPersonToBp(person));
        } finally {
            DbUtils.closeStmt(ps);
        }
        return count;
    }

    @Override
    public int remove(Connection c, Person item) {
        return 0;
    }

    @Override
    public int remove(Person item) {
        return 0;
    }

    @Override
    public <T1 extends Person> void setFilter(T1 filter) {
        this.filter = filter;
    }

    public Person getByUnionId(String branch, String union_id){
        Connection c = null;
        Person person = null;
        try {
            if (alias != null) {
                c = ConnectionPool.getConnection(alias);
            } else {
                c = ConnectionPool.getConnection();
            }
            person = getByUnionId(c, branch, union_id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.close(c);
        }
        return person;
    }

    private Person getByUnionId(Connection c,String branch,String union_id){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Person person = null;
        try {
            ps = c.prepareStatement("select * from client_addinfo_person where union_id=?");
            //ps.setString(1, branch);
            ps.setString(1, union_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                person = new Person(
                        rs.getString("branch"),
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getDate("birthday"),
                        rs.getString("post_address"),
                        rs.getString("passport_type"),
                        rs.getString("passport_serial"),
                        rs.getString("passport_number"),
                        rs.getString("passport_place_registration"),
                        rs.getDate("passport_date_registration"),
                        rs.getString("code_place_regist"),
                        rs.getDate("date_registration"),
                        rs.getString("number_registration_doc"),
                        rs.getString("code_tax_org"),
                        rs.getString("number_tax_registration"),
                        rs.getInt("state"),
                        rs.getString("code_citizenship"),
                        rs.getString("birth_place"),
                        rs.getString("code_capacity"),
                        rs.getDate("capacity_status_date"),
                        rs.getString("capacity_status_place"),
                        rs.getString("num_certif_capacity"),
                        rs.getString("phone_home"),
                        rs.getString("phone_mobile"),
                        rs.getString("email_address"),
                        rs.getString("pension_sertif_serial"),
                        rs.getString("code_gender"),
                        rs.getString("code_nation"),
                        rs.getString("code_birth_region"),
                        rs.getString("code_birth_distr"),
                        rs.getString("type_document"),
                        rs.getDate("passport_date_expiration"),
                        rs.getString("code_adr_region"),
                        rs.getString("code_adr_distr"),
                        rs.getString("inps"),
                        rs.getString("family"),
                        rs.getString("first_name"),
                        rs.getString("patronymic"),
                        rs.getString("code_country"),
                        rs.getString("code_resident"),
                        rs.getString("family_local"),
                        rs.getString("first_name_local"),
                        rs.getString("patronymic_local"),
                        rs.getString("pass_place_region"),
                        rs.getString("pass_place_district"),
                        rs.getString("union_id")
                );
                person.setPinfl(rs.getString("pinfl"));
                if(CustomerUtils.isAtaccamaOn()) {
                person.setSign_public_official(rs.getString("sign_public_official"));
                person.setPo_job_title(rs.getString("po_job_title"));
                person.setPo_welfare_source(rs.getString("po_welfare_source"));
                person.setPo_other_data(rs.getString("po_other_data"));
                }
            }
        } catch (Exception e) {
            logger.error(CheckNull.getPstr(e));
        } finally {
            DbUtils.closeResultSet(rs);
            DbUtils.closeStmt(ps);
        }
        return person;
    }
}
