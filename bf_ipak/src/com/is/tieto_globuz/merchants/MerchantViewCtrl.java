package com.is.tieto_globuz.merchants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
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
import org.zkoss.zul.event.PagingEvent;

import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.is.utils.Res;

public class MerchantViewCtrl extends GenericForwardComposer
{
	private Div frm;
	private Listbox dataGrid, dataGrid_client;
	private Paging contactPaging;
	private Div grd;
	private Grid addgrd, frmgrd, fgrd;
	private Toolbarbutton btn_last;
	private Toolbarbutton btn_next;
	private Toolbarbutton btn_prev;
	private Toolbarbutton btn_first;
	private Toolbarbutton btn_add;
	private Toolbarbutton btn_search, show_new, show_all;
	private static HashMap<String, String> _actionDesc = new HashMap<String, String>();
	
	private Toolbarbutton btn_back, btn_search_client, file_create;
	private Toolbar tb;
	private Textbox merchant, parent, abrv_name, fax, full_name, cntry, city, reg_nr, street, post_ind, phone, cont_person, p_cntry, p_city, p_street, p_post_ind, mrc_phone, report_crit, e_mail, add_info, report_crit2, user_field, acc;
	private Textbox amerchant, aparent, aabrv_name, afax, afull_name, acntry, acity, areg_nr, astreet, apost_ind, aphone, acont_person, ap_cntry, ap_city, ap_street, ap_post_ind, amrc_phone, areport_crit, ae_mail, aadd_info, areport_crit2, aacc,
		auser_field;
	private Textbox fmerchant, fparent, fabrv_name, ffax, ffull_name, fcntry, fcity, freg_nr, fstreet, fpost_ind, fphone, fcont_person, fp_cntry, fp_city, fp_street, fp_post_ind, fmrc_phone, freport_crit, fe_mail, fadd_info, freport_crit2,
		fuser_field;
	private Textbox nCustName;
	private Paging merchantPaging;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	public MerchantFilter filter = new MerchantFilter();
	private String branch, alias;
	private RefCBox mcc, fmcc, amcc;
	
	PagingListModel model = null;
	PagingListModelNew modelnew = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	
	private Merchant current = new Merchant();
	private Cust nCust = new Cust();
	private List<RefData> list = new ArrayList<RefData>();
	
	public MerchantViewCtrl()
	{
		super('$', false, false);
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception
	{
		super.doAfterCompose(comp);
		binder = new AnnotateDataBinder(comp);
		binder.bindBean("current", this.current);
		binder.bindBean("nCust", this.nCust);
		binder.loadAll();
		
		String[] parameter = (String[]) param.get("ht");
		if (parameter != null)
		{
			_pageSize = Integer.parseInt(parameter[0]) / 44;
			dataGrid.setRows(_pageSize);
		}
		
		branch = (String) session.getAttribute("branch");
		alias = (String) session.getAttribute("alias");
		
		list = MerchantService.getListMcc(alias);
		mcc.setModel((new ListModelList(MerchantService.getListMcc(alias))));
		amcc.setModel((new ListModelList(MerchantService.getListMcc(alias))));
		fmcc.setModel((new ListModelList(MerchantService.getListMcc(alias))));
		
		_actionDesc = MerchantService.getActionDesc(alias);
		
		dataGrid.setItemRenderer(new ListitemRenderer()
		{
			public void render(final Listitem row, Object data) throws Exception
			{
				Merchant pMerchant = (Merchant) data;
				row.setValue(pMerchant);
				row.appendChild(new Listcell(pMerchant.getMerchant()));
				row.appendChild(new Listcell(pMerchant.getCity()));
				row.appendChild(new Listcell(_actionDesc.get(pMerchant.getAction())));
				row.appendChild(new Listcell(pMerchant.getFull_name()));
			}
		});
		
		dataGrid_client.setItemRenderer(new ListitemRenderer()
		{
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception
			{
				Cust cust = (Cust) data;
				row.setValue(cust);
				row.appendChild(new Listcell(cust.getNibbd()));
				row.appendChild(new Listcell(cust.getFull_Name()));
			}
		});
		
		refreshModel(_startPageNumber);
	}
	
	public void onPaging$merchantPaging(ForwardEvent event)
	{
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}
	
	private void refreshModel(int activePage)
	{
		merchantPaging.setPageSize(_pageSize);
		model = new PagingListModel(activePage, _pageSize, filter, "");
		
		if (_needsTotalSizeUpdate)
		{
			_totalSize = model.getTotalSize(filter, session.getAttribute("alias").toString());
			_needsTotalSizeUpdate = false;
		}
		
		merchantPaging.setTotalSize(_totalSize);
		
		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0)
		{
			this.current = (Merchant) model.getElementAt(0);
			sendSelEvt();
		}
	}
	
