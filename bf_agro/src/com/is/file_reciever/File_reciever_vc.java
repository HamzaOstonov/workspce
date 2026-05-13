package com.is.file_reciever;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.sql.Connection;
import java.text.SimpleDateFormat;

import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;

import com.is.ConnectionPool;
import com.is.file_reciever.simple.File_filter;
import com.is.file_reciever.simple.File_types;
import com.is.file_reciever.simple.Fr_file;
import com.is.file_reciever.simple.Fr_file_in;
import com.is.file_reciever.simple.Fr_file_out;
import com.is.file_reciever.simple.Simple_ui_class;
import com.is.file_reciever.tieto_transations.Onus_file_service;
import com.is.file_reciever_srv.exception.Reciever_exception;
import com.is.file_reciever_srv.recievers.tieto_file.Handler;
import com.is.file_reciever_srv.recievers.tieto_file.Reciever;
import com.is.file_reciever_srv.simple.stat.File_in;
import com.is.utils.RefCBox;

public class File_reciever_vc extends GenericForwardComposer
{
	private File_types file_types = new File_types();
	
	private RefCBox file_type;
	
	private Datebox filter_date_calendar;
	private Datebox filter_date_from_calendar;
	private Datebox filter_date_to_calendar;
	private Listbox dataGrid;
	private Paging dataPaging;
	private int current_data_page = 0;
	private int data_page_size = 20;
	private Div filter_div;
	private Window show_details_wnd, manual_wnd;
	//private Iframe show_details_wnd$file_details_frame;
	private Include show_details_wnd$file_details_frame;
	
	private AnnotateDataBinder binder;
	
	private String uploaded_file;
	
	private File_filter file_filter = new File_filter();
	private Fr_file current_fr_file;
	
	private SimpleDateFormat sdf_time = new SimpleDateFormat("dd.MM.yyyy HH:mm");
	protected String alias, ses_branch, un, pw;
	
	public Fr_file getCurrent_fr_file()
	{
		return current_fr_file;
	}

	public void setCurrent_fr_file(Fr_file current_fr_file)
	{
		this.current_fr_file = current_fr_file;
	}

