package com.is.user;

import java.sql.Date;

import java.text.SimpleDateFormat;

import com.is.utils.CheckNull;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.DropEvent;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
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
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;


import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.is.utils.RefRenderer;

@SuppressWarnings("serial")
public class UserViewCtrl extends GenericForwardComposer{
        private Div frm;
        private Paging contactPaging;
        private Div grd;
        private Listbox dataGrid;
        private Paging userPaging;
        private  int _pageSize = 15;
        private int _startPageNumber = 0;
        private int _totalSize = 0;
        private boolean _needsTotalSizeUpdate = true;
        private Grid addgrd,frmgrd,fgrd,pwdwnd$pwdgrd;
        private Toolbarbutton btn_last, btn_save;
        private Toolbarbutton btn_next;
        private Toolbarbutton btn_prev;
        private Toolbarbutton btn_first;
        private Toolbarbutton btn_add;
        private Toolbarbutton btn_search;
        private Toolbarbutton btn_back;
        private Toolbar tb;
        private Textbox id,/*username*/password,salt,firstname,secondname,surname,pseries,pnumber,pauthority,state;
        private Textbox aid,ausername,apassword,asalt,afirstname,asecondname,asurname,apseries,apnumber,apauthority,astate;
        private Textbox fid,fusername,fpassword,fsalt,ffirstname,fsecondname,fsurname,fpseries,fpnumber,fpauthority,fstate, trans_name;
        private Window rlwnd,pwdwnd;
        private Listbox rlwnd$left,rlwnd$right;
        private RefCBox branch,subbranch,abranch,asubbranch,fbranch,fsubbranch,ptype,aptype,fptype;
        private Textbox pwdwnd$oldpwd,pwdwnd$newpwd,pwdwnd$newpwd1;
        private Button rlwnd$bt_add_role, rlwnd$bt_rem_role;


        public User current= new User();
        public UserFilter filter;

        PagingListModel model = null;
        ListModelList lmodel =null;
        private AnnotateDataBinder binder;
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        private String branch1,alias;
        private String uname,curip;
        private int uid;
        private Role cur_role_id = null;




