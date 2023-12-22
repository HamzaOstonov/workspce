package com.is.customer_.core.constraint;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zul.Constraint;

import com.is.utils.RefCBox;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PassportNumberConstraint implements Constraint {

	@Override
	public void validate(Component comp, Object value) throws WrongValueException {
		if (value == null || String.valueOf(value).isEmpty())
			throw new WrongValueException(comp,"���� �� ����� ���� ������");
		Pattern pattern = Pattern.compile("^([0-9])\\1{6,}$");

		Component type_document = comp.getParent().getFirstChild().getNextSibling();
		if (type_document==null ) {
			type_document=comp.getParent().getParent().getFirstChild().getNextSibling();
		}
		RefCBox r = (RefCBox) type_document.getLastChild();
		if (type_document!=null){
			//Matcher matcher = pattern.matcher(String.valueOf(value));
			//if (matcher.matches())
				//throw new RuntimeException("� �������� �� ����� 7 ���������������� ���������� �����");

			if (r != null && (r.getValue().equals("1") || r.getValue().equals("6"))){
				String str = String.valueOf(value);
				Boolean isDigit = str.matches("[0-9]+");
				Boolean isLengthCorrect = str.length()==7;
				/*if (isLengthCorrect &&  isDigit)
					return;
				else
					throw new WrongValueException(comp,"����� ������� �� 7 ����");*/
				if (!isDigit || !isLengthCorrect)
					throw new WrongValueException(comp,"����� ������� �� 7 ����");
			}
		}
	}

}
