package com.is.openway.trpay;

//bf_tr_pay_printed
import issuing_v_01_02_xsd.OperationConnectionInfo;
import issuing_v_01_02_xsd.RowType_ExecTransaction_Request;
import issuing_v_01_02_xsd.holders.OperationResponseInfoHolder;
import issuing_v_01_02_xsd.holders.RowType_ExecTransaction_ResponseHolder;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.type.OrientationEnum;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.zkoss.util.media.AMedia;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;

import IssuingWS.IssuingPortProxy;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.report.DPrint;

import com.is.openway.PostUtils;
import com.is.openway.Utils;
import com.is.openway.XmlUtils;
import com.is.openway.accpay.AccPayService;
import com.is.openway.customer.CustomerService;
import com.is.openway.model.UFXMsgAddContractAcc;
import com.is.openway.model.UFXMsgAddContractAccResp;
import com.is.openway.model.UFXMsgPayAcc;
import com.is.openway.model.UFXMsgPayAccResp;
import com.is.openwayutils.user.Action;
import com.is.openwayutils.user.UserActionsLog;
import com.is.openwayutils.user.UserService;
import com.is.openwayutils.utils.CheckNull;
import com.is.openwayutils.utils.RefCBox;
import com.is.openwayutils.utils.RefData;
import com.is.openwayutils.utils.RefDataService;
import com.is.openwayutils.utils.Res;

public class TrPayViewCtrl extends GenericForwardComposer {
	private Div frm;
	private Listbox dataGrid, paytmpGrid, documentsGrid;
	private Paging contactPaging;
	private Div grd, dfgrd;
	private Grid addgrd, frmgrd, fgrd;
	private Toolbarbutton btn_last;
	private Toolbarbutton btn_next;
	private Toolbarbutton btn_prev;
	private Toolbarbutton btn_first;
	private Listheader nnmb_h;
	private Toolbarbutton btn_add;
	private Toolbarbutton btn_search;
	private Checkbox to_print;
	private Toolbarbutton btn_back, fbt, btn_ref$refwnd, btn_cancel$refwnd;
	private Toolbar tb;
	private Textbox id, branch, operation_id, amount, card_acc, cur_acc,
			parentgroupid, state, account_no, cl_name, txt_duplicate,
			txt_duplicate_d;
	private Textbox aid, abranch, aoperation_id, aamount, acard_acc, acur_acc,
			aparentgroupid, astate, aaccount_no, acl_name;
	private Textbox fid, fbranch, foperation_id, famount, fcard_acc, fcur_acc,
			fparentgroupid, fstate, faccount_no, fcl_name;
	private Datebox date_created, adate_created, fdate_created, fl_date;
	private Paging trpayPaging;
	private int _pageSize = 6;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	private RefCBox filter_box, filter_box_oper;
	private Window refwnd, printwnd;
	public TrPayDocs tcurrent;
	private Iframe printwnd$rpframe;
    private Tabbox tbfrm;
    
	private HashMap<Long, Boolean> to_print_mem = new HashMap<Long, Boolean>();

	private DecimalFormat dmf = new DecimalFormat("0.00##");
	public TrPayFilter filter;
	private String un, pwd, alias;
	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	private SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	private int uid = 0;
	private Toolbarbutton bt = null;
	private Toolbarbutton bt_doc = null;
	private IssuingPortProxy pp = null;
	private static HashMap<String, String> _trState = null;
	private static List<RefData> states;
	static String wayRrnConstant; //81
	static String wayPaySourceContractNum; //9058-RBS_LORO_NOSTRO
	static String openwayEndpoint;
	// private final int actid=0;

	private Toolbar toolbar_filter;
	//private int access;

	public class Cur_cont {
		String un;
		String pwd;
		String alias;
		int operation_id;
		String action_desc;
		String operation_desc;
		TrPay trpay1;
		int actid;

		public Cur_cont(String un, String pwd, String alias, TrPay trpay1,
				int actid, String operation_desc, int operation_id,
				String action_desc) {
			this.un = un;
			this.pwd = pwd;
			this.alias = alias;
			this.trpay1 = trpay1;
			this.actid = actid;
			this.operation_desc = operation_desc;
			this.operation_id = operation_id;
			this.action_desc = action_desc;

		}

		@Override
		public String toString() {
			return "Cur_cont [un=" + un + ", pwd=" + pwd + ", alias=" + alias
					+ ", operation_id=" + operation_id + ", action_desc="
					+ action_desc + ", operation_desc=" + operation_desc
					+ ", trpay1=" + trpay1 + ", actid=" + actid + "]";
		}

	}

	private Cur_cont cur_cont;
	private String uname, curip, branch1;
	private boolean show_active;
	private NumberFormat nf = NumberFormat.getInstance();
	private boolean sorted_desc = true;

	private TrPay current = new TrPay();

	public TrPayViewCtrl() {
		super('$', false, false);
	}

	/**
	     *
	     *
	     */
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		// TODO Auto-generated method stub
		binder = new AnnotateDataBinder(comp);
		binder.bindBean("current", this.current);
		binder.loadAll();
		String[] parameter = (String[]) param.get("ht");
		alias = (String) session.getAttribute("alias");
		// DO NOT NOW
		/*
		 * if (parameter!=null){ _pageSize = Integer.parseInt( parameter[0])/36;
		 * dataGrid.setRows(Integer.parseInt( parameter[0])/36); }
		 */

