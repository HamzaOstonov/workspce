package com.is.customer_.core.constraint;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zul.Constraint;

public class LatinConstraint implements Constraint {

	@Override
	public void validate(Component comp, Object value) throws WrongValueException {
		if (value == null || String.valueOf(value).isEmpty()){
			if (comp!=null){
				String id = comp.getId();
				Boolean b1 = id.equals("p_patronymic");
				if (!b1)
					throw new WrongValueException(comp,"Поле не может быть пустым");
			}
		}
		if (value != null && !String.valueOf(value).isEmpty())
			if (!String.valueOf(value).matches("[A-Za-z\\s\\-']+"))
				throw new WrongValueException(comp, "Только латинские буквы");
	}
}
