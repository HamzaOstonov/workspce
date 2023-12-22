package com.is.sign.dvs;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.zkoss.zk.ui.util.Clients;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.sign.SignProvider;
import com.is.sign.log.Signlog;
import com.is.sign.log.SignlogService;
import com.is.user.UserService;
import com.is.utils.CheckNull;
import com.is.utils.RefData;
import com.is.utils.Res;

public class DvsSignService {
	//Код ошибки, возникшей при обработке документа. Возможные значения:000 - обработка выполнена успешно.810..815 - коды ошибок обработки DVS.
	private final static HashMap<String,String> responces = new HashMap<String, String>()
			{
			    {
			    	put("000", "Ok");
			        put("810", "Электронно-цифровая подпись блока данных не верна");
			        put("811", "Не правильный формат электронно-цифровой подписи");
					put("812", "Время действия ключа пользователя не наступило или истекло");
			        put("813", "Ключ заблокирован или удален администратором системы безопасности");
					put("814", "ЭЦП вычислена другим пользователем");
			        put("815", "Тестовый пользователь");
			        put("-1", "Отсутствует контекст пользователя в Dvs-Server");
			        put("-2", "Документ не был отправлен на проверку");
			        put("-3", "Подпись не найдена");
					put("-18", "Other error");
			    }
			};
	
