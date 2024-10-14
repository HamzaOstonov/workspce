package com.is.accounts;

import java.util.ArrayList;
import java.util.List;

import com.is.accounts.SAccount;
import com.is.clientcrm.utils.Util;
import com.is.accounts.DbUtils;
import com.is.utils.RefData;

public class AccountUtil {
	
	public static String getSgn(String accBal, List<SAccount> list) {
		String sgn = null;
		for(SAccount sacc: list) {
			if(sacc.getCode_b().equals(accBal)){
				if(sacc.getType() == null) {
					return "N";
				} else if(sacc.getType().equals("0")){
					return "A";
				} else if(sacc.getType().equals("1")){
					return "P";
				}
			}
		}
		return sgn;
	}
	
	
	public static String getBal(String accBal) {
		if(accBal == null || accBal.length() == 0){
			return null;
		}
		int firstDigit= Integer.parseInt(accBal.substring(0,1));
		return Util.inInts(firstDigit, 1,2,3,4,5)?"B":firstDigit == 9 ? "O":null;
	}
	public static List<RefData> sortByClientType(String type, List<SAccount> saccList) {
		List<RefData> list = new ArrayList<RefData>();
		for(SAccount sacc: saccList) {
			if(sacc.getKod_k().equals(type)
							|| sacc.getKod_k().equals("00")){
				list.add(new RefData(sacc.getCode_b(),sacc.getName_s()));
			}
		}
		return list;
	}
	public static List<RefData> getAccBal(List<SAccount> saccList){
		List<RefData> list = new ArrayList<RefData>();
		for(SAccount sacc: saccList) {
//			if(sacc.getDestin().equals("3") 
//					&& sacc.getAct().equals("A")
//					&& !sacc.getCode_b().substring(1, 5).equals("0000")
//					&& !sacc.getCode_b().substring(2, 5).equals("000")){
				list.add(new RefData(sacc.getCode_b(),sacc.getName_s()));
//			}
		}
		return list;
	}
	
	public static int getSignRegistr(String accBal, List<SAccount> list) {
		int signRegistr = 2;
		for(SAccount sacc: list) {
			if(sacc.getDestin().equals("3")
					&& sacc.getAct().equals("A")
					&& sacc.getCode_b().equals(accBal)
					&& sacc.getPr_nibbd().equals("1")) {
				signRegistr = 1;
			}
		}
		return signRegistr;
	}
	
}	
