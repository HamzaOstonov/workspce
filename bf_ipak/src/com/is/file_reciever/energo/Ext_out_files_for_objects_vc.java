package com.is.file_reciever.energo;

import java.text.SimpleDateFormat;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;

import com.is.file_reciever.File_reciever_vc;
import com.is.file_reciever.simple.Ext_out_file;

public class Ext_out_files_for_objects_vc extends GenericForwardComposer
{
	private Listbox dataGrid;
	private Long current_object_id;
	private static SimpleDateFormat sdf_ui = new SimpleDateFormat("dd.MM.yyyy");
	
	public void doAfterCompose(Component comp) throws Exception
	{
        super.doAfterCompose(comp);
        String [] current_fr_id = (String[])param.get("object_id");
        current_object_id = Long.parseLong(current_fr_id[0]);
        fill_files_grid();
	}
	
	private void fill_files_grid()
	{
		List<Ext_out_file> current_object_files = 
			Ext_out_files_for_objects_service.get_out_files(current_object_id);
		
		for(Ext_out_file current_ext_out_file : current_object_files)
		{
			Listitem cur_li = new Listitem();
			Listcell lc = new Listcell(
					current_ext_out_file.getFile_name()
					);
			cur_li.appendChild(lc);
			lc = new Listcell(
					current_ext_out_file.getBranch()
					);
			cur_li.appendChild(lc);
			lc = new Listcell(
					sdf_ui.format(current_ext_out_file.getV_date())
					);
			cur_li.appendChild(lc);
			
			Button btn_show_out_file = new Button();
			btn_show_out_file.setLabel("Показать в архиве");
			btn_show_out_file.setAttribute("object_id", current_ext_out_file.getFr_id());
			btn_show_out_file.addEventListener(Events.ON_CLICK, new EventListener()
	            	{
						@Override
						public void onEvent(Event event)
								throws Exception 
						{
							long object_id = (Long)event.getTarget().getAttribute("object_id");
							File_reciever_vc current_iframe = (File_reciever_vc)session.getAttribute("current_iframe");
							current_iframe.SetShow_details_wnd$file_details_frameSrc(
									"energo_sent_ui.zul"+
									"?fr_id="+Long.toString(object_id));
						}
	            	});
			lc = new Listcell();
			lc.appendChild(btn_show_out_file);
			cur_li.appendChild(lc);
			
			dataGrid.appendChild(cur_li);
		}
	}
}
