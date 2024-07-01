package com.is.login;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import com.is.ConnectionPool;
import com.is.report.Report;
import com.is.user.Res;
import com.is.user.User;
import com.is.user.UserActionsLog;
import com.is.user.UserService;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
//import com.is.user.User;
//import com.is.user.UserService;
import net.sf.jasperreports.engine.JasperRunManager;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.ClientInfoEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.impl.PageImpl;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
//import org.zkoss.image.Image;
import org.zkoss.util.Locales;
import org.zkoss.util.media.AMedia;
import org.zkoss.util.resource.Labels;
import org.zkoss.web.Attributes;
import org.zkoss.zul.Button;
import org.zkoss.zul.Constraint;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Image;
import org.zkoss.zul.Include;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Menu;
import org.zkoss.zul.Menubar;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Menuseparator;
import org.zkoss.zul.Row;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.impl.InputElement;

/**
 * The controller class for the view
 */
public class ViewController extends GenericForwardComposer {

        private static final long serialVersionUID = 3611872886887987776L;
        //Page

        private  Div div_logged_in;
        private SessionController sessionController = new SessionController();
        private Textbox win$tb_password;
        private Textbox win$tb_username,postsets$tb_host,postsets$tb_from,postsets$tb_un,postsets$tb_pwd;
       // private Intbox postsets$ib_port;
        private Label res, login_info;
        private Image ipak_logo, small_ipak_logo, win$card_img, img_left;
        // Button win$btn_login;
        // Vbox vbox_logged_out;
        private Div vbox_logged_out;
        private Div mainwnd;
        //Include tab0;
        //Include tab1;
        //Tabbox main_tabbox;
        private Include ical, iusr;
        private Button btnUsr,btnCal;
        private Window postsets;
        private Menubar menubar;
        private Menuitem btn_ru,btn_en,btn_uz,btn_logout,btn_pwd;
        private Menupopup mmdl;

        private SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        
        private int desktopHeight;
        private Window  repparwnd,pwdwnd;
        private Report _rp;
        private Grid repparwnd$parGrid,pwdwnd$pwdgrd;
        private AMedia repmd; 
        private Textbox pwdwnd$oldpwd,pwdwnd$newpwd,pwdwnd$newpwd1;
        private User currusr;

        /**
         * Tries to fill the form with data stored to cookie
         */

        /**
         * Tries to automatically login the user
         */

        public ViewController() {
                super('$', false, false);
        }

        @Override
        public void doAfterCompose(Component comp) throws Exception {
                super.doAfterCompose(comp);
                /*
               if( Executions.getCurrent().isExplorer()){
            	   System.out.println("Ишак "+Executions.getCurrent().getUserAgent());
               }else{
            	   System.out.println("Не Ишак "+Executions.getCurrent().getUserAgent()); 
               }
               String port = ( Executions.getCurrent().getServerPort() == 80 ) ? "" : (":" + Executions.getCurrent().getServerPort());
               System.out.println( Executions.getCurrent().getScheme() + "://" + Executions.getCurrent().getServerName() + port + Executions.getCurrent().getContextPath() +  Executions.getCurrent().getDesktop().getRequestPath());
               */
                init();
                
                
               /* double c = 9999999999.999999;
                NumberFormat f = NumberFormat.getInstance();
                f.setGroupingUsed(false);

                System.out.println(f.format(c));*/
                Calendar cl0 = Calendar.getInstance();
               // cl0.setTime(df.parse("15.01.2015"));
                Date date0 = cl0.getTime();
                System.out.println();
                
                Calendar cl = Calendar.getInstance();
                Date date1 = cl.getTime();
                date1.setDate(10);
                date1.setMonth(0);
                
                Calendar cl2 = Calendar.getInstance();
                Date date2 = cl2.getTime();
                date2.setDate(20);
                date2.setMonth(11);
                
                if (
                		!(
                				(date0.after(date1)) && (date0.before(date2))
                		 )
                		)
                {
                	//mainwnd.setStyle("background:url(\"images/stockvault-christmas-frame-with-snow139092.jpg\") no-repeat ");
                	//mainwnd.setStyle("background:url(\"images/1920x1200-new-year-toys.png\") no-repeat ");
                	mainwnd.setStyle("background-color:#517A49");
                	//mainwnd.setStyle("background:url(\"images/Christmas_background_by_missiet.jpg\") no-repeat ")
                	img_left.setSrc("images/Christmas_background_by_missiet.jpg");
                	img_left.setVisible(true);
                	mainwnd.appendChild(img_left);
                	win$card_img.setSrc("images/logo1_ny.png");
                }
        }

