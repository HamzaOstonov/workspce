package com.is.tieto_globuz.customer;

import globus.IssuingWS.IssuingPortProxy;
import globus.issuing_v_01_02_xsd.OperationConnectionInfo;
import globus.issuing_v_01_02_xsd.OperationResponseInfo;
import globus.issuing_v_01_02_xsd.RowType_Customer;
import globus.issuing_v_01_02_xsd.RowType_CustomerCustomInfo;
import globus.issuing_v_01_02_xsd.holders.ListType_CustomerCustomInfoHolder;
import globus.issuing_v_01_02_xsd.holders.RowType_CustomerHolder;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;

import com.is.ConnectionPool;
import com.is.LtLogger;
import com.is.tieto_globuz.tieto.Tclient;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.Res;

public class CustomerViewCtrl extends GenericForwardComposer
{
	private Window tmpcl;
	private Div frm;
	private Listbox dataGrid, tmpcl$tmpgrid, tmpcl$tmptgrid;
	private Paging contactPaging;
	private Div grd;
	private Grid fgrdl, fgrdr, addgrdl, addgrdr;
	private Hbox addgrd, frmgrd, fgrd;
	private Toolbarbutton btn_last;
	private Toolbarbutton btn_next;
	private Toolbarbutton btn_prev;
	private Toolbarbutton btn_first;
	private Toolbarbutton btn_add;
	private Toolbarbutton btn_search;
	private Toolbarbutton btn_back, btn_card;
	private Toolbar tb;
	private Textbox p_birth_place, id, branch, id_client, name, code_country, code_type, code_resident, code_subject, sign_registr, code_form, date_open, date_close, state, p_birthday, p_post_address, p_passport_serial, p_passport_number,
		p_passport_place_registration, p_passport_date_registration, p_number_tax_registration, p_capacity_status_date, p_capacity_status_place, p_num_certif_capacity, p_phone_home, p_phone_mobile, p_email_address, p_pension_sertif_serial,
		p_passport_date_expiration, p_inps, p_family, p_first_name, p_patronymic;
	private Textbox ap_birth_place, aid, abranch, aid_client, aname, acode_subject, asign_registr, acode_form, astate, ap_post_address, ap_passport_serial, ap_passport_number, ap_passport_place_registration, ap_number_tax_registration,
		ap_capacity_status_date, ap_capacity_status_place, ap_num_certif_capacity, ap_phone_home, ap_phone_mobile, ap_email_address, ap_pension_sertif_serial, ap_inps, ap_family, ap_first_name, ap_patronymic;
	private Textbox fid, fbranch, fid_client, fname, fcode_country, fcode_type, fcode_resident, fcode_subject, fsign_registr, fcode_form, fdate_open, fdate_close, fstate, fp_post_address, fp_passport_serial, fp_passport_number,
		fp_passport_place_registration, fp_passport_date_registration, fp_number_tax_registration, fp_birth_place, fp_capacity_status_date, fp_capacity_status_place, fp_num_certif_capacity, fp_phone_home, fp_phone_mobile, fp_email_address,
		fp_pension_sertif_serial, fp_passport_date_expiration, fp_inps, fp_family, fp_first_name, fp_patronymic;
	private Paging customerPaging;
	private RefCBox p_passport_type, p_code_tax_org, p_code_bank, p_code_class_credit, p_code_citizenship, p_code_capacity, p_code_gender, p_code_nation, p_code_birth_region, p_code_birth_distr, p_type_document, p_code_adr_region, p_code_adr_distr;
	private RefCBox acode_resident, acode_type, acode_country, ap_passport_type, ap_code_tax_org, ap_code_bank, ap_code_class_credit, ap_code_citizenship, ap_code_capacity, ap_code_gender, ap_code_nation, ap_code_birth_region, ap_code_birth_distr,
		ap_type_document, ap_code_adr_region, ap_code_adr_distr;
	private RefCBox fp_passport_type, fp_code_tax_org, fp_code_bank, fp_code_class_credit, fp_code_citizenship, fp_code_capacity, fp_code_gender, fp_code_nation, fp_code_birth_region, fp_code_birth_distr, fp_type_document, fp_code_adr_region,
		fp_code_adr_distr;
	private Datebox adate_open, adate_close, ap_passport_date_registration, ap_passport_date_expiration, ap_birthday;
	private int _pageSize = 20;
	private Datebox fp_birthday;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	
	// public Customer current;
	public CustomerFilter filter = new CustomerFilter();
	
	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	
	private Customer current = new Customer();
	private Customer tcustomer = new Customer();
	private Tclient tietocl = new Tclient();
	
	private String alias, un, curip, branch1;
	private int uid;
	
