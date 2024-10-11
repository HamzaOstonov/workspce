package com.is.tieto_client;

import java.sql.SQLException;
import java.util.Vector;

import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Toolbarbutton;

public class ti_view_ctrl extends GenericForwardComposer{
	
	
	private Toolbarbutton KNOPKA;
	
	
	 public void onClick$btn_print_arch_inquiry() {
		 try {
			 Vector<Tieto_client> tcl=Service.getClient();
			 for (int i=0 ;i<tcl.size();i++){
			    System.out.println(tcl.get(i).getCLIENT());
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       }


	public void setKNOPKA(Toolbarbutton kNOPKA) {
		KNOPKA = kNOPKA;
	}


	public Toolbarbutton getKNOPKA() {
		return KNOPKA;
	}
	
	

}
