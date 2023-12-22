package com.is.qr_online.transaction;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.zkoss.util.media.AMedia;
import org.zkoss.web.servlet.Servlets;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Div;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;

import com.cb.munis.Document;
import com.is.ISLogger;
import com.is.qr_online.merchant.Merchant;
import com.is.qr_online.merchant.MerchantService;
import com.is.qr_online.payee.Payee;
import com.is.qr_online.payee.PayeeService;
import com.is.qr_online.registr.QrGenerator;
import com.is.qr_online.registr.References;
import com.is.qr_online.send.Answer;
import com.is.qr_online.send.Body;
import com.is.qr_online.send.Header;
import com.is.qr_online.send.Request;
import com.is.qr_online.send.Send;
import com.is.userreport.RepTempl;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefDataService;
import com.is.userreport.RepTempl;
import com.is.qr_online.transaction.*;

import sun.misc.BASE64Decoder;

import com.is.qr_online.payee.*;

public class TransactionViewCtrl extends GenericForwardComposer {

	private static final long serialVersionUID = 3284715443688870396L;

	private Window transactionmain;
	private RefCBox fcurrency, ffee_type, fformat;
	private RefCBox currency, fee_type, format;
	private RefCBox acurrency, afee_type, aformat;

	private RefCBox adiapazon_supp, diapazon_supp;
	private RefCBox acategory_supp, category_supp;

	private Label alabel_fee_amount, alabel_fee_percent, alabel_amount;
	private Label label_fee_amount, label_fee_percent, label_amount;
	private Label merchant_id;

	private Div frm;
	private Listbox dataGrid;
	private Div grd;
	private Grid addgrd, frmgrd, fgrd;
	private Toolbarbutton btn_back;
	private Toolbarbutton btn_update;
	private Textbox amount, category, product_code, product_name, fee_amount, fee_percent;
	private Textbox aamount, acategory, aproduct_code, aproduct_name, afee_amount, afee_percent;
	private Textbox famount, fcategory, fproduct_code, fproduct_name, ffee_amount, ffee_percent;
	private Paging transactionPaging;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	
	
	// private static final String path
	// =getClass().getResource("/reports/text.txt").getFile(); //
	// "C:\\Intel\\qr.png";

	public TransactionFilter filter;

	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");

	private Transaction current = new Transaction();
	private String alias, branch;

