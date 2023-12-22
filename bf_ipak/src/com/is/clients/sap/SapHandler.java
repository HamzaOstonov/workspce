package com.is.clients.sap;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.is.customer_.CustomerFactory;
import com.is.customer_.local.service.CustomerActionInterface;
import com.is.customer_.sap.service.RoleInterface;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.is.ISLogger;
import com.is.base.Dao;
import com.is.base.utils.DbUtils;
import com.is.client_personmap.PersonMapService;
import com.is.client_personmap.PersonMapUtil;
import com.is.client_personmap.model.Person;
import com.is.client_personmap.model.PersonMap;
import com.is.client_sap.Mappers;
import com.is.client_sap.SapEnum;
import com.is.client_sap.SapFactory;
import com.is.client_sap.abstraction.AbstractSapOrganizationService;
import com.is.client_sap.abstraction.AbstractSapRelationService;
import com.is.client_sap.abstraction.AbstractSapRoleService;
import com.is.client_sap.abstraction.ICustomerService;
import com.is.client_sap.exceptions.SapException;
import com.is.clients.TimerSession;
import com.is.clients.dao.ClientDao;
import com.is.clients.dao.DaoFactory;
import com.is.clients.models.ClientJ;
import com.is.clients.utils.ClientUtil;
import com.is.customer_.core.model.Customer;
import com.is.customer_.entrepreneur.IndividualEnterpreneur;
import com.is.customer_.local.service.InternalControlService;
import com.is.customer_.sap.EmergencyMode;
import com.is.customer_.sap.service.CustomerGatewayDecorator;
import com.is.customer_.sap.service.RelationshipGateway;
import com.is.customer_.sap.service.SAPServiceFactory;
import com.is.customer_.sap.service.impl.CustomerGatewayDecoratorImpl;
import com.is.customer_.sap.service.impl.RoleInterfaceImpl;
import com.is.utils.CheckNull;
import com.is.utils.Res;

import org.apache.poi.util.StringUtil;
import relationships.NCI.com.ipakyulibank.BPRelResp;
import relationships.NCI.com.ipakyulibank.BPRelWithDate;

public class SapHandler {
    private static Logger logger = Logger.getLogger(SapHandler.class);

    public static String IP_MODE_CORRECT = "correct";
    public static String IP_MODE_CREATE = "create";
    private static ICustomerService personService = SapFactory.instance().getCustomerService();
    private static AbstractSapRoleService roleService = SapFactory.instance().getRoleService();
    private static AbstractSapRelationService relationService = SapFactory.instance().getRelationService();
    private static CustomerGatewayDecorator customerGatewayService = SAPServiceFactory.getInstance().getCustomerGatewayDecorator();
    private static RelationshipGateway relationshipGateway = SAPServiceFactory.getInstance().getRelationshipGateway();
    private static CustomerActionInterface customerDatabaseService = CustomerFactory.getInstance(null).getCustomerActionService();