        /**
         * Initializes this component
         */
        private void init() {
                // Try to auto login if session is still ok

                if (!isLoggedIn()) {
                        switchToLoggedOutView();
                }
        }

        /**
         * Checks if the user is already logged in
         *
         * @return Returns true if the user is logged in, false if not.
         */
        private boolean isLoggedIn() {
                if (sessionController.sessionIsNew()) {
                        // Return false if session is fresh
                        return false;
                } else {
                        // Returns the status that's set in the session object
                        Object status = sessionController.getSessionObject("isLoggedIn");
                        if (status == null) {
                                return false;
                        } else {
                                return (Boolean) status;
                        }
                }
        }

        /**
         * Logs in the user
         * @throws Exception
         */

        public void login()  {
        	int uid = -1;
        	HttpServletRequest hr = (HttpServletRequest) Executions.getCurrent().getNativeRequest();
      if(win$tb_username.isValid()&&win$tb_password.isValid()){
    	  
    	 // User us = new User();
    	 // us.setBranch("01066");
    	 // us.setUsername("admin");
    	 // us.setPassword("admin");
    	 // UserService.create(us);
    	  //Sessions.getCount();
    	  currusr=UserService.lg(win$tb_username.getText(), win$tb_password.getText());
    	  if (currusr.getId()>0){
    		  session.setAttribute("port", hr.getLocalPort());
              session.setAttribute("un", win$tb_username.getText());
              session.setAttribute("pwd", win$tb_password.getText());
              session.setAttribute("alias", currusr.getAlias());
              session.setAttribute("branch", currusr.getBranch());

               // if (UserService.lg(win$tb_username.getText(), win$tb_password.getText())) {
                        sessionController.setSessionObject("isLoggedIn", true);
                        sessionController.setSessionObject("CurrUser", win$tb_username.getText());
                        //HttpSession hs= (HttpSession) Executions.getCurrent().getSession().getNativeSession();
                        
                        /*
                        System.out.println("LocalAddr "+hr.getLocalAddr());
                        System.out.println("LocalName "+hr.getLocalName());
                        System.out.println("RemoteAddr "+hr.getRemoteAddr());
                        System.out.println("RemoteUser "+hr.getRemoteUser());
                        System.out.println("ServerName "+hr.getServerName());
                       */
                       // UserActionsLog ulg = new UserActionsLog(uid,win$tb_username.getText(),hr.getRemoteAddr(),1);
                        String ipAddress = hr.getHeader("x-forwarded-for");
                        if (ipAddress == null) {
                            ipAddress = hr.getHeader("X_FORWARDED_FOR");
                            if (ipAddress == null){
                                ipAddress = hr.getRemoteAddr();
                            }
                        }    
                        
                        UserService.UsrLog(new UserActionsLog(currusr.getId(),win$tb_username.getText(),ipAddress,1, 1, "Удачный вход в систему", currusr.getBranch()));
                       // UserService.UsrLog(new UserActionsLog(uid,win$tb_username.getText(),hr.getRemoteAddr(),1));
                        //currusr = UserService.getUser(uid);
                        switchToLoggedInView();
                        Constraint ct = win$tb_password.getConstraint();
                        win$tb_password.setConstraint("");
                        win$tb_password.setValue(null);
                        win$tb_password.setConstraint(ct);
                        ct = win$tb_username.getConstraint();
                        win$tb_username.setConstraint("");
                        win$tb_username.setValue("");
                        win$tb_username.setConstraint(ct);
                            ical.setSrc(null);
                            ical.setSrc("welcome.zul");
                            
                            addModule(currusr.getId());
                            session.setAttribute("uid", currusr.getId());
                            session.setAttribute("uname", currusr.getUser_name());
                            session.setAttribute("branch", currusr.getBranch());
                            session.setAttribute("curip", ipAddress);
                           // session.setAttribute("guid",UUID.randomUUID().toString());
                            onClick$btn_ru();
                           // System.out.println("User "+uid);
                            
                            ipak_logo.setVisible(false);
                            small_ipak_logo.setVisible(true);
                            
                            login_info.setVisible(true);
                      //      mainwnd.setStyle("background-color:#cae0ff");
                         //   mainwnd.setStyle("background-color:#eeeeff");
                            mainwnd.setStyle("background-color:#F2F2F2");
                            //login_info.setVisible(true);
                            //login_info.set
                            login_info.setValue(currusr.getFull_name()+" ("+currusr.getBranch()+": "+com.is.utils.RefDataService.getMfo_name(currusr.getBranch(), currusr.getAlias()).get(0).getLabel()+")");

                } else {
                	UserService.UsrLog(new UserActionsLog(-1,win$tb_username.getText(),hr.getRemoteAddr(),2, 1, "Неверное имя пользователя или пароль"));
                        alert("Неверное имя или пароль!");
                        // new TcException("Неверное имя или пароль");

                }
      }else{
              if(!win$tb_username.isValid()){
              win$tb_username.setValue(null) ;
              }
              if(!win$tb_password.isValid()){
              win$tb_password.setValue(null);
              }
      }
        }
        
        
        
        
        
        
        private void addModule(int userId) {
    		Connection c = null;
    		Menuitem mi,mt;
    		Menupopup mp = null;
    		Menu m;
    		Menuseparator ms;
    		
    		try {
    			//mmdl.getChildren().clear();
    			menubar.getChildren().clear();
    			m = new Menu("File");
    			mp = new Menupopup();
    			
    			btn_logout = new Menuitem("exit");
    			btn_logout.setId("btn_logout");
    			btn_logout.setImage("/images/export.png");
    			btn_logout.addEventListener(Events.ON_CLICK, new EventListener() {
    				public void onEvent(Event event) throws Exception {
    					onClick$btn_logout();
    				}
    			});
    			
    			mp.appendChild(btn_logout);

    			String ssss = Labels.getLabel("user.chpwd");
    			
    			//System.out.println("lbl "+ssss);
    			btn_pwd = new Menuitem(Labels.getLabel("user.chpwd"));
    			btn_pwd.setId("btn_pwd");
    			btn_pwd.setImage("/images/export.png");
    			btn_pwd.addEventListener(Events.ON_CLICK, new EventListener() {
    				public void onEvent(Event event) throws Exception {
    					onClick$btn_pwd();
    				}
    			});
    			
    			mp.appendChild(btn_pwd);
    			
    			
    			
    			ms = new Menuseparator(); 
    			mp.appendChild(ms);
    			
    			btn_ru = new Menuitem("ru");
    			btn_ru.setId("btn_ru");
    			btn_ru.setChecked(true);
    			btn_ru.addEventListener(Events.ON_CLICK, new EventListener() {
    				public void onEvent(Event event) throws Exception {
    					onClick$btn_ru();
    				}
    			});
    			mp.appendChild(btn_ru);	
    			
    			btn_en = new Menuitem("en");
    			btn_en.setId("btn_en");
    			btn_en.setChecked(false);
    			btn_en.addEventListener(Events.ON_CLICK, new EventListener() {
    				public void onEvent(Event event) throws Exception {
    					onClick$btn_en();
    				}
    			});
    			mp.appendChild(btn_en);
    			
    			btn_uz = new Menuitem("uz");
    			btn_uz.setId("btn_uz");
    			btn_uz.setChecked(false);
    			btn_uz.addEventListener(Events.ON_CLICK, new EventListener() {
    				public void onEvent(Event event) throws Exception {
    					onClick$btn_uz();
    				}
    			});
    			mp.appendChild(btn_uz);

    			m.appendChild(mp);
    			menubar.appendChild(m);
    			
    			m = new Menu("Модули");
    			mmdl = new Menupopup();
    			mmdl.setId("mmdl");
    			m.appendChild(mmdl);
    			menubar.appendChild(m);
    			
    			m = new Menu("Help");
    			mp = new Menupopup();
    			mi = new Menuitem("help");
    			mi.setImage("/images/faq.png");
    			mp.appendChild(mi);
    			m.appendChild(mp);
    			menubar.appendChild(m);
    			

    			
    			
    			c = ConnectionPool.getConnection(currusr.getAlias());
    			//Statement s = c.createStatement();
    			//ResultSet rs = s.executeQuery("select level,'md'||t.id mid,t.mtype,t.name,t.mname,t.icon  from modules t START WITH t.parentid IS NULL CONNECT BY PRIOR t.id = t.parentid ORDER SIBLINGS BY t.id");
    			PreparedStatement ps = c.prepareStatement("select level,'md'||t.id mid,t.mtype,t.name_ru,t.mname,t.icon, t.extparam "+  
                "from (select * from bf_modules m "+
                "where m.group_id in (0,144) and  m.id in (select m.moduleid from bf_user_roles r, bf_role_modules m where m.roleid=r.roleid and r.userid=?) "+
                "or( m.id in (select t.parentid from bf_modules t where t.id in (select m.moduleid from bf_user_roles r, bf_role_modules m where m.roleid=r.roleid and r.userid=?) ))) t "+
                "START WITH t.parentid IS NULL CONNECT BY PRIOR t.id = t.parentid ORDER SIBLINGS BY t.id");
    			/*System.out.println("select level,'md'||t.id mid,t.mtype,t.name_ru,t.mname,t.icon, t.extparam "+  
    	                "from (select * from bf_modules m "+
    	                "where m.id in (select m.moduleid from bf_user_roles r, bf_role_modules m where m.roleid=r.roleid and r.userid="+userId+") "+
    	                "or( m.id in (select t.parentid from bf_modules t where t.id in (select m.moduleid from bf_user_roles r, bf_role_modules m where m.roleid=r.roleid and r.userid="+userId+") ))) t "+
    	                "START WITH t.parentid IS NULL CONNECT BY PRIOR t.id = t.parentid ORDER SIBLINGS BY t.id");
    			*/ps.setInt(1, userId);
    			ps.setInt(2, userId);
    			ResultSet rs = ps.executeQuery();
    			while (rs.next()) {
    				if(rs.getString("mtype").equals("0")){
    					m = new Menu();
    					m.setLabel(rs.getString("name_ru"));
    					mp = new Menupopup();
    					m.appendChild(mp);
    					mmdl.appendChild(m);
    					
    				}else{
    				mi = new Menuitem();
    				mi.setId(rs.getString("mid"));
    				mi.setLabel(rs.getString("name_ru"));
    				mi.setImage(rs.getString("icon"));
    				mi.setValue(rs.getString("mname"));
    				mi.setAttribute("exppar", rs.getString("extparam"));
    				//System.out.println(":"+rs.getString("extparam"));
    				mi.addEventListener(Events.ON_CLICK, new EventListener() {
    					public void onEvent(Event event) throws Exception {
                            ical.setSrc(null);
                            Menuitem menuItem  = (Menuitem) event.getTarget();
                            if(menuItem.getValue().equals("report.zul")){
                            	//System.out.println("onclick:"+menuItem.getAttribute("exppar"));
                            	ical.setSrc(menuItem.getValue()+"?id="+menuItem.getId().substring(2));
                            }else{
                            	//System.out.println("onclickelse:"+menuItem.getAttribute("exppar"));
                            	
                            ical.setSrc(menuItem.getValue()+"?ht="+desktopHeight+ (menuItem.getAttribute("exppar")!=null ? menuItem.getAttribute("exppar").toString():""));
                            //System.out.println("ical:"+ical.getSrc());
                            }
                            //System.out.println(menuItem.getValue());
    					}
    				});
    				
    				mp.appendChild(mi);
    				//toolbar
    				
    				mt = new Menuitem();
    				mt.setId("t"+rs.getString("mid"));
    				mt.setLabel(rs.getString("name_ru"));
    				mt.setImage(rs.getString("icon"));
    				mt.setValue(rs.getString("mname"));
    				mt.setAttribute("exppar", rs.getString("extparam"));
    				mt.addEventListener(Events.ON_CLICK, new EventListener() {
    					public void onEvent(Event event) throws Exception {
                            ical.setSrc(null);
                            Menuitem menuItem  = (Menuitem) event.getTarget();
                            if(menuItem.getValue().equals("report.zul")){
                            	ical.setSrc(menuItem.getValue()+"?id="+menuItem.getId().substring(3));
                            }else{
                            ical.setSrc(menuItem.getValue()+"?ht="+desktopHeight+ (menuItem.getAttribute("exppar")!=null ? menuItem.getAttribute("exppar").toString():""));
                            }
                            //System.out.println(menuItem.getValue());
    					}
    				});
    				menubar.appendChild(mt);
    				//toolbar
    				}
    				//mmdl.appendChild(mi);
    				
    			}
    			
    		} catch (Exception e) {
    			e.printStackTrace();

    		} finally {
    			ConnectionPool.close(c);
    		}

    	}

        
        
        
        
        
        
        
        
        
        
        
        