	public static void signVerification() {
		Connection klbc = null;
		Statement s = null;
		ResultSet rs = null;
		List<RefData> list = new ArrayList<RefData>();
		Res docstateres = null;
		try {
			klbc = ConnectionPool.getConnection();
			s = klbc.createStatement();
			rs = s.executeQuery("select * from SS_KLB_SIGN_OBJECTS t where t.classname is not null order by id");
			while (rs.next()) {
				list.add(new RefData(
						rs.getString("id"),
						rs.getString("classname")));
			}
			for (int i = 0; i < list.size(); i++) {
				try {
					SignProvider sp = (SignProvider)Class.forName(list.get(i).getLabel()).newInstance();
					sp.checkDVS();
				} catch (Exception e) {
					e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			try {s.close();} catch (Exception e) {}
			try {rs.close();} catch (Exception e) {}
			
			ConnectionPool.close(klbc);
			try {if (klbc != null) klbc.close();} catch (Exception e) {}
		}
	}
	
	/*public static void signVerificationDocuments() {
		Connection klbc = null;
		Connection dvsc = null;
		Statement s = null;
		PreparedStatement psclients = null;
		PreparedStatement psdocstate = null;
		Statement ssign = null;
		ResultSet rs = null;
		//Signin signin = new Signin();
		List<Document> documents = new ArrayList<Document>();
		Res docstateres = null;
		try {
			klbc = ConnectionPool.getConnection();
			s = klbc.createStatement();
			psclients = klbc.prepareStatement("select uc.username, c.*, c.schema bank_scheme from KLB_USER_CLIENTS uc, klb_clients c where uc.username = ? and c.id = uc.client_id and c.id = ?  ");
			psdocstate = klbc.prepareStatement("UPDATE KLB_GENERAL SET ERROR_MESSAGE = ?, state = ?  WHERE id=? and state = 3");
			rs = s.executeQuery("SELECT * FROM klb_general where state = 3");
			while (rs.next()) {
				documents.add(new Document(
						rs.getLong("id"),
						rs.getString("branch"),
						rs.getString("doc_num"),
						rs.getDate("d_date"),
						rs.getString("bank_cl"),
						rs.getString("acc_cl"),
						rs.getString("name_cl"),
						rs.getString("inn_cl"),
						rs.getString("bank_co"),
						rs.getString("acc_co"),
						rs.getString("name_co"),
						rs.getString("inn_co"),
						rs.getString("purpose_code"),
						rs.getString("purpose"),
						rs.getBigDecimal("summa").divide(new BigDecimal("100")),
						rs.getString("currency"),
						rs.getString("type_doc"),
						rs.getDate("v_date"),
						rs.getString("pdc"),
						rs.getInt("state"),
						"",//rs.getString("state_desc"),
						rs.getLong("parent_group_id"),
						rs.getLong("parent_id"),
						rs.getInt("s_deal_id"),
						rs.getString("cash_code"),
						rs.getLong("child_id"),
						rs.getLong("kod_err"),
						rs.getString("file_name"),
						rs.getLong("err_general"),
						rs.getLong("emp_id"),
						rs.getLong("id_transh"),
						rs.getLong("id_transh_purp"),
						rs.getDate("val_date"),
						rs.getLong("client_id"),
						rs.getString("username"),
						rs.getString("sign"),
						rs.getLong("general_id"),
						rs.getString("error_message"),
						rs.getInt("parent_id_type"),
						rs.getString("budget_account"),
						rs.getString("budget_inn"),
						rs.getString("budget_name"),
						0));
			}
			dvsc = client_bank_common.ConnectionPool.getConnection_dvs();
			ssign = dvsc.createStatement();
			for (int i = 0; i < documents.size(); i++) {
				try {
					Long docid = documents.get(i).getId() * (-1L);
					//System.out.println(documents.get(i).getId() + " > " + docid);
							
					//send to verification
					
					Signin signin = new Signin(); 
					signin.setId(docid);
					signin.setQuery_line(documents.get(i).getSignBody());
					signin.setCrp_line(documents.get(i).getSign());
					signin.setCurr_state("0");
					create(signin);
					
					String username = "";
					String client_id = "";
					String branch = "";
					Long clb_client_id = 0L;
					String alias = "";
					
					psclients.setString(1, documents.get(i).getUsername());
					psclients.setLong(2, documents.get(i).getClient_id());
					rs = psclients.executeQuery();
					if (rs.next()) {
						username = rs.getString("username");
						client_id = rs.getString("client_id");
						branch = rs.getString("branch");
						clb_client_id = rs.getLong("id");
						alias =  rs.getString("bank_scheme");
					}
					if (CheckNull.isEmpty(client_id)) {
						throw new Exception(responces.get("-3"));
						//DocumentService.updateDocGeneralErr(responces.get("-3"),7,documents.get(i).getId());
						//return;
					}
					DvsUserAccess dvs = getDVSUserAccess(branch, username, client_id, clb_client_id, alias);
					if (CheckNull.isEmpty(dvs.getUser_id())) {
						throw new Exception(responces.get("813"));
						//DocumentService.updateDocGeneralErr(responces.get("813"),7,documents.get(i).getId());
						//return;
					} 
					Date dt = new Date();
					if (dvs.getKey_expired().getTime() < dt.getTime()) {
						throw new Exception(responces.get("812"));
						//DocumentService.updateDocGeneralErr(responces.get("812"),7,documents.get(i).getId());
						//return;
					}
					
					//check document sign (verification)
					Signin signin = getSignin(dvsc, ssign, rs, docid, dvs.getKey_code());
					if (!CheckNull.isEmpty(signin.getId())) {
						//Код состояния документа. Возможные значения:0 - документ поступил и еще не обработан DSV.1 - документ обработан DSV
						if (signin.getCurr_state().trim().equalsIgnoreCase("1")) {
							if (signin.getErr_code().trim().equalsIgnoreCase("000")) {
								//DocumentService.updateDocGeneralErr(responces.get(signin.getErr_code()),3,documents.get(i).getId());
								docstateres = DocumentService.updateDocGeneralState(klbc, psdocstate, responces.get(signin.getErr_code()),4,documents.get(i).getId());
								
								if (docstateres.getCode()==0) {
									klbc.commit();
								} else {
									ISLogger.getLogger().error(docstateres.getName());
									klbc.rollback();
								}
								
							} else {
								if (!CheckNull.isEmpty(responces.get(signin.getErr_code()))){
									//DocumentService.updateDocGeneralErr(responces.get(signin.getErr_code()),7,documents.get(i).getId());
									docstateres = DocumentService.updateDocGeneralState(klbc, psdocstate, responces.get(signin.getErr_code()),8,documents.get(i).getId());
								} else {
									//DocumentService.updateDocGeneralErr(responces.get("-18"),7,documents.get(i).getId());
									docstateres = DocumentService.updateDocGeneralState(klbc, psdocstate, responces.get("-18"),8,documents.get(i).getId());
								}
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
					if (CheckNull.isEmpty(e.getMessage())) {
						docstateres = DocumentService.updateDocGeneralState(klbc, psdocstate, responces.get("-18"),8,documents.get(i).getId());
					} else if (e.getMessage().indexOf(responces.get("-3")) > -1) {
						docstateres = DocumentService.updateDocGeneralState(klbc, psdocstate, responces.get("-3"),8,documents.get(i).getId());
					} else if (e.getMessage().indexOf(responces.get("813")) > -1) {
						docstateres = DocumentService.updateDocGeneralState(klbc, psdocstate, responces.get("813"),8,documents.get(i).getId());
					} else if (e.getMessage().indexOf(responces.get("812")) > -1) {
						docstateres = DocumentService.updateDocGeneralState(klbc, psdocstate, responces.get("812"),8,documents.get(i).getId());
					} else {
						docstateres = DocumentService.updateDocGeneralState(klbc, psdocstate, responces.get("-18"),8,documents.get(i).getId());
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			try {s.close();} catch (Exception e) {}
			try {psclients.close();} catch (Exception e) {}
			try {psdocstate.close();} catch (Exception e) {}
			try {ssign.close();} catch (Exception e) {}
			try {rs.close();} catch (Exception e) {}
			
			ConnectionPool.close(klbc);
			ConnectionPool.close(dvsc);
			try {klbc.close();} catch (Exception e) {}
			try {dvsc.close();} catch (Exception e) {}
		}
	}
			
	public static void signVerification_() {
		List<Document> documents = DocumentService.getDocumentForSign();
		Connection klbc = null;
		for (int i = 0; i < documents.size(); i++) {
			Long docid = documents.get(i).getId() * (-1L);
			//System.out.println(documents.get(i).getId() + " > " + docid);
			
			//send to verification
			
			Signin signin = new Signin(); 
			signin.setId(docid);
			signin.setQuery_line(documents.get(i).getSignBody());
			signin.setCrp_line(documents.get(i).getSign());
			signin.setCurr_state("0");
			create(signin);
			
			String username = "";
			String client_id = "";
			Long clb_client_id = 0L;
			String branch = "";
			String alias = "";
			try {
				klbc = ConnectionPool.getConnection();
				PreparedStatement ps = klbc.prepareStatement("select uc.username, c.*, c.schema bank_scheme from KLB_USER_CLIENTS uc, klb_clients c where uc.username = ? and c.id = uc.client_id and c.id = ?  ");
				ps.setString(1, documents.get(i).getUsername());
				ps.setLong(2, documents.get(i).getClient_id());
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					username = rs.getString("username");
					client_id = rs.getString("client_id");
					branch = rs.getString("branch");
					clb_client_id = rs.getLong("id");
					alias = rs.getString("bank_scheme");
				}
			} catch (Exception e) {
				e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
			}
			if (CheckNull.isEmpty(client_id)) {
				DocumentService.updateDocGeneralErr(responces.get("-3"),7,documents.get(i).getId());
				return;
			}
			DvsUserAccess dvs = getDVSUserAccess(branch, username, client_id, clb_client_id, alias);
			if (CheckNull.isEmpty(dvs.getUser_id())) {
				DocumentService.updateDocGeneralErr(responces.get("813"),7,documents.get(i).getId());
				return;
			} 
			Date dt = new Date();
			if (dvs.getKey_expired().getTime() < dt.getTime()) {
				DocumentService.updateDocGeneralErr(responces.get("812"),7,documents.get(i).getId());
				return;
			}
			
			//check document sign (verification)
			Signin signin = getSignin(docid, dvs.getKey_code());
			if (!CheckNull.isEmpty(signin.getId())) {
				//Код состояния документа. Возможные значения:0 - документ поступил и еще не обработан DSV.1 - документ обработан DSV
				if (signin.getCurr_state().trim().equalsIgnoreCase("1")) {
					if (signin.getErr_code().trim().equalsIgnoreCase("000")) {
						DocumentService.updateDocGeneralErr(responces.get(signin.getErr_code()),3,documents.get(i).getId());
					} else {
						if (!CheckNull.isEmpty(responces.get(signin.getErr_code()))){
							DocumentService.updateDocGeneralErr(responces.get(signin.getErr_code()),7,documents.get(i).getId());
						} else {
							DocumentService.updateDocGeneralErr(responces.get("-18"),7,documents.get(i).getId());
						}
					}
				}
			}
		}
	}*/
	
	public static Signin getSignin(Connection c, Statement s, ResultSet rs, Long signinId, String dvs_user_id) {
		Signin signin = new Signin();
		//Connection c = null;
		try {
			//c = client_bank_common.ConnectionPool.getConnection_dvs();
			rs = s.executeQuery("SELECT * FROM in_"+dvs_user_id+" WHERE id="+signinId);
			if (rs.next()) {
				signin = new Signin();
				signin.setId(rs.getLong("id"));
				signin.setQuery_line(rs.getString("query_line"));
				signin.setCrp_line(rs.getString("crp_line"));
				signin.setCrp_date(rs.getString("crp_date"));
				signin.setCurr_state(rs.getString("curr_state"));
				signin.setErr_code(rs.getString("err_code"));
			} else {
				signin.setId(signinId);
				signin.setErr_code("-2");
				signin.setErr_message("~Документ с номером "+signinId+" не был отправлен на проверку.~ ");
			}
		} catch (SQLException e) {
			signin = new Signin();
			signin.setId(signinId); 
			signin.setCurr_state("0"); 
			if (e.getErrorCode() == -942) {
				signin.setErr_code("-1");
				signin.setErr_message("~Отсутствует контекст пользователя в Dvs-Server.~ " + CheckNull.getPstr(e));
			} else {
				signin.setErr_code("-18");
				signin.setErr_message(CheckNull.getPstr(e));
			}
			//System.err.println("SQLState: " + ((SQLException)e).getSQLState());
			//System.err.println("Error Code: " + ((SQLException)e).getErrorCode());
			//System.err.println("Message: " + e.getMessage());
			e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e)); ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			//ConnectionPool.close(c);
		}
		return signin;
	}
	
