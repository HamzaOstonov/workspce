package com.is.sign;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.zkoss.json.JSONArray;
import org.zkoss.json.parser.JSONParser;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Div;
import org.zkoss.zul.Window;
import org.zkoss.zul.Listbox;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.sign.asclient.ASClientService;
import com.is.sign.dvs.DvsSignService;
import com.is.sign.dvs.Signin;
import com.is.sign.log.SignlogService;
import com.is.user.UserService;
import com.is.utils.CheckNull;

public class SignViewCtrl  extends GenericForwardComposer{
	static final long serialVersionUID = 123456789111L;
	
	private Div signmain, noactivex;
    SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
    private int signtype = 1;

    public SignViewCtrl() {
        super('$', false, false);
    }
    
	@Override
	public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        signtype = Integer.parseInt(ConnectionPool.getValue("SIGN_TYPE"));
        SignService.checkActiveX();
	}
	
	public void onActiveX$sign(Event event) throws Exception {
		try {
			Event eventx = Events.getRealOrigin((ForwardEvent)event);
			Object[] data = (Object[])eventx.getData();
			if ((Integer)data[0] == 0) {
				noactivex.setVisible(false);
			} else {
				noactivex.setVisible(true);
			}
		} catch (Exception e) {
			e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
			//alert("При верификации подписи возникли ошибки!");
		}
	}
	
	//CheckKeyCode---------------------------------
	public void onCheckKeyCode$sign(Event event) throws Exception {
		Res res = new Res();
		String page = "";
		String branch = "";
		int user_id = 0;
		String username = "";
		String key_sn = "";
		int isLogin = 0;
		int res_code = 0;
		String err_message = "";
		try {
			Event eventx = Events.getRealOrigin((ForwardEvent)event);
			Object[] data = (Object[])eventx.getData();
			page = (String)data[0];
			branch = (String)data[1];
			user_id = (Integer)data[2];
			username = (String)data[3];
			key_sn = (String)data[4];
			isLogin = (Integer)data[5];
			res_code = (Integer)data[6];
			err_message = (String)data[7];
			
			if (!(Boolean)session.getAttribute("isLoggedIn")) {
				throw new Exception("Попытка доступа незарегестрированного пользователя ("+username+")!");
			}
			if (!username.equalsIgnoreCase((String)session.getAttribute("un"))) {
				throw new Exception("Попытка доступа другого пользователя (владелец сессии: "+(String)session.getAttribute("un")+"; пользователь: "+username+")");
			}
			
			Component comp = execution.getDesktop().getComponentByUuid(page);
			HashMap<String, Object> _params = new HashMap<String, Object>();
			_params.put("page", page);
			_params.put("branch", branch);
			_params.put("user_id", user_id);
			_params.put("username", username);
			_params.put("key_sn", key_sn);
			_params.put("isLogin", isLogin);
			_params.put("res_code", res_code);
			_params.put("err_message", err_message);
			res = new Res(res_code, err_message, _params);
			Events.sendEvent("onCheckKeyCodeLogin", comp, res);
		} catch (Exception e) {
			e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
			alert("При верификации подписи возникли ошибки ("+e.getMessage()+")!");
		}
	}
	
	//sign---------------------------------
	public void onSign$sign(Event event) throws Exception {
		Res res = new Res();
		String page = ""; 
		int key_type = 0; 
		String key_sn = ""; 
		String signData = "";
		String textForSign = "";
		String branch = "";
		int user_id = 0;
		String username = "";
		String schema = ""; 
		int group_id = 0; 
		int deal_id = 0;  
		int action_id = 0;  
		Long object_id = 0L;  
		Long sign_log_id = 0L; 
		int res_code = 0;
		String err_message = "";
		try {
			Event eventx = Events.getRealOrigin((ForwardEvent)event);
			Object[] data = (Object[])eventx.getData();
			key_type = (Integer)data[0]; 
			key_sn = (String)data[1]; 
			signData = (String)data[2]; 
			textForSign = (String)data[3];
			res_code = (Integer)data[5];
			err_message = (String)data[6];
			//addinfo
			JSONArray addinfo = (JSONArray) data[4];
			page = (String)addinfo.get(0);
			branch = (String)addinfo.get(1);
			user_id = (Integer)addinfo.get(2);
			username = (String)addinfo.get(3);
			schema = (String)addinfo.get(4); 
			group_id = (Integer)addinfo.get(5); 
			deal_id = (Integer)addinfo.get(6); 
			action_id = (Integer)addinfo.get(7); 
			object_id = Long.parseLong((String)addinfo.get(8)); 
			sign_log_id = Long.parseLong((String)addinfo.get(9)); 
			
			if (!(Boolean)session.getAttribute("isLoggedIn")) {
				throw new Exception("Попытка доступа незарегестрированного пользователя ("+username+")!");
			}
			if (!username.equalsIgnoreCase((String)session.getAttribute("un"))) {
				throw new Exception("Попытка доступа другого пользователя (владелец сессии: "+(String)session.getAttribute("un")+"; пользователь: "+username+")");
			}
			
			if (res_code == 0) {
				try {
					//DVS sign verification
					if (key_type == 1 && res_code == 0) {
						Signin signin = new Signin(); 
						signin.setId(sign_log_id);
						signin.setQuery_line(textForSign);//signBody
						signin.setCrp_line(signData);
						signin.setCurr_state("0");
						if (CheckNull.isEmpty(key_sn)) {
							throw new Exception("При верификации подписи возникли ошибки! Ключ пользователя не закреплен за данной организацией!");
						}
						signin = DvsSignService.create(signin, key_sn, 1);
						if (signin.getUpd_cnt() > 0) {
							res = SignlogService.updateStateAndErr(sign_log_id,group_id,deal_id,action_id,object_id, 1, err_message);
							if (res.getCode() != 0) {
								throw new Exception("При верификации подписи возникли ошибки! ("+res.getName()+")");
							}
						} else {
							throw new Exception("При верификации подписи возникли ошибки! ("+signin.getErr_message()+")");
						}
					}
					
					//ASClient online verification
					if (key_type == 2 && res_code == 0) {
						res = ASClientService.checkASClientSign(signData, textForSign);
						if (res.getCode() == 0) {
							Res rs = SignlogService.updateStateAndErr(sign_log_id,group_id,deal_id,action_id,object_id, 2, res.getName());
							if (rs.getCode() != 0) {
								throw new Exception(rs.getName());
							}
						} else {
							throw new Exception(res.getName());
						}
					}
				} catch (Exception e) {
					res_code = 1;
					err_message = CheckNull.isEmpty(e.getMessage())?CheckNull.getPstr(e):e.getMessage();
					Res rs = SignlogService.updateToErr(sign_log_id, object_id, 3, err_message);
					if (rs.getCode() != 0) {
						throw new Exception("При верификации подписи возникли ошибки! ("+rs.getName()+")");
					}
				}
			}
			
			
			Component comp = execution.getDesktop().getComponentByUuid(page);
			HashMap<String, Object> _params = new HashMap<String, Object>();
			_params.put("res_code", res.getCode());
			_params.put("res_name", res.getName());
			_params.put("page", page);
			_params.put("branch", branch);
			_params.put("user_id", user_id);
			_params.put("username", username);
			_params.put("schema", schema);
			_params.put("key_type", key_type);
			_params.put("key_sn", key_sn);
			_params.put("signData", signData);
			_params.put("textForSign", textForSign);
			_params.put("group_id", group_id);
			_params.put("deal_id", deal_id);
			_params.put("action_id", action_id);
			_params.put("object_id", object_id);
			_params.put("sign_log_id", sign_log_id);
			_params.put("res_code", res_code+"");
			_params.put("err_message", err_message);
			res = new Res(res_code, err_message, _params);
			Events.sendEvent("onSign", comp, _params);
		} catch (Exception e) {
			e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
			alert("При верификации подписи возникли ошибки ("+e.getMessage()+")!");
		}
	}
	
	//signList---------------------------------
	public void onSignList$sign(Event event) throws Exception {
		Res res = new Res();
		String page = ""; 
		String branch = "";
		int user_id = 0;
		String username = "";
		String schema = ""; 
		Long client_id = 0L; 
		String bank_client_id = ""; 
		int key_type = 0; 
		String key_sn = ""; 
		int group_id = 0; 
		int deal_id = 0;  
		int action_id = 0;  
		int res_code = 0;
		String err_message = "";
		List<SignList> objects = new ArrayList<SignList>();
		try {
			Event eventx = Events.getRealOrigin((ForwardEvent)event);
			Object[] data = (Object[])eventx.getData();
			key_type = (Integer)data[0]; 
			key_sn = (String)data[1]; 
			res_code = (Integer)data[4];
			err_message = (String)data[5];
			//addinfo
			JSONArray addinfo = (JSONArray) data[3];
			page = (String)addinfo.get(0);
			branch = (String)addinfo.get(1);
			user_id = (Integer)addinfo.get(2);
			username = (String)addinfo.get(3);
			schema = (String)addinfo.get(4); 
			group_id = (Integer)addinfo.get(5); 
			deal_id = (Integer)addinfo.get(6); 
			action_id = (Integer)addinfo.get(7); 
			//objects
			JSONArray objs = (JSONArray) data[2];
			for (int i = 0; i < objs.size(); i++) {
				JSONArray obj = (JSONArray) objs.get(i);
				objects.add(new SignList(Long.parseLong((String)obj.get(0)), (String)obj.get(1), (String)obj.get(2), (String)obj.get(3), (Integer)obj.get(4), (String)obj.get(5), Long.parseLong((String)obj.get(6))));
			}
			
			if (!(Boolean)session.getAttribute("isLoggedIn")) {
				throw new Exception("Попытка доступа незарегестрированного пользователя ("+username+")!");
			}
			if (!username.equalsIgnoreCase((String)session.getAttribute("un"))) {
				throw new Exception("Попытка доступа другого пользователя (владелец сессии: "+(String)session.getAttribute("un")+"; пользователь: "+username+")");
			}
			if (res_code == 0) {
			
				for (int i = 0; i < objects.size(); i++) {
					try {
						if (objects.get(i).getRes_code() == 0) {
							//DVS sign verification
							if (key_type == 1 && res_code == 0) {
								Signin signin = new Signin(); 
								signin.setId(objects.get(i).getObject_id());
								signin.setQuery_line(objects.get(i).getSign_text());//signBody
								signin.setCrp_line(objects.get(i).getSign_data());
								signin.setCurr_state("0");
								if (CheckNull.isEmpty(key_sn)) {
									throw new Exception("При верификации подписи возникли ошибки! Ключ пользователя не закреплен за данной организацией!");
								}
								signin = DvsSignService.create(signin, key_sn, 1);
								if (signin.getUpd_cnt() > 0) {
									res = SignlogService.updateStateAndErr(objects.get(i).getSign_log_id(),group_id,deal_id,action_id,objects.get(i).getObject_id(), 1, objects.get(i).getErr_message());
									if (res.getCode() != 0) {
										throw new Exception(res.getName());
									}
								} else {
									throw new Exception("При верификации подписи возникли ошибки! ("+signin.getErr_message()+")");
								}
							}
							
							//ASClient online verification
							if (key_type == 2 && res_code == 0) {
								res = ASClientService.checkASClientSign(objects.get(i).getSign_data(), objects.get(i).getSign_text());
								if (res.getCode() == 0) {
									Res rs = SignlogService.updateStateAndErr(objects.get(i).getSign_log_id(),group_id,deal_id,action_id,objects.get(i).getObject_id(), 2, res.getName());
									if (rs.getCode() != 0) {
										throw new Exception(rs.getName());
									}
								} else {
									Res rs = SignlogService.updateStateAndErr(objects.get(i).getSign_log_id(),group_id,deal_id,action_id,objects.get(i).getObject_id(), 3, res.getName());
									if (rs.getCode() != 0) {
										throw new Exception(rs.getName());
									}
									throw new Exception(res.getName());
								}
							}
						}
					} catch (Exception e) {
						objects.get(i).setRes_code(1);
						objects.get(i).setErr_message(CheckNull.isEmpty(e.getMessage())?CheckNull.getPstr(e):e.getMessage());
					}
				}
			}
			
			Component comp = execution.getDesktop().getComponentByUuid(page);
			HashMap<String, Object> _params = new HashMap<String, Object>();
			_params.put("page", page);
			_params.put("branch", branch);
			_params.put("user_id", user_id);
			_params.put("username", username);
			_params.put("schema", schema);
			_params.put("key_type", key_type);
			_params.put("key_sn", key_sn);
			_params.put("group_id", group_id);
			_params.put("deal_id", deal_id);
			_params.put("action_id", action_id);
			_params.put("err_message", err_message);
			_params.put("objects", objects);
			res = new Res(res_code, err_message, _params);
			Events.sendEvent("onSignList", comp, res);
		} catch (Exception e) {
			e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
			alert("При верификации подписи возникли ошибки ("+e.getMessage()+")!");
		}
	}
	
	
}
