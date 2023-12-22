package com.is.tietovisa.customer;

import java.io.IOException;

import java.net.ConnectException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.tietovisa.StringUtils;
import com.is.tietovisa.model.ListType_GenericHolder;
import com.is.tietovisa.model.Tclient;
import com.is.utils.CheckNull;
import com.is.utils.Res;

public class AddCstViewCtrl extends GenericForwardComposer {
	private static final long serialVersionUID = 1L;
	public CustomerFilter filter = new CustomerFilter();
	public Customer current = new Customer();
	public Customer copyOfCurrent;

	private AnnotateDataBinder binder;

	private int _pageSize;
	private int _startPageNumber = 0;
	private int _totalSize;
	private String un;
	private String pwd;
	private String branch;
	private String alias;
	private String curip;
	private int uid;
	static String openwayEndpoint;
	private boolean add_way;
	private boolean add_bnk;
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat bdf = new SimpleDateFormat("dd.mm.yyyy");

	private Window add_everywhere, add_account, add_card_wnd;
	private Datebox add_everywhere$ap_birthday;
	// private Datebox add_everywhere$ap_passport_date_expiration;
	private Datebox add_everywhere$ap_passport_date_registration;
	private Datebox add_everywhere$ao_address_fact_date;
	private Grid add_everywhere$addgrdl;
	private Grid add_everywhere$addgrdr;
	private Grid add_account$addgrdl;
	private Grid add_card_wnd$addgrdl;

	// private Textbox add_everywhere$aid_client;
	private Textbox add_everywhere$ao_city;
	private Textbox add_everywhere$ap_zip_code;
	private Textbox add_everywhere$ap_birth_place;
	private Textbox add_everywhere$ap_email_address;
	private Textbox add_everywhere$ap_family;
	private Textbox add_everywhere$ap_first_name;
	// private Textbox add_everywhere$ap_inps;
	private Textbox add_everywhere$ap_number_tax_registration;
	private Textbox add_everywhere$ap_passport_number;
	private Textbox add_everywhere$ap_passport_place_registration;
	private Textbox add_everywhere$ap_passport_serial;
	private Textbox add_everywhere$ap_patronymic;
	// private Textbox add_everywhere$ap_phone_home;
	private Textbox add_everywhere$ap_phone_mobile;
	private Textbox add_everywhere$ap_post_address;
	private Textbox add_everywhere$ap_pinfl;
	// private Textbox add_everywhere$customerId;
	private Textbox add_everywhere$ao_security_name;
	private Textbox add_everywhere$ao_post_address_fact;
	private Textbox add_account$id_order;
	private Textbox add_account$o_comment_text;
	private Textbox add_card_wnd$o_rbs_number;
	private Textbox add_card_wnd$o_comment_text;

	// private Paging bankdataPaging;
	private PagingListModel model;
	private Listbox branch_customers, accGrid, cardGrid;

	private Textbox txbId_client;
	private Textbox txbPinfl;

	private Textbox txbPassportSerial;
	private Textbox txbName;
	private Datebox dbxB_date;
	private Textbox txbId_tieto, txbCardNumber, txbMobilePhone, txbLastName;

	public AddCstViewCtrl() {
		super('$', false, false);
		this._pageSize = 50;
		add_way = false;
		add_bnk = false;

	}

