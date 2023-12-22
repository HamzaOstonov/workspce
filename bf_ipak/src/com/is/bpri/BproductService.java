package com.is.bpri;

//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
//import java.util.Calendar.*;
import java.util.Map;

//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.apache.http.util.EntityUtils;
//import org.json.JSONObject;
//
//import oracle.jdbc.OracleTypes;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.bpri.ball_scoring.AnswerModel;
import com.is.bpri.ball_scoring.BallScoringModel;
import com.is.bpri.ball_scoring.BallScoringViewModel;
import com.is.bpri.ball_scoring.SavedAnswer;
import com.is.bpri.bpr_change_limit.BprChangeLimit;
import com.is.bpri.bproductAddInf.Parameter;
import com.is.bpri.creategrids.Create;
import com.is.bpri.creategrids.CreateService;
import com.is.bpri.creategrids.CreateValues;
import com.is.bpri.main_scoring.ScoringType;
import com.is.bpri.models.Ld_sv_gate;
import com.is.bpri.product_info.ProductInfo;
import com.is.bpri.product_info.Product_ld_char;
import com.is.bpri.product_info.Product_ld_exp;
import com.is.bpri.scoring.Scoring;
import com.is.bpri.utils.Utils;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.RefData;
import com.is.utils.Res;

import accounting_transaction_2.Util_DAO;

public class BproductService {
	
	private static HashMap<String, String> schemas = null;
	private static SimpleDateFormat sdf_doaction = new SimpleDateFormat("dd.MM.yyyy");
	private PreparedStatement ps_get_oper_date = null;
	private ResultSet rs_get_oper_date = null;
	private CallableStatement ps_get_name_and_inn = null;
	private CallableStatement ps_get_deal_general = null;
	private static CallableStatement cs_clearparam = null;
	private static CallableStatement cs_setparam = null;
	//private CallableStatement cs_getparam = null;
	//private CallableStatement cs_doaction = null;
	private static CallableStatement cs_scoring = null;
	private static CallableStatement cs_getparam_scoring = null;
	
	private static String type_proc_do;
	public static  Statement st =null;
	
	private static  Util_DAO util_dao;
	
