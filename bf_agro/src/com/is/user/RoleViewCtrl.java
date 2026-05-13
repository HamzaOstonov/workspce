/**
 * 
 */
package com.is.user;

import java.util.List;

import com.is.ISLogger;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.is.utils.RefDataService;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.DropEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;

/**
 * @author sergey_l
 *
 */
@SuppressWarnings("serial")
public class RoleViewCtrl extends GenericForwardComposer {

	private Listbox right;
	private Listbox left;
//	private Toolbarbutton btn_add;
//	private Toolbarbutton btn_del;
//	private Toolbarbutton btn_save;
	private Textbox tbText,addwnd$tbText;
	private Listbox lrole,actwnd$left,actwnd$right, actwnd$deal_id;
	private RefCBox caccess,addwnd$caccess;
	private Window actwnd,addwnd;
	private String branch,alias;
	private int deal_id;
	
	public Role current= new Role();
	public Module currmodule = new Module();
	/*
	private AnnotateDataBinder binder;

	
    public RoleViewCtrl() {
        super('$', false, false);
}
*/
	/**
	 *
	 *
	 */
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		// TODO Auto-generated method stub
        /*
		binder = new AnnotateDataBinder(comp);
        binder.bindBean("current", this.current);
        binder.loadAll();
        */

		
//    	left.setItemRenderer(new com.is.utils.RefListRenderer());
//    	right.setItemRenderer(new com.is.utils.RefListRenderer());
//    	left.setModel((new ListModelList(RefDataService.getPrType())));
//    	right.setModel((new ListModelList(RefDataService.getPrType())));
		
		branch = (String) session.getAttribute("branch");
		alias = (String) session.getAttribute("alias");
		caccess.setModel((new ListModelList(com.is.utils.RefDataService.getAccess(alias))));
		addwnd$caccess.setModel((new ListModelList(com.is.utils.RefDataService.getAccess(alias))));
        left.setItemRenderer(new ListitemRenderer(){
            @SuppressWarnings("unchecked")
            public void render(Listitem row, Object data) throws Exception {
                Module pModule = (Module) data;
                row.setValue(pModule);
                row.setDraggable("true");
                row.setDroppable("true");
                Listcell lc = new Listcell(pModule.getName());
                lc.setSrc(pModule.getIcon());
                row.appendChild(lc);
                row.addEventListener(Events.ON_DROP, new EventListener() {
					public void onEvent(Event event) throws Exception {
						onDrop$left((DropEvent) event);
					}
                });
            }});
        
        
        right.setItemRenderer(new ListitemRenderer(){
            @SuppressWarnings("unchecked")
            public void render(Listitem row, Object data) throws Exception {
            	Module pModule = (Module) data;
                row.setValue(pModule);
                row.setDraggable("true");
                row.setDroppable("true");
                Listcell lc = new Listcell(pModule.getName());
                lc.setSrc(pModule.getIcon());
                row.appendChild(lc);
                row.addEventListener(Events.ON_DROP, new EventListener() {
					public void onEvent(Event event) throws Exception {
						onDrop$right((DropEvent) event);
					}
                });
            }});
        
        
        actwnd$left.setItemRenderer(new ListitemRenderer(){
            @SuppressWarnings("unchecked")
            public void render(Listitem row, Object data) throws Exception {
            	Action pAction = (Action) data;
                row.setValue(pAction);
                row.setDraggable("true");
                row.setDroppable("true");
                Listcell lc = new Listcell(pAction.getName());
                lc.setSrc(pAction.getIcon());
                row.appendChild(lc);
            }});
        
        
        actwnd$right.setItemRenderer(new ListitemRenderer(){
            @SuppressWarnings("unchecked")
            public void render(Listitem row, Object data) throws Exception {
            	Action pAction = (Action) data;
                row.setValue(pAction);
                row.setDraggable("true");
                row.setDroppable("true");
                Listcell lc = new Listcell(pAction.getName());
                lc.setSrc(pAction.getIcon());
                row.appendChild(lc);
            }});
        