	public void doAfterCompose(final Component comp) throws Exception, ConnectException, SQLException {
		super.doAfterCompose(comp);

		binder = new AnnotateDataBinder(comp);
		this.self.setAttribute("binder", (Object) this.binder);

		binder.bindBean("filter", this.filter);
		binder.bindBean("current", this.current);
		// binder.bindBean("curr_acc", this.curr_acc);
		// binder.bindBean("curr_card", this.curr_card);

		this.uid = (Integer) this.session.getAttribute("uid");
		this.un = (String) this.session.getAttribute("un");
		this.pwd = (String) this.session.getAttribute("pwd");
		this.branch = (String) this.session.getAttribute("branch");
		this.alias = (String) this.session.getAttribute("alias");
		this.curip = (String) session.getAttribute("curip");
		if (openwayEndpoint == null || openwayEndpoint == "" || openwayEndpoint.equals(""))
			openwayEndpoint = ConnectionPool.getValue("OPENWAY_ENDPOINT");
		openwayEndpoint = "http://localhost:8080/VisaTiGV/ListCustomers";
		// filter.setP_pinfl("56789012340078");
		// filter.setId_client("60000001");
		filter.setEndpoint(openwayEndpoint);

		branch_customers.setItemRenderer(new ListitemRenderer() {
			@Override
			public void render(Listitem row, Object data, int index) {
				Customer vCustomer = (Customer) data;
				row.setAttribute("row", (Object) row);
				row.setAttribute("sign_record", vCustomer.getSign_record());
				// row.appendChild(new Listcell(""));
				
				if ( vCustomer.getSign_record().equals("1") || vCustomer.getSign_record().equals("2") ) {
					// unlink button
					Listcell br_cell = new Listcell();
					Toolbarbutton btbreak = new Toolbarbutton();
					btbreak.setLabel("");
					// btbreak.setAttribute("lnk_id", lnk);
					btbreak.setImage("/images/link_break16.png");
					btbreak.setTooltiptext("Развязать связку");
					btbreak.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
						@Override
						public void onEvent(Event event) throws Exception {
							final int lnk_id = (Integer) event.getTarget().getAttribute("lnk_id");
							Messagebox.show("test break?", "test break", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION,
									new EventListener<Event>() {
										@Override
										public void onEvent(Event event) throws Exception {
											// TODO Auto-generated method stub
											int answer = (Integer) event.getData();
											if (answer == Messagebox.OK) {
												refreshModel(0);
											} else
												return;
										}
									});
						}
					});
					btbreak.setStyle("padding: 0;");
					br_cell.appendChild(btbreak);
					row.appendChild(br_cell);
				} else {
					row.appendChild(new Listcell(""));
				}
				
				if ( vCustomer.getSign_record().equals("1") ) {
					// edit everwhere
					Listcell edit_cell = new Listcell();
					Toolbarbutton btedit = new Toolbarbutton();
					btedit.setLabel("");
					// btbreak.setAttribute("lnk_id", lnk);
					btedit.setImage("/images/edit.png");
					btedit.setTooltiptext("Редактировать");
					btedit.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
						@Override
						public void onEvent(Event event) throws Exception {
							final int lnk_id = (Integer) event.getTarget().getAttribute("lnk_id");
							Messagebox.show("test edit?", "test edit", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION,
									new EventListener<Event>() {
										@Override
										public void onEvent(Event event) throws Exception {
											// TODO Auto-generated method stub
											int answer = (Integer) event.getData();
											if (answer == Messagebox.OK) {
												refreshModel(0);
											} else
												return;
										}
									});
						}
					});
					btedit.setStyle("padding: 0;");
					edit_cell.appendChild(btedit);
					row.appendChild(edit_cell);
				} else if (vCustomer.getSign_record().equals("5") ) {
					//Add_nci_or_find_from_nci&link
					Listcell edit_cell = new Listcell();
					Toolbarbutton btedit = new Toolbarbutton();
					btedit.setLabel("");
					// btbreak.setAttribute("lnk_id", lnk);
					btedit.setImage("/images/add-link-16.png");
					btedit.setTooltiptext("Добавить/привязать в НСИ");
					btedit.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
						@Override
						public void onEvent(Event event) throws Exception {
							final int lnk_id = (Integer) event.getTarget().getAttribute("lnk_id");
							Messagebox.show("test Add_nci_or_find_from_nci?", "test Add_nci_or_find_from_nci", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION,
									new EventListener<Event>() {
										@Override
										public void onEvent(Event event) throws Exception {
											// TODO Auto-generated method stub
											int answer = (Integer) event.getData();
											if (answer == Messagebox.OK) {
												refreshModel(0);
											} else
												return;
										}
									});
						}
					});
					btedit.setStyle("padding: 0;");
					edit_cell.appendChild(btedit);
					row.appendChild(edit_cell);
				} else {
					row.appendChild(new Listcell(""));	
				}

				//4талик
				
				Listcell listcell = new Listcell();
				Toolbarbutton btn1 = new Toolbarbutton();
				btn1.setLabel("");
				btn1.setImage("/images/001.png");
				btn1.setTooltiptext("Добавить/найти в Тието");
				btn1.setStyle("padding: 0;");
				listcell.appendChild(btn1);
				row.appendChild(listcell);

				listcell = new Listcell();
				btn1 = new Toolbarbutton();
				btn1.setLabel("");
				btn1.setImage("/images/002.png");
				btn1.setTooltiptext("Добавить/найти в Тието");
				btn1.setStyle("padding: 0;");
				listcell.appendChild(btn1);
				row.appendChild(listcell);

				listcell = new Listcell();
				btn1 = new Toolbarbutton();
				btn1.setLabel("");
				btn1.setImage("/images/003.png");
				btn1.setTooltiptext("Добавить/найти в Тието");
				btn1.setStyle("padding: 0;");
				listcell.appendChild(btn1);
				row.appendChild(listcell);

				listcell = new Listcell();
				btn1 = new Toolbarbutton();
				btn1.setLabel("");
				btn1.setImage("/images/004.png");
				btn1.setTooltiptext("Добавить/найти в Тието");
				btn1.setStyle("padding: 0;");
				listcell.appendChild(btn1);
				row.appendChild(listcell);
				
				
				//4та енд
				

				row.appendChild(new Listcell(StringUtils.secureNull(vCustomer.getBranch())));
				row.appendChild(new Listcell(StringUtils.secureNull(vCustomer.getId_client())));
				row.appendChild(new Listcell(StringUtils.secureNull(vCustomer.getName())));
				row.appendChild(new Listcell(StringUtils.secureNull(vCustomer.getP_birthday())));
				row.appendChild(new Listcell(StringUtils.secureNull(vCustomer.getP_pinfl())));
				/*
				 * row.appendChild(new
				 * Listcell(StringUtils.secureNull(vCustomer.getP_passport_serial()) +
				 * StringUtils.secureNull(vCustomer.getP_passport_number())));
				 */
				//knopka
				if ( vCustomer.getSign_record().equals("2") ) {
					// unlink button
					Listcell cell = new Listcell();
					Toolbarbutton btn = new Toolbarbutton();
					btn.setLabel("");
					// btbreak.setAttribute("lnk_id", lnk);
					btn.setImage("/images/add-link-color-16.png");
					btn.setTooltiptext("Добавить в Тието");
					btn.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
						@Override
						public void onEvent(Event event) throws Exception {
							final int lnk_id = (Integer) event.getTarget().getAttribute("lnk_id");
							Messagebox.show("test Добавить в Тието?", "test Добавить в Тието", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION,
									new EventListener<Event>() {
										@Override
										public void onEvent(Event event) throws Exception {
											// TODO Auto-generated method stub
											int answer = (Integer) event.getData();
											if (answer == Messagebox.OK) {
												refreshModel(0);
											} else
												return;
										}
									});
						}
					});
					btn.setStyle("padding: 0;");
					cell.appendChild(btn);
					row.appendChild(cell);
				} else if ( vCustomer.getSign_record().equals("4") ) {
					// unlink button
					Listcell cell = new Listcell();
					Toolbarbutton btn = new Toolbarbutton();
					btn.setLabel("");
					// btbreak.setAttribute("lnk_id", lnk);
					btn.setImage("/images/add-link-color-16.png");
					btn.setTooltiptext("Добавить/найти в Тието");
					btn.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
						@Override
						public void onEvent(Event event) throws Exception {
							final int lnk_id = (Integer) event.getTarget().getAttribute("lnk_id");
							Messagebox.show("test Добавить/найти в Тието?", "test Добавить/найти в Тието", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION,
									new EventListener<Event>() {
										@Override
										public void onEvent(Event event) throws Exception {
											// TODO Auto-generated method stub
											int answer = (Integer) event.getData();
											if (answer == Messagebox.OK) {
												refreshModel(0);
											} else
												return;
										}
									});
						}
					});
					btn.setStyle("padding: 0;");
					cell.appendChild(btn);
					row.appendChild(cell);
				} else {
					row.appendChild(new Listcell(""));	
				}
				
				row.appendChild(new Listcell(StringUtils.secureNull(vCustomer.getT_client())));
				row.appendChild(new Listcell(StringUtils.secureNull(vCustomer.getT_client_b())));
				row.appendChild(new Listcell(StringUtils.secureNull(vCustomer.getT_surname()) + " "
						+ StringUtils.secureNull(vCustomer.getT_f_names()) + " "
						+ StringUtils.secureNull(vCustomer.getT_m_name())));
				row.appendChild(
						new Listcell(vCustomer.getT_b_date() != null ? df.format(vCustomer.getT_b_date()) : ""));
				row.appendChild(new Listcell(StringUtils.secureNull(vCustomer.getT_id_card()) + "/"
						+ StringUtils.secureNull(vCustomer.getT_person_code())));

			}
		});

		binder.loadAll();

	}

	public void onClick$tbtn_search() {

		/*
		 * if (this.filter == null) { this.filter = new CustomerFilter(); }
		 * this.filter.setId_client(txbId_client.getValue());
		 * this.filter.setP_pinfl(txbPinfl.getValue());
		 * this.filter.setName(txbName.getValue());
		 * this.filter.setP_family(txbLastName.getValue());
		 * this.filter.setP_birthday(this.filter.getB_date());
		 */

		if (filter.getBranch() == null)
			filter.setBranch(branch);
		// this.filter.setId_client("60000001");
		// this.filter.setP_pinfl("56789012340078");
		// this.bfilter = this.filter;
		this.filter.setEndpoint(openwayEndpoint);
		this.refreshModel(/* this._startPageNumber */0);
	}

	private void refreshModel(final int activePage) {
		// nci bankdan va tietodan klientlar ruyxatini olish
		this.model = new PagingListModel(activePage, this._pageSize, this.filter, this.alias);
		System.out.println(this.model);
		// tietodan ham olamiz va ikkala natijani birlashtirib kursatamiz
		// List<Customer> tieto_customes =
		// CustomerService.getCustomers_tieto(this.filter, this.alias, openwayEndpoint,
		// true);
		// System.out.println(tieto_customes);
		this.branch_customers.setModel((ListModel) this.model);
		if (model.getSize() > 0) {
			branch_customers.setSelectedIndex(0);
			sendSelEvt();
		}

	}

	public void onOkToFilter(Event event) {
		onClick$tbtn_search();
	}

	public void onClick$btn_add_everywhere() {
		current = new Customer();
		this.add_way = true;
		this.add_bnk = true;

		/*
		 * CheckNull.clearForm(this.add_everywhere$addgrdr);
		 * CheckNull.clearForm(this.add_everywhere$addgrdl);
		 */
		this.add_everywhere.setTitle("�������� ������� [����] - [WAY4]");

		// loaddata qilamiz shu yerda
		loadRefData();
		//
		// current.setO_client_type("PR");
		current.setCode_resident("1");
		current.setCode_country("860");
		current.setP_code_citizenship("860");
		// CustomerService.prepareFakeValues(current);

		this.add_everywhere.setVisible(true);
		binder.loadComponent(add_everywhere);

	}

	public void loadRefData() {

		/*
		 * if (add_everywhere$acode_resident.getItems().size() == 0)
		 * add_everywhere$acode_resident.setModel(new ListModelList(Utils
		 * .getRezCl(this.alias)));
		 */
	}

	public void loadRefAccData() {

		/*
		 * if (add_card_wnd$o_product_code1.getItems().size() == 0)
		 * add_card_wnd$o_product_code1.setModel(new ListModelList(
		 * CustomerService.getSubProduct_code1_way4(alias)));
		 */

	}

	public void onClick$close_btn$add_everywhere() {
		// this.add_everywhere.setVisible(false);
		// this.fl_edit = false;

		binder.loadComponent(add_everywhere);

	}

	public void onFocus$ao_category_client$add_everywhere() {
		// bu narsa klient qushish tugmasini modulga kirgandan sung faqatgina
		// birinchi marta
		// bosganda kerak buladi. chunki malumotlar rcombobox ga yuklanmagan
		// buladi ushanda
		// agar spravochnik malumotlar rcombobox ga hali yuklanmagan bulsa
		// binder.loadcomponent qilamiz bir marta
		/*
		 * if (add_everywhere$acode_resident.getItems().size() == 0 ||
		 * (add_everywhere$acode_resident.getValue() != current .getCode_resident())) {
		 * binder.loadComponent(add_everywhere); }
		 */
		/*
		 * System.out.println("jj "+add_everywhere$acode_resident.getItems().size ());
		 * while (add_everywhere$acode_resident.getItems().size()==0) { try {
		 * TimeUnit.SECONDS.sleep(1); } catch(InterruptedException ex) {
		 * //Thread.currentThread().interrupt();
		 * System.out.println("err openway while delay : " + ex.getMessage()); } }
		 * System.out.println("kones "+add_everywhere$acode_resident.getItems()
		 * .size());
		 */
	}

	public void onChange$ap_code_adr_region$add_everywhere() {

	}

	/*
	 * public void onSelect$accGrid() { if (curr_acc != null &&
	 * curr_acc.getProductCode1() != null) {
	 * 
	 * List<CardInfo> carInfoList = new ArrayList<CardInfo>();
	 * 
	 * // ABS dan qushimcha (BF_OPENWAY_CONTRACT_Card) List<CardInfo> cardList =
	 * CustomerService.getContractCardList_ABS( current.getBranch(),
	 * current.getId_client(), curr_acc.getProductCode1(), alias);
	 * 
	 * // WAYdan UFXMsgReqContractResp clResp = CustomerService
	 * .getCustomersContract_openway(current.getBranch(), current.getId_client(),
	 * current.getP_pinfl(), openwayEndpoint, ""); ArrayList<ContractRs>
	 * contractCardList = new ArrayList<ContractRs>();
	 * 
	 * if (clResp.getResp_code().equals("0")) { ArrayList<ContractRs> contractList =
	 * clResp.getMsgDataReq() .getApplication().getDataRsObject().getContractRs();
	 * for (int i = 0; i < contractList.size(); i++) { if
	 * (contractList.get(i).getContract().getProduct()
	 * .getAddInfo().getParm().getValue().equals("Card")) {
	 * contractCardList.add(contractList.get(i)); } } }
	 * 
	 * for (int i = 0; i < cardList.size(); i++) { CardInfo inf = new CardInfo();
	 * inf.setBranch(branch); inf.setClient_id(cardList.get(i).getClient_id());
	 * inf.setMasterProductCode1(cardList.get(i) .getMasterProductCode1());
	 * inf.setProductCode1(cardList.get(i).getProductCode1());
	 * inf.setRbsNumberIbs(cardList.get(i).getRbsNumberIbs());
	 * inf.setContractName(cardList.get(i).getContractName());
	 * inf.setCbsNumber(cardList.get(i).getCbsNumber()); //
	 * inf.setAb_expirity(cardList.get(i).getDate_open()); //
	 * inf.setContractNumber(""); // inf.setAcc_bal(cardList.get(i).getAcc_bal());
	 * // inf.setId_order(cardList.get(i).getId_order()); inf.setWay_exist(false);
	 * 
	 * // shetimizga mos keladigan kontrakt topsak is_way ni true // qilamiz, //
	 * kerakli polelarni tuldirtiramiz va kontraktlistdan uchirib // quyamiz for
	 * (int j = 0; j < contractCardList.size(); j++) { if
	 * (contractCardList.get(j).getContract().getContractIDT().getCBSNumber()
	 * .equals(cardList.get(i).getRbsNumberIbs())) { inf.setWay_exist(true); //
	 * inf.setCbsNumber(contractCardList.get(j) //
	 * .getContractIDT().getCBSNumber());
	 * inf.setContractNumber(contractCardList.get(j).getContract()
	 * .getContractIDT().getContractNumber()); if
	 * (contractCardList.get(j).getContract().getProductionParmsObject() != null)
	 * inf.setCardExpiry(contractCardList.get(j).getContract()
	 * .getProductionParmsObject().getCardExpiry()); else inf.setCardExpiry("-");
	 * inf.setSTATUS(contractCardList.get(j).getInfo().getStatus().getStatusDetails(
	 * )); inf.setSTATUS2(contractCardList.get(j).getInfo().getStatus().
	 * getProductionStatus()); contractCardList.remove(j); break; } }
	 * 
	 * // carInfoList.add(inf); }
	 * 
	 * for (int j = 0; j < contractCardList.size(); j++) { CardInfo inf = new
	 * CardInfo(); inf.setWay_exist(true);
	 * inf.setCbsNumber(contractCardList.get(j).getContract().getContractIDT()
	 * .getCBSNumber());
	 * inf.setContractNumber(contractCardList.get(j).getContract().getContractIDT()
	 * .getContractNumber()); if (contractCardList.get(j).getContract()
	 * .getProductionParmsObject()!=null)
	 * inf.setCardExpiry(contractCardList.get(j).getContract()
	 * .getProductionParmsObject().getCardExpiry()); else inf.setCardExpiry("-");
	 * inf.setSTATUS(contractCardList.get(j).getInfo().getStatus().getStatusDetails(
	 * )); inf.setSTATUS2(contractCardList.get(j).getInfo().getStatus().
	 * getProductionStatus()); carInfoList.add(inf); }
	 * 
	 * // accGrid.setModel(new ListModelList(infoList)); cardGrid.setModel(new
	 * BindingListModelList(carInfoList, true)); //
	 * 
	 * if (carInfoList.size() > 0) { cardGrid.setSelectedIndex(0); // this.curr_acc
	 * =(AccInfo) infoList.get(0); SelectEvent evt = new SelectEvent("onSelect",
	 * cardGrid, cardGrid.getSelectedItems()); Events.sendEvent(evt); //
	 * binder.loadComponent(accGrid); }
	 * 
	 * } }
	 */

	public void onClick$btn_add_acc() {
		if (current == null || current.getId_client() == null) {
			alert("������ �� ������");
			return;
		}
		/*
		 * curr_acc = new AccInfo(); curr_acc.setBranch(branch);
		 * curr_acc.setClient(current.getId_client());
		 */

		/* CheckNull.clearForm(this.add_account$addgrdl); */
		this.add_everywhere.setTitle("�������� ����� � ��������� ��������� [����] - [WAY4]");

		// loaddata qilamiz shu yerda
		loadRefAccData();
		//

		this.add_account.setVisible(true);
		binder.loadComponent(add_account);

	}

	public void onClick$btn_add_card() {
		alert("www");

	}

	public void onChange$acc_bal$add_account() {
		// curr_acc.setAcc_bal(add_account$acc_bal.getValue());
	}

	public void onChange$o_product_code1$add_account() {
		// curr_acc.setProductCode1(add_account$o_product_code1.getValue());
	}

	public void onChange$o_product_code1$add_card_wnd() {
		// curr_card.setProductCode1(add_card_wnd$o_product_code1.getValue());
	}

	private void sendSelEvt() {
		/*
		 * if (dataGrid.getSelectedIndex()==0){ btn_first.setDisabled(true);
		 * btn_prev.setDisabled(true); }else{ btn_first.setDisabled(false);
		 * btn_prev.setDisabled(false); }
		 * if(dataGrid.getSelectedIndex()==(model.getSize()-1)){
		 * btn_next.setDisabled(true); btn_last.setDisabled(true); }else{
		 * btn_next.setDisabled(false); btn_last.setDisabled(false); }
		 */
		SelectEvent evt = new SelectEvent("onSelect", branch_customers, branch_customers.getSelectedItems());
		Events.sendEvent(evt);
	}

	public CustomerFilter getFilter() {
		return filter;
	}

	public void setFilter(CustomerFilter filter) {
		this.filter = filter;
	}

	public Customer getCurrent() {
		return current;
	}

	public void setCurrent(Customer current) {
		this.current = current;
	}

}