	private Connection c;
	public BproductService(Connection c) throws SQLException
	{
		this.c = c;
		//if(this.schemas == null) fill_shemas();
		ps_get_oper_date = this.c.prepareStatement("select info.GetDay oper_date from dual");
		ps_get_name_and_inn = this.c.prepareCall("{? = call kernel.getnameandinn(?, ?) }");
		cs_clearparam = this.c.prepareCall("{ call param.ClearAll() }");		
		cs_setparam = this.c.prepareCall("{ call param.SetParam(?, ?) }");
		
		cs_getparam_scoring = c.prepareCall("{? = call Param.getparam('INFO') }");
		cs_getparam_scoring.registerOutParameter(1, java.sql.Types.VARCHAR);
		//cs_scoring = c.prepareCall("{ call PROC_LD_SCORING.getScoringSumma22 () }");
		cs_scoring = c.prepareCall("{" +type_proc_do+"()" + " }");
		st = this.c.createStatement();
	}

	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from ( ";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ? ";
	private static String msql = "select * from (select bproduct.id,bproduct.branch,bproduct.customer,bproduct.prodid,ss_bpr_type.bpr_name,bproduct.vdate,bproduct.currency,bproduct.amount,bproduct.emp_id,bproduct.state,ss_state_anket.name,bproduct.ni_req_id from ss_state_anket,bproduct,ss_bpr_type where ss_bpr_type.bpr_id=bproduct.prodid and ss_state_anket.id=bproduct.state order by bproduct.id desc)";
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
	private final static String CARD_FORMAT_EXCEPTION = "Неверный формат пластиковой карты";
	private final static String CARD_NOT_FOUND_EXCEPTION = "Не найдена карта с номером";
	
	public static void doActionForDepositGiving(String un, String pw,
			Bproduct bproduct, List<Parameter> parameters,List<BallScoringViewModel> balls, String br,
			String cl_id,
			String dateContractComp, 
			String bpr_id, int trans_id, String head_of_bank, List<RefData> accountList,
			List<BprChangeLimit> limit,
			String bpr_type,String card,List<CreateValues> create_values, List<Ld_sv_gate> cards,
			int btn_click,
			String alias,String btn,Res res) {
		ISLogger.getLogger().error("Запущено оформление кредита //13.01.2020 yil");
		ISLogger.getLogger().error("!Updated");
		ISLogger.getLogger().error("!BPR_ID! = "+bpr_id+"!");
		ISLogger.getLogger().error("!bpr_type! = "+bpr_type+"!");
		Connection c = null;
		CallableStatement cs = null;
		CallableStatement acs = null;
		CallableStatement ccs = null;
		CallableStatement csi = null;
		String data = "";
		System.out.println(card+" вот номер карты");
		try {
			ISLogger.getLogger().error("pre if!");
			if(useCard(alias, card)){
				res.setCode(1);
				res.setName("Данная карта уже используется в зарегистрированном кредите //13.01.2020 yil");
				return;
			}
			ISLogger.getLogger().error("After if!");
			ISLogger.getLogger().error("!login! = "+un);
			ISLogger.getLogger().error("!pass! = "+pw);
			ISLogger.getLogger().error("!alias! = "+alias);
			c = ConnectionPool.getConnection(un, pw,alias);
			csi = c.prepareCall("{ call info.init() }");
			cs = c.prepareCall("{ call Param.SetParam(?,?) }");
			acs = c.prepareCall("{ call kernel.doaction(?,?,?) }");
			ccs = c.prepareCall("{ call Param.clearparam() }");
			ccs.execute();
			// ------------------------------ Set param - общие параметры
			// -------------------------------
			setParam("ID", bproduct.getId()+"", cs);
			setParam("P_BTN_CLICK", btn_click+"", cs);
			if(bproduct.getNi_req_id()>0) setParam("P_NIREQ_ID", bproduct.getNi_req_id()+"", cs);
			setParam("P_BPRODUCT_ID", bproduct.getId()+"", cs);
			if (!CheckNull.isEmpty(br)) {
				setParam("P_BRANCH", br, cs);
			}
			if (!CheckNull.isEmpty(br)) {
				setParam("P_BPR_TYPE", bpr_type, cs);
			}
			if (!CheckNull.isEmpty(cl_id)) {
				setParam("P_ID_CLIENT", cl_id, cs);
			}
			ISLogger.getLogger().error("!bpr_id! = "+bpr_id);
			if (!CheckNull.isEmpty(bpr_id)) {
				setParam("P_BPR_ID", bpr_id, cs);
				ISLogger.getLogger().error("!bpr_id! = "+bpr_id);
			}
			if (!CheckNull.isEmpty(dateContractComp)) {
				setParam("P_DATE_END_CONTRACT", dateContractComp, cs);
			}
			ISLogger.getLogger().error("Trans_id = "+trans_id);
			if (!CheckNull.isEmpty(trans_id)) {
				ISLogger.getLogger().error("Trans_id = "+trans_id);
				setParam("P_TRANS_ID", Integer.toString(trans_id), cs);
			}
			if (!CheckNull.isEmpty(head_of_bank)) {
				setParam("P_HEAD_OF_BANK", head_of_bank, cs);
			}
			// ------------------------------ Set param - общие параметры
			// -------------------------------

			// ------------------------- № пЛАСТИКОВОЙ КАРТЫ
			// ---------------------------------
			if(bpr_type.equals("1")||bpr_type.equals("4")||bpr_type.equals("5")){
				setParam("P_CARD_NUMBER", card, cs);
				ISLogger.getLogger().error("P_CARD_NUMBER = "+card);
			}
			System.out.println(parameters.size()+" размер параметров");
			for (int i = 0; i < parameters.size(); i++) {
				ISLogger.getLogger().error("Запущен цикл столько раз = "+i);
				if (parameters.get(i).getBpr_type() == 1 || parameters.get(i).getBpr_type() == 4 || parameters.get(i).getBpr_type() == 5) {
					setParam("P_CARD_NUMBER", card, cs);
					ISLogger.getLogger().error("P_CARD_NUMBER = "+card);
				} else if (parameters.get(i).getBpr_type() == 2) {
					setParam("P_DEP_ID_01", parameters.get(0).getParam_value(), cs);
					setParam("P_DEP_ID_02", parameters.get(2).getParam_value(), cs);
					setParam("P_DEP_ID_03", parameters.get(4).getParam_value(), cs);
					setParam("P_DEP_ID_04", parameters.get(6).getParam_value(), cs);
					setParam("P_DEP_ID_05", parameters.get(8).getParam_value(), cs);
				} 
			}
			// ------------------------- № пЛАСТИКОВОЙ КАРТЫ
			
			// ----------------------------- СЧЕТА ---------------------------
			for (int j = 0; j < accountList.size(); j++) {
				if (!CheckNull.isEmpty(accountList.get(j).getLabel())) {
					if (accountList.get(j).getLabel().length() == 21) {
						data = ("000" + accountList.get(j).getLabel());
						if (j == 0) {
							j = j + 1;
						}
						setParam("P_ACCOUNT_" + j + "", data, cs);
						cs.setString(1, "P_ACCOUNT_" + j + ""); cs.setString(2, data); cs.execute();
						System.out.println("P_ACCOUNT_" + j + "");
						System.out.println(data);
					} else if (accountList.get(j).getLabel().length() == 22) {
						data = ("00" + accountList.get(j).getLabel());
						if (j == 0) {
							j = j + 1;
						}
						setParam("P_ACCOUNT_" + j + "", data, cs);
						System.out.println("P_ACCOUNT_" + j + "");
						System.out.println(data);
					} else if (accountList.get(j).getLabel().length() == 23) {
						data = ("0" + accountList.get(j).getLabel());
						if (j == 0) {
							j = j + 1;
						}
						setParam("P_ACCOUNT_" + j + "", data, cs);
						System.out.println("P_ACCOUNT_" + j + "");
						System.out.println(data);
					}

				}
			}// ----------------------------- СЧЕТА ---------------------------
			ISLogger.getLogger().error("ID нажатой кнопки = "+btn_click);
			if(cl_id.equals("99246674"))
				ISLogger.getLogger().error("Before doAction");
			acs.setInt(1, 193);
			acs.setString(2, bpr_type);
			acs.setInt(3, btn_click);
			System.out.println(193 + " " + bpr_type + " " + btn_click);
			for (int j = 0; j < create_values.size(); j++) {
				if(create_values.get(j).getParam()!=null&&!create_values.get(j).getParam().equals("")){
						//String value = create_values.get(j).getValue().replace(",", ".");
						cs.setString(1, create_values.get(j).getParam().trim());
						cs.setString(2, create_values.get(j).getValue());
						//cs.setString(2, value);
						ISLogger.getLogger().error("ПАРАМ = "+create_values.get(j).getParam());
						ISLogger.getLogger().error("Value = "+create_values.get(j).getValue());
						System.out.println("ПАРАМ = "+create_values.get(j).getParam());
						System.out.println("Value = "+create_values.get(j).getValue());
						cs.execute();
				}
			}
			csi.execute();
			acs.execute();
			ISLogger.getLogger().error("After doAction "+cl_id);
			Long id = null;
			if(btn_click!=4){
				id = getIdProduct(c);
			} else {
				id = bproduct.getId();
			}
			ISLogger.getLogger().error("Id bp = "+id);
			Create create = new Create();
			create.setId(id);
			create.setTid(Long.parseLong(bpr_id));
			create.setBranch(br);
			create.setDate_bank(Utils.getInfoDate(alias, res));
			System.out.println("bpr ser = "+create_values.size());
			if(btn_click!=4){
				CreateService.save(create, create_values, c);
			} else {
				CreateService.update(create, create_values, res);
			}
			if(balls != null && !balls.isEmpty() && !btn.equals("double")){
				saveScoringBallForm(id, balls, c);
			}
			if(cards != null && btn_click != 3) {
				Long form_id = Long.parseLong(getForm_id(id, br, c));
//				deleteCards(br,form_id,c);
				for (int i = 0; i < cards.size(); i++) {
					saveCards(cards.get(i), cl_id, br, form_id, c);
				}
			}
			ISLogger.getLogger().error("Before commit "+cl_id);
			c.commit();
			ISLogger.getLogger().error("After commit "+cl_id);
//			String niki_id = saveAsokiRequest(alias, c, cs, id, cl_id, br, res);
//			if(res.getCode()==1){
//				throw new Exception(res.getName());
//			}
//			JSONObject jsonResponse = new JSONObject(sendAsokiRequest(getJsonForAsoki(niki_id, br, alias)));
//			JSONObject result = jsonResponse.getJSONObject("result");
//			updateAsokiState(getParam("REQUEST_ID", c), result, c);
			System.out.println("Всё отправлено и закомичено");
		} catch (Exception e) {
			res.setCode(1);
			res.setName(CheckNull.getPstr(e));
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			Utils.rollback(c);
			e.printStackTrace();
		} finally {
			Utils.close(ccs);
			Utils.close(acs);
			Utils.close(csi);
			Utils.close(cs);
			ConnectionPool.close(c);
		}
	}
	
	protected static void saveCards(Long id, String branch, String client, List<Ld_sv_gate> cards, Res res) {
		Connection c = null;
		try {
			c = ConnectionPool.getConnection();
			Long form_id = Long.parseLong(getForm_id(id, branch, c));
			deleteCards(branch, form_id, c);
			if(cards!=null){
				for (int i = 0; i < cards.size(); i++) {
					saveCards(cards.get(i), client, branch, form_id, c);
				}
			}
			c.commit();
		} catch (Exception e) {
			Utils.rollback(c);
			res.setCode(1);
			res.setName(CheckNull.getPstr(e));
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
	}
	
//	private static void updateAsokiState(String request_id, JSONObject obj, Connection c) throws Exception {
//		PreparedStatement ps = null;
//		try {
//			ps = c.prepareStatement("update asoki_01 set state_code = ?, state_name = ? where request_id = ?");
//			ps.setString(1, obj.getString("code").equals("00000") ? "4" : "5");
//			ps.setString(2, obj.getString("code").equals("00000") ? "Подтверждён" : obj.getString("message"));
//			ps.setString(3, request_id);
//			ps.execute();
//			ps = c.prepareStatement("update asoki_02 set state_code = ?, state_name = ? where request_id = ?");
//			ps.setString(1, obj.getString("code").equals("00000") ? "4" : "5");
//			ps.setString(2, obj.getString("code").equals("00000") ? "Подтверждён" : obj.getString("message"));
//			ps.setString(3, request_id);
//			ps.execute();
//		} finally {
//			Utils.close(ps);
//		}
//	}
	
//	private static JSONObject getJsonForAsoki(String niki_id, String branch, String alias) throws Exception{
//		JSONObject obj = new JSONObject();
//		obj.put("credit_bureau", 1);
//		obj.put("application_id", niki_id);
//		String code_type = Utils.getData("select code_type from client where id_client = (select id_client from ni_req where id = "+niki_id+" and branch = '"+branch+"') and branch = '"+branch+"'", alias);
//		obj.put("request_type", code_type.equals("08")||code_type.equals("11") ? 1 : 2);
//		obj.put("branch", branch);
//		return obj;
//	}
	
//	private static String sendAsokiRequest(JSONObject object) throws Exception{
//		String url = getNikiAsokiSenderUrl();
//		HttpPost httpPost = createConnectivity(url);
//		return executeReq(object.toString(), httpPost);
//	}
	
//	private static HttpPost createConnectivity(String restUrl) throws Exception{
//        HttpPost post = new HttpPost(restUrl);
//        post.setHeader("Content-Type", "application/json");
//        post.setHeader("Accept", "application/json");
//        post.setHeader("X-Stream" , "true");
//        return post;
//    }
	
//	private static String executeReq(String jsonData, HttpPost httpPost) throws Exception{
//		String json = null;
//        try{
//            json = executeHttpRequest(jsonData, httpPost);
//        }
//        catch (UnsupportedEncodingException e){
//        	e.printStackTrace();
//        	ISLogger.getLogger().error(CheckNull.getPstr(e));
//        	ISLogger.getLogger().error("error while encoding api url : "+e.getMessage());
//        }
//        catch (IOException e){
//        	ISLogger.getLogger().error(CheckNull.getPstr(e));
//        	e.printStackTrace();
//            System.out.println("ioException occured while sending http request : "+e);
//        }
//        catch(Exception e){
//        	ISLogger.getLogger().error(CheckNull.getPstr(e));
//            System.out.println("exception occured while sending http request : "+e);
//        }
//        finally{
//            httpPost.releaseConnection();
//        }
//        return json;
//    }
	
//	private static String executeHttpRequest(String jsonData,  HttpPost httpPost)  throws UnsupportedEncodingException, IOException, Exception {
//		HttpResponse response=null;
//        httpPost.setEntity(new StringEntity(jsonData));
//        HttpClient client = HttpClientBuilder.create().build();
//        response = client.execute(httpPost);
//        return EntityUtils.toString(response.getEntity(), "UTF-8");
//    }
	
//	private static String saveAsokiRequest(String alias, Connection c, CallableStatement setParam, Long id, String client_id, String branch, Res res) throws Exception{
//		CallableStatement cs = null;
//		String form_id = getForm_id(id, "", c);
//		String niki_id = null;
//		PreparedStatement ps = null;
//		try {
//			System.out.println("form_id = "+form_id);
//			System.out.println("ID = "+id);
//			niki_id = getNiki_id(form_id, id, branch, c, alias);
//			System.out.println("NIKI_ID = "+niki_id);
//			setParam("NIKI_ID", niki_id, setParam);
//			setParam("BRANCH", branch, setParam);
//			setParam("D_DATE", sdf.format(Utils.getInfoDate(alias, res)), setParam);
//			setParam("EMP_CODE", Utils.getInfoEmp(alias, res), setParam);
//			setParam("PUSK_DAY", "2", setParam);
//			setParam("USER_REG_OBNOV", "1", setParam);
//			setParam("IS_LIST01", "3", setParam);
//			ps = c.prepareStatement("update ni_req set state = 2 where id = ?");
//			ps.setString(1, niki_id);
//			ps.execute();
//			cs = c.prepareCall("{ call asoki.asoki_CI_01_02 }");
//			cs.execute();
//			if(getParam("ASOKI_STATE", c).equals("0")) {
//				res.setCode(1);
//				res.setName(getParam("ASOKI_TEXT", c));
//			}
//			ps = c.prepareStatement("update ni_req set state = 1 where id = ?");
//			ps.setString(1, niki_id);
//			ps.execute();
//		} finally {
//			Utils.close(cs);
//			Utils.close(ps);
//		}
//		return niki_id;
//	}
	
//	private static String getParam(String paramName, Connection c) throws Exception{
//		CallableStatement cs = null;
//		String param = null;
//		try {
//			cs = c.prepareCall("{ ? = call param.GetParam(?) }");
//			cs.registerOutParameter(1, OracleTypes.VARCHAR);
//			cs.setString(2, paramName);
//			cs.execute();
//			param = cs.getString(1);
//		} finally {
//			Utils.close(cs);
//		}
//		return param;
//	}
	
	private static void setParam(String paramName, String paramValue, CallableStatement cs) throws Exception{
		ISLogger.getLogger().error("PARAM = "+paramName+"\n"+"Value = "+paramValue);
		cs.setString(1, paramName);
		cs.setString(2, paramValue);
		cs.execute();
	}
	
	private static void saveScoringBallForm(Long id,List<BallScoringViewModel> list,Connection c) throws Exception{
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement("insert into bpr_ball_scrong_result (id,question,selected_answer,ball) values (?,?,?,?)");
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).isVisible()){
					ps.setLong(1, id);
					ps.setString(2, list.get(i).getQuestion());
					ps.setString(3, list.get(i).getSelectedAnswer());
					ps.setLong(4, list.get(i).getResult_ball()==null?0L:list.get(i).getResult_ball());
					ps.execute();
				}
			}
		} finally {
			Utils.close(ps);
		}
	}
	
	private static void deleteCards(String branch, Long id, Connection c) throws Exception{
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement("delete from ld_sv_gate where branch = ? and id = ?");
			ps.setString(1, branch);
			ps.setLong(2, id);
			ps.execute();
		} finally {
			Utils.close(ps);
		}
	}
	
	private static void saveCards(Ld_sv_gate card, String client, String branch, Long id, Connection c) throws Exception{
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement("insert into ld_sv_gate (branch,id,card_number,expiry_date,sign_client,sign_card) values (?,?,?,?,?,?)");
			ps.setString(1, branch);
			ps.setLong(2, id);
			ps.setString(3, card.getCard_number());
			ps.setString(4, card.getExpiry_date());
			ps.setInt(5, card.getSign_client());
			ps.setString(6, cardValidation(card, client, branch, id, c)?"1":"2");
			ps.execute();
		} finally {
			Utils.close(ps);
		}
	}
	
	private static boolean cardValidation(Ld_sv_gate card, String client, String branch, Long id, Connection c) throws Exception{
		if(card.getCard_number().length()!=16) throw new Exception(CARD_FORMAT_EXCEPTION+" '"+card.getCard_number()+"'");
		String card_bank_type = card.getCard_number().substring(3, 6);
		boolean isValid = false;
		if(card_bank_type.equals(getBank_type(branch))){
			isValid = findCard(card.getCard_number(), client, branch, c);
		}
		return isValid;
	}
	
	private static boolean findCard(String card, String client, String branch, Connection c) throws Exception{
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean bool = false;
		try {
			ps = c.prepareStatement("select card_status from card where card_number = ?");
			ps.setString(1, card);
			rs = ps.executeQuery();
			if(rs.next()){
				if(!rs.getString(1).equals("CRST0")){
					throw new Exception("Карта заблокирована");
				} 
				bool = !bool;
			} else { throw new Exception(CARD_NOT_FOUND_EXCEPTION+" '"+card+"'"); }
		} finally {
			Utils.close(rs);
			Utils.close(ps);
		}
		return bool;
	}
	
	protected static List<SavedAnswer> getSavedAnswers(Long id){
		List<SavedAnswer> list = new ArrayList<SavedAnswer>();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select * from bpr_ball_scrong_result where id = ?");
			ps.setLong(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				SavedAnswer savedAnswer = new SavedAnswer();
				savedAnswer.setBall(rs.getLong("ball"));
				savedAnswer.setId(rs.getLong("id"));
				savedAnswer.setQuestion(rs.getString("question"));
				savedAnswer.setSelected_answer(rs.getString("selected_answer"));
				list.add(savedAnswer);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return list;
	}
	
	protected static void beginIssuance(
			String branch,
			String id,
			String branch_co,
			String account_co,
			String acc_name_co,
			Double summa,
			String alias,
			Res res){
		Connection c = null;
		CallableStatement cs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			cs = c.prepareCall("{ call PROC_LD1.doOper01(?,?,?,?,?,?) }");
			cs.setString(1, branch);
			cs.setString(2, id);
			cs.setString(3, branch_co);
			cs.setString(4, account_co);
			cs.setString(5, acc_name_co);
			cs.setDouble(6, summa);
			cs.execute();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			Utils.rollback(c);
			res.setCode(1);
			res.setName(CheckNull.getPstr(e));
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
	}
	
	protected static ProductInfo getProductInfo(String bpr_id,String alias){
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ProductInfo info = null;
		Product_ld_char ld_char = null;
		Product_ld_exp [] ld_exps = null;
		String [] guarrs = null;
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		df.setMinimumFractionDigits(0);
		df.setGroupingUsed(false);
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select c.currency,c.ld_amount,c.shifr_id,shifr.shifr_name,c.kred_id_cb,cb.kred_name,c.calc_id,calc.name calc_name,c.term_type,term.type_kr,c.kred_id,credit.kred_name,c.use_branch,zm.name_zm from S_SHIFR shifr,bpr_ld_char c,s_credit cb,SS_LD_CALC_METHOD calc,S_SROKKR term,ss_credit credit,S_TYPE_ZM zm where zm.kod_zm||zm.kod_pod=c.use_branch and credit.kred_id (+) = c.kred_id and term.kod_kr (+) = c.term_type and calc.id (+) = c.calc_id and cb.kred_id (+) = c.kred_id_cb and shifr.kod_gr||shifr.kod_pgr||shifr.shifr_id=c.shifr_id and bpr_id=?");
			ps.setString(1, bpr_id);
			rs = ps.executeQuery();
			if(rs.next()){
				ld_char = new Product_ld_char(rs.getString("currency"),
												df.format(rs.getDouble("ld_amount")/100),
												rs.getString("shifr_name"),
												rs.getString("kred_name"),
												rs.getString("calc_name"),
												rs.getString("type_kr"),
												rs.getString("kred_name"),
												rs.getString("name_zm"));
			}
			Utils.close(rs);
			Utils.close(ps);
			int size_array = 0;
			ps = c.prepareStatement("SELECT count(*) FROM (select et.name, be.bpr_id, br.rate from bpr_ld_exp be, bpr_ld_rate br, ss_ld_exp_type et where et.id = be.exp_id and et.id = br.exp_id and be.id = br.id and be.bpr_id = br.bpr_id and et.id<13 order by be.bpr_id)  where bpr_id=?");
			ps.setString(1, bpr_id);
			rs = ps.executeQuery();
			if(rs.next()){
				size_array = rs.getInt(1);
			}
			if(size_array>0){
				Utils.close(rs);
				Utils.close(ps);
				ps = c.prepareStatement("SELECT * FROM (select et.name, be.bpr_id, br.rate from bpr_ld_exp be, bpr_ld_rate br, ss_ld_exp_type et where et.id = be.exp_id and et.id = br.exp_id and be.id = br.id and be.bpr_id = br.bpr_id and et.id<13 order by be.bpr_id)  where bpr_id=?");
				ps.setString(1, bpr_id);
				rs = ps.executeQuery();
				ld_exps = new Product_ld_exp[size_array];
				int index = 0;
				while(rs.next()){
					Product_ld_exp ld_exp = new Product_ld_exp(rs.getString(1), df.format(rs.getDouble(3)));
					ld_exps[index] = ld_exp;
					index++;
				}
			}
			Utils.close(rs);
			Utils.close(ps);
			ps = c.prepareStatement("select count(*) from bpr_ld_guarr where bpr_id=?");
			ps.setString(1, bpr_id);
			rs = ps.executeQuery();
			if(rs.next()) size_array = rs.getInt(1);
			if(size_array>0){
				Utils.close(rs);
				Utils.close(ps);
				ps = c.prepareStatement("select g.guar_id,o.guar_name from bpr_ld_guarr g,s_obesp o where bpr_id=? and g.guar_id=o.guar_id");
				ps.setString(1, bpr_id);
				rs = ps.executeQuery();
				guarrs = new String[size_array];
				int index = 0;
				while(rs.next()){
					String guarr = rs.getString(2);
					guarrs[index] = guarr;
					index++;
				}
			}
			info = new ProductInfo();
			info.setLd_char(ld_char);
			info.setLd_exps(ld_exps);
			info.setLd_guarr_type(guarrs);
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return info;
	}
	
	private static Long getIdProduct(Connection c) throws SQLException{
		Long id = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ps = c.prepareStatement("select * from (select id from bproduct order by id desc) where rownum = 1");
		rs = ps.executeQuery();
		if(rs.next()) id = rs.getLong(1);
		Utils.close(rs);
		Utils.close(ps);
		return id;
	}
	
	private static String getCond(List<FilterField> flfields) {
		if (flfields.size() > 0) {
			return " and ";
		} else
			return " where ";
	}
	
	public static List<RefData> getSMFO(String alias){
		return Utils.getRefData("select s_mfo.bank_id data,s_mfo.bank_id||' - '||s_mfo.bank_name label from s_mfo order by s_mfo.bank_id",alias);
	}

	private static List<FilterField> getFilterFields(BproductFilter filter) {
		List<FilterField> flfields = new ArrayList<FilterField>();
		if (filter.getId()!=null&&!CheckNull.isEmpty(filter.getId())) {
			flfields.add(new FilterField(getCond(flfields) + "id=?", filter.getId()));
		}
		if (!CheckNull.isEmpty(filter.getBranch())) {
			flfields.add(new FilterField(getCond(flfields) + "branch=?", filter.getBranch()));
		}
		if (!CheckNull.isEmpty(filter.getCustomer())) {
			flfields.add(new FilterField(getCond(flfields) + "customer=?",filter.getCustomer()));
		}
		if (filter.getProdid()!=null&&!CheckNull.isEmpty(filter.getProdid())) {
			flfields.add(new FilterField(getCond(flfields) + "prodid=?", filter.getProdid()));
		}
		if (!CheckNull.isEmpty(filter.getVdate())) {
			flfields.add(new FilterField(getCond(flfields) + "vdate=?", new java.sql.Date(filter.getVdate().getTime())));
		}
		if (!CheckNull.isEmpty(filter.getCurrency())) {
			flfields.add(new FilterField(getCond(flfields) + "currency=?",filter.getCurrency()));
		}
		if (filter.getAmount()!=null&&!CheckNull.isEmpty(filter.getAmount())) {
			flfields.add(new FilterField(getCond(flfields) + "amount=?", filter.getAmount()));
		}
		if (filter.getEmp_id()!=null&&!CheckNull.isEmpty(filter.getEmp_id())) {
			flfields.add(new FilterField(getCond(flfields) + "emp_id=?", filter.getEmp_id()));
		}
		if (filter.getState()!=null) {
			flfields.add(new FilterField(getCond(flfields) + "state=?", filter.getState()));
		}
		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));
		return flfields;
	}

	public static int getCount(BproductFilter filter, String alias) {

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM Bproduct ");
		if (flFields.size() > 0) {
			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement(sql.toString());
			for (int k = 0; k < flFields.size(); k++) {
				ps.setObject(k + 1, flFields.get(k).getColobject());
			}
			rs = ps.executeQuery();
			if (rs.next()) {
				n = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return n;

	}

	public static List<Bproduct> getBproductsFl(int pageIndex, int pageSize,
			BproductFilter filter, String alias) {
		ISLogger.getLogger().error("тут алиас "+alias);
		List<Bproduct> list = new ArrayList<Bproduct>();
		Connection c = null;
		int v_lowerbound = pageIndex + 1;
		int v_upperbound = v_lowerbound + pageSize - 1;
		int params;
		List<FilterField> flFields = getFilterFields(filter);
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append(psql1);
		sql.append(msql);
		if (flFields.size() > 0) {

			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		sql.append(psql2);

		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement(sql.toString());
			for (params = 0; params < flFields.size(); params++) {
				ps.setObject(params + 1, flFields.get(params).getColobject());
			}
			params++;
			ps.setInt(params++, v_upperbound);
			ps.setInt(params++, v_lowerbound);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Bproduct(
						rs.getInt("id"),
						rs.getString("branch"),
						rs.getString("customer"), 
						rs.getInt("prodid"),
						rs.getString("vdate")==null?"":sdf.format(new Date(rs.getDate("vdate").getTime())), 
						rs.getString("currency"), 
						rs.getLong("amount")/100+"",
//						decf.format(rs.getLong("amount")/100), 
						rs.getInt("emp_id"), 
						rs.getInt("state"),
						getUname(rs.getString("branch"), rs.getInt("emp_id")),
						rs.getString("bpr_name"),
						rs.getString("name"),
//						rs.getString("detail_id"),
						rs.getInt("ni_req_id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return list;

	}
	
	private static String getUname(String branch,int uid){
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String uname = null;
		try {
			c = ConnectionPool.getConnection(getAlias(branch));
			ps = c.prepareStatement("select u.user_name from users u where u.branch=? and u.id=?");
			ps.setString(1, branch);
			ps.setInt(2,uid);
			rs = ps.executeQuery();
			if(rs.next()){
				uname = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return uname;
	}
	
	private static String getAlias(String branch){
		String alias = "";
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select user_name from ss_dblink_branch where branch=?");
			ps.setString(1, branch);
			rs = ps.executeQuery();
			if(rs.next()) alias = rs.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return alias;
	}
	
	public static void update(String cl_id, String alias) {

		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement(" UPDATE bpr_addinfo SET client_code=? WHERE id=1 and bpr_type = 1 ");
			ps.setString(1, cl_id);
			ps.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(ps);
			ConnectionPool.close(c);
		}

	}

	public static List<RefData> getProdId(String mfo,String reg_id,String client_type,String branch) {
		String sql = "";
		String temp = "";
		if(!client_type.equals("")){
			temp = " and target_clients='"+client_type+"' ";
		} 
		if(mfo.equals("")){
			sql = "select bpr_id data, bpr_id ||' - '|| bpr_name label from ss_bpr_type where state=1 "+temp+" order by data";
		} else {
			sql = "select bpr_id data, bpr_id ||' - '|| bpr_name label from ss_bpr_type where state=1 "+temp+" and (mfo = '"+mfo+"' or mfo is null) and (region = '"+reg_id+"' or region is null) order by data";
		}
		return Utils.getRefData(sql,branch);
	}

	public static List<RefData> getCurrency(String branch){ // ---------- Валюты
		return Utils.getRefData("select kod data, kod ||' - '|| namev label from s_val order by data",branch);
	}
	
	public static List<RefData> getTypeClient(String cl_code,String mfo,String branch){ // ---------- Тип клиента
		return Utils.getRefData("select  client.code_type data,client.code_subject label from client where client.id_client='"+cl_code+"' and branch='"+mfo+"'",branch);
	}
	
	public static List<RefData> getTypeClientCb(String branch){ // ---------- Тип клиента
		return Utils.getRefData("select S_TYPEKL.KOD_K data,S_TYPEKL.Name_K2 label from S_TYPEKL",branch);
	}
	
	public static List<RefData> getStateAnket(String branch){ // ---------- Тип клиента
		return Utils.getRefData("select ss_state_anket.id data,ss_state_anket.name label from ss_state_anket order by data",branch);
	}

	public static String getTrans_id(String alias, int bpr_id) {
		Connection c = null;
		String res = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select s.deal_id dealId from ss_bpr_type s where bpr_id  = ? ");
			ps.setInt(1, bpr_id);
			rs = ps.executeQuery();
			if(rs.next()){
				res = rs.getString("dealId");
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return res;
	}

	public static int getBpr_type_value(String alias, int bpr_id) {
		Connection c = null;
		int res = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select s.bpr_type val from ss_bpr_type s where s.bpr_id=? ");
			ps.setInt(1, bpr_id);
			rs = ps.executeQuery();
			if(rs.next()){
				res = rs.getInt("val");
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return res;
	}

	public static String getCustomerName(String branch,String alias,String cl_id) {
		Connection c = null;
		String res = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select client.name from client where client.id_client=? and client.branch=?");
			ps.setString(1, cl_id);
			ps.setString(2, branch);
			rs = ps.executeQuery();
			if (rs.next()) {
				res = rs.getString(1);
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return res;
	}

	public static String getCustomerNameForDeposit(String alias, String br,
			String cl_id) {
		Connection c = null;
		String res = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select s.name label from sd_customer s where s.branch = ? and s.client_code = ? ");
			ps.setString(1, br);
			ps.setString(2, cl_id);
			rs = ps.executeQuery();
			if(rs.next()){
				res = rs.getString("label");
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return res;
	}

	public static String getBproductName(String alias, int bpr_id) {
		Connection c = null;
		String res = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select s.bpr_name label from ss_bpr_type s where s.bpr_id = ? ");
			ps.setInt(1, bpr_id);
			rs = ps.executeQuery();
			if(rs.next()){
				res = rs.getString("label");
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return res;
	}

	public static String getHeadOfBankValue(String alias, int bpr_id) {
		Connection c = null;
		String res = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select blf.head_of_bank label from bpr_ld_forms blf where blf.bpr_id = ? ");
			ps.setInt(1, bpr_id);
			rs = ps.executeQuery();
			if(rs.next()){
				res = rs.getString("label");
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return res;
	}

	public static Long getProvisionVal(String alias, int bpr_id) {
		Connection c = null;
		Long res = 0L;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select s.provision val from ss_bpr_type s where s.bpr_id = ? ");
			ps.setInt(1, bpr_id);
			rs = ps.executeQuery();
			if(rs.next()){
				res = rs.getLong("val");
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return res;
	}

	public static List<RefData> getBpr_ld_account(String alias, int bpr_id) {
		List<RefData> list = new LinkedList<RefData>();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select b.bpr_id data, b.acc_type_id||b.account label from bpr_ld_account b where b.bpr_id = ? ");
			ps.setInt(1, bpr_id);
			rs = ps.executeQuery();
			while (rs.next())
				list.add(new RefData(rs.getString("data"), rs
						.getString("label")));
		} catch (SQLException e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return list;
	}

	// ------------------------Added part for tests---------------------------

	public static String getCardNumber(String alias) {
		Connection c = null;
		String res = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select b.param_value card_number from bpr_addinfo b where id=1 ");
			rs = ps.executeQuery();
			if(rs.next()){
				res = rs.getString("card_number");
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return res;
	}

	public static String getPreviousClient_code() {
		Connection c = null;
		String res = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement(" select b.client_code val from bpr_addinfo b where id  = 2 ");
			rs = ps.executeQuery();
			if (rs.next()) {
				res = rs.getString("val");
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return res;
	}

	public static List<RefData> getListOfCards(String alias, String branch,
			String client_code) {
		List<RefData> list = new LinkedList<RefData>();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select c.card_number data, c.card_number label from card c , client cc where c.branch=? and c.branch=cc.branch and cc.state=2 and cc.id_client=? and substr(substr(c.def_atm_account,-20),10,8 )=? ");
			ps.setString(1, branch);
			ps.setString(2, client_code);
			ps.setString(3, client_code);
			rs = ps.executeQuery();
			while (rs.next())
				list.add(new RefData(rs.getString("data"), rs
						.getString("label")));
		} catch (SQLException e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return list;
	}
	
	public static String getBprType(String bpr_id,String alias){
		String temp = "";
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select ss_bpr_type.bpr_type from ss_bpr_type where ss_bpr_type.bpr_id=?");
			ps.setString(1, bpr_id);
			rs = ps.executeQuery();
			if(rs.next()){
				temp = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return temp;
	}
	
	public static BproductClientsModel getDatasClientP(String cl_code,String branch,String alias,Res res){
		BproductClientsModel model = new BproductClientsModel();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select client_p.name,client_p.passport_serial||''||client_p.passport_number,client_p.number_tax_registration,client_p.inps,client_p.phone_home,client_p.phone_mobile,client_p.email_address from client_p where client_p.id=? and branch=?");
			ps.setString(1, cl_code);
			ps.setString(2, branch);
			rs = ps.executeQuery();
			if(rs.next()){
				model.setName(rs.getString(1));
				model.setPassport(rs.getString(2));
				model.setNumber_tax_registration(rs.getString(3));
				model.setInps(rs.getString(4));
				model.setPhone_home(rs.getString(5));
				model.setPhone_mobile(rs.getString(6));
				model.setEmail_address(rs.getString(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setCode(1);
			res.setName(CheckNull.getPstr(e));
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return model;
	}
	
	public static BproductClientsModel getDatasClientJ(String cl_code,String alias,Res res){
		BproductClientsModel model = new BproductClientsModel();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select client_j.name,client_j.number_tax_registration,client_j.phone,client_j.fax,client_j.email from client_j where client_j.id=?");
			ps.setString(1, cl_code);
			rs = ps.executeQuery();
			if(rs.next()){
				model.setName(rs.getString(1));
				model.setNumber_tax_registration(rs.getString(2));
				model.setPhone_home(rs.getString(3));
				model.setFax(rs.getString(4));
				model.setEmail_address(rs.getString(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setCode(1);
			res.setName(CheckNull.getPstr(e));
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return model;
	}
	
	protected static Double getAmount(String bpr_id,String alias){
		Double amount = null;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select bpr_ld_char.ld_amount from bpr_ld_char where bpr_ld_char.bpr_id=?");
			ps.setString(1, bpr_id);
			rs = ps.executeQuery();
			if(rs.next()){
				amount = rs.getDouble(1)/100;
				ISLogger.getLogger().error("Сумма = "+amount+"  BPR ID = "+bpr_id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return amount;
	}
	
	protected static List<RefData> getWorkFromClient(String client_id,String branch,String alias){
		return Utils.getRefData("select j.number_tax_registration, j.name from client_j j where exists (select * from card_client_work w where w.customer_id = j.id and w.employee_id='"+client_id+"' and w.branch='"+branch+"' )", alias);
	}
	
	protected static List<RefData> getCardNumberFromAcc(String acc,String branch,String alias){
		return Utils.getRefData("select card.card_number,card.card_number from card where substr(card.def_pos_account,6)='"+acc+"' and CARD_STATUS='CRST0' and branch='"+branch+"'", alias);
	}
		
	protected static List<RefData> getClientcard(String cl_id,String branch,String acc,String alias){
		return Utils.getRefData("SELECT C.CARD_NUMBER DATA, C.CARD_NUMBER LABEL FROM CARD C , CLIENT CC WHERE C.BRANCH='"+branch+"' AND C.BRANCH=CC.BRANCH AND CC.STATE=2 AND CC.ID_CLIENT='"+cl_id+"' AND C.CARD_STATUS='CRST0' AND SUBSTR(SUBSTR(C.DEF_ATM_ACCOUNT,-20),10,8 )='"+cl_id+"'"
			+" union all select to_char(h.real_card) DATA, to_char(h.real_card) LABEL from humo_cards h where h.branch='"+branch+"' and h.client_b='"+cl_id+"'", alias);
	}
	
	
	protected static String getCardReg(Long bprod_id,String alias){
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String result = "";
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select b.detail_id from bproduct_desc b where b.id=? and b.detail_group=117 and b.state < 90");
			ps.setLong(1, bprod_id);
			rs = ps.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return result;
	}
	
	protected static void changeCard(Integer uid,String name,String newCard,String oldCard,String branch,String id,String client,String alias,Res res){
		Connection c =null;
		PreparedStatement ps = null;
		Integer id_ = null;
		Date date = new Date();
		Statement st = null;
		ResultSet rs = null;
		CallableStatement cs = null;
		try {
			if(useCard(alias, newCard)){
				res.setCode(1);
				res.setName("Данная карта уже используется в зарегистрированном кредите");
				return;
			}
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("update bproduct_desc set bproduct_desc.detail_id=? where bproduct_desc.id=? and bproduct_desc.detail_group=117 and bproduct_desc.state < 90");
			ps.setString(1, newCard);
			ps.setString(2, id);
			ps.execute();
			ps = c.prepareStatement("SELECT SEQ_BPR_USER_ACTIONS_LOG.NEXTVAL bpr_id FROM DUAL");
			rs = ps.executeQuery();
			if(rs.next()){
				id_ = rs.getInt(1);
			}
			cs = c.prepareCall("{ call info.init() }");
			cs.execute();
			st = c.createStatement();
			rs = st.executeQuery("select info.GetDay from dual");
			if(rs.next()){
				date = rs.getDate(1);
			}
			ps = c.prepareStatement("insert into BPR_USER_ACTIONS_LOG (id,USER_ID,USER_NAME,ACTION_DATE,ACT_TYPE,change_id,client_id) values (?,?,?,?,?,?,?)");
			ps.setInt(1, id_);
			ps.setInt(2, uid);
			ps.setString(3, name);
			ps.setDate(4, date==null?null:new java.sql.Date(date.getTime()));
			ps.setInt(5, 4);
			ps.setString(6, id);
			ps.setString(7, client);
			ps.execute();
			c.commit();
		} catch (Exception e) {
			try {
				c.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			res.setCode(1);
			res.setName(CheckNull.getPstr(e));
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(st);
			Utils.close(cs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
	}
	
	public static List<RefData> getMfo(String branch,String alias){
		List<RefData> list = null;
		if(isHeader(branch, alias)){
			list = Utils.getRefData("select * from (select '0' data, 'Все филиалы' label from  dual union all select s_mfo.bank_id data, s_mfo.bank_id || ' - ' || s_mfo.bank_name label from s_mfo where s_mfo.header_id = "+branch+" and s_mfo.act= 'A') order by data", alias);
		} else {
			list = Utils.getRefData("select s_mfo.bank_id data, s_mfo.bank_id||' - '||s_mfo.bank_name label from s_mfo where bank_id='"+branch+"'", alias);
		}
		return list;
	}
	
	private static boolean isHeader(String branch,String alias){
		boolean isHeader = false;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select s_mfo.header_id from s_mfo where bank_id=?");
			ps.setString(1, branch);
			rs = ps.executeQuery();
			if(rs.next()){
				String mfo = rs.getString(1);
				if(mfo.equals(branch)) isHeader = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return isHeader;
	}
	
	private static List<RefData> getDb_link(String branch, String alias){
		String temp = "";
		if(!branch.equals("0")){
			temp = " where branch = '"+branch+"' ";
		}
		return Utils.getRefData("select branch,user_name from ss_dblink_branch "+temp+" order by branch", alias);
	}
	
	public static List<BproductReport> getReport(String mfo,String alias){
		List<BproductReport> list = new LinkedList<BproductReport>();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<RefData> refdata = getDb_link(mfo, alias);
		try {
			for (int i = 0; i < refdata.size(); i++) {
				c = ConnectionPool.getConnection(refdata.get(i).getLabel());
				ps = c.prepareStatement("select ld_forms.branch,ld_forms.id,ld_forms.client,ld_char.amount,account.s_in,ld_account.acc_type_id from ld_forms,ld_char,ld_account,account where ld_forms.type_id=10 and ld_account.id=ld_forms.id and ld_account.acc_type_id in (2,3,9) and ld_forms.state=2 and account.id=ld_account.account and ld_char.id=ld_forms.id order by ld_forms.client,ld_account.acc_type_id");
				rs = ps.executeQuery();
				while (rs.next()){
					list.add(new BproductReport(
							rs.getString(1),
								rs.getString(2), 
									rs.getString(3),
										rs.getString(4)==null?"0":rs.getString(4),
												rs.getString(5)==null?"0":rs.getString(5),
														rs.getString(6)));
				}
				ConnectionPool.close(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return list;
	}
	
	protected static List<BproductScoring> getParams(int group_id,int bpr_type,String alias) throws SQLException{
		List<BproductScoring> list = new LinkedList<BproductScoring>();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select * from create_grid_template where group_id=? and bpr_type=? order by group_id,id");
			ps.setInt(1, group_id);
			ps.setInt(2, bpr_type);
			rs = ps.executeQuery();
			while(rs.next()){
				list.add(new BproductScoring(
						rs.getInt("group_id"), 
							rs.getInt("id"), 
								rs.getString("name"),
									rs.getString("name_ru"),
										rs.getString("name_en"),
											rs.getString("name_uz"),
												rs.getString("selects"),
													rs.getString("type"),
														rs.getString("def_value"),
															rs.getString("ord"),
																rs.getString("mandatory"),
																	rs.getString("param_type")));
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return list;
	}
	
	protected static String[] getMouths(String date,String card,String alias){
		String[] mounths = new String[2];
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			System.out.println(card.substring(0, 1)+"  ll "+card.substring(1, 1));
			if(card.substring(0, 1).equals("8")) {
			
	 		ps = c.prepareStatement("select MONTHS_BETWEEN(to_date(c.expiration_date,'mm.yy'),to_date(?,'dd.mm.yyyy')) as result,c.expiration_date card_date from card c where c.card_number=?");
			}else {
				   ps = c.prepareStatement("select MONTHS_BETWEEN(to_date(h.expiry1,'dd.mm.yy'),to_date(?,'dd.mm.yyyy')) as result,h.expiry1 card_date from humo_cards h where h.real_card=?");
				} 
				
					
			ps.setString(1, date);
			ISLogger.getLogger().error("DATE = "+date);
			ps.setString(2, card);
			ISLogger.getLogger().error("CARD = "+card);
			
			rs = ps.executeQuery();
			
			
			
			if(rs.next()){
				ISLogger.getLogger().error("ПОЛУЧАЕМ РЕСУЛЬТ СЕТ");
				mounths[0] = rs.getString(1);
				ISLogger.getLogger().error("result = "+rs.getString(1));
				mounths[1] = rs.getString(2);
				ISLogger.getLogger().error("date = "+rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return mounths;
	}
	
	protected static List<RefData> getList(String sql,String customer,String branch,String alias) throws Exception{
		List<RefData> list = new LinkedList<RefData>();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		c = ConnectionPool.getConnection(alias);
		ps = c.prepareStatement(sql);
		ps.setString(1, customer);
		ps.setString(2, branch);
		rs = ps.executeQuery();
		while(rs.next()){
			list.add(new RefData(
					rs.getString(1),
						rs.getString(2)));
		}
		Utils.close(rs);
		Utils.close(ps);
		ConnectionPool.close(c);
		return list;
	}
	
	protected static BigDecimal getScoreResult(String[] array,String sql,String alias,Res res){
		BigDecimal result = null;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
//		String acc[] = null;
		try {
//			if(acc_mask!=null&&!acc_mask.equals("")&&!acc_mask.equals("%")){
//				acc = acc_mask.split(",");
//				acc_mask = "";
//				for (int i = 0; i < acc.length; i++) {
//					acc_mask += "'"+acc[i]+"'";
//					if(i!=acc.length-1){
//						acc_mask += ",";
//					}
//				}
//				sql = sql.replace("<acc>", acc_mask);
//			}
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement(sql);
			System.out.println("sql = "+sql);
			for (int i = 0; i < array.length; i++) {
				if(array==null||array[i]==null||array[i].equals("")){
					continue;
				} else {
					System.out.println((i+1)+" index "+array[i]+" value");
					ps.setObject(i+1, array[i]);
				}
			}
			rs = ps.executeQuery();
			if(rs.next()){
				result = rs.getBigDecimal(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return result==null?new BigDecimal("0"):result;
	}
	
	protected static int getReadSumm(String bpr_id,String alias){
		int result = 0;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select bpr_ld_forms.state from bpr_ld_forms where bpr_ld_forms.bpr_id=?");
			ps.setString(1, bpr_id);
			rs = ps.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return result;
	}
	
	protected static String getAccMask(String name,String bpr_id,String alias){
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String result = "";
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select bpr_create_scoring_grd.def_value from bpr_create_scoring_grd where upper(bpr_create_scoring_grd.name)=upper('"+name+"') and bpr_create_scoring_grd.bpr_id=?");
			ps.setString(1, bpr_id);
			rs = ps.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
				if(result!=null&&!result.equals("")){
					if(result.contains("%")){
						result = result.replace("%", "");
					}
					result = " and account.id like('"+result+"%')";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return result;
	}
	
	protected static String getAcc_mask(String bpr_id,String alias){
		String acc_mask = "";
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select def_value from bpr_create_scoring_grd where bpr_id=? and ord='7'");
			ps.setString(1, bpr_id);
			rs = ps.executeQuery();
			if(rs.next()){
				acc_mask = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return acc_mask;
	}
	
	private static String getSelectParam(Integer id,String alias){
		String param = "";
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select value from create_grd_template_nastr where id=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				param = rs.getString(1);
			}
//			System.out.println("masks = "+acc_masks);
//			if(rs.next()){
//				param = rs.getString(1);
//				if(id==1){
//					param = param.replace("<acc_bal>", "'"+acc_bal+"'");
//					param = param.replace("<id_order>", "'"+id_order+"'");
//				} else if(id==2){
//					String [] acc = acc_masks.split(",");
//					acc_masks = "";
//					for (int i = 0; i < acc.length; i++) {
//						acc_masks += "'"+acc[i]+"'";
//						if(i!=acc.length-1){
//							acc_masks += ",";
//						}
//					}
//					param = param.replace("<acc>", acc_masks);
//				}
//			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return param;
	}
	
	protected static String getSubSelectParam(List<String> withOrder,List<String> withOutOrder,String alias){
		String subSelect = "";
		String allWithOrder = "";
		for (int i = 0; i < withOrder.size(); i++) {
			allWithOrder += withOrder.get(i);
			if(i<withOrder.size()-1){
				allWithOrder += ",";
			}
		}
		String allWithOutOrder = "";
		for (int i = 0; i < withOutOrder.size(); i++) {
			allWithOutOrder += withOutOrder.get(i);
			if(i<withOutOrder.size()-1){
				allWithOutOrder += ",";
			}
		}
		if(!withOrder.isEmpty()&&!withOutOrder.isEmpty()){
			subSelect = getSelectParam(1, alias)+" or "+getSelectParam(2, alias);
		} else if(!withOrder.isEmpty()){
			subSelect += getSelectParam(1, alias)+" ";
		} else if(!withOutOrder.isEmpty()){
			subSelect += getSelectParam(2, alias)+" ";
		}
		subSelect = subSelect.replace("<withorder>", allWithOrder);
		subSelect = subSelect.replace("<withoutorder>", allWithOutOrder);
		return " and ("+subSelect+")";
	}
	
	protected static List<Scoring> getScoring(String bpr_id,String alias){
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Scoring> list = new LinkedList<Scoring>();
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select * from bpr_create_scoring_grd where bpr_create_scoring_grd.bpr_id=?");
			ps.setString(1, bpr_id);
			rs = ps.executeQuery();
			while(rs.next()){
				list.add(new Scoring(
							rs.getInt("group_id"),
								rs.getInt("id"),
									rs.getString("name"),
										rs.getString("name_ru"),
											rs.getString("name_en"),
												rs.getString("name_uz"),
													rs.getString("selects"),
														rs.getString("type"),
															rs.getString("def_value"),
																rs.getString("ord"),
																	rs.getString("mandatory"),
																		rs.getString("param_type"),
																			rs.getString("bpr_id")));
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return list;
	}
	
	protected static List<RefData> getScoringInfo(String acc,String date,String month,String acc_cl,String alias){
		String sql = "select to_char(v_date,'yyyy.mm') mm, sum(summa)/100 from general_arh where acc_co = '"+acc+"' and state = 3 and v_date between ADD_MONTHS('"+date+"', -"+month+") and '"+date+"'";
		List<String> paramsWithOrder = new ArrayList<String>();
		List<String> paramsWithOutOrder = new ArrayList<String>();
		String subString = "";
		if(acc_cl!=null){
//			sql += " and acc_cl like ('"+acc_cl+"')";
			String accs[] = acc_cl.split(",");
			for (int i = 0; i < accs.length; i++) {
				if(accs[i].contains("-")){
					String withOrder = "'"+accs[i].split("-")[0]+accs[i].split("-")[1]+"'";
					paramsWithOrder.add(withOrder);
				} else {
					String withOutOrder = "'"+accs[i]+"'";
					paramsWithOutOrder.add(withOutOrder);
				}
			}
			subString = getSubSelectParam(paramsWithOrder, paramsWithOutOrder, alias);
			sql += subString;
		}
		sql += " group by to_char(v_date,'yyyy.mm') order by mm";
		System.out.println("sql info = "+sql);
		return Utils.getRefData(sql, alias);
	}
	
	protected static String getInfoClient(String neededInfo,String client,String alias){
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String info = "";
		String cl_type = "";
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select client.code_subject from client where id_client=?");
			ps.setString(1, client);
			rs = ps.executeQuery();
			if(rs.next()){
				cl_type = rs.getString(1);
			}
			if(cl_type.equals("P")&&neededInfo.equals("PHONE")){
				neededInfo = "phone_home||' - '||phone_mobile";
			} else if (cl_type.equals("P")&&neededInfo.equals("HOMEPHONE")){
				neededInfo = "phone_home";
			} else if (cl_type.equals("P")&&neededInfo.equals("MOBPHONE")){
				neededInfo = "phone_mobile";
			} else if (cl_type.equals("J")&&neededInfo.equals("MOBPHONE")){
				return "";
			} else if (cl_type.equals("J")&&neededInfo.equals("HOMEPHONE")){
				neededInfo = "PHONE";
			} else if (cl_type.equals("J")&&neededInfo.equals("PASSPORT")){
				neededInfo = "director_passp_serial||''||director_passp_number";
			} else if (cl_type.equals("P")&&neededInfo.equals("PASSPORT")){
				neededInfo = "passport_serial||''||passport_number";
			} else if (cl_type.equals("J")&&neededInfo.equals("PSINFO")){
				neededInfo = "director_passp_date_reg||''||director_passp_place_reg";
			} else if (cl_type.equals("P")&&neededInfo.equals("PSINFO")){
				neededInfo = "passport_date_registration||''||passport_place_registration";
			}
			ps = c.prepareStatement("select "+neededInfo+" from client_"+cl_type+" where id=?");
			ps.setString(1, client);
			rs = ps.executeQuery();
			if(rs.next()){
				info = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return info;
	}
	
	protected static String getBankName(String un, String pw, String branch,String alias){
		return getInfo(un, pw, branch, alias, "select bank_name from s_mfo where bank_id=?");
	}
	
	protected static String getCardAcc(String un, String pw, String card,String alias){
		String new_card = getInfo(un, pw, card,alias,"select card.def_atm_account from card where card_number=?");
		if(new_card!=null&&!new_card.equals("")){
			new_card = new_card.substring(5);
		}
		return new_card;
	}
	
	protected static String getRegionFromBranch(String un, String pw, String branch,String alias){
		return getInfo(un, pw, branch,"","select region_id from s_mfo where bank_id=?");
	}
	protected static String getRegionFromBranch_2(String un, String pw, String branch,String alias){
		return getInfo(un, pw, branch,branch,"select region_id from s_mfo where bank_id=?");
	}
	
	private static String getInfo(String un, String pw, String branch,String alias,String sql){
		Connection c = null;
		PreparedStatement ps = null;
		CallableStatement csi1 = null;
		ResultSet rs = null;
		String name = "";
		try {
			c = ConnectionPool.getConnection(un, pw,alias);
			csi1 = c.prepareCall("{ call info.init() }");
			csi1.execute();
			//c = ConnectionPool.getConnection();
			ps = c.prepareStatement(sql);
			ps.setString(1, branch);
			rs = ps.executeQuery();
			if(rs.next()){
				name = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return name;
	}
	
	protected static String getWsumm(String summa,String curr,String alias){
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String wsumm = "";
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareCall("select multimoneytostr(?,'10',?) from dual");
			ps.setString(1, summa);
			ps.setString(2, curr);
			rs = ps.executeQuery();
			if(rs.next()){
				wsumm = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return wsumm;
	}
	
	private static boolean useCard(String alias,String card) throws Exception{
		boolean useCard = false;
		Connection c = null;
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		ISLogger.getLogger().error("this card = "+card+" alias = "+alias);
		try {
			System.out.println("alias = "+alias);
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select bproduct_desc.detail_id from bproduct_desc where"
					+ " bproduct_desc.detail_group=117 and bproduct_desc.detail_id=? and bproduct_desc.state in (2,1,4,98,99)");
			//			ps = c.prepareStatement("select bproduct_desc.detail_id from bproduct_desc where bproduct_desc.detail_group=117 and bproduct_desc.detail_id=? and bproduct_desc.state in (2,1,4,98,99)"
//					+ " union all\r\n" + 
//					"               select t.real_card from humo_cards t where t.real_card=?");
			ps.setString(1, card);
		//	ps.setString(2, card);
			rs = ps.executeQuery();
			if(rs.next()){
				useCard = true;
			}
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return useCard;
	}
	
	protected static Date getV_date(String acc,String alias){
		Date v_date = null;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select * from (select v_date,summa from general_arh where acc_co=? and state=3 order by v_date desc) where rownum<=1");
			ps.setString(1, acc);
			rs = ps.executeQuery();
			if(rs.next()) v_date = rs.getDate("v_date");
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return v_date;
	}
	
	protected static Integer getMounth_value(String bpr_id,String alias){
		Integer value = null;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select def_value from bpr_create_scoring_grd where bpr_id=? and id=5");
			ps.setString(1, bpr_id);
			rs = ps.executeQuery();
			if(rs.next()) value = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return value;
	}
	
	protected static String getParam(Long bpr_id,Long oid){
		String param = null;
		Connection  c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select a.param from bpr_template_fields f,ss_bpr_scoring_anket a where tid=? and f.conformity_id!=0 and f.model='scoring' and a.attribute=1 and a.id=f.conformity_id and f.oid=?");
			ps.setLong(1, bpr_id);
			ps.setLong(2, oid);
			rs = ps.executeQuery();
			if(rs.next()) param = rs.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return param;
	}
	
	protected static ScoringType getScroringType(Integer bpr_id){
		ScoringType st = null;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select s.* from bpr_scoring_type b, ss_scoring_type s where b.bpr_id=? and b.id_scoring=s.id");
			ps.setInt(1, bpr_id);
			rs = ps.executeQuery();
			if(rs.next()){
				st = new ScoringType(
						rs.getLong("id"),
						rs.getString("name"),
						rs.getString("path"),
						rs.getString("class_name"),
						rs.getString("method")
				);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return st;
	}
	
	protected static List<BallScoringModel> getBallModel(String bpr_id){
		List<BallScoringModel> list = new ArrayList<BallScoringModel>();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select * from bpr_question_scoring where bpr_id = ? and a_id is null order by q_order");
			ps.setString(1, bpr_id);
			rs = ps.executeQuery();
			while(rs.next()){
				List<AnswerModel> answers = getAnswers(rs.getLong("q_id"), c);
				list.add(new BallScoringModel(
						rs.getInt("bpr_id"),
						rs.getLong("q_id"),
						rs.getString("q_name"),
						rs.getLong("q_order"),
						rs.getLong("a_id"),
						answers
				));
				getSubQuestions(rs.getLong("q_id"),list,c);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return list;
	}
	
	private static void getSubQuestions(Long q_id,List<BallScoringModel> list,Connection c){
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = c.prepareStatement("select * from bpr_question_scoring where a_id in (select a_id from bpr_answer_scoring  where q_id = ? and type_answer = 2)");
			ps.setLong(1, q_id);
			rs = ps.executeQuery();
			while(rs.next()){
				List<AnswerModel> answers = getAnswers(rs.getLong("q_id"), c);
				BallScoringModel sub_ball = new BallScoringModel(
						rs.getInt("bpr_id"),
						rs.getLong("q_id"),
						rs.getString("q_name"),
						rs.getLong("q_order"),
						rs.getLong("a_id"),
						answers
				);
				sub_ball.setVisible(false);
				list.add(sub_ball);
				getSubQuestions(rs.getLong("q_id"), list, c);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
		}
	}
	
	private static List<AnswerModel> getAnswers(Long q_id,Connection c){
		List<AnswerModel> list = new ArrayList<AnswerModel>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = c.prepareStatement("select * from bpr_answer_scoring where q_id = ? order by a_id");
			ps.setLong(1, q_id);
			rs = ps.executeQuery();
			while(rs.next()){
				List<BallScoringModel> questions = getQuestions(rs.getLong("a_id"), c);
				list.add(new AnswerModel(
						rs.getLong("q_id"),
						rs.getLong("a_id"),
						rs.getString("a_name"),
						rs.getLong("a_ball"),
						rs.getInt("type_answer"),
						questions
				));
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
		}
		return list;
	}
	
	private static List<BallScoringModel> getQuestions(Long map_id,Connection c){
		List<BallScoringModel> list = new ArrayList<BallScoringModel>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = c.prepareStatement("select * from bpr_question_scoring where a_id = ? order by q_order");
			ps.setLong(1, map_id);
			rs = ps.executeQuery();
			while(rs.next()){
				List<AnswerModel> answers = getAnswers(rs.getLong("q_id"), c);
				list.add(new BallScoringModel(
						rs.getInt("bpr_id"),
						rs.getLong("q_id"),
						rs.getString("q_name"),
						rs.getLong("q_order"),
						rs.getLong("a_id"),
						answers
				));
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
		}
		return list;
	}
	
	protected static String getScoringResult(String bpr_id,Long interval){
		String result = null;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select max(interval_before) from bpr_ball_scoring_result where bpr_id = ?");
			ps.setString(1, bpr_id);
			rs = ps.executeQuery();
			if(rs.next()){
				if(interval>rs.getLong(1)){
					result = Utils.getData("select name_result||'#@'||is_close_credit from bpr_ball_scoring_result where bpr_id = "+bpr_id+" and interval_before = "+rs.getLong(1), "");
					System.out.println("RESULT 1 = "+result);
					return result;
				} else {
					result = Utils.getData("select name_result||'#@'||is_close_credit from bpr_ball_scoring_result where bpr_id = "+bpr_id+" and  "+interval+" >= interval_from and "+interval+" <= interval_before", "");
					System.out.println("RESULT 2 = "+result);
					return result;
				}
			} else result = "Ошибка расчета балов скоринговой анкеты шаблона № "+bpr_id;
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		System.out.println("RESULT = "+result);
		return result;
	}
	
	protected static List<RefData> getAccInfo(String client,String branch,String alias){
		return Utils.getRefData("select account.id,account.s_in from account where account.client='"+client+"' and account.branch='"+branch+"' and account.s_in>0", alias);
	}
	
	protected static String getMaxLenght(Long id){
		return Utils.getData("select field_length from ss_bpr_scoring_anket where id = "+id,"");
	}
	
	protected static String getForm_id(Long id,String branch, Connection c){
		return Utils.getData("select to_number(substr(b.detail_id,6,9)) from bproduct_desc b where b.detail_group=10 and b.id="+id+" and b.branch='"+branch+"'", "", c);
	}
	
	protected static String getForm_id(Long id,String branch){
		return Utils.getData("select to_number(substr(b.detail_id,6,9)) from bproduct_desc b where b.detail_group=10 and b.id="+id+" and b.branch='"+branch+"'", "");
	}
	
	protected static List<Ld_sv_gate> getLdSvGate(String form_id, String branch, String alias){
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Ld_sv_gate> list = new ArrayList<Ld_sv_gate>();
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select * from ld_sv_gate where id = ? and branch = ?");
			System.out.println("form_id = "+form_id);
			System.out.println("branch = "+branch);
			ps.setString(1, form_id);
			ps.setString(2, branch);
			rs = ps.executeQuery();
			while(rs.next()){
				System.out.println("WIW");
				list.add(new Ld_sv_gate(
						rs.getString("branch"),
						rs.getLong("id"),
						rs.getString("card_number"),
						rs.getString("expiry_date"),
						rs.getInt("sign_client"),
						rs.getInt("sign_card")
				));
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return list;
	}
	
	public static List<RefData> getSMfo(){
		return Utils.getRefData("select bank_id,bank_id||' '||bank_name from s_mfo where act='A' order by bank_id", "");
	}
	
	public static List<RefData> getType_doc(String alias){
		return Utils.getRefData("select kod_doc,naim_doc from v_typedoc where act='A' order by naim_doc", alias);
	}
	
	private static String getNiki_idFromBpr(Long id, Connection c){
		return Utils.getData("select ni_req_id from bproduct where id = "+id, "", c);
	}
	
	private static String getNiki_idFromLdChar(String form_id, String branch, Connection c){
		return Utils.getData("select niki_id from ld_char where id = "+form_id+" and branch = '"+branch+"'", "");
	}
	
	protected static String getNiki_id(String form_id, Long id, String branch, Connection c, String alias) throws Exception{
		if(c == null) c = ConnectionPool.getConnection(alias);
		return form_id.equals("") ? getNiki_idFromBpr(id,c) : getNiki_idFromLdChar(form_id, branch,c);
	}
	
//	private static String getNikiAsokiSenderUrl(){
//		return Utils.getData("select value from bf_sets where id = 'NikiAsokiSender'", "");
//	}
	
	protected static List<RefData> getTypeCardOwner(){
		return Utils.getRefData("select id, name from ss_ld_type_card_owner order by name", "");
	}
	
	private static String getBank_type(String branch){
		return Utils.getData("select bank_type from s_mfo where bank_id = '"+branch+"'", "");
	}
	//для CRM
	protected static String getInfoClientbyBranch(String neededInfo,String client,String alias,String branch){
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String info = "";
		String cl_type = "";
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select client.code_subject from client where id_client=?");
			ps.setString(1, client);
			rs = ps.executeQuery();
			if(rs.next()){
				cl_type = rs.getString(1);
			}
			if(cl_type.equals("P")&&neededInfo.equals("PHONE")){
				neededInfo = "phone_home||' - '||phone_mobile";
			} else if (cl_type.equals("P")&&neededInfo.equals("HOMEPHONE")){
				neededInfo = "phone_home";
			} else if (cl_type.equals("P")&&neededInfo.equals("MOBPHONE")){
				neededInfo = "phone_mobile";
			} else if (cl_type.equals("J")&&neededInfo.equals("MOBPHONE")){
				return "";
			} else if (cl_type.equals("J")&&neededInfo.equals("HOMEPHONE")){
				neededInfo = "PHONE";
			} else if (cl_type.equals("J")&&neededInfo.equals("PASSPORT")){
				neededInfo = "director_passp_serial||''||director_passp_number";
			} else if (cl_type.equals("P")&&neededInfo.equals("PASSPORT")){
				neededInfo = "passport_serial||' '||passport_number";
			} else if (cl_type.equals("J")&&neededInfo.equals("PSINFO")){
				neededInfo = "director_passp_date_reg||' '||director_passp_place_reg";
			} else if (cl_type.equals("P")&&neededInfo.equals("PSINFO")){
				neededInfo = "passport_place_registration||'  '||passport_date_registration";
				
			}
			ps = c.prepareStatement("select "+neededInfo+" from client_"+cl_type+" where id=? and branch=?");
			ps.setString(1, client);
			ps.setString(2, branch);
			rs = ps.executeQuery();
			if(rs.next()){
				info = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return info;
	}
	
	  protected static String getCreditTimeByMonth(String branch,String form_id,String alias){
		  
		    Connection c = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			String result=" ";
            try{
            	c = ConnectionPool.getConnection(alias);
            	ps=c.prepareStatement("select ROUND(MONTHS_BETWEEN(to_date(l.date_close,'dd.mm.yyyy'),to_date(l.ld_date,'dd.mm.yyyy'))) months_cred from ld_char l "+
               " where l.date_close is not null and l.ld_date is not null and l.id=?");
            	ps.setString(1,form_id);
            	rs=ps.executeQuery();
            	while(rs.next()){
	     		result=rs.getString("months_cred");         		
            	}
            }		  
		  catch(Exception e) {
				e.printStackTrace();
			  
		  }
            finally {
    			Utils.close(rs);
    			Utils.close(ps);
    			ConnectionPool.close(c);
    		}
            return result;
	  }
	  
	  protected static String getCreditTimeByMonthNew(String branch,String claim_id,String alias){
		  
		    Connection c = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			String result=" ";
          try{
          	c = ConnectionPool.getConnection(alias);
          	ps=c.prepareStatement("select ROUND(MONTHS_BETWEEN(to_date(l.end_date,'dd.mm.yyyy'),to_date(l.v_date,'dd.mm.yyyy'))) months_cred from ni_req l "+
             " where l.v_date is not null and l.end_date is not null and l.id=?");
          	ps.setString(1,claim_id);
          	rs=ps.executeQuery();
          	while(rs.next()){
	     		result=rs.getString("months_cred");         		
          	}
          }		  
		  catch(Exception e) {
				e.printStackTrace();
				ISLogger.getLogger().error("getCreditTimeByMonthNew :"+CheckNull.getPstr(e)); 
		  }
          finally {
  			Utils.close(rs);
  			Utils.close(ps);
  			ConnectionPool.close(c);
  		}
          return result;
	  }
	  
	  protected static String getInfoCreditbyBranch(String neededInfo,String form_id,String alias,String branch){
			Connection c = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			String info ="";
								
				try {
				c = ConnectionPool.getConnection(alias);
				
				if(neededInfo.equals("LD_DATE")){
					neededInfo = "ld_date";
				} else if (neededInfo.equals("DATE_CLOSE")){
					neededInfo = "date_close";
					}
/*				} else if (cl_type.equals("P")&&neededInfo.equals("MOBPHONE")){
					neededInfo = "phone_mobile";
				} else if (cl_type.equals("J")&&neededInfo.equals("MOBPHONE")){
					return "";
				} else if (cl_type.equals("J")&&neededInfo.equals("HOMEPHONE")){
					neededInfo = "PHONE";
				} else if (cl_type.equals("J")&&neededInfo.equals("PASSPORT")){
					neededInfo = "director_passp_serial||''||director_passp_number";
				} else if (cl_type.equals("P")&&neededInfo.equals("PASSPORT")){
					neededInfo = "passport_serial||''||passport_number";
				} else if (cl_type.equals("J")&&neededInfo.equals("PSINFO")){
					neededInfo = "director_passp_date_reg||' '||director_passp_place_reg";
				} else if (cl_type.equals("P")&&neededInfo.equals("PSINFO")){
					neededInfo = "passport_date_registration||''||passport_place_registration";
				}
*/				ps = c.prepareStatement("select to_char("+neededInfo+",'dd.mm.yyyy') from ld_char l where l.id=? and l.branch=?");
				ps.setString(1, form_id);
				ps.setString(2, branch);
				rs = ps.executeQuery();
				if(rs.next()){
				  info=rs.getString(1).substring(0,10);
				 		                    	
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				Utils.close(rs);
				Utils.close(ps);
				ConnectionPool.close(c);
			}
			return info;
		}
	  
	  protected static int getLdrateByClient(String form_id,String alias,String branch){
		String result=" ";  
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps=c.prepareStatement("select m.rate from ld_rate m where m.id=? and m.branch=? and rownum<=1");
			ps.setString(1, form_id);
			ps.setString(2, branch);
			rs=ps.executeQuery();
			if(rs.next()){
				
				result=rs.getString("rate");
			
			}
		  
		}
		catch (Exception e){
			e.printStackTrace();
			}
		finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);	
			}
		  return Integer.valueOf(result);
	  }
	  
	  protected static double getLdrateByClientNew(String apr_id,String alias){
			String result=" ";  
			Connection c = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				c = ConnectionPool.getConnection(alias);
				ps=c.prepareStatement("select b.rate from bpr_ld_rate b where b.exp_id=1 and b.bpr_id=?");
				ps.setString(1, apr_id);
				rs=ps.executeQuery();
				if(rs.next()){
					
					result=rs.getString("rate");
				
				}
			  
			}
			catch (Exception e){
				e.printStackTrace();
				}
			finally {
				Utils.close(rs);
				Utils.close(ps);
				ConnectionPool.close(c);	
				}
			 ISLogger.getLogger().info("loan_ld_rate : "+result);
			  return Double.valueOf(result.replace(",", "."));
		  }
	  
	  
	  protected static String numberToWord(int number) {
	        // variable to hold string representation of number 
	        String words = "";
	        String unitsArray[] = { "Ноль", "Бир", "Икки", "Уч", "Турт", "Беш", "Олти", 
	                      "Етти", "Саккиз", "Туккиз", "Ун", "Ун бир", "Ун икки",
	                      "Ун уч", "Ун турт", "Ун беш", "Ун олти", "Ун етти", 
	                      "Ун саккиз", "Ун туккиз" };
		String tensArray[] = { "Ноль", "Ун", "Йигирма", "Уттиз", "Кирк", "Эллик",
	                      "Олтмиш", "Етмиш", "Саксон", "Туксон" };
	 
		if (number == 0) {
		    return "Ноль";
		}
		// add minus before conversion if the number is less than 0
		if (number < 0) { // convert the number to a string 
	           String numberStr = "" + number; 
	           // remove minus before the number 
	           numberStr = numberStr.substring(1); 
	           // add minus before the number and convert the rest of number 
	           return "minus " + numberToWord(Integer.parseInt(numberStr));
	        } 
	        // check if number is divisible by 1 million
	        if ((number / 1000000) > 0) {
		   words += numberToWord(number / 1000000) + " Миллион ";
		   number %= 1000000;
		}
		// check if number is divisible by 1 thousand
		if ((number / 1000) > 0) {
		    words += numberToWord(number / 1000) + " Минг ";
		    number %= 1000;
		}
		// check if number is divisible by 1 hundred
		if ((number / 100) > 0) {
		     words += numberToWord(number / 100) + " Юз ";
		     number %= 100;
		}
	 
		if (number > 0) {
		     // check if number is within teens
		     if (number < 20) { 
	                // fetch the appropriate value from unit array 
	                words += unitsArray[number];
	             } else { 
	                // fetch the appropriate value from tens array 
	                words += tensArray[number / 10]; 
	                if ((number % 10) > 0) {
			    words += "-" + unitsArray[number % 10];
	                }  
		     }
	          }
	 
		  return words;
	   }
	  
	protected static String getInfoFilialbyBranch(String neededInfo, String alias, String branch) {

		List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String info = "";
		StringBuilder par = new StringBuilder("000");
		String id = par.append(branch).toString();

		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select * from client_j h where h.branch=? and h.id=?");

			ps.setString(1, branch);
			ps.setString(2, id);

			rs = ps.executeQuery();
			if (rs.next()) {

				ResultSetMetaData md = rs.getMetaData();
				int columns = md.getColumnCount();

				Map<String, Object> row = new HashMap<String, Object>(columns);
				for (int i = 1; i <= columns; ++i) {
					row.put(md.getColumnName(i), rs.getObject(i));
				}
				res.add(row);
			}

			for (int j = 0; j < res.size(); j++) {
				
				if (res.get(j).containsKey("DIRECTOR_NAME") && neededInfo.equals("DIRECTOR_NAME")) {
					info = (String) res.get(j).get("DIRECTOR_NAME");
					//System.out.println("res-----eeee1:" + info);
				} else if (res.get(j).containsKey("CHIEF_ACCOUNTER_NAME")&& neededInfo.equals("CHIEF_ACCOUNTER_NAME")) {
					info = (String) res.get(j).get("CHIEF_ACCOUNTER_NAME");
					//System.out.println("res-----eeee2:" + info);
				}
				else if(res.get(j).containsKey("ACCOUNT")&& neededInfo.equals("ACCOUNT")){
					info = (String) res.get(j).get("ACCOUNT");
					//System.out.println("res-----eeee3:" + info);
						
				}
				else if(res.get(j).containsKey("POST_ADDRESS")&& neededInfo.equals("POST_ADDRESS")){
					info = (String) res.get(j).get("POST_ADDRESS");
					//System.out.println("res-----eeee4:" + info);
									
				}
				else if(res.get(j).containsKey("NUMBER_TAX_REGISTRATION")&& neededInfo.equals("NUMBER_TAX_REGISTRATION")){
					info = (String) res.get(j).get("NUMBER_TAX_REGISTRATION");
					//System.out.println("res-----eeee5:" + info);
						
				}
				else if(res.get(j).containsKey("PHONE")&& neededInfo.equals("PHONE")){
					info = (String) res.get(j).get("PHONE");
					//System.out.println("res-----eeee6:" + info);
								
				}
			}
					} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return info;
	}
	
	public static String getNameDoc(int bpr_id,String alias,String type_doc){
		  String res=" ";  
		  Connection c = null;
		  PreparedStatement ps = null;
		  ResultSet rs = null;
		  try{
		    c = ConnectionPool.getConnection(alias);
		    ps = c.prepareStatement("select b.doc_name from bpr_templates_doc b where b.bpr_id=? and b.type_doc=?");

		    ps.setInt(1, bpr_id);
		    ps.setString(2, type_doc);
		    rs = ps.executeQuery();
		    if(rs.next()){
		      res=rs.getString("DOC_NAME");
		          
		    }
		        
		  }
		   catch(Exception e){
		  e.printStackTrace();
		  ISLogger.getLogger().error(e.getMessage());
		    }
		  finally{
		    Utils.close(rs);
		    Utils.close(ps);
		    ConnectionPool.close(c);    
		  }
		    
		    return res;
	}
	
	

	
	
	public static String execute_transaction(String BRANCH, String ID_CLIENT,String CARD_NUMBER,String Summa_scoring, String type_proc) throws SQLException
	{
		
		Connection c = null;
		PreparedStatement ps = null;
			
		try {
			c = ConnectionPool.getConnection();
			//if(this.schemas == null) fill_shemas();
			
			cs_clearparam = c.prepareCall("{ call param.ClearAll() }");		
			cs_setparam = c.prepareCall("{ call param.SetParam(?, ?) }");
			cs_getparam_scoring = c.prepareCall("{? = call Param.getparam('INFO') }");
			cs_getparam_scoring.registerOutParameter(1, java.sql.Types.VARCHAR);
			//cs_scoring = c.prepareCall("{ call PROC_LD_SCORING.getScoringSumma96 () }");
			 System.out.println("cs_scoring: "+"{" +type_proc.trim()+"() }");                                    
			cs_scoring = c.prepareCall("{ call " +type_proc.trim()+"() }");
			st = c.createStatement();
		
			//doaction_type(type_proc);
			st.executeUpdate("ALTER SESSION SET NLS_NUMERIC_CHARACTERS = '. '");
			
			clearparam();
			cs_setparam.setString(1, "BRANCH");  cs_setparam.setString(2,BRANCH); cs_setparam.execute();
			cs_setparam.setString(1, "ID_CLIENT");  cs_setparam.setString(2,ID_CLIENT); cs_setparam.execute();
			cs_setparam.setString(1, "CARD_NUMBER");  cs_setparam.setString(2,CARD_NUMBER); cs_setparam.execute();
			cs_setparam.setString(1, "SUMMA");  cs_setparam.setString(2,Summa_scoring); cs_setparam.execute();
			
			cs_scoring.execute();
			cs_getparam_scoring.
			execute();
//			util_dao.setparam("BRANCH",BRANCH);
//			util_dao.setparam("ID_CLIENT", ID_CLIENT);
//			util_dao.setparam("CARD_NUMBER", CARD_NUMBER);
//			util_dao.setparam("SUMMA", "1000000");
//			
//			util_dao.execute_setparam_batch();
//			util_dao.doscoring();			
			
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
		   
		return cs_getparam_scoring.getString(1); 

	}
	
	protected static String getScroringType_Proc(String bpr_id){
		String scoring_name = null;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select * from bpr_scoring_type b where b.bpr_id=? ");
			ps.setString(1, bpr_id);
			rs = ps.executeQuery();
			if(rs.next()){
				
				scoring_name = rs.getString("scoring_name");
			
				type_proc_do=scoring_name;
				BproductService(c);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return scoring_name;
	}
	
	private static void BproductService(Connection c2) {
		// TODO Auto-generated method stub
		
	}

	public static void doaction_type(String type_proc) {
		type_proc_do=type_proc;
	}
	
	public static void clearparam() throws SQLException
	{
		cs_clearparam.execute();
		cs_setparam.clearBatch();
	}
	
	public static String getClientId(String branch,long loan_id,String alias){
		long client_id = 0;
		 Connection c = null;
		  PreparedStatement ps = null;
		  ResultSet rs = null;
		 
		  try{
		    c = ConnectionPool.getConnection(alias);
		    ps = c.prepareStatement("select b.customer from bproduct b where b.id = ? and b.branch = ? ");

		    ps.setLong(1, loan_id);
		    ps.setString(2, branch);
		    rs = ps.executeQuery();
		  //  System.out.println("come to this line");
		    if(rs.next()){
		    	client_id= rs.getLong("CUSTOMER");
		          
		    }
		        
		  }
		   catch(Exception e){
		  e.printStackTrace();
		  ISLogger.getLogger().error("loan_getCustomer: "+e.getMessage());
		    }
		  finally{
		    Utils.close(rs);
		    Utils.close(ps);
		    ConnectionPool.close(c);    
		  }
		    
		    return String.valueOf(client_id);		
		
		
	}
	
	public static Res_Scoring getScoringByClaimId(String branch, long claim_id,int aprod_id,String alias){
		
		Res_Scoring res = new Res_Scoring();
		Connection c = null;
  		CallableStatement css = null;
  		CallableStatement csin=null;
  		CallableStatement csout=null;
  		CallableStatement paramCall1 = null;
  		CallableStatement paramCall2 = null;
  		
  		try{
  			c = ConnectionPool.getConnection(alias);
 			csin = c.prepareCall("{call info.init()}");
 			csin.executeUpdate();
 			css = c.prepareCall("{ call param.clearparam() }");
 			css.execute(); 			
 						
 			csout = c.prepareCall("{call loan_scoring_bykatm_reports.LdScoringByKatm(?,?,?)}");
 			csout.setString(1, branch);
 			csout.setLong(2, claim_id);
 			csout.setInt(3, aprod_id); 			
 			csout.execute();
 			paramCall1 = c.prepareCall("{? = call Param.GetParam('res') }");
 			paramCall1.registerOutParameter(1, java.sql.Types.DOUBLE);
 			paramCall1.execute();
 			ISLogger.getLogger().error("loan_scoring_bykatm_reports.LdScoringByKatm_result: "+paramCall1.getDouble(1));
 			res.setCode(paramCall1.getDouble(1));
 			paramCall2 = c.prepareCall("{? = call Param.GetParam('restext') }");
 			paramCall2.registerOutParameter(1, java.sql.Types.VARCHAR);
 			paramCall2.execute();
 			ISLogger.getLogger().error("loan_scoring_bykatm_reports.LdScoringByKatm_res_text: "+paramCall2.getString(1));
 			res.setName(paramCall2.getString(1));	
  			
  		}		
		catch(Exception e){
			e.printStackTrace();
 			ISLogger.getLogger().error(CheckNull.getPstr(e));
 			res.setCode(0.00);
 			res.setName(e.getMessage());			
		}
  		finally{
  		    Utils.close(paramCall2);
 		    Utils.close(paramCall1);
 		    Utils.close(csout);
 		    Utils.close(css);
 		    Utils.close(csin);
 		    ConnectionPool.close(c);   			
  		}
  		
  		ISLogger.getLogger().info("res_summa: "+res.getCode()+" , res_name: "+res.getName());
		return res;		
	}
	
	public static double getAvrLoan(double sum_loan,int loan_period,double loan_rate,String alias){
		
		double avrSumLoan = 0.00;
		Connection c = null;
		CallableStatement cs=null;
		
		try{
			c = ConnectionPool.getConnection(alias);
			CallableStatement paramcall = c.prepareCall("{call info.init()}");
	        paramcall.execute();
	        cs = c.prepareCall("{?= call loan_scoring_bykatm_reports.curLoanPay(?,?,?)}");
	        cs.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
	        cs.setDouble(2, sum_loan);
			cs.setInt(3, loan_period);
			cs.setDouble(4, loan_rate);
			cs.execute();
			
			avrSumLoan=cs.getDouble(1);		
			
		}
		catch(Exception e){
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));			
		}
		
		finally{
  		    Utils.close(cs);
 		    ConnectionPool.close(c);   			
  		}
		
		return avrSumLoan;	
		
	}
	
	public static BigDecimal truncateDecimal(double x,int numberofDecimals)
	{
	    if ( x > 0) {
	        return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_FLOOR);
	    } else {
	        return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_CEILING);
	    }
	}
	
}
