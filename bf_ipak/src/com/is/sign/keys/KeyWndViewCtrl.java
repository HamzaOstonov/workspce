package com.is.sign.keys;

import java.text.SimpleDateFormat;


import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.user.User;
import com.is.utils.CheckNull;
import com.is.utils.Res;

@SuppressWarnings("serial")
public class KeyWndViewCtrl extends GenericForwardComposer {
	private Window user_keymain;
	private Div frm;
	private Paging contactPaging;
	private Div grd;
	private Listbox dataGrid;
	private Grid addgrd, frmgrd, fgrd;
	private Toolbarbutton btn_last;
	private Toolbarbutton btn_next;
	private Toolbarbutton btn_prev;
	private Toolbarbutton btn_first;
	private Toolbarbutton btn_add;
	private Toolbarbutton btn_search;
	private Toolbarbutton btn_back;
	private Toolbar tb;
	private Textbox username;
	private Longbox key_type;
	private Textbox key_code, key_sn, version, signature_algoritm;
	private Longbox key_size;
	private Textbox issuer, pkey;
	private Longbox sign_failure;
	private Datebox key_expired, activate_date, deactivate_date;
	private Textbox branch;
	private Longbox emp_id, state;
	private Textbox ausername;
	private Longbox akey_type;
	private Textbox akey_code, akey_sn, aversion, asignature_algoritm;
	private Longbox akey_size;
	private Textbox aissuer, apkey;
	private Longbox asign_failure;
	private Datebox akey_expired, aactivate_date, adeactivate_date;
	private Textbox abranch;
	private Longbox aemp_id, astate;
	private Textbox fusername;
	private Longbox fkey_type;
	private Textbox fkey_code, fkey_sn, fkey_certn, fversion, fsignature_algoritm;
	private Longbox fkey_size;
	private Textbox fissuer, fpkey;
	private Longbox fsign_failure;
	private Datebox fkey_expired, factivate_date, fdeactivate_date;
	private Textbox fbranch;
	private Longbox femp_id, fstate;
	private Paging User_keyPaging;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	//private String keyout = "''";
	private int currentkeytype = Integer.parseInt(ConnectionPool.getValue("SIGN_TYPE"));
	private User _user;

	public Key current = new Key();
	public KeyFilter filter = new KeyFilter();

	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");

	public KeyWndViewCtrl() {
		super('$', false, false);
	}

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
		
		_pageSize = 10;
		dataGrid.setRows(11);

		dataGrid.setItemRenderer(new ListitemRenderer() {
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception {
				Key pUser_key = (Key) data;
				row.setValue(pUser_key);

				//row.appendChild(new Listcell(pUser_key.getUsername()));
				row.appendChild(new Listcell(pUser_key.getKey_type() + ""));
				row.appendChild(new Listcell(pUser_key.getKey_code()));
				row.appendChild(new Listcell(pUser_key.getKey_sn()));
				row.appendChild(new Listcell(pUser_key.getKey_certn()));
				//row.appendChild(new Listcell(pUser_key.getVersion()));
				//row.appendChild(new Listcell(pUser_key.getSignature_algoritm()));
				//row.appendChild(new Listcell(pUser_key.getKey_size() + ""));
				//row.appendChild(new Listcell(pUser_key.getIssuer()));
				//row.appendChild(new Listcell(pUser_key.getPkey()));
				row.appendChild(new Listcell((pUser_key.getSign_failure()==1L)?"Активна":"Не активна"));
				row.appendChild(new Listcell(CheckNull.isEmpty(pUser_key.getKey_expired())?"":df.format(pUser_key.getKey_expired())));
				row.appendChild(new Listcell(CheckNull.isEmpty(pUser_key.getActivate_date())?"":df.format(pUser_key.getActivate_date())));
				row.appendChild(new Listcell(CheckNull.isEmpty(pUser_key.getDeactivate_date())?"":df.format(pUser_key.getDeactivate_date())));
				//row.appendChild(new Listcell(pUser_key.getBranch()));
				//row.appendChild(new Listcell(pUser_key.getEmp_id() + ""));
				//row.appendChild(new Listcell(pUser_key.getState() + ""));
			}
		});
		
