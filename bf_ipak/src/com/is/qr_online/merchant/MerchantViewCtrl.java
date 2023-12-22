package com.is.qr_online.merchant;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
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
import org.zkoss.zul.event.PagingEvent;

import com.is.ISLogger;
import com.is.qr_online.registr.References;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefDataService;

public class MerchantViewCtrl extends GenericForwardComposer {

	private static final long serialVersionUID = -7007290898996568578L;

	private Window merchantmain;
	private Label payee_inn;
	private RefCBox activity, country;
	private RefCBox aactivity, acountry;
	private RefCBox factivity, fcountry;

	private Div frm;
	private Listbox dataGrid;
	private Div grd;
	private Grid addgrd, frmgrd, fgrd;
	private Toolbarbutton btn_back;
	private Toolbarbutton btn_update;
	private Textbox name, city, postal_code, phone_number, email;
	private Textbox aname, acity, apostal_code, aphone_number, aemail;
	private Textbox fname, fcity, fpostal_code, fphone_number, femail;
	private Paging merchantPaging;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;

	public MerchantFilter filter;

	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");

	private Merchant current = new Merchant();
	private String alias;
	
	public MerchantViewCtrl() {
		super('$', false, false);
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		filter = new MerchantFilter();
		super.doAfterCompose(comp);
		// TODO Auto-generated method stub
		binder = new AnnotateDataBinder(comp);
		binder.bindBean("current", this.current);
		binder.loadAll();
		alias = (String) session.getAttribute("alias");
		filter.setPayee_id(payee_inn.getValue());
		System.out.println("Payee Id ==  " + filter.getPayee_id());
		String[] parameter = (String[]) param.get("ht");
		if (parameter != null) {
			_pageSize = Integer.parseInt(parameter[0]) / 36;
			dataGrid.setRows(Integer.parseInt(parameter[0]) / 36);
		}

		dataGrid.setItemRenderer(new ListitemRenderer() {

			@Override
			public void render(Listitem row, Object data) throws Exception {
				// TODO Auto-generated method stub
				Merchant pMerchant = (Merchant) data;

				row.setValue(pMerchant);
				row.appendChild(new Listcell(pMerchant.getPayee_id()));
				row.appendChild(new Listcell(References.ss_qr_payment_subject(pMerchant.getActivity())));
				row.appendChild(new Listcell(pMerchant.getName()));
				row.appendChild(new Listcell(References.s_str(pMerchant.getCountry())));
				row.appendChild(new Listcell(pMerchant.getCity()));
				row.appendChild(new Listcell(pMerchant.getPostal_code()));
				row.appendChild(new Listcell(pMerchant.getPhone_number()));
				row.appendChild(new Listcell(pMerchant.getEmail()));
			}
		});

		btn_back.setVisible(false);

		activity.setModel(new ListModelList(
				RefDataService.getRefData("select s.id data, s.name label from ss_qr_payment_subject s", alias)));
		country.setModel(new ListModelList(
				RefDataService.getRefData("select s.char_code data, s.name label from s_str s order by s.name",alias)));

		aactivity.setModel(new ListModelList(
				RefDataService.getRefData("select s.id data, s.name label from ss_qr_payment_subject s",alias)));
		acountry.setModel(new ListModelList(
				RefDataService.getRefData("select s.char_code data, s.name label from s_str s order by s.name",alias)));

		factivity.setModel(new ListModelList(
				RefDataService.getRefData("select s.id data, s.name label from ss_qr_payment_subject s",alias)));
		fcountry.setModel(new ListModelList(
				RefDataService.getRefData("select s.char_code data, s.name label from s_str s order by s.name",alias)));

		activity.setValue(References.ss_qr_payment_subject(current.getActivity()));

		refreshModel(_startPageNumber);

	}

	public void onPaging$merchantPaging(ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}

