package com.is.qr_online.payee;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Constraint;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Image;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.api.South;
import org.zkoss.zul.event.PagingEvent;

//import com.is.SwiftBuffer.SwiftBufferService;
//import com.is.SwiftBuffer.Transliterator;
import com.is.ISLogger;
import com.is.qr_online.merchant.MerchantService;
import com.is.qr_online.send.RegQRClient.AnswerRegQRClient;
import com.is.qr_online.send.RegQRClient.RegQRClient;
import com.is.qr_online.send.RegQRClient.RequestQRClient;
import com.is.qr_online.send.SetClientPhone.AnswerSetClientPhone;
import com.is.qr_online.send.SetClientPhone.RequestClientPhone;
import com.is.qr_online.send.SetClientPhone.SetClientPhone;
import com.is.utils.CheckNull;
import com.is.utils.RefData;

public class PayeeViewCtrl extends GenericForwardComposer {

	private static final long serialVersionUID = -7332560912464063891L;

	private Div frm;
	private Listbox dataGrid;
	private Div grd;
	private Grid addgrd, frmgrd, fgrd;
	private Toolbarbutton btn_back;
	private Toolbarbutton btn_update;
	private Textbox inn, account, id, chinnwnd$client_id, chaccwnd$aacc, addname;
	private Textbox addinn, addaccount, mobphone,mobphone_upd;
	private Textbox finn, faccount;
	private Image sort_inn_down, sort_account_down;
	private Paging payeePaging;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	private Window chaccwnd, chinnwnd,chaccwndupd;
	private Listbox chaccwnd$acc, chinnwnd$inn,chaccwndupd$upacc;
	private String alias, branch;

	public PayeeFilter filter;

	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");

	private Payee current = new Payee();

	public PayeeViewCtrl() {
		super('$', false, false);
	}

	private RefData currentacc = new RefData("", "");
	private RefData currentinn = new RefData("", "");

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		// TODO Auto-generated method stub
		binder = new AnnotateDataBinder(comp);
		binder.bindBean("current", this.current);
		binder.loadAll();
		String[] parameter = (String[]) param.get("ht");
		if (parameter != null) {
			_pageSize = Integer.parseInt(parameter[0]) / 36;
			dataGrid.setRows(Integer.parseInt(parameter[0]) / 36);
		}

		alias = (String) session.getAttribute("alias");
		branch = (String) session.getAttribute("branch");
		dataGrid.setItemRenderer(new ListitemRenderer() {

			@Override
			public void render(Listitem row, Object data) throws Exception {
				// TODO Auto-generated method stub
				Payee pPayee = (Payee) data;
				row.setValue(pPayee);
				row.appendChild(new Listcell(pPayee.getInn()));
				row.appendChild(new Listcell(pPayee.getAccount()));
				row.appendChild(new Listcell(pPayee.getName()));
				row.appendChild(new Listcell(pPayee.getMark_RegQRClient() == null ? "-" : "+"));
				row.appendChild(new Listcell(pPayee.getMark_ClientPhone() == null ? "-" : "+"));
			}
		});

		chaccwnd$acc.setItemRenderer(new ListitemRenderer() {
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception {
				RefData pRefData = (RefData) data;
				row.setValue(pRefData);
				row.appendChild(new Listcell(pRefData.getData()));
				row.appendChild(new Listcell(pRefData.getLabel()));

			}
		});
		chinnwnd$inn.setItemRenderer(new ListitemRenderer() {
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception {
				RefData pRefData = (RefData) data;
				row.setValue(pRefData);
				row.appendChild(new Listcell(pRefData.getData()));
				row.appendChild(new Listcell(pRefData.getLabel()));

			}
		});
		
