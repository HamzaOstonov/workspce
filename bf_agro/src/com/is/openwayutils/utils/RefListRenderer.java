package com.is.openwayutils.utils;

import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

import com.is.utils.RefData;

public class RefListRenderer implements ListitemRenderer{

        @Override
        public void render(Listitem item, Object data) throws Exception {
  
                item.setLabel(((RefData)data).getLabel());
                item.setValue(((RefData)data).getData());

                item.setDraggable("true");
                item.setDroppable("true");
                //item.o


        }

}