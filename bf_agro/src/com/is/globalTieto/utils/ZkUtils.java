package com.is.globalTieto.utils;

import java.math.BigDecimal;

import com.is.tieto_capital.refill.utils.InputElHandler;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Row;
import org.zkoss.zul.impl.InputElement;

public class ZkUtils {

	public static void clearForm(Object comp) {
		try {
			if (comp instanceof org.zkoss.zul.Textbox) {
				((org.zkoss.zul.Textbox) comp).setConstraint("");
				((org.zkoss.zul.Textbox) comp).setValue(null);
			} else if (comp instanceof org.zkoss.zul.Intbox) {
				((org.zkoss.zul.Intbox) comp).setConstraint("");
				((org.zkoss.zul.Intbox) comp).setValue(null);
			} else if (comp instanceof org.zkoss.zul.Longbox) {
				((org.zkoss.zul.Longbox) comp).setConstraint("");
				((org.zkoss.zul.Longbox) comp).setValue(null);
			} else if (comp instanceof org.zkoss.zul.Decimalbox) {
				((org.zkoss.zul.Decimalbox) comp).setConstraint("");
				((org.zkoss.zul.Decimalbox) comp).setValue(BigDecimal.ZERO);
				((org.zkoss.zul.Decimalbox) comp).setRawValue(null);
			} else if (comp instanceof org.zkoss.zul.Datebox) {
				((org.zkoss.zul.Datebox) comp).setConstraint("");
				((org.zkoss.zul.Datebox) comp).setText(null);
			} else if (comp instanceof com.is.utils.RefCBox) {
				((com.is.utils.RefCBox) comp).setConstraint("");
				((com.is.utils.RefCBox) comp).setValue(null);
			} else if (comp instanceof org.zkoss.zul.impl.XulElement) {
				for (Object obj : ((org.zkoss.zul.impl.XulElement) comp).getChildren()) {
					clearForm(obj);
				}
				;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void changeForm(Object comp, InputElHandler handler) {
		try {
			if (comp instanceof org.zkoss.zul.impl.InputElement) {
				handler.makeChanges((org.zkoss.zul.impl.InputElement) comp);
			} else if (comp instanceof org.zkoss.zul.impl.XulElement) {
				for (Object obj : ((org.zkoss.zul.impl.XulElement) comp).getChildren()) {
					changeForm(obj, handler);
				}
				;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void checkAllRows(Object comp, boolean checked) {
		try {
			if (comp instanceof Checkbox) {
				((Checkbox) comp).setChecked(checked);
			} else if (comp instanceof org.zkoss.zul.impl.XulElement) {
				for (Object obj : ((org.zkoss.zul.impl.XulElement) comp).getChildren()) {
					checkAllRows(obj, checked);
				}
				;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void checkAllRowsUnderSpecificColumn(String columnAttr, String attrValue, Object comp,
			boolean checked) {
		try {
			if (comp instanceof Checkbox) {
				String attr = (String) ((Checkbox) comp).getAttribute(columnAttr);
				if (attr != null && attr.equals(attrValue)) {
					((Checkbox) comp).setChecked(checked);
				}
			} else if (comp instanceof org.zkoss.zul.impl.XulElement) {
				for (Object obj : ((org.zkoss.zul.impl.XulElement) comp).getChildren()) {
					checkAllRowsUnderSpecificColumn(columnAttr, attrValue, obj, checked);
				}
				;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void removeStyle(Object obj) {
		changeForm(obj, new InputElHandler() {
			@Override
			public void makeChanges(InputElement element) {
				element.setStyle(null);
			}
		});
	}

	public static void disableFormWithConstraint(Object obj, final boolean disabled) {
		changeForm(obj, new InputElHandler() {
			@Override
			public void makeChanges(InputElement element) {
				applyAttributesForDisabled(element, disabled);
			}
		});
	}

	public static void highlightField(Object obj, final String filedName) {
		changeForm(obj, new InputElHandler() {
			@Override
			public void makeChanges(InputElement element) {
				if (element.getId().equalsIgnoreCase(filedName)) {
					element.setStyle("color: blue");
				}
			}
		});
	}

	public static boolean validateForm(Object comp) {
		try {
			if (comp instanceof org.zkoss.zul.impl.InputElement) {
				if (!((org.zkoss.zul.impl.InputElement) comp).isDisabled()) {
					if (!((org.zkoss.zul.impl.InputElement) comp).isValid()) {
						((org.zkoss.zul.impl.InputElement) comp).focus();
					}
					return ((org.zkoss.zul.impl.InputElement) comp).isValid();
				}
			} else if (comp instanceof org.zkoss.zul.impl.XulElement) {
				for (Object obj : ((org.zkoss.zul.impl.XulElement) comp).getChildren()) {
					if (!validateForm(obj)) {
						return false;
					}
				}
				;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public static void applyAttributesForDisabled(InputElement comp, boolean disabled) {
		comp.setDisabled(disabled);
		if (disabled) {
			comp.clearErrorMessage();
			comp.setStyle("background: gray;color: black;");
		} else {
			comp.setConstraint("no empty");
			comp.setStyle(null);
		}
	}

	public static boolean isRowChecked(Row row) {
		boolean isAnyChecked = false;
		for (Object obj : row.getChildren()) {
			if (obj instanceof Checkbox) {
				isAnyChecked = !isAnyChecked ? ((Checkbox) obj).isChecked() : isAnyChecked;
			}
		}
		return false;
	}

	public static boolean isRowCheckedByAttibute(Row row, String attrName, String attrValue) {
		for (Object obj : row.getChildren()) {
			if (obj instanceof Checkbox) {
				Checkbox cb = (Checkbox) obj;
				if (cb.getAttribute(attrName).equals(attrValue)) {
					return cb.isChecked();
				}
			}
		}
		return false;
	}
}
