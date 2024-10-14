package com.is.bpri.utils;

import org.zkoss.zul.Comboitem;
import org.zkoss.zul.ComboitemRenderer;

public class MyComboRender implements ComboitemRenderer{

	@Override
	public void render(Comboitem item, Object data) throws Exception {
		if(data instanceof MyComboModel){
			item.setLabel(((MyComboModel)data).getLabel());
	        item.setValue(((MyComboModel)data).getValue());
	        item.setAttribute("model",((MyComboModel)data).getModel());
		} else { throw new Exception("Use only 'MyComboModel' model!") ;}
	}

}
