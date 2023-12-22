package com.is.customer_.core.constraint;
import org.apache.commons.validator.routines.EmailValidator;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zul.Constraint;

public class EmailConstraint implements Constraint{

	@Override
	public void validate(Component comp, Object value)
			throws WrongValueException {
		if (value != null && !String.valueOf(value).isEmpty()){
			if (!EmailValidator.getInstance(true).isValid(
					String.valueOf(value))){
				throw new WrongValueException(comp,"¬ведите корректный e-mail");
			}
		}
	}

}
