package com.is.file_reciever.ui_classes;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Label;

public class CB_approval_details_3 extends GenericForwardComposer
{
	private Label parsed_file;
	
	private long current_file_fr_id;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception
	{
	        super.doAfterCompose(comp);
	        String [] current_fr_id = (String[])param.get("fr_id");
	        current_file_fr_id = Long.parseLong(current_fr_id[0]);
	        
	        String details = Service.get_file_records_details(current_file_fr_id);
	        parsed_file.setValue(details);
	}
}
