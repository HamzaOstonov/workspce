package com.is.client_personmap.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.apache.log4j.Logger;

import com.is.ConnectionPool;
import com.is.base.Dao;
import com.is.base.utils.DbUtils;
import com.is.client_personmap.model.LegalEntity;
import com.is.client_sap.SapFactory;
import com.is.client_sap.exceptions.SapException;
import com.is.clients.models.SapLogger;
import com.is.customer_.core.utils.CustomerUtils;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;

public class LegalEntityDao implements Dao<LegalEntity> {
	private static Logger logger = Logger.getLogger(LegalEntityDao.class);
	
	private static final String LEGAL_ENTITY_SELECT = "select * from client_addinfo_legal_entity";
	
	private String alias;
	private LegalEntity filter;
//	private PersonMap personMap; 
	
	private LegalEntityDao(String alias) {
		super();
		this.alias = alias;
	}
	
	public static LegalEntityDao getInstance(String alias) {
		return new LegalEntityDao(alias);
	}

	@Override
	public List<FilterField> getFilterFields() {
	    List<FilterField> flFields = new ArrayList<FilterField>();
	    if (!CheckNull.isEmpty(filter.getNumber_tax_registration()))
	        flFields.add(new FilterField(DbUtils.getCond(flFields) + "number_tax_registration=?",filter.getNumber_tax_registration()));
	    if (!CheckNull.isEmpty(filter.getName()))
	        flFields.add(new FilterField(DbUtils.getCond(flFields) + "name like ?",filter.getName()));
	    return flFields;
	}