	public TransactionViewCtrl() {
		super('$', false, false);
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		filter = new TransactionFilter();
		super.doAfterCompose(comp);
		// TODO Auto-generated method stub
		binder = new AnnotateDataBinder(comp);
		binder.bindBean("current", this.current);
		binder.loadAll();
		alias = (String) session.getAttribute("alias");
		branch = (String) session.getAttribute("branch");
		System.out.println("Merchant Id == " + merchant_id.getValue());
		filter.setMerchant_id(merchant_id.getValue());
		String[] parameter = (String[]) param.get("ht");
		if (parameter != null) {
			_pageSize = Integer.parseInt(parameter[0]) / 36;
			dataGrid.setRows(Integer.parseInt(parameter[0]) / 36);
		}

		dataGrid.setItemRenderer(new ListitemRenderer() {

			@Override
			public void render(Listitem row, Object data) throws Exception {
				// TODO Auto-generated method stub
				Transaction pTransaction = (Transaction) data;

				row.setValue(pTransaction);

				row.appendChild(new Listcell(pTransaction.getResult_message()));
				row.appendChild(new Listcell(References.ss_qr_cod_types(pTransaction.getCode_type())));
				row.appendChild(new Listcell(References.s_val(pTransaction.getCurrency())));
				row.appendChild(new Listcell(pTransaction.getAmount()));
				row.appendChild(new Listcell(pTransaction.getCategory()));
				row.appendChild(new Listcell(pTransaction.getProduct_code()));
				row.appendChild(new Listcell(pTransaction.getProduct_name()));
				row.appendChild(new Listcell(References.ss_qr_commission_fee(pTransaction.getFee_type())));
				row.appendChild(new Listcell(pTransaction.getFee_amount()));
				row.appendChild(new Listcell(pTransaction.getFee_percent()));

			}
		});

		settings();
		btn_back.setVisible(false);

		fcurrency.setModel(new ListModelList(RefDataService.getRefData(
				"select s.kod data, s.namev label from s_val s where not s.kod='070' and s.kod='860' order by s.namev", alias)));

		ffee_type.setModel(new ListModelList(
				RefDataService.getRefData("select s.id data, s.name label from ss_qr_commission_fee s", alias)));
		currency.setModel(new ListModelList(RefDataService.getRefData(
				"select s.kod data, s.namev label from s_val s where not s.kod='070' and s.kod='860' order by s.namev", alias)));

		fee_type.setModel(new ListModelList(
				RefDataService.getRefData("select s.id data, s.name label from ss_qr_commission_fee s", alias)));

		acurrency.setModel(new ListModelList(RefDataService.getRefData(
				"select s.kod data, s.namev label from s_val s where not s.kod='070' and s.kod='860' order by s.namev", alias)));

		afee_type.setModel(new ListModelList(
				RefDataService.getRefData("select s.id data, s.name label from ss_qr_commission_fee s", alias)));

		aformat.setModel(new ListModelList(RefDataService
				.getRefData("select s.code_type data, s.id ||'.'|| s.name label from ss_qr_cod_types s", alias)));
		fformat.setModel(new ListModelList(RefDataService
				.getRefData("select s.code_type data, s.id ||'.'|| s.name label from ss_qr_cod_types s", alias)));
		format.setModel(new ListModelList(RefDataService
				.getRefData("select s.code_type data, s.id ||'.'|| s.name label from ss_qr_cod_types s", alias)));

		adiapazon_supp.setModel(new ListModelList(
				RefDataService.getRefData("select s.id data, s.name label from ss_qr_diapazon_suppliers s", alias)));

		diapazon_supp.setModel(new ListModelList(
				RefDataService.getRefData("select s.id data, s.name label from ss_qr_diapazon_suppliers s", alias)));

		refreshModel(_startPageNumber);
	}

	public void onPaging$transactionPaging(ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}

