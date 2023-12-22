package com.is.customer_.core.constraint;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zul.Constraint;

import com.is.utils.RefCBox;

public class PassportSerialConstraint implements Constraint {
	@Override
	public void validate(Component comp, Object value) throws WrongValueException {
		if (value == null || String.valueOf(value).isEmpty())
			throw new WrongValueException(comp, "Поле не может быть пустым");
		Component type_document = comp.getParent().getFirstChild().getNextSibling();
		RefCBox r = (RefCBox) type_document.getLastChild();
		
		if (r != null) {
			if (r.getId().equalsIgnoreCase("p_type_document")
					|| r.getId().equalsIgnoreCase("ap_type_document")) {
				if (r.getValue().equalsIgnoreCase("1") || r.getValue().equals("6")){
					String str = String.valueOf(value);
					//[A-Za-z]+
					// \\w+
					if (str.length()!=2 || !(str.matches("[A-Za-z]+")))
						throw new WrongValueException(comp,"Серия паспорта из 2 латинских символов");
				}
				else{
					String str = String.valueOf(value);
					if (str.length() > 9)
						throw new WrongValueException(comp,"Серия документа не больше 9 символов");
				}
			}
		}
	}
}
