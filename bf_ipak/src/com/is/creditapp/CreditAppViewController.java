package com.is.creditapp;

import java.util.Date;
import java.util.List;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Html;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Row;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;

import com.is.ISLogger;
import com.is.bpri.BproductService;
import com.is.bpri.utils.Utils;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.is.utils.Res;

@SuppressWarnings("serial")
public class CreditAppViewController extends GenericForwardComposer {

	private Div frm, katmwnd$katmwnd_div;
	private Listbox dataGrid;
	private Div grd;
	private Grid addgrd, facgrd, idgrd, grdclient, grdcredapp, grddop, grdniki;
	private Label typelabel, idlabel;
	private Row typerow;
	private Toolbarbutton btn_last;
	private Toolbarbutton btn_next;
	private Toolbarbutton btn_prev;
	private Toolbarbutton btn_first;
	private Toolbarbutton btn_back;
	private Toolbarbutton btn_rejectdata, btn_reject, btn_save, btn_confirm,
			btn_cl_refresh, btn_add, btn_search;
	private Datebox v_date, end_date;
	private Doublebox amount;
	private RefCBox etype, fac_class_id, fac_j_code_organ_direct, fac_resident,
			dbbranch, sign_client, rezkl, client_type, is_letter, nwp, code,
			type_zm, currency, bank_inps, reqtype, branch_id, shifr_id,
			kred_id, resolve_org;
	private Textbox client_name, branchid, qw, id_client, reqnum, dbinn,
			dbaddress, inn_passport, inn_org, name_org;
	private Paging ni_reqPaging;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private String alias, un, pw;
	private String btn = "";
	private String branch = "";
	private long id = -1;
	public CreditAppFilter filter = new CreditAppFilter();;
	private PagingListModel model = null;
	private AnnotateDataBinder binder;
	private CreditApp current = new CreditApp();
	private Include include;
	private String cancel = "";
	private String customer = "";
	private Window katmwnd;
	private Html html = new Html();