	public static Signin getSignin(Long signinId, String dvs_user_id) {
		Signin signin = new Signin();
		Connection c = null;
		try {
			//c = client_bank_common.ConnectionPool.getConnection_dvs();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM in_"+dvs_user_id+" WHERE id=?");
			ps.setLong(1, signinId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				signin = new Signin();
				signin.setId(rs.getLong("id"));
				signin.setQuery_line(rs.getString("query_line"));
				signin.setCrp_line(rs.getString("crp_line"));
				signin.setCrp_date(rs.getString("crp_date"));
				signin.setCurr_state(rs.getString("curr_state"));
				signin.setErr_code(rs.getString("err_code"));
			} else {
				signin.setId(signinId);
				signin.setErr_code("-2");
				signin.setErr_message("~Документ с номером "+signinId+" не был отправлен на проверку.~ ");
			}
		} catch (SQLException e) {
			signin = new Signin();
			signin.setId(signinId); 
			signin.setCurr_state("0"); 
			if (e.getErrorCode() == -942) {
				signin.setErr_code("-1");
				signin.setErr_message("~Отсутствует контекст пользователя в Dvs-Server.~ " + CheckNull.getPstr(e));
			} else {
				signin.setErr_code("-18");
				signin.setErr_message(CheckNull.getPstr(e));
			}
			//System.err.println("SQLState: " + ((SQLException)e).getSQLState());
			//System.err.println("Error Code: " + ((SQLException)e).getErrorCode());
			//System.err.println("Message: " + e.getMessage());
			e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e)); ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return signin;
	}

	public static Signin create(Signin signin, String dvs_user_id, int object_id)  {
		Connection c = null;
		Connection clb = null;
		PreparedStatement ps_clb = null;
		PreparedStatement ps = null;
		ResultSet rs_clb = null;
		ResultSet rs = null;
		ResultSet rs_dvs = null;
		try {
			clb = ConnectionPool.getConnection();
		//	c = client_bank_common.ConnectionPool.getConnection_dvs();
			ps_clb = clb.prepareStatement("SELECT * from klb_sign_map where object_type = ? and id_object = ?");
			ps_clb.setInt(1, object_id);
			ps_clb.setLong(2, signin.getId());
			rs_clb = ps_clb.executeQuery();
			if (rs_clb.next()) {
				signin.setId(rs_clb.getLong("sign_id"));
				//ps_clb = clb.prepareStatement("UPDATE klb_sign_map set state = 0 where sign_id = ?");
				//ps_clb.setLong(1, signin.getId());
				//if (ps_clb.executeUpdate() != 1) {
				//	throw new Exception("Подпись невозможна! Произошла ошибка при сохранении в мапинга подписи!");
				//}
				ps = c.prepareStatement("SELECT * FROM in_"+dvs_user_id+" WHERE id=?");
				ps.setLong(1, signin.getId()* (-1L));
				rs_dvs = ps.executeQuery();
				if (rs_dvs.next()) {
					String curr_state = rs_dvs.getString("curr_state");
					String err_code = rs_dvs.getString("err_code");
					if (curr_state.equalsIgnoreCase("0")) {
						throw new Exception("Подпись невозможна! Документ уже был подписан, дождитесь проверки подписи!");
					}
				}
				
				ps = c.prepareStatement("UPDATE in_"+dvs_user_id+" SET id = ?, query_line = ?, crp_line = ?, crp_date = ?, curr_state = ?, err_code = ?  WHERE id=?");
				ps.setLong(1, signin.getId()* (-1L));
				ps.setString(2, signin.getQuery_line());
				ps.setString(3, signin.getCrp_line());
				ps.setString(4, signin.getCrp_date());
				ps.setString(5, signin.getCurr_state());
				ps.setString(6, signin.getErr_code());
				ps.setLong(7, signin.getId()* (-1L));
				signin.setUpd_cnt(ps.executeUpdate());
				signin.setErr_message(signin.getUpd_cnt()==1?"Ok":"Updated "+signin.getUpd_cnt()+"rows, mast be 1 row!");
			} else {
				ps = clb.prepareStatement("SELECT SEQ_SIGN.NEXTVAL id FROM DUAL");
				rs = ps.executeQuery();
				if (rs.next()) {
					Long sign_id = rs.getLong("id");
					ps = clb.prepareStatement("insert into klb_sign_map (sign_id, id_object, object_type) values(?, ?, ?)");
					ps.setLong(1, sign_id);
					ps.setLong(2, signin.getId());
					ps.setInt(3, object_id);
					if (ps.executeUpdate() != 1) {
						throw new Exception("Подпись невозможна! Произошла ошибка при сохранении в мапинга!");
					}
					signin.setId(sign_id);
				} else {
					throw new Exception("SEQ_SIGN not found!");
				}
				//System.out.println("dvs_user_id = "+dvs_user_id);
				ps = c.prepareStatement("INSERT INTO in_"+dvs_user_id+" (id, query_line, crp_line, crp_date, curr_state, err_code) VALUES (?, ?, ?, ?, ?, ?)");
				ps.setLong(1, signin.getId()* (-1L));
				ps.setString(2, signin.getQuery_line());
				ps.setString(3, signin.getCrp_line());
				ps.setString(4, signin.getCrp_date());
				ps.setString(5, signin.getCurr_state());
				ps.setString(6, signin.getErr_code());
				//'||v_id_doc_next||','''||v_SignBody||''','''||v_Sign||''', NULL, 0, NULL)
				signin.setUpd_cnt(ps.executeUpdate());
				signin.setErr_message(signin.getUpd_cnt()==1?"Ok":"Created "+signin.getUpd_cnt()+"rows, mast be 1 row!");
				
			}
			c.commit();
			clb.commit();
		} catch (Exception e) {
			e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
			try {
				if (c != null) c.rollback();
			} catch (Exception ex) {
				ex.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(ex));
			}
			try {
				if (clb != null) clb.rollback();
			} catch (Exception ex) {
				ex.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(ex)); 
			}
			signin.setUpd_cnt(-1);
			signin.setErr_message(CheckNull.isEmpty(e.getMessage())?(CheckNull.getPstr(e).length()>200?CheckNull.getPstr(e).substring(0, 199):CheckNull.getPstr(e)):e.getMessage());
		} finally {
			ConnectionPool.close(c);
			ConnectionPool.close(clb);
		}
		return signin;
	}

	public static Signin update(Signin signin, String dvs_user_id, int object_id)  {
		Connection c = null;
		try {
		//	c = client_bank_common.ConnectionPool.getConnection_dvs();
			PreparedStatement ps = c.prepareStatement("UPDATE in_"+dvs_user_id+" SET id = ?, query_line = ?, crp_line = ?, crp_date = ?, curr_state = ?, err_code = ?  WHERE id=?");
			ps.setLong(1, signin.getId());
			ps.setString(2, signin.getQuery_line());
			ps.setString(3, signin.getCrp_line());
			ps.setString(4, signin.getCrp_date());
			ps.setString(5, signin.getCurr_state());
			ps.setString(6, signin.getErr_code());
			ps.setLong(7, signin.getId());
			
			signin.setUpd_cnt(ps.executeUpdate());
			signin.setErr_message(signin.getUpd_cnt()==1?"Ok":"Updated "+signin.getUpd_cnt()+"rows, mast be 1 row!");
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
			try {
				c.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			signin.setUpd_cnt(-1);
			signin.setErr_message(e.getMessage());
		} finally {
			ConnectionPool.close(c);
		}
		return signin;
	}

	public static void remove(Signin signin, String dvs_user_id)  {
		Connection c = null;
		try {
			//c = client_bank_common.ConnectionPool.getConnection_dvs();
			PreparedStatement ps = c.prepareStatement("DELETE FROM in_"+dvs_user_id+" WHERE id=?");
			ps.setLong(1, signin.getId());
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
			try {
				c.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} finally {
			ConnectionPool.close(c);
		}
	}
	
	public static Signout getSignout(Long signoutId, String dvs_user_id) {
		Signout signout = new Signout();
		Connection c = null;
		try {
		//	c = client_bank_common.ConnectionPool.getConnection_dvs();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM OUT_11111 WHERE id=?");
			ps.setLong(1, signoutId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				signout = new Signout();
				signout.setId(rs.getLong("id"));
				signout.setQuery_line(rs.getString("query_line"));
				signout.setCrp_line(rs.getString("crp_line"));
				signout.setCrp_date(rs.getString("crp_date"));
				signout.setCurr_state(rs.getString("curr_state"));
				signout.setErr_code(rs.getString("err_code"));
			}
		} catch (Exception e) {
			e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return signout;
	}

	public static Signout create(Signout signout, String dvs_user_id)  {
		Connection c = null;
		PreparedStatement ps = null;
		try {
		//	c = client_bank_common.ConnectionPool.getConnection_dvs();
			ps = c.prepareStatement("SELECT SEQ_OUT_"+dvs_user_id+".NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				signout.setId(rs.getLong("id"));
			}
			ps = c.prepareStatement("INSERT INTO OUT_"+dvs_user_id+" (id, query_line, crp_line, crp_date, curr_state, err_code) VALUES (?, ?, ?, ?, ?, ?)");
				ps.setLong(1, signout.getId());
				ps.setString(2, signout.getQuery_line());
				ps.setString(3, signout.getCrp_line());
				ps.setString(4, signout.getCrp_date());
				ps.setString(5, signout.getCurr_state());
				ps.setString(6, signout.getErr_code());

			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
			try {
				c.rollback();
			} catch (Exception ex) {
				ex.printStackTrace(); 
			}
		} finally {
			ConnectionPool.close(c);
		}
		return signout;
	}

	public static void update(Signout signout, String dvs_user_id)  {
		Connection c = null;
		try {
		//	c = client_bank_common.ConnectionPool.getConnection_dvs();
			PreparedStatement ps = c.prepareStatement("UPDATE OUT_"+dvs_user_id+" SET id = ?, query_line = ?, crp_line = ?, crp_date = ?, curr_state = ?, err_code = ?  WHERE id=?");
				ps.setLong(1, signout.getId());
				ps.setString(2, signout.getQuery_line());
				ps.setString(3, signout.getCrp_line());
				ps.setString(4, signout.getCrp_date());
				ps.setString(5, signout.getCurr_state());
				ps.setString(6, signout.getErr_code());

			ps.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
			try {
				c.rollback();
			} catch (Exception ex) {
				ex.printStackTrace(); 
			}
		} finally {
			ConnectionPool.close(c);
		}
	}

	public static void remove(Signout signout, String dvs_user_id)  {
		Connection c = null;
		try {
		//	c = client_bank_common.ConnectionPool.getConnection_dvs();
			PreparedStatement ps = c.prepareStatement("DELETE FROM OUT_"+dvs_user_id+" WHERE id=?");
			ps.setLong(1, signout.getId());
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
	}
	
	public static DvsUserAccess getDVSUserAccess(String branch, String username, String client_id, Long clb_client_id, String alias)  {
		//System.out.println(""+ branch+", "+username+", "+client_id);
		DvsUserAccess dvsuseraccess = new DvsUserAccess();
		//List<RefData> ss_db_link_branches = new ArrayList<RefData>();
		//String alias = "";
		Connection c = null;
		Connection cclb = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			cclb = ConnectionPool.getConnection();
			ps = cclb.prepareStatement("select  "+
			           " 1 as user_id,u.username as user_name,u.name as full_name,uk.key_code,1 as emp_id,k.key_expired, uk.client_id  "+
			           " from klb_users u, klb_user_keys uk, klb_keys k "+
			           " where u.username = upper(?) and uk.username = u.username and uk.client_id = ? "+
			           " and k.key_type = uk.key_type and k.key_code = uk.key_code and k.key_sn = k.key_sn ");
			ps.setString(1, username);
			ps.setLong(2, clb_client_id);
			rs = ps.executeQuery();
			if (rs.next()) {
				dvsuseraccess = new DvsUserAccess();
				dvsuseraccess.setUser_id(rs.getLong("user_id"));
				dvsuseraccess.setUser_name(rs.getString("user_name"));
				dvsuseraccess.setFull_name(rs.getString("full_name"));
				dvsuseraccess.setKey_code(rs.getString("key_code"));
				dvsuseraccess.setEmp_id(rs.getLong("emp_id"));
				dvsuseraccess.setKey_expired(rs.getDate("key_expired"));
			} else {
				/*ss_db_link_branches = getSSDBLinkBranch();
				if (ss_db_link_branches.size() < 1) {
					throw new Exception("No ss_db_link_branches found!");
				}
				alias = lvalue(branch, ss_db_link_branches);
				if (CheckNull.isEmpty(alias)) {
					throw new Exception("No alias found for branch = "+branch+"!");
				}*/
				c = ConnectionPool.getConnection(alias);
				ps = c.prepareStatement("select "+
						" iua.user_id,u.user_name,u.full_name,iua.key_code,iua.emp_id,iua.key_EXPIRED, c.id_client "+
						" from users u, i_user_access iua, i_client_access c "+
						" where u.user_name = upper(?) and u.branch = iua.branch and u.id = iua.user_id "+
						" and c.id_client = ? and c.branch = u.branch and c.user_id = u.id ");
				ps.setString(1, username);
				ps.setString(2, client_id);
				rs = ps.executeQuery();
				if (rs.next()) {
					dvsuseraccess = new DvsUserAccess();
					dvsuseraccess.setUser_id(rs.getLong("user_id"));
					dvsuseraccess.setUser_name(rs.getString("user_name"));
					dvsuseraccess.setFull_name(rs.getString("full_name"));
					dvsuseraccess.setKey_code(rs.getString("key_code"));
					dvsuseraccess.setEmp_id(rs.getLong("emp_id"));
					dvsuseraccess.setKey_expired(rs.getDate("key_expired"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			if (cclb != null) ConnectionPool.close(cclb);
			if (c != null) ConnectionPool.close(c);
		}
		return dvsuseraccess;
	}
	
	public static DvsUserAccess getDVSUserAccessFromOldIB(String branch, String username, String client_id, Long clb_client_id, String alias)  {
		//System.out.println(""+ branch+", "+username+", "+client_id);
		DvsUserAccess dvsuseraccess = new DvsUserAccess();
		//List<RefData> ss_db_link_branches = new ArrayList<RefData>();
		//String alias = "";
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select "+
					" iua.user_id,u.user_name,u.full_name,iua.key_code,iua.emp_id,iua.key_EXPIRED, c.id_client "+
					" from users u, i_user_access iua, i_client_access c "+
					" where u.user_name = upper(?) and u.branch = iua.branch and u.id = iua.user_id "+
					" and c.id_client = ? and c.branch = u.branch and c.user_id = u.id ");
			ps.setString(1, username);
			ps.setString(2, client_id);
			rs = ps.executeQuery();
			if (rs.next()) {
				dvsuseraccess = new DvsUserAccess();
				dvsuseraccess.setUser_id(rs.getLong("user_id"));
				dvsuseraccess.setUser_name(rs.getString("user_name"));
				dvsuseraccess.setFull_name(rs.getString("full_name"));
				dvsuseraccess.setKey_code(rs.getString("key_code"));
				dvsuseraccess.setEmp_id(rs.getLong("emp_id"));
				dvsuseraccess.setKey_expired(rs.getDate("key_expired"));
			}
		} catch (Exception e) {
			e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			if (c != null) ConnectionPool.close(c);
		}
		return dvsuseraccess;
	}
	
	public static List<RefData> getRefDataBank(String sql)  {
        List<RefData> list = new LinkedList<RefData>();
        Connection c = null;
        try {
	        c = ConnectionPool.getConnection();
	        PreparedStatement ps = c.prepareStatement(sql);
	        //ps.setString(1, key);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
                list.add(new RefData(
                                rs.getString("data"),
                                rs.getString("label")));
	        }
        } catch (SQLException e) {
        	e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
        } finally {
        	ConnectionPool.close(c);
		}
		return list;
	}
	
	public static String  lvalue(String val, List<RefData> dp) {
	    String res="";
	    if ( dp != null ) {
	        for (int i = 0; i < dp.size(); i++) {
	            if ( val.equals(dp.get(i).getData())) {
	                res = dp.get(i).getLabel();
	    }    }    }
	    return res;
	}
	
	public static List<RefData> getSSDBLinkBranch() {
		return getRefDataBank("select branch data, user_name label from ss_dblink_branch order by branch");
	}
	
}