        public UserViewCtrl() {
                super('$', false, false);
        }
    /**
     *
     *
     */
    @Override
    public void doAfterCompose(Component comp) throws Exception {
            super.doAfterCompose(comp);
            // TODO Auto-generated method stub
                binder = new AnnotateDataBinder(comp);
                binder.bindBean("current", this.current);
                binder.loadAll();
        String[] parameter = (String[]) param.get("ht");
        //System.out.println("loadUserMd " + session.getAttribute("un")+"   "+session.getAttribute("pwd"));
        branch1 = (String) session.getAttribute("branch");
        uid= (Integer) session.getAttribute("uid");
        uname = (String) session.getAttribute("uname");
        curip = (String) session.getAttribute("curip");
        alias = (String) session.getAttribute("alias");
        if (parameter!=null){
                _pageSize = Integer.parseInt( parameter[0])/36;
                dataGrid.setRows(Integer.parseInt( parameter[0])/36);
        }

        UserService.fill_transleat_names(alias);
        
         filter = new UserFilter();

            dataGrid.setItemRenderer(new ListitemRenderer(){
        @SuppressWarnings("unchecked")
        public void render(Listitem row, Object data) throws Exception {
                    User pUser = (User) data;

                    row.setValue(pUser);
                    
                    row.appendChild(new Listcell(pUser.getId()+""));
                    row.appendChild(new Listcell(pUser.getBranch()));
                    row.appendChild(new Listcell(pUser.getUser_name()));
                    row.appendChild(new Listcell(pUser.getFull_name()));
                    row.appendChild(new Listcell(pUser.getTitle()));
                    row.appendChild(new Listcell(pUser.getTrans_name()));
                   // row.appendChild(new Listcell(pUser.getNot_chg_pas()));
                  //  row.appendChild(new Listcell(pUser.getLocked()));
                  //  row.appendChild(new Listcell(pUser.getDate_open()));
                  // row.appendChild(new Listcell(pUser.getPwd_expired()));
                    /*
                    row.appendChild(new Listcell(pUser.getId()+""));
                    row.appendChild(new Listcell(pUser.getUsername()));
                   
                    row.appendChild(new Listcell(pUser.getPassword()));
                    row.appendChild(new Listcell(pUser.getSalt()));
                    
                    row.appendChild(new Listcell(pUser.getBranch()));
                    row.appendChild(new Listcell(pUser.getSubbranch()));
                    row.appendChild(new Listcell(pUser.getFirstname()));
                    row.appendChild(new Listcell(pUser.getSecondname()));
                    row.appendChild(new Listcell(pUser.getSurname()));
                    row.appendChild(new Listcell(pUser.getPtype()+""));
                    row.appendChild(new Listcell(pUser.getPseries()));
                    row.appendChild(new Listcell(pUser.getPnumber()));
                    row.appendChild(new Listcell(pUser.getPauthority()));
                    row.appendChild(new Listcell(pUser.getState()+""));
*/

        }});

        refreshModel(_startPageNumber);
        
        
        rlwnd$left.setItemRenderer(new ListitemRenderer(){
            @SuppressWarnings("unchecked")
            public void render(Listitem row, Object data) throws Exception {
            	Role pRole = (Role) data;
                        row.setValue(pRole);
                        row.setDraggable("true");
                        row.setDroppable("true");
                        Listcell lc = new Listcell(pRole.getName());
                        row.appendChild(lc);
            }});
        
        
        rlwnd$right.setItemRenderer(new ListitemRenderer(){
            @SuppressWarnings("unchecked")
            public void render(Listitem row, Object data) throws Exception {
            	Role pRole = (Role) data;
                        row.setValue(pRole);
                        row.setDraggable("true");
                        row.setDroppable("true");
                        Listcell lc = new Listcell(pRole.getName());
                        row.appendChild(lc);
            }});
        
        branch.setModel((new ListModelList(com.is.utils.RefDataService.get_ipak_Mfo(alias)))); 
      // subbranch.setModel((new ListModelList(com.is.utils.RefDataService.getSusidiary())));
      // abranch.setModel((new ListModelList(com.is.utils.RefDataService.getMfo())));
       //asubbranch.setModel((new ListModelList(com.is.utils.RefDataService.getSusidiary())));
       fbranch.setModel((new ListModelList(com.is.utils.RefDataService.get_ipak_Mfo(alias))));
       //fsubbranch.setModel((new ListModelList(com.is.utils.RefDataService.getSusidiary())));
       //fptype.setModel((new ListModelList(com.is.utils.RefDataService.getDocType())));
       //aptype.setModel((new ListModelList(com.is.utils.RefDataService.getDocType())));
       //ptype.setModel((new ListModelList(com.is.utils.RefDataService.getDocType())));


    }

public void onPaging$userPaging(ForwardEvent event){
    final PagingEvent pe = (PagingEvent) event.getOrigin();
    _startPageNumber = pe.getActivePage();
    refreshModel(_startPageNumber);
}


private void refreshModel(int activePage){
        userPaging.setPageSize(_pageSize);
    model = new PagingListModel(activePage, _pageSize,filter,alias);
    

    if(_needsTotalSizeUpdate) {
            _totalSize = model.getTotalSize(filter,alias);
          //  _needsTotalSizeUpdate = false;
    }

    userPaging.setTotalSize(_totalSize);

    dataGrid.setModel((ListModel) model);
    if (model.getSize()>0){
    this.current =(User) model.getElementAt(0);
    sendSelEvt();
    }
}



// Omitted...
public User getCurrent() {
    return current;
}

public void setCurrent(User current) {
    this.current = current;
}

public void onDoubleClick$dataGrid$grd() {
                grd.setVisible(false);
                frm.setVisible(true);
                frmgrd.setVisible(true);
               // addgrd.setVisible(false);
                fgrd.setVisible(false);
                btn_back.setImage("/images/folder.png");
                btn_back.setLabel(Labels.getLabel("grid"));
}




public void onClick$btn_back() {
        if (frm.isVisible()){
            frm.setVisible(false);
            grd.setVisible(true);
            btn_back.setImage("/images/file.png");
            btn_back.setLabel(Labels.getLabel("back"));
            
            
            refreshModel(_startPageNumber);

        }else onDoubleClick$dataGrid$grd();
        btn_save.setVisible(false);
}


public void onClick$btn_first() {
        dataGrid.setSelectedIndex(0);
        sendSelEvt();
        btn_save.setVisible(false);
}
public void onClick$btn_last() {
        dataGrid.setSelectedIndex(model.getSize()-1);
        sendSelEvt();
        btn_save.setVisible(false);
}
public void onClick$btn_prev() {
        if (dataGrid.getSelectedIndex()!=0){
        dataGrid.setSelectedIndex(dataGrid.getSelectedIndex()-1);
        sendSelEvt();
        }
        btn_save.setVisible(false);
}
public void onClick$btn_next() {
        if (dataGrid.getSelectedIndex()!=(model.getSize()-1)){
        dataGrid.setSelectedIndex(dataGrid.getSelectedIndex()+1);
        sendSelEvt();
        }
        btn_save.setVisible(false);
}



private void sendSelEvt(){
        if (dataGrid.getSelectedIndex()==0){
                btn_first.setDisabled(true);
                btn_prev.setDisabled(true);
        }else{
                btn_first.setDisabled(false);
                btn_prev.setDisabled(false);
        }
        if(dataGrid.getSelectedIndex()==(model.getSize()-1)){
                btn_next.setDisabled(true);
                btn_last.setDisabled(true);
        }else{
                btn_next.setDisabled(false);
                btn_last.setDisabled(false);
        }
        SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
        Events.sendEvent(evt);
}


public void onClick$btn_add() {
        onDoubleClick$dataGrid$grd();
        frmgrd.setVisible(false);
       // addgrd.setVisible(true);
        fgrd.setVisible(false);
}

public void onClick$btn_search() {
        onDoubleClick$dataGrid$grd();
        frmgrd.setVisible(false);
     //   addgrd.setVisible(false);
        fgrd.setVisible(true);
        btn_save.setVisible(true);
}


public void onClick$btn_save() {
	try{
//        if(addgrd.isVisible()){
        	/*
                UserService.create(new User(
                        0,
                        ausername.getValue(),
                        apassword.getValue(),
                        "0",//asalt.getValue(),
                        abranch.getValue(),
                        asubbranch.getValue(),
                        afirstname.getValue(),
                        asecondname.getValue(),
                        asurname.getValue(),
                        Integer.parseInt( aptype.getValue()),
                        apseries.getValue(),
                        apnumber.getValue(),
                        apauthority.getValue(),
                        0
                ));
            CheckNull.clearForm(addgrd);
            frmgrd.setVisible(true);
            addgrd.setVisible(false);
            fgrd.setVisible(false);
            */
    //    }	else 
		if(fgrd.isVisible()){
            filter = new UserFilter();
            
           // filter.setId(Integer.parseInt(fid.getValue()));
            filter.setUser_name(fusername.getValue().toUpperCase());
           // filter.setPassword(fpassword.getValue());
           // filter.setSalt(fsalt.getValue());
            filter.setBranch(fbranch.getValue());
           // filter.setSubbranch(fsubbranch.getValue());
            filter.setFull_name(ffirstname.getValue().toUpperCase());
            //filter.setSecondname(fsecondname.getValue());
           // filter.setSurname(fsurname.getValue());
          //  filter.setPtype(Integer.parseInt(fptype.getValue()));
          //  filter.setPseries(fpseries.getValue());
          //  filter.setPnumber(fpnumber.getValue());
          //  filter.setPauthority(fpauthority.getValue());
           // filter.setState(Integer.parseInt(fstate.getValue()));

      }else{
          /*
            current.setId(Integer.parseInt(id.getValue()));
            current.setUsername(username.getValue());
           // current.setPassword(password.getValue());
            //current.setSalt(salt.getValue());
            current.setBranch(branch.getValue());
            current.setSubbranch(subbranch.getValue());
            current.setFirstname(firstname.getValue());
            current.setSecondname(secondname.getValue());
            current.setSurname(surname.getValue());
            current.setPtype(Integer.parseInt(ptype.getValue()));
            current.setPseries(pseries.getValue());
            current.setPnumber(pnumber.getValue());
            current.setPauthority(pauthority.getValue());
            //current.setState(Integer.parseInt(state.getValue()));
          UserService.update(current);  
          */      
          }
    onClick$btn_back();
    refreshModel(_startPageNumber);

    SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
    Events.sendEvent(evt);
    } catch (Exception e) {
        e.printStackTrace();
    }

}
public void onClick$btn_cancel() {
        if(fgrd.isVisible()){
                filter = new UserFilter();
        }
    onClick$btn_back();
    frmgrd.setVisible(true);
    //addgrd.setVisible(false);
    fgrd.setVisible(false);
    //CheckNull.clearForm(addgrd);
    CheckNull.clearForm(fgrd);
    refreshModel(_startPageNumber);
}

public void onClick$btn_role() {
	rlwnd$right.setModel((new ListModelList(UserService.getUserInRole(current.getId(), current.getBranch(),alias))));
	rlwnd$left.setModel((new ListModelList(UserService.getUserNotInRole(current.getId(), current.getBranch(),alias))));
	if (rlwnd$left.getItems().size()==0){rlwnd$bt_add_role.setDisabled(true);}
	else {rlwnd$bt_add_role.setDisabled(false);}
	if (rlwnd$right.getItems().size()==0){rlwnd$bt_rem_role.setDisabled(true);}
	else {rlwnd$bt_rem_role.setDisabled(false);}
	rlwnd.setVisible(true);
}

public void onDrop$right$rlwnd(DropEvent e){
    if (e.getDragged() instanceof Listitem) {
            Listitem        li =(Listitem)e.getDragged();
            Role role= (Role) li.getValue();
            UserService.addRole(current.getId(), role.getId(), current.getBranch(),alias);
            
            UserService.UsrLog(new UserActionsLog(
            		uid, 
            		uname, 
            		curip, 
            		3, 
            		1, 
            		"Добавлена роль "+role.getName()+" пользователю "+current.getUser_name().toUpperCase()+" филиала "+current.getBranch(),
            		branch1));
        	
            rlwnd$right.setModel((new ListModelList(UserService.getUserInRole(current.getId(), current.getBranch(),alias))));
        	rlwnd$left.setModel((new ListModelList(UserService.getUserNotInRole(current.getId(), current.getBranch(),alias))));
    }
}	
public void onDrop$left$rlwnd(DropEvent e){
    if (e.getDragged() instanceof Listitem) {
            Listitem        li =(Listitem)e.getDragged();
            Role role= (Role) li.getValue();
            UserService.removeRole(current.getId(), role.getId(), current.getBranch(),alias);

            UserService.UsrLog(new UserActionsLog(uid, uname, curip, 3, 1, "Удалена роль "+role.getName()+" пользователя "+current.getUser_name()+" филиала "+current.getBranch() ,branch1));
            
        	rlwnd$right.setModel((new ListModelList(UserService.getUserInRole(current.getId(), current.getBranch(),alias))));
        	rlwnd$left.setModel((new ListModelList(UserService.getUserNotInRole(current.getId(), current.getBranch(),alias))));
    }
}	


public void onClick$btn_pwd() {
	pwdwnd.setVisible(true);
}
public void onClick$btn_cancel$pwdwnd() {
	CheckNull.clearForm(pwdwnd$pwdgrd);
	pwdwnd.setVisible(false);
}
public void onClick$btn_save$pwdwnd() {
	
	Res rs = UserService.chPwd(current.getUser_name(), pwdwnd$newpwd.getValue(), pwdwnd$newpwd1.getValue(),alias);
	
	if (rs.getCode()==0){
		CheckNull.clearForm(pwdwnd$pwdgrd);
		pwdwnd.setVisible(false);
	    
	}else{
		alert(rs.getName());
	}
}

public void onSelect$right$rlwnd()
{
	cur_role_id = ((Role)rlwnd$right.getSelectedItem().getValue());
}

public void onSelect$left$rlwnd()
{
	cur_role_id = ((Role)rlwnd$left.getSelectedItem().getValue());
}

public void onClick$bt_add_role$rlwnd()
{
	if (cur_role_id == null){alert("Роль не выбрана"); return;}
	UserService.addRole(current.getId(), cur_role_id.getId(), current.getBranch(),alias);
    
    UserService.UsrLog(new UserActionsLog(uid, uname, curip, 3, 1, "Добавлена роль "+cur_role_id.getName()+" пользователю "+current.getUser_name().toUpperCase()+" филиала "+current.getBranch() ,branch1));
	
    rlwnd$right.setModel((new ListModelList(UserService.getUserInRole(current.getId(), current.getBranch(),alias))));
	rlwnd$left.setModel((new ListModelList(UserService.getUserNotInRole(current.getId(), current.getBranch(),alias))));
	if (rlwnd$left.getItems().size()==0){rlwnd$bt_add_role.setDisabled(true); return;}
	//rlwnd$left.setSelectedIndex(0);
	rlwnd$bt_rem_role.setDisabled(false);
	//cur_role_id = (Role)rlwnd$left.getItemAtIndex(0).getValue();
}

public void onClick$bt_rem_role$rlwnd()
{
	if (cur_role_id == null){alert("Роль не выбрана"); return;}
	UserService.removeRole(current.getId(), cur_role_id.getId(), current.getBranch(),alias);

    UserService.UsrLog(new UserActionsLog(uid, uname, curip, 3, 1, "Удалена роль "+cur_role_id.getName()+" пользователя "+current.getUser_name()+" филиала "+current.getBranch() ,branch1));
    
	rlwnd$right.setModel((new ListModelList(UserService.getUserInRole(current.getId(), current.getBranch(),alias))));
	rlwnd$left.setModel((new ListModelList(UserService.getUserNotInRole(current.getId(), current.getBranch(),alias))));
	if (rlwnd$right.getItems().size()==0){rlwnd$bt_rem_role.setDisabled(true); return;}
	rlwnd$bt_add_role.setDisabled(false);
	//rlwnd$right.setSelectedIndex(0);
	//cur_role_id = (Role)(rlwnd$right.getItemAtIndex(0).getValue());
}
public void onClick$save_trans()
{
	UserService.save_name_transleat(current.getId(), current.getBranch(), trans_name.getValue(), alias);
	alert("Сохранено");
}

}


