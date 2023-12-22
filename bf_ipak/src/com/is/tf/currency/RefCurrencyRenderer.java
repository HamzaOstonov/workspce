package com.is.tf.currency;

import org.zkoss.zul.Comboitem;
import org.zkoss.zul.ComboitemRenderer;

public class RefCurrencyRenderer implements ComboitemRenderer{

        @Override
        public void render(Comboitem item, Object data) throws Exception {
                // TODO Auto-generated method stub
                item.setLabel(((RefCurrencyData)data).getCurrency());
                item.setValue(((RefCurrencyData)data).getKod());
                item.setAttribute("course", (Double) ((RefCurrencyData)data).getCourse());

        }

}