      /*  
        
        
	private void addModule(int userId) {
		Connection c = null;
		Menuitem mi,mt;
		Menupopup mp = null;
		Menu m;
		Menuseparator ms;
		
		try {
			//mmdl.getChildren().clear();
			menubar.getChildren().clear();
			m = new Menu("File");
			mp = new Menupopup();
			
			btn_logout = new Menuitem("exit");
			btn_logout.setId("btn_logout");
			btn_logout.setImage("/images/export.png");
			btn_logout.addEventListener(Events.ON_CLICK, new EventListener() {
				public void onEvent(Event event) throws Exception {
					onClick$btn_logout();
				}
			});
			
			mp.appendChild(btn_logout);

			String ssss = Labels.getLabel("user.chpwd");
			
			//System.out.println("lbl "+ssss);
			btn_pwd = new Menuitem(Labels.getLabel("user.chpwd"));
			btn_pwd.setId("btn_pwd");
			btn_pwd.setImage("/images/export.png");
			btn_pwd.addEventListener(Events.ON_CLICK, new EventListener() {
				public void onEvent(Event event) throws Exception {
					onClick$btn_pwd();
				}
			});
			
			mp.appendChild(btn_pwd);
			
			
			
			ms = new Menuseparator(); 
			mp.appendChild(ms);
			
			btn_ru = new Menuitem("ru");
			btn_ru.setId("btn_ru");
			btn_ru.setChecked(true);
			btn_ru.addEventListener(Events.ON_CLICK, new EventListener() {
				public void onEvent(Event event) throws Exception {
					onClick$btn_ru();
				}
			});
			mp.appendChild(btn_ru);	
			
			btn_en = new Menuitem("en");
			btn_en.setId("btn_en");
			btn_en.setChecked(false);
			btn_en.addEventListener(Events.ON_CLICK, new EventListener() {
				public void onEvent(Event event) throws Exception {
					onClick$btn_en();
				}
			});
			mp.appendChild(btn_en);
			
			btn_uz = new Menuitem("uz");
			btn_uz.setId("btn_uz");
			btn_uz.setChecked(false);
			btn_uz.addEventListener(Events.ON_CLICK, new EventListener() {
				public void onEvent(Event event) throws Exception {
					onClick$btn_uz();
				}
			});
			mp.appendChild(btn_uz);

			m.appendChild(mp);
			menubar.appendChild(m);
			
			m = new Menu("Модули");
			mmdl = new Menupopup();
			mmdl.setId("mmdl");
			m.appendChild(mmdl);
			menubar.appendChild(m);
			
			m = new Menu("Help");
			mp = new Menupopup();
			mi = new Menuitem("help");
			mi.setImage("/images/faq.png");
			mp.appendChild(mi);
			m.appendChild(mp);
			menubar.appendChild(m);
			
			c = ConnectionPool.getConnection(currusr.getAlias());
			//Statement s = c.createStatement();
			//ResultSet rs = s.executeQuery("select level,'md'||t.id mid,t.mtype,t.name,t.mname,t.icon  from modules t START WITH t.parentid IS NULL CONNECT BY PRIOR t.id = t.parentid ORDER SIBLINGS BY t.id");
			PreparedStatement ps = c.prepareStatement("select level,'md'||t.id mid,t.mtype,t.name_ru,t.mname,t.icon "+  
            "from (select * from bf_modules m "+
            "where m.id in (select m.moduleid from bf_user_roles r, bf_role_modules m where m.roleid=r.roleid and r.userid=?) "+
            "or( m.id in (select t.parentid from bf_modules t where t.id in (select m.moduleid from bf_user_roles r, bf_role_modules m where m.roleid=r.roleid and r.userid=?) ))) t "+
            "START WITH t.parentid IS NULL CONNECT BY PRIOR t.id = t.parentid ORDER SIBLINGS BY t.id");
			ps.setInt(1, userId);
			ps.setInt(2, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if(rs.getString("mtype").equals("0")){
					m = new Menu();
					m.setLabel(rs.getString("name_ru"));
					mp = new Menupopup();
					m.appendChild(mp);
					mmdl.appendChild(m);
					
				}else{
				mi = new Menuitem();
				mi.setId(rs.getString("mid"));
				mi.setLabel(rs.getString("name_ru"));
				mi.setImage(rs.getString("icon"));
				mi.setValue(rs.getString("mname"));
				mi.addEventListener(Events.ON_CLICK, new EventListener() {
					public void onEvent(Event event) throws Exception {
                        ical.setSrc(null);
                        Menuitem menuItem  = (Menuitem) event.getTarget();
                        if(menuItem.getValue().equals("report.zul")){
                        	ical.setSrc(menuItem.getValue()+"?id="+menuItem.getId().substring(2));
                        }else{
                        ical.setSrc(menuItem.getValue()+"?ht="+desktopHeight);
                        }
                        //System.out.println(menuItem.getValue());
					}
				});
				
				mp.appendChild(mi);
				//toolbar
				
				mt = new Menuitem();
				mt.setId("t"+rs.getString("mid"));
				mt.setLabel(rs.getString("name_ru"));
				mt.setImage(rs.getString("icon"));
				mt.setValue(rs.getString("mname"));
				mt.addEventListener(Events.ON_CLICK, new EventListener() {
					public void onEvent(Event event) throws Exception {
                        ical.setSrc(null);
                        Menuitem menuItem  = (Menuitem) event.getTarget();
                        if(menuItem.getValue().equals("report.zul")){
                        	ical.setSrc(menuItem.getValue()+"?id="+menuItem.getId().substring(3));
                        }else{
                        ical.setSrc(menuItem.getValue()+"?ht="+desktopHeight);
                        }
                        //System.out.println(menuItem.getValue());
					}
				});
				menubar.appendChild(mt);
				//toolbar
				}
				//mmdl.appendChild(mi);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionPool.close(c);
		}

	}*/
	private void rmvMenu(){
		int cnt = menubar.getChildren().size();
		List lt = menubar.getChildren();
		System.out.println("all "+menubar.getChildren().size());
		Menuitem mi;
		for (int i=0;i<cnt-1;i++){
			//if (menubar.getChildren().get(i) instanceof Menuitem){
			//mi = (Menuitem) menubar.getChildren().get(i);
			if (lt.get(i) instanceof Menuitem){
				mi = (Menuitem) lt.get(i);
			    System.out.println(mi.getId()+"  "+mi.getLabel());
			    menubar.getChildren().remove(i);
			}
		}
	}
        

