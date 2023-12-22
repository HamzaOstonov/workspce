package com.is.login;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.zkoss.util.Locales;
import org.zkoss.util.media.AMedia;
import org.zkoss.util.resource.Labels;
import org.zkoss.web.Attributes;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.ClientInfoEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Constraint;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Menu;
import org.zkoss.zul.Menubar;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Menuseparator;
import org.zkoss.zul.Row;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.user.User;
import com.is.user.UserService;
import com.is.utils.CheckNull;
import com.is.utils.OBJ_Res;
import com.is.utils.RefCBox;
import com.is.utils.Res;

/**
 * The controller class for the view
 */
public class ViewController extends GenericForwardComposer
{
	
	private static final long serialVersionUID = 3611872886887987776L;
	// Page
	
	private Div div_logged_in;
	private SessionController sessionController = new SessionController();
	private Textbox win$tb_password;
	private Textbox win$tb_username, postsets$tb_host, postsets$tb_from, postsets$tb_un, postsets$tb_pwd;
	// private Intbox postsets$ib_port;
	private Label res, lblusr, lbldate, lblversion;
	// Button win$btn_login;
	// Vbox vbox_logged_out;
	private Div vbox_logged_out;
	private Window mainwnd;
	// Include tab0;
	// Include tab1;
	// Tabbox main_tabbox;
	private Include ical, iusr;
	private RefCBox win$cb_branch;
	private Row win$r_branch;
	private Iframe ihelp;
	private Button btnUsr, btnCal, pwdwnd$btn_save;
	private Window postsets;
	private Menubar menubar;
	private Menuitem btn_ru, btn_en, btn_uz, btn_logout, btn_pwd, btn_helpitem;
	private Menupopup mmdl;
	private Label module_caption;
	
	private int desktopHeight, desktopWidth;
	private Window repparwnd, pwdwnd;
	//private Report _rp;
	private Grid repparwnd$parGrid, pwdwnd$pwdgrd;
	private AMedia repmd;
	private Textbox pwdwnd$oldpwd, pwdwnd$newpwd, pwdwnd$newpwd1;
	private User currusr;
	private SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	private String helpurl = "/help/RU/HTML/index.html";
	
	/**
	 * Tries to fill the form with data stored to cookie
	 */
	
	/**
	 * Tries to automatically login the user
	 */
	