	public CustomerViewCtrl()
	{
		super('$', false, false);
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception
	{
		super.doAfterCompose(comp);
		// TODO Auto-generated method stub
		binder = new AnnotateDataBinder(comp);
		binder.bindBean("current", this.current);
		// binder.bindBean("tcustomer", this.tcustomer);
		binder.loadAll();
		String[] parameter = (String[]) param.get("ht");
		alias = (String) session.getAttribute("alias");
		uid = (Integer) session.getAttribute("uid");
		curip = (String) session.getAttribute("curip");
		un = (String) session.getAttribute("un");
		branch1 = (String) session.getAttribute("branch");
		
		if (parameter != null)
		{
			_pageSize = Integer.parseInt(parameter[0]) / 60;
			dataGrid.setRows(Integer.parseInt(parameter[0]) / 60);
		}
		
		dataGrid.setItemRenderer(new ListitemRenderer()
		{
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception
			{
				Customer pCustomer = (Customer) data;
				
				row.setValue(pCustomer);
				row.appendChild(new Listcell(pCustomer.getId_client()));
				row.appendChild(new Listcell(pCustomer.getName()));
				row.appendChild(new Listcell(pCustomer.getP_post_address()));
			}
		});
		
		tmpcl$tmpgrid.setItemRenderer(new ListitemRenderer()
		{
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception
			{
				Customer pCustomer = (Customer) data;
				row.setValue(pCustomer);
				row.appendChild(new Listcell(pCustomer.getId_client()));
				row.appendChild(new Listcell(pCustomer.getName()));
			}
		});
		
		tmpcl$tmptgrid.setItemRenderer(new ListitemRenderer()
		{
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception
			{
				Tclient pTclient = (Tclient) data;
				
				row.setValue(pTclient);
				row.appendChild(new Listcell(pTclient.getClient_b() != null ? pTclient.getClient_b() : "--//--"));
				row.appendChild(new Listcell(pTclient.getF_names()));
				row.appendChild(new Listcell(pTclient.getSurname()));
				// row.appendChild(new Listcell(pTclient.getB_date()!=null ?
				// df.format(pTclient.getB_date()):"--//--"));
			}
		});
		
		// tmpcl$tmpgrid.setModel( new ListModelList( ));
		
		// p_passport_type.setModel((new
		// ListModelList(com.is.utils.RefDataService.getType_document())));
		p_code_tax_org.setModel((new ListModelList(com.is.utils.RefDataService.getTax(alias))));
		p_code_bank.setModel((new ListModelList(CustomerService.getMfo(alias))));
		p_code_class_credit.setModel((new ListModelList(com.is.utils.RefDataService.getClassCR(alias))));
		p_code_citizenship.setModel((new ListModelList(com.is.utils.RefDataService.getCountry(alias))));
		// p_birth_place.setModel((new
		// ListModelList(com.is.utils.RefDataService.getDistr())));
		// p_code_capacity.setModel((new
		// ListModelList(com.is.utils.RefDataService.getCapacity())));
		p_code_gender.setModel((new ListModelList(com.is.utils.RefDataService.getGender(alias))));
		p_code_nation.setModel((new ListModelList(com.is.utils.RefDataService.getNation(alias))));
		p_code_birth_region.setModel((new ListModelList(com.is.utils.RefDataService.getRegion(alias))));
		p_code_birth_distr.setModel((new ListModelList(com.is.utils.RefDataService.getDistr(alias))));
		p_type_document.setModel((new ListModelList(com.is.utils.RefDataService.getType_document(alias))));
		p_code_adr_region.setModel((new ListModelList(com.is.utils.RefDataService.getRegion(alias))));
		p_code_adr_distr.setModel((new ListModelList(com.is.utils.RefDataService.getDistr(alias))));
		
		ap_type_document.setModel((new ListModelList(com.is.utils.RefDataService.getType_document(alias))));
		// ap_passport_type.setModel((new
		// ListModelList(com.is.utils.RefDataService.getType_document())));
		ap_code_tax_org.setModel((new ListModelList(com.is.utils.RefDataService.getTax(alias))));
		// ap_code_bank.setModel((new
		// ListModelList(com.is.utils.RefDataService.getMfo(alias))));
		// ap_code_class_credit.setModel((new
		// ListModelList(com.is.utils.RefDataService.getClassCR())));
		ap_code_citizenship.setModel((new ListModelList(com.is.utils.RefDataService.getCountry(alias))));
		acode_country.setModel((new ListModelList(com.is.utils.RefDataService.getCountry(alias))));
		// ap_birth_place.setModel((new
		// ListModelList(com.is.utils.RefDataService.getDistr())));
		// ap_code_capacity.setModel((new
		// ListModelList(com.is.utils.RefDataService.getCapacity())));
		ap_code_gender.setModel((new ListModelList(com.is.utils.RefDataService.getGender(alias))));
		ap_code_nation.setModel((new ListModelList(com.is.utils.RefDataService.getNation(alias))));
		ap_code_birth_region.setModel((new ListModelList(com.is.utils.RefDataService.getRegion(alias))));
		ap_code_birth_distr.setModel((new ListModelList(com.is.utils.RefDataService.getDistr(alias))));
		// ap_type_document.setModel((new
		// ListModelList(com.is.utils.RefDataService.getType_document())));
		ap_code_adr_region.setModel((new ListModelList(com.is.utils.RefDataService.getRegion(alias))));
		ap_code_adr_distr.setModel((new ListModelList(com.is.utils.RefDataService.getDistr(alias))));
		// acode_type.setModel((new
		// ListModelList(com.is.utils.RefDataService.getType_client(alias))));
		acode_resident.setModel((new ListModelList(com.is.utils.RefDataService.getRezCl(alias))));
		
		fp_code_tax_org.setModel((new ListModelList(com.is.utils.RefDataService.getTax(alias))));
		// fp_code_bank.setModel((new
		// ListModelList(com.is.utils.RefDataService.getMfo(alias))));
		// fp_code_class_credit.setModel((new
		// ListModelList(com.is.utils.RefDataService.getClassCR(alias))));
		fp_code_citizenship.setModel((new ListModelList(com.is.utils.RefDataService.getCountry(alias))));
		fp_code_gender.setModel((new ListModelList(com.is.utils.RefDataService.getGender(alias))));
		fp_code_nation.setModel((new ListModelList(com.is.utils.RefDataService.getNation(alias))));
		fp_code_birth_region.setModel((new ListModelList(com.is.utils.RefDataService.getRegion(alias))));
		fp_code_birth_distr.setModel((new ListModelList(com.is.utils.RefDataService.getDistr(alias))));
		fp_type_document.setModel((new ListModelList(com.is.utils.RefDataService.getType_document(alias))));
		fp_code_adr_region.setModel((new ListModelList(com.is.utils.RefDataService.getRegion(alias))));
		fp_code_adr_distr.setModel((new ListModelList(com.is.utils.RefDataService.getDistr(alias))));
		
		first();
		
		refreshModel(_startPageNumber);
		
	}
	
	public void first()
	{
		onClick$btn_search();
		filter = new CustomerFilter();
		filter.setName("");
		filter.setBranch((String) session.getAttribute("branch_filter"));
		if (session.getAttribute("alias_filter") != null)
		{
			alias = (String) session.getAttribute("alias_filter");
		}
		filter.setP_post_address(fp_post_address.getValue());
		filter.setP_passport_serial(fp_passport_serial.getValue());
		filter.setP_passport_number(fp_passport_number.getValue());
		filter.setP_phone_mobile(fp_phone_mobile.getValue());
		filter.setP_email_address(fp_email_address.getValue());
		filter.setP_family(fp_family.getValue());
		filter.setP_first_name(fp_first_name.getValue());
		filter.setP_patronymic(fp_patronymic.getValue());
		onClick$btn_save();
		refreshModel(_startPageNumber);
	}
	
	
	public void onPaging$customerPaging(ForwardEvent event)
	{
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}
	
	private void refreshModel(int activePage)
	{
		customerPaging.setPageSize(_pageSize);
		filter.setBranch((String) session.getAttribute("branch_filter"));
		
		if (session.getAttribute("alias_filter") != null)
		{
			if (alias.compareTo((String) session.getAttribute("alias_filter")) != 0)
			{
				CheckNull.clearForm(fgrdl);
				CheckNull.clearForm(fgrdr);
				filter = new CustomerFilter();
				filter.setBranch((String) session.getAttribute("branch_filter"));
			}
			alias = (String) session.getAttribute("alias_filter");
		}
		model = new PagingListModel(activePage, _pageSize, filter, alias);
		
		if (_needsTotalSizeUpdate)
		{
			_totalSize = model.getTotalSize(filter, alias);
			// _needsTotalSizeUpdate = false;
		}
		
		customerPaging.setTotalSize(_totalSize);
		
		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0)
		{
			dataGrid.setSelectedIndex(0);
			// this.current = (Customer) model.getElementAt(0);
			sendSelEvt();
		}
	}
	
