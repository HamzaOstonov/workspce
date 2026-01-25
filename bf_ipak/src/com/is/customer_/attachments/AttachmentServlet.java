package com.is.customer_.attachments;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.is.ISLogger;
import com.is.base.utils.FileUtil;
import com.is.customer_.sap.EmergencyMode;
import com.is.customer_.sap.service.SAPServiceFactory;
import com.is.utils.CheckNull;

/**
 * Created by root on 20.07.2017. 16:15
 */
public class AttachmentServlet extends HttpServlet {
	// private LocalAttachmentService localAttachmentService - Неправильно
	// private String alias - Неправильно
	// private SessionAttributes sessionAttributes - Неправильно
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			
			
			String documentId = (String) request.getParameter("documentId");
			String schema = (String) request.getParameter("schema");
			String customerId = (String) request.getParameter("clientId");
			String branch = (String) request.getParameter("branch");
			//String id_apps = (String) request.getParameter("id_apps");
			Attachment attachment = null;
			if (!EmergencyMode.isTrue) {
				attachment = SAPServiceFactory.getInstance().getAttachmentService().getAttachmentContent(documentId);
				ISLogger.getLogger().error("AttachmentServlet 01 :" + documentId+".");
			}
			else{
				LocalAttachmentService localAttachmentService = LocalAttachmentService.getInstance(schema);
				attachment = localAttachmentService.getAttachment(branch, customerId, documentId);
				ISLogger.getLogger().error("AttachmentServlet 02 :" + documentId+".");
			}
			if (attachment == null)
				throw new RuntimeException("Attachment is null ");
			if (attachment.getFileName() == null)
				throw new RuntimeException("File Name is Null");
			if (attachment.getFileName().contains(".URL")) {
				String address = new String(attachment.getData(), "UTF-8");
				ISLogger.getLogger().error("AttachmentServlet URL " + attachment.getData().toString()+".");
				ISLogger.getLogger().error("AttachmentServlet address " + address+".");
				System.out.println(attachment.getData().toString());
				System.out.println(new String(attachment.getData(), "UTF-8"));
				response.sendRedirect(address);
				ISLogger.getLogger().error("AttachmentServlet 03 ");
				return;
			}

			OutputStream out = response.getOutputStream();
			ISLogger.getLogger().error("AttachmentServlet 04 ");
			String fileName = attachment.getFileName();
			ISLogger.getLogger().error("AttachmentServlet fileName= "+fileName+".");
			String ext = fileName != null ? fileName.substring(fileName.lastIndexOf(".") + 1) : "";
			String mimeType = FileUtil.getMimeType(ext);

			response.setContentType(mimeType);
			ISLogger.getLogger().error("AttachmentServlet 05 ");
			response.setHeader("Content-Disposition",
					"inline;filename=\"" + URLEncoder.encode(attachment.getFileName(), "UTF-8") + "\"");

			out.write(attachment.getData());
			out.flush();
			out.close();
			ISLogger.getLogger().error("AttachmentServlet 06 ");
		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			ISLogger.getLogger().error("AttachmentServlet 07 err");
		}
	}
}
