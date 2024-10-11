package com.is.humo.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.impl.InputElement;

import com.is.utils.RefCBox;

public class ZkUtils {
        
	public static void clearForm(Object comp) {
		try{
			if (comp instanceof org.zkoss.zul.Textbox) {
				((org.zkoss.zul.Textbox)comp).setConstraint("");
				((org.zkoss.zul.Textbox)comp).setValue(null);
			} else if (comp instanceof org.zkoss.zul.Intbox) {
				((org.zkoss.zul.Intbox)comp).setConstraint("");
				((org.zkoss.zul.Intbox)comp).setValue(null);
			} else if (comp instanceof org.zkoss.zul.Longbox) {
				((org.zkoss.zul.Longbox)comp).setConstraint("");
				((org.zkoss.zul.Longbox)comp).setValue(null);
			} else if (comp instanceof org.zkoss.zul.Decimalbox) {
				((org.zkoss.zul.Decimalbox)comp).setConstraint("");
				((org.zkoss.zul.Decimalbox)comp).setValue(BigDecimal.ZERO);
				((org.zkoss.zul.Decimalbox)comp).setRawValue(null);
			} else if (comp instanceof org.zkoss.zul.Datebox) {
				((org.zkoss.zul.Datebox)comp).setConstraint("");
				((org.zkoss.zul.Datebox)comp).setText(null);
			} else if (comp instanceof com.is.utils.RefCBox) {
				((com.is.utils.RefCBox)comp).setConstraint("");
				((com.is.utils.RefCBox)comp).setValue(null);
			} else if (comp instanceof org.zkoss.zul.impl.XulElement) {
				for (Object obj : ((org.zkoss.zul.impl.XulElement)comp).getChildren()) {
					clearForm(obj);
				};
			}
		}catch(Exception e){
			e.printStackTrace(); 
		}
    }
	
	public static void changeForm(Object comp,InputElHandler handler) {
		try{
			if (comp instanceof org.zkoss.zul.impl.InputElement) {
				handler.makeChanges((org.zkoss.zul.impl.InputElement)comp);
			} else if (comp instanceof org.zkoss.zul.impl.XulElement) {
				for (Object obj : ((org.zkoss.zul.impl.XulElement)comp).getChildren()) {
					changeForm(obj,handler);
				};
			}
		}catch(Exception e){
			e.printStackTrace();
		}
    }
	
	public static void checkAllRows(Object comp, boolean checked) {
		try{
			if (comp instanceof Checkbox) {
				((Checkbox)comp).setChecked(checked);
			} else if (comp instanceof org.zkoss.zul.impl.XulElement) {
				for (Object obj : ((org.zkoss.zul.impl.XulElement)comp).getChildren()) {
					checkAllRows(obj,checked);
				};
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void checkAllRowsUnderSpecificColumn(String columnAttr, String attrValue, Object comp, boolean checked) {
		try{
			if (comp instanceof Checkbox) {
				String attr = (String)((Checkbox)comp).getAttribute(columnAttr);
				if(attr != null && attr.equals(attrValue)) {
					((Checkbox)comp).setChecked(checked);
				}
			} else if (comp instanceof org.zkoss.zul.impl.XulElement) {
				for (Object obj : ((org.zkoss.zul.impl.XulElement)comp).getChildren()) {
					checkAllRowsUnderSpecificColumn(columnAttr, attrValue, obj, checked);
				};
			}
		}catch(Exception e){
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
	
	public static void disableFormWithConstraint(Object obj,final boolean disabled) {
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
				if(element.getId().equalsIgnoreCase(filedName)) {
					element.setStyle("color: blue");
				}
			}
		});
	}
	
	public static boolean validateForm(Object comp) {
		try{
			if (comp instanceof org.zkoss.zul.impl.InputElement) {
				if(!((org.zkoss.zul.impl.InputElement)comp).isDisabled()){
					if(!((org.zkoss.zul.impl.InputElement)comp).isValid()) {
						((org.zkoss.zul.impl.InputElement)comp).focus();
					}
					return ((org.zkoss.zul.impl.InputElement)comp).isValid();
				}
			} else if (comp instanceof org.zkoss.zul.impl.XulElement) {
				for (Object obj : ((org.zkoss.zul.impl.XulElement)comp).getChildren()) {
					if(!validateForm(obj)) {
						return false;
					}
				};
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return true;
    }
	public static void applyAttributesForDisabled(InputElement comp, boolean disabled) {
		comp.setDisabled(disabled);
		if(disabled) {
			comp.clearErrorMessage();
			comp.setStyle("background: gray;color: black;");
		} else {
			comp.setConstraint("no empty");
			comp.setStyle(null);
		}
	}
	public static boolean isRowChecked(Row row) {
		boolean isAnyChecked = false;
		for(Object obj: row.getChildren()) {
			if(obj instanceof Checkbox) {
				isAnyChecked = !isAnyChecked ? ((Checkbox)obj).isChecked() : isAnyChecked;
			}
		}
		return false;
	}
	public static boolean isRowCheckedByAttibute(Row row, String attrName, String attrValue) {
		//boolean isAnyChecked = false;
		for(Object obj: row.getChildren()) {
			if(obj instanceof Checkbox){ 
				Checkbox cb = (Checkbox)obj;
				if (cb!=null && cb.getAttribute(attrName) != null &&
                        cb.getAttribute(attrName).equals(attrValue)) {
					return cb.isChecked();
				}	
			}
		}
		return false;
	}
	
	public static InputElement makeInputElement(Object value) {
		InputElement inputEl = null;
		if(value instanceof Date) {
			inputEl = new Datebox((Date)value);
		}
		/*else if (value instanceof BigDecimal){
		    if (value != null){
		        BigDecimal bigDecimal = new BigDecimal(String.valueOf(value));
		        bigDecimal.setScale(2,BigDecimal.ROUND_HALF_EVEN);
		        inputEl = new Textbox(bigDecimal.toPlainString());
            }
            else
                inputEl = new Textbox("");
        }*/
		else {
			inputEl =  new Textbox(value != null ? String.valueOf(value) : "");
			inputEl.setHflex("1");
		}
		return inputEl;
	}
	public static List<String> getCheckedRows(Rows rows, String fieldAttr) {
		List<String> list = new ArrayList<String>();
		for(Object row: rows.getChildren()) {
			if(ZkUtils.isRowChecked((Row)row)) {
				list.add((String)((Row)row).getAttribute(fieldAttr));
			}
		}
		return list;
	}
	
	public static List<String> getCheckedRowsByAttribute(Rows rows, String attr, String columnAttr, String fieldAttr) {
		List<String> list = new ArrayList<String>();
		for(Object row: rows.getChildren()) {
			if(ZkUtils.isRowCheckedByAttibute((Row)row, columnAttr, attr)) {
				list.add((String)((Row)row).getAttribute(fieldAttr));
			}
		}
		return list;
	}

	public static void sendOnInitToRCombobox(Component root){
        if (root.getChildren().size() == 0) {
            if (root instanceof RefCBox) {
                Events.sendEvent(root, new Event("onInitRender"));
            }
            return;
        }

        List<Component> list = root.getChildren();
        for (Component component : list) {
            sendOnInitToRCombobox(component);
        }
    }
}
