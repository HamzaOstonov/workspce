package com.is.clientcrm.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Date;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.zkoss.zk.ui.Executions;

//import com.is.clients.models.Action;
import com.is.utils.RefData;

public class Util {
	public static boolean inInts(int num,int... numbers) {
		for(int i: numbers){
			if(num == i) {
				return true;
			}
		}
		return false;
	}
	
	
	public static boolean inChars(char charItem,char... chars) {
		for(int i: chars){
			if(charItem == i) {
				return true;
			}
		}
		return false;
	}
	public static boolean inStrings(String str,String... strings) {
		for(String i: strings){
			if(str != null&& str.equals(i)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isNew() {
		HttpSession session = (HttpSession) Executions.getCurrent() .getSession().getNativeSession();
		if (session.getAttribute("isLoggedIn") == null) {
			// Executions.sendRedirect("/");
			return true;
		} else
			return false;	
	}

	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}
        
	public static boolean isEmpty(Boolean bool) {
		return bool == null;
	}

	public static boolean isEmpty(Double db) {
		if (db != null) {
			return db.equals(Double.NaN);
		} else {
			return true;
		}
	}

	public static boolean isEmpty(Date dt) {
		return dt == null;
	}

	public static boolean isEmpty(java.util.Date dt) {
		return dt == null;
	}

	public static boolean isEmpty(Long lg) {
		if (lg != null) {
			return lg == 0;
		} else {
			return true;
		}
	}

	public static boolean isEmpty(int it) {
		return it == 0;
	}
	
	public static java.sql.Date sqlDate(java.util.Date date) {
		return date != null ? new java.sql.Date(date.getTime()) : null;
	}

	public static java.sql.Date d2sql(java.util.Date dt){
		return new java.sql.Date(dt.getTime());
	}
    
	public static String getPstr(Exception ex){
		StringWriter sw = new StringWriter();
		ex.printStackTrace(new PrintWriter(sw));
		return sw.toString();
    }	
        
	public static double FMod(double x, double y) {
		return x - Math.floor(x / y) * y;
	}
        
	public static String F2Money(double d) {
		final double[] T1 = {1000000000000.00, 1000000000.00, 1000000.00, 1000.00, 1.00};
		final String[] T2 = {"триллион", "миллиард", "миллион", "тысяч", "сум"};
		final String[] T3 = {"", "", "", "а", ""};
		final String[] T4 = {"а", "а", "а", "и", "а"};
		final String[] T5 = {"ов", "ов", "ов", "", "ов"};
		final double[] T6 = {900, 800, 700, 600, 500, 400, 300, 200, 100, 90,
							 80, 70, 60, 50, 40, 30, 20, 19, 18, 17, 16, 15,
							 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
		final String[] T7 = {"девятьсот", "восемьсот", "семьсот", "шестьсот", "пятьсот", "четыресто",
				             "тристо", "двести", "сто", "девяносто", "восемьдесят", "семьдесят",
                             "шестьдесят", "пятьдесят", "сорок", "тридцать", "двадцать",
                             "девятнадцать", "восемнадцать", "семнадцать", "шестнадцать",
                             "пятнадцать", "четырнадцать", "тринадцать", "двенадцать",
                             "одиннадцать", "десять", "девять", "восемь", "семь", "шесть",
                             "пять", "четыре", "три", "два", "один"};
		String s = "";
		int i = 0, j = 0, k = 0;
		double n = 0.0, n1 = 0.0, t = 0.0;
		
		if (d > 1000000000000000.00) {
			return Double.toString(d);
		} else {
			n = Math.abs(d);
            k = (int)Math.round(FMod(n, 1) * 100);
            n = Math.floor(n);
            if (d < 0)
              s += "минус ";
            if (n == 0)
              s += "ноль ";
            i = 0;
            while (n > 0)
            {
              if (n >= T1[i])
              {
                t = Math.floor(n / T1[i]);
                n = FMod(n, T1[i]);
                j = 0;
                while (t > 0)
                {
                  if (t >= T6[j])
                  {
                    n1 = t;
                    t = t - T6[j];
                    if (i == 3 && (j == 34 || j == 35))
                    {
                      if (j == 34)
                        s += "две";
                      else
                        s += "одна";
                    }
                    else
                      s += T7[j];
                    s += " ";
                  }
                  j++;
                }
                s += T2[i];
                if (n1 == 1)
                  s += T3[i];
                else if (n1 < 5)
                  s += T4[i];
                else
                  s += T5[i];
                s += " ";
              }
              i++;
            }
            if (i != 5)
              s += "сумов ";
            s += Integer.toString(k) + " тийин.";
          }
          return s = s.substring(0, 1).toUpperCase() + s.substring(1);
	}
        
        
	public static String F2Money(double d, String cur_name, String cur2_name) {
          final double[] T1 = {1000000000000.00, 1000000000.00, 1000000.00, 1000.00, 1.00};
          final String[] T2 = {"триллион", "миллиард", "миллион", "тысяч", cur_name};
          final String[] T3 = {"", "", "", "а", ""};
          final String[] T4 = {"а", "а", "а", "и", "а"};
          final String[] T5 = {"ов", "ов", "ов", "", "ов"};
          final double[] T6 = {900, 800, 700, 600, 500, 400, 300, 200, 100, 90,
                               80, 70, 60, 50, 40, 30, 20, 19, 18, 17, 16, 15,
                               14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
          final String[] T7 = {"девятьсот", "восемьсот", "семьсот", "шестьсот", "пятьсот", "четыресто",
                               "тристо", "двести", "сто", "девяносто", "восемьдесят", "семьдесят",
                               "шестьдесят", "пятьдесят", "сорок", "тридцать", "двадцать",
                               "девятнадцать", "восемнадцать", "семнадцать", "шестнадцать",
                               "пятнадцать", "четырнадцать", "тринадцать", "двенадцать",
                               "одиннадцать", "десять", "девять", "восемь", "семь", "шесть",
                               "пять", "четыре", "три", "два", "один"};
          String s = "";
          int i = 0, j = 0, k = 0;
          double n = 0.0, n1 = 0.0, t = 0.0;

          if (d > 1000000000000000.00)
            return Double.toString(d);
          else
          {
            n = Math.abs(d);
            k = (int)Math.round(FMod(n, 1) * 100);
            n = Math.floor(n);
            if (d < 0)
              s += "минус ";
            if (n == 0)
              s += "ноль ";
            i = 0;
            while (n > 0)
            {
              if (n >= T1[i])
              {
                t = Math.floor(n / T1[i]);
                n = FMod(n, T1[i]);
                j = 0;
                while (t > 0)
                {
                  if (t >= T6[j])
                  {
                    n1 = t;
                    t = t - T6[j];
                    if (i == 3 && (j == 34 || j == 35))
                    {
                      if (j == 34)
                        s += "две";
                      else
                        s += "одна";
                    }
                    else
                      s += T7[j];
                    s += " ";
                  }
                  j++;
                }
                s += T2[i];
                if (n1 == 1)
                  s += T3[i];
                else if (n1 < 5)
                  s += T4[i];
                else
                  s += T5[i];
                s += " ";
              }
              i++;
            }
            if (i != 5)
              s += " " + cur_name;
            s += Integer.toString(k) + " " + cur2_name + ".";
          }
          return s = s.substring(0, 1).toUpperCase() + s.substring(1);
	}
	
	
	public static char[] addElementToArray(char[] org, char added) {
		char[] result = Arrays.copyOf(org, org.length +1);
	    result[org.length] = added;
	    return result;
	}
	public static HashMap<String,String> listToMap(List<RefData> list) {
		HashMap<String,String> map = new HashMap<String, String>();
		for(RefData rd: list) {
			map.put(rd.getData(), rd.getLabel());
		}
		return map;
	}
	public static HashMap<Integer,String> listToMapInt(List<RefData> list) {
		HashMap<Integer,String> map = new HashMap<Integer, String>();
		for(RefData rd: list) {
			map.put(Integer.parseInt(rd.getData()), rd.getLabel());
		}
		return map;
	}
	
//	public static HashMap<Integer,String> actionListToMapInt(int dealId,List<Action> list) {
//		HashMap<Integer,String> map = new HashMap<Integer, String>();
		//for(Action rd: list) {
			//if(rd.getDealId() == dealId){
				//map.put(rd.getId(), rd.getName());
			//}
		//}
		//return map;
	//}
}