        private boolean lg(String un, String pwd) {
                Connection c = null;
                boolean res = false;
                try {
                        c = ConnectionPool.getConnection(currusr.getBranch());
                        System.out.println("SELECT * FROM v_bf_bank_users WHERE username="+un+" and state=1");
                        PreparedStatement ps = c.prepareStatement("SELECT * FROM v_bf_bank_users WHERE username=? and state=1");
                        
                        //PreparedStatement ps = c.prepareStatement("SELECT * FROM users WHERE username=? and state=1");
                        ps.setString(1, un);
                        ResultSet rs = ps.executeQuery();
                        if (rs.next()) {
                                res = rs.getString("password").equals(getMd5Digest(pwd));
                        }
                } catch (Exception e) {
                        e.printStackTrace();

                } finally {
                        ConnectionPool.close(c);
                }
                return res;

        }

        static String getMd5Digest(String input) {
                try {
                        MessageDigest md = MessageDigest.getInstance("MD5");
                        byte[] messageDigest = md.digest(input.getBytes());
                        BigInteger number = new BigInteger(1, messageDigest);
                        String s = number.toString(16);
                        while (s.length() < 32)
                                s = "0" + s;
                        return s;
                } catch (NoSuchAlgorithmException e) {
                        throw new RuntimeException(e);
                }
        }

