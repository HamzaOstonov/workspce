package com.is.sign;

import java.text.SimpleDateFormat;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Window;
import org.zkoss.zul.api.Listbox;

import com.is.ConnectionPool;

public class SignProtocolViewCtrl  extends GenericForwardComposer{
	static final long serialVersionUID = 123456789111L;
	
	private Window signerrwnd;
	private Listbox signProtocol;
    SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
    private int signtype = 1;

    public SignProtocolViewCtrl() {
        super('$', false, false);
    }
    
	@Override
	public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        signtype = Integer.parseInt(ConnectionPool.getValue("SIGN_TYPE"));
        
        signProtocol.setItemRenderer(new ListitemRenderer(){
        @SuppressWarnings("unchecked")
        public void render(Listitem row, Object data) throws Exception {
        	SignList pSignList = (SignList) data;
        	row.setValue(pSignList);
        	//row.appendChild(new Listcell(pSignList.getObject_id()+""));
        	row.appendChild(new Listcell(pSignList.getObject_num()));
        	//row.appendChild(new Listcell(pSignList.getSign_text()));
        	//row.appendChild(new Listcell(pSignList.getSign_data()));
        	row.appendChild(new Listcell(pSignList.getRes_code()+""));
        	row.appendChild(new Listcell(pSignList.getErr_message()));
        	//row.appendChild(new Listcell(pSignList.getSign_log_id()+""));
        }});
	}
	
	public void init(List<SignList> list) {
		signProtocol.setModel(new BindingListModelList(list, true));
		signerrwnd.setVisible(true);
	}
	
	public void onClick$btn_close() {
		signerrwnd.setVisible(false);
	}
}
