package com.is.clients.controllers.renderers;

import com.is.client_personmap.model.LegalEntity;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

import search.NCI.com.ipakyulibank.BPSearchResponceOrganization;

public class SapSearchRenderer implements ListitemRenderer {

	@Override
	public void render(Listitem row, Object data) throws Exception {
	    if (data instanceof BPSearchResponceOrganization) {
            BPSearchResponceOrganization resp = (BPSearchResponceOrganization) data;
            row.setValue(resp);

            row.appendChild(new Listcell(resp.getId_client_sap()));
            row.appendChild(new Listcell(resp.getId_client_nci()));
            row.appendChild(new Listcell(resp.getBranch()));
            row.appendChild(new Listcell(resp.getName()));
            row.appendChild(new Listcell(resp.getName_short()));
            row.appendChild(new Listcell(resp.getType()));
            row.appendChild(new Listcell(resp.getOpf()));
        }
        else{
	        if (data instanceof LegalEntity) {
                LegalEntity legalEntity = (LegalEntity) data;
                row.appendChild(new Listcell(null));
                row.appendChild(new Listcell(legalEntity.getId()));
                row.appendChild(new Listcell(legalEntity.getBranch()));
                row.appendChild(new Listcell(legalEntity.getName()));
                row.appendChild(new Listcell(legalEntity.getShort_name()));
                row.appendChild(new Listcell("ёридический"));
                row.appendChild(new Listcell(legalEntity.getOpf()));
                row.setValue(legalEntity);
            }
        }
	}

}
