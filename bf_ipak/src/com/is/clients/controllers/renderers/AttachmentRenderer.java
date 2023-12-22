package com.is.clients.controllers.renderers;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.A;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;

import com.is.client_sap.abstraction.AbstractSapOrganizationService;
import com.is.clients.controllers.ClientJViewCtrl;
import com.is.clients.models.ClientJ;
import com.is.customer_.attachments.Attachment;
import com.is.customer_.attachments.AttachmentComposer;
import com.is.customer_.core.utils.CustomerUtils;
import com.is.customer_.core.utils.ReferenceDecoder;
import com.is.customer_.sap.EmergencyMode;

public class AttachmentRenderer implements ListitemRenderer {
    private static Logger logger = Logger.getLogger(AttachmentComposer.class);

	private HashMap<String,String> attachMap; 
	//private ClientJ clientJ;
	private String schema;
	//private AttachmentInterface attachmentService;
	private Window c_window;

	private AbstractSapOrganizationService<ClientJ> sapOrganizationService;
	private AbstractSapOrganizationService<ClientJ> localOrganizationService;
	

	//public void setAttachmentService(AttachmentInterface attachmentService) {
//		this.attachmentService = attachmentService;
//	}

	//private LocalAttachmentService localAttachmentService;

	//public void setLocalAttachmentService(LocalAttachmentService localAttachmentService) {
	//	this.localAttachmentService = localAttachmentService;
	//}
	
	public AttachmentRenderer(HashMap<String,String> attachMap) {
		this.attachMap = attachMap;
	}

	public AttachmentRenderer(String schema){
		this.schema = schema;
	}

	public AttachmentRenderer(String schema, Window c_window, AbstractSapOrganizationService<ClientJ> sapOrganizationService, AbstractSapOrganizationService<ClientJ> localOrganizationService){
		this.schema = schema;
		this.c_window = c_window;
		this.sapOrganizationService = sapOrganizationService;
		this.localOrganizationService = localOrganizationService;
	}


	//public void setClientJ(ClientJ clientJ){
	//	this.clientJ = clientJ;
	//}

	@Override
	/*public void render(Listitem item, Object data) throws Exception {
		final BPAttachmentsAttachment attachment = (BPAttachmentsAttachment) data;

		Listcell fileName = new Listcell();
		A a = new A(attachment.getName());
		
		a.addEventListener("onClick", new EventListener() {
			@Override
			public void onEvent(Event event) throws Exception {
				String docId = attachment.getURL().toString();
				try {
					ContentServerResponce data = SapFactory.instance().getContentService().getFile(docId);
    				Filedownload.save(data.getData(),null,data.getDoc_name());
				} catch (Exception e) {
					ISLogger.getLogger().error(CheckNull.getPstr(e));
				}
			}
		});
		a.setParent(fileName);
		item.appendChild(new Listcell(attachment.getType()));
		item.appendChild(new Listcell(attachMap.get(attachment.getType())));
		item.appendChild(new Listcell(Util.formatDate(attachment.getCreated_at().getTime())));
		item.appendChild(new Listcell(attachment.getDoc_number()));
		
	};*/
	
    public void render(Listitem item, Object data) throws Exception {
        Attachment attachment = (Attachment) data;
        item.setValue(attachment);
        item.appendChild(createCell(attachment));
        //if(attachment.getUrl().contains("URL"))
        if( attachment.getUrl()!=null && attachment.getUrl().contains("URL"))
            item.appendChild(new Listcell("URL"));
        else
            item.appendChild(new Listcell(ReferenceDecoder.getApplicationName(attachment.getDoc_type())));
        item.appendChild(new Listcell(attachment.getDescription()));
        item.appendChild(new Listcell(CustomerUtils.dateToString(attachment.getDoc_date())));
        item.appendChild(new Listcell(CustomerUtils.dateToStringWithMinutes(attachment.getCreatedAt())));
		item.appendChild(createDeleteBtnCell(attachment));
		item.setValue(attachment);
    }

    private Component createCell(final Attachment attachment) {
        Listcell listcell = new Listcell();
        listcell.setStyle("text-decoration:underline");
        A a = new A(attachment.getFileName());
        a.setStyle("color:blue");
        a.addEventListener(Events.ON_CLICK, new EventListener() {
            @Override
            public void onEvent(Event event) throws Exception {
                //Attachment content = service.getAttachmentContent(attachment.getUrl());
                Execution exec = Executions.getCurrent();

                //nayti put, tipa bf_ipak ili bf , bf_agro
                String doroga;
                try {
                  doroga = exec.getContextPath();
                  doroga="/"+doroga.substring(doroga.indexOf("/")+1);
                }
                catch (Exception e) {
                	doroga="/bf";
                }
                finally
                {
                }
                String localAdr=exec.getLocalAddr();
                if (localAdr.equals("0:0:0:0:0:0:0:1")) 
                  localAdr="localhost";
                //-------- 
                String request = "http://" +
                        //exec.getLocalAddr() + ":" +
                        localAdr + ":" +
                        //exec.getLocalPort() + "/bf/attachmentServlet?documentId=" + attachment.getUrl() 
                        exec.getLocalPort() + doroga+ "/attachmentServlet?documentId=" + attachment.getUrl()
                        + "&schema=" + schema + "&clientId=" + attachment.getId_client() + "&branch=" +  attachment.getBranch() ;
                    	
                logger.error("nci attach 2");
				
                Executions.getCurrent().sendRedirect(request, "_blank");
            }
        });
        a.setParent(listcell);
        return listcell;
    }

	private Component createDeleteBtnCell(final Attachment attachment) {
		Listcell listcell = new Listcell();
		Toolbarbutton tb = new Toolbarbutton();
		tb.setImage("/images/deletered3.png");
		tb.setTooltiptext("Удалить");
		tb.addEventListener(Events.ON_CLICK, new EventListener() {
			@Override
			public void onEvent(Event event) throws Exception {

				// deleteFile(attachment);
				try {
					Messagebox.show("Удалить файл?", "", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION,
							new EventListener() {
								@Override
								public void onEvent(Event event) throws Exception {
									if (event.getName().equals(Messagebox.ON_OK)) {

										ClientJ clientJ = new ClientJ();
										clientJ.setBranch(attachment.getBranch());
										clientJ.setId_client(attachment.getId_client());

										if (!EmergencyMode.isTrue)
											sapOrganizationService.deleteAttachment(clientJ, attachment.getUrl());

										try {
											int id_apps = Integer.valueOf(attachment.getUrl());
											localOrganizationService.deleteAttachment(clientJ, ""+id_apps);
										} catch (NumberFormatException e) {
										}

										ClientJViewCtrl clientController = (ClientJViewCtrl) c_window
												.getAttribute("clientmain$composer");
										clientController.refreshAtachmentList();
										
									}
								}
							});
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				// AttachmentComposer atttachmentController =
				// (AttachmentComposer) c_window
				// .getAttribute("attachmentWnd$composer");
				// atttachmentController.refresh(customer);

			}
		});
		listcell.appendChild(tb);
		return listcell;
	}

	public AbstractSapOrganizationService<ClientJ> getSapOrganizationService() {
		return sapOrganizationService;
	}

	public void setSapOrganizationService(AbstractSapOrganizationService<ClientJ> sapOrganizationService) {
		this.sapOrganizationService = sapOrganizationService;
	}

	public AbstractSapOrganizationService<ClientJ> getLocalOrganizationService() {
		return localOrganizationService;
	}

	public void setLocalOrganizationService(AbstractSapOrganizationService<ClientJ> localOrganizationService) {
		this.localOrganizationService = localOrganizationService;
	}

}