	// Omitted...
	public Customer getCurrent()
	{
		return current;
	}
	
	public void setCurrent(Customer current)
	{
		this.current = current;
	}
	
	public Customer getTcustomer()
	{
		return tcustomer;
	}
	
	public void setTcustomer(Customer tcustomer)
	{
		this.tcustomer = tcustomer;
	}
	
	public Tclient getTietocl()
	{
		return tietocl;
	}
	
	public void setTietocl(Tclient tietocl)
	{
		this.tietocl = tietocl;
	}
	
	public void onDoubleClick$dataGrid$grd()
	{
		grd.setVisible(false);
		frm.setVisible(true);
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		btn_back.setImage("/images/folder.png");
		btn_back.setLabel(Labels.getLabel("grid"));
		btn_card.setVisible(frmgrd.isVisible());
	}
	
	public void onClick$btn_back()
	{
		if (frm.isVisible())
		{
			frm.setVisible(false);
			grd.setVisible(true);
			btn_back.setImage("/images/file.png");
			btn_back.setLabel(Labels.getLabel("back"));
			btn_card.setVisible(frm.isVisible());
		}
		else onDoubleClick$dataGrid$grd();
		
	}
	
	public void onClick$btn_first()
	{
		dataGrid.setSelectedIndex(0);
		sendSelEvt();
	}
	