	// Omitted...
	public Merchant getCurrent()
	{
		return current;
	}
	
	public void setCurrent(Merchant current)
	{
		this.current = current;
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
		
		merchant.setMaxlength(15);
		parent.setMaxlength(15);
		abrv_name.setMaxlength(27);
		fax.setMaxlength(11);
		full_name.setMaxlength(78);
		cntry.setMaxlength(3);
		city.setMaxlength(15);
		reg_nr.setMaxlength(25);
		street.setMaxlength(30);
		post_ind.setMaxlength(6);
		phone.setMaxlength(11);
		cont_person.setMaxlength(30);
		p_cntry.setMaxlength(3);
		p_city.setMaxlength(15);
		p_street.setMaxlength(30);
		p_post_ind.setMaxlength(6);
		mrc_phone.setMaxlength(11);
		report_crit.setMaxlength(1);
		e_mail.setMaxlength(256);
		add_info.setMaxlength(30);
		report_crit2.setMaxlength(4);
		user_field.setMaxlength(10);
		
		if (current != null)  {
			mcc.setSelecteditem(current.getMcc());
			acc.setValue(current.getAcc());
		}
		
	}
	
	public void onClick$btn_back()
	{
		if (frm.isVisible())
		{
			frm.setVisible(false);
			grd.setVisible(true);
			btn_back.setImage("/images/file.png");
			btn_back.setLabel(Labels.getLabel("back"));
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
		clearAddGreed();
		dataGrid_client.getItems().clear();
		nCustName.setValue("");
		
		onDoubleClick$dataGrid$grd();
		frmgrd.setVisible(false);
		addgrd.setVisible(true);
		fgrd.setVisible(false);
		
		amerchant.setDisabled(true);
		aparent.setDisabled(true);
	}
	
	public void onClick$file_create() throws Exception
	{
		try
		{
			alert(com.is.tieto_globuz.file.FileWrite.fileWrite("C:\\GLOBUS\\").getName() + "");
			onClick$show_all();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			alert(e.getMessage());
		}
		
	}
	
	public void onClick$show_new() throws Exception
	{
		try
		{
			merchantPaging.setPageSize(_pageSize);
			modelnew = new PagingListModelNew(0, _pageSize, filter, "");
			
			_totalSize = modelnew.getTotalSize(null, alias);
			
			merchantPaging.setTotalSize(_totalSize);
			
			dataGrid.setModel((ListModel) modelnew);
			if (modelnew.getSize() > 0)
			{
				this.current = (Merchant) modelnew.getElementAt(0);
				sendSelEvt();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			alert(e.getMessage());
		}
		
	}
	
	public void onClick$show_all() throws Exception
	{
		refreshModel(_startPageNumber);
	}
	
	public void onClick$btn_search()
	{
		onDoubleClick$dataGrid$grd();
		frmgrd.setVisible(false);
		addgrd.setVisible(false);
		fgrd.setVisible(true);
	}
	
	public Integer checkLenth(Merchant merchant)
	{
		String descMerchant = "Код предприятия";
		String descParent = "Код главного предприятия";
		String descAbrv_name = "Сокращенное название предприятия";
		String descFax = "Номер факсимильного аппарата";
		String descFull_name = "Полное название предприятия";
		String descCntry = "Юридический адрес – код государства";
		String descCity = "Юридический адрес – город ";
		String descReg_nr = "Номер регистрации предприятия ";
		String descStreet = "Юридический адрес – улица ";
		String descPost_ind = "Юридический адрес – почтовый индекс";
		String descPhone = "Юридический адрес – телефон";
		String descCont_person = "Юридический адрес – контактное лицо";
		String descMcc = "Код MCC предприятия";
		String descP_cntry = "Физический адрес – код государства";
		String descP_city = "Физический адрес – город";
		String descP_street = "Физический адрес – улица";
		String descP_post_ind = "Физический адрес – почтовый индекс";
		String descMrc_phone = "Физический адрес – телефон";
		String descReport_crit = "Код группы для печати отчетов ";
		String descE_mail = "Электронный почтовый адрес";
		String descAdd_info = "Дополнительная информация ";
		String descReport_crit2 = "Код группы для печати отчетов ";
		String descUser_field = "Пользовательское поле ";
		
		if (merchant.getMerchant().length() > 15)
		{
			alert("Длина поля" + descMerchant + " не может быть больше чем " + "15 символов");
			return 0;
		}
		if (merchant.getParent().length() > 15)
		{
			alert("Длина поля" + descParent + " не может быть больше чем " + "15 символов");
			return 0;
		}
		if (merchant.getAbrv_name().length() > 27)
		{
			alert("Длина поля" + descAbrv_name + " не может быть больше чем " + "27 символов");
			return 0;
		}
		if (merchant.getFax().length() > 11)
		{
			alert("Длина поля" + descFax + " не может быть больше чем " + "11 символов");
			return 0;
		}
		if (merchant.getFull_name().length() > 78)
		{
			alert("Длина поля" + descFull_name + " не может быть больше чем " + "78 символов");
			return 0;
		}
		if (merchant.getCntry().length() > 3)
		{
			alert("Длина поля" + descCntry + " не может быть больше чем " + "3 символов");
			return 0;
		}
		if (merchant.getCity().length() > 15)
		{
			alert("Длина поля" + descCity + " не может быть больше чем " + "15  символов");
			return 0;
		}
		if (merchant.getReg_nr().length() > 25)
		{
			alert("Длина поля" + descReg_nr + " не может быть больше чем " + "25  символов");
			return 0;
		}
		if (merchant.getStreet().length() > 30)
		{
			alert("Длина поля" + descStreet + " не может быть больше чем " + "30  символов");
			return 0;
		}
		if (merchant.getPost_ind().length() > 6)
		{
			alert("Длина поля" + descPost_ind + " не может быть больше чем " + "6   символов");
			return 0;
		}
		if (merchant.getPhone().length() > 11)
		{
			alert("Длина поля" + descPhone + " не может быть больше чем " + "11  символов");
			return 0;
		}
		if (merchant.getCont_person().length() > 30)
		{
			alert("Длина поля" + descCont_person + " не может быть больше чем " + "30  символов");
			return 0;
		}
		if (merchant.getMcc().length() > 4)
		{
			alert("Длина поля" + descMcc + " не может быть больше чем " + "4   символов");
			return 0;
		}
		if (merchant.getP_cntry().length() > 3)
		{
			alert("Длина поля" + descP_cntry + " не может быть больше чем " + "3   символов");
			return 0;
		}
		if (merchant.getP_city().length() > 15)
		{
			alert("Длина поля" + descP_city + " не может быть больше чем " + "15  символов");
			return 0;
		}
		if (merchant.getP_street().length() > 30)
		{
			alert("Длина поля" + descP_street + " не может быть больше чем " + "30  символов");
			return 0;
		}
		if (merchant.getP_post_ind().length() > 6)
		{
			alert("Длина поля" + descP_post_ind + " не может быть больше чем " + "6   символов");
			return 0;
		}
		if (merchant.getMrc_phone().length() > 11)
		{
			alert("Длина поля" + descMrc_phone + " не может быть больше чем " + "11  символов");
			return 0;
		}
		if (merchant.getReport_crit().length() > 1)
		{
			alert("Длина поля" + descReport_crit + " не может быть больше чем " + "1   символов");
			return 0;
		}
		if (merchant.getE_mail().length() > 256)
		{
			alert("Длина поля" + descE_mail + " не может быть больше чем " + "256 символов");
			return 0;
		}
		if (merchant.getAdd_info().length() > 30)
		{
			alert("Длина поля" + descAdd_info + " не может быть больше чем " + "30  символов");
			return 0;
		}
		if (merchant.getReport_crit2().length() > 4)
		{
			alert("Длина поля" + descReport_crit2 + " не может быть больше чем " + "4   символов");
			return 0;
		}
		if (merchant.getUser_field().length() > 10)
		{
			alert("Длина поля" + descUser_field + " не может быть больше чем " + "10  символов");
			return 0;
		}
		
		return 1;
	}
	
	public void onClick$btn_save()
	{
		Res result = new Res();
		try
		{
			if (addgrd.isVisible())
			{
				if (amcc.getValue() == null || amcc.getValue().length() < 4)
				{
					alert("MCC Код выбран неправильно");
					return;
				}
				
				Merchant insMerchant =
					new Merchant(
						amerchant.getValue(),
						aparent.getValue(),
						aabrv_name.getValue(),
						afax.getValue(),
						afull_name.getValue(),
						acntry.getValue(),
						acity.getValue(),
						areg_nr.getValue(),
						astreet.getValue(),
						apost_ind.getValue(),
						aphone.getValue(),
						acont_person.getValue(),
						amcc.getValue(),
						ap_cntry.getValue(),
						ap_city.getValue(),
						ap_street.getValue(),
						ap_post_ind.getValue(),
						amrc_phone.getValue(),
						areport_crit.getValue(),
						ae_mail.getValue(),
						aadd_info.getValue(),
						areport_crit2.getValue(),
						auser_field.getValue(),
						"I",
						aacc.getValue()
					);
				
				if (checkLenth(insMerchant) > 0)
				{
					result = MerchantService.create(insMerchant);
					if (result.getCode() == 0)
					{
						alert(result.getName());
						return;
					}
					else alert(result.getName());
					CheckNull.clearForm(addgrd);
					frmgrd.setVisible(true);
					addgrd.setVisible(false);
					fgrd.setVisible(false);
				}
				else return;
				
			}
			else if (fgrd.isVisible())
			{
				filter = new MerchantFilter();
				
				filter.setMerchant(fmerchant.getValue());
				filter.setParent(fparent.getValue());
				filter.setAbrv_name(fabrv_name.getValue());
				filter.setFax(ffax.getValue());
				filter.setFull_name(ffull_name.getValue());
				filter.setCntry(fcntry.getValue());
				filter.setCity(fcity.getValue());
				filter.setReg_nr(freg_nr.getValue());
				filter.setStreet(fstreet.getValue());
				filter.setPost_ind(fpost_ind.getValue());
				filter.setPhone(fphone.getValue());
				filter.setCont_person(fcont_person.getValue());
				
				if (fmcc.getValue() == null || fmcc.getValue().length() < 4)
				{
					alert("MCC Код выбран неправильно");
					return;
				}
				
				filter.setMcc(fmcc.getValue());
				filter.setP_cntry(fp_cntry.getValue());
				filter.setP_city(fp_city.getValue());
				filter.setP_street(fp_street.getValue());
				filter.setP_post_ind(fp_post_ind.getValue());
				filter.setMrc_phone(fmrc_phone.getValue());
				filter.setReport_crit(freport_crit.getValue());
				filter.setE_mail(fe_mail.getValue());
				filter.setAdd_info(fadd_info.getValue());
				filter.setReport_crit2(freport_crit2.getValue());
				filter.setUser_field(fuser_field.getValue());
			}
			else
			{
				if (!current.getAction().equals("S"))
				{
					if (!current.getAction().equals("I"))
					{
						current.setAction("U");
					}
					
					current.setMcc(mcc.getValue());
					current.setAcc(acc.getValue());
					
					MerchantService.update(current);
				}
				else
				{
					alert("В данном состоянии нельзя произвести обновление !");
					return;
				}
				
			}
			onClick$btn_back();
			refreshModel(_startPageNumber);
			SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
			Events.sendEvent(evt);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			alert(e.getMessage());
		}
	}
	
	public void onClick$btn_cancel()
	{
		if (fgrd.isVisible())
		{
			filter = new MerchantFilter();
		}
		
		onClick$btn_back();
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		CheckNull.clearForm(addgrd);
		CheckNull.clearForm(fgrd);
		refreshModel(_startPageNumber);
	}
	
	public void onClick$btn_search_client()
	{
		clearAddGreed();
		
		List<Cust> custs =  MerchantService.findClients(alias, nCustName.getValue());
		if (custs.size() < 1)
		{
			alert("Данные не найдены");
			return;
		}
		else dataGrid_client.setModel(new BindingListModelList(custs , false));
		
	}
	
	public void setMerch(String NIBBD) 
	{
		if (NIBBD.length() != 8)
		{
			alert("Введен неправильный код НИББД");
			return;
		}
		
		Merchant merchant = MerchantService.getMerchantDetails(NIBBD, alias, branch);
		if (merchant.getMerchant() != null)
		{
			amerchant.setValue(getValue(merchant.getMerchant(), 15));
			aparent.setValue(getValue(merchant.getParent(), 15));
			aabrv_name.setValue(getValue(merchant.getAbrv_name(), 27));
			afax.setValue(getValue(merchant.getFax(), 11));
			afull_name.setValue(getValue(merchant.getFull_name(), 78));
			acntry.setValue(getValue(merchant.getCntry(), 3));
			acity.setValue(getValue(merchant.getCity(), 15));
			areg_nr.setValue(getValue(merchant.getReg_nr(), 25));
			astreet.setValue(getValue(merchant.getStreet(), 30));
			apost_ind.setValue(getValue(merchant.getPost_ind(), 6));
			aphone.setValue(getValue(merchant.getPhone(), 11));
			acont_person.setValue(getValue(merchant.getCont_person(), 30));
			amcc.setValue(getValue(merchant.getMcc(), 4));
			ap_cntry.setValue(getValue(merchant.getP_cntry(), 3));
			ap_city.setValue(getValue(merchant.getP_city(), 15));
			ap_street.setValue(getValue(merchant.getP_street(), 30));
			ap_post_ind.setValue(getValue(merchant.getP_post_ind(), 6));
			amrc_phone.setValue(getValue(merchant.getMrc_phone(), 11));
			areport_crit.setValue(getValue(merchant.getReport_crit(), 1));
			ae_mail.setValue(getValue(merchant.getE_mail(), 256));
			aadd_info.setValue(getValue(merchant.getAdd_info(), 30));
			areport_crit2.setValue(getValue(merchant.getReport_crit2(), 4));
			auser_field.setValue(getValue(merchant.getUser_field(), 10));
		}
		else
		{
			alert("Клиент не найден");
			nCustName.setValue(null);
		}
	}
	
	public void onSelect$amcc()
	{
		//System.out.println("amcc.getValue()=> " + amcc.getValue());
	}
	
	private void clearAddGreed()
	{
		amerchant.setValue(null);
		aparent.setValue(null);
		aabrv_name.setValue(null);
		afax.setValue(null);
		afull_name.setValue(null);
		acntry.setValue(null);
		acity.setValue(null);
		areg_nr.setValue(null);
		astreet.setValue(null);
		apost_ind.setValue(null);
		aphone.setValue(null);
		acont_person.setValue(null);
		amcc.setValue(null);
		ap_cntry.setValue(null);
		ap_city.setValue(null);
		ap_street.setValue(null);
		ap_post_ind.setValue(null);
		amrc_phone.setValue(null);
		areport_crit.setValue(null);
		ae_mail.setValue(null);
		aadd_info.setValue(null);
		areport_crit2.setValue(null);
		auser_field.setValue(null);
		
		amerchant.setMaxlength(15);
		aparent.setMaxlength(15);
		aabrv_name.setMaxlength(27);
		afax.setMaxlength(11);
		afull_name.setMaxlength(78);
		acntry.setMaxlength(3);
		acity.setMaxlength(15);
		areg_nr.setMaxlength(25);
		astreet.setMaxlength(30);
		apost_ind.setMaxlength(6);
		aphone.setMaxlength(11);
		acont_person.setMaxlength(30);
		//amcc.setMaxlength(4);
		ap_cntry.setMaxlength(3);
		ap_city.setMaxlength(15);
		ap_street.setMaxlength(30);
		ap_post_ind.setMaxlength(6);
		amrc_phone.setMaxlength(11);
		areport_crit.setMaxlength(1);
		ae_mail.setMaxlength(256);
		aadd_info.setMaxlength(30);
		areport_crit2.setMaxlength(4);
		auser_field.setMaxlength(10);
	}
	
	private String getValue(String string, int size)
	{
		if (string == null) string = "";
		
		return string.length() > size ? string.substring(0, size - 1) : string;
	}
	
	public void onSelect$dataGrid_client()
	{
		Cust newcust = (Cust) dataGrid_client.getSelectedItem().getValue();
		setMerch(newcust.getNibbd());
	}

	public Cust getnCust()
	{
		return this.nCust;
	}

	public void setnCust(Cust nCust)
	{
		this.nCust = nCust;
	}
	
	
}