    public static void makeSapRequest(ClientJ client, Connection connection) throws SapException, Exception {
        AbstractSapOrganizationService<ClientJ> organizationService = SapFactory.instance().getOrganizationService();

        if (!(client.getCode_type().equals(ClientUtil.CODE_TYPE_IP) || client.getCode_type().equals(ClientUtil.CODE_TYPE_SE) )) {
            if (client.getId_sap() != null) {
                organizationService.orgRequestEdit(client);
            } else {
                organizationService.createIfAbsent(client);
            }
        } else {
            // 05-03-2018
            TimerSession.setAttribute("branch", client.getBranch());
            TimerSession.setAttribute("uid", client.getNibbd_emp_id());

            // Создается физическое лицо и отправляется в SAP
            Customer customer = connection == null ?
                    customerDatabaseService.getCustomer(client.getBranch(), client.getId_client(), client.getCode_subject()) :
                    customerDatabaseService.getCustomer(connection, client.getBranch(),client.getId_client(),client.getCode_subject());

            logger.error("Sap Handler Customer -> " + customer);

            if (!StringUtils.isBlank(customer.getP_family_local()))
                customer.setP_family(customer.getP_family_local());
            if (!StringUtils.isBlank(customer.getP_first_name_local()))
                customer.setP_first_name(customer.getP_first_name_local());
            if (!StringUtils.isBlank(customer.getP_patronymic_local()))
                customer.setP_patronymic(customer.getP_patronymic_local());
            if (StringUtils.isEmpty(customer.getP_number_tax_registration()))
                customer.setP_number_tax_registration(client.getJ_number_tax_registration());

            //logger.error("Sap Handler Customer after -> " + customer);

            if (StringUtils.isBlank(customer.getP_family()))
                return;
            if (StringUtils.isBlank(customer.getP_first_name()))
                return;
            if (StringUtils.isBlank(customer.getP_family_local()))
                return;
            if (StringUtils.isBlank(customer.getP_first_name_local()))
                return;
            if (StringUtils.isBlank(customer.getP_patronymic_local()))
                return;


            customer = customerGatewayService.create(customer);

            // Создать роль
            //RoleInterface roleInterface = SAPServiceFactory.getInstance().getRoleService();
            //roleInterface.createRole(customer, "BP45");

            // Найти отношения
            // И проставить SAPId для юридического лица
            boolean isExists = false;
            BPRelResp[] relations = relationService.getRelationsByPersonId(customer.getIdSap());
            for (BPRelResp relation : relations) {
                BPRelWithDate currentRelation = relation.getBp_relationships();
                if (currentRelation.getRel_type().equalsIgnoreCase(SapEnum.REL_TYPE_IP.getSapValue())) {
                    client.setId_sap(relation.getBp_id_01_type().equals("2") ? relation.getBp_id_01()
                            : relation.getBp_id_02_type().equals("2") ? relation.getBp_id_02() : null);
                    isExists = true;
                }
            }

            // Создается юридическое лицо
            // Временно проставить ОКЕД
            client.setJ_code_sector("0");
            organizationService.createIfAbsentWithoutInn(client);

            // Создается отношение между ФЛ/ЮЛ
            PersonMap personMap = new PersonMap();
            personMap.setClient_id(client.getId_client());
            personMap.setBranch(client.getBranch());
            personMap.setPerson_id(customer.getId());
            personMap.setPerson_type(PersonMapUtil.PERSONTYPE_P);
            personMap.setPerson(new Person());
            personMap.getPerson().setBranch(customer.getBranch());
            personMap.getPerson().setId(customer.getId());
            personMap.getPerson().setIdSap(customer.getIdSap());
            personMap.setPerson_kind(PersonMapUtil.PERSONKIND_IP);

            if (isExists) {
                relationService.modifyRelation(personMap);
            } else {
                relationService.createRelation(personMap);
            }

        }
    }

    public static PersonMap handleIpRelation(ClientJ client, int clientAction) throws SapException, Exception {
        Person ipPerson = new Person(client);
        Dao<PersonMap> personMapDao = DaoFactory.instance().getPersonMapDao();
        personMapDao.setFilter(new PersonMap(client.getId_client()));
        List<PersonMap> personMaps = personMapDao.getList();

        int personActionId = PersonMapService.ACTION_CONFIRM_HOP;
        int mapActionId = PersonMapService.ACTION_CONFIRM_HOP;
        PersonMap map = PersonMap.ipInstance(client.getBranch());
        map.setClient_id(client.getId_client());
        map.setPerson(ipPerson);

        for (PersonMap mapItem : personMaps) {
            if (mapItem.getPerson_kind().equals(PersonMapUtil.PERSONKIND_IP)) {
                map.setId(mapItem.getId());
                map.setState(mapItem.getState());
                map.setPerson_id(mapItem.getPerson_id());
                map.getPerson().setId(mapItem.getPerson_id());
                mapActionId = mapItem.getState() == PersonMapService.STATE_CONFIRMED ? PersonMapService.ACTION_MODIFY
                        : PersonMapService.ACTION_CONFIRM;
                personActionId = mapItem.getPerson().getState() == PersonMapService.STATE_CONFIRMED
                        ? PersonMapService.ACTION_MODIFY : PersonMapService.ACTION_CONFIRM;

            }
        }

        PersonMapService personMapService = PersonMapService.instance();

        Res personAction = personMapService.personAction(ipPerson, personActionId);
        if (personAction.getCode() != 0) {
            throw new Exception(personAction.getName());
        }
        map.setPerson_id(personAction.getName());
        Res mapAction = personMapService.relationAction(map, mapActionId);
        if (mapAction.getCode() != 0) {
            throw new Exception(mapAction.getName());
        }
        return map;
    }