        actwnd$deal_id.setItemRenderer(new ListitemRenderer(){
            @SuppressWarnings("unchecked")
            public void render(Listitem row, Object data) throws Exception {
            	RefData pref_data = (RefData) data;
                row.setValue(pref_data);
                //row.setDraggable("true");
                //row.setDroppable("true");
                Listcell lc = new Listcell(pref_data.getLabel());
                //lc.setSrc(pAction.getIcon());
                row.appendChild(lc);
            }});
        
        
    	lrole.setItemRenderer(new ListitemRenderer(){
           
            public void render(Listitem row, Object data) throws Exception {
                Role pRole = (Role) data;
                row.setValue(pRole);
               // row.appendChild(new Listcell(pRole.getId()));
                row.appendChild(new Listcell(pRole.getName()));
                
               // row.appendChild(new Listcell(pRole.getDataaccess()));

            }});
    	
    	refresh();
	}
	
	public void refresh(){
    	lrole.setModel((new ListModelList(UserService.getRole(alias))));
    	if(lrole.getModel().getSize()>0){
    	    this.current =(Role) lrole.getModel().getElementAt(0);
    		lrole.setSelectedIndex(0);
            SelectEvent evt = new SelectEvent("onSelect", lrole,lrole.getSelectedItems());
            Events.sendEvent(evt);
            caccess.setSelecteditem(current.getDataaccess()+"");
    	}
    	
		
	}
	
	public void onClick$btn_save(){
		current.setName(tbText.getValue());
		int curinf = lrole.getSelectedIndex();
		current.setDataaccess(Integer.parseInt( caccess.getSelectedItem().getValue().toString()));
		UserService.update(current,alias);
		refresh();
		lrole.setSelectedIndex(curinf);
        SelectEvent evt = new SelectEvent("onSelect", lrole,lrole.getSelectedItems());
        Events.sendEvent(evt);

	}
	public void onClick$btn_del() throws InterruptedException{
		
		
		
		Messagebox.show("Удалить роль?", "IS Smart Bank", Messagebox.OK  | Messagebox.CANCEL, Messagebox.QUESTION, new org.zkoss.zk.ui.event.EventListener() {
		    public void onEvent(Event evt) throws InterruptedException {
		        if (evt.getName().equals("onOK")) {
		        	if (UserService.remove(current,alias)==-1)
		        	{
		        		alert("Удалять можно только те роли, которые не назначены никому из пользователей.");
		        		return;
		        	}
		        	refresh();
		            alert("Роль удалена");
		        } 
		    }
		});
		
	}
	public void onClick$btn_add(){
		addwnd$caccess.setModel((new ListModelList(com.is.utils.RefDataService.getAccess(alias))));
		addwnd.setVisible(true);
	}
	public void onClick$btn_save$addwnd(){
		
		if (CheckNull.isEmpty(addwnd$caccess.getValue())){alert("Не заполнено поле доступ");return;} 
		current = new Role();
		current.setName(addwnd$tbText.getValue());
		current.setDataaccess(Integer.parseInt( addwnd$caccess.getSelectedItem().getValue().toString()));
		UserService.create(current,alias); 
		addwnd.setVisible(false);
		refresh();
		
	}
	public void onClick$btn_cancel$addwnd(){
		addwnd.setVisible(false);
	}
	
	
	public void onSelect$lrole(){
		
		int index = lrole.getSelectedIndex();
		ListModelList model = (ListModelList)lrole.getModel();
		current = (Role) model.get(index);
		tbText.setValue(current.getName());
		caccess.setSelecteditem(current.getDataaccess()+"");
		right.setModel((new ListModelList(UserService.getModuleInRole(current.getId(),alias))));
		left.setModel((new ListModelList(UserService.getModuleNotInRole(current.getId(),alias))));
	}
    public void onDrop$right(DropEvent e){
        if (e.getDragged() instanceof Listitem) {
                Listitem        li =(Listitem)e.getDragged();
                Module module= (Module) li.getValue();
               // System.out.println("Left add role "+current.getId()+ " module "+module.getId());
                UserService.addModule(current.getId(), module.getId(),alias);
        		right.setModel((new ListModelList(UserService.getModuleInRole(current.getId(),alias))));
        		left.setModel((new ListModelList(UserService.getModuleNotInRole(current.getId(),alias))));
        }
    }	
    public void onDrop$left(DropEvent e) {
        if (e.getDragged() instanceof Listitem) {
                Listitem        li =(Listitem)e.getDragged();
                Module module= (Module) li.getValue();
               // System.out.println("right remove role "+current.getId()+ " module "+module.getId());
                UserService.removeModule(current.getId(), module.getId(),alias);
        		right.setModel((new ListModelList(UserService.getModuleInRole(current.getId(),alias))));
        		left.setModel((new ListModelList(UserService.getModuleNotInRole(current.getId(),alias))));
        }
    }
    
    public void onInitRenderLater$deal_id$actwnd() {
    	if(actwnd$deal_id.getModel().getSize()>0) {
    		actwnd$deal_id.setSelectedIndex(0);
    	}
    }
    
    public void onSelect$deal_id$actwnd() {
    	Listitem li =(Listitem) actwnd$deal_id.getSelectedItem();
    	RefData rd = (RefData) li.getValue();
    	this.deal_id = Integer.parseInt(rd.getData());
    	actwnd$right.setModel((new ListModelList(UserService.getActionInRole(current.getId(),currmodule.getId(),alias, deal_id))));
    	actwnd$left.setModel((new ListModelList(UserService.getActionNotInRole(current.getId(),currmodule.getId(),alias, deal_id))));
    }
    
    public void onDoubleClick$right() {
    	try {
	    	Listitem li =(Listitem) right.getSelectedItem();
	    	Module module= (Module) li.getValue();
	    	currmodule = module;
	    	deal_id = 0;
	    	List<RefData> deal_idlist = UserService.get_deal_id(currmodule.getGroup_id(), currmodule.getId(), alias);
	    	if (deal_idlist.size() > 0) {
	    		deal_id = Integer.parseInt(deal_idlist.get(0).getData());
	    	} else {
	    		deal_idlist.add(new RefData("0", "Все действия"));
	    	}
	    	actwnd$deal_id.setModel((new ListModelList(deal_idlist)));
	    	if(actwnd$deal_id.getModel().getSize()>0) {
	    		actwnd$deal_id.setSelectedIndex(0);
	    	}
	    	//System.out.println(" role "+current.getId()+ " module "+module.getId());
	    	actwnd$right.setModel((new ListModelList(UserService.getActionInRole(current.getId(),module.getId(),alias, deal_id))));
	    	actwnd$left.setModel((new ListModelList(UserService.getActionNotInRole(current.getId(),module.getId(),alias, deal_id))));
	    	actwnd.setVisible(true);
    	} catch (Exception e) {
			e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
    		alert("При вызове действий произошла ошибка: "+e.getMessage());
		}
    }
    public void onDrop$right$actwnd(DropEvent e){
        if (e.getDragged() instanceof Listitem) {
                Listitem        li =(Listitem)e.getDragged();
                Action action= (Action) li.getValue();
               // System.out.println("Left add role "+current.getId()+ " module "+module.getId());
                UserService.addAction(current.getId(),currmodule.getId(), action,alias);
            	actwnd$right.setModel((new ListModelList(UserService.getActionInRole(current.getId(),currmodule.getId(),alias, deal_id))));
            	actwnd$left.setModel((new ListModelList(UserService.getActionNotInRole(current.getId(),currmodule.getId(),alias, deal_id))));
        }
    }	
    public void onDrop$left$actwnd(DropEvent e){
        if (e.getDragged() instanceof Listitem) {
                Listitem        li =(Listitem)e.getDragged();
                Action action= (Action) li.getValue();
               // System.out.println("right remove role "+current.getId()+ " module "+module.getId());
                UserService.removeAction(current.getId(),currmodule.getId(), action,alias);
            	actwnd$right.setModel((new ListModelList(UserService.getActionInRole(current.getId(),currmodule.getId(),alias, deal_id))));
            	actwnd$left.setModel((new ListModelList(UserService.getActionNotInRole(current.getId(),currmodule.getId(),alias, deal_id))));
        }
    }	
    

}
