package com.is.clienta;

import java.util.Map;

import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

public class ClientRenderer implements ListitemRenderer{
	private Map<String, String> clientState;
	private Map<String, String> clientTypes;
	
	public ClientRenderer(Map<String, String> states, Map<String, String> clientTypes) {
		this.clientState = states;
		this.clientTypes = clientTypes;
	}
	@Override
	public void render(Listitem row, Object data) throws Exception {
		ClientA pClientJ = (ClientA) data;
    	row.setValue(pClientJ);
        row.appendChild(new Listcell(Long.toString(pClientJ.getId())));
        row.appendChild(new Listcell(pClientJ.getId_client()));
        row.appendChild(new Listcell(pClientJ.getName()));
        row.appendChild(new Listcell(clientTypes.get(pClientJ.getCode_type())));
        row.appendChild(new Listcell(clientState.get(pClientJ.getState())));
//        if(currentId != null && currentId.equals(pClientJ.getId_client())){
//        	dataGrid.setSelectedItem(row);
//        }
	}

}
