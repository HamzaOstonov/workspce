package com.is.tf.confirm;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.Severity;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Constraint;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Row;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;


import com.is.ISLogger;
import com.is.tf.contract.ContractService;
import com.is.user.User;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.is.utils.Res;
import com.sbs.service.BankServiceProxy;
import com.sbs.service.ContractResult;
import com.sbs.service.Result;
import com.sbs.service.SaveConfirm;

public class ConfirmwndViewCtrl extends GenericForwardComposer{
    private Window confirmmain;
	private Div confirmmaindiv;
    private Grid frmgrd;
    private Toolbarbutton btn_save;
    private Toolbarbutton btn_cancel;
    private Toolbar tb;
    private Textbox bankinn,contractidn,docnum,chdocnum,reason,responsiblename;
    private Textbox abankinn,acontractidn,adoctype,adocnum,achdocnum,aconfirm,areason,aresponsiblename;
    private Textbox fbankinn,fcontractidn,fdoctype,fdocnum,fchdocnum,fconfirm,freason,fresponsiblename ;
    private RefCBox doctype,confirm;
    private Row row_reason;
    private List<RefData> yeisvodocs = new ArrayList<RefData>();
    private List<RefData> actions = new ArrayList<RefData>();
    public Confirm current = new Confirm();
    private User curuser;
    private String alias;
    private Object object;
    PagingListModel model = null;
    ListModelList lmodel =null;
    private AnnotateDataBinder binder;
    SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");

    public ConfirmwndViewCtrl() {
            super('$', false, false);
    }

    @Override
	public void doAfterCompose(Component comp) throws Exception {
    	super.doAfterCompose(comp);
    	binder = new AnnotateDataBinder(comp);
    	binder.bindBean("current", this.current);
    	binder.loadAll();
	    String[] parameter = (String[]) param.get("ht");
	    if (parameter!=null){
	    	
	    }
	    curuser = (User)session.getAttribute("current_user"); 
	    alias = (String)session.getAttribute("alias");
	    actions.add(new RefData("0","Отклонение изменений"));
	    actions.add(new RefData("1","Подтверждение изменений"));
	    yeisvodocs = ContractService.getYeisvoDocs(alias);
	    
	    confirm.setModel(new ListModelList(actions));
	    doctype.setModel(new ListModelList(yeisvodocs));
	    
	}

    public void init(String _inn, String _idn, String _action, String _doctype, String _docnum, String _chdocnum) {
    	current = new Confirm();
    	current.setBankinn(_inn);
    	current.setContractidn(_idn);
    	current.setConfirm(_action);
    	//System.out.println("_doctype"+_doctype);
    	current.setDoctype(_doctype);
    	current.setDocnum(_docnum);
    	current.setChdocnum(_chdocnum);
    	current.setResponsiblename(curuser.getFull_name());
    	binder.loadAll();
    	confirm.setModel(new ListModelList(actions));
	    doctype.setModel(new ListModelList(yeisvodocs));
	    if (_action.equalsIgnoreCase("0")) {
	    	row_reason.setVisible(true);
	    } else {
	    	row_reason.setVisible(false);
	    }
	    confirmmain.setVisible(true);
    }

	public void onClick$btn_save() {
		try{
			Res res = saveConfirm(current);
			if (res.getCode() == 0) {
				current = new Confirm();
			    clearForm(frmgrd);
			    confirmmain.setVisible(false);
			    Events.sendEvent("onConfirm", self, current);
			} else {
				alert(res.getName());
			}
		} catch (Exception e) {
		    e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
		    alert(e.getMessage());
		}
	
	}
	
	public void onClick$btn_cancel() {
	    current = new Confirm();
	    clearForm(frmgrd);
	    confirmmain.setVisible(false);
	}
	
	private Res saveConfirm(Confirm confirm) {
		Res res = new Res();
		final BankServiceProxy ws = new BankServiceProxy((String)session.getAttribute("YESVO_URL"));
		try {
			Result r = ws.saveConfirm(
				confirm.getBankinn(), 
				confirm.getContractidn(), 
				confirm.getDoctype(), 
				confirm.getDocnum(), 
				confirm.getChdocnum(), 
				Short.parseShort(confirm.getConfirm()), 
				confirm.getReason(), 
				confirm.getResponsiblename());
			if (r.getStatus() != 0) {
				res = new Res(1, "ERROR: "+r.getGtkId() + " - "+r.getErrorMsg());
				System.out.println("ERROR: GtkId:"+r.getGtkId() + " - "+r.getErrorMsg());
			} else {
				res = new Res(0, "Ok");
			}
		} catch (Exception e) {
			e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
			res = new Res(1, "ERROR: "+(CheckNull.isEmpty(e.getMessage())?CheckNull.getPstr(e):e.getMessage()));
			System.out.println("ERROR: GtkId:"+(CheckNull.isEmpty(e.getMessage())?CheckNull.getPstr(e):e.getMessage()));
		}
		return res;
	}
	
