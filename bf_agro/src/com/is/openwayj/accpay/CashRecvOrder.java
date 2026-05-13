package com.is.openwayj.accpay;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Filedownload;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.openwayutils.report.PoiReport;
import com.is.openwayutils.report.basic.ExcelReport;
import com.is.utils.CheckNull;

public class CashRecvOrder extends PoiReport {
	private String un;
	private String pwd;
	private String alias;
	//private Circulate cir1;
	private Long general_id;
	private final static SimpleDateFormat df = new SimpleDateFormat(
	"dd-MM-yyyy");
    private final static String emptyString = "";


	public CashRecvOrder(String un,String pwd,String alias,Long general_id){
		super();
		this.un = un;
		this.pwd = pwd;
		this.alias = alias;
		//this.cir1=cir1;
		this.general_id=general_id;
	}
	
	private CashRecvOrder() {
	}
	
	public static CashRecvOrder getInstance() {
		return new CashRecvOrder();
	}
	
	@Override
	public AMedia getRepmd(Map<String, Object> params, Connection c,
			String templf, String outfl) {
		AMedia repMd = null;
		POIFSFileSystem fs = null;
		HSSFWorkbook workbook = null;
		try {
			fs = new POIFSFileSystem(new FileInputStream(templf));
			workbook = new HSSFWorkbook(fs);
			HSSFSheet sheet = workbook.getSheetAt(0);

			//fillRow(sheet, 3, 1, "hamza");
			//fillRow(sheet, 1, 3, "test");
			//fillRow(sheet, 2, 3, "test");
			
			//replaceval2(sheet, "test", "notebook");

			//replaceval2(sheet, "<AMOUNT_TEXT>", "BIR YUZ MILLION TUQSON YETTI BESH ");
			
			replaceval2(sheet, "<CASH_RECV_ORDER_NUMBER>", params.get("CASH_RECV_ORDER_NUMBER").toString());
			replaceval2(sheet, "<DATE>", params.get("DATE").toString());
			replaceval2(sheet, "<DEBT_ACCOUNT_NUM>", params.get("DEBT_ACCOUNT_NUM").toString());
			replaceval2(sheet, "<AMOUNT>", params.get("AMOUNT").toString());
			replaceval2(sheet, "<DEBT_CLIENT_NAME>", params.get("DEBT_CLIENT_NAME").toString());
			replaceval2(sheet, "<CREDIT_ACCOUNT_NUM>", params.get("CREDIT_ACCOUNT_NUM").toString());
			replaceval2(sheet, "<AMOUNT_TEXT>", params.get("AMOUNT_TEXT").toString());
			replaceval2(sheet, "<PAYMENT_PURPOSE>", params.get("PAYMENT_PURPOSE").toString());
			
				/*String id = "rs.getString(id)";
				fillRow(sheet, 3, 1, id);

				String name = "rs.getString(name)";
				fillRow(sheet, 4, 1, name);

				String region = emptyString;
				fillRow(sheet, 6, 1, region);


				String passport_place_registration = "rs.getString(passport_place_registration)";
				fillRow(sheet, 15, 2, passport_place_registration);

				String inn = "rs.getString(number_tax_registration)";
				fillRow(sheet, 16, 1, inn);


				String phone = emptyString;
				fillRow(sheet, 19, 2, phone);

				String som_opers = emptyString;
				fillRow(sheet, 20, 1, som_opers);

				String pod_opers = emptyString;
				fillRow(sheet, 21, 1, pod_opers);

				String add_data = emptyString;
				fillRow(sheet, 22, 1, add_data);

				String job_information = emptyString;
				fillRow(sheet, 23, 1, job_information);

				String account_open_purpose = emptyString;
				fillRow(sheet, 24, 1, account_open_purpose);

				String date_first_acc = emptyString;
				fillRow(sheet, 25, 1, date_first_acc);

				String emp_account_open_name = emptyString;
				fillRow(sheet, 26, 1, emp_account_open_name);

				String emp_account_confirm_name = emptyString;
				fillRow(sheet, 27, 1, emp_account_confirm_name);

				String accountOpen = emptyString;
				fillRow(sheet, 28, 1, accountOpen);

				String risk_degree_name = emptyString;
				fillRow(sheet, 29, 1, risk_degree_name);

				String risk_date = emptyString;
				fillRow(sheet, 29, 1, sheet.getRow(29).getCell(2) + risk_date);

				String risk_degree_detail = emptyString;
				fillRow(sheet, 30, 1, risk_degree_detail);

				String date_open = emptyString;
				fillRow(sheet, 31, 1, date_open);

				String date_change = emptyString;
				fillRow(sheet, 32, 1, date_change);

				String date_last_save = emptyString;
				fillRow(sheet, 33, 1, date_last_save);*/

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			workbook.write(out);
			repMd = new AMedia(outfl, "xls", "application/vnd.ms-excel",
					out.toByteArray());
			workbook.close();
		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
		return repMd;
	}

	private void fillRow(HSSFSheet sheet, int rowNum, int cellNum, String value) {
		HSSFRow row = sheet.getRow(rowNum);
		Cell cell = row.getCell(cellNum);
		if (cell != null)
			cell.setCellValue(value);
	}

	//public static void replaceText(String arg0, String arg1, Range range) {
	//	if (arg1 != null)
	//		range.replaceText(arg0, arg1);
	//}

	private void replaceval2(HSSFSheet sheet, String cellContent, String newChar){
	    for(Row row : sheet) {
	            //System.out.println("row "+row.getRowNum());
	        for(Cell cell : row) {
	                //System.out.println("cell "+cell.getColumnIndex());
	            if(cell.getCellType() == Cell.CELL_TYPE_STRING){
	                if(cell.getRichStringCellValue().getString ().contains(cellContent) );{
	                        cell.setCellValue( cell.getStringCellValue().replace(cellContent, newChar));
	                }
	            }
	        }
	    }

	}
	
	public void getReport(String reportClass, String template, String outFile,
			Map<String, Object> params) {
		Connection c = null;
		PoiReport poiReport = null;
		AMedia repMd = null;
		try {
			c = ConnectionPool.getConnection();
			poiReport = (PoiReport) Class.forName(reportClass).newInstance();
			repMd = poiReport.getRepmd(params, c, Executions.getCurrent()
					.getDesktop().getWebApp().getRealPath(template), outFile);
			Filedownload.save(repMd.getByteData(), "application/vnd.ms-excel",
					outFile + ".xls");
		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
	}
}

