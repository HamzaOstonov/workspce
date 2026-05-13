package com.is.bpri.bprLimits;

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
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.event.PagingEvent;

import com.is.ISLogger;
import com.is.utils.CheckNull;
import com.is.utils.Res;

@SuppressWarnings("serial")
public class bprLimitsViewCtrl extends GenericForwardComposer {
	
	private Div frm;
	private Listbox dataGrid;
	private Div grd;
	private Grid frmgrd;
	private Toolbarbutton btn_last;
	private Toolbarbutton btn_next;
	private Toolbarbutton btn_prev;
	private Toolbarbutton btn_first;
	private Toolbarbutton btn_back,btn_save,btn_del,btn_add;
	private Toolbarbutton btn_refresh;
	private Textbox summ_limit;
	private Datebox date_limit;
	private Paging bprlimitsPaging;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	public bprLimitsFilter filter = new bprLimitsFilter();
	private String  alias;
	private int template;
	private PagingListModel model = null;
	private AnnotateDataBinder binder;
	private int bpr_type;
	private String gtype = "";
	private String btn = "";
	private String client = "";
	private bprLimits current = new bprLimits();

	public bprLimitsViewCtrl() {
		super('$', false, false);
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		try {
			binder = new AnnotateDataBinder(comp);
			binder.bindBean("current", this.current);
			binder.loadAll();
			date_limit.setFormat("dd.MM.yyyy");
			if (this.param.get("template") != null) {
				template = Integer.parseInt(((String[]) this.param.get("template"))[0]);
			}
			if (this.param.get("bpr_type") != null) {
				bpr_type = Integer.parseInt(((String[]) this.param.get("bpr_type"))[0]);
			}
			if (this.param.get("gtype") != null) {
				gtype = ((String[]) this.param.get("gtype"))[0];
			}
			if (this.param.get("client") != null) {
				client = ((String[]) this.param.get("client"))[0];
			}
			filter.setBproduct_id(template);
			alias = (String) session.getAttribute("alias");
			dataGrid.setItemRenderer(new ListitemRenderer() {
				
				public void render(Listitem row, Object data) throws Exception {
					try {
						bprLimits pbprLimits = (bprLimits) data;
						row.setValue(pbprLimits);
						row.appendChild(new Listcell(pbprLimits.getDate_limit()));
						row.appendChild(new Listcell(pbprLimits.getSumm_limit()));
						row.appendChild(new Listcell(pbprLimits.getId_state()));
						if(pbprLimits.getId_state().equals("1")&&(pbprLimits.getSumm_limit()==null||pbprLimits.getSumm_limit().equals("0")||pbprLimits.getSumm_limit().equals(",00"))) {
							btn_refresh.setDisabled(true);
							btn_add.setDisabled(true);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			refreshModel(_startPageNumber);
			if((bpr_type==1||bpr_type==4||bpr_type==5)&&(gtype!=null&&gtype.equals("3"))){
				btn_refresh.setVisible(true);
			} else {
				btn_refresh.setVisible(false);
			}
			if(gtype!=null&&gtype.equals("3")){
				btn_add.setVisible(true);
			} else {
				btn_add.setVisible(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			alert(e.getMessage());
		}
	}

	public void onPaging$bprlimitsPaging(ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}

	private void refreshModel(int activePage) {
		bprlimitsPaging.setPageSize(_pageSize);
		model = new PagingListModel(activePage, _pageSize, filter, alias);
		_totalSize = model.getTotalSize(filter,alias);
		bprlimitsPaging.setTotalSize(_totalSize);
		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0) {
			this.current = (bprLimits) model.getElementAt(0);
			sendSelEvt();
		}
	}
	
	// Omitted...
	public bprLimits getCurrent() {
		return current;
	}

	public void setCurrent(bprLimits current) {
		this.current = current;
	}

	public void onDoubleClick$dataGrid$grd() {
		try {
			grd.setVisible(false);
			frm.setVisible(true);
			frmgrd.setVisible(true);
			btn_back.setImage("/images/folder.png");
			btn_back.setLabel(Labels.getLabel("grid"));
			if(gtype!=null&&gtype.equals("3")){
				btn_save.setVisible(true);
				btn_del.setVisible(true);
			} else {
				btn_save.setVisible(false);
				btn_del.setVisible(false);
			}
			btn = "double";
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onClick$btn_del(){
		try {
			Messagebox.show("Вы уверены что хотите удалить эту запись?","Удаление",Messagebox.OK|Messagebox.CANCEL,Messagebox.QUESTION,new EventListener() {
				
				@Override
				public void onEvent(Event event) throws Exception {
					if(event.getName().equals("onOK")){
						if(current.getId_state().equals("0")){
							Res res = new Res();
							int uid = (Integer) session.getAttribute("uid");
							String str = (String) session.getAttribute("un");
							bprLimitsService.remove(current,uid,str,client,alias,res);
							if(res.getCode()==1){
								alert(res.getName());
								return;
							}
							onClick$btn_back();
							refreshModel(_startPageNumber);
						} else {
							alert("Редактирование запрещено, лимит отправлен в ЕОПЦ");
						}
					}
				}
			});
		} catch (InterruptedException e) {
			e.printStackTrace();
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
	}
	
	public void onClick$btn_add(){
		onDoubleClick$dataGrid$grd();
		btn = "add";
	}

	public void onClick$btn_save() {
		Res res = new Res();
		try {
			int uid = (Integer) session.getAttribute("uid");
			String str = (String) session.getAttribute("un");
			if(btn.equals("double")){
				if(current.getId_state().equals("0")){
					current.setDate_limit(date_limit.getText());
					current.setSumm_limit(!summ_limit.getValue().equals("") ? summ_limit.getValue(): "0");
					bprLimitsService.update(current,uid,str,client,alias,res);
				} else {
					alert("Редактирование запрещено, лимит отправлен в ЕОПЦ");
				}
			} else if(btn.equals("add")){
				if(current == null){
					current = new bprLimits();
				}
				current.setBproduct_id(template);
				current.setDate_limit(date_limit.getText());
				current.setSumm_limit(!summ_limit.getValue().equals("") ? summ_limit.getValue(): "0");
				bprLimitsService.create(current, uid, str,client, alias, res);
			}
			if(res.getCode()!=1){
				alert("Данные успешно сохранены!");
				onClick$btn_back();
				refreshModel(_startPageNumber);
				SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
				Events.sendEvent(evt);
			} else {
				alert(res.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			alert(e.getMessage());
		}
	}
	
	public void onClick$btn_refresh(){
		Res res = new Res();
		int uid = (Integer) session.getAttribute("uid");
		String str = (String) session.getAttribute("un");
		try {
			bprLimitsService.refresh(bpr_type,template,uid,str,client,alias, res);
			if(res.getCode()!=1){
				alert("Лимиты пересчитаны");
				refreshModel(_startPageNumber);
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
		frmgrd.setVisible(true);
		refreshModel(_startPageNumber);
	}

}