	public CreditAppViewController() {
		super('$', false, false);
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		try {
			super.doAfterCompose(comp);
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
		try {
			binder = new AnnotateDataBinder(comp);
			binder.bindBean("current", this.current);
			binder.loadAll();

			String[] parameter = (String[]) param.get("ht");
			if (parameter != null) {
				_pageSize = Integer.parseInt(parameter[0]) / 36;
				dataGrid.setRows(Integer.parseInt(parameter[0]) / 36);
				System.out.println("ht-------RRRRR:" + param.get("ht"));
			}
			if (this.param.get("customer") != null) {
				parameter = (String[]) this.param.get("customer");
				customer = parameter[0];
				System.out.println("customer_credapp----" + customer);
			}
			parameter = (String[]) param.get("search_clients");
			// System.out.println("search_clents---RRR:"+parameter[0]);
			if (this.param.get("search_clients") != null) {
				parameter = (String[]) param.get("search_clients");
				// parameter = (String[]) this.param.get("client_id");
				filter.setId_client(parameter[0]);
				System.out.println("customer_credapp22----" + parameter[0]);
			}
			branch = (String) session.getAttribute("branch");
			alias = (String) session.getAttribute("alias");
			un = (String) session.getAttribute("un");
			pw = (String) session.getAttribute("pwd");

			loadRefCBoxModels();
			dataGrid.setItemRenderer(new ListitemRenderer() {

				public void render(Listitem row, Object data) throws Exception {
					try {
						CreditApp pni_req = (CreditApp) data;
						row.setValue(pni_req);
						row.appendChild(new Listcell(pni_req.getId() + ""));
						row.appendChild(new Listcell(pni_req.getBranch()));
						row.appendChild(new Listcell(pni_req.getId_client()));
						System.out.println("pni_req----"
								+ pni_req.getId_client());
						// row.appendChild(new Listcell(pni_req.getReqnum()));
						// row.appendChild(new
						// Listcell(pni_req.getReqtype()+""));
						// row.appendChild(new
						// Listcell(pni_req.getBranch_id()));
						// row.appendChild(new Listcell(pni_req.getKred_id()));
						// row.appendChild(new Listcell(pni_req.getShifr_id()));
						// row.appendChild(new Listcell(pni_req.getCurrency()));
						row.appendChild(new Listcell(pni_req.getAmount() + ""));
						row.appendChild(new Listcell(pni_req.getState_name()));
						row.appendChild(new Listcell(CreditAppService
								.getNikiState(pni_req.getId() + "",
										pni_req.getBranch(),
										Utils.getAlias(pni_req.getBranch()))));
						// row.appendChild(new
						// Listcell(pni_req.getEnd_date()+""));
						// row.appendChild(new Listcell(pni_req.getDbinn()));
						// row.appendChild(new Listcell(pni_req.getDbbranch()));
						// row.appendChild(new
						// Listcell(pni_req.getDbaddress()));
						// row.appendChild(new
						// Listcell(pni_req.getV_date()+""));
						// row.appendChild(new
						// Listcell(pni_req.getResolve_org()));
						// row.appendChild(new
						// Listcell(pni_req.getIs_letter()));
						// row.appendChild(new
						// Listcell(pni_req.getSign_client()));
						// row.appendChild(new Listcell(pni_req.getRezkl()));
						// row.appendChild(new
						// Listcell(pni_req.getClient_type()));
						// row.appendChild(new
						// Listcell(pni_req.getInn_passport()));
						// row.appendChild(new
						// Listcell(pni_req.getFac_resident()));
						// row.appendChild(new
						// Listcell(pni_req.getFac_j_code_organ_direct()));
						// row.appendChild(new
						// Listcell(pni_req.getFac_class_id()));
						// row.appendChild(new Listcell(pni_req.getInn_org()));
						// row.appendChild(new Listcell(pni_req.getName_org()));
						// row.appendChild(new Listcell(pni_req.getNwp()));
						// row.appendChild(new Listcell(pni_req.getQw()));
						// row.appendChild(new Listcell(pni_req.getEtype()));
						// row.appendChild(new Listcell(pni_req.getType_zm()));
						// row.appendChild(new
						// Listcell(pni_req.getBank_inps()));
					} catch (Exception e) {
						e.printStackTrace();
						ISLogger.getLogger().error(CheckNull.getPstr(e));
					}
				}
			});
			filter = new CreditAppFilter();
			refreshModel(_startPageNumber);
			btn_rejectdata.setVisible(false);
			btn_cl_refresh.setVisible(false);
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
	}

	public void onPaging$ni_reqPaging(ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}

	private void refreshModel(int activePage) {
		if (this.param.get("client_id") != null) {
			String[] parameter = (String[]) this.param.get("client_id");
			filter.setId_client(parameter[0]);
		}
		if (this.param.get("id_client") != null) {
			String[] parameter = (String[]) this.param.get("client_id");
			filter.setId_client(parameter[0]);
		}
		if (!customer.equals("")) {
			filter.setId_client(customer);
			filter.setBranch(branch);
			btn_add.setVisible(false);
			btn_search.setVisible(false);
		}
		ni_reqPaging.setPageSize(_pageSize);
		model = new PagingListModel(activePage, _pageSize, filter, alias);
		_totalSize = model.getTotalSize(filter, alias);
		ni_reqPaging.setTotalSize(_totalSize);
		dataGrid.setModel((ListModel) model);
		filter.setBranch(branch);
		if (model.getSize() > 0) {
			this.current = (CreditApp) model.getElementAt(0);
			sendSelEvt();
		}
	}

	public CreditApp getCurrent() {
		return current;
	}

	public void setCurrent(CreditApp current) {
		this.current = current;
	}

	public void onDoubleClick$dataGrid$grd() {
		try {
			btn_rejectdata.setVisible(true);
			btn = "double";
			grd.setVisible(false);
			frm.setVisible(true);
			onSelect$reqtype();
			getCurrentValue();
			idgrd.setVisible(true);
			btn_back.setImage("/images/folder.png");
			btn_back.setLabel(Labels.getLabel("grid"));
			String[] parameter = (String[]) param.get("gtype");
			// System.out.println("GTYPE = "+parameter[0]);
			if (parameter != null && parameter[0].equals("3")) {
				btn_save.setVisible(true);
				btn_confirm.setVisible(true);
				btn_reject.setVisible(true);
				btn_cl_refresh.setVisible(true);
			} else if (parameter != null && parameter[0].equals("2")) {
				btn_save.setVisible(false);
				btn_confirm.setVisible(false);
				btn_reject.setVisible(false);
				btn_cl_refresh.setVisible(false);
			} else {
				btn_save.setVisible(true);
				btn_cl_refresh.setVisible(true);
				btn_confirm.setVisible(true);
				btn_reject.setVisible(true);
			}
			if (current != null) {
				idlabel.setValue("Регистрационный номер - " + current.getId()
						+ " Состояние - " + current.getState_name());
				id = current.getId();
				branch = current.getBranch();
				onChange$id_client();
				if (current.getState().equals("6")
						|| current.getState().equals("2")) {
					readOnly(true);
					btn_reject.setVisible(false);
					btn_save.setVisible(false);
					if (current.getState().equals("6")) {
						btn_rejectdata.setVisible(true);
					} else {
						btn_reject.setVisible(true);
						btn_rejectdata.setVisible(false);
					}
					btn_confirm.setVisible(false);
				} else {
					btn_reject.setVisible(true);
					btn_save.setVisible(true);
					btn_confirm.setVisible(true);
					btn_rejectdata.setVisible(false);
					readOnly(false);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
	}

	public void onClick$btn_print_KatmInfo() {
		// String claim_id2 = "11729";
		if (dataGrid.getSelectedItem() == null) {
			alert("клиент не выбран");
		} else {
			try {
				// String claim_id1 =
				// BproductService.getForm_id(current.getId(), branch1);
				String html_source = CreditAppService.getKatmInfo(
						current.getBranch(), current.getId());
				// System.out.println("html: "+html_source);
				if (html_source.isEmpty()) {
					alert("Катм запрос не существует");
				} else {
					katmwnd.setVisible(true);
					if (Utils.isEmpty(html.getContent())) {
						html.setWidth(katmwnd.getWidth());
						html.setContent(html_source);
						//katmwnd.appendChild(html);
						katmwnd$katmwnd_div.appendChild(html);
					}
					katmwnd.setClosable(true);
				}
			} catch (Exception e) {
				ISLogger.getLogger().error(CheckNull.getPstr(e));
				alert(CheckNull.getPstr(e));
				e.printStackTrace();
			}
		}

	}

	public void onClick$btn_cancel_ktm$katmwnd() {
		katmwnd.setVisible(false);
		// System.out.println("ererfsfsfsfs");
	}

	private void readOnly(boolean bool) {
		Utils.ReadOnly(facgrd, bool);
		Utils.ReadOnly(grdclient, bool);
		Utils.ReadOnly(grdcredapp, bool);
		Utils.ReadOnly(grdniki, bool);
		Utils.ReadOnly(grddop, bool);
	}

	public void onClick$btn_back() {
		try {
			if (frm.isVisible()) {
				frm.setVisible(false);
				btn_rejectdata.setVisible(false);
				grd.setVisible(true);
				btn_back.setImage("/images/file.png");
				btn_back.setLabel(Labels.getLabel("back"));
				btn_cl_refresh.setVisible(false);
				refreshModel(_startPageNumber);
			} else {
				onDoubleClick$dataGrid$grd();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		try {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onClick$btn_cl_refresh() {
		try {
			System.out.println("1");
			Res res = new Res();
			System.out.println("2");
			CreditAppService.refreshClientData(current.getId_client(), alias,
					res);
			System.out.println("11");
			if (res.getCode() != 1) {
				System.out.println("12");
				onClick$btn_back();
				System.out.println("13");
				alert("Данные обновлены");
				System.out.println("14");
			} else {
				System.out.println("15");
				alert(res.getName());
			}
			System.out.println("16");
		} catch (Exception e) {
			System.out.println("Exception wow");
			e.printStackTrace();
		}
	}

	public void onSelect$reqtype() {
		if (reqtype.getValue().equals("3")) {
			facgrd.setVisible(true);
		} else {
			Utils.clearForm(facgrd);
			facgrd.setVisible(false);
		}
	}

	public void onChange$reqtype() {
		onSelect$reqtype();
	}

	public void onChange$id_client() {
		visibleType(false);
		List<RefData> cl_name = null;
		System.out.println("!!" + id_client.getValue());
		if (!id_client.getValue().equals("")) {
			List<RefData> list = CreditAppService.getTypeClient(
					id_client.getValue(), alias);
			if (list.isEmpty()) {
				alert("Клиент не найден");
				return;
			} else {
				rezkl.setSelecteditem(list.get(0).getLabel());
				type_zm.setModel(new ListModelList(com.is.bpri.NiReqService
						.getTypeZm(alias,
								" kod_zm=(select client.code_type from client where client.id_client='"
										+ id_client.getValue()
										+ "' and client.branch='" + branch
										+ "') ")));
				if (list.get(0).getData().equals("08")
						|| list.get(0).getData().equals("11")) {
					is_letter.setSelecteditem("0");
					sign_client.setSelecteditem("0");
					if (list.get(0).getData().equals("08")) {
						client_type.setSelecteditem("Ф");
					} else {
						client_type.setSelecteditem("И");
					}
					setReadOnly(true);
					if (qw.getValue() == null || qw.getValue().equals("")) {
						qw.setValue("0");
					}
					nwp.setSelecteditem("2");
					etype.setSelecteditem("0");
					cl_name = CreditAppService.getPClient(id_client.getValue(),
							branch, Utils.getAlias(branch));
					inn_passport.setValue(cl_name.get(0).getData());
					List<RefData> work = CreditAppService.getWorkFromClientId(
							branch, id_client.getValue());
					if (!work.isEmpty()) {
						inn_org.setValue(work.get(0).getData());
						name_org.setValue(work.get(0).getLabel());
					}
					visibleType(true);
				} else {
					setReadOnly(false);
					client_type.setSelecteditem("Ю");
					cl_name = CreditAppService.getJClient(id_client.getValue(),
							alias);
					inn_passport.setValue(cl_name.get(0).getData());
					Utils.clearForm(inn_org);
					Utils.clearForm(typerow);
					visibleType(false);
				}
				System.out.println("!" + cl_name.get(0).getLabel() + "!");
				client_name.setValue(cl_name.get(0).getLabel());
			}
		}
	}

	private void setReadOnly(boolean bool) {
		qw.setReadonly(bool);
		nwp.setReadonly(bool);
		etype.setReadonly(bool);
	}

	public void onInitRenderLater$type_zm() {
		if (btn.equals("double")) {
			type_zm.setSelecteditem(current.getType_zm());
		}
	}

	private void visibleType(boolean bool) {
		typerow.setVisible(bool);
		typelabel.setVisible(bool);
		inn_org.setVisible(bool);
	}

	public void onClick$btn_add() {
		try {
			Utils.clearForm(frm);
			current = null;
			onDoubleClick$dataGrid$grd();
			btn = "add";
			branchid.setValue(branch);
			idgrd.setVisible(false);
			v_date.setValue(new Date());
			btn_reject.setVisible(false);
			btn_confirm.setVisible(false);
			setReadOnly(false);
			readOnly(false);
			client_name.setReadonly(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onClick$btn_rejectdata() {
		setSessionObject();
		frm.setVisible(false);
		cancel = "2";
		include.setSrc("/creditapp/reject.zul?branch=" + branch + "&alias="
				+ alias + "&id=" + current.getId() + "&btn=" + cancel);
		include.setVisible(true);
	}

	public void onClick$btn_refresh() {
		Res res = new Res();
		CreditAppService.refreshCredit(branch, alias, res);
		if (res.getCode() != 1) {
			refreshModel(_startPageNumber);
		} else {
			alert(res.getName());
		}
	}

	private void setSessionObject() {
		session.setAttribute("frm", frm);
		session.setAttribute("include", include);
		session.setAttribute("current", current);
		session.setAttribute("grd", grd);
		session.setAttribute("btn_back", btn_back);
		session.setAttribute("btn_rejectdata", btn_rejectdata);
		session.setAttribute("btn_cl_refresh", btn_cl_refresh);
		session.setAttribute("ni_reqPaging", ni_reqPaging);
		session.setAttribute("model", model);
		session.setAttribute("_pageSize", _pageSize);
		session.setAttribute("filter", filter);
		session.setAttribute("_totalSize", _totalSize);
		session.setAttribute("dataGrid", dataGrid);
		session.setAttribute("_startPageNumber", _startPageNumber);
	}

	public void onClick$btn_reject() {
		setSessionObject();
		frm.setVisible(false);
		cancel = "1";
		include.setSrc("/creditapp/reject.zul?branch=" + branch + "&alias="
				+ alias + "&id=" + current.getId() + "&btn=" + cancel);
		include.setVisible(true);
	}

	public void onClick$btn_confirm() {
		try {
			Messagebox.show("Вы уверены что хотите утвердить заявку?",
					"Предупреждение", Messagebox.YES | Messagebox.NO,
					Messagebox.EXCLAMATION, new EventListener() {

						@Override
						public void onEvent(Event event) throws Exception {
							try {
								if (event.getName().equals("onYes")) {
									if (allFields()) {
										Res res = new Res();
										// alert("SHIFR_ID = "+shifr_id.getValue()+"!!!");
										CreditAppService.doActionForCreditApp(
												current, 2, alias, res);
										// current.setState("2");
										// CreditAppService.update(current,
										// alias, res);
										if (res.getCode() != 1) {
											alert("Заявка успешно утверждена");
											onClick$btn_back();
										} else {
											alert(res.getName());
										}
									} else {
										alert("Заполнены не все поля");
									}
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private boolean allFields() {
		boolean bool = false;
		bool = Utils.filledFields(grdclient);
		if (bool) {
			bool = Utils.filledFields(grdcredapp);
			if (bool) {
				bool = Utils.filledFields(grddop);
				if (bool) {
					bool = Utils.filledFields(grdniki);
					if (reqtype.getValue().equals("3") && bool) {
						bool = Utils.filledFields(facgrd);
					}
				}
			}
		}
		Utils.setBool(true);
		return bool;
	}

	public void onClick$btn_search() {
		try {
			onDoubleClick$dataGrid$grd();
			getFilterValue();
			btn = "search";
			btn_reject.setVisible(false);
			btn_confirm.setVisible(false);
			setReadOnly(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onClick$btn_save() {
		try {
			Res res = new Res();
			if (btn.equals("search")) {
				filter = new CreditAppFilter();
				filter.setBranch(branchid.getValue());
				filter.setId_client(id_client.getValue());
				filter.setReqnum(reqnum.getValue());
				filter.setReqtype(reqtype.getValue());
				filter.setBranch_id(branch_id.getValue());
				filter.setKred_id(kred_id.getValue());
				filter.setShifr_id(shifr_id.getValue());
				filter.setCurrency(currency.getValue());
				if (amount.getValue() != null) {
					filter.setAmount(Long.parseLong(amount.getValue() + ""));
				}
				filter.setEnd_date(end_date.getValue());
				filter.setDbinn(dbinn.getValue());
				filter.setDbbranch(dbbranch.getValue());
				filter.setDbaddress(dbaddress.getValue());
				filter.setV_date(v_date.getValue());
				filter.setResolve_org(resolve_org.getValue());
				filter.setIs_letter(is_letter.getValue());
				filter.setSign_client(sign_client.getValue());
				filter.setRezkl(rezkl.getValue());
				filter.setClient_type(client_type.getValue());
				filter.setInn_passport(inn_passport.getValue());
				filter.setFac_resident(fac_resident.getValue());
				filter.setFac_j_code_organ_direct(fac_j_code_organ_direct
						.getValue());
				filter.setFac_class_id(fac_class_id.getValue());
				filter.setInn_org(inn_org.getValue());
				filter.setName_org(name_org.getValue());
				filter.setNwp(nwp.getValue());
				filter.setQw(qw.getValue());
				filter.setEtype(etype.getValue());
				filter.setType_zm(type_zm.getValue());
				filter.setBank_inps(bank_inps.getValue());
			} else {
				current = new CreditApp();
				current.setId(id);
				current.setBranch(branchid.getValue());
				current.setAmount((amount.getValue() * 100) + "");
				current.setBank_inps(bank_inps.getValue());
				current.setBranch_id(branch_id.getValue());
				current.setClient_type(client_type.getValue());
				current.setCode(code.getValue());
				current.setCurrency(currency.getValue());
				current.setDbaddress(dbaddress.getValue());
				current.setDbbranch(dbbranch.getValue());
				current.setDbinn(dbinn.getValue());
				current.setEnd_date(end_date.getValue());
				current.setEtype(etype.getValue());
				current.setFac_class_id(fac_class_id.getValue());
				current.setFac_j_code_organ_direct(fac_j_code_organ_direct
						.getValue());
				current.setFac_resident(fac_resident.getValue());
				current.setId_client(id_client.getValue());
				current.setInn_org(inn_org.getValue());
				current.setInn_passport(inn_passport.getValue());
				current.setIs_letter(is_letter.getValue());
				current.setKred_id(kred_id.getValue());
				current.setName_org(name_org.getValue());
				current.setNwp(nwp.getValue());
				current.setQw(qw.getValue());
				current.setReqnum(reqnum.getValue());
				current.setReqtype(reqtype.getValue());
				current.setResolve_org(resolve_org.getValue());
				current.setRezkl(rezkl.getValue());
				current.setShifr_id(shifr_id.getValue());
				current.setSign_client(sign_client.getValue());
				current.setType_zm(type_zm.getValue());
				current.setV_date(v_date.getValue());
				current.setState("1");
				// alert("SHIFR_ID = "+shifr_id.getValue()+"!!!");
				if (btn.equals("add")) {
					CreditAppService.doActionForCreditApp(current, 1, alias,
							res);
					// CreditAppService.create(current, alias, res);
				} else if (btn.equals("double")) {
					CreditAppService.doActionForCreditApp(current, 4, alias,
							res);
					// CreditAppService.update(current, alias, res);
				}
			}
			if (res.getCode() != 1) {
				onClick$btn_back();
				refreshModel(_startPageNumber);
				SelectEvent evt = new SelectEvent("onSelect", dataGrid,
						dataGrid.getSelectedItems());
				Events.sendEvent(evt);
			} else {
				alert(res.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
			alert(CheckNull.getPstr(e));
		}

	}

	public void onClick$btn_cancel() {
		onClick$btn_back();
		CheckNull.clearForm(addgrd);
	}

	private void getFilterValue() {
		try {
			if (filter != null) {
				branchid.setValue(filter.getBranch());
				id_client.setValue(filter.getId_client());
				type_zm.setSelecteditem(filter.getType_zm());
				inn_org.setValue(filter.getInn_org());
				bank_inps.setSelecteditem(filter.getBank_inps());
				name_org.setValue(filter.getName_org());
				reqnum.setValue(filter.getReqnum());
				reqtype.setSelecteditem(filter.getReqtype() + "");
				end_date.setValue(filter.getEnd_date());
				v_date.setValue(filter.getV_date());
				branch_id.setSelecteditem(filter.getBranch_id());
				shifr_id.setSelecteditem(filter.getShifr_id());
				kred_id.setSelecteditem(filter.getKred_id());
				resolve_org.setSelecteditem(filter.getResolve_org());
				currency.setSelecteditem(filter.getCurrency());
				amount.setValue(filter.getAmount());
				code.setSelecteditem(filter.getCode());
				nwp.setSelecteditem(filter.getNwp());
				qw.setValue(filter.getQw());
				etype.setSelecteditem(filter.getEtype());
				is_letter.setSelecteditem(filter.getIs_letter());
				rezkl.setSelecteditem(filter.getRezkl());
				client_type.setSelecteditem(filter.getClient_type());
				inn_passport.setValue(filter.getInn_passport());
				sign_client.setSelecteditem(filter.getSign_client());
				dbinn.setValue(filter.getDbinn());
				dbbranch.setSelecteditem(filter.getDbbranch());
				fac_resident.setSelecteditem(filter.getFac_resident());
				fac_j_code_organ_direct.setSelecteditem(filter
						.getFac_j_code_organ_direct());
				fac_class_id.setSelecteditem(filter.getFac_class_id());
				dbaddress.setValue(filter.getDbaddress());
			}
		} catch (Exception e) {
			e.printStackTrace();
			alert(CheckNull.getPstr(e));
		}
	}

	private void getCurrentValue() {
		try {
			if (current != null) {
				branchid.setValue(current.getBranch());
				id_client.setValue(current.getId_client());
				type_zm.setSelecteditem(current.getType_zm());
				inn_org.setValue(current.getInn_org());
				bank_inps.setSelecteditem(current.getBank_inps());
				name_org.setValue(current.getName_org());
				reqnum.setValue(current.getReqnum());
				reqtype.setSelecteditem(current.getReqtype() + "");
				end_date.setValue(current.getEnd_date());
				v_date.setValue(current.getV_date());
				branch_id.setSelecteditem(current.getBranch_id());
				shifr_id.setSelecteditem(current.getShifr_id());
				kred_id.setSelecteditem(current.getKred_id());
				resolve_org.setSelecteditem(current.getResolve_org());
				currency.setSelecteditem(current.getCurrency());
				String tempAmount = current.getAmount().replace(" ", "")
						.replace(",", ".");
				amount.setValue(Double.parseDouble(tempAmount));
				code.setSelecteditem(current.getCode());
				nwp.setSelecteditem(current.getNwp());
				qw.setValue(current.getQw());
				etype.setSelecteditem(current.getEtype());
				is_letter.setSelecteditem(current.getIs_letter());
				rezkl.setSelecteditem(current.getRezkl());
				client_type.setSelecteditem(current.getClient_type());
				inn_passport.setValue(current.getInn_passport());
				sign_client.setSelecteditem(current.getSign_client());
				dbinn.setValue(current.getDbinn());
				dbbranch.setSelecteditem(current.getDbbranch());
				fac_resident.setSelecteditem(current.getFac_resident());
				fac_j_code_organ_direct.setSelecteditem(current
						.getFac_j_code_organ_direct());
				fac_class_id.setSelecteditem(current.getFac_class_id());
				dbaddress.setValue(current.getDbaddress());
			}
		} catch (Exception e) {
			e.printStackTrace();
			alert(CheckNull.getPstr(e));
		}
	}

	private void loadRefCBoxModels() {
		bank_inps.setModel(new ListModelList(CreditAppService.getS_mfo(alias)));
		reqtype.setModel(new ListModelList(com.is.bpri.NiReqService
				.getReqType(alias)));
		branch_id.setModel(new ListModelList(com.is.bpri.NiReqService
				.getBranchId(alias)));
		shifr_id.setModel(new ListModelList(com.is.bpri.NiReqService
				.getShifrId(alias)));
		kred_id.setModel(new ListModelList(com.is.bpri.NiReqService
				.getKredId(alias)));
		resolve_org.setModel(new ListModelList(com.is.bpri.NiReqService
				.getResolveOrg(alias)));
		currency.setModel(new ListModelList(com.is.bpri.NiReqService
				.getCurrency(alias)));
		code.setModel(new ListModelList(com.is.bpri.NiReqService.getCode(alias)));
		nwp.setModel(new ListModelList(com.is.bpri.NiReqService.getNwp(alias)));
		etype.setModel(new ListModelList(com.is.bpri.NiReqService
				.getEtype(alias)));
		is_letter.setModel(new ListModelList(CreditAppService
				.getIs_letter(alias)));
		client_type.setModel(new ListModelList(CreditAppService
				.getS_rezkl(alias)));
		rezkl.setModel(new ListModelList(CreditAppService.getClient_type(alias)));
		sign_client.setModel(new ListModelList(CreditAppService
				.getSign_client(alias)));
		dbbranch.setModel(new ListModelList(com.is.bpri.NiReqService
				.getBranchId(alias)));
		fac_resident.setModel(new ListModelList(CreditAppService
				.getS_rezkl(alias)));
		fac_j_code_organ_direct.setModel(new ListModelList(CreditAppService
				.getS_soogun(alias)));
		fac_class_id.setModel(new ListModelList(CreditAppService
				.getS_klass(alias)));
	}
}
