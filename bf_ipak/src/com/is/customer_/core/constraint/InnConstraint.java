package com.is.customer_.core.constraint;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zul.Constraint;

public class InnConstraint implements Constraint{

	@Override
	public void validate(Component comp, Object value) throws WrongValueException {
		if (value != null){
			String input = String.valueOf(value);
			if (!input.isEmpty()){
				if (input.length() < 9 || !(input.matches("[0-9]+")))
					throw new WrongValueException(comp,"Инн состоит из 9 цифр");
				
			}
		}
	}
	
}
