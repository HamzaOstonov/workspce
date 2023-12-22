package com.is.utils;

import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

public class RefListRenderer implements ListitemRenderer{

        @Override
        public void render(Listitem item, Object data, int rowid) throws Exception {
                // TODO Auto-generated method stub
                item.setLabel(((RefData)data).getLabel());
                item.setValue(((RefData)data).getData());

                item.setDraggable("true");
                item.setDroppable("true");
                //item.o


        }

}