		nf.setGroupingUsed(false);

		uid = (Integer) session.getAttribute("uid");
		un = (String) session.getAttribute("un");
		branch1 = (String) session.getAttribute("branch");
		uname = "uname";//(String) session.getAttribute("uname");
		curip = (String) session.getAttribute("curip");
		if (curip == null || curip == "" || curip.equals(""))
			curip = "1";
		pwd = (String) session.getAttribute("pwd");
		_trState = com.is.openwayutils.utils.RefDataService.getHTrState(alias);
		setStates();
		
		show_active = false;
		if (wayRrnConstant == null || wayRrnConstant == ""
			|| wayRrnConstant.equals(""))
			wayRrnConstant = ConnectionPool.getValue("OPENWAY_RRN_CODE");
		if (wayPaySourceContractNum == null || wayPaySourceContractNum == ""
			|| wayPaySourceContractNum.equals(""))
			wayPaySourceContractNum = ConnectionPool.getValue("OPENWAY_PAYSOURCE_CONTRACTNUM");
		if (openwayEndpoint == null || openwayEndpoint == ""
			|| openwayEndpoint.equals(""))
		openwayEndpoint = ConnectionPool.getValue("OPENWAY_ENDPOINT");
				
		//this.access = com.is.user.UserService.getUser_Access(alias, uid);

		//List<State> states;
		//states = TrPayService.getStates(alias);

		//filter_box.setModel((new ListModelList(RefDataService
		//		.get_payments_states(alias))));
        