	public void onInitRenderLater$confirm() {
		confirm.setSelecteditem(current.getConfirm());
	}
	
	public void onInitRenderLater$doctype() {
		doctype.setSelecteditem(current.getDoctype());
	}
	/*
	public void onChange$doctype() {
		if (doctype!=null){
			doctype.setSelecteditem(doctype.getValue());
			alert("DocType= "+doctype);
		}
	}
	*/
	public static void clearForm(Object comp) {
		Constraint ct;
		try{
			if (comp instanceof org.zkoss.zul.Textbox) {
				//System.out.println("Name :" +((org.zkoss.zul.Textbox) comp).getId()+"; org.zkoss.zul.Textbox");
				ct =((org.zkoss.zul.Textbox)comp).getConstraint();
				((org.zkoss.zul.Textbox)comp).setConstraint("");
				((org.zkoss.zul.Textbox)comp).setValue(null);
				((org.zkoss.zul.Textbox)comp).setConstraint(ct);
			} else if (comp instanceof org.zkoss.zul.Intbox) {
				//System.out.println("Name :" +((org.zkoss.zul.Intbox) comp).getId()+"; org.zkoss.zul.Intbox");
				ct =((org.zkoss.zul.Intbox)comp).getConstraint();
				((org.zkoss.zul.Intbox)comp).setConstraint("");
				((org.zkoss.zul.Intbox)comp).setValue(null);
				((org.zkoss.zul.Intbox)comp).setConstraint(ct);
			} else if (comp instanceof org.zkoss.zul.Longbox) {
				//System.out.println("Name :" +((org.zkoss.zul.Longbox) comp).getId()+"; org.zkoss.zul.Longbox");
				ct =((org.zkoss.zul.Longbox)comp).getConstraint();
				((org.zkoss.zul.Longbox)comp).setConstraint("");
				((org.zkoss.zul.Longbox)comp).setValue(null);
				((org.zkoss.zul.Longbox)comp).setConstraint(ct);
			} else if (comp instanceof org.zkoss.zul.Decimalbox) {
				//System.out.println("Name :" +((org.zkoss.zul.Decimalbox) comp).getId()+"; org.zkoss.zul.Decimalbox");
				ct =((org.zkoss.zul.Decimalbox)comp).getConstraint();
				((org.zkoss.zul.Decimalbox)comp).setConstraint("");
				((org.zkoss.zul.Decimalbox)comp).setValue(BigDecimal.ZERO);
				((org.zkoss.zul.Decimalbox)comp).setRawValue(null);
				((org.zkoss.zul.Decimalbox)comp).setConstraint(ct);
			} else if (comp instanceof org.zkoss.zul.Datebox) {
				//System.out.println("Name :" +((org.zkoss.zul.Datebox) comp).getId()+"; org.zkoss.zul.Datebox");
				//((Datebox)ie).setValue(null);
				ct =((Datebox)comp).getConstraint();
				((org.zkoss.zul.Datebox)comp).setConstraint("");
				((org.zkoss.zul.Datebox)comp).setText(null);
				((org.zkoss.zul.Datebox)comp).setConstraint(ct);
			} else if (comp instanceof com.is.utils.RefCBox) {
				//System.out.println("Name :" +((com.is.utils.RefCBox) comp).getId()+"; com.is.utils.RefCBox");
				ct =((com.is.utils.RefCBox)comp).getConstraint();
				((com.is.utils.RefCBox)comp).setConstraint("");
				((com.is.utils.RefCBox)comp).setValue(null);
				((com.is.utils.RefCBox)comp).setConstraint(ct);
			} else if (comp instanceof org.zkoss.zul.Div) {
				for (Object obj : ((org.zkoss.zul.Div)comp).getChildren()) {
					clearForm(obj);
				};
			} else if (comp instanceof org.zkoss.zul.Hbox) {
				for (Object obj : ((org.zkoss.zul.Hbox)comp).getChildren()) {
					clearForm(obj);
				};
			} else if (comp instanceof org.zkoss.zul.Vbox) {
				for (Object obj : ((org.zkoss.zul.Vbox)comp).getChildren()) {
					clearForm(obj);
				};
			} else if (comp instanceof org.zkoss.zul.Grid) {
				for (Object obj : ((org.zkoss.zul.Grid)comp).getRows().getChildren()) {
					clearForm(obj);
				};
			} else if (comp instanceof org.zkoss.zul.Row) {
				for (Object obj : ((org.zkoss.zul.Row)comp).getChildren()) {
					clearForm(obj);
				};
			} else if (comp instanceof org.zkoss.zul.Cell) {
				for (Object obj : ((org.zkoss.zul.Cell)comp).getChildren()) {
					clearForm(obj);
				};
			} else if (comp instanceof org.zkoss.zul.Groupbox) {
				for (Object obj : ((org.zkoss.zul.Groupbox)comp).getChildren()) {
					clearForm(obj);
				};
			}
		}catch(Exception e){
			e.printStackTrace();
		}
    }

}
