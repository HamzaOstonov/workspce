package com.is.bfreport;

import org.zkoss.zul.Comboitem;
import org.zkoss.zul.ComboitemRenderer;



public class RepRenderer implements ComboitemRenderer{

        @Override
        public void render(Comboitem item, Object data) throws Exception {
                // TODO Auto-generated method stub
                item.setLabel(((RepData)data).getLabel());
                item.setValue(((RepData)data).getData());
        }

}