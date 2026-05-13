package com.is.clientcrm.reference;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
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

import com.is.clientcrm.DictionaryKeeper;
import com.is.clientcrm.S_spr_oked;
import com.is.utils.RefCBox;
import com.is.utils.RefData;

@SuppressWarnings("serial")
public class ReferenceViewCtrl extends GenericForwardComposer {
	private static Logger logger = Logger.getLogger(ReferenceViewCtrl.class);
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
	private Textbox id1,
					name1,
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
	private String myChoice;

	private Paging paging;
  
	private int _pageSize = 15;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	//private String alias, branch1;
	private Connection conn;

	PagingListModel model = null;
    private ReferenceService service;

	
	public RefData current = new RefData("","");
	private RefData filter = new RefData("","");
	

	public RefData getCurrent() {
		return current;
	}

	public void setCurrent(RefData current) {
		this.current = current;
	}

	public RefData getFilter() {
		return filter;
	}

	public void setFilter(RefData filter) {
		this.filter = filter;
	}

	public String getMyChoice() {
		return myChoice;
	}

	public void setMyChoice(String myChoice) {
		this.myChoice = myChoice;
	}

	public ReferenceViewCtrl() {
		// TODO Auto-generated constructor stub
		super('$', false, false);
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		binder = new AnnotateDataBinder(comp);
		binder.bindBean("current", this.current);
		binder.bindBean("filter", this.filter);
		binder.loadAll();
		String[] parameter = (String[]) param.get("ht");
        service = new ReferenceService();

		if (parameter != null) {
			dataGrid.setRows(Integer.parseInt(parameter[0]) / 36);
		}
		
		//hamza 1
		HashMap<String, Object> incomingArguments = (HashMap)Executions.getCurrent().getArg();
		myChoice=(String)incomingArguments.get("myChoice");
		// end
		
		alias = (String) session.getAttribute("alias");
		branch1 = (String) session.getAttribute("branch");
		un = session.getAttribute("un").toString();
		pw = session.getAttribute("pwd").toString();
		
		dataGrid.setItemRenderer(new ListitemRenderer() {
            @Override
			public void render(Listitem row, Object data) throws Exception {
				RefData refdata = (RefData) data;
				row.setValue(refdata);
				row.appendChild(new Listcell(refdata.getData()));
				row.appendChild(new Listcell(refdata.getLabel()));
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
		
        //paging.setPageSize(_pageSize);
        //paging.setTotalSize(service.getSoogunCountFl(0, 0, filter));
		List<RefData> list = null;
		if (filter==null || (filter.getData()==null && filter.getLabel()==null) || (filter.getData().equals("") && filter.getLabel().equals("")) ) {
		
			if (myChoice.equals("j_opf")) {
				list=DictionaryKeeper.getOpf();
			}
			else if (myChoice.equals("code_form")) {
				list=DictionaryKeeper.getVsbs();
			}
			else if (myChoice.equals("j_code_tax_org")) {
				list=DictionaryKeeper.getGni();
			}
			else if (myChoice.equals("type_non_resident")) {
				list=DictionaryKeeper.getNonResidentTypes();
			}
			else if (myChoice.equals("swift_id")) {
				list=DictionaryKeeper.getSwift();
			}
			else if (myChoice.equals("file_name")) {
				list=DictionaryKeeper.getSubsidiary_by_branch(branch1);
			}
			else if (myChoice.equals("subbranch")) {
				list=DictionaryKeeper.getSubbranch_by_branch(branch1);
			}
			else if (myChoice.equals("j_type_activity")) {
				list=DictionaryKeeper.getActivityType();
			}
		} else {
			if (myChoice.equals("j_opf")) {
		           list = service.getDataFl("select opf_id data, name_ru label from S_SPR_OPF1 where act <> 'Z' ", "opf_id", "name_ru", filter);
			}
			else if (myChoice.equals("code_form")) {
		           list = service.getDataFl("select kod data, name label from s_vsbs where act <> 'Z' ", "kod", "name", filter);
			}
			else if (myChoice.equals("j_code_tax_org")) {
		           list = service.getDataFl("select gni_id data,name_gni label from s_gni ", "gni_id", "name_gni", filter);
			}
			else if (myChoice.equals("type_non_resident")) {
		           list = service.getDataFl("select id data, name label from SS_non_resident ", "id", "name", filter);
			}
			else if (myChoice.equals("swift_id")) {
		           list = service.getDataFl("select swift_id data, bank_name label from s_spr_47 ", "swift_id", "bank_name", filter);
			}
			else if (myChoice.equals("file_name")) {
		           list = service.getDataFl("select code data, name label from SS_SUBSIDIARY where branch='"+ branch1 +"' and STATE='A' ", "code", "name", filter);
			}
			else if (myChoice.equals("subbranch")) {
		           list = service.getDataFl("select bank_id data, bank_name label from s_mfo where act <> 'Z' and bank_type=(select s2.bank_type from s_mfo s2 where s2.bank_id=(select branch from sets where rownum=1)) and bank_statu in ('10') and tcc_id='"+branch1+"' ", "bank_id", "bank_name", filter);
			}
			else if (myChoice.equals("j_type_activity")) {
			       list = service.getDataFl("select id data, name label from ss_client_type_activity ", "id", "name", filter);
			}
			
		}
			
        dataGrid.setModel(new BindingListModelList(list,true));		
	}
	
	public void showData() {
		filter.setData("");
		filter.setLabel("");
		id1.setValue("");
		name1.setValue("");
		refreshModel(0);
	}

    public void onPaging$paging(ForwardEvent event) throws InterruptedException {
        PagingEvent pagingEvent = (PagingEvent) event.getOrigin();
        int startPageNumber = pagingEvent.getActivePage();
        refreshModel(startPageNumber);
    }

    public void onClick$btnSelect() throws Exception {
        if (current == null)
            throw new Exception("Current is null");
        RefData rdata = (RefData) current;
        //S_spr_oked dan foydalansak buladi
        S_spr_oked payload = new S_spr_oked();
        payload.setCode(rdata.getData());
        payload.setName_uz(myChoice);
        Events.sendEvent("onNotifyReference",self,payload);
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