        /**
         * Logs out the user
         */
        private void logout() {
                // Set logout flag in session and switch to login view
                sessionController.setSessionObject("isLoggedIn", false);
                sessionController.setSessionObject("CurrUser", null);
                switchToLoggedOutView();

        }

        /**
         * Event handler. Gets called when the user clicks the login button.
         */
        public void onClick$btn_login$win() {
                login();
        }

        /**
         * Event handler. Gets called when the user presses the logout button.
         */
        public void onClick$btn_logout() {
                logout();
        }

        public void onClick$btn_ru() {
                Locale locale = new Locale("ru");

                session.setAttribute(Attributes.PREFERRED_LOCALE, locale);
                try {
                                Clients.reloadMessages(locale);
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                Locales.setThreadLocal(locale);
                btn_ru.setChecked(true);
                btn_en.setChecked(false);
                btn_uz.setChecked(false);
                String tmp =ical.getSrc();
                ical.setSrc(null);
                ical.setSrc(tmp);

        }
        public void onClick$btn_uz() {
            Locale locale = new Locale("uz");

            session.setAttribute(Attributes.PREFERRED_LOCALE, locale);
            try {
                            Clients.reloadMessages(locale);
                    } catch (IOException e) {
                            e.printStackTrace();
                    }
            Locales.setThreadLocal(locale);
            btn_ru.setChecked(false);
            btn_en.setChecked(false);
            btn_uz.setChecked(true);
            String tmp =ical.getSrc();
            ical.setSrc(null);
            ical.setSrc(tmp);

    }

        public void onClick$btn_en() {
                Locale locale = new Locale("en","US");
                session.setAttribute(Attributes.PREFERRED_LOCALE, locale);
                try {
                                Clients.reloadMessages(locale);
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                Locales.setThreadLocal(locale);
                btn_ru.setChecked(false);
                btn_uz.setChecked(false);
                btn_en.setChecked(true);
                String tmp =ical.getSrc();
                ical.setSrc(null);
                ical.setSrc(tmp);

        }

        public void onClick$btnUsr() {
                ical.setSrc(null);
                ical.setSrc("user.zul?ht="+desktopHeight);
        }





        /**
         * Event handler. Gets called when the user presses the enter key while the
         * textbox for the password has focus.
         */
        public void onOK$tb_password$win() {
                login();
        }

        /**
         * Event handler. Gets called when the user presses the enter key while the
         * textbox for the username has focus.
         */
        public void onOK$tb_username$win() {
                login();
        }

        /**
         * Displays the logged in view
         */
        public void switchToLoggedInView() {
                vbox_logged_out.setVisible(false);
                div_logged_in.setVisible(true);
                mainwnd.setStyle("background-color:#F2F2F2");
                ipak_logo.setVisible(false);
                small_ipak_logo.setVisible(true);
                login_info.setVisible(true);
                img_left.setVisible(false);
        }

        /**
         * Displays the logged out view
         */
        public void switchToLoggedOutView() {
                vbox_logged_out.setVisible(true);
                div_logged_in.setVisible(false);
                mainwnd.setStyle("background-color:#517a49");
                ipak_logo.setVisible(true);
                small_ipak_logo.setVisible(false);
                login_info.setValue(null);
                login_info.setVisible(true);
                img_left.setVisible(false);
        }

        public void onClientInfo$mainwnd(ClientInfoEvent evt) {
          mainwnd.setHeight((evt.getDesktopHeight()-10)+"px");
          desktopHeight = evt.getDesktopHeight();
        }

        public void onClick$btn_pwd() {
        	pwdwnd.setVisible(true);
        }
        public void onClick$btn_cancel$pwdwnd() {
        	CheckNull.clearForm(pwdwnd$pwdgrd);
        	pwdwnd.setVisible(false);
        }
        public void onClick$btn_save$pwdwnd() {
        	
        	Res rs = UserService.chPwd(currusr.getUser_name(), pwdwnd$newpwd.getValue(), pwdwnd$newpwd1.getValue(), pwdwnd$oldpwd.getValue());
        	
        	if (rs.getCode()==0){
        		CheckNull.clearForm(pwdwnd$pwdgrd);
        		pwdwnd.setVisible(false);
        	    
        	}else{
        		alert(rs.getName());
        	}
        }

}