	@Override
	public int getCount() {
		return 0;
	}
	@Override
	public List<LegalEntity> getList() {
		Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<LegalEntity> list = new ArrayList<LegalEntity>();
        LegalEntity legal = null;
        List<FilterField> flFields =getFilterFields();
		StringBuffer sql = new StringBuffer();
		sql.append(LEGAL_ENTITY_SELECT);
		if(flFields.size()>0){
			for(int i=0;i<flFields.size();i++){
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
        
        try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement(sql.toString());
			for(int k=0;k<flFields.size();k++){
				ps.setObject(k+1, flFields.get(k).getColobject());
			}
			
			rs = ps.executeQuery();
			while(rs.next()) {
				
				System.out.println("hamza "+rs.getDate("egrsp_date"));
				
				legal = new LegalEntity(
                        rs.getString("id"),
                        rs.getString("branch"),
                        rs.getString("client_id"),
                        rs.getString("name"),
                        rs.getString("short_name"),
                        rs.getDate("date_registration"),
                        rs.getString("place_regist_name"),
                        rs.getString("number_registration_doc"),
                        rs.getString("code_tax_org"),
                        rs.getString("number_tax_registration"),
                        rs.getString("code_sector"),
                        rs.getString("code_organ_direct"),
                        rs.getString("code_head_organization"),
                        rs.getString("inn_head_organization"),
                        rs.getString("code_bank"),
                        rs.getString("account"),
                        rs.getString("post_address_region"),
                        rs.getString("post_address_distr"),
                        rs.getString("post_address"),
                        rs.getString("phone_mobile"),
                        rs.getString("phone"),
                        rs.getString("fax"),
                        rs.getString("email"),
                        rs.getString("sign_trade"),
                        rs.getString("opf"),
                        rs.getString("soato"),
                        rs.getString("okpo"),
                        rs.getInt("state"),
                        rs.getString("code_country"),
                        rs.getString("code_resident"),
                        rs.getString("num_reestr"),
                        rs.getString("code_form"),
                        rs.getString("code_type"),
                        rs.getString("j_type_activity"),
						rs.getString("union_id"),
                        rs.getString("small_business"),
                        rs.getDate("egrsp_date"),
                        rs.getString("egrsp_number"),
						rs.getString("beneficiary")

                );
				
				list.add(legal);
			}
			
		} catch (Exception e) {
			logger.error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
			DbUtils.closeResultSet(rs);
			DbUtils.closeStmt(ps);
			DbUtils.closeResultSet(rs);
		}
        return list;
	}
	@Override
	public List<LegalEntity> getListWithPaging(int pageIndex, int pageSize) {
		throw new UnsupportedOperationException();
	}

	@Override
	public LegalEntity getItemByLongId(String branch,long itemId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public LegalEntity getItemByStringId(String branch,String id) {
		Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        LegalEntity legal = null;
        try {
        	if (alias != null) {
        		c = ConnectionPool.getConnection(alias);
        	} else {
        		c = ConnectionPool.getConnection();
        	}
			legal = getItemByStringId(c,null, id);
		} catch (Exception e) {
			logger.error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
			DbUtils.closeResultSet(rs);
			DbUtils.closeStmt(ps);
		}
        return legal;
	}
	
	@Override
	public LegalEntity getItemByLongId(Connection c,String branch, long itemId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public LegalEntity getItemByStringId(Connection c, String branch,String itemId) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        LegalEntity legal = null;
        try {
			ps = c.prepareStatement("select * from client_addinfo_legal_entity where id=?");
			ps.setString(1, itemId);
			rs = ps.executeQuery();
			if(rs.next()) {
				//System.out.println("hamza2 "+rs.getDate("egrsp_date"));				
				legal = new LegalEntity(
                        rs.getString("id"),
                        rs.getString("branch"),
                        rs.getString("client_id"),
                        rs.getString("name"),
                        rs.getString("short_name"),
                        rs.getDate("date_registration"),
                        rs.getString("place_regist_name"),
                        rs.getString("number_registration_doc"),
                        rs.getString("code_tax_org"),
                        rs.getString("number_tax_registration"),
                        rs.getString("code_sector"),
                        rs.getString("code_organ_direct"),
                        rs.getString("code_head_organization"),
                        rs.getString("inn_head_organization"),
                        rs.getString("code_bank"),
                        rs.getString("account"),
                        rs.getString("post_address_region"),
                        rs.getString("post_address_distr"),
                        rs.getString("post_address"),
                        rs.getString("phone_mobile"),
                        rs.getString("phone"),
                        rs.getString("fax"),
                        rs.getString("email"),
                        rs.getString("sign_trade"),
                        rs.getString("opf"),
                        rs.getString("soato"),
                        rs.getString("okpo"),
                        rs.getInt("state"),
                        rs.getString("code_country"),
                        rs.getString("code_resident"),
                        rs.getString("num_reestr"),
                        rs.getString("code_form"),
                        rs.getString("code_type"),
                        rs.getString("cl_activity_type_id"),
						rs.getString("union_id"),
						
                        rs.getString("small_business"),
                        rs.getDate("egrsp_date"),
                        rs.getString("egrsp_number"),
						rs.getString("beneficiary")
						
                );
				//System.out.println("hamza2 0 "+legal);
			}
		} catch (Exception e) {
			logger.error(CheckNull.getPstr(e));
		} finally {
			DbUtils.closeResultSet(rs);
			DbUtils.closeStmt(ps);
		}
        return legal;
	}

	@Override
	public LegalEntity create(LegalEntity legal) throws Exception {
		Connection c = null;
		PreparedStatement ps = null;
		LegalEntity createdLegal = null;
		try {
			c = ConnectionPool.getConnection(alias);
			createdLegal = create(c, legal);
			if(legal.getIdSap() == null) {
            	legal.setIdSap(SapFactory.instance().getLegalEntityService().orgRequestNew(createdLegal));
            }
//			new SapLogger.Builder()
//					.alias(alias)
//					.createLegalEntity(createdLegal)
//					.state(SapLogger.STATE_INSERTED)
//			.build()
//			.log();
			c.commit();
		} catch(SapException e) {
			logger.error(CheckNull.getPstr(e));
			int state = SapLogger.STATE_ERROR;
			boolean isEmergencyModeOn = CustomerUtils.isEmergencyModeOn(); 
			if(isEmergencyModeOn) {
				state = SapLogger.STATE_EMM_MODE;
			}
			new SapLogger.Builder()
					.alias(alias)
					.createLegalEntity(createdLegal)
					.state(state)
					.message(e.getMessage())
			.build()
			.log();
			if(!isEmergencyModeOn) {
				DbUtils.rollback(c);
				throw new Exception(e.getMessage());
			} else {
				c.commit();
			}
		} catch (Exception e) {
			logger.error(CheckNull.getPstr(e));
			throw new Exception(e.getMessage());
		} finally {
			ConnectionPool.close(c);
			DbUtils.closeStmt(ps);
		}
		
		return createdLegal;
	}
	
	@Override
	public LegalEntity create(Connection c, LegalEntity legal)
			throws SapException, Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
				 ps = c.prepareStatement("select SEQ_CLIENT_ADDINFO_PERSON.NEXTVAL id from dual");
	             rs = ps.executeQuery();
	             if (rs.next()) {
	                     legal.setId(rs.getString("id"));
	             }
	             ps.close();
				ps = c.prepareStatement("insert into client_addinfo_legal_entity (name, " +
																				"short_name, date_registration, " +//3
																				"place_regist_name, number_registration_doc, " +//5
																				"code_tax_org, number_tax_registration, " +//7
																				"code_sector, code_organ_direct, " +//9
																				"code_head_organization, inn_head_organization, " +//11
																				"code_bank, " +//13
																				"account, post_address_region, " +//15
																				"post_address_distr, post_address, " +//17
																				"phone_mobile, phone, " +//19
																				"fax, email, " +//21
																				"sign_trade, opf, " +//23
																				"soato, okpo,id,branch,client_id, " +//28
																				"CODE_COUNTRY, code_resident)" +//30
																				"values(?,?,?,?,?" +
																						",?,?,?,?,?"+
																						",?,?,?,?,?"+
																						",?,?,?,?,?"+
																						",?,?,?,?,?"+
																						",?,?,?,?,?)");
				
	            ps.setString(1,legal.getName());
	            ps.setString(2,legal.getShort_name());
	            ps.setDate(3,legal.getDate_registration()!=null?new java.sql.Date(legal.getDate_registration().getTime()):null);
	            ps.setString(4,legal.getPlace_regist_name());
	            ps.setString(5,legal.getNumber_registration_doc());
	            ps.setString(6,legal.getCode_tax_org());
	            ps.setString(7,legal.getNumber_tax_registration());
	            ps.setString(8,legal.getCode_sector());
	            ps.setString(9,legal.getCode_organ_direct());
	            ps.setString(10,legal.getCode_head_organization());
	            ps.setString(11,legal.getInn_head_organization());
	            ps.setString(12,legal.getCode_bank());
	            ps.setString(13,legal.getAccount());
	            ps.setString(14,legal.getPost_address_region());
	            ps.setString(15,legal.getPost_address_distr());
	            ps.setString(16,legal.getPost_address());
	            ps.setString(17,legal.getPhone_mobile());
	            ps.setString(18,legal.getPhone());
	            ps.setString(19,legal.getFax());
	            ps.setString(20,legal.getEmail());
	            ps.setString(21,legal.getSign_trade());
	            ps.setString(22,legal.getOpf());
	            ps.setString(23,legal.getSoato());
	            ps.setString(24,legal.getOkpo());
	            ps.setString(25,legal.getId());
	            ps.setString(26,legal.getBranch());
	            ps.setString(27,legal.getClient_id());
	            ps.setString(28,legal.getCode_country());
	            ps.setString(29,legal.getCode_resident());
	            ps.executeUpdate();
			
		} finally {
			DbUtils.closeStmt(ps);
			DbUtils.closeResultSet(rs);
		}
		
		return legal;
	}

