package com.is.customer_.attachments;

import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;

import com.is.customer_.core.ReferenceDictionary;
import com.is.customer_.core.model.Customer;
import com.is.customer_.core.utils.CustomerUtils;
import com.is.customer_.sap.EmergencyMode;
import com.is.customer_.sap.service.AttachmentInterface;
import com.is.customer_.sap.service.BusinessPartnerInterface;
import com.is.customer_.sap.service.SAPServiceFactory;
import com.is.utils.RefCBox;

/**
 * Created by root on 18.07.2017. 11:04
 */
public class AttachmentComposer extends GenericForwardComposer {
	private static Logger logger = Logger.getLogger(AttachmentComposer.class);
	private AnnotateDataBinder binder;

	private Window attachmentWnd, urlWnd;
	private Listbox appsListbox;
	private RefCBox appsTypes;
	private Toolbarbutton btnUploadFile, btn_deleteFile, btn_deleteFile2;

	private AttachmentInterface attachmentService;
	private BusinessPartnerInterface customerService;
	private Customer selectedCustomer;
	private Attachment attachment = new Attachment();
	private Attachment current;

	private LocalAttachmentService localAttachmentService;

	private String schema;

	@Override
	public void doAfterCompose(Component component) throws Exception {
		super.doAfterCompose(component);
		binder = new AnnotateDataBinder(component);
		binder.bindBean("attachment", this.attachment);
		binder.bindBean("current", this.current);
		binder.loadAll();

		schema = (String) session.getAttribute("alias");
		appsTypes.setModel(new ListModelList(ReferenceDictionary.getAppsTypes(schema)));
		final AttachmentRenderer attachmentRenderer = new AttachmentRenderer(schema);
		appsListbox.setItemRenderer(attachmentRenderer);

		localAttachmentService = LocalAttachmentService.getInstance(schema);

		attachmentService = SAPServiceFactory.getInstance().getAttachmentService();
		customerService = SAPServiceFactory.getInstance().getBusinessPartnerService();
		self.addEventListener("onUploadApps", new EventListener() {
			@Override
			public void onEvent(Event event) throws Exception {
				Customer customer = (Customer) event.getData();
				selectedCustomer = customer;
				attachmentRenderer.setCustomer(customer);
				attachmentRenderer.setC_window(attachmentWnd);
				attachmentRenderer.setAttachmentService(attachmentService);
				attachmentRenderer.setLocalAttachmentService(localAttachmentService);
				refresh(customer);
			}
		});
	}

	//private void refresh(Customer customer) throws RemoteException {
	public void refresh(Customer customer) throws RemoteException {		
		// Attachment attachment = new Attachment();
		List<Attachment> attachmentList = new ArrayList<Attachment>();
		if (!EmergencyMode.isTrue) {
			attachmentList = attachmentService.getAttachments(customer.getIdSap());
		} else {
			attachmentList = localAttachmentService.getAttachments(customer.getBranch(), customer.getId());
		}
		appsListbox.setModel(new ListModelList(attachmentList));

		if (appsListbox.getModel().getSize() > 0) {
			// this.current = (Attachment)
			// appsListbox.getModel().getElementAt(0);
			sendSelEvt();
		} else
			current = null;
	}

	public void onClick$btn_getFile() throws RemoteException {
		refresh(selectedCustomer);
	}

	public void onClick$btn_deleteFile() throws RemoteException {
		if ((current == null) || (current.getUrl() == null)) {
			try {
				Messagebox.show("Не выбран файл", "Information", Messagebox.OK, Messagebox.INFORMATION);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return;
		}
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
									localAttachmentService.deleteAttachment(selectedCustomer.getBranch(), selectedCustomer.getId(), id_apps);
								} catch (NumberFormatException e) {

								}

								refresh(selectedCustomer);

							}
						}
					});
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void onClick$btn_deleteFile2() throws RemoteException {
		// attachmentService.deleteAttachment(current.getUrl());
		alert(current.getDoc_number() + "; " + current.getUrl() + "; " + current.getDescription() + "; "
				+ current.getFileName() + "; " + current.getCreatedAt() + "; " + current.getDoc_date() + "; ");
		// refresh(selectedCustomer);
	}

	public void onUpload$btnUploadFile(UploadEvent event) throws Exception {
		if (appsTypes.getValue() == null || appsTypes.getValue().trim().isEmpty()) {
			Messagebox.show("Выберите тип документа");
			return;
		} else {
			Media aMedia = event.getMedia();
			InputStream inputStream = aMedia.getStreamData();
			byte[] data = org.apache.commons.io.IOUtils.toByteArray(inputStream);
			// Attachment attachment = new Attachment();
			attachment.setData(data);
			attachment.setFileName(aMedia.getName());
			attachment.setDoc_type(appsTypes.getValue());

			// if (aMedia.getName() == null ||
			// FilenameUtils.removeExtension(aMedia.getName()).length() >= 18) {
			// Messagebox.show("Длина имени файла должна содержать не более 18
			// сиволов");
			// return;
			// }

			if (data == null) {
				return;
			}

			//logger.error("not err zagruzka " + attachment.getFileName() + " - " + CustomerUtils.toCalendar(new Date()));

			if (!EmergencyMode.isTrue) {
				//logger.error("EmergencyMode off 2 ");
				Customer clone = customerService.get(selectedCustomer.getBranch(), selectedCustomer.getId(), null);
				attachmentService.sendAttachment(clone, attachment);
			}

			localAttachmentService.saveAttachment(attachment, selectedCustomer);
			attachment = new Attachment(); //очистить

			refresh(selectedCustomer);
			binder.loadAll();
		}
	}

	private void sendSelEvt() {
		SelectEvent evt = new SelectEvent(Events.ON_SELECT, appsListbox, appsListbox.getSelectedItems());
		Events.sendEvent(evt);
	}

	public void onChange$appsTypes(InputEvent event) {
		RefCBox rcombobox = (RefCBox) event.getTarget();
		if (event.getValue() == null || event.getValue().isEmpty())
			btnUploadFile.setDisabled(true);
		else
			btnUploadFile.setDisabled(false);
	}

	public void onClick$btn_URL() throws InterruptedException {
		if (!urlWnd.isVisible()) {
			Clients.evalJavaScript(String.format("$('#%s').fadeToggle('slow')", urlWnd.getUuid()));
			urlWnd.setVisible(true);
		} else {
			Clients.evalJavaScript(String.format("$('#%s').fadeToggle('slow')", urlWnd.getUuid()));
			urlWnd.setVisible(false);
		}
	}

	public void onClick$btnAdd$urlWnd() throws Exception {
		// Только для загрузки ссылок
		attachment.convertURLToBytes();
		attachment.setFileName(attachment.getDescription());
		// attachment.setData(attachment.getUrl().getBytes("UTF-8"));
		attachment.isURLValid();

		if (!EmergencyMode.isTrue) {
			Customer clone = customerService.get(selectedCustomer.getBranch(), selectedCustomer.getId(), null);

			attachmentService.sendAttachment(clone, attachment);
		}
		localAttachmentService.saveAttachment(attachment, selectedCustomer);

		refresh(selectedCustomer);
		binder.loadAll();
		urlWnd.setVisible(false);
	}

	public Attachment getAttachment() {
		return attachment;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	public Attachment getCurrent() {
		return current;
	}

	public void setCurrent(Attachment current) {
		this.current = current;
	}

}
