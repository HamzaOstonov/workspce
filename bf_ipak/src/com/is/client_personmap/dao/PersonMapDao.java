package com.is.client_personmap.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.is.ConnectionPool;
import com.is.base.Dao;
import com.is.base.SqlScripts;
import com.is.base.utils.DbUtils;
import com.is.client_personmap.PersonMapUtil;
import com.is.client_personmap.model.FounderCapital;
import com.is.client_personmap.model.FounderMap;
import com.is.client_personmap.model.LegalEntity;
import com.is.client_personmap.model.Person;
import com.is.client_personmap.model.PersonMap;
import com.is.client_sap.SapFactory;
import com.is.client_sap.exceptions.SapCustomerException;
import com.is.client_sap.exceptions.SapRelationException;
import com.is.client_sap.exceptions.SapRoleException;
import com.is.clients.dao.DaoFactory;
import com.is.clients.models.SapLogger;
import com.is.delta_relations.DeltaRelation;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;

public class PersonMapDao implements Dao<PersonMap> {
    private static Logger logger = Logger.getLogger(PersonMapDao.class);

    private String alias;
    private PersonMap filter;
    private Dao<Person> personDao;
    private Dao<LegalEntity> legalEntityDao;
    private Dao<FounderCapital> capitalDao;
    private Dao<FounderMap> founderMapDao;

    private PersonMapDao(String alias, DaoFactory daoFactory) {
        this.alias = alias;
        // Конткных лиц создает Андрей, модуль customer_, поэтому здесь
        personDao = daoFactory.getPersonDao();
        legalEntityDao = daoFactory.getLegalEntityDao();
        capitalDao = daoFactory.getCapitalDao();
        founderMapDao = daoFactory.getFounderMapDao();
    }

    public static PersonMapDao getInstance(String alias, DaoFactory daoFactory) {
        return new PersonMapDao(alias, daoFactory);
    }

    @Override
    public List<PersonMap> getList() {
        List<PersonMap> list = new ArrayList<PersonMap>();
        Connection c = null;
        PreparedStatement ps = null;
        CallableStatement init = null;
        ResultSet rs = null;
        PersonMap personMap = null;
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM client_addinfo_person_map where client_id = ? " +
                "and branch = info.getBranch and state != 2");

        try {
            if (alias == null) {
                //logger.error("Schema Identifier " + filter.getBranch());
                String schema = DbUtils.getSchemaByBranch(filter.getBranch());
                c = ConnectionPool.getConnection(schema);
            } else {
                c = ConnectionPool.getConnection(alias);
            }
            init = c.prepareCall(SqlScripts.INFO_INIT.getSql());
            init.execute();

            ps = c.prepareStatement(sb.toString());
            ps.setString(1, filter.getClient_id());
            rs = ps.executeQuery();
            while (rs.next()) {
                personMap = new PersonMap(rs.getLong("id"), rs.getString("branch"), rs.getString("client_id"),
                        rs.getString("person_type"), rs.getString("person_id"), rs.getString("person_kind"),
                        rs.getString("prson_name"), false, rs.getInt("state"));

                if (personMap.getPerson_kind().equals(PersonMapUtil.PERSONKIND_FOUNDER) || personMap.getPerson_kind().equals(PersonMapUtil.PERSONKIND_BENEFICIARY)) {
                    personMap.setCapital(capitalDao.getItemByLongId(c, null, personMap.getId()));
                }
                if (personMap.getPerson_type().equals(PersonMapUtil.PERSONTYPE_J)) {
                    LegalEntity legalEntity = legalEntityDao.getItemByStringId(c, null, personMap.getPerson_id());
                    personMap.setLegalEntity(legalEntity);
                    personMap.setUnion_id(legalEntity.getUnion_id());
                } else if (personMap.getPerson_type().equals(PersonMapUtil.PERSONTYPE_P)) {
                    Person person = personDao.getItemByStringId(c, null, personMap.getPerson_id());
                    if (person != null) {
                        personMap.setPerson(person);
                        personMap.setUnion_id(person.getUnion_id());
                    }
                }

                list.add(personMap);
            }
        } catch (Exception e) {
            logger.error(CheckNull.getPstr(e));
        } finally {
            DbUtils.closeResultSet(rs);
            DbUtils.closeStmt(init);
            DbUtils.closeStmt(ps);
            ConnectionPool.close(c);
        }
        return list;
    }

    @Override
    public List<PersonMap> getListWithPaging(int pageIndex, int pageSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T1 extends PersonMap> void setFilter(T1 filter) {
        this.filter = filter;
    }

    @Override
    public List<FilterField> getFilterFields() {
        List<FilterField> flfields = new ArrayList<FilterField>();

        if (!CheckNull.isEmpty(filter.getClient_id())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "id client_id=?", filter.getClient_id()));
        }
        if (!CheckNull.isEmpty(filter.getBranch())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "id branch=?", filter.getBranch()));
        }
        return flfields;
    }

    @Override
    public int getCount() {
        throw new UnsupportedOperationException();
    }

    @Override
    public PersonMap getItemByLongId(String branch, long itemId) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        PersonMap personMap = null;
        try {
            c = ConnectionPool.getConnection(alias);
            ps = c.prepareStatement("SELECT * FROM client_addinfo_person_map where id=?");
            ps.setLong(1, itemId);
            rs = ps.executeQuery();

            if (rs.next()) {
                personMap = new PersonMap(rs.getLong("id"), rs.getString("branch"), rs.getString("client_id"),
                        rs.getString("person_type"), rs.getString("person_id"), rs.getString("person_kind"),
                        rs.getString("prson_name"), false, rs.getInt("state"));

                if (personMap.getPerson_type().equals(PersonMapUtil.PERSONTYPE_J)) {
                    LegalEntity legalEntity = legalEntityDao.getItemByStringId(c, null, personMap.getPerson_id());
                    personMap.setLegalEntity(legalEntity);
                    personMap.setUnion_id(legalEntity.getUnion_id());
                } else if (personMap.getPerson_type().equals(PersonMapUtil.PERSONTYPE_P)) {
                    Person person = personDao.getItemByStringId(c, null, personMap.getPerson_id());
                    personMap.setPerson(person);
                    personMap.setUnion_id(person.getUnion_id());
                }
                if (personMap.getPerson_kind().equals(PersonMapUtil.PERSONKIND_FOUNDER)) {
                    personMap.setCapital(capitalDao.getItemByLongId(c, null, personMap.getId()));
                }
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            ConnectionPool.close(c);
            DbUtils.closeStmt(ps);
            DbUtils.closeResultSet(rs);
        }

        return personMap;
    }

    @Override
    public PersonMap getItemByStringId(String branch, String itemId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public PersonMap getItemByLongId(Connection c, String branch, long itemId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public PersonMap getItemByStringId(Connection c, String branch, String itemId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public PersonMap create(PersonMap map) throws Exception {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int state = SapLogger.STATE_ERROR;
        try {
            c = ConnectionPool.getConnection(alias);

			/*
			 * создание лица в таблице
			 * 
			 * */
            if (map.getPerson_id() == null) {
                if (map.getPerson_type().equals(PersonMapUtil.PERSONTYPE_P)) {
                    map.setPerson(personDao.create(c, map.getPerson()));
                    map.setPerson_id(map.getPerson().getId());
                } else if (map.getPerson_type().equals(PersonMapUtil.PERSONTYPE_J)) {
                    map.setLegalEntity(legalEntityDao.create(c, map.getLegalEntity()));
                    map.setPerson_id(map.getLegalEntity().getId());
                }
            }
			/*
			 * создание связки в таблице client_addinfo_person_map
			 * 
			 * */
            ps = c.prepareStatement("SELECT seq_client_addinfo_person_map.NEXTVAL id FROM DUAL");
            rs = ps.executeQuery();
            if (rs.next()) {
                map.setId(rs.getLong("id"));
            }
            ps.close();
            ps = c.prepareStatement(
                    "INSERT INTO client_addinfo_person_map (id, branch, client_id, person_type, person_id, person_kind, prson_name) VALUES (?,?,?,?,?,?,?)");

            ps.setLong(1, map.getId());
            ps.setString(2, map.getBranch());
            ps.setString(3, map.getClient_id());
            ps.setString(4, map.getPerson_type());
            ps.setString(5, map.getPerson_id());
            ps.setString(6, map.getPerson_kind());
            ps.setString(7, map.name());
            ps.executeUpdate();
			/*
			 * создание дыанных о капитале
			 * 
			 * */
            if (map.getPerson_kind().equals(PersonMapUtil.PERSONKIND_FOUNDER)) {
                map.getCapital().setIdPersonMap(map.getId());
                capitalDao.create(c, map.getCapital());
            }
			/*
			 * 
			 * создание отношения  ДП в SAP
			 * */
//			String idsap = SapFactory.instance().getRelationService().getIPcode(map.getClient_id(), map.getBranch());
//			boolean hasIpRelation = idsap != null;
//			
//			if(!hasRelation) {
            if (!map.isDoNotSendRelationRequest()) {
//				if(map.getPerson_type().equals(PersonMapUtil.PERSONTYPE_P)) {
//					ISLogger.getLogger().error("::::::::::::::::::::::::::::: personMapDao create map.getPerson().getIdSap() == null  - " +map.getPerson().getIdSap() == null);
//					if (map.getPerson().getIdSap() == null) {
//						map.getPerson().setBranch(map.getBranch());
//						SapFactory.instance().getCustomerService().createPerson(map.getPerson());
//						ISLogger.getLogger().error("::::::::::::::::::::::::::::: personMapDao create after person sap request  - ");
//					}
//				} else if(map.getPerson_type().equals(PersonMapUtil.PERSONTYPE_J)) {
//					 if(map.getLegalEntity().getIdSap() == null) {
//						 map.getLegalEntity().setBranch(map.getBranch());
//						 map.getLegalEntity().setIdSap(SapFactory.instance().getLegalEntityService().orgRequestNew(map.getLegalEntity()));
//			         }
//				}

                SapFactory.instance().getRelationService().createRelation(map);
                try {
                    SapFactory.instance().getRoleService().createRole(map);
                } catch (Exception e) {
                }
            }
//			String role = map.getPerson_type().equals(PersonMapUtil.PERSONTYPE_J) 
//					? SapEnum.ROLE_EVENT_FOUNDER_LE.getSapValue()
//					: SapEnum.ROLE_EVENT_FOUNDER_I.getSapValue();
//			UtilityService.getInstance(alias).roleProtocol(role
//					, SapEnum.ROLE_CLIENT_NO_ACCOUNT.getSapValue()
//					, null);

            c.commit();
            new DeltaRelation.Builder().creatingRelation(map).state(SapLogger.STATE_SENT).build().log();
        } catch (SapCustomerException e) {
            logger.error(CheckNull.getPstr(e));
            if (map.getPerson_type().equals(PersonMapUtil.PERSONTYPE_J)) {
                new SapLogger.Builder().alias(alias).editLegalEntity(map.getLegalEntity()).state(state)
                        .build().log();
            } else {
                new SapLogger.Builder().alias(alias).editPerson(map.getPerson()).state(state)
                        .build().log();
            }
            new DeltaRelation.Builder().creatingRelation(map).message(e.getMessage()).state(state).build().log();
            DbUtils.rollback(c);
            throw new Exception(e.getMessage());
        } catch (SapRelationException e) {
            logger.error(CheckNull.getPstr(e));
            new DeltaRelation.Builder().creatingRelation(map).message(e.getMessage()).state(state).build().log();
            DbUtils.rollback(c);
            throw new Exception(e.getMessage());
        } catch (SapRoleException e) {

        } catch (Exception e) {
            logger.error(CheckNull.getPstr(e));
            DbUtils.rollback(c);
            throw new Exception(e.getMessage());
        } finally {
            ConnectionPool.close(c);
            DbUtils.closeStmt(ps);
            DbUtils.closeResultSet(rs);
        }
        return map;
    }


    /**
     * used to create only IP relation
     */
    @Override
    public PersonMap create(Connection c, PersonMap map) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
			/*
			 * создание лица в таблице
			 * 
			 * */
            map.getPerson().setBranch(map.getBranch());
            map.setPerson_id(personDao.create(c, map.getPerson()).getId());
			/*
			 * создание связки в таблице client_addinfo_person_map
			 * 
			 * */
            ps = c.prepareStatement("SELECT seq_client_addinfo_person_map.NEXTVAL id FROM DUAL");
            rs = ps.executeQuery();
            if (rs.next()) {
                map.setId(rs.getLong("id"));
            }
            ps.close();
            ps = c.prepareStatement(
                    "INSERT INTO client_addinfo_person_map (id, branch, client_id, person_type, person_id, person_kind, prson_name) VALUES (?,?,?,?,?,?,?)");

            ps.setLong(1, map.getId());
            ps.setString(2, map.getBranch());
            ps.setString(3, map.getClient_id());
            ps.setString(4, map.getPerson_type());
            ps.setString(5, map.getPerson_id());
            ps.setString(6, map.getPerson_kind());
            ps.setString(7, map.name());
            ps.executeUpdate();
        } catch (Exception e) {
            logger.error(CheckNull.getPstr(e));
            throw new Exception(e.getMessage());
        } finally {
            ConnectionPool.close(c);
            DbUtils.closeStmt(ps);
            DbUtils.closeResultSet(rs);
        }
        return map;
    }

    @Override
    public int update(PersonMap map) throws Exception {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            c = ConnectionPool.getConnection(alias);
            ps = c.prepareStatement("update client_addinfo_person_map set prson_name=? where id=?");
            ps.setString(1, map.name());
            ps.setLong(2, map.getId());
            ps.executeUpdate();

            if (map.getPerson_type().equals(PersonMapUtil.PERSONTYPE_P)) {
                personDao.update(c, map.getPerson());
            } else if (map.getPerson_type().equals(PersonMapUtil.PERSONTYPE_J)) {
                legalEntityDao.update(c, map.getLegalEntity());
            }
            capitalDao.update(c, map.getCapital());

            c.commit();

        } catch (Exception e) {
            logger.error("Exception " + CheckNull.getPstr(e));
            DbUtils.rollback(c);
            throw new Exception(e.getMessage());
        } finally {
            ConnectionPool.close(c);
            DbUtils.closeStmt(ps);
            DbUtils.closeResultSet(rs);
        }
        return count;
    }

    @Override
    public int update(Connection c, PersonMap item) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public int remove(PersonMap map) throws Exception {
        Connection c = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            c = ConnectionPool.getConnection(alias);
            ps = c.prepareStatement("delete from client_addinfo_person_map where person_id=?");
            ps.setString(1, map.getPerson_id());
            count = ps.executeUpdate();

            capitalDao.remove(c, map.getCapital());
            SapFactory.instance().getRelationService().deleteRelation(map);
            c.commit();
        } catch (SapRelationException e) {
            logger.error(CheckNull.getPstr(e));
            new DeltaRelation.Builder().deletingRelation(map).message(e.getMessage()).state(SapLogger.STATE_ERROR).
                    build().log();
            DbUtils.rollback(c);
            throw new Exception(e.getMessage());
        } catch (Exception e) {
            logger.error(CheckNull.getPstr(e));
            DbUtils.rollback(c);
            throw new Exception(e.getMessage());
        } finally {
            ConnectionPool.close(c);
            DbUtils.closeStmt(ps);
        }
        return count;
    }

    @Override
    public int remove(Connection c, PersonMap item) {
        throw new UnsupportedOperationException();
    }

    public String getUnionIdByMap(Connection c, PersonMap personMap){
        String union_id = null;
        if (personMap.getPerson_type().equalsIgnoreCase(PersonMapUtil.PERSONTYPE_J)){
            LegalEntity legalEntity = legalEntityDao.getItemByStringId(c, null, personMap.getPerson_id());
            if (legalEntity != null)
                union_id = legalEntity.getUnion_id();

        }
        else{
            Person person = personDao.getItemByStringId(c, null, personMap.getPerson_id());
            if (person != null)
                union_id = person.getUnion_id();
        }
        return union_id;
    }
}
