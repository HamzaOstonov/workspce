package com.is.sign;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Window;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.sign.keys.Key;
import com.is.sign.keys.KeyService;
import com.is.sign.log.Signlog;
import com.is.sign.log.SignlogService;
import com.is.utils.CheckNull;
import com.is.utils.RefData;

public class SignService {
	private static Logger log = ISLogger.getLogger();
	
	//checkActiveX---------------------------------
	public static void checkActiveX() {
		int signtype = Integer.parseInt(ConnectionPool.getValue("SIGN_TYPE"));
        if (signtype == 2) {
        	Clients.evalJavaScript("preloadAS();");
        } else {
        	Clients.evalJavaScript("preload();");
        }
	}
	
	//checkKeyCode---------------------------------
	public static void checkKeyCode(String page, String branch, int user_id, String username, int isLogin) throws Exception{
		Long signtype = Long.parseLong(ConnectionPool.getValue("SIGN_TYPE"));
		Key ukey = KeyService.getUser_key(branch, user_id, username);
		if (ukey == null) {
			throw new Exception("Для пользователя "+username+" ("+branch+" - "+user_id+") не найден ключ для верификации!");
		}
		if (ukey.getKey_type() != signtype) {
			throw new Exception("Тип ключа для верификации не совпадает с установленным в системе!");
		}
		if (signtype == 2) {
        	Clients.evalJavaScript("checkKeyCodeAS('"+page+"', '"+branch+"', "+user_id+", '"+username+"', '"+ukey.getKey_sn()+"',"+isLogin+");");
        } else {
        	Clients.evalJavaScript("checkKeyCode(  '"+page+"', '"+branch+"', "+user_id+", '"+username+"', '"+ukey.getKey_code()+"',"+isLogin+");");
        }
	}
	
	//sign---------------------------------
	public static Res sign(String page, String branch, int user_id, String username, String scheme, int group_id, int deal_id, int action_id, Long object_id, String textForSign) {
		Res res = new Res();
		Long sign_log_id=0L;
		Long signtype = Long.parseLong(ConnectionPool.getValue("SIGN_TYPE"));
		Key ukey = KeyService.getUser_key(branch, user_id, username);
		try {
			if (ukey == null) {
				throw new Exception("Для пользователя "+username+" ("+branch+" - "+user_id+" - "+username+") не найден ключ для верификации!");
			}
			if (ukey.getKey_type() != signtype) {
				throw new Exception("Тип ключа для верификации не совпадает с установленным в системе!");
			}
			
			textForSign = textForSign.replaceAll("'", "`");
			res = SignlogService.create(
					new Signlog(sign_log_id, 
								new Date(),
								group_id, 
								deal_id, 
								action_id, 
								object_id, 
								textForSign, 
								"", 
								ukey.getKey_type(), 
								ukey.getKey_code(), 
								ukey.getKey_sn(), 
								branch, 
								user_id, 
								username, 
								scheme, 
								0, 
								""));
			if (res.getCode() == 0) {
				if (ukey.getKey_type() == 2) {
		        	Clients.evalJavaScript("signAS("+ukey.getKey_type()+",'"+ukey.getKey_sn()  +"','"+textForSign+"', ['"+page+"','"+branch+"','"+user_id+"','"+username+"','"+scheme+"',"+group_id+","+deal_id+","+action_id+",'"+object_id+"','"+sign_log_id+"']);");
		        } else {
		        	Clients.evalJavaScript("sign  ("+ukey.getKey_type()+",'"+ukey.getKey_code()+"','"+textForSign+"', ['"+page+"','"+branch+"','"+user_id+"','"+username+"','"+scheme+"',"+group_id+","+deal_id+","+action_id+",'"+object_id+"','"+sign_log_id+"']);");
		        }
			} else {
				throw new Exception(res.getName());
			}
		} catch (Exception e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
			res = new Res(1, "При попытке подписи объекта возникли ошибки: "+e.getMessage());
		}
		return res;
	}
	
