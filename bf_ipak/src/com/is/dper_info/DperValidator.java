package com.is.dper_info;

import com.is.dper_info.model.dper_info;
import com.is.dper_info.service.SumsService;
import com.is.dper_info.service.dper_infoService;
import com.is.utils.CheckNull;

public class DperValidator {
	private dper_info dper;
	private String alias;
	
	private DperValidator(dper_info dper, String alias) {
		this.dper = dper;
		this.alias = alias;
	}
	
	public static DperValidator getInstance(dper_info dper, String alias) {
		return new DperValidator(dper, alias);
	}
	
	public String check() {
		String error = checkInput();
		if(error.length() != 0) {
			return error;
		}
		return null;
	}
	
	private String checkInput() {
		
		System.out.println(dper.getMtcn());
		
		/*if (CheckNull.isEmpty(dper.getMtcn())
				|| !(dper.getMtcn().matches("[0-9]+"))) {
			return "������ ���������� �����:\n ������� ��������� ���� \n ��� ��������(MTCN) ��� ����";
		}*/
//		if (err.trim().length() != 0) {
//			return ("������ ���������� �����:\n ������� ��������� ���� " + err);
//		}
		System.out.println("countries - " + dper.getStrr() + " 2ountries - " + dper.getStrs());
		
		
		
		if ((dper.getStrr()).equals(dper.getStrs())) {
			
			return ("(2868)��������� ��� �������� �������� ������� �� ������ ("
					+ dper.getStrs() + ") � �� �� ����� ������ ("
					+ dper.getStrr() + ")" + " �����������");
		}
		
		if (!dper_infoService.isValidCode("s_str", dper.getStrs(), alias)) {
			return  "\n" + dper.getStrs()
					+ "��� ������-���� �� ������ � ����������� �����";
		}
		if (!dper_infoService.isValidCode("s_str", dper.getStrr(), alias)) {
			return  "\n" + dper.getStrr()
					+ "��� ������-����� �� ������ � ����������� �����";
		}
		if (!dper_infoService.isValidCode("s_str", dper.getClient_i13code_str(), alias)) {
			return  "\n" + dper.getClient_i13code_str()
					+ "��� ������ ����� ����������� �� ������ � ����������� �����";
		}
		if (!dper_infoService.isValidCode("distr", dper.getDistr(), alias)) {
			return  "\n" + dper.getDistr()
					+ "��� ������ ������ �� ������ � �����������";
		}
		if (!dper_infoService.isValidCode("u1f2", Integer.toString(dper.getU1f2()), alias)) {
			return  "\n" + dper.getU1f2()
					+ "��� ��� ���� ����������� �� ������ � �����������";
		}
		
		if (!dper_infoService.isValidCode_dper_dop(7, dper.getCurrency(), alias)) {
			return  "\n" + dper.getCurrency()
					+ "��� ������ �� ������ � �����������";
		}
		if (!dper_infoService.isValidCode_dper_dop(7, dper.getEval(), alias)) {
			return  "\n" + dper.getEval()
					+ "��� ������� ������ �� ������ � �����������";
		}
		if (!dper_infoService.isValidCode_dper_dop(12, dper.getVeoper(), alias)) {
			return  "\n" + dper.getVeoper()
					+ "��� ����� �������� �� ������ � ����������� ";
		}
		
//		if (!CheckNull.isEmpty(adoc_date_exp.getValue())) {
//			try {
//				Date v_Date_tmp = df.parse(df.format(av_date.getValue()));
//				Date ecpir_tmp = df.parse(df.format(adoc_date_exp.getValue()));
//				if (v_Date_tmp.compareTo(
//						ecpir_tmp) > 0) {
//					alert("���� ���������������� ��������� ����� ��� ����������");
//					return;
//				}
//			} catch (WrongValueException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} 
//			
//		}
		
		return "";
	}

	public String checkKontragent() {
		String summa_per_v_summah = 
				SumsService.getPerInSums(dper.getSumma3(), 
						dper.getCurrency(),alias); 

			String kontra = "";
			if(summa_per_v_summah!=null&&!summa_per_v_summah.equals("NO_DATA_FOUND")){
				try {
					//�������� ���. �������
					if(dper.getKind().equals("0")){ 
						kontra += checkCustomerSum(summa_per_v_summah, 6, 5, 8, 7, 2908, 2895, 2908, 2895);
					}
					//�������� ���. �������
					if(dper.getKind().equals("1")){ 
						kontra += checkCustomerSum(summa_per_v_summah, 2, 1, 4, 3, 2908, 2895, 2908, 2895);
					}
					//������� ���. �������
					if(dper.getKind().equals("2")){ 
						kontra += checkCustomerSum(summa_per_v_summah, 11, 10, 13, 12, 3111, 3126, 3165, 3180);
					}
				} catch (Exception e) {
					return e.getMessage();
				}
			}

			if(kontra.trim().length()!=0){
				return ("(3174) ������� ������ �����������: "+kontra+"\n�� ��������� ��������������� �2528 " +
						"\n(��. ����������� ���� � ����������� �15 � �. �������� ��������)");
			}
			return "";
	}
	
	private int counter(String sums,int id,int err) throws Exception{	//������������� ����� 0 ��� 1
		String var = SumsService.getMinMax(15, id,alias);		
		int counter = 0;								
		if(var.trim().length()==0){
			counter = 0;
		}
		else {
			try {;
				if(Double.compare(Double.parseDouble(sums), Double.parseDouble(var)) > 0){
					counter = 1;
				}
			} catch (NumberFormatException e){
				String sets="";
				if(id==2||id==4||id==11||id==13||id==6||id==8)sets="�����������";
				else sets="�����������";
				throw new Exception("("+err+") �������� ����� � ���������� ���������� "+sets+ " \"������ �����������\"");
			}
		}
		return counter;
	} 
	private String checkCustomerSum(String summ,		//��������� ����� �������� �� ��� � ���� 
			int min1,int max1,int min2,int max2,	//� ����������� 15
			int err1,int err2,int err3,int err4) throws Exception{	//��� ������� ���� ���. ���.
		int counter_min = 0,counter_max = 0;
		String kontra="";							
		//���� ��� ����
		if(dper.getU1f2() == 2){ 
			counter_min=counter(summ,min1,err1);
			counter_max=counter(summ,max1,err2);
		}
		if(counter_max > 0||counter_min > 0){
			if(dper.getClient_i().trim().length()==0)kontra+=" - �������";
			if(dper.getClient_i2().trim().length()==0)kontra+=" - ���";
			if(dper.getMtcn().trim().length()==0)kontra+=" - MTCN";
		}
		if(counter_max > 0){
			if(dper.getClient_i11date()==null||
					dper.getClient_i12().trim().length()==0)
				kontra+=" - ����� ��� ���� ��������";
		}
		//���� �� ���� 3165 3180
		if(dper.getU1f2() == 1) { 
			counter_min=counter(summ,min2,err3);
			counter_max=counter(summ,max2,err4);
		}
		if(counter_max > 0||counter_min > 0){
			if(dper.getMtcn().trim().length()==0)kontra+=" - MTCN";
		}
		if(counter_max > 0){
			if(dper.getClient_i10()==null)
				kontra+=" - �����";
		}
		kontra = kontra.length() != 0 ? "(3174) ������� ������ �����������: "+kontra+"\n�� ��������� ��������������� �2528 " +
				"\n(��. ����������� ���� � ����������� �15 � �. �������� ��������)" : "";
		return kontra;
	}
}
