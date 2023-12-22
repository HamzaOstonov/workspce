package com.is.utils.refobj;

import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

public class RefObjListRenderer implements ListitemRenderer{

        @Override
        public void render(Listitem item, Object data, int rowid) throws Exception {
                // TODO Auto-generated method stub
                item.setLabel(((RefObjData)data).getLabel());
                item.setValue(((RefObjData)data).getData());
                item.setAttribute("obj", ((RefObjData)data).getObject());

                item.setDraggable("true");
                item.setDroppable("true");
        }

}