		filter_box.setModel((new ListModelList(states)));
		filter_box_oper.setModel((new ListModelList(TrPayService.getListOperActions(alias))));
		/*
		 * for ( int i=0;i<states.size();i++) { fbt = new Toolbarbutton();
		 * fbt.setLabel(states.get(i).getName());
		 * fbt.setImage("/images/filter.png"); fbt.setAttribute("state_id",
		 * states.get(i).getId()); fbt.setAttribute("deal_id",
		 * states.get(i).getDeal_id()); fbt.addEventListener(Events.ON_CLICK,
		 * new EventListener() {
		 * 
		 * @Override public void onEvent(Event event) {
		 * filter.setState((Integer)event.getTarget().getAttribute("state_id"));
		 * filter
		 * .setDeal_id((Integer)event.getTarget().getAttribute("deal_id")); if
		 * (access>0) filter.setBranch((String)session.getAttribute("branch"));
		 * refreshModel(_startPageNumber); } }); //
		 * toolbar_filter.appendChild(fbt); }
		 */
		fbt = new Toolbarbutton();
		fbt.setLabel("Очистить фильтр");
		fbt.setImage("/images/filter(delete).png");
		fbt.setLeft("100px");
		fbt.addEventListener(Events.ON_CLICK, new EventListener() {
			@Override
			public void onEvent(Event event) {
				filter = new TrPayFilter();
				filter.setParent_group_id(144);
				//if (access > 0)
				filter.setBranch((String) session.getAttribute("branch"));
				fl_date.setValue(null);
				filter_box.setValue(null);
				refreshModel(_startPageNumber);
			}
		});
		toolbar_filter.appendChild(fbt);
		filter = new TrPayFilter();
		//if (access > 0) {
			filter.setBranch((String) session.getAttribute("branch"));
			filter.setParent_group_id(144);
		//}
		dataGrid.setItemRenderer(new ListitemRenderer() {
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception {
				TrPay pTrPay = (TrPay) data;
				row.setAttribute("row", (Object) row);
				Listcell lc = new Listcell();
				Listcell lc_doc = new Listcell();
				List<Action> la = TrPayService.getActions(pTrPay.getState(),
						uid, (String) session.getAttribute("branch"),
						pTrPay.getDeal_id(), alias);
				// if (la.size()==0) row.setVisible(false);
				for (int i = 0; i < la.size(); i++) {

					bt = new Toolbarbutton();
					bt.setLabel(la.get(i).getName());
					bt.setImage(la.get(i).getIcon());
					bt.setAttribute("actid", la.get(i).getId());
					bt.setAttribute("act_name", la.get(i).getName());
					bt.setAttribute("operation_desc", pTrPay.getOperation());
					bt.setAttribute("operation_id", pTrPay.getOperation_id());
					bt.setAttribute("action_desc", la.get(i).getName());

					bt.setAttribute("deal_id", pTrPay.getDeal_id());
					bt.setAttribute("pTrPay", pTrPay);
					bt.addEventListener(Events.ON_CLICK, new EventListener() {

						@Override
						public void onEvent(Event event) throws Exception {
							cur_cont = new Cur_cont(un, pwd, alias,
									(TrPay) event.getTarget().getAttribute(
											"pTrPay"), (Integer) event
											.getTarget().getAttribute("actid"),
									(String) event.getTarget().getAttribute(
											"operation_desc"), (Integer) event
											.getTarget().getAttribute(
													"operation_id"),
									(String) event.getTarget().getAttribute(
											"action_desc"));

							/*
							 * if (false) //////////////// DO NOT REMOVE {
							 * refwnd.setVisible(true); } else
							 */

							if (TrPayService.isConfirmationAction(
									144,
									(Integer) event.getTarget().getAttribute(
											"deal_id"), (Integer) event
											.getTarget().getAttribute("actid"),
									alias)) {
								try {
									Messagebox.show("Выполнить действие '"
											+ (String) event.getTarget()
													.getAttribute("act_name")
											+ "'?", "Подтверждение",
											Messagebox.YES | Messagebox.NO,
											Messagebox.QUESTION,
											new EventListener() {
												public void onEvent(Event e) {
													// if (Messagebox.ON_YES
													// .equals(e.getName()))
													int answer = (Integer) e
															.getData();
													if (answer == Messagebox.YES) {
														// alert(cur_cont.toString());
														authomat_step(cur_cont);
													} else if (/*
																 * Messagebox.ON_NO
																 * .
																 * equals(e.getName
																 * ())
																 */answer == Messagebox.YES) {
													}
												}
											});

								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							} else {
								authomat_step(cur_cont);
								// alert(cur_cont.toString());
							}

							// authomat_step(cur_cont);

							/*
							 * Connection c = null; c =
							 * ConnectionPool.getConnection(un,pwd,alias);
							 * c.setAutoCommit(false);
							 * 
							 * Res rs = TrPayService.doAction(un, pwd,
							 * (TrPay)event
							 * .getTarget().getAttribute("pTrPay"),(Integer
							 * )event
							 * .getTarget().getAttribute("actid"),alias,c); if
							 * (rs.getCode()<0) { alert(rs.getName()); } else {
							 * TrPay tp1 =
							 * (TrPay)event.getTarget().getAttribute("pTrPay");
							 * if(TrPayService.isEndState(tp1.getId(), alias,
							 * c)) { if
							 * (send2t((TrPay)event.getTarget().getAttribute
							 * ("pTrPay"))) {c.commit();} else c.rollback(); }
							 * else c.commit();
							 * 
							 * refreshModel(_startPageNumber); }
							 * ConnectionPool.close(c);
							 */
						}

					});
					lc.appendChild(bt);
				}

				bt_doc = new Toolbarbutton();
				bt_doc.setLabel("мемориальные ордера");
				bt_doc.setImage("images/add-printer.png");
				bt_doc.setAttribute("pTrPay", pTrPay);

				bt_doc.addEventListener(Events.ON_CLICK, new EventListener() {
					@Override
					public void onEvent(Event event) throws Exception {
						HashMap<String, Object> params = new HashMap<String, Object>();

						// (TrPay)event.getTarget().getAttribute("pTrPay");
						params.put("P_PAYID", BigDecimal.valueOf(((TrPay) event
								.getTarget().getAttribute("pTrPay")).getId()));
						printwnd$rpframe.setContent(DPrint.getRepPdf(
								"TI_MEMORDER.pdf",
								application
										.getRealPath("reports/TI_MEMORDER.jasper"),
								params, alias));

						printwnd.setVisible(true);
					}
				});
				to_print = new Checkbox();
				to_print.setAttribute("pTrPay", pTrPay);
				to_print.addEventListener(Events.ON_CLICK, new EventListener() {
					@Override
					public void onEvent(Event event) throws Exception {
						if (to_print_mem.containsKey(((TrPay) event.getTarget()
								.getAttribute("pTrPay")).getId())) {
							to_print_mem.remove(((TrPay) event.getTarget()
									.getAttribute("pTrPay")).getId());
						} else {
							to_print_mem.put(((TrPay) event.getTarget()
									.getAttribute("pTrPay")).getId(), true);
						}
					}
				});
				// to_print_mem = new Checkbox();

				// if (
				/*
				 * (TrPayService.isInPrintableState(pTrPay.getDeal_id(),
				 * pTrPay.getState(), alias))&&
				 */
				// (pTrPay.getBranch().compareTo(branch1) == 0)
				// )
				lc_doc.appendChild(to_print);
				// lc_doc.appendChild(bt_doc);

				row.setValue(pTrPay);
				row.setCheckable(true);
				row.appendChild(new Listcell(Long.toString(pTrPay.getId())));
				row.appendChild(new Listcell(pTrPay.getDoc_num()));
				row.appendChild(new Listcell(pTrPay.getBranch()));
				row.appendChild(new Listcell(pTrPay.getCur_acc()));
				row.appendChild(new Listcell(pTrPay.getCard_acc()));
				row.appendChild(new Listcell(pTrPay.getPan()));
				row.appendChild(new Listcell(dmf.format(((double) (pTrPay
						.getAmount())) / 100)));
				row.appendChild(new Listcell(
						df.format(pTrPay.getDate_created())));
				row.appendChild(lc);
				row.appendChild(new Listcell(pTrPay.getCl_name()));
				row.appendChild(new Listcell(_trState.get(pTrPay.getState()
						+ "v" + pTrPay.getDeal_id())));
				row.appendChild(new Listcell(TrPayService.get_operation_desc(
						pTrPay.getOperation_id(), alias)));
				row.appendChild(lc_doc);

			}
		});
		/*pp = new IssuingPortProxy(ConnectionPool.getValue("TIETO_HOST", alias),
				ConnectionPool.getValue("TIETO_HOST_USERNAME", alias),
				ConnectionPool.getValue("TIETO_HOST_PASSWORD", alias));*/

		paytmpGrid.setItemRenderer(new ListitemRenderer() {
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception {
				TrPayDocs pTrPayDocs = (TrPayDocs) data;

				row.setValue(pTrPayDocs);

				row.appendChild(new Listcell(pTrPayDocs.getPay_id() + ""));
				row.appendChild(new Listcell(pTrPayDocs.getBranch()));
				row.appendChild(new Listcell(df.format(pTrPayDocs.getD_date())));
				row.appendChild(new Listcell(pTrPayDocs.getBank_cl()));
				row.appendChild(new Listcell(pTrPayDocs.getAcc_cl()));
				row.appendChild(new Listcell(pTrPayDocs.getName_cl()));
				row.appendChild(new Listcell(pTrPayDocs.getBank_co()));
				row.appendChild(new Listcell(pTrPayDocs.getAcc_co()));
				row.appendChild(new Listcell(pTrPayDocs.getName_co()));
				row.appendChild(new Listcell(
						dmf.format(pTrPayDocs.getSumma() / 100)));

			}
		});

		documentsGrid.setItemRenderer(new ListitemRenderer() {
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception {
				Paydoc paydoc = (Paydoc) data;

				row.setValue(paydoc);

				row.appendChild(new Listcell(Integer.toString(paydoc.getORD())));
				row.appendChild(new Listcell(paydoc.getBRANCH()));
				row.appendChild(new Listcell(df.format(paydoc.getD_DATE())));
				row.appendChild(new Listcell(paydoc.getBANK_CL()));
				row.appendChild(new Listcell(paydoc.getACC_CL()));
				row.appendChild(new Listcell(paydoc.getNAME_CL()));
				row.appendChild(new Listcell(paydoc.getBANK_CO()));
				row.appendChild(new Listcell(paydoc.getACC_CO()));
				row.appendChild(new Listcell(paydoc.getNAME_CO()));
				row.appendChild(new Listcell(paydoc.getPURPOSE()));
				row.appendChild(new Listcell(nf.format(paydoc.getSUMMA())));
				row.appendChild(new Listcell(paydoc.getTYPEDOC()));
				row.appendChild(new Listcell(paydoc.getPDC()));
				row.appendChild(new Listcell(paydoc.getCASH_CODE()));
				row.appendChild(new Listcell(Integer.toString(paydoc
						.getID_TRANSH_PURP())));
				row.appendChild(new Listcell(paydoc.getG_BRANCH()));
				row.appendChild(new Listcell(Integer.toString(paydoc
						.getG_DOCID())));
			}
		});
		//onClick$btn_filter_act();
		refreshModel(_startPageNumber);
	}

