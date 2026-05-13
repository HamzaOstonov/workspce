package com.is.openwayutils.utils;

import org.zkoss.zul.Comboitem;
import org.zkoss.zul.ComboitemRenderer;

import com.is.utils.RefData;

public class RefRenderer implements ComboitemRenderer{

        @Override
        public void render(Comboitem item, Object data) throws Exception {
 
                item.setLabel(((RefData)data).getLabel());
                item.setValue(((RefData)data).getData());


        }

}