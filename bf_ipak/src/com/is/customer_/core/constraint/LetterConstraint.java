package com.is.customer_.core.constraint;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zul.Constraint;
import org.zkoss.zul.Messagebox;

public class LetterConstraint implements Constraint{
	@Override
	public void validate(Component comp, Object value) throws WrongValueException {
		if (value == null || String.valueOf(value).isEmpty())
			throw new WrongValueException(comp,"Поле не может быть пустым");
		
		boolean valid = true;
		
		for (int i = 0; i < String.valueOf(value).length(); i++) {
			if (!Character.isLetter(String.valueOf(value).charAt(i)))
				if (String.valueOf(value).charAt(i) != 32
                        && String.valueOf(value).charAt(i) != 39
                        && String.valueOf(value).charAt(i) != 45)
					valid = false;
		}
		
		if (!valid)
			throw new WrongValueException(comp,"Некорректный ввод");
	}
}
