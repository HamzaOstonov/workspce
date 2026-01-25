package com.is.customer_.attachments;

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

import com.is.customer_.core.model.Customer;
import com.is.customer_.core.utils.CustomerUtils;
import com.is.customer_.core.utils.ReferenceDecoder;
import com.is.customer_.sap.EmergencyMode;
import com.is.customer_.sap.service.AttachmentInterface;
import com.is.ISLogger;
/**
 * Created by root on 11.05.2017. 15:21
 */
public class AttachmentRenderer implements ListitemRenderer {
	// private AttachmentInterface service =
	// SAPServiceFactory.getInstance().getAttachmentService();
	private String schema;
	private Customer customer;
	//private ClientJ clientJ;
	private Window c_window;

	private AttachmentInterface attachmentService;

	public void setAttachmentService(AttachmentInterface attachmentService) {
		this.attachmentService = attachmentService;
	}

	private LocalAttachmentService localAttachmentService;

	public void setLocalAttachmentService(LocalAttachmentService localAttachmentService) {
		this.localAttachmentService = localAttachmentService;
	}

	public AttachmentRenderer(String schema) {
		this.schema = schema;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
		//this.clientJ = null;
	}

	//public void setClientJ(ClientJ clientJ) {
		//this.clientJ = clientJ;
//		this.customer = null;
//	}

	public void setC_window(Window c_window) {
		this.c_window = c_window;
	}

	@Override
	public void render(Listitem item, Object data) throws Exception {
		Attachment attachment = (Attachment) data;
		item.setValue(attachment);
		item.appendChild(createCell(attachment));
		// if(attachment.getUrl().contains("URL"))
		if (attachment.getUrl() != null && attachment.getUrl().contains("URL"))
			item.appendChild(new Listcell("URL"));
		else
			item.appendChild(new Listcell(ReferenceDecoder.getApplicationName(attachment.getDoc_type())));
		item.appendChild(new Listcell(attachment.getDescription()));
		item.appendChild(new Listcell(CustomerUtils.dateToString(attachment.getDoc_date())));
		// item.appendChild(new
		// Listcell(CustomerUtils.dateToStringWithMinutes(attachment.getCreatedAt())));
		item.appendChild(new Listcell(CustomerUtils.dateToStringWithMinutes(attachment.getCreatedAt())));

		item.appendChild(createDeleteBtnCell(attachment));
	}

	private Component createCell(final Attachment attachment) {
		Listcell listcell = new Listcell();
		listcell.setStyle("text-decoration:underline");
		A a = new A(attachment.getFileName());
		a.setStyle("color:blue");
		a.addEventListener(Events.ON_CLICK, new EventListener() {
			@Override
			public void onEvent(Event event) throws Exception {
				// Attachment content =
				// service.getAttachmentContent(attachment.getUrl());
				ISLogger.getLogger().error("AttachmentRenderer 01. "+attachment.getUrl());
				Execution exec = Executions.getCurrent();
				ISLogger.getLogger().error("AttachmentRenderer 02. "+exec.getContextPath());
				ISLogger.getLogger().error("AttachmentRenderer 03. "+exec.getLocalAddr());
				// nayti put, tipa bf_ipak ili bf , bf_agro
				String doroga;
				try {
					doroga = exec.getContextPath();
					doroga = "/" + doroga.substring(doroga.indexOf("/") + 1);
				} catch (Exception e) {
					doroga = "/bf";
					ISLogger.getLogger().error("AttachmentRenderer 030. "+e.getMessage());
					ISLogger.getLogger().error("AttachmentRenderer 031. "+e.getCause());
				} finally {
				}
				String localAdr = exec.getLocalAddr();
				if (localAdr.equals("0:0:0:0:0:0:0:1"))
					localAdr = "localhost";
				// --------
				String request = /*"http://" +*/
				// exec.getLocalAddr() + ":" +
				/*localAdr + ":" +*/
				// exec.getLocalPort() + "/bf/attachmentServlet?documentId=" +
				// attachment.getUrl()
				/*exec.getLocalPort() + doroga +*/ "/attachmentServlet?documentId=" + attachment.getUrl() + "&schema="
						+ schema + "&clientId=" +  customer.getId()
						+ "&branch=" +  customer.getBranch();
				ISLogger.getLogger().error("AttachmentRenderer 04. "+request);
				Executions.getCurrent().sendRedirect(request, "_blank");
				ISLogger.getLogger().error("AttachmentRenderer 05. "+request);
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

										if (!EmergencyMode.isTrue)
											attachmentService.deleteAttachment(attachment.getUrl());

										try {
											int id_apps = Integer.valueOf(attachment.getUrl());
											localAttachmentService.deleteAttachment(customer.getBranch(), customer.getId(), id_apps);
										} catch (NumberFormatException e) {
										}

										AttachmentComposer atttachmentController = (AttachmentComposer) c_window
												.getAttribute("attachmentWnd$composer");
										atttachmentController.refresh(customer);

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

	/*public void deleteFile(final Attachment current) throws RemoteException {
		try {
			Messagebox.show("Удалить файл?", "", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION,
					new EventListener() {
						@Override
						public void onEvent(Event event) throws Exception {
							if (event.getName().equals(Messagebox.ON_OK)) {

								if (!EmergencyMode.isTrue)
									attachmentService.deleteAttachment(current.getUrl());

								try {
									int id_apps = Integer.valueOf(current.getUrl());
									localAttachmentService.deleteAttachment(id_apps, customer.getBranch(), customer.getId());
								} catch (NumberFormatException e) {

								}
							}
						}
					});
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}*/

}
