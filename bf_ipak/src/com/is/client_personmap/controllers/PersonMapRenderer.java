package com.is.client_personmap.controllers;

import java.util.Map;

import org.zkoss.zul.Label;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

import com.is.base.Dao;
import com.is.client_personmap.PersonMapUtil;
import com.is.client_personmap.model.PersonMap;

public class PersonMapRenderer implements ListitemRenderer {
	private Map<String,String> personKinds;
	private Map<Integer,String> states;
	private Dao<PersonMap> personMapDao;
	
	private static final String PERSON_MAP_ATTR = "personMap";
	
	public PersonMapRenderer(Dao<PersonMap> personMapDao, Map<String,String> personKinds, Map<Integer,String> states) {
		this.personMapDao = personMapDao;
		this.personKinds = personKinds;
		this.states = states;
	}
	@Override
	public void render(Listitem row, Object data) throws Exception {
		PersonMap pm = (PersonMap) data;
		row.setValue(pm);
		row.appendChild(new Listcell(pm.getBranch()));
		row.appendChild(new Listcell(getPersonType(pm.getPerson_type())));
		row.appendChild(new Listcell(pm.getPrson_name()));
		if (pm.getPerson_kind().equals(PersonMapUtil.PERSONKIND_FOUNDER)) {
			row.appendChild(new Listcell(pm.getCapital().getPart_of_capital() != null 
					? pm.getCapital().getPart_of_capital().toPlainString()
					: ""));
		} else {
			row.appendChild(new Listcell(personKinds.get(pm.getPerson_kind())));
		}
		if(pm.isFromSap()) {
			row.setStyle("background: #4CAF50;color: white;");
		}
		
		row.appendChild(new Listcell(states.get(pm.getState())));
		if(pm.isOld() && !pm.getPerson_kind().equals(PersonMapUtil.PERSONKIND_FOUNDER)) {
			Label label = new Label("Недействительное контактное лицо");
			Listcell buttonCell = new Listcell();
			buttonCell.appendChild(label);
			row.appendChild(buttonCell);
		}
		
	}

    private String getPersonType(String person_type) {
        return person_type.equalsIgnoreCase("P") ? "Физический" : "Юридический";
    }

}
