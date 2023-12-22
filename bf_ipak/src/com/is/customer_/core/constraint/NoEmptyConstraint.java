package com.is.customer_.core.constraint;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zul.Constraint;

public class NoEmptyConstraint implements Constraint {

	@Override
	public void validate(Component comp, Object value) throws WrongValueException {
		if (value == null || String.valueOf(value).isEmpty())
			throw new WrongValueException(comp, "Поле не может быть пустым");
	}
}