		refreshModel(_startPageNumber);
	}
	
	public void init(User usr) {
		_user = usr;
		//keyout = User_keyService.getKeyOut(usr.getUsername(), 1);
		user_keymain.setVisible(true);
		_startPageNumber = 0;
		User_keyPaging.setActivePage(0);
		refreshModel(_startPageNumber);
	}

	public void onPaging$User_keyPaging(ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}

	private void refreshModel(int activePage) {
		filter.setKey_type(currentkeytype);
		filter.setState(1);
		//filter.setKeyout(keyout);
		User_keyPaging.setPageSize(_pageSize);
		model = new PagingListModel(activePage, _pageSize, filter);
		if (_needsTotalSizeUpdate) {
			_totalSize = model.getTotalSize(filter);
			// _needsTotalSizeUpdate = false;
		}
		User_keyPaging.setTotalSize(_totalSize);
		dataGrid.setModel((ListModel) model);
		sort();
		if (model.getSize() > 0) {
			dataGrid.setSelectedIndex(0);
			sendSelEvt();
			this.current = (Key) model.getElementAt(0);
			sendSelEvt();
		}
	}

	public void sort() {
		for (int i = 0; i < dataGrid.getListhead().getChildren().size(); i++) {
			Listheader listheader = (Listheader) dataGrid.getListhead().getChildren().get(i);
			if (!listheader.getSortDirection().equalsIgnoreCase("natural")) {
				listheader.sort(listheader.getSortDirection().equalsIgnoreCase("ascending"), true);
				return;
			}

		}
	}

	// Omitted...
	public Key getCurrent() {
		return current;
	}

	public void setCurrent(Key current) {
		this.current = current;
	}

	public void onDoubleClick$dataGrid$grd() {
		onClick$btn_add_key();
		/*
		grd.setVisible(false);
		frm.setVisible(true);
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		btn_back.setImage("/images/folder.png");
		btn_back.setLabel(Labels.getLabel("grid"));
		*/
	}

	public void onClick$btn_back() {
		if (frm.isVisible()) {
			frm.setVisible(false);
			grd.setVisible(true);
			btn_back.setImage("/images/file.png");
			btn_back.setLabel(Labels.getLabel("back"));
		} else onDoubleClick$dataGrid$grd();
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
		SelectEvent evt = new SelectEvent(	"onSelect",
											dataGrid,
											dataGrid.getSelectedItems());
		Events.sendEvent(evt);
	}

	public void onClick$btn_add() {
		onDoubleClick$dataGrid$grd();
		frmgrd.setVisible(false);
		addgrd.setVisible(true);
		fgrd.setVisible(false);
	}

	public void onClick$btn_search() {
		onDoubleClick$dataGrid$grd();
		frmgrd.setVisible(false);
		addgrd.setVisible(false);
		fgrd.setVisible(true);
	}

	public void onClick$btn_save() {
		try {
			if (addgrd.isVisible()) {
				/*KeyService.create(new Key(//ausername.getValue(),
													akey_type.getValue(),
													akey_code.getValue(),
													akey_sn.getValue(),
													aversion.getValue(),
													asignature_algoritm.getValue(),
													akey_size.getValue(),
													aissuer.getValue(),
													apkey.getValue(),
													asign_failure.getValue(),
													akey_expired.getValue(),
													aactivate_date.getValue(),
													adeactivate_date.getValue(),
													abranch.getValue(),
													aemp_id.getValue(),
													astate.getValue()

				));
				CheckNull.clearForm(addgrd);*/
				frmgrd.setVisible(true);
				addgrd.setVisible(false);
				fgrd.setVisible(false);
			} else if (fgrd.isVisible()) {
				filter = new KeyFilter();
				/*if (!CheckNull.isEmpty(fusername.getValue())) {
					filter.setUsername(fusername.getValue());
				}*/
				/*if (!CheckNull.isEmpty(fkey_type.getValue())) {
					filter.setKey_type(fkey_type.getValue());
				}*/
				if (!CheckNull.isEmpty(fkey_code.getValue())) {
					filter.setKey_code(fkey_code.getValue());
				}
				if (!CheckNull.isEmpty(fkey_sn.getValue())) {
					filter.setKey_sn(fkey_sn.getValue());
				}
				if (!CheckNull.isEmpty(fkey_certn.getValue())) {
					filter.setKey_certn(fkey_certn.getValue());
				}
				if (!CheckNull.isEmpty(fversion.getValue())) {
					filter.setVersion(fversion.getValue());
				}
				if (!CheckNull.isEmpty(fsignature_algoritm.getValue())) {
					filter.setSignature_algoritm(fsignature_algoritm.getValue());
				}
				if (!CheckNull.isEmpty(fkey_size.getValue())) {
					filter.setKey_size(fkey_size.getValue());
				}
				if (!CheckNull.isEmpty(fissuer.getValue())) {
					filter.setIssuer(fissuer.getValue());
				}
				if (!CheckNull.isEmpty(fpkey.getValue())) {
					filter.setPkey(fpkey.getValue());
				}
				if (!CheckNull.isEmpty(fsign_failure.getValue())) {
					filter.setSign_failure(fsign_failure.getValue());
				}
				if (!CheckNull.isEmpty(fkey_expired.getValue())) {
					filter.setKey_expired(fkey_expired.getValue());
				}
				if (!CheckNull.isEmpty(factivate_date.getValue())) {
					filter.setActivate_date(factivate_date.getValue());
				}
				if (!CheckNull.isEmpty(fdeactivate_date.getValue())) {
					filter.setDeactivate_date(fdeactivate_date.getValue());
				}
				if (!CheckNull.isEmpty(fbranch.getValue())) {
					filter.setBranch(fbranch.getValue());
				}
				/*if (!CheckNull.isEmpty(femp_id.getValue())) {
					filter.setEmp_id(femp_id.getValue());
				}
				if (!CheckNull.isEmpty(fstate.getValue())) {
					filter.setState(fstate.getValue());
				}*/

			} else {
				/*//current.setUsername(username.getValue());
				current.setKey_type(key_type.getValue());
				current.setKey_code(key_code.getValue());
				current.setKey_sn(key_sn.getValue());
				current.setVersion(version.getValue());
				current.setSignature_algoritm(signature_algoritm.getValue());
				current.setKey_size(key_size.getValue());
				current.setIssuer(issuer.getValue());
				current.setPkey(pkey.getValue());
				current.setSign_failure(sign_failure.getValue());
				current.setKey_expired(key_expired.getValue());
				current.setActivate_date(activate_date.getValue());
				current.setDeactivate_date(deactivate_date.getValue());
				current.setBranch(branch.getValue());
				current.setEmp_id(emp_id.getValue());
				current.setState(state.getValue());

				User_keyService.update(current);*/
			}
			onClick$btn_back();
			refreshModel(_startPageNumber);
			SelectEvent evt = new SelectEvent(	"onSelect",
												dataGrid,
												dataGrid.getSelectedItems());
			Events.sendEvent(evt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onClick$btn_cancel() {
		if (fgrd.isVisible()) {
			filter = new KeyFilter();
		}
		onClick$btn_back();
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		CheckNull.clearForm(addgrd);
		CheckNull.clearForm(fgrd);
		refreshModel(_startPageNumber);
	}
	
	public void onClick$btn_find() {
		filter = new KeyFilter();
		/*if (!CheckNull.isEmpty(fusername.getValue())) {
			filter.setUsername(fusername.getValue());
		}
		if (!CheckNull.isEmpty(fkey_type.getValue())) {
			filter.setKey_type(fkey_type.getValue());
		}
		*/
		if (!CheckNull.isEmpty(fkey_code.getValue())) {
			filter.setKey_code(fkey_code.getValue());
		}
		if (!CheckNull.isEmpty(fkey_sn.getValue())) {
			filter.setKey_sn(fkey_sn.getValue());
		}
		if (!CheckNull.isEmpty(fkey_certn.getValue())) {
			filter.setKey_certn(fkey_certn.getValue());
		}
		/*
		if (!CheckNull.isEmpty(fversion.getValue())) {
			filter.setVersion(fversion.getValue());
		}
		if (!CheckNull.isEmpty(fsignature_algoritm.getValue())) {
			filter.setSignature_algoritm(fsignature_algoritm.getValue());
		}
		if (!CheckNull.isEmpty(fkey_size.getValue())) {
			filter.setKey_size(fkey_size.getValue());
		}
		if (!CheckNull.isEmpty(fissuer.getValue())) {
			filter.setIssuer(fissuer.getValue());
		}
		if (!CheckNull.isEmpty(fpkey.getValue())) {
			filter.setPkey(fpkey.getValue());
		}
		if (!CheckNull.isEmpty(fsign_failure.getValue())) {
			filter.setSign_failure(fsign_failure.getValue());
		}
		*/
		if (!CheckNull.isEmpty(fkey_expired.getValue())) {
			filter.setKey_expired(fkey_expired.getValue());
		}
		/*
		if (!CheckNull.isEmpty(factivate_date.getValue())) {
			filter.setActivate_date(factivate_date.getValue());
		}
		if (!CheckNull.isEmpty(fdeactivate_date.getValue())) {
			filter.setDeactivate_date(fdeactivate_date.getValue());
		}
		if (!CheckNull.isEmpty(fbranch.getValue())) {
			filter.setBranch(fbranch.getValue());
		}
		if (!CheckNull.isEmpty(femp_id.getValue())) {
			filter.setEmp_id(femp_id.getValue());
		}
		if (!CheckNull.isEmpty(fstate.getValue())) {
			filter.setState(fstate.getValue());
		}
		*/
		_startPageNumber = 0;
		User_keyPaging.setActivePage(0);
		refreshModel(_startPageNumber);
	}
	
	public void onClick$btn_find_clear() {
		KeyService.clearForm(fgrd);
	}
	
	public void onClick$btn_find_cancel() {
		filter = new KeyFilter();
		KeyService.clearForm(fgrd);
		_startPageNumber = 0;
		User_keyPaging.setActivePage(0);
		refreshModel(_startPageNumber);
	}
	
	public void onClick$btn_add_key() {
		if ((current != null && !CheckNull.isEmpty(current.getKey_code()) && (current.getState() == 1))) {
			Res res = KeyService.createUserKey(_user, current);
			if (res.getCode() == 0) {
				Events.sendEvent("onAddKey", self, current);
				user_keymain.setVisible(false);
			} else {
				alert(res.getName());
			}
		} else {
			alert("Отсутствуют данные для прикрепления");
		}
	}
	
}