/**
 * 
 */
package com.is.userti;

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
import org.zkoss.zul.Button;
import org.zkoss.zul.Grid;
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
	private Grid addgrd;
	
	public Role current= new Role();
	public Module currmodule = new Module();
	private String uname,curip;
    private int uid;
    private String old_role_name, old_role_access_name, deal_id_desc;
    private int old_access;
    private Button bt_add_mod, bt_rem_mod, actwnd$bt_add_act, actwnd$bt_rem_act;
    private Module cur_mod=null;
    private Action cur_act=null;
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
		uid= (Integer) session.getAttribute("uid");
        uname = (String) session.getAttribute("uname");
        curip = (String) session.getAttribute("curip");
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
                       // lc.setTooltiptext("Жопа");
                        row.appendChild(lc);
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
    	
    	
    	//onSelect$lrole();
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
		current.setDataaccess(Integer.parseInt( caccess.getSelectedItem().getValue().toString()));
		UserService.update(current,alias);
		
		if (old_role_name.compareTo(tbText.getValue()) != 0)
		{
			UserService.UsrLog(new UserActionsLog(uid, uname, curip, 4, 1, "Роль ["+old_role_name+"] переименована в ["+current.getName()+"]" ,branch));
		}
		if (old_access != Integer.parseInt( caccess.getSelectedItem().getValue().toString()))
		{
			UserService.UsrLog(new UserActionsLog(uid, uname, curip, 4, 1, "Для роли ["+current.getName()+"] доступ изменен с ["+old_role_access_name+"] на ["+caccess.getSelectedItem().getLabel()+"]", branch));
		}
			
		old_role_name = current.getName();
		old_access = current.getDataaccess();
		old_role_access_name = caccess.getSelectedItem().getLabel();
		
		refresh();
	}
	public void onClick$btn_del(){
		/*Messagebox.show("?","text");,
				Messagebox.QUESTION,
				new EventListener(){
			public void onEvent(Event e)
			{
				if ("onOK".equals(e.getName()))
				{
					UserService.remove(current,branch);
				}
				else return;
			}
		});*/
		UserService.remove(current,alias);
		UserService.UsrLog(new UserActionsLog(uid, uname, curip, 4, 1, "Удалена роль ["+current.getName()+"]" ,branch));
		refresh();
	}
	public void onClick$btn_add(){
		addwnd$caccess.setModel((new ListModelList(com.is.utils.RefDataService.getAccess(alias))));
		addwnd.setVisible(true);
		//addwnd$tbText.setValue("");
		//addwnd$caccess.setSelecteditem(null);
	}
	public void onClick$btn_save$addwnd(){
		current = new Role();
		current.setName(addwnd$tbText.getValue());
		current.setDataaccess(Integer.parseInt( addwnd$caccess.getValue()));
		current = UserService.create(current,alias); 
		
		UserService.UsrLog(new UserActionsLog(uid, uname, curip, 4, 1, "Добавлена роль ["+current.getName()+"]" ,branch));
		
		tbText.setValue(current.getName());
		caccess.setSelecteditem(Integer.toString(current.getDataaccess()));
		
		addwnd.setVisible(false);
		lrole.setModel((new ListModelList(UserService.getRole(alias))));
		//refresh();
		
	}
	public void onClick$btn_cancel$addwnd(){
		addwnd.setVisible(false);
	}
	
	public void onClick$btn_cancel$actwnd()
	{
		actwnd.setVisible(false);
	}
	
	
	public void onSelect$lrole(){
		
		int index = lrole.getSelectedIndex();
		ListModelList model = (ListModelList)lrole.getModel();
		current = (Role) model.get(index);
		tbText.setValue(current.getName());
		caccess.setSelecteditem(current.getDataaccess()+"");
		right.setModel((new ListModelList(UserService.getModuleInRole(current.getId(),alias))));
		left.setModel((new ListModelList(UserService.getModuleNotInRole(current.getId(),alias))));
		old_role_name = current.getName();
		old_access = current.getDataaccess();
		if (caccess.getSelectedItem() != null) old_role_access_name = caccess.getSelectedItem().getLabel();
	}
    public void onDrop$right(DropEvent e){
        if (e.getDragged() instanceof Listitem) {
                Listitem        li =(Listitem)e.getDragged();
                Module module= (Module) li.getValue();
               // System.out.println("Left add role "+current.getId()+ " module "+module.getId());
                UserService.addModule(current.getId(), module.getId(),alias);
                
                UserService.UsrLog(new UserActionsLog(uid, uname, curip, 4, 1, "Роли ["+current.getName()+"] добавлен модуль ["+module.getName()+"]", branch));
                
        		right.setModel((new ListModelList(UserService.getModuleInRole(current.getId(),alias))));
        		left.setModel((new ListModelList(UserService.getModuleNotInRole(current.getId(),alias))));
        }
    }	
    public void onDrop$left(DropEvent e){
        if (e.getDragged() instanceof Listitem) {
                Listitem        li =(Listitem)e.getDragged();
                Module module= (Module) li.getValue();
               // System.out.println("right remove role "+current.getId()+ " module "+module.getId());
                UserService.removeModule(current.getId(), module.getId(),alias);
                
                UserService.UsrLog(new UserActionsLog(uid, uname, curip, 4, 1, "Из роли ["+current.getName()+"] удален модуль ["+module.getName()+"]", branch));
                
        		right.setModel((new ListModelList(UserService.getModuleInRole(current.getId(),alias))));
        		left.setModel((new ListModelList(UserService.getModuleNotInRole(current.getId(),alias))));
        }
    }	
    public void onSelect$deal_id$actwnd()
    {
    	Listitem li =(Listitem) actwnd$deal_id.getSelectedItem();
    	RefData rd = (RefData) li.getValue();
    	this.deal_id = Integer.parseInt(rd.getData());
    	deal_id_desc = rd.getLabel();
    	onDoubleClick$right();
    }
    
    public void onDoubleClick$right() {
    	Listitem li =(Listitem) right.getSelectedItem();
    	Module module= (Module) li.getValue();
    	currmodule = module;
    	//System.out.println(" role "+current.getId()+ " module "+module.getId());
    	actwnd$right.setModel((new ListModelList(UserService.getActionInRole(current.getId(),module.getId(),alias, deal_id))));
    	actwnd$left.setModel((new ListModelList(UserService.getActionNotInRole(current.getId(),module.getId(),alias, deal_id))));
    	actwnd$deal_id.setModel((new ListModelList(com.is.utils.RefDataService.get_deal_id(alias, 144))));
    	actwnd.setVisible(true);
    }
    public void onDrop$right$actwnd(DropEvent e){
        if (e.getDragged() instanceof Listitem) {
                Listitem        li =(Listitem)e.getDragged();
                Action action= (Action) li.getValue();
               // System.out.println("Left add role "+current.getId()+ " module "+module.getId());
                action.setDeal_id(deal_id);
                UserService.addAction(current.getId(),currmodule.getId(), action,alias);
                
                UserService.UsrLog(new UserActionsLog(uid, uname, curip, 4, 1, "Для роли ["+current.getName()+"], модуля ["+currmodule.getName()+"] добавлено действие ["+action.getName()+"] подгруппы ["+deal_id_desc+"]", branch));
                
            	actwnd$right.setModel((new ListModelList(UserService.getActionInRole(current.getId(),currmodule.getId(),alias, deal_id))));
            	actwnd$left.setModel((new ListModelList(UserService.getActionNotInRole(current.getId(),currmodule.getId(),alias, deal_id))));
        }
    }	
    public void onDrop$left$actwnd(DropEvent e){
        if (e.getDragged() instanceof Listitem) {
                Listitem        li =(Listitem)e.getDragged();
                Action action= (Action) li.getValue();
                action.setDeal_id(deal_id);
               // System.out.println("right remove role "+current.getId()+ " module "+module.getId());
                UserService.removeAction(current.getId(),currmodule.getId(), action,alias);
                UserService.UsrLog(new UserActionsLog(uid, uname, curip, 4, 1, "Из роли ["+current.getName()+"], модуля ["+currmodule.getName()+"] удалено действие ["+action.getName()+"] подгруппы ["+deal_id_desc+"]", branch));
            	actwnd$right.setModel((new ListModelList(UserService.getActionInRole(current.getId(),currmodule.getId(),alias, deal_id))));
            	actwnd$left.setModel((new ListModelList(UserService.getActionNotInRole(current.getId(),currmodule.getId(),alias, deal_id))));
        }
    }	
    
    public void onSelect$right()
    {
    	cur_mod = (Module)(right.getSelectedItem().getValue());
    }

    public void onSelect$left()
    {
    	cur_mod = (Module)(left.getSelectedItem().getValue());
    }

    public void onClick$bt_add_mod()
    {
    	if (cur_mod == null){alert("Модуль не выбран"); return;}
    	
    	UserService.addModule(current.getId(), cur_mod.getId(),alias);
        
        UserService.UsrLog(new UserActionsLog(uid, uname, curip, 4, 1, "Роли ["+current.getName()+"] добавлен модуль ["+cur_mod.getName()+"]", branch));
        
		right.setModel((new ListModelList(UserService.getModuleInRole(current.getId(),alias))));
		left.setModel((new ListModelList(UserService.getModuleNotInRole(current.getId(),alias))));
    }

    public void onClick$bt_rem_mod()
    {
    	if (cur_mod == null){alert("Модуль не выбран"); return;}
    	
    	UserService.removeModule(current.getId(), cur_mod.getId(),alias);
        
        UserService.UsrLog(new UserActionsLog(uid, uname, curip, 4, 1, "Из роли ["+current.getName()+"] удален модуль ["+cur_mod.getName()+"]", branch));
        
		right.setModel((new ListModelList(UserService.getModuleInRole(current.getId(),alias))));
		left.setModel((new ListModelList(UserService.getModuleNotInRole(current.getId(),alias))));
    }
    
    public void onSelect$right$actwnd()
    {
    	cur_act = (Action)(actwnd$right.getSelectedItem().getValue());
    }

    public void onSelect$left$actwnd()
    {
    	cur_act = (Action)(actwnd$left.getSelectedItem().getValue());
    }
    
    public void onClick$bt_add_act$actwnd()
    {
    	if (cur_act == null){alert("Действие не выбрано"); return;}
    	
    	cur_act.setDeal_id(deal_id);
        UserService.addAction(current.getId(),currmodule.getId(), cur_act,alias);
        
        UserService.UsrLog(new UserActionsLog(uid, uname, curip, 4, 1, "Для роли ["+current.getName()+"], модуля ["+currmodule.getName()+"] добавлено действие ["+cur_act.getName()+"] подгруппы ["+deal_id_desc+"]", branch));
        
    	actwnd$right.setModel((new ListModelList(UserService.getActionInRole(current.getId(),currmodule.getId(),alias, deal_id))));
    	actwnd$left.setModel((new ListModelList(UserService.getActionNotInRole(current.getId(),currmodule.getId(),alias, deal_id))));
    }

    public void onClick$bt_rem_act$actwnd()
    {
    	if (cur_act == null){alert("Действие не выбрано"); return;}
    	cur_act.setDeal_id(deal_id);
    	UserService.removeAction(current.getId(),currmodule.getId(), cur_act,alias);
        UserService.UsrLog(new UserActionsLog(uid, uname, curip, 4, 1, "Из роли ["+current.getName()+"], модуля ["+currmodule.getName()+"] удалено действие ["+cur_act.getName()+"] подгруппы ["+deal_id_desc+"]", branch));
    	actwnd$right.setModel((new ListModelList(UserService.getActionInRole(current.getId(),currmodule.getId(),alias, deal_id))));
    	actwnd$left.setModel((new ListModelList(UserService.getActionNotInRole(current.getId(),currmodule.getId(),alias, deal_id))));
    }

}