    public static Res sendUvk(Map<String, Object> uvkMap) {
        Res res = new Res(1, "ok");
        String clientType = (String) uvkMap.get("code_subject");
        for (Map.Entry<String, Object> entry : uvkMap.entrySet()) {
            ISLogger.getLogger().error("uvkMap key = " + entry.getKey() + ", value = " + entry.getValue());
            if (entry.getValue() instanceof List) {
                ISLogger.getLogger().error("uvkMap list.size = " + ((List) entry.getValue()).size());
            }
        }

        if (clientType == null) {
            res.setCode(2);
            res.setName("no client type!");
        } else if (clientType.equals("J") || clientType.equalsIgnoreCase("I")) {
            String alias = (String) uvkMap.get("alias");
            String idClient = (String) uvkMap.get("id_client");
            String branch = DbUtils.getBranchBySchema(alias);
            ClientJ client = ClientDao.getInstance(alias).getItemByStringId(branch, idClient);
            try {
                SapFactory.instance().getOrganizationService().createIfAbsentUvk(client, uvkMap);
            } catch (Exception e) {
                logger.error(CheckNull.getPstr(e));
                res.setCode(2);
                res.setName(e.getMessage());
            }
        } else if (clientType.equals("P")) {
            try {
                InternalControlService.correct(uvkMap);
            } catch (Exception e) {
                logger.error(CheckNull.getPstr(e));
                res.setCode(2);
                res.setName(e.getMessage());
            }
        } else if (clientType.equals("N")) {
            try {
                String id = "N" + uvkMap.get("id_client");
                uvkMap.put("id_client", id);
                InternalControlService.correct(uvkMap);
            } catch (Exception e) {
                logger.error(CheckNull.getPstr(e));
                res.setCode(2);
                res.setName(e.getMessage());
            }
        } else {
            res.setCode(2);
            res.setName("undefined client type! " + clientType);
        }
        return res;
    }

    /**
     * determine whether client has IP relation if yes, updates client by seting
     * businesspartner
     */
    public static boolean checkIP(ClientJ client) throws Exception {
        boolean hasRelation = false;
        BPRelResp relation = SapFactory.instance().getRelationService().getIPcodeByIdNci("J" + client.getId_client(),
                client.getBranch());
        // if(relation == null) {
        // relation =
        // SapRelationService.getIPcodeByIdSap("J"+client.getId_client(),
        // client.getBranch());
        // }
        if (relation != null) {
            ISLogger.getLogger().error("relType = " + relation.getBp_id_02_type());
            ISLogger.getLogger().error("idSAp = " + relation.getBp_id_02());
            Customer bp = SAPServiceFactory.getInstance().getBusinessPartnerService().get(null, null,
                    relation.getBp_id_02());
            if (bp != null) {
                ISLogger.getLogger().error("bp is not null !!");
                if (client.getIndividualEnterpreneur() == null) {
                    client.setIndividualEnterpreneur(new IndividualEnterpreneur(bp, IP_MODE_CORRECT));
                }
            }
            client.updateIndividualEnterpreneur();
            hasRelation = true;
        } else {
            client.createIndividualEnterpreneur();
        }
        return hasRelation;
    }

    public static String hasIPRelation(ClientJ client) throws Exception {
        BPRelResp relation = SapFactory.instance().getRelationService().getIPcodeByIdNci("J" + client.getId_client(),
                client.getBranch());

        return relation != null ? relation.getBp_id_01() : null;
    }

    public static void fetchIp(ClientJ client, String idOrgSap) {
        try {
            BPRelResp relation = null;
            if (idOrgSap != null) {
                relation = SapFactory.instance().getRelationService().getIPcodeByIdSap(idOrgSap);
            } else {
                relation = SapFactory.instance().getRelationService().getIPcodeByIdNci("J" + client.getId_client(),
                        client.getBranch());
            }
            String idSap = null;
            if (relation != null) {
                if (relation.getBp_id_01_type().equals(SapEnum.CLIENT_CUST.getSapValue())) {
                    idSap = relation.getBp_id_01();
                } else {
                    idSap = relation.getBp_id_02();
                }
                Customer bp = SAPServiceFactory.getInstance().getBusinessPartnerService().get(null, null, idSap);
                client.setIndividualEnterpreneur(new IndividualEnterpreneur(bp, IP_MODE_CORRECT));
                client.setIndividualEnterpreneurClean(new IndividualEnterpreneur(
                        client.getIndividualEnterpreneur().getCustomer(), SapHandler.IP_MODE_CORRECT));
                client.updateIndividualEnterpreneur();
            }
        } catch (Exception e) {
            ISLogger.getLogger().error(CheckNull.getPstr(e));
        }
    }

    public static boolean findBpForIP(ClientJ client) {
        boolean found = false;
        Customer bp = new Customer();
        bp.setP_birthday(client.getP_birthday());
        bp.setP_family(client.getP_last_name_cyr());
        bp.setP_first_name(client.getP_first_name_cyr());
        bp.setP_patronymic(client.getP_patronymic_cyr());

        try {
            List<Customer> list = SapFactory.instance().getCustomerService().searchBusinessPartners(bp);
            if (!list.isEmpty()) {
                found = true;
                client.setIndividualEnterpreneurClean(new IndividualEnterpreneur(list.get(0), IP_MODE_CORRECT));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return found;
    }

}
