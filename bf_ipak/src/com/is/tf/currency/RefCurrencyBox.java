package com.is.tf.currency;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Combobox;


@SuppressWarnings("serial")
public class RefCurrencyBox extends Combobox{
    
	public void setSelecteditem(String data){
		setSelectedIndex(-1);
		// System.out.println("Selected data "+data);
		if(data!=null){
			for(int i=0;i<this.getItemCount();i++){
				if(getItemAtIndex(i).getValue().equals(data)){
					setSelectedIndex(i);
					break;
				}
			}
		}	
	}
        
	public RefCurrencyBox(){
		super();
		this.setItemRenderer(new RefCurrencyRenderer());
	}
        
/*        
        public void onChange(Event evt){
        	System.out.println("change value "+getId()+" "+_value);
        }
        
*/
	public String getValue(){
		String res ="";
		if(_value!=null){
			for(int i=0;i<this.getItemCount();i++){
				if(getItemAtIndex(i).getLabel().equals(_value)){
					res = (String) getItemAtIndex(i).getValue();
					//_value=res;
					break;
				}
			}
		}
		//System.out.println("value "+_value +" res "+res);
		// System.out.println(  "Selected value for "+getId()+" "+_value);
		return res;
	}
	
	public Double getCourse(){
		Double res = null;
		if(_value!=null){
			for(int i=0;i<this.getItemCount();i++){
				if(getItemAtIndex(i).getLabel().equals(_value)){
					res = (Double) getItemAtIndex(i).getAttribute("course");
					//_value=res;
					break;
				}
			}
		}
		//System.out.println("value "+_value +" res "+res);
		// System.out.println(  "Selected value for "+getId()+" "+_value);
		return res;
	}

	/*
        public void setValue(String data){
            setSelectedIndex(-1);
            //System.out.println("Selected data "+data);
            if(data!=null){
            for(int i=0;i<this.getItemCount();i++){
                    if(getItemAtIndex(i).getValue().equals(data)){
                            setSelectedIndex(i);
                            break;
                    }
            }
            }

    }
*/
}