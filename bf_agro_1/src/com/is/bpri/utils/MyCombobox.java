package com.is.bpri.utils;

import org.zkoss.zul.Combobox;

public class MyCombobox extends Combobox {

	private static final long serialVersionUID = 1L;
	
	public MyCombobox() {
		super();
		this.setItemRenderer(new MyComboRender());
	}
	
	public MyCombobox(MyCombobox myCombobox) {

	}

	public void setSelecteditem(String data){
        setSelectedIndex(-1);
        if(data!=null){
        	for(int i=0;i<this.getItemCount();i++){
                if(getItemAtIndex(i).getValue().equals(data)){
                	setSelectedIndex(i);
                	break;
                }
        	}
        }
	}
	
	public void setSelectedItemFromModel(String data,String model){
		setSelectedIndex(-1);
        if(data!=null&&model!=null){
        	for(int i=0;i<this.getItemCount();i++){
        		if(getItemAtIndex(i).getAttribute("model").equals(model)){
        			if(getItemAtIndex(i).getValue().equals(data)){
                    	setSelectedIndex(i);
                    	break;
                    }
        		}
            }
        }
	}
	
	public String getSelectedModel(){
		String model = null;
		if(getSelectedItem()!=null){
			model = getSelectedItem().getAttribute("model")+"";
		}
		return model;
	}
	
	public String getValue(){
		String res ="";
        if(_value!=null){
        	for(int i=0;i<this.getItemCount();i++){
        		if(getItemAtIndex(i).getLabel().equals(_value)){
        			res = (String) getItemAtIndex(i).getValue();
        			break;
        		}
        	}
        }
        return res;
    }

}