	@Override
	public int update(LegalEntity legal) throws Exception {
		Connection c = null;
		PreparedStatement ps = null;
		int count = 0;
		try {
			c = ConnectionPool.getConnection(alias);
			count = update(c, legal);
//			new SapLogger.Builder()
//					.alias(alias)
//					.editLegalEntity(legal)
//					.state(SapLogger.STATE_INSERTED)
//			.build()
//			.log();
            c.commit();
		} 
//		catch(SapException e) {
//			logger.error(CheckNull.getPstr(e));
//			int state = SapLogger.STATE_ERROR;
//			boolean isEmergencyModeOn = PartnerUtils.isEmergencyModeOn(); 
//			if(isEmergencyModeOn) {
//				state = SapLogger.STATE_EMM_MODE;
//			}
//			new SapLogger.Builder()
//					.alias(alias)
//					.editLegalEntity(legal)
//					.state(state)
//					.message(e.getMessage())
//			.build()
//			.log();
//			if(!isEmergencyModeOn) {
//				DbUtils.rollback(c);
//				throw new Exception(e.getMessage());
//			}
//		} 
		catch (Exception e) {
			logger.error(CheckNull.getPstr(e));
			DbUtils.rollback(c);
		} finally {
			ConnectionPool.close(c);
			DbUtils.closeStmt(ps);
		}
		
		return count;
	}
	