		btn_back.setVisible(false);
		settings();
		refreshModel(_startPageNumber);
	}

	public void onPaging$payeePaging(ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}

	private void refreshModel(int activePage) {
		payeePaging.setPageSize(_pageSize);
		ISLogger.getLogger().error("alias_paging: "+alias);
		//System.out.println("alias_paging1: "+alias);
		model = new PagingListModel(activePage, _pageSize, filter, alias);

		if (_needsTotalSizeUpdate) {
			_totalSize = model.getTotalSize(filter,alias);
			_needsTotalSizeUpdate = false;
		}

		payeePaging.setTotalSize(_totalSize);

		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0) {
			this.current = (Payee) model.getElementAt(0);
			sendSelEvt();
		}
	}

	// Omitted...
	public Payee getCurrent() {
		return current;
	}

	public void setCurrent(Payee current) {
		this.current = current;
	}

	public RefData getCurrentacc() {
		return currentacc;
	}

	public void setCurrentacc(RefData currentacc) {
		this.currentacc = currentacc;
	}

	public RefData getCurrentinn() {
		return currentinn;
	}

	public void setCurrentinn(RefData currentinn) {
		this.currentinn = currentinn;
	}

	public void onDoubleClick$dataGrid$grd() {
		if (dataGrid.getSelectedItem() != null) {
			HashMap<String, Object> arguments = new HashMap<String, Object>();
			arguments.put("id", current.getInn());
			String template = "/qr_merchant.zul";
			Window window = (Window) Executions.createComponents(template, null, arguments);
			try {
				window.doModal();
			} catch (SuspendNotAllowedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			window.setClosable(true);
		}
	}
	
	public void onClick$btn_sets(){
		if(dataGrid.getSelectedItem() !=null){
 HashMap<String, Object> arguments1 = new HashMap<String, Object>();
    arguments1.put("id", current.getInn());		
    String template = "/qr_leadsets.zul";
	Window window = (Window) Executions.createComponents(template, null, arguments1);
	try {
		window.doModal();
	} catch (SuspendNotAllowedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	window.setClosable(true);	
					
		}
		else{
		alert("выберите клиента для настройка!!!");	
			
			
		}
			
	}
	
	
	public void onClick$btn_update() {

		if (dataGrid.getSelectedItem() != null) {
			grd.setVisible(false);
			frm.setVisible(true);
			frmgrd.setVisible(true);
			addgrd.setVisible(false);
			fgrd.setVisible(false);
			btn_back.setVisible(true);
			btn_update.setVisible(false);

			id.setValue(current.getInn());
			System.out.println(id.getValue());
		}
	}
  
	
	public void onClick$btn_back() {
		if (frm.isVisible()) {
			frm.setVisible(false);
			grd.setVisible(true);
			btn_update.setVisible(true);
			btn_back.setVisible(false);

		} else {
			grd.setVisible(true);
			frm.setVisible(false);
			frmgrd.setVisible(true);
			addgrd.setVisible(false);
			fgrd.setVisible(false);
			btn_back.setVisible(false);
			btn_update.setVisible(true);
		}
	}

	private void sendSelEvt() {
		SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
		Events.sendEvent(evt);
	}

	public void onClick$btn_search() {
		grd.setVisible(false);
		frm.setVisible(true);
		btn_back.setVisible(true);

		frmgrd.setVisible(false);
		addgrd.setVisible(false);
		fgrd.setVisible(true);
	}

	public void onClick$btn_save() {
		try {
			boolean ifTrue = true;
			if (addgrd.isVisible()) {
				boolean isEmpty = false;
				isEmpty = isEmpty(addinn.getValue(), addaccount.getValue(),mobphone.getValue());
				if (!isEmpty) {
					alert("Не заполнены необходимые параметры");
					ifTrue = false;
				} else {

					PayeeService.create(new Payee(branch, addinn.getValue(), addaccount.getValue(), addname.getValue(),
							mobphone.getValue(), null, null));
					CheckNull.clearForm(addgrd);
					frmgrd.setVisible(true);
					addgrd.setVisible(false);
					fgrd.setVisible(false);
					ifTrue = true;
				}
			} else if (fgrd.isVisible()) {
				filter = new PayeeFilter();
				filter.setInn(finn.getValue());
				filter.setAccount(faccount.getValue());
			} else {
				boolean isEmpty = false;
				isEmpty = isEmpty(inn.getValue(), account.getValue());
				if (!isEmpty) {
					alert("Не заполнены необходимые параметры");
					ifTrue = false;
				} else {

					current.setInn(inn.getValue());
					current.setAccount(account.getValue());
					current.setMobile_phone(mobphone_upd.getValue());
					boolean ifUpdate = PayeeService.update(current);
					if (ifUpdate) {
						MerchantService.update(current.getInn(), current.getInn());
					}
					ifTrue = true;
				}
			}
			if (ifTrue) {
				onClick$btn_back();
				refreshModel(_startPageNumber);
				SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
				Events.sendEvent(evt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void onClick$btn_send_QRClient() {
		if (dataGrid.getSelectedItem() == null) {
			alert("клиент не выбран");

		} else {
			try {
				RegQRClient rqc = new RegQRClient();
				rqc.setInn(current.getInn());
				System.out.println("regqr INN:" + current.getInn());
				rqc.setPhone(current.getMobile_phone());
				System.out.println("req qr Phone: " + current.getMobile_phone());
				rqc.setDsign("");
				// RequestQRClient RQrClient=new RequestQRClient();
				String docn = PayeeService.getDocnum(current.getInn(), current.getBranch());

				AnswerRegQRClient answer = RequestQRClient.getRegQRClient(rqc, docn);
				String message = answer.getMessage();
				String code1 = answer.getCode();
				PayeeService.updateResRegQrClient(current.getInn(), current.getBranch(), code1);
				if (code1.equals("12000")) {
					alert(message + "\n" + code1);
				} else {
					alert("Ошибка при регистрация :" + message + "\n" + code1);
				}

			} catch (Exception e) {
				alert(e.getMessage());
				e.printStackTrace();
				ISLogger.getLogger().error("oshibka pri reg_RegQRClient: " + e.getMessage());
			}

		}
	}

	public void onClick$btn_send_ClientPhone() {
		if (dataGrid.getSelectedItem() == null) {
			alert("клиент не выбран");

		} else {
			try {
				SetClientPhone sphone = new SetClientPhone();
				sphone.setInn(current.getInn());
				sphone.setPhone(current.getMobile_phone());
				sphone.setDsign("");
				String docp = PayeeService.getDocnum(current.getInn(), current.getBranch());
				AnswerSetClientPhone answer = RequestClientPhone.getClientPhone(sphone, docp);
				String message = answer.getMessage();
				String code = answer.getCode();
				PayeeService.updateResClientPhone(current.getInn(), current.getBranch(), code);
				if (code.equals("12000")) {
					alert(message + "\n" + code);
				} else {
					alert("Ошибка при регистрация :" + message + "\n" + code);
				}
			} catch (Exception e) {
				alert(e.getMessage());
				e.printStackTrace();
				ISLogger.getLogger().error("oshibka pri reg_SetClientPhone: " + e.getMessage());

			}

		}

	}

	public void onClick$btn_cancel() {
		if (fgrd.isVisible()) {
			filter = new PayeeFilter();
		}
		onClick$btn_back();
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		CheckNull.clearForm(addgrd);
		CheckNull.clearForm(fgrd);
		refreshModel(_startPageNumber);
	}

	public void settings() {
		inn.setMaxlength(9);
		account.setMaxlength(20);
		addinn.setMaxlength(9);
		addaccount.setMaxlength(20);
		finn.setMaxlength(9);
		faccount.setMaxlength(20);
	}

	public static boolean isEmpty(String... args) {
		boolean isEmpty = true;
		for (String str : args) {
			if (str == null || str.length() == 0) {
				isEmpty = false;
			}
		}
		return isEmpty;
	}

	public void onClick$btn_add() {
		grd.setVisible(false);
		frm.setVisible(true);
		frmgrd.setVisible(false);
		addgrd.setVisible(true);
		fgrd.setVisible(false);
		btn_back.setVisible(true);
		btn_update.setVisible(false);
		addname.setValue("");

	}

	public void onClick$sort_inn_down() {
		if (sort_inn_down.getSrc().equals("/images/down3.png")) {
			sort_inn_down.setSrc("/images/up4.png");
			sort_inn_down.setId("sort_inn_up");
			System.out.println(PayeeService.orderBy(1, "inn"));
			refreshModel(_startPageNumber);
		} else {
			sort_inn_down.setSrc("/images/down3.png");
			sort_inn_down.setId("sort_inn_down");
			System.out.println(PayeeService.orderBy(2, "inn"));
			refreshModel(_startPageNumber);
		}
	}

	public void onClick$sort_account_down() {
		if (sort_account_down.getSrc().equals("/images/down3.png")) {
			sort_account_down.setSrc("/images/up4.png");
			sort_account_down.setId("sort_account_up");
			System.out.println(PayeeService.orderBy(1, "account"));
			refreshModel(_startPageNumber);
		} else {
			sort_account_down.setSrc("/images/down3.png");
			sort_account_down.setId("sort_account_down");
			System.out.println(PayeeService.orderBy(2, "account"));
			refreshModel(_startPageNumber);
		}
	}

	public void onCtrlKey$addinn(Event event) {
		chinnwnd$inn.setModel(new BindingListModelList(com.is.utils.RefDataService.getRefData(
				"select c.number_tax_registration data, id||' '||name label   from client_j c where c.number_tax_registration=?",
				addinn.getValue(), alias), false));
		System.out.println("alias:" + alias + ", branch :" + branch + ", id" + chinnwnd$client_id.getValue() + ", inn"
				+ addinn.getValue());
		chinnwnd.setVisible(true);
	}
	

	public void onCtrlKey$addaccount(Event event) {
		chaccwnd$acc.setModel(new BindingListModelList(com.is.utils.RefDataService.getRefData(
				"select a.id data, a.name label from account a where exists(select 'x' from client_j c where a.branch=c.branch and a.client=c.id and c.number_tax_registration=?) and SUBSTR(a.id,1,8)='23520000' ",
				addinn.getValue(), alias), false));
		chaccwnd.setVisible(true);
	}
	/*  and SUBSTR(a.id,1,8)='23520000'*/
	/*public void onCtrlKey$account(Event event){
		try{
			System.out.println("innRRRR: "+inn.getValue());
			chaccwndupd$upacc.setModel(new BindingListModelList(com.is.utils.RefDataService.getRefData(
				"select a.id data, a.name label from account a where exists(select 'x' from client_j c where a.branch=c.branch and a.client=c.id and c.number_tax_registration=?)",
				inn.getValue(), alias), false));
		System.out.println("innRRRR4444: "+inn.getValue());
		chaccwnd.setVisible(true);
		}
		catch(Exception e){
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			System.out.println(CheckNull.getPstr(e));
		}
		
	}*/

	public void onDoubleClick$inn$chinnwnd() {
		addinn.setValue(currentinn.getData());
		addname.setValue(currentinn.getLabel());
		chinnwnd.setVisible(false);
	}

	public void onDoubleClick$acc$chaccwnd() {
		addaccount.setValue(currentacc.getData());
		chaccwnd.setVisible(false);
	}

	public void onClick$okbtn$chinnwnd() {
		System.out.println("client  " + chinnwnd$client_id.getValue());
		chinnwnd$inn.setModel(new BindingListModelList(com.is.utils.RefDataService
				// .getRefData("select c.number_tax_registration data, id||'
				// '||name label from client_j c where id=?",
				.getRefData("select c.number_tax_registration data, name label   from client_j c where id=?",
						chinnwnd$client_id.getValue(), alias),
				false));

	}

	public void onClick$okbtn$chaccwnd() {
		System.out.println("inn  " + addinn.getValue());
		chaccwnd$acc.setModel(new BindingListModelList(com.is.utils.RefDataService.getRefData(
				"select a.id data, a.name label from account a where exists(select 'x' from client_j c where a.branch=c.branch and a.client=c.id and c.number_tax_registration=? ) and SUBSTR(a.id,1,8)='23520000' ",
				addinn.getValue(), alias), false));

	}

}
/*  */