	//signList
	public static Res signList(String page, String branch, int user_id, String username, String scheme, int group_id, int deal_id, int action_id, List<SignList> objects) {
		Res res = new Res();
		Long sign_log_id=0L;
		Long signtype = Long.parseLong(ConnectionPool.getValue("SIGN_TYPE"));
		Key ukey = KeyService.getUser_key(branch, user_id, username);
		try {
			if (ukey == null) {
				throw new Exception("Для пользователя "+username+" ("+branch+" - "+user_id+") не найден ключ для верификации!");
			}
			if (ukey.getKey_type() != signtype) {
				throw new Exception("Тип ключа для верификации не совпадает с установленным в системе!");
			}
			for (int i = 0; i < objects.size(); i++) {
				if (CheckNull.isEmpty(objects.get(i).getRes_code()) || objects.get(i).getRes_code() == 0) {
					res = SignlogService.create(
							new Signlog(sign_log_id, 
									new Date(),
									group_id, 
									deal_id, 
									action_id, 
									objects.get(i).getObject_id(), 
									objects.get(i).getSign_text(), 
									"", 
									ukey.getKey_type(), 
									ukey.getKey_code(), 
									ukey.getKey_sn(), 
									branch, 
									user_id, 
									username, 
									scheme, 
									objects.get(i).getRes_code(), 
									objects.get(i).getErr_message()));
					if (res.getCode() == 0) {
						objects.get(i).setSign_log_id((Long)res.getObj());
						objects.get(i).setRes_code(0);
						objects.get(i).setErr_message("");
					} else {
						objects.get(i).setSign_log_id(0L);
						objects.get(i).setRes_code(1);
						objects.get(i).setErr_message(res.getName());
					}
				}
			}
			StringBuffer js = new StringBuffer();
			if (ukey.getKey_type() == 2) {//signtype == 2) {
	        	js.append("signListAS("+ukey.getKey_type()+",'"+ukey.getKey_sn()+"', ");
	        	js.append(" [");
	        } else {
	        	js.append("signList  ("+ukey.getKey_type()+",'"+ukey.getKey_code()+"', ");
	        	js.append(" [");
	        }
			for (int j = 0; j < objects.size(); j++) {
				if (j != 0) {
					js.append(",");
				}
				js.append(" ['"+objects.get(j).getObject_id()+"','"+objects.get(j).getObject_num()+"','"+objects.get(j).getSign_text()+"','',"+objects.get(j).getRes_code()+",'"+objects.get(j).getErr_message()+"','"+objects.get(j).getSign_log_id()+"']");
			}
			js.append("],");
        	js.append(" ['"+page+"','"+branch+"','"+user_id+"','"+username+"','"+scheme+"',"+group_id+","+deal_id+","+action_id+"]);");
			Clients.evalJavaScript(js.toString());
		} catch (Exception e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
			res = new Res(1, "При попытке подписи объекта возникли ошибки: "+e.getMessage());
		}
		return res;
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
	
	public static void showSignErrProtocol(List<SignList> list, Component comp) {
		Window wnd = null;
		SignProtocolViewCtrl sv = null;
		try{
			if(wnd == null && !comp.hasAttribute("signprotocol.zul")){
				wnd = (Window)Executions.createComponents("/sign/signprotocol.zul", comp, null);
				comp.setAttribute("signprotocol.zul", wnd);
				//wnd.addEventListener("onAddDocument", this);
				sv = (SignProtocolViewCtrl)wnd.getAttribute("signerrwnd$composer");
  			} else {
  				wnd = (Window)comp.getAttribute("signprotocol.zul");
  			}
  			if (sv == null) {
  				sv = (SignProtocolViewCtrl)wnd.getAttribute("signerrwnd$composer");
  			}
  			sv.init(list);
  		} catch (Exception e) {
  			// TODO: handle exception
  			e.printStackTrace();
  		}
	}
}
