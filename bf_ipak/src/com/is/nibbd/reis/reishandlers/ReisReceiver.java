package com.is.nibbd.reis.reishandlers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import com.is.ISLogger;
import com.is.nibbd.models.ArchiveItem;
import com.is.nibbd.models.Nibbd;
import com.is.nibbd.reis.filehandlers.Extractor;
import com.is.nibbd.reis.filehandlers.ExtractorImpl;
import com.is.nibbd.reis.reisServices.ReceiverService;
import com.is.nibbd.util.FilePaths;

public class ReisReceiver implements ReisHandler{
	private ReceiverService service;
	private ReisReceiver(ReceiverService service){
		this.service = service;
	}
	public static ReisReceiver getInstance(ReceiverService service){
		return new ReisReceiver(service);
	}
	public ReisReceiver() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void handle() {
		Extractor extractor = ExtractorImpl.getInstance(FilePaths.INP.getPath());
		Nibbd nibbd = null; 
		List<ArchiveItem> files = extractor.getQueries();
		for(ArchiveItem item: files) {
			try {
				nibbd = new Nibbd(item.getTextData()); 
				nibbd.setQuery_date(getQueryDate(item.getArchiveName()));
				nibbd.setReis_num(getReisNo(item.getArchiveName()));
				service.updateToState3(nibbd);
				
			} catch (ParseException e) {
				ISLogger.getLogger().error("Error while getting query_date "+e.getStackTrace());
				e.printStackTrace();
			}
			
		}
	}
	
	private String getReisNo(String fileName) {
		if(fileName == null) {
			return null;
		}
		int index = fileName.indexOf(".");
		return fileName.substring(index+2);
	}
	private Date getQueryDate(String fileName) throws ParseException {
		if(fileName == null){ return null; }
//		Date operDay = service.getBankDate();
		Date operDay = new Date();
		String day = fileName.substring(6, 8);
		String month = fileName.substring(9, 10);
		String year = null;
		StringBuilder sb = new StringBuilder();
		if(month.equals("a")){
			month = "10";
		} else if(month.equals("b")){
			month = "11";
		} else if(month.equals("c")){
			month = "12";
		} else {
			month = "0"+month;
		}
		DateTime dt = new DateTime(operDay);
		year = Integer.toString(dt.getYear());
		if(month.equals("12") && dt.getDayOfYear() == 1) {
			year = Integer.toString(dt.getYear()-1);
		}
		sb.append(day).append(".").append(month).append(".").append(year);
		
		return new SimpleDateFormat("dd.MM.yyyy").parse(sb.toString());
	}
	
	private void moveFile(ArchiveItem item) {
		
	}
	private boolean isReisStartsWithLetter(String str) {
		return str.equalsIgnoreCase("A")
				|| str.equalsIgnoreCase("B")
				|| str.equalsIgnoreCase("C")
				|| str.equalsIgnoreCase("D")
				|| str.equalsIgnoreCase("E")
				|| str.equalsIgnoreCase("F");
	}
	
//	public static void main(String[] args) {
//		String fileName = "}0044419.149";
//		ReisReceiver item = new ReisReceiver();
//		System.out.println(item.getReisNo(fileName));
//		try {
//			System.out.println(item.getQueryDate(fileName));
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
