package com.is.soogun;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Div;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;

import com.is.utils.RefCBox;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
public class SoogunViewCtrl extends GenericForwardComposer {
	private static Logger logger = Logger.getLogger(SoogunViewCtrl.class);
	private Window nibbd_wnd, nibbd_modal, spec_acc_wnd;
	private Div filter_div,new_acc,spec_acc_parent;
	private Tabbox acc_tabs;
	private Div grd;
	private Listbox dataGrid,history;
	private Toolbar action_bar;
	private Toolbarbutton btn_last;
	private Toolbarbutton btn_next;
	private Toolbarbutton btn_prev;
	private Toolbarbutton btn_first;
	private Toolbarbutton btn_back;
	private Textbox id_order,
					name,
					acc_group_idText;
	private RefCBox state,
					acc_bal,
					currency,
					sgn,
					bal,
					acc_group_id;
	private RefCBox fstate,
					facc_bal,
					fcurrency,
					fsgn,
					fbal,
					facc_group_id;
	private Textbox aclient,
	aacc_group_idText,
	aid_order,
	aname;
	private RefCBox astate,
					aacc_bal,
					acurrency,
					asgn,
					abal,
					aacc_group_id;
	private Textbox currencyValue,fcurrencyValue,acurrencyValue;
	private Textbox facc_bal_text;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	SimpleDateFormat dfWithTime = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	private String alias, branch1;
	private String un;
	private String pw;
	private String clientId;
	private String clientName;

	private Paging paging;
  
	private int _pageSize = 15;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	//private String alias, branch1;
	private Connection conn;

	PagingListModel model = null;
    private SoogunService service;

	
	@Getter @Setter public Soogun current = new Soogun();
	@Getter @Setter private Soogun filter = new Soogun();
	

	public SoogunViewCtrl() {
		// TODO Auto-generated constructor stub
		super('$', false, false);
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		binder = new AnnotateDataBinder(comp);
		binder.bindBean("current", this.current);
		//binder.bindBean("currentListItem", this.currentListItem);
		binder.bindBean("filter", this.filter);
		binder.loadAll();
		String[] parameter = (String[]) param.get("ht");
        service = new SoogunService();

		if (parameter != null) {
			dataGrid.setRows(Integer.parseInt(parameter[0]) / 36);
		}

		alias = (String) session.getAttribute("alias");
		branch1 = (String) session.getAttribute("branch");
		un = session.getAttribute("un").toString();
		pw = session.getAttribute("pwd").toString();
		
		dataGrid.setItemRenderer(new ListitemRenderer() {
            @Override
			public void render(Listitem row, Object data) throws Exception {
				Soogun soogun = (Soogun) data;
				row.setValue(soogun);
				row.appendChild(new Listcell(soogun.getData()));
				row.appendChild(new Listcell(soogun.getLabel()));
				//row.appendChild(new Listcell(!CheckNull.isEmpty(pAccount.getL_date())?df.format(pAccount.getL_date()):null));
				//row.appendChild(new Listcell(statesMap.get(Integer.toString(pAccount.getState()))));
				//if(selectedSoogun != null && selectedSoogun.equals(pAccount.getData())) {
				//	dataGrid.setSelectedItem(row);
				//	//setCurrentListItem(pAccount);
				//	//initActionBar();
				//}
				/*row.addEventListener(Events.ON_CLICK, new EventListener() {
                    @Override
                    public void onEvent(Event event) throws Exception {
                        onClick$btnSelect();
                    }
                });*/

			}
		});
        self.addEventListener(Events.ON_CLOSE, new EventListener() {
            @Override
            public void onEvent(Event event) throws Exception {
            	self.setVisible(false);
                event.stopPropagation();
            }
        });

		refreshModel(0);
		//dataGrid.setSelectedIndex(0);
	}
	
	private void refreshModel(int activePage) {
		////if(clientId != null){
		////	filter.setClient(clientId);
		////}
		////accountDao.setFilter(filter);
		////dataGrid.setModel(new BindingListModelList(accountDao.getList(), true));
		//model = new PagingListModel(activePage, _pageSize, filter, "");
		//dataGrid.setModel((ListModel) model);
		
        paging.setPageSize(_pageSize);
        paging.setTotalSize(service.getSoogunCountFl(0, 0, filter));
        List<Soogun> list = service.getSoogunFl(activePage, _pageSize, filter);
        dataGrid.setModel(new BindingListModelList(list,true));
		
	}

    public void onPaging$paging(ForwardEvent event) throws InterruptedException {
        PagingEvent pagingEvent = (PagingEvent) event.getOrigin();
        int startPageNumber = pagingEvent.getActivePage();
        refreshModel(startPageNumber);
    }

    public void onClick$btnSelect() throws Exception {
        if (current == null)
            throw new Exception("Current is null");
        Soogun soogun = (Soogun) current;
        Events.sendEvent("onNotifySoogun",self,soogun);
		binder.loadAll();
    }

    
    public void onDoubleClick$dataGrid$grd()  throws Exception{
    	onClick$btnSelect();
    }
    
    public void onClick$btnRefresh() throws InterruptedException {
        refreshModel(0);
    }
    
   /* private void refreshModel(int startPageNumber) {
        Criteria criteria = new Criteria.Builder().
                pageIndex(startPageNumber).
                pageSize(_pageSize).
                filter(this.filter).
                build();
        paging.setPageSize(_pageSize);
        paging.setTotalSize(service.getCount(criteria));
        List<Soato> list = service.getData(criteria);
        resultListbox.setModel(new BindingListModelList(list,true));
    }
*/

    public void onClick$btnClear(){
        filter.setData("");
        filter.setLabel("");
        binder.loadAll();
    }
}