	public void onPaging$trpayPaging(ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}

	private void refreshModel(int activePage) {
		//if (access > 0) {
			filter.setBranch((String) session.getAttribute("branch"));
		//}
		trpayPaging.setPageSize(_pageSize);
		filter.setParent_group_id(144);
		model = new PagingListModel(activePage, _pageSize, filter, alias,
				sorted_desc);

		_totalSize = model.getTotalSize(filter, alias);

		trpayPaging.setTotalSize(_totalSize);

		dataGrid.setModel((ListModel) model);
		to_print_mem.clear();
		if (model.getSize() > 0) {
			dataGrid.setSelectedIndex(0);
			sendSelEvt();
		}

	}

	// Omitted...
	public TrPay getCurrent() {
		return current;
	}

	public void setCurrent(TrPay current) {
		this.current = current;
	}

	public void authomat_step(Cur_cont cur_cont) {

		un = cur_cont.un;
		pwd = cur_cont.pwd;
		alias = cur_cont.alias;
		TrPay trpay1 = cur_cont.trpay1;
		int actid = cur_cont.actid;

		Connection c = null;
		try {
			c = ConnectionPool.getConnection(un, pwd, alias);
			c.setAutoCommit(false);
			Res rs = TrPayService.doAction(un, pwd, trpay1, actid, alias, c);

			String oper_desc = TrPayService.getOperation_desc(
					cur_cont.operation_id, alias);
			String log = "Операция [" + oper_desc + "] id [" + trpay1.getId()
					+ "] действие [" + cur_cont.action_desc + "] подгруппы ["
					+ TrPayService.getDeal_desc(trpay1.getDeal_id(), alias)
					+ "] No карты [" + trpay1.getPan() + "] счет карты ["
					+ trpay1.getCard_acc() + "]";

			UserService.UsrLog(new UserActionsLog(uid, uname, curip, 5, 1, log,
					branch1), alias);

			if (rs.getCode() < 0) {
				alert(rs.getName());
			} else {
				TrPay tp1 = trpay1;
				if (TrPayService.isEndState(tp1.getId(), alias, c)) {
					// if (send2t(trpay1)) {
					if (send2way(trpay1)) {
						c.commit();
					} else
						c.rollback();
				} else
					c.commit();

				refreshModel(_startPageNumber);
				// onClick$btn_filter_act();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
	}

	public void onDoubleClick$dataGrid$grd() {
		grd.setVisible(false);
		frm.setVisible(true);
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		dfgrd.setVisible(false);
		btn_back.setImage("/images/folder.png");
		btn_back.setLabel(Labels.getLabel("grid"));
		amount.setValue(nf.format(current.getAmount() / 100));
		
		if ( tbfrm.getSelectedIndex()==1 ) {
			  paytmpGrid.setModel(new BindingListModelList(TrPayService.getTrPayDocs(
						current.getId(), alias), false));
			}

		if ( tbfrm.getSelectedIndex()==2 ) { 
			documentsGrid.setModel(new BindingListModelList(TrPayService
					.getPaydocs(current.getId(), alias), false));
		}

	}

	public void onClick$btn_back() {
		if (frm.isVisible()) {
			frm.setVisible(false);
			grd.setVisible(true);
			btn_back.setImage("/images/file.png");
			btn_back.setLabel(Labels.getLabel("back"));
		} else
			onDoubleClick$dataGrid$grd();
	}

	public void onClick$btn_first() {
		dataGrid.setSelectedIndex(0);
		sendSelEvt();
	}

	public void onClick$btn_last() {
		dataGrid.setSelectedIndex(model.getSize() - 1);
		sendSelEvt();
	}

	public void onClick$btn_prev() {
		if (dataGrid.getSelectedIndex() != 0) {
			dataGrid.setSelectedIndex(dataGrid.getSelectedIndex() - 1);
			sendSelEvt();
		}
	}

	public void onClick$btn_next() {
		if (dataGrid.getSelectedIndex() != (model.getSize() - 1)) {
			dataGrid.setSelectedIndex(dataGrid.getSelectedIndex() + 1);
			sendSelEvt();
		}
	}

	private void sendSelEvt() {
		if (dataGrid.getSelectedIndex() == 0) {
			btn_first.setDisabled(true);
			btn_prev.setDisabled(true);
		} else {
			btn_first.setDisabled(false);
			btn_prev.setDisabled(false);
		}
		if (dataGrid.getSelectedIndex() == (model.getSize() - 1)) {
			btn_next.setDisabled(true);
			btn_last.setDisabled(true);
		} else {
			btn_next.setDisabled(false);
			btn_last.setDisabled(false);
		}
		SelectEvent evt = new SelectEvent("onSelect", dataGrid,
				dataGrid.getSelectedItems());
		Events.sendEvent(evt);
		if ( frm.isVisible() && tbfrm.getSelectedIndex()==1 ) { 
		  paytmpGrid.setModel(new BindingListModelList(TrPayService.getTrPayDocs(
				current.getId(), alias), false));
		}
		
		if ( frm.isVisible() && tbfrm.getSelectedIndex()==2 ) { 
			documentsGrid.setModel(new BindingListModelList(TrPayService
					.getPaydocs(current.getId(), alias), false));
		}
	}
	
	public void onSelect$tbfrm() {
		
		if ( tbfrm.getSelectedIndex()==1 ) { 
			  paytmpGrid.setModel(new BindingListModelList(TrPayService.getTrPayDocs(
					current.getId(), alias), false));
			}

		if ( tbfrm.getSelectedIndex()==2 ) { 
				documentsGrid.setModel(new BindingListModelList(TrPayService
						.getPaydocs(current.getId(), alias), false));
			}
		
		}

	public void onClick$btn_add() {
		onDoubleClick$dataGrid$grd();
		frmgrd.setVisible(false);
		addgrd.setVisible(true);
		dfgrd.setVisible(false);
	}

	public void onClick$btn_search() {
		onDoubleClick$dataGrid$grd();
		frmgrd.setVisible(false);
		addgrd.setVisible(false);
		dfgrd.setVisible(true);
	}

	public void onClick$btn_save() {
		try {
			filter.setParent_group_id(144);
			filter.setId(Long.parseLong(fid.getValue()));
			filter.setBranch(fbranch.getValue());
			filter.setOperation_id(Integer.parseInt(foperation_id.getValue()));
			filter.setAmount(Integer.parseInt(famount.getValue()));
			filter.setCard_acc(fcard_acc.getValue());
			filter.setCur_acc(fcur_acc.getValue());
			filter.setDate_created(fdate_created.getValue());
			filter.setParent_group_id(Integer.parseInt(fparentgroupid
					.getValue()));
			filter.setState(Integer.parseInt(fstate.getValue()));
			filter.setAccount_no(faccount_no.getValue());
			filter.setCl_name(fcl_name.getValue());

			onClick$btn_back();
			refreshModel(_startPageNumber);
			SelectEvent evt = new SelectEvent("onSelect", dataGrid,
					dataGrid.getSelectedItems());
			Events.sendEvent(evt);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void onClick$btn_cancel() {
		if (dfgrd.isVisible()) {
			filter = new TrPayFilter();
			filter.setParent_group_id(144);
		}
		onClick$btn_back();
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		dfgrd.setVisible(false);
		CheckNull.clearForm(addgrd);
		CheckNull.clearForm(fgrd);
		refreshModel(_startPageNumber);
	}

	public void onClick$btn_ref$refwnd() {
		refwnd.setVisible(false);
		authomat_step(cur_cont);
	}

	public void onClick$btn_cancel$refwnd() {
		refwnd.setVisible(false);
	}

	private boolean send2t(TrPay tp) {
		OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		RowType_ExecTransaction_Request parameters = new RowType_ExecTransaction_Request();
		OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();
		RowType_ExecTransaction_ResponseHolder details = new RowType_ExecTransaction_ResponseHolder();

		try {

			connectionInfo.setBANK_C("01");
			connectionInfo.setGROUPC("02");
			parameters.setBANK_C("01");
			parameters.setGROUPC("02");
			// parameters.setTRAN_TYPE(Integer.toString(TrPayService.get_tieto_operation_code(tp.getTieto_type(),
			// alias)));//tp.getOperation_id(), alias)));
			parameters.setTRAN_TYPE(Integer.toString(tp.getTieto_type()));// tp.getOperation_id(),
																			// alias)));
			parameters.setTRAN_CCY("USD");
			parameters.setPAYMENT_MODE("2");
			parameters.setTRAN_AMNT(BigDecimal.valueOf(tp.getAmount_t()/*
																		 * tp.
																		 * getAmount
																		 * ()
																		 */));
			parameters.setACCOUNT_NO(BigDecimal.valueOf(Integer.parseInt(tp
					.getAccount_no())));
			parameters.setCARD_ACCT(tp.getCard_acc());
			pp.executeTransaction(connectionInfo, parameters, responseInfo,
					details);
			if (responseInfo.value.getResponse_code().intValue() != 0) {
				alert(responseInfo.value.getError_action() + "\r\n"
						+ responseInfo.value.getError_description());
				return false;
			}
		} catch (Exception e) {
			alert(e.getMessage());
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private boolean send2way(TrPay tp) {
		/*
		 * popolnenie yoki spisanie qilish
		 * 
		 * way ga qilingan zaprosni (javobini) bf.log ga yozish, tablisaga ham
		 * yozish
		 */
		//boolean v_res = false;
		//String v_msg = "_";
		String v_xml = "_";

		// class yasaymiz, qiymatlar beramiz
		UFXMsgPayAcc payAcc = TrPayService.makePayAcc(
				tp, alias, wayRrnConstant, wayPaySourceContractNum);

		// classni xml stringga aylantiramiz
		XmlUtils xmlUtils = new XmlUtils();
		try {
			v_xml = xmlUtils.serializeXmlFromObject(payAcc);
			System.out.println("xmlUtils.serializeXmlFromObject(UFXMsgPayAcc): "
					+ v_xml);
		} catch (Exception e) {
			
			System.out.println("error serialize xml(UFXMsgPayAcc) "
					+ e.getMessage());
			//v_msg = v_msg + "\nError serialize xml(addContract) "
			//		+ e.getMessage();
			alert ("error serialize xml(UFXMsgPayAcc) "
					+ e.getMessage());
			return false;
		}
		ISLogger.getLogger().error("postUtils.sendData v_xml : " + v_xml);
		
		// post zapros qilamiz
		// zapros qilib javobini olamiz
		PostUtils postUtils = new PostUtils();
		String v_res2 = "";
		try {
			v_res2 = postUtils.sendData(openwayEndpoint, v_xml);
		} catch (Exception e) {
			System.out.println("postUtils.sendData err " + e.getMessage());
			//v_msg = v_msg + "\nError postUtils.sendData " + e.getMessage();
			ISLogger.getLogger().error("postUtils.sendData err " + e.getMessage());
			alert("postUtils.sendData err " + e.getMessage());
			return false;
		}
		
		System.out.println("postUtils.sendData v_res2: " + v_res2);
		ISLogger.getLogger().error("postUtils.sendData v_res2: " + v_res2);

		// kelgan javobdagi xml-stringdan java object (klass) yasaymiz
		UFXMsgPayAccResp clResp = null;
		XmlMapper xmlMapper = new XmlMapper();
		try {
			clResp = xmlMapper
					.readValue(v_res2, UFXMsgPayAccResp.class);
		} catch (Exception e) {
			ISLogger.getLogger().error("xmlMapper.readValue err (UFXMsgPayAccResp): "
					+ e.getMessage());
			ISLogger.getLogger().error("xmlMapper.readValue v_res2 (UFXMsgPayAccResp): "
					+ v_res2);
			alert("xmlMapper.readValue err (UFXMsgPayAccResp): "
					+ e.getMessage());
			return false;
		}
		
		// uspeshno bulsa davom etamiz, xato bulsa xatoni kursatamiz va return
		// qilamiz

		if (clResp.getResp_code() == "0" || clResp.getResp_code().equals("0")) { /*--success*/
			//logga yozamiz
			//int user_id, String user_name, String ip_address,
			//int act_type, String branch, String query_body, String resp_body
			int actType;
			if (tp.getTieto_type()==110)
				actType=1;
			else
				actType=2;
				
			UserService.WayQueryLog(new UserActionsLog(uid, uname, curip, actType, branch1, v_xml,
					v_res2), alias);
			return true;
		} else {
			//v_msg = v_msg
			//		+ "\nОшибка пополнения/списания в Openway: "
			//		+ clResp.getResp_text();
			alert("\nОшибка пополнения/списания в Openway: "
					+ clResp.getResp_text());
			return false;
		}
		
		//return true;
	}

	public void onSelect$filter_box() {
		
		//filter = new TrPayFilter();
		String f_str = filter_box.getValue();
		int k = f_str.lastIndexOf('v');
		//int j = f_str.lastIndexOf('j');
		// di =
		String st = f_str.substring(0, k);
		//String di = f_str.substring(k + 1, j);
		//String oper = f_str.substring(j + 1);
		filter.setState(Integer.parseInt(st));
		filter.setParent_group_id(144);
		//filter.setOperation_id(Integer.parseInt(oper));
		//filter.setDeal_id(Integer.parseInt(di));
		//if (access > 0)
			filter.setBranch((String) session.getAttribute("branch"));
		refreshModel(_startPageNumber);
	}

	public void onSelect$filter_box_oper() {
		//filter = new TrPayFilter();
		String oper = filter_box_oper.getValue();
		filter.setParent_group_id(144);
		filter.setOperation_id(Integer.parseInt(oper));
		//filter.setDeal_id(Integer.parseInt(di));
		//if (access > 0)
			filter.setBranch((String) session.getAttribute("branch"));
		refreshModel(_startPageNumber);
	}
	
	public void onClick$btn_filter_act() {
		show_active = true;
		filter.setParent_group_id(144);
		filter.setShow_act(true);
		filter.setU_br(branch1);
		filter.setU_id(uid);
		refreshModel(_startPageNumber);
	}

	public void onClick$btn_r_filter_act() {
		show_active = false;
		filter.setShow_act(false);
		filter.setParent_group_id(144);
		refreshModel(_startPageNumber);
	}

	public void onChange$fl_date() {
		filter.setDate_created(fl_date.getValue());
		filter.setParent_group_id(144);
		refreshModel(_startPageNumber);
	}

	public void onClick$btn_print_selected() {
		if (to_print_mem.size() < 1) {
			alert("Ничего не выбрано");
			return;
		}
		List<Long> res = new ArrayList();
		Iterator it = to_print_mem.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry) it.next();
			res.add((Long) pairs.getKey());
		}
		Collections.sort(res);

		String s_res = "";

		for (int i = 0; i < res.size() - 1; i++) {
			s_res += res.get(i) + ";";
		}

		s_res += res.get(res.size() - 1);

		HashMap<String, Object> params = new HashMap<String, Object>();
		System.out.println("REPORT BRANCH:" + branch1);
		System.out.println("REPORT alias:" + alias);
		System.out.println("REPORT ID:" + s_res);

		params.put("P_PAYID", s_res);
		params.put("P_BRANCH", branch1);
		//if (branch1.equals(ConnectionPool.getValue("HO", alias)))
			printwnd$rpframe.setContent(DPrint.getRepPdf(un, pwd,
					"TI_MEMORDER.pdf",
					application.getRealPath("reports/TI_MEMORDER.jasper"),
					params, alias));
		//else
		//	printwnd$rpframe.setContent(DPrint.getRepPdf(un, pwd,
		//			"TI_MEMORDER.pdf",
		//			application.getRealPath("reports/TI_MEMORDER_FIL.jasper"),
		//			params, alias));
		printwnd.setVisible(true);
	}

	public void onClick$btn_print_all() {
		HashMap<String, Object> params = new HashMap<String, Object>();
		System.out.println("REPORT BRANCH:" + branch1);
		params.put("P_BRANCH", branch1);
		//if (branch1.equals(ConnectionPool.getValue("HO", alias)))
			printwnd$rpframe.setContent(DPrint.getRepPdf(un, pwd,
					"TI_MEMORDER.pdf",
					application.getRealPath("reports/TI_MEMORDER_ALL.jasper"),
					params, alias));
		//else
		//	printwnd$rpframe.setContent(DPrint.getRepPdf(un, pwd,
		//			"TI_MEMORDER.pdf", application
		//					.getRealPath("reports/TI_MEMORDER_ALL_FIL.jasper"),
		//			params, alias));
		printwnd.setVisible(true);
	}

	public void onClick$btn_print_unprinted() {
		HashMap<String, Object> params = new HashMap<String, Object>();

		params.put("P_PAYID",
				TrPayService.get_all_unprinted_by_day(branch1, alias));
		params.put("P_BRANCH", branch1);
		printwnd$rpframe.setContent(DPrint.getRepPdf(un, pwd,
				"TI_MEMORDER.pdf",
				application.getRealPath("reports/TI_MEMORDER.jasper"), params,
				alias));

		printwnd.setVisible(true);
	}

	public void onClick$btn_refresh() {
		//onClick$btn_filter_act();
		refreshModel(_startPageNumber);
	}

	public void onClick$nnmb_h() {
		// alert("clicked!!!1111111111111");
		sorted_desc = !sorted_desc;
		refreshModel(_startPageNumber);
	}

	public void onClick$btn_print_duplicate() {
		// alert(txt_duplicate.getValue());
		// Ti_Duplicate duplicate = AccPayService.getDuplicate(
		// Integer.parseInt(txt_duplicate.getValue()), alias);
		// printp(duplicate, "reports/TI_KREDIT_OPERATOR.jasper");
		txt_duplicate.setValue("");
	}

	public void onClick$btn_print_duplicate_d() {
		// alert(txt_duplicate_d.getValue());
		// Ti_DuplicateDebit duplicate_debit = AccPayService.getDuplicateDebit(
		// Integer.parseInt(txt_duplicate_d.getValue()), alias);
		// printp2(duplicate_debit, "reports/TI_DEBIT_OPERATOR.jasper");
		txt_duplicate_d.setValue("");
	}

	public void printp(Ti_Duplicate duplicate, String report_file) {

		printwnd.setVisible(true);

		JasperPrint jasperPrint;
		AMedia repmd = null;
		HashMap<String, Object> params = new HashMap<String, Object>();

		params.put("V_DATE", duplicate.getV_date());
		params.put("INCLIENT_NAME1", duplicate.getInclient_name1());
		params.put("INCLIENT_NAME2", "");
		params.put("INCLIENT_NAME3", "");
		params.put("T_CURRENCY", "USD");
		params.put("POST_ADDRESS", duplicate.getPost_address());
		params.put("SUMMA6", duplicate.getSumma6());
		params.put("ESUMMA6", duplicate.getEsumma6());
		params.put("PSUMMA6", duplicate.getPsumma6());
		params.put("UZPSUMMA6", duplicate.getUzpsumma6());
		params.put("UZPESUMMA6", duplicate.getUzpesumma6());
		params.put("BANK_NAME", duplicate.getBank_name());
		params.put("NAME2", duplicate.getName2());
		params.put("KARTA_NUM", duplicate.getKarta_num());// 621045CUTZQH6018
		params.put("MY_BRANCH", duplicate.getMy_branch());
		params.put("SEQ", duplicate.getId());

		Connection c = null;
		try {
			c = ConnectionPool.getConnection(alias);
			jasperPrint = JasperFillManager.fillReport(
					application.getRealPath(report_file), params, c);
			jasperPrint.setLeftMargin(0);
			jasperPrint.setRightMargin(0);
			jasperPrint.setTopMargin(0);
			jasperPrint.setBottomMargin(0);
			jasperPrint.setPageHeight(595);// (461);
			jasperPrint.setPageWidth(842);// (652);
			jasperPrint.setOrientation(OrientationEnum.LANDSCAPE);
			final byte[] buf = JasperRunManager.runReportToPdf(
					application.getRealPath(report_file), params, c);
			final java.io.InputStream mediais = new ByteArrayInputStream(buf);

			repmd = new AMedia("Report.pdf", "pdf", "application/pdf", mediais);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (repmd != null) {
			printwnd$rpframe.setContent(repmd);

		}
	}

	public void printp2(Ti_DuplicateDebit duplicate_debit, String report_file) {

		printwnd.setVisible(true);

		JasperPrint jasperPrint;
		AMedia repmd = null;
		HashMap<String, Object> params = new HashMap<String, Object>();

		params.put("V_DATE", duplicate_debit.getV_date());
		params.put("INCLIENT_NAME1", duplicate_debit.getInclient_name1());
		params.put("INCLIENT_NAME2", "");
		params.put("INCLIENT_NAME3", "");
		params.put("T_CURRENCY", "USD");
		params.put("POST_ADDRESS", duplicate_debit.getPost_address());
		params.put("SUMMA6", duplicate_debit.getSumma6());
		params.put("ESUMMA6", duplicate_debit.getEsumma6());
		params.put("PSUMMA6", duplicate_debit.getPsumma6());
		params.put("UZPSUMMA6", duplicate_debit.getUzpsumma6());
		params.put("UZPESUMMA6", duplicate_debit.getUzpesumma6());
		params.put("BANK_NAME", duplicate_debit.getBank_name());
		params.put("NAME2", duplicate_debit.getName2());
		params.put("KARTA_NUM", duplicate_debit.getKarta_num());// 621045CUTZQH6018
		params.put("MY_BRANCH", duplicate_debit.getMy_branch());
		params.put("SEQ", duplicate_debit.getId());

		Connection c = null;
		try {
			c = ConnectionPool.getConnection(alias);
			jasperPrint = JasperFillManager.fillReport(
					application.getRealPath(report_file), params, c);
			jasperPrint.setLeftMargin(0);
			jasperPrint.setRightMargin(0);
			jasperPrint.setTopMargin(0);
			jasperPrint.setBottomMargin(0);
			jasperPrint.setPageHeight(595);// (461);
			jasperPrint.setPageWidth(842);// (652);
			jasperPrint.setOrientation(OrientationEnum.LANDSCAPE);
			final byte[] buf = JasperRunManager.runReportToPdf(
					application.getRealPath(report_file), params, c);
			final java.io.InputStream mediais = new ByteArrayInputStream(buf);

			repmd = new AMedia("Report.pdf", "pdf", "application/pdf", mediais);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (repmd != null) {
			printwnd$rpframe.setContent(repmd);

		}
	}
	
	public static void setStates() {
		if (states==null || states.size()==0) {
			List<RefData> list = new LinkedList<RefData>();
			//iterate _trState
			for(Map.Entry<String, String> entry : _trState.entrySet()) {
			    String key = entry.getKey();
			    String value = entry.getValue();
			    if ( !value.trim().equals("Неопределен") ) {
			      list.add(new RefData(key, value));
			    }
			}
			states=list;	
		}
	}

}
