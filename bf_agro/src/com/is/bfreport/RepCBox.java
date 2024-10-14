package com.is.bfreport;

import org.zkoss.zul.Combobox;




@SuppressWarnings("serial")
public class RepCBox extends Combobox{
        public void setSelecteditem(String data){
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
        
        public RepCBox(){
                super();
                this.setItemRenderer(new RepRenderer());
                this.setReadonly(true);
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
                        //System.out.println("value "+_value +" res "+res);
                return res;
        }
        	
}