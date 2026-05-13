package com.is.bpri.reports;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.ListModelList;

import com.is.bpri.BproductReport;
import com.is.bpri.BproductService;
import com.is.utils.RefCBox;

@SuppressWarnings("serial")
public class CreditLine extends GenericForwardComposer{
	
	private String alias,branch;
	private RefCBox mfo;
	
	public CreditLine() {
		super('$',false,false);
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		alias = (String) session.getAttribute("alias");
		branch = (String) session.getAttribute("branch");
		mfo.setModel(new ListModelList(BproductService.getMfo(branch, alias)));
	}
	
	public void onClick$print(){
		if(mfo.getValue()!=null&&!mfo.getValue().equals("")){
			printCreditLine(mfo.getValue());
		}
	}
	
	private void printCreditLine(String mfo){
		XSSFWorkbook wb;
		InputStream is = null;
		List<BproductReport> list = BproductService.getReport(mfo,alias);
		try {
			is = new FileInputStream(application.getRealPath("/reports/bpr_report.xlsx"));
			wb = new XSSFWorkbook(is);
			XSSFSheet sheet = wb.getSheetAt(0);
			XSSFCellStyle style = wb.createCellStyle();
			style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
			style.setBorderTop(XSSFCellStyle.BORDER_THIN);
			style.setBorderRight(XSSFCellStyle.BORDER_THIN);
			style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
			XSSFRow row = null;
			XSSFCell cell = null;
			BigDecimal one = null;
			BigDecimal two = null;
			BigDecimal three = null;
			String tempID = "";
			int i = 4;
			int kol = 0;
			int irow = i;
			for (; i < list.size()+4; i++) {
				char temp = list.get(i-4).getS_in().charAt(0);
				if(temp=='-'){
					list.get(i-4).setS_in(list.get(i-4).getS_in().substring(1));
				}
				if(!tempID.equals(list.get(i-4).getId())){
					one = BigDecimal.ZERO;
					two = BigDecimal.ZERO;
					three = BigDecimal.ZERO;
					irow = i;
					irow -= kol;
					sheet.createRow(irow);
					row = sheet.getRow(irow);
					row.createCell(0);
					cell = row.getCell(0);
					cell.setCellStyle(style);
					cell.setCellValue(list.get(i-4).getId());
					row.createCell(1);
					cell = row.getCell(1);
					cell.setCellStyle(style);
					cell.setCellValue(list.get(i-4).getClient());
					row.createCell(2);
					cell = row.getCell(2);
					cell.setCellStyle(style);
					cell.setCellValue(Double.parseDouble(list.get(i-4).getAmount())/100);
					row.createCell(3);
					cell = row.getCell(3);
					cell.setCellStyle(style);
					if(list.get(i-4).getAcc_type_id().equals("2")){
						one = new BigDecimal(list.get(i-4).getS_in()).divide(new BigDecimal(100));
						cell.setCellValue(one.doubleValue());
					} 
					row.createCell(4);
					cell = row.getCell(4);
					cell.setCellStyle(style);
					if(list.get(i-4).getAcc_type_id().equals("3")){
						two = new BigDecimal(list.get(i-4).getS_in()).divide(new BigDecimal(100));
						cell.setCellValue(two.doubleValue());
					} 
					row.createCell(5);
					cell = row.getCell(5);
					cell.setCellStyle(style);
					if(list.get(i-4).getAcc_type_id().equals("9")){
						three = new BigDecimal(list.get(i-4).getS_in()).divide(new BigDecimal(100));
						cell.setCellValue(three.doubleValue());
					}
				} else {
					if(list.get(i-4).getAcc_type_id().equals("2")){
						row.createCell(3);
						cell = row.getCell(3);
						cell.setCellStyle(style);
						one = new BigDecimal(list.get(i-4).getS_in()).divide(new BigDecimal(100));
						cell.setCellValue(one.doubleValue());
						++kol;
					} 
					if(list.get(i-4).getAcc_type_id().equals("3")){
						row.createCell(4);
						cell = row.getCell(4);
						cell.setCellStyle(style);
						two = new BigDecimal(list.get(i-4).getS_in()).divide(new BigDecimal(100));
						cell.setCellValue(two.doubleValue());
						++kol;
					} 
					if(list.get(i-4).getAcc_type_id().equals("9")){
						row.createCell(5);
						cell = row.getCell(5);
						cell.setCellStyle(style);
						three = new BigDecimal(list.get(i-4).getS_in()).divide(new BigDecimal(100));
						cell.setCellValue(three.doubleValue());
						++kol;
					}
				}
				row.createCell(6);
				cell = row.getCell(6);
				cell.setCellStyle(style);
				cell.setCellValue(new BigDecimal(list.get(i-4).getAmount()).divide(new BigDecimal(100)).subtract(one).subtract(two).subtract(three).doubleValue());
				row.createCell(7);
				cell = row.getCell(7);
				cell.setCellStyle(style);
				cell.setCellValue(list.get(i-4).getBranch());
				tempID = list.get(i-4).getId();
			}
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			wb.write(out);
			out.close();
			byte[] arr = out.toByteArray();
			AMedia b = new AMedia("CreditLineReport.xlsx", "xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", arr);
			Filedownload.save(b);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(is!=null){
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