	private void init_variables()
	{
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection();
			file_types.load(c);
		}
		catch (Exception e)
		{
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		}
		finally
		{
			try{if(c!=null) c.close();}catch(Exception e){}
		}
	}
	
	public void doAfterCompose(Component comp) throws Exception
	{
        super.doAfterCompose(comp);
        
        binder = new AnnotateDataBinder(comp);
        binder.bindBean("current_fr_file", this.current_fr_file);
        binder.loadAll();
        
	    ses_branch = (String) session.getAttribute("branch");
	    alias = (String) session.getAttribute("alias");
        
        init_variables();
        file_type.setModel(new ListModelList(file_types.get_ref_data()));
        
        dataGrid.setItemRenderer(new ListitemRenderer()
        {
            @SuppressWarnings("unchecked")
            public void render(Listitem row, Object data) throws Exception 
            {
            	Fr_file p_file  = (Fr_file) data;
            	
            	row.appendChild(new Listcell(p_file.getId()+""));
            	
            	row.setValue(p_file);
            	if (p_file.getClass().getName().contains("Fr_file_in"))
            	{
            		Fr_file_in local_file = (Fr_file_in)p_file;
            		row.appendChild(new Listcell("Входящий"));
            		row.appendChild(new Listcell(
            				p_file.getName().lastIndexOf("\\")>0?
            						p_file.getName().substring(p_file.getName().lastIndexOf("\\")+1):
            							p_file.getName().substring(p_file.getName().lastIndexOf("/")+1)
            				));
            		row.appendChild(new Listcell(
	                		file_types.get_file_type(local_file.getId_file_type()).getComment()));
            		row.appendChild(new Listcell(sdf_time.format(p_file.getDate_time())));
	                row.appendChild(new Listcell(p_file.getName()));
	                
	                Simple_ui_class cur_file_ui = 
            			(Simple_ui_class)
            			Class.forName(File_service.get_file_ui_class(local_file.getId_file_type())).newInstance();
	                
	                row.appendChild(new Listcell(cur_file_ui.get_file_state(local_file.getId())));
            	}
            	else if (p_file.getClass().getName().contains("Fr_file_out"))
            	{
            		Fr_file_out local_file = (Fr_file_out)p_file;
            		row.appendChild(new Listcell("Исходящий"));
	                row.appendChild(new Listcell(
	                		p_file.getName().lastIndexOf("\\")>0?
	                				p_file.getName().substring(p_file.getName().lastIndexOf("\\")+1):
	                					p_file.getName().substring(p_file.getName().lastIndexOf("/")+1)
    						));
	                row.appendChild(new Listcell());
	                row.appendChild(new Listcell(sdf_time.format(p_file.getDate_time())));
	                row.appendChild(new Listcell(p_file.getName()));
	                
            	}
            	
            	row.setAttribute("current_fr_file", p_file);
            	row.addEventListener(Events.ON_DOUBLE_CLICK, new EventListener()
            	{
					@Override
					public void onEvent(Event event)
							throws Exception 
					{
						current_fr_file = (Fr_file)event.getTarget().getAttribute("current_fr_file");
						show_file_details();
					}
            	});
            }
        });
        update_model(0);
        filter_date_calendar.setValue(new java.util.Date());
        
        /*   filter_div.addEventListener("onMouseOver", new EventListener() {
            public void onEvent(Event e) {
                filter_div.setWidth("350px");
              }
           });

        filter_div.addEventListener("onMouseOut", new EventListener() {
             public void onEvent(Event e) {
               filter_div.setWidth("20px");
            }
        });*/
	}
	
	private void update_model(int current_data_page)
	{
		dataPaging.setPageSize(data_page_size);
		PagingListModel model = new PagingListModel(current_data_page, data_page_size, file_filter, null);
		dataPaging.setTotalSize(model.getTotalSize(file_filter, null));
		dataGrid.setModel((ListModel) model);
	}
	
	public void onPaging$dataPaging(ForwardEvent event)
	{
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		current_data_page = pe.getActivePage();
		update_model(current_data_page);
	}
	
	public void onClick$tb_search()
	{
		
		if ((filter_date_from_calendar.getValue() != null)&&(filter_date_to_calendar.getValue() == null))
			{ alert("Не задано значение Дата до"); return; }
		if ((filter_date_to_calendar.getValue() != null)&&(filter_date_from_calendar.getValue() == null))
			{ alert("Не задано значение Дата с"); return; }
		
		file_filter.setFile_date(filter_date_calendar.getValue());
		file_filter.setDate_from(filter_date_from_calendar.getValue());
		file_filter.setDate_to(filter_date_to_calendar.getValue());
		file_filter.setFile_type(
				file_type.getValue().length() > 1?
						Long.parseLong(file_type.getValue()):
						null
				);
		update_model(0);
	}
	
	public void onChange$filter_date_calendar()
	{
		filter_date_from_calendar.setValue(null);
		filter_date_to_calendar.setValue(null);
		onClick$tb_search();
	}
	
	public void onChange$filter_date_from_calendar()
	{
		filter_date_calendar.setValue(null);
	}
	
	public void onChange$filter_date_to_calendar()
	{
		filter_date_calendar.setValue(null);
	}
	
	public void onSelect$file_type()
	{
		onClick$tb_search();
	}
	
	public void show_file_details()
	{
		show_details_wnd.setTitle("Подробности о файле " +
				(current_fr_file.getName().lastIndexOf("\\")>0?
						current_fr_file.getName().substring(current_fr_file.getName().lastIndexOf("\\")+1):
							current_fr_file.getName().substring(current_fr_file.getName().lastIndexOf("/")+1))
				);
		
		if (current_fr_file.getClass().getName().contains("Fr_file_in"))
    	{
			try
			{
				Simple_ui_class cur_file_ui = 
					(Simple_ui_class)
					Class.forName(File_service.get_file_ui_class(((Fr_file_in)current_fr_file).getId_file_type())).newInstance();
			
				show_details_wnd$file_details_frame.setSrc(
						cur_file_ui.get_file_user_inerface()+
						"?fr_id="+Long.toString(current_fr_file.getId()));
				session.setAttribute("current_iframe", this);
						//show_details_wnd$file_details_frame);
				
				show_details_wnd.setVisible(true);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
    	}
		if (current_fr_file.getClass().getName().contains("Fr_file_out"))
    	{
			try
			{
				/*Simple_ui_class cur_file_ui = 
					(Simple_ui_class)
					Class.forName(File_service.get_file_ui_class(((Fr_file_in)current_fr_file).getId_file_type())).newInstance();
			
				show_details_wnd$file_details_frame.setSrc(
						cur_file_ui.get_file_user_inerface()+
						"?fr_id="+Long.toString(current_fr_file.getId()));
				*/
				show_details_wnd$file_details_frame.setSrc("energo_sent_ui.zul"+
						"?fr_id="+Long.toString(current_fr_file.getId()));
				show_details_wnd.setVisible(true);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
    	}
	}
	
	public void SetShow_details_wnd$file_details_frameSrc(String src)
	{
		show_details_wnd$file_details_frame.setSrc(src);
	}
	
	public void onClick$tb_manual()
	{
		manual_wnd.setVisible(true);
	}
	
	public void onUpload$iias_file$manual_wnd(UploadEvent event) throws Exception
	{
		//Media media = Fileupload.get(true);
		Media media = event.getMedia();
		//OutputStream outputStream = new FileOutputStream(new File("c:\\uploaded_files\\tieto\\iias\\"+media.getName()));
		OutputStream outputStream = new FileOutputStream(new File("C://test//TietoFiles//EXPT1//"+media.getName()));
		InputStream inputStream = media.getStreamData();
		byte[] buffer = new byte[1024];
		for (int count; (count = inputStream.read(buffer)) != -1;) {
			outputStream.write(buffer, 0, count);
		}
		outputStream.flush();
		outputStream.close();
		inputStream.close();
		
		
		//uploaded_file = "c:\\uploaded_files\\tieto\\iias\\"+media.getName();
		uploaded_file = "C://test//TietoFiles//EXPT1//"+media.getName();
		// 1-й параметр из fr_file_type.id
		// 2-й параметр из ss_ext_file_types.id 
		//recieve_file(13, 12l); //ipak  
		recieve_file(32, 12l); //агро 32- Тието
	}
	
	public void recieve_file(int file_type_id, long ext_file_type)
	{
		try
		{
			com.is.file_reciever_srv.recievers.tieto_file.Reciever reciever = new Reciever();
			File_in fin = com.is.file_reciever_srv.simple.stat.File_service.create_file_in(
					uploaded_file,
					file_type_id);
			com.is.ISLogger.getLogger().error("not err 11 ");
			com.is.LtLogger.getLogger().error("not err 12 ");
			reciever.Recieve_file(
					uploaded_file, 
					fin.getId(),
					ext_file_type,
					ses_branch);
			com.is.file_reciever_srv.recievers.tieto_file.Handler handler = new Handler();
			handler.run_handler(ext_file_type);
			manual_wnd.setVisible(false);
			update_model(0);
		}
		catch(Exception e)
		{
			com.is.LtLogger.getLogger().error("not err 1 "+com.is.utils.CheckNull.getPstr(e));
			com.is.ISLogger.getLogger().error("not err 2 "+com.is.utils.CheckNull.getPstr(e));
			alert(com.is.utils.CheckNull.getPstr(e));
			
		}
	}
	
	public void recieve_onus_file(int file_type_id, long ext_file_type)
	{
		try
		{
			com.is.file_reciever_srv.recievers.tieto_file.Reciever reciever = new Reciever();
			File_in fin = com.is.file_reciever_srv.simple.stat.File_service.create_file_in(
					uploaded_file,
					file_type_id);
			reciever.Recieve_file(
					uploaded_file, 
					fin.getId(),
					ext_file_type,
					ses_branch);
			Onus_file_service s = new Onus_file_service();
			s.run_handler();
			manual_wnd.setVisible(false);
			update_model(0);
		}
		catch(Exception e)
		{
			alert(com.is.utils.CheckNull.getPstr(e));
		}
	}
	
	public void onUpload$iias_sms_file$manual_wnd(UploadEvent event) throws Exception
	{
		//Media media = Fileupload.get(true);
		Media media = event.getMedia();
		//OutputStream outputStream = new FileOutputStream(new File("c:\\uploaded_files\\tieto\\iias\\"+media.getName()));
		OutputStream outputStream = new FileOutputStream(new File("c:\\test\\TietoFiles\\EXPT1\\"+media.getName()));
		InputStream inputStream = media.getStreamData();
		byte[] buffer = new byte[1024];
		for (int count; (count = inputStream.read(buffer)) != -1;) {
			outputStream.write(buffer, 0, count);
		}
		outputStream.flush();
		outputStream.close();
		inputStream.close();
		
		
		//uploaded_file = "c:\\uploaded_files\\tieto\\iias\\"+media.getName();
		uploaded_file = "c:\\test\\TietoFiles\\EXPT1\\"+media.getName();
		
		
		recieve_file(19, 13l);
	}
	
	public void onUpload$onus_file$manual_wnd(UploadEvent event) throws Exception
	{
		//Media media = Fileupload.get(true);
		Media media = event.getMedia();
		//OutputStream outputStream = new FileOutputStream(new File("c:\\uploaded_files\\tieto\\iias\\"+media.getName()));
		OutputStream outputStream = new FileOutputStream(new File("c:\\test\\TietoFiles\\EXPT1\\"+media.getName()));
		InputStream inputStream = media.getStreamData();
		byte[] buffer = new byte[1024];
		for (int count; (count = inputStream.read(buffer)) != -1;) {
			outputStream.write(buffer, 0, count);
		}
		outputStream.flush();
		outputStream.close();
		inputStream.close();
		
		
		//uploaded_file = "c:\\uploaded_files\\tieto\\iias\\"+media.getName();
		uploaded_file = "c:\\test\\TietoFiles\\EXPT1\\"+media.getName();
		
		//recieve_onus_file(22, 18l);
		recieve_file(22, 21l);
	}
	
	public void onUpload$trbeq_file$manual_wnd(UploadEvent event) throws Exception
	{
		//Media media = Fileupload.get(true);
		Media media = event.getMedia();
		//OutputStream outputStream = new FileOutputStream(new File("c:\\uploaded_files\\tieto\\iias\\"+media.getName()));
		OutputStream outputStream = new FileOutputStream(new File("c:\\test\\TietoFiles\\EXPT1\\"+media.getName()));
		InputStream inputStream = media.getStreamData();
		byte[] buffer = new byte[1024];
		for (int count; (count = inputStream.read(buffer)) != -1;) {
			outputStream.write(buffer, 0, count);
		}
		outputStream.flush();
		outputStream.close();
		inputStream.close();
		
		
		//uploaded_file = "c:\\uploaded_files\\tieto\\iias\\"+media.getName();
		uploaded_file = "c:\\test\\TietoFiles\\EXPT1\\"+media.getName();
		
		recieve_file(21, 15l);
	}
}