	public ViewController()
	{
		super('$', false, false);
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception
	{
		super.doAfterCompose(comp);
		/*
		 * if( Executions.getCurrent().isExplorer()){
		 * System.out.println("Ишак "+Executions.getCurrent().getUserAgent());
		 * }else{
		 * System.out.println("Не Ишак "+Executions.getCurrent().getUserAgent
		 * ()); } String port = ( Executions.getCurrent().getServerPort() == 80
		 * ) ? "" : (":" + Executions.getCurrent().getServerPort());
		 * System.out.println( Executions.getCurrent().getScheme() + "://" +
		 * Executions.getCurrent().getServerName() + port +
		 * Executions.getCurrent().getContextPath() +
		 * Executions.getCurrent().getDesktop().getRequestPath());
		 */

		// win$tb_username.setValue("test_select:"+UserService.test_select());
		
		init();
		ihelp = new Iframe();
		//pwdwnd.appendChild(ihelp);
		
		win$tb_username.setFocus(true);
		this.win$cb_branch.setModel(new ListModelList(Login_service.get_branches()));
	}
	
	/**
	 * Initializes this component
	 */
	private void init()
	{
		// Try to auto login if session is still ok
		
		if (!isLoggedIn())
		{
			switchToLoggedOutView();
		}
	}
	
	/**
	 * Checks if the user is already logged in
	 * 
	 * @return Returns true if the user is logged in, false if not.
	 */
	private boolean isLoggedIn()
	{
		if (sessionController.sessionIsNew())
		{
			// Return false if session is fresh
			return false;
		}
		else
		{
			// Returns the status that's set in the session object
			Object status = sessionController.getSessionObject("isLoggedIn");
			if (status == null)
			{
				return false;
			}
			else
			{
				return (Boolean) status;
			}
		}
	}
	
	/**
	 * Logs in the user
	 * 
	 * @throws Exception
	 */
	
	public void login()
	{
		int uid = -1;
		HttpServletRequest hr = (HttpServletRequest) Executions.getCurrent().getNativeRequest();
		if (win$tb_username.isValid() && win$tb_password.isValid())
		{
			
			// User us = new User();
			// us.setBranch("01066");
			// us.setUsername("admin");
			// us.setPassword("admin");
			// UserService.create(us);
			// Sessions.getCount();
			OBJ_Res res = UserService.lg(win$tb_username.getText(), win$tb_password.getText());
			if ((this.win$cb_branch.getValue() != null) && (this.win$cb_branch.getValue().length() > 0)) {
	        res = UserService.lg(this.win$tb_username.getText(), this.win$tb_password.getText(), this.win$cb_branch.getValue());
	      } else {
	        res = UserService.lg(this.win$tb_username.getText(), this.win$tb_password.getText());
	      }
	      if ((res.getCode() == -2) && (
	        (this.win$cb_branch.getValue() == null) || (this.win$cb_branch.getValue().length() == 0)))
	      {
	        this.win$r_branch.setVisible(true);
	        return;
	      }
			if (res.getCode() == 2) alert("Срок действия Вашего пароля подходит к концу.\n\nПожалуйста смените пароль.");
			
			currusr = (User) (res.getObj());
			lblusr.setValue(currusr.getFull_name() + " " + currusr.getTitle());
			lblversion.setValue("Версия:" + ConnectionPool.getValue("MUNIS_WEB_VERSION"));
			lbldate.setValue(df.format(new java.util.Date()));
			if (currusr.getId() > 0)
			{
				session.setAttribute("un", win$tb_username.getText());
				session.setAttribute("pwd", win$tb_password.getText());
				session.setAttribute("alias", currusr.getAlias());
				session.setAttribute("branch", currusr.getBranch());
				session.setAttribute("uid", currusr.getId());
				
				// if (UserService.lg(win$tb_username.getText(),
				// win$tb_password.getText())) {
				sessionController.setSessionObject("isLoggedIn", true);
				sessionController.setSessionObject("CurrUser", win$tb_username.getText());
				// HttpSession hs= (HttpSession)
				// Executions.getCurrent().getSession().getNativeSession();
				
				/*
				 * System.out.println("LocalAddr "+hr.getLocalAddr());
				 * System.out.println("LocalName "+hr.getLocalName());
				 * System.out.println("RemoteAddr "+hr.getRemoteAddr());
				 * System.out.println("RemoteUser "+hr.getRemoteUser());
				 * System.out.println("ServerName "+hr.getServerName());
				 */
				// UserActionsLog ulg = new
				// UserActionsLog(uid,win$tb_username.getText(),hr.getRemoteAddr(),1);
				String ipAddress = hr.getHeader("x-forwarded-for");
				if (ipAddress == null)
				{
					ipAddress = hr.getHeader("X_FORWARDED_FOR");
					if (ipAddress == null)
					{
						ipAddress = hr.getRemoteAddr();
					}
				}
				 //UserService.UsrLog(new UserActionLog(currusr.getId(),win$tb_username.getText(),ipAddress,1, Long.valueOf(1), "Удачный вход в систему", currusr.getBranch()));
                 //UserService.UsrLog(new UserActionLog(currusr.getBranch(),uid,win$tb_username.getText(),ipAddress,1),currusr.getAlias());
				//UserService.UsrLog(new UserActionsLog(currusr.getId(), win$tb_username.getText(), ipAddress, 1, Long.valueOf(1), "Удачный вход в систему", currusr.getBranch()));
				//UserService.UsrLog(new UserActionsLog(currusr.getBranch(), uid, win$tb_username.getText(), ipAddress, 1), currusr.getAlias());
				// UserService.UsrLog(new
				// UserActionsLog(uid,win$tb_username.getText(),hr.getRemoteAddr(),1));
				// currusr = UserService.getUser(uid);
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
				// ical.setSrc("ComJpayment.zul");
				
				// menubar.setWidth(width);
				addModule(currusr.getId(), "ru");
				session.setAttribute("uid", currusr.getId());
				session.setAttribute("current_user", currusr);
				session.setAttribute("branch", currusr.getBranch());
				session.setAttribute("uaccess", UserService.getAccess(currusr.getId(), currusr.getAlias()));
				// session.setAttribute("guid",UUID.randomUUID().toString());
				onClick$btn_ru();
				
				/** Adds session to list **/
				Execution exec = Executions.getCurrent();
				String req = "http://" + exec.getLocalAddr() + ":" + exec.getLocalPort() +
						"/bf/Users_control?uid=" +
						(Integer) sessionController.getSessionObject("uid") +
						"&session=" +
						exec.getSession().getNativeSession().hashCode() +
						"&action=login";
				
				URL url;
				try
				{
					url = new URL(req);
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					connection.setRequestMethod("GET");
					connection.connect();
					String strres = getResp(connection.getInputStream(), "cp1251");
					
					 System.out.println("Request:"+req+"  "+strres);
				}
				catch (Exception e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				// ical.setSrc("ComJpayment.zul?ht="+desktopHeight);
				
				// System.out.println("User "+uid);
				
			}
			else
			{
				//UserService.UsrLog(new UserActionsLog(currusr.getBranch(), uid, win$tb_username.getText(), hr.getRemoteAddr(), 2), currusr.getBranch());
				alert("Неверное имя или пароль!");
				// new TcException("Неверное имя или пароль");
				
			}
		}
		else
		{
			if (!win$tb_username.isValid())
			{
				win$tb_username.setValue(null);
			}
			if (!win$tb_password.isValid())
			{
				win$tb_password.setValue(null);
			}
		}
	}
	
	private void addModule(int userId, String lang)
	{
		Connection c = null;
		Menuitem mi, mt;
		Menupopup mp = null;
		Menu m;
		Menuseparator ms;
		int lmenulength = 0;
		
		try
		{
			// mmdl.getChildren().clear();
			menubar.getChildren().clear();
			m = new Menu("File");
			mp = new Menupopup();
			
			btn_logout = new Menuitem("exit");
			btn_logout.setId("btn_logout");
			btn_logout.setImage("/images/export.png");
			btn_logout.addEventListener(Events.ON_CLICK, new EventListener()
			{
				public void onEvent(Event event) throws Exception
				{
					onClick$btn_logout();
				}
			});
			
			mp.appendChild(btn_logout);
			
			String ssss = Labels.getLabel("user.chpwd");
			
			// System.out.println("lbl "+ssss);
			btn_pwd = new Menuitem(Labels.getLabel("user.chpwd"));
			btn_pwd.setId("btn_pwd");
			btn_pwd.setImage("/images/export.png");
			btn_pwd.addEventListener(Events.ON_CLICK, new EventListener()
			{
				public void onEvent(Event event) throws Exception
				{
					onClick$btn_pwd();
				}
			});
			
			mp.appendChild(btn_pwd);
			
			ms = new Menuseparator();
			mp.appendChild(ms);
			
			btn_ru = new Menuitem("ru");
			btn_ru.setId("btn_ru");
			btn_ru.setChecked(lang.equals("ru"));
			btn_ru.addEventListener(Events.ON_CLICK, new EventListener()
			{
				public void onEvent(Event event) throws Exception
				{
					onClick$btn_ru();
				}
			});
			mp.appendChild(btn_ru);
			
			btn_en = new Menuitem("en");
			btn_en.setId("btn_en");
			btn_en.setChecked(lang.equals("en"));
			btn_en.addEventListener(Events.ON_CLICK, new EventListener()
			{
				public void onEvent(Event event) throws Exception
				{
					onClick$btn_en();
				}
			});
			mp.appendChild(btn_en);
			
			btn_uz = new Menuitem("uz");
			btn_uz.setId("btn_uz");
			btn_uz.setChecked(lang.equals("uz"));
			btn_uz.addEventListener(Events.ON_CLICK, new EventListener()
			{
				public void onEvent(Event event) throws Exception
				{
					onClick$btn_uz();
				}
			});
			mp.appendChild(btn_uz);
			
			m.appendChild(mp);
			menubar.appendChild(m);
			/*
			if (lang.equals("ru"))
			{
				m = new Menu("Модули");
			}
			else
			{
				m = new Menu("Modullar");
			}
			mmdl = new Menupopup();
			mmdl.setId("mmdl");
			m.appendChild(mmdl);
			menubar.appendChild(m);
			*/
			m = new Menu("Help");
			// m.setId("btn_help");
			/*
			 * m.addEventListener(Events.ON_CLICK, new EventListener() { public
			 * void onEvent(Event event) throws Exception { onClick$btn_help();
			 * } });
			 */

			mp = new Menupopup();
			btn_helpitem = new Menuitem("help");
			btn_helpitem.setImage("/images/faq.png");
			btn_helpitem.setHref(helpurl);
			btn_helpitem.setTarget(_applied);
			btn_helpitem.setId("btn_helpitem");
			
			btn_helpitem.addEventListener(Events.ON_CLICK, new EventListener()
			{
				public void onEvent(Event event) throws Exception
				{
					onClick$btn_help();
				}
			});
			
			mp.appendChild(btn_helpitem);
			m.appendChild(mp);
			menubar.appendChild(m);
			
			c = ConnectionPool.getConnection(currusr.getAlias());
			// Statement s = c.createStatement();
			// ResultSet rs =
			// s.executeQuery("select level,'md'||t.id mid,t.mtype,t.name,t.mname,t.icon  from modules t START WITH t.parentid IS NULL CONNECT BY PRIOR t.id = t.parentid ORDER SIBLINGS BY t.id");
			System.out.println("select level,'md'||t.id mid,t.mtype,t.name_" + lang + ",t.mname,t.icon, t.extparam,t.help_url " +
					"from (select * from bf_modules m " +
					"where m.id in (select m.moduleid from bf_user_roles r, bf_role_modules m where m.roleid=r.roleid and r.userid=? and r.branch = ?) " +
					"or( m.id in (select t.parentid from bf_modules t where t.id in (select m.moduleid from bf_user_roles r, bf_role_modules m where m.roleid=r.roleid and r.userid=? and r.branch = ?) ))) t " +
					"START WITH t.parentid IS NULL CONNECT BY PRIOR t.id = t.parentid ORDER SIBLINGS BY t.id");
			PreparedStatement ps = c.prepareStatement("select level,'md'||t.id mid,t.mtype,t.name_" + lang + ",t.mname,t.icon, t.extparam,t.help_url " +
					"from (select * from bf_modules m " +
					"where m.id in (select m.moduleid from bf_user_roles r, bf_role_modules m where m.roleid=r.roleid and r.userid=? and r.branch = ?) " +
					"or( m.id in (select t.parentid from bf_modules t where t.id in (select m.moduleid from bf_user_roles r, bf_role_modules m where m.roleid=r.roleid and r.userid=? and r.branch = ?) ))) t " +
					"START WITH t.parentid IS NULL CONNECT BY PRIOR t.id = t.parentid ORDER SIBLINGS BY t.id");
			/*
			 * System.out.println(
			 * "select level,'md'||t.id mid,t.mtype,t.name_ru,t.mname,t.icon, t.extparam "
			 * + "from (select * from bf_modules m "+
			 * "where m.id in (select m.moduleid from bf_user_roles r, bf_role_modules m where m.roleid=r.roleid and r.userid="
			 * +userId+") "+
			 * "or( m.id in (select t.parentid from bf_modules t where t.id in (select m.moduleid from bf_user_roles r, bf_role_modules m where m.roleid=r.roleid and r.userid="
			 * +userId+") ))) t "+
			 * "START WITH t.parentid IS NULL CONNECT BY PRIOR t.id = t.parentid ORDER SIBLINGS BY t.id"
			 * );
			 */

			ps.setInt(1, userId);
			ps.setString(2, currusr.getBranch());
			ps.setInt(3, userId);
			ps.setString(4, currusr.getBranch());
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				System.out.println(lang +" "+rs.getString("name_" + lang));
				if (rs.getString("mtype").equals("0"))
				{
					m = new Menu();
					m.setLabel(rs.getString("name_" + lang));
					mp = new Menupopup();
					m.appendChild(mp);
					//mmdl.appendChild(m);
					menubar.appendChild(m);
					
				}
				else
				{
					mi = new Menuitem();
					mi.setId(rs.getString("mid"));
					mi.setLabel(rs.getString("name_" + lang));
					mi.setImage(rs.getString("icon"));
					mi.setValue(rs.getString("mname"));
					mi.setAttribute("exppar", rs.getString("extparam"));
					mi.setAttribute("help_url", rs.getString("help_url"));
					// System.out.println(":"+rs.getString("extparam"));
					mi.addEventListener(Events.ON_CLICK, new EventListener()
					{
						public void onEvent(Event event) throws Exception
						{
							ical.setSrc(null);
							Menuitem menuItem = (Menuitem) event.getTarget();
							if (menuItem.getValue().equals("report.zul"))
							{
								// System.out.println("onclick:"+menuItem.getAttribute("exppar"));
								ical.setSrc(menuItem.getValue() + "?id=" + menuItem.getId().substring(2));
							}
							else
							{
								// System.out.println("onclickelse:"+menuItem.getAttribute("exppar"));
								// System.out.println("Svitched to module:"+menuItem.getLabel());
								module_caption.setValue(menuItem.getLabel());
								ical.setSrc(menuItem.getValue() + "?ht=" + desktopHeight + (menuItem.getAttribute("exppar") != null ? menuItem.getAttribute("exppar").toString() : ""));
								
								// System.out.println("ical:"+ical.getSrc());
							}
							helpurl = "/help/RU/HTML/" + menuItem.getAttribute("help_url");
							btn_helpitem.setHref(helpurl);
							// System.out.println(menuItem.getValue());
						}
					});
					
					mp.appendChild(mi);
					// toolbar
					
					mt = new Menuitem();
					mt.setId("t" + rs.getString("mid"));
					mt.setLabel(rs.getString("name_" + lang));
					mt.setImage(rs.getString("icon"));
					mt.setValue(rs.getString("mname"));
					mt.setAttribute("exppar", rs.getString("extparam"));
					mt.setAttribute("help_url", rs.getString("help_url"));
					mt.addEventListener(Events.ON_CLICK, new EventListener()
					{
						public void onEvent(Event event) throws Exception
						{
							ical.setSrc(null);
							Menuitem menuItem = (Menuitem) event.getTarget();
							if (menuItem.getValue().equals("report.zul"))
							{
								ical.setSrc(menuItem.getValue() + "?id=" + menuItem.getId().substring(3));
							}
							else
							{
								module_caption.setValue(menuItem.getLabel());
								ical.setSrc(menuItem.getValue() + "?ht=" + desktopHeight + "&exppar=" + (menuItem.getAttribute("exppar") != null ? menuItem.getAttribute("exppar").toString() : ""));
							}
							// System.out.println(menuItem.getValue());
							helpurl = "/help/RU/HTML/" + menuItem.getAttribute("help_url");
							btn_helpitem.setHref(helpurl);
						}
					});
					// desktopWidth
					/*
					lmenulength = lmenulength + rs.getString("name_" + lang).length();
					if (lmenulength * 13 < desktopWidth)
					{
						menubar.appendChild(mt);
					}
					*/
					// System.out.println("Width "+menubar.getWidth()+"  label    "+lmenulength*13+"  "+rs.getString("name_ru"));
					// toolbar
				}
				// mmdl.appendChild(mi);
				
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
			
		}
		finally
		{
			ConnectionPool.close(c);
		}
		
	}
	
	private void rmvMenu()
	{
		int cnt = menubar.getChildren().size();
		List lt = menubar.getChildren();
		// System.out.println("all "+menubar.getChildren().size());
		Menuitem mi;
		for (int i = 0; i < cnt - 1; i++)
		{
			// if (menubar.getChildren().get(i) instanceof Menuitem){
			// mi = (Menuitem) menubar.getChildren().get(i);
			if (lt.get(i) instanceof Menuitem)
			{
				mi = (Menuitem) lt.get(i);
				// System.out.println(mi.getId()+"  "+mi.getLabel());
				menubar.getChildren().remove(i);
			}
		}
	}
	
	private boolean lg(String un, String pwd)
	{
		Connection c = null;
		boolean res = false;
		try
		{
			c = ConnectionPool.getConnection(currusr.getBranch());
			// System.out.println("SELECT * FROM v_bf_bank_users WHERE username="+un+" and state=1");
			PreparedStatement ps = c.prepareStatement("SELECT * FROM v_bf_bank_users WHERE username=? and state=1");
			
			// PreparedStatement ps =
			// c.prepareStatement("SELECT * FROM users WHERE username=? and state=1");
			ps.setString(1, un);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				res = rs.getString("password").equals(getMd5Digest(pwd));
			}
		}
		catch (Exception e)
		{
			ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
			
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return res;
		
	}
	
	static String getMd5Digest(String input)
	{
		try
		{
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			String s = number.toString(16);
			while (s.length() < 32)
				s = "0" + s;
			return s;
		}
		catch (NoSuchAlgorithmException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Logs out the user
	 */
	public void logout()
	{
		// Set logout flag in session and switch to login view
		sessionController.setSessionObject("isLoggedIn", false);
		sessionController.setSessionObject("CurrUser", null);
		switchToLoggedOutView();
		
	}
	
	/**
	 * Event handler. Gets called when the user clicks the login button.
	 */
	public void onClick$btn_login$win()
	{
		login();
	}
	
	/**
	 * Event handler. Gets called when the user presses the logout button.
	 */
	public void onClick$btn_logout()
	{
		logout();
	}
	
	public void onClick$btn_ru()
	{
		Locale locale = new Locale("ru", "RU");
		
		session.setAttribute(Attributes.PREFERRED_LOCALE, locale);
		try
		{
			Clients.reloadMessages(locale);
		}
		catch (IOException e)
		{
			ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		}
		Locales.setThreadLocal(locale);
		btn_ru.setChecked(true);
		btn_en.setChecked(false);
		btn_uz.setChecked(false);
		String tmp = ical.getSrc();
		ical.setSrc(null);
		ical.setSrc(tmp);
		addModule(currusr.getId(), "ru");
		
	}
	
	public void onClick$btn_uz()
	{
		Locale locale = new Locale("uz", "UZ");
		
		session.setAttribute(Attributes.PREFERRED_LOCALE, locale);
		try
		{
			Clients.reloadMessages(locale);
		}
		catch (IOException e)
		{
			ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		}
		Locales.setThreadLocal(locale);
		btn_ru.setChecked(false);
		btn_en.setChecked(false);
		btn_uz.setChecked(true);
		String tmp = ical.getSrc();
		ical.setSrc(null);
		ical.setSrc(tmp);
		addModule(currusr.getId(), "uz");
		
	}
	
	public void onClick$btn_en()
	{
		Locale locale = new Locale("en", "US");
		session.setAttribute(Attributes.PREFERRED_LOCALE, locale);
		try
		{
			Clients.reloadMessages(locale);
		}
		catch (IOException e)
		{
			ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		}
		Locales.setThreadLocal(locale);
		btn_ru.setChecked(false);
		btn_uz.setChecked(false);
		btn_en.setChecked(true);
		String tmp = ical.getSrc();
		ical.setSrc(null);
		ical.setSrc(tmp);
		addModule(currusr.getId(), "en");
		
	}
	
	public void onClick$btnUsr()
	{
		ical.setSrc(null);
		ical.setSrc("user.zul?ht=" + desktopHeight);
	}
	
	/**
	 * Event handler. Gets called when the user presses the enter key while the
	 * textbox for the password has focus.
	 */
	public void onOK$tb_password$win()
	{
		login();
	}
	
	/**
	 * Event handler. Gets called when the user presses the enter key while the
	 * textbox for the username has focus.
	 */
	public void onOK$tb_username$win()
	{
		login();
	}
	
	/**
	 * Displays the logged in view
	 */
	public void switchToLoggedInView()
	{
		vbox_logged_out.setVisible(false);
		div_logged_in.setVisible(true);
	}
	
	/**
	 * Displays the logged out view
	 */
	public void switchToLoggedOutView()
	{
		vbox_logged_out.setVisible(true);
		div_logged_in.setVisible(false);
	}
	
	public void onClientInfo$mainwnd(ClientInfoEvent evt)
	{
		desktopHeight = evt.getDesktopHeight() - 10;
		// mainwnd.setHeight(desktopHeight+"px");
		desktopHeight = evt.getDesktopHeight() - 80;
		desktopWidth = evt.getDesktopWidth();
		// menubar.setWidth((desktopWidth-60)+"px");
	}
	
	public void onClick$btn_pwd()
	{
		pwdwnd.setVisible(true);
	}
	
	public void onClick$btn_cancel$pwdwnd()
	{
		CheckNull.clearForm(pwdwnd$pwdgrd);
		pwdwnd.setVisible(false);
	}
	
	public void onClick$btn_save$pwdwnd()
	{
		
		Res rs = UserService.chPwd(currusr.getUser_name(), pwdwnd$newpwd.getValue(), pwdwnd$newpwd1.getValue(), pwdwnd$oldpwd.getValue());
		
		alert(rs.getName());
		
		if (rs.getCode() == 0)
		{
			CheckNull.clearForm(pwdwnd$pwdgrd);
			pwdwnd.setVisible(false);
			session.setAttribute("pwd", pwdwnd$newpwd.getValue());
		}
	}
	
	public void onClick$btn_help()
	{
		System.out.println("click!!!!!");
		btn_helpitem.setHref(helpurl);
		/*
		 * pwdwnd$btn_save.setVisible(false); pwdwnd.setTitle("Help");
		 * pwdwnd$pwdgrd.setVisible(false);
		 * pwdwnd.setHeight(desktopHeight+"px");
		 * pwdwnd.setWidth((desktopWidth-20)+"px");
		 * ihelp.setHeight((desktopHeight-20)+"px");
		 * ihelp.setWidth((desktopWidth-25)+"px"); pwdwnd.setVisible(true);
		 * //ihelp.setSrc("/help/RU/HTML/index.html");
		 * System.out.println("helpurl  "+helpurl); ihelp.setSrc(helpurl);
		 * //pwdwnd.beforeChildAdded(child, refChild)
		 */
		// Executions.getCurrent().sendRedirect("http://www.google.com",
		// "_blank");
		// Executions.getCurrent().
		// Clients.evalJavaScript("window.open('http://www.google.com','','top=100,left=200,height=600,width=800,scrollbars=1,resizable=1')");
	}
	
	public void onClick$btn_helpitem()
	{
		System.out.println("click!!!!!");
		onClick$btn_help();
	}
	
	public String getResp(InputStream stream, String cp)
	{
		StringWriter writer = new StringWriter();
		try
		{
			IOUtils.copy(stream, writer, cp);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return writer.toString();
	}
	
}
