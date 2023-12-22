package com.is.utils.refobj;

import org.zkoss.zul.Comboitem;
import org.zkoss.zul.ComboitemRenderer;

public class RefObjRenderer implements ComboitemRenderer{

        @Override
        public void render(Comboitem item, Object data, int rowid) throws Exception {
                // TODO Auto-generated method stub
                item.setLabel(((RefObjData)data).getLabel());
                item.setValue(((RefObjData)data).getData());
                item.setAttribute("obj", ((RefObjData)data).getObject());
        }

}