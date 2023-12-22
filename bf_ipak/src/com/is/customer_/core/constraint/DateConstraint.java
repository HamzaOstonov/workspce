package com.is.customer_.core.constraint;

import org.joda.time.DateTime;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zul.Constraint;
import org.zkoss.zul.Datebox;

public class DateConstraint implements Constraint {
	@Override
	public void validate(Component comp, Object value)
			throws WrongValueException {
		if (value == null)
			throw new WrongValueException(comp, "Поле не может быть пустым");
		try {
			Datebox dateBox = (Datebox) comp;
			DateTime dateTime = new DateTime(dateBox.getValue());
			System.out.println("DATETIME YEARS " + dateTime.getYear());
		} catch (WrongValueException w) {
			throw new WrongValueException("Укажите корректную дату");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