	private void refreshModel(int activePage) {
		merchantPaging.setPageSize(_pageSize);
		ISLogger.getLogger().error("alias_paging: "+alias);
		model = new PagingListModel(activePage, _pageSize, filter, alias);

		if (_needsTotalSizeUpdate) {
			_totalSize = model.getTotalSize(filter,alias);
			_needsTotalSizeUpdate = false;
		}

		merchantPaging.setTotalSize(_totalSize);

		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0) {
			this.current = (Merchant) model.getElementAt(0);
			sendSelEvt();
		}
	}

	// Omitted...
	public Merchant getCurrent() {
		return current;
	}

	public void setCurrent(Merchant current) {
		this.current = current;
	}

	public void onDoubleClick$dataGrid$grd() {
		if(dataGrid.getSelectedItem() != null) {
			HashMap<String, Object> arguments = new HashMap<String, Object>();
			arguments.put("id", current.getId());
			String template = "/qr_transaction.zul";
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

	public void onClick$btn_update() {

		if (dataGrid.getSelectedItem() != null) {
			grd.setVisible(false);
			frm.setVisible(true);
			frmgrd.setVisible(true);
			addgrd.setVisible(false);
			fgrd.setVisible(false);
			btn_back.setVisible(true);
			btn_update.setVisible(false);

			activity.setValue(References.ss_qr_payment_subject(current.getActivity()));
			country.setValue(References.s_str(current.getCountry()));
		}
	}

	public void onClick$btn_back() {
		if (frm.isVisible()) {
			frm.setVisible(false);
			grd.setVisible(true);
			btn_back.setVisible(false);
			btn_update.setVisible(true);
		} else {
			grd.setVisible(false);
			frm.setVisible(true);
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
		// onClick$btn_update();
		grd.setVisible(false);
		frm.setVisible(true);
		btn_back.setVisible(true);

		frmgrd.setVisible(false);
		addgrd.setVisible(false);
		fgrd.setVisible(true);
		btn_update.setVisible(true);
	}

	public void onClick$btn_save() {
		try {
			boolean ifTrue = true;
			if (addgrd.isVisible()) {
				boolean isEmpty = false;
				isEmpty = isEmpty(aactivity.getValue(), aname.getValue(), acountry.getValue(), acity.getValue());
				if (!isEmpty) {
					alert("Не заполнены необходимые параметры");
					ifTrue = false;
				} else {
					MerchantService.create(new Merchant(payee_inn.getValue(), aactivity.getValue(), aname.getValue(),
							acountry.getValue().substring(0, 2), acity.getValue(), apostal_code.getValue(),
							aphone_number.getValue(), aemail.getValue()));
					CheckNull.clearForm(addgrd);
					frmgrd.setVisible(true);
					addgrd.setVisible(false);
					fgrd.setVisible(false);
					System.out.println("save new merchant");
					ifTrue = true;
				}
			} else if (fgrd.isVisible()) {
				filter = new MerchantFilter();

				filter.setPayee_id(payee_inn.getValue());
				filter.setActivity(factivity.getValue());
				filter.setName(fname.getValue());
				filter.setCountry(fcountry.getValue());
				filter.setCity(fcity.getValue());
				filter.setPostal_code(fpostal_code.getValue());
				filter.setPhone_number(fphone_number.getValue());
				filter.setEmail(femail.getValue());

			} else {
				boolean isEmpty = false;
				isEmpty = isEmpty(activity.getValue(), name.getValue(), country.getValue(), city.getValue());
				if (!isEmpty) {
					alert("Не заполнены необходимые параметры");
					ifTrue = false;
				} else {
					current.setPayee_id(payee_inn.getValue());
					current.setId(current.getId());
					current.setActivity(activity.getValue());
					current.setName(name.getValue());
					current.setCountry(country.getValue().substring(0, 2));
					current.setCity(city.getValue());
					current.setPostal_code(postal_code.getValue());
					current.setPhone_number(phone_number.getValue());
					current.setEmail(email.getValue());
					MerchantService.update(current);
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

	public void onClick$btn_cancel() {
		if (fgrd.isVisible()) {
			filter = new MerchantFilter();
			filter.setPayee_id(payee_inn.getValue());
		}
		onClick$btn_back();
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		CheckNull.clearForm(addgrd);
		CheckNull.clearForm(fgrd);
		refreshModel(_startPageNumber);
	}

	public void onClick$btn_add() {
		grd.setVisible(false);
		frm.setVisible(true);
		frmgrd.setVisible(false);
		addgrd.setVisible(true);
		fgrd.setVisible(false);
		btn_back.setVisible(true);
		btn_update.setVisible(false);
	}

	public void onClick$btn_close() {
		merchantmain.detach();
	}

	public void settings() {
		name.setMaxlength(25);
		city.setMaxlength(15);
		postal_code.setMaxlength(10);
		phone_number.setMaxlength(12);
		email.setMaxlength(30);
		aname.setMaxlength(25);
		acity.setMaxlength(15);
		apostal_code.setMaxlength(10);
		aphone_number.setMaxlength(12);
		aemail.setMaxlength(30);
		fname.setMaxlength(25);
		fcity.setMaxlength(15);
		fpostal_code.setMaxlength(10);
		fphone_number.setMaxlength(12);
		femail.setMaxlength(30);
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

}