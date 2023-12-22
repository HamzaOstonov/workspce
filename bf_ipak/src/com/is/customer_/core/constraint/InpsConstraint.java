package com.is.customer_.core.constraint;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zul.Constraint;

public class InpsConstraint implements Constraint{
	@Override
	public void validate(Component comp, Object value) throws WrongValueException {
		if (value != null){
			String input = String.valueOf(value);
			if (!input.isEmpty()){
				if (input.length() < 14 || !(input.matches("[0-9]+")))
					throw new WrongValueException(comp,"ָֽֿׁ סמסעמטע טח 14 צטפנ");
			}
		}
	}
}