	private void refreshModel(int activePage) {
		transactionPaging.setPageSize(_pageSize);
		ISLogger.getLogger().error("alias_paging: "+alias);
		model = new PagingListModel(activePage, _pageSize, filter, alias);

		if (_needsTotalSizeUpdate) {
			_totalSize = model.getTotalSize(filter,alias);
			_needsTotalSizeUpdate = false;
		}

		transactionPaging.setTotalSize(_totalSize);

		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0) {
			this.current = (Transaction) model.getElementAt(0);
			sendSelEvt();
		}
	}

	// Omitted...
	public Transaction getCurrent() {
		return current;
	}

	public void setCurrent(Transaction current) {
		this.current = current;
	}

	public void onClick$btn_update() {
		if (dataGrid.getSelectedItem() != null) {
			grd.setVisible(false);
			frm.setVisible(true);
			frmgrd.setVisible(true);
			addgrd.setVisible(false);
			fgrd.setVisible(false);
			btn_back.setVisible(true);
			btn_update.setVisible(false);

			currency.setValue(References.s_val(current.getCurrency()));
			fee_type.setValue(References.ss_qr_commission_fee(current.getFee_type()));
			format.setValue(current.getFormat() + "." + References.ss_qr_cod_types(current.getCode_type()));

			String id_category = References.ss_qr_category_suppliers(current.getCategory());
			category_supp.setValue(id_category.split("/")[0]);
			diapazon_supp.setValue(References.ss_qr_diapazon_suppliers(id_category.split("/")[1]));
		}
	}

	public void onClick$btn_back() {
		if (frm.isVisible()) {
			frm.setVisible(false);
			grd.setVisible(true);
			btn_back.setVisible(false);
			btn_update.setVisible(true);
		} else {
			grd.setVisible(false);
			frm.setVisible(true);
			frmgrd.setVisible(true);
			addgrd.setVisible(false);
			fgrd.setVisible(false);
			btn_back.setVisible(false);
			btn_update.setVisible(true);
		}

	}

	private void sendSelEvt() {
		SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
		Events.sendEvent(evt);
	}
	/*public void onClick$btn_trm()throws Exception{
		String qr_str1="00020101021140660012qr-online.uz01186paIoTYX1x2yWKhLzW0202010309E0081AC790405ovqat52045812530386054061000005502015802UZ5919ABDULLOH ELIT GROUP6015Toshkent Muqimi610610004380560012qr-online.uz03129989773381080420agrobank.max@mail.ru6304C56F";
		Map<String, String> map = new HashMap<String, String>();
		TransactionService ts=new TransactionService();
		ts.trimStr(qr_str1,map);
		
		System.out.println("Qr_id: "+qr_str1);

	}*/

	public void onClick$btn_print() throws Exception {
		BufferedImage image = null;
        if(dataGrid.getSelectedItem()!=null){
		String str = TransactionService.getQrCode(current.getId());
		ISLogger.getLogger().error("QR_STR---- :"+str);
		String base64Image = str.substring(str.indexOf(",") + 1, str.length());
		System.out.println("qr code 56: " + base64Image);

		try {
			String path = application.getRealPath("/reports/qr.png");
			ISLogger.getLogger().error("path---- :"+path);
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] decodedBytes = DatatypeConverter.parseBase64Binary(base64Image);

			File output = new File(path);
			//File output = new File("/reports/qr.png");
			String decodeString = new String(decodedBytes);
			ByteArrayInputStream bis = new ByteArrayInputStream(decodedBytes);
			ImageIO.setUseCache(false);
			image = ImageIO.read(bis);
			bis.close();
			ImageIO.write(image, "png", output);
			System.out.println("image :" + image);
			String docFile = application.getRealPath("/reports/test_qr.docx");
			ISLogger.getLogger().error("path_doc_file---- :"+docFile);
			addImageToWord(decodedBytes, path, "qr.png", docFile);
			Filedownload.save	(new File(docFile),
					"application/vnd.openxmlformats-officedocument.wordprocessingml.document");

		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error("oshibka metod onClick$btn_print() :");
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
        }
        
	}

	public void addImageToWord(byte[] qr_code, String filePath, String fileName, String docFile)
			throws IOException, InvalidFormatException {
		XWPFDocument doc = new XWPFDocument();
		XWPFParagraph p = doc.createParagraph();
		XWPFRun r = p.createRun();

		ByteArrayInputStream bais = new ByteArrayInputStream(qr_code);
		BufferedImage bimg1 = ImageIO.read(bais);
		double width1 = bimg1.getWidth();
		double height1 = bimg1.getHeight();
		double scaling=1.0;
		if(width1>72*6){
			scaling=(72*6)/width1;
		}
		int imgFormat1 = XWPFDocument.PICTURE_TYPE_PNG;
		//String p1 = "Text";
		Merchant getMerchant = MerchantService.getMerchant(merchant_id.getValue());
		String  name = PayeeService.getNamePayee(getMerchant.getPayee_id());
		r.setText(name);
		r.addBreak();
		r.addPicture(new FileInputStream(filePath), imgFormat1, fileName, Units.toEMU(width1*scaling), Units.toEMU(height1*scaling));
		FileOutputStream out = new FileOutputStream(new File(docFile), false);
		doc.write(out);
		out.close();
		doc.close();

	}

	public void onClick$btn_search() {
		grd.setVisible(false);
		frm.setVisible(true);
		frmgrd.setVisible(false);
		addgrd.setVisible(false);
		fgrd.setVisible(true);
		btn_back.setVisible(true);
		btn_update.setVisible(false);
	}

	public void onClick$btn_save() {
		try {
			boolean ifTrue = true;
			if (addgrd.isVisible()) {
				boolean isEmpty = false;
				if (afee_type.getValue().equals("02")) {
					isEmpty = isEmpty(aformat.getValue(), acurrency.getValue(), acategory.getValue(),
							afee_amount.getValue());
				} else if (afee_type.getValue().equals("03")) {
					isEmpty = isEmpty(aformat.getValue(), acurrency.getValue(), acategory.getValue(),
							afee_percent.getValue());
				} else if (afee_type.getValue().equals("01")) {
					isEmpty = isEmpty(aformat.getValue(), acurrency.getValue(),/* aamount.getValue(),*/
							acategory.getValue());
				} else {
					isEmpty = isEmpty(aformat.getValue(), acurrency.getValue(), acategory.getValue());
				}

				if (!isEmpty) {
					alert("Не заполнены необходимые параметры");
					ifTrue = false;
				} else {
					int code_type = Integer.parseInt(aformat.getValue());
					String format = aformat.getText().substring(0, 2);
                     System.out.println("RRRRRR_:"+format);
					Transaction transaction = new Transaction(merchant_id.getValue(), acurrency.getValue(),
							aamount.getValue(), acategory.getValue(), aproduct_code.getValue(),
							aproduct_name.getValue(), afee_type.getValue(), afee_amount.getValue(),
							afee_percent.getValue(), format);
					transaction.setCode_type(code_type);

					TransactionService.create(transaction);

					CheckNull.clearForm(addgrd);
					frmgrd.setVisible(true);
					addgrd.setVisible(false);
					fgrd.setVisible(false);
					ifTrue = true;
				}
			} else if (fgrd.isVisible()) {
				filter = new TransactionFilter();

				int code_type = Integer.parseInt(fformat.getValue());
				String format = aformat.getText().substring(0, 2);

				filter.setMerchant_id(merchant_id.getValue());
				filter.setFormat(format);
				filter.setCurrency(fcurrency.getValue());
				filter.setAmount(famount.getValue());
				filter.setCategory(fcategory.getValue());
				filter.setProduct_code(fproduct_code.getValue());
				filter.setProduct_name(fproduct_name.getValue());
				filter.setFee_type(ffee_type.getValue());
				filter.setFee_amount(ffee_amount.getValue());
				filter.setFee_percent(ffee_percent.getValue());
				filter.setCode_type(code_type);

				System.err.println("Save filter");
			} else {
				boolean isEmpty = false;
				if (fee_type.getValue().equals("02")) {
					isEmpty = isEmpty(format.getValue(), currency.getValue(), category.getValue(),
							fee_amount.getValue());
				} else if (fee_type.getValue().equals("03")) {
					isEmpty = isEmpty(format.getValue(), currency.getValue(), category.getValue(),
							fee_percent.getValue());
				} else if (fee_type.getValue().equals("01")) {
					isEmpty = isEmpty(format.getValue(), currency.getValue(), /*amount.getValue(),*/ category.getValue());
				} else {
					isEmpty = isEmpty(format.getValue(), currency.getValue(), category.getValue());
				}

				if (!isEmpty) {
					alert("Не заполнены необходимые параметры");
					ifTrue = false;
				} else {

					int code_type = Integer.parseInt(format.getValue());
					String newformat = format.getText().substring(0, 2);

					current.setMerchant_id(merchant_id.getValue());
					current.setId(current.getId());
					current.setFormat(newformat);
					current.setCurrency(currency.getValue());
					current.setAmount((amount.getValue()==null?"":amount.getValue()));
					current.setCategory(category.getValue());
					current.setProduct_code(product_code.getValue());
					current.setProduct_name(product_name.getValue());
					current.setFee_type(fee_type.getValue());
					current.setFee_amount(fee_amount.getValue());
					current.setFee_percent(fee_percent.getValue());
					current.setCode_type(code_type);

					TransactionService.update(current);
					ifTrue = true;
				}
			}

			if (ifTrue) {
				onClick$btn_back();
				refreshModel(_startPageNumber);
				SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
				Events.sendEvent(evt);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}

	}

	public void onClick$btn_cancel() {
		if (fgrd.isVisible()) {
			filter = new TransactionFilter();
			filter.setMerchant_id(merchant_id.getValue());
		}
		onClick$btn_back();
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		CheckNull.clearForm(addgrd);
		CheckNull.clearForm(fgrd);
		refreshModel(_startPageNumber);
	}

	public void onClick$btn_close() {
		transactionmain.detach();
	}

	public void onClick$btn_add() {
		grd.setVisible(false);
		frm.setVisible(true);
		frmgrd.setVisible(false);
		addgrd.setVisible(true);
		fgrd.setVisible(false);
		btn_back.setVisible(true);
		btn_update.setVisible(false);
	}

	public void settings() {
		aamount.setMaxlength(13);
		acategory.setMaxlength(4);
		aproduct_code.setMaxlength(12);
		aproduct_name.setMaxlength(32);
		afee_amount.setMaxlength(13);
		afee_percent.setMaxlength(5);

		amount.setMaxlength(13);
		category.setMaxlength(4);
		product_code.setMaxlength(12);
		product_name.setMaxlength(32);
		fee_amount.setMaxlength(13);
		fee_percent.setMaxlength(5);

		famount.setMaxlength(13);
		fcategory.setMaxlength(4);
		fproduct_code.setMaxlength(12);
		fproduct_name.setMaxlength(32);
		ffee_amount.setMaxlength(13);
		ffee_percent.setMaxlength(5);
	}

	public static boolean isEmpty(String... args) {
		boolean isEmpty = true;
		for (String str : args) {
			if (str == null || str.length() == 0) {
				isEmpty = false;
			}
		}
		return isEmpty;
	}

	public static String isNull(String value) {
		if (value == null) {
			value = "";
		}
		return value;
	}

	public void onChange$afee_type() {
		if (afee_type.getValue().equals("02") || fee_type.getValue().equals("02")) {
			alabel_fee_amount.setValue(alabel_fee_amount.getValue() + " *");
			alabel_fee_percent.setValue(alabel_fee_percent.getValue().replace("*", ""));
			alabel_amount.setValue(alabel_amount.getValue().replace("*", ""));
			label_fee_amount.setValue(label_fee_amount.getValue() + " *");
			label_fee_percent.setValue(label_fee_percent.getValue().replace("*", ""));
			label_amount.setValue(label_amount.getValue().replace("*", ""));
		}
		if (afee_type.getValue().equals("03") || fee_type.getValue().equals("03")) {
			alabel_fee_percent.setValue(alabel_fee_percent.getValue() + " *");
			alabel_fee_amount.setValue(alabel_fee_amount.getValue().replace("*", ""));
			alabel_amount.setValue(alabel_amount.getValue().replace("*", ""));
			label_fee_percent.setValue(label_fee_percent.getValue() + " *");
			label_fee_amount.setValue(label_fee_amount.getValue().replace("*", ""));
			label_amount.setValue(label_amount.getValue().replace("*", ""));
		}
		if (afee_type.getValue().equals("01") || fee_type.getValue().equals("01")) {
			alabel_amount.setValue(alabel_amount.getValue() + " *");
			alabel_fee_amount.setValue(alabel_fee_amount.getValue().replace("*", ""));
			alabel_fee_percent.setValue(alabel_fee_percent.getValue().replace("*", ""));
			label_amount.setValue(label_amount.getValue() + " *");
			label_fee_amount.setValue(label_fee_amount.getValue().replace("*", ""));
			label_fee_percent.setValue(label_fee_percent.getValue().replace("*", ""));
		}
	}

	public void onClick$btn_send() {
		if (dataGrid.getSelectedItem() != null) {
			System.out.println("Poluchit qr code");
			String qrUrl = "";
			try {
				String format = current.getFormat().substring(0, 2);
				ISLogger.getLogger().error("Format :"+format);
				String amount1="";
				//Header header = new Header(TransactionService.getBank(branch), branch, format);
				if(current.getAmount()!=null){
				String amount = String.format("%.2f", Double.parseDouble(current.getAmount())).replace(",", ".");
				amount1=amount.replace(".", "");
				System.out.println("amount1: "+amount1);
				}
				
				
				Transaction transaction = new Transaction(format,current.getCurrency(), isNull(amount1), current.getCategory(),
						isNull(current.getProduct_code()), isNull(current.getProduct_name()),
						isNull(current.getFee_type()), isNull(current.getFee_amount()),
						isNull(current.getFee_percent()));

				Merchant getMerchant = MerchantService.getMerchant(merchant_id.getValue());

				Merchant merchant = new Merchant(getMerchant.getActivity(), getMerchant.getName(),
						getMerchant.getCountry().substring(0, 2), getMerchant.getCity(),
						isNull(getMerchant.getPostal_code()), isNull(getMerchant.getPhone_number()),
						isNull(getMerchant.getEmail()));
				Payee_template payee_template=new Payee_template();
				Localized_info localinfo = new Localized_info("uz",getMerchant.getName(),null);
				

				Payee getPayee = PayeeService.getPayee(getMerchant.getPayee_id());
				ISLogger.getLogger().error("getPayee: "+getPayee);

				Payee payee = new Payee(getPayee.getBranch(),getPayee.getInn(), getPayee.getAccount());

				System.out.println(transaction.toString());
				System.out.println(merchant.toString());
				ISLogger.getLogger().error("object_Transaction :"+transaction.toString());
				ISLogger.getLogger().error("object_Merchant :"+merchant.toString());
				System.out.println(payee.toString());
				ISLogger.getLogger().error("object_payee :"+payee.toString());
				String dsign="";
				String docnum=PayeeService.getDocnum(getPayee.getInn(), getPayee.getBranch());

				Body body = new Body(payee.toString(), merchant.toString(), transaction.toString(), null, localinfo.toString(),dsign);
				//Send send = new Send(header.toString(), body.toString());
				System.out.println(body.toString());

				Answer answer = Request.getQRCB(body,docnum);
				String message = answer.getMessage();
				String code = answer.getCode();
				String qr = answer.getQr();
				String qr_id=answer.getQr_id();
				String descr=answer.getDescr();

				TransactionService.updateMessage(current.getId(), message, code, qr,qr_id,descr);
				if (code.equals("12000")) {
					System.out.println("Generate qr kod");
					qrUrl = QrGenerator.qrGenerate(getPayee.getInn(), merchant_id.getValue(), current.getId(), qr);
				}

				alert(message + "\n" + qrUrl);
			} catch (Exception e) {
				alert(e.getMessage());
				ISLogger.getLogger().error(CheckNull.getPstr(e));
			}

			CheckNull.clearForm(addgrd);
			frmgrd.setVisible(true);
			addgrd.setVisible(false);
			fgrd.setVisible(false);
			refreshModel(_startPageNumber);
			SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
			Events.sendEvent(evt);
		}
	}

	public void onSelect$adiapazon_supp() {
		String id_diapazon = adiapazon_supp.getValue();
		acategory_supp.setModel(new ListModelList(RefDataService
				.getRefData("select s.id data, s.name_ru label from ss_qr_category_suppliers s where s.id_diapazon='"
						+ id_diapazon + "'", alias)));

	}

	public void onSelect$acategory_supp() {
		String id_category = acategory_supp.getValue();
		acategory.setValue(id_category.substring(0, 4));
	}

	public void onSelect$diapazon_supp() {
		String id_diapazon = diapazon_supp.getValue();
		category_supp.setModel(new ListModelList(RefDataService
				.getRefData("select s.id data, s.name_ru label from ss_qr_category_suppliers s where s.id_diapazon='"
						+ id_diapazon + "'", alias)));

	}

	public void onSelect$category_supp() {
		String id_category = category_supp.getValue();
		category.setValue(id_category.substring(0, 4));
	}

}