	@Override
	public int update(Connection c, LegalEntity legal)
			throws SapException, Exception {
		PreparedStatement ps = null;
		int count = 0;
		try {
			ps = c.prepareStatement("update client_addinfo_legal_entity set name = ?, " +
																			"short_name = ?, date_registration = ?, " +
																			"place_regist_name = ?, number_registration_doc = ?, " +
																			"code_tax_org = ?, number_tax_registration = ?, " +
																			"code_sector = ?, code_organ_direct = ?, " +
																			"code_head_organization = ?, inn_head_organization = ?, " +
																			"code_bank = ?, " +
																			"account = ?, post_address_region = ?, " +
																			"post_address_distr = ?, post_address = ?, " +
																			"phone_mobile = ?, phone = ?, " +
																			"fax = ?, email = ?, " +
																			"sign_trade = ?, opf = ?, " +
																			"soato = ?, okpo = ? , code_country = ?" +
																			"where id=? and branch=? and client_id=?");
			
            ps.setString(1,legal.getName());
            ps.setString(2,legal.getShort_name());
            ps.setDate(3,legal.getDate_registration()!=null?new java.sql.Date(legal.getDate_registration().getTime()):null);
            ps.setString(4,legal.getPlace_regist_name());
            ps.setString(5,legal.getNumber_registration_doc());
            ps.setString(6,legal.getCode_tax_org());
            ps.setString(7,legal.getNumber_tax_registration());
            ps.setString(8,legal.getCode_sector());
            ps.setString(9,legal.getCode_organ_direct());
            ps.setString(10,legal.getCode_head_organization());
            ps.setString(11,legal.getInn_head_organization());
            ps.setString(12,legal.getCode_bank());
            ps.setString(13,legal.getAccount());
            ps.setString(14,legal.getPost_address_region());
            ps.setString(15,legal.getPost_address_distr());
            ps.setString(16,legal.getPost_address());
            ps.setString(17,legal.getPhone_mobile());
            ps.setString(18,legal.getPhone());
            ps.setString(19,legal.getFax());
            ps.setString(20,legal.getEmail());
            ps.setString(21,legal.getSign_trade());
            ps.setString(22,legal.getOpf());
            ps.setString(23,legal.getSoato());
            ps.setString(24,legal.getOkpo());
            ps.setString(25,legal.getCode_country());
            ps.setString(26,legal.getId());
            ps.setString(27,legal.getBranch());
            ps.setString(28,legal.getClient_id());
            count = ps.executeUpdate();
            
		} finally {
			DbUtils.closeStmt(ps);
		}
		
		return count;
	}

	@Override
	public int remove(LegalEntity item) {
		Connection c = null;
		int count = 0;
		try {
			c = ConnectionPool.getConnection(alias);
			count = remove(c, item);
			c.commit();
		} catch (SQLException e) {
			logger.error(CheckNull.getPstr(e));
			e.printStackTrace();
			DbUtils.rollback(c);
		} finally {
			ConnectionPool.close(c);
		}
		
		return count;
	}
	@Override
	public int remove(Connection c, LegalEntity item) {
		PreparedStatement ps = null; 
		CallableStatement infoInit = null;
		int count = 0;
		try {
			ps = c.prepareStatement("delete from client_addinfo_legal_entity where id=?");
			ps.setString(1, item.getId());
			count = ps.executeUpdate();
			
		} catch (SQLException e) {
			logger.error(CheckNull.getPstr(e));
			e.printStackTrace();
			DbUtils.rollback(c);
		} finally {
			DbUtils.closeStmt(ps);
			DbUtils.closeStmt(infoInit);
		}
		
		return count;
	}
	

	@Override
	public <T1 extends LegalEntity> void setFilter(T1 filter) {
        this.filter = filter;
	}

	public LegalEntity getByUnionId(String branch, String union_id){
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		LegalEntity legal = null;
		try {
			if (alias != null) {
				c = ConnectionPool.getConnection(alias);
			} else {
				c = ConnectionPool.getConnection();
			}
			legal = getItemByUnionId(c,union_id);
		} catch (Exception e) {
			logger.error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
			DbUtils.closeResultSet(rs);
			DbUtils.closeStmt(ps);
		}
		return legal;
	}

	private LegalEntity getItemByUnionId(Connection c, String union_id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		LegalEntity legal = null;
		try {
			ps = c.prepareStatement("select * from client_addinfo_legal_entity where union_id=?");
			ps.setString(1, union_id);
			rs = ps.executeQuery();
			if(rs.next()) {
				//System.out.println("hamza3 "+rs.getDate("egrsp_date"));
				legal = new LegalEntity(
						rs.getString("id"),
						rs.getString("branch"),
						rs.getString("client_id"),
						rs.getString("name"),
						rs.getString("short_name"),
						rs.getDate("date_registration"),
						rs.getString("place_regist_name"),
						rs.getString("number_registration_doc"),
						rs.getString("code_tax_org"),
						rs.getString("number_tax_registration"),
						rs.getString("code_sector"),
						rs.getString("code_organ_direct"),
						rs.getString("code_head_organization"),
						rs.getString("inn_head_organization"),
						rs.getString("code_bank"),
						rs.getString("account"),
						rs.getString("post_address_region"),
						rs.getString("post_address_distr"),
						rs.getString("post_address"),
						rs.getString("phone_mobile"),
						rs.getString("phone"),
						rs.getString("fax"),
						rs.getString("email"),
						rs.getString("sign_trade"),
						rs.getString("opf"),
						rs.getString("soato"),
						rs.getString("okpo"),
						rs.getInt("state"),
						rs.getString("code_country"),
						rs.getString("code_resident"),
						rs.getString("num_reestr"),
						rs.getString("code_form"),
						rs.getString("code_type"),
						rs.getString("cl_activity_type_id"),
						rs.getString("union_id"),
                        rs.getString("small_business"),
                        rs.getDate("egrsp_date"),
                        rs.getString("egrsp_number"),
						rs.getString("beneficiary")

				);
			}
		} catch (Exception e) {
			logger.error(CheckNull.getPstr(e));
		} finally {
			DbUtils.closeResultSet(rs);
			DbUtils.closeStmt(ps);
		}
		return legal;
	}
}