	public void onClick$btn_last()
	{
		dataGrid.setSelectedIndex(model.getSize() - 1);
		sendSelEvt();
	}
	
	public void onClick$btn_prev()
	{
		if (dataGrid.getSelectedIndex() != 0)
		{
			dataGrid.setSelectedIndex(dataGrid.getSelectedIndex() - 1);
			sendSelEvt();
		}
	}
	
	public void onClick$btn_next()
	{
		if (dataGrid.getSelectedIndex() != (model.getSize() - 1))
		{
			dataGrid.setSelectedIndex(dataGrid.getSelectedIndex() + 1);
			sendSelEvt();
		}
	}
	
	private void sendSelEvt()
	{
		if (dataGrid.getSelectedIndex() == 0)
		{
			btn_first.setDisabled(true);
			btn_prev.setDisabled(true);
		}
		else
		{
			btn_first.setDisabled(false);
			btn_prev.setDisabled(false);
		}
		if (dataGrid.getSelectedIndex() == (model.getSize() - 1))
		{
			btn_next.setDisabled(true);
			btn_last.setDisabled(true);
		}
		else
		{
			btn_next.setDisabled(false);
			btn_last.setDisabled(false);
		}
		SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
		Events.sendEvent(evt);
	}
	
	public void onClick$btn_add()
	{
		
		tmpcl.setVisible(false);
		onDoubleClick$dataGrid$grd();
		frmgrd.setVisible(false);
		// CheckNull.clearForm(addgrdl);
		// CheckNull.clearForm(addgrdr);
		
		fgrd.setVisible(false);
		addgrd.setVisible(true);
		tcustomer = new Customer();
		// CheckNull.clearForm(addgrdl);
		// CheckNull.clearForm(addgrdr);
		btn_card.setVisible(frmgrd.isVisible());
	}
	
	public void onClick$btn_search()
	{
		onDoubleClick$dataGrid$grd();
		frmgrd.setVisible(false);
		addgrd.setVisible(false);
		fgrd.setVisible(true);
		btn_card.setVisible(frmgrd.isVisible());
	}
	
