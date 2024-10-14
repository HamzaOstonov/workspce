package com.is.tietovisa.customer;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zul.Constraint;

public class PhoneNumberConstraint implements Constraint{

	@Override
	public void validate(Component comp, Object value)
			throws WrongValueException {
		if (value != null && !String.valueOf(value).isEmpty()){
			if (!String.valueOf(value).matches("\\+[0-9]+"))
					throw new WrongValueException(comp,"Введите по формату +998********* состоящую из 12 цифр");
			if (!String.valueOf(value).substring(0,4).equals("+998"))
				throw new WrongValueException(comp,"Введите по формату +998********* состоящую из 12 цифр");
			if (String.valueOf(value).length()!=13 )
				throw new WrongValueException(comp,"Введите по формату +998********* состоящую из 12 цифр");
			
		}
	}
}
