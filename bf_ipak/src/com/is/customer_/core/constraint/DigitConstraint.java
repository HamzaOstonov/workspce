package com.is.customer_.core.constraint;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zul.Constraint;

public class DigitConstraint implements Constraint{

	@Override
	public void validate(Component comp, Object value)
			throws WrongValueException {
		if (value != null && !String.valueOf(value).isEmpty()){
			if (!String.valueOf(value).matches("[0-9]+"))
					throw new WrongValueException(comp,"ֲגוהטעו צטפנû");
		}
	}
}