	public void onClick$btn_save()
	{
		try
		{
			if (addgrd.isVisible())
			{
				// if(CheckNull.checkForm(addgrdr)&&(CheckNull.checkForm(addgrdl)))
				// {
				
				tcustomer.setName(ap_family.getValue() + " " + ap_first_name.getValue() + " " + ap_patronymic.getValue());
				
				boolean fl_err = false;
				String err = "";
				
				if ((!((ap_passport_number.getValue()).matches("[a-zA-Z0-9]+"))) || (ap_passport_number.getValue().length() > 9))
				{
					fl_err = true;
					err += "\nНомер паспорта";
				}
				if ((!((ap_passport_serial.getValue()).matches("[a-zA-Z0-9]+"))) || (ap_passport_serial.getValue().length() > 9))
				{
					fl_err = true;
					err += "\nСерия паспорта";
				}
				if ((!((ap_passport_place_registration.getValue()).matches("[a-zA-Z0-9\\s\\.\\,_\\/-]+"))) || (ap_passport_place_registration.getValue().length() > 200))
				{
					fl_err = true;
					err += "\nМесто регистрации паспорта";
				}
				if ((!((ap_family.getValue()).matches("[a-zA-Z0-9]+"))) || (ap_family.getValue().length() > 34))
				{
					fl_err = true;
					err += "\nФамилия";
				}
				if ((!((ap_first_name.getValue()).matches("[a-zA-Z0-9]+"))) || (ap_first_name.getValue().length() > 20))
				{
					fl_err = true;
					err += "\nИмя";
				}
				if ((!((ap_patronymic.getValue()).matches("[a-zA-Z0-9]*"))) || (ap_patronymic.getValue().length() > 20))
				{
					fl_err = true;
					err += "\nОтчество";
				}
				if ((CheckNull.isEmpty(ap_type_document.getValue())))
				{
					fl_err = true;
					err += "\nТип документа";
				}
				if ((!((ap_number_tax_registration.getValue()).matches("[0-9]*"))) || (ap_number_tax_registration.getValue().length() > 9))
				{
					fl_err = true;
					err += "\nИНН";
				}
				if ((CheckNull.isEmpty(ap_code_citizenship.getValue())))
				{
					fl_err = true;
					err += "\nГражданство";
				}
				if ((CheckNull.isEmpty(acode_country.getValue())))
				{
					fl_err = true;
					err += "\nСтрана";
				}
				if ((CheckNull.isEmpty(acode_resident.getValue())))
				{
					fl_err = true;
					err += "\nРезидент";
				}
				if ((CheckNull.isEmpty(ap_passport_date_registration.getValue())))
				{
					fl_err = true;
					err += "\nДата регистрации паспорта";
				}
				if ((CheckNull.isEmpty(ap_birthday.getValue())))
				{
					fl_err = true;
					err += "\nДата рождения";
				}
				if ((!((ap_post_address.getValue()).matches("[a-zA-Z0-9\\s\\.\\,_\\/-]+"))) || (ap_post_address.getValue().length() > 95))
				{
					fl_err = true;
					err += "\nПочтовый адрес";
				}
				if ((!((ap_birth_place.getValue()).matches("[a-zA-Z0-9\\s\\.\\,_\\/-]*"))) || (ap_birth_place.getValue().length() > 200))
				{
					fl_err = true;
					err += "\nМесто рождения";
				}
				
				/*
				 * if
				 * ((((ap_passport_number.getValue()).matches("[а-яА-Я]+")))||
				 * (ap_passport_number.getValue().length()>9)){fl_err = true;
				 * err += "\nНомер паспорта";} if
				 * ((((ap_passport_serial.getValue
				 * ()).matches("[а-яА-Я]+")))||(ap_passport_serial
				 * .getValue().length()>9)){fl_err = true; err +=
				 * "\nСерия паспорта";} if
				 * ((((ap_passport_place_registration.getValue
				 * ()).matches("[а-яА-Я]+"
				 * )))||(ap_passport_place_registration.getValue
				 * ().length()>200)){fl_err = true; err +=
				 * "\nМесто регистрации паспорта";} if
				 * ((((ap_family.getValue()).
				 * matches("[а-яА-Я]+")))||(ap_family.getValue
				 * ().length()>34)){fl_err = true; err += "\nФамилия";} if
				 * ((((ap_first_name
				 * .getValue()).matches("[а-яА-Я]+")))||(ap_first_name
				 * .getValue().length()>20)){fl_err = true; err += "\nИмя";} if
				 * ((((ap_patronymic.getValue()).matches("[а-яА-Я]*")))||(
				 * ap_patronymic.getValue().length()>20)){fl_err = true; err +=
				 * "\nОтчество";} if
				 * ((CheckNull.isEmpty(ap_type_document.getValue()))){fl_err =
				 * true; err += "\nТип документа";} if
				 * ((((ap_number_tax_registration
				 * .getValue()).matches("[0-9]*")))
				 * ||(ap_number_tax_registration.getValue().length()>9)){fl_err
				 * = true; err += "\nИНН";} if
				 * ((CheckNull.isEmpty(ap_code_citizenship.getValue()))){fl_err
				 * = true; err += "\nГражданство";} if
				 * ((CheckNull.isEmpty(acode_country.getValue()))){fl_err =
				 * true; err += "\nСтрана";} if
				 * ((CheckNull.isEmpty(acode_resident.getValue()))){fl_err =
				 * true; err += "\nРезидент";} if
				 * ((CheckNull.isEmpty(ap_passport_date_registration
				 * .getValue()))){fl_err = true; err +=
				 * "\nДата регистрации паспорта";} if
				 * ((CheckNull.isEmpty(ap_birthday.getValue()))){fl_err = true;
				 * err += "\nДата рождения";} if
				 * ((((ap_post_address.getValue()).
				 * matches("[а-яА-Я]+")))||(ap_post_address
				 * .getValue().length()>95)){fl_err = true; err +=
				 * "\nПочтовый адрес";} if
				 * ((((ap_birth_place.getValue()).matches
				 * ("[а-яА-Я]*")))||(ap_birth_place
				 * .getValue().length()>200)){fl_err = true; err +=
				 * "\nМесто рождения";}
				 */

				if (fl_err)
				{
					alert("Ошибка заполнения формы:\nневерно заполнено поле " + err);
					return;
				}
				
				// String cur_acc = null;
				Customer new_customer = new Customer();
				
				new_customer.setP_passport_number(ap_passport_number.getValue());
				new_customer.setP_passport_type(ap_type_document.getValue());
				new_customer.setP_type_document(ap_type_document.getValue());
				new_customer.setP_passport_serial(ap_passport_serial.getValue());
				new_customer.setP_passport_place_registration(ap_passport_place_registration.getValue());
				new_customer.setP_family(ap_family.getValue());
				new_customer.setP_first_name(ap_first_name.getValue());
				new_customer.setName(ap_family.getValue() + " " +
						ap_first_name.getValue() + " " +
						ap_patronymic.getValue());
				new_customer.setP_birthday(ap_birthday.getValue());
				new_customer.setCode_country(acode_country.getValue());
				new_customer.setCode_resident(acode_resident.getValue());
				new_customer.setP_post_address(ap_post_address.getValue());
				
				// new_customer.setAcode_tel(acode_tel.getValue());
				new_customer.setP_patronymic(ap_patronymic.getValue());
				
				new_customer.setP_passport_date_expiration(ap_passport_date_expiration.getValue());
				new_customer.setP_passport_date_registration(ap_passport_date_registration.getValue());
				new_customer.setP_code_birth_region(CheckNull.isEmpty(ap_code_birth_region.getValue()) ? null : ap_code_birth_region.getValue());
				new_customer.setP_code_birth_distr(ap_code_birth_distr.getValue());
				new_customer.setP_birth_place(ap_birth_place.getValue());
				new_customer.setP_code_gender(ap_code_gender.getValue());
				new_customer.setP_code_nation(ap_code_nation.getValue());
				new_customer.setP_code_adr_region(ap_code_adr_region.getValue());
				new_customer.setP_code_adr_distr(ap_code_adr_distr.getValue());
				new_customer.setP_code_tax_org(ap_code_tax_org.getValue());
				new_customer.setP_number_tax_registration(ap_number_tax_registration.getValue());
				new_customer.setP_code_citizenship(ap_code_citizenship.getValue());
				new_customer.setP_phone_mobile(ap_phone_mobile.getValue());
				new_customer.setP_email_address(ap_email_address.getValue());
				new_customer.setP_phone_home(ap_phone_home.getValue());
				new_customer.setP_inps(ap_inps.getValue());
				
				filter.setBranch((String) session.getAttribute("branch_filter"));
				
				new_customer.setBranch((String) session.getAttribute("branch_filter"));
				new_customer.setP_code_bank((String) session.getAttribute("branch_filter"));
				// sendSelEvt();
				new_customer.setP_code_class_credit("1");
				new_customer.setP_passport_type("N");
				new_customer.setCode_subject("P");
				new_customer.setSign_registr(2);
				new_customer.setCode_form("");
				new_customer.setCode_type("08");
				
				Res res =
					CustomerService.doAction(session.getAttribute("un").toString(), session.getAttribute("pwd").toString(), new_customer, 1, 2, (String) session.getAttribute("alias_filter"), ((String) session.getAttribute("branch_filter")).compareTo((String) session.getAttribute("branch")) == 0 ? true : false);
				// Res res1 =
				// CustomerService.doAction(session.getAttribute("un").toString(),
				// session.getAttribute("pwd").toString(), tcustomer, 2, alias,
				// true);
				if (res.getCode() != 0)
				{
					alert(res.getName());
				}
				else
				{
					Customer lg_c = CustomerService.getCustomerById_tbl(res.getName(), (String) session.getAttribute("branch_filter"), alias);
					String cl_n = lg_c.getName() + lg_c.getP_birthday();
					// UserService.UsrLog(new UserActionsLog(uid, un, curip, 6,
					// 1,
					// "В филиале ["+(String)session.getAttribute("branch_filter")+"] открыт клиент id ["+res.getName()+"] ["+cl_n+"]",
					// branch1));
					refreshModel(_startPageNumber);
					onClick$btn_back();
				}
				/*
				 * } else{ alert("Не заполнены необходимые поля"); }
				 */
			}
			
			else if (fgrd.isVisible())
			{
				filter = new CustomerFilter();
				filter.setBranch((String) session.getAttribute("branch_filter"));
				if (session.getAttribute("alias_filter") != null)
				{
					alias = (String) session.getAttribute("alias_filter");
				}
				
				// filter.setId(fid.getValue());
				// filter.setBranch(fbranch.getValue());
				// filter.setId_client(fid_client.getValue());
				filter.setName(fname.getValue());
				// filter.setCode_country(fcode_country.getValue());
				// filter.setCode_type(fcode_type.getValue());
				// filter.setCode_resident(fcode_resident.getValue());
				// filter.setCode_subject(fcode_subject.getValue());
				// filter.setSign_registr(fsign_registr.getValue());
				// filter.setCode_form(fcode_form.getValue());
				// filter.setDate_open(fdate_open.getValue());
				// filter.setDate_close(fdate_close.getValue());
				// filter.setState(fstate.getValue());
				// filter.setP_birthday(fp_birthday.getValue());
				filter.setP_post_address(fp_post_address.getValue());
				// filter.setP_passport_type(fp_passport_type.getValue());
				filter.setP_passport_serial(fp_passport_serial.getValue());
				filter.setP_passport_number(fp_passport_number.getValue());
				// filter.setP_passport_place_registration(fp_passport_place_registration.getValue());
				// filter.setP_passport_date_registration(fp_passport_date_registration.getValue());
				// filter.setP_code_tax_org(fp_code_tax_org.getValue());
				// filter.setP_number_tax_registration(fp_number_tax_registration.getValue());
				// filter.setP_code_bank(fp_code_bank.getValue());
				// filter.setP_code_class_credit(fp_code_class_credit.getValue());
				// filter.setP_code_citizenship(fp_code_citizenship.getValue());
				// filter.setP_birth_place(fp_birth_place.getValue());
				// filter.setP_code_capacity(fp_code_capacity.getValue());
				// filter.setP_capacity_status_date(fp_capacity_status_date.getValue());
				// filter.setP_capacity_status_place(fp_capacity_status_place.getValue());
				// filter.setP_num_certif_capacity(fp_num_certif_capacity.getValue());
				// filter.setP_phone_home(fp_phone_home.getValue());
				filter.setP_phone_mobile(fp_phone_mobile.getValue());
				filter.setP_email_address(fp_email_address.getValue());
				// filter.setP_pension_sertif_serial(fp_pension_sertif_serial.getValue());
				// filter.setP_code_gender(fp_code_gender.getValue());
				// filter.setP_code_nation(fp_code_nation.getValue());
				// filter.setP_code_birth_region(fp_code_birth_region.getValue());
				// filter.setP_code_birth_distr(fp_code_birth_distr.getValue());
				// filter.setP_type_document(fp_type_document.getValue());
				// filter.setP_passport_date_expiration(fp_passport_date_expiration.getValue());
				// filter.setP_code_adr_region(fp_code_adr_region.getValue());
				// filter.setP_code_adr_distr(fp_code_adr_distr.getValue());
				// filter.setP_inps(fp_inps.getValue());
				filter.setP_family(fp_family.getValue());
				filter.setP_first_name(fp_first_name.getValue());
				filter.setP_patronymic(fp_patronymic.getValue());
				
			}
			else
			{
				/*
				 * //current.setId(id.getValue());
				 * current.setBranch(branch.getValue());
				 * current.setId_client(id_client.getValue());
				 * current.setName(name.getValue());
				 * current.setCode_country(code_country.getValue());
				 * current.setCode_type(code_type.getValue());
				 * current.setCode_resident(code_resident.getValue());
				 * current.setCode_subject(code_subject.getValue());
				 * //current.setSign_registr(sign_registr.getValue());
				 * current.setCode_form(code_form.getValue());
				 * //current.setDate_open(date_open.getValue());
				 * //current.setDate_close(date_close.getValue());
				 * //current.setState(state.getValue());
				 * //current.setP_birthday(p_birthday.getValue());
				 * current.setP_post_address(p_post_address.getValue());
				 * current.setP_passport_type(p_passport_type.getValue());
				 * current.setP_passport_serial(p_passport_serial.getValue());
				 * current.setP_passport_number(p_passport_number.getValue());
				 * current
				 * .setP_passport_place_registration(p_passport_place_registration
				 * .getValue()); //current.setP_passport_date_registration(
				 * p_passport_date_registration.getValue());
				 * current.setP_code_tax_org(p_code_tax_org.getValue());
				 * current.
				 * setP_number_tax_registration(p_number_tax_registration
				 * .getValue()); current.setP_code_bank(p_code_bank.getValue());
				 * current
				 * .setP_code_class_credit(p_code_class_credit.getValue());
				 * current.setP_code_citizenship(p_code_citizenship.getValue());
				 * current.setP_birth_place(p_birth_place.getValue());
				 * current.setP_code_capacity(p_code_capacity.getValue());
				 * //current
				 * .setP_capacity_status_date(p_capacity_status_date.getValue
				 * ());
				 * current.setP_capacity_status_place(p_capacity_status_place
				 * .getValue());
				 * current.setP_num_certif_capacity(p_num_certif_capacity
				 * .getValue());
				 * current.setP_phone_home(p_phone_home.getValue());
				 * current.setP_phone_mobile(p_phone_mobile.getValue());
				 * current.setP_email_address(p_email_address.getValue());
				 * current
				 * .setP_pension_sertif_serial(p_pension_sertif_serial.getValue
				 * ()); current.setP_code_gender(p_code_gender.getValue());
				 * current.setP_code_nation(p_code_nation.getValue());
				 * current.setP_code_birth_region
				 * (p_code_birth_region.getValue());
				 * current.setP_code_birth_distr(p_code_birth_distr.getValue());
				 * current.setP_type_document(p_type_document.getValue());
				 * //current
				 * .setP_passport_date_expiration(p_passport_date_expiration
				 * .getValue());
				 * current.setP_code_adr_region(p_code_adr_region.getValue());
				 * current.setP_code_adr_distr(p_code_adr_distr.getValue());
				 * current.setP_inps(p_inps.getValue());
				 * current.setP_family(p_family.getValue());
				 * current.setP_first_name(p_first_name.getValue());
				 * current.setP_patronymic(p_patronymic.getValue());
				 */

				// CustomerService.doAction(session.getAttribute("un").toString(),
				// session.getAttribute("pwd").toString(), current, 1,2, alias,
				// true);
			}
			onClick$btn_back();
			refreshModel(_startPageNumber);
			SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
			Events.sendEvent(evt);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void onClick$btn_cancel()
	{
		if (fgrd.isVisible())
		{
			filter = new CustomerFilter();
			filter.setBranch((String) session.getAttribute("branch_filter"));
			if (session.getAttribute("alias_filter") != null)
			{
				alias = (String) session.getAttribute("alias_filter");
			}
			System.out.println("alias:" + alias);
		}
		onClick$btn_back();
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		CheckNull.clearForm(addgrdl);
		CheckNull.clearForm(addgrdr);
		CheckNull.clearForm(fgrdl);
		CheckNull.clearForm(fgrdr);
		refreshModel(_startPageNumber);
	}
	
	public void onClick$btn_tst()
	{
		// OperationConnectionInfo connectionInfo = new
		// OperationConnectionInfo("01","01",null,null);
		OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		OperationResponseInfo orInfo = null;
		
		RowType_Customer rtc = new RowType_Customer();
		rtc.setF_NAMES(current.getP_first_name());
		rtc.setCL_TYPE("1");
		rtc.setCLIENT_B(current.getId_client());
		rtc.setSURNAME(current.getP_family());
		rtc.setM_NAME(current.getP_patronymic());
		Calendar cal = Calendar.getInstance();
		cal.setTime(current.getP_passport_date_registration());
		rtc.setDOC_SINCE(cal);
		cal.setTime(current.getP_birthday());
		rtc.setB_DATE(cal);
		rtc.setRESIDENT("1");
		rtc.setSTATUS("10");
		rtc.setSEX("1");
		// rtc.setID_CARD("BRRRR");
		// rtc.setCLIENT("99123456");
		
		RowType_CustomerHolder customerInfo = new RowType_CustomerHolder(rtc);
		
		// RowType_CustomerCustomInfo[] cci ={ new
		// RowType_CustomerCustomInfo("1","jopa")};
		RowType_CustomerCustomInfo[] cci = {};
		// ListType_CustomerCustomInfoHolder customListInfo =new
		// ListType_CustomerCustomInfoHolder(cci);
		ListType_CustomerCustomInfoHolder customListInfo = new ListType_CustomerCustomInfoHolder();
		
		//IssuingPortProxy pp = new IssuingPortProxy("http://iss_ws:visaipak@10.11.0.63:8080/issuingws/services/Issuing");
		
		/*IssuingPortProxy pp = new IssuingPortProxy(
			ConnectionPool.getValue("TIETO_HOST", alias),
			ConnectionPool.getValue("TIETO_HOST_USERNAME", alias),
			ConnectionPool.getValue("TIETO_HOST_PASSWORD", alias)
			);
		
		*/
		
		IssuingPortProxy pp = new IssuingPortProxy(
			ConnectionPool.getValue("EMPC_TIETO_HOST", alias),
			ConnectionPool.getValue("EMPC_TIETO_HOST_USERNAME", alias),
			ConnectionPool.getValue("EMPC_TIETO_HOST_PASSWORD", alias));
		
		try
		{
			connectionInfo.setBANK_C("01");
			connectionInfo.setGROUPC("01");
			// connectionInfo.setEXTERNAL_SESSION_ID("100");
			// connectionInfo.setFAULT_MODE(new BigDecimal(0));
			// connectionInfo.setFAULT_MODE(BigDecimal.ONE);
			
			// IssuingServiceLocator sl = new IssuingServiceLocator();
			// IssuingService is = sl.geti
			orInfo = pp.newCustomer(connectionInfo, customerInfo, customListInfo);
			
			// orInfo = pp.newCustomer(connectionInfo, customerInfo, null);
			alert(orInfo.getError_action() + "\r\n" + orInfo.getError_description());
			System.out.println("resp " + orInfo.getResponse_code() + "  client " + customerInfo.value.getCLIENT());
			
		}
		catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void onSelect$ap_code_gender(Event evt)
	{
		// System.out.println(ap_code_gender.getValue()); // null
	}
	
	public void onSelect$ap_code_birth_region(Event evt)
	{
		ap_code_birth_distr.setSelectedIndex(-1);
		ap_code_birth_distr.setModel((new ListModelList(com.is.utils.RefDataService.getDistr(ap_code_birth_region.getValue()))));
	}
	
	public void onSelect$ap_code_adr_region(Event evt)
	{
		ap_code_adr_distr.setSelectedIndex(-1);
		ap_code_adr_distr.setModel((new ListModelList(com.is.utils.RefDataService.getDistr(ap_code_adr_region.getValue()))));
	}
	
	public void onChange$ap_passport_number(Event evt)
	{
		LtLogger.getLogger().info(ap_passport_number.getValue());
		List<Customer> clist = CustomerService.getCustomer(ap_passport_number.getValue(), alias);
		//List<Tclient> tetcl = TclientService.getTclient(ap_passport_number.getValue(), alias, issuingPortProxy);
		if (clist.size() > 0)
		{
			try
			{
				tmpcl$tmpgrid.setModel(new BindingListModelList(clist, false));
			}
			catch (Exception e)
			{
			}
			// tmpcl.setVisible(true);
			
		}
		/*if (tetcl.size() > 0)
		{
			try
			{
				tmpcl$tmptgrid.setModel(new BindingListModelList(tetcl, false));
			}
			catch (Exception e)
			{
			}
			// tmpcl.setVisible(true);
		}*/
		tmpcl.setVisible(true);
		
	}
	
	public void onDoubleClick$tmpgrid$tmpcl()
	{
		
		tmpcl.setVisible(false);
		
	}
	
	public void onFocus$customermain()
	{
		refreshModel(_startPageNumber);
	}
	
}
