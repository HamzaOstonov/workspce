package com.is.tieto_visae.customer;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.is.ConnectionPool;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.impl.InputElement;

import com.is.ISLogger;
import com.is.utils.CheckNull;

public class CustomerUtils {
	private static final SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	private static final String EMPTY_STRING = "";
    private static SimpleDateFormat dfM = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    private static final char[][] charTable = new char[65536][];

    static
    {
    	charTable['�'] = "A".toCharArray();   
    	charTable['�'] = "B".toCharArray();   
    	charTable['�'] = "V".toCharArray();   
    	charTable['�'] = "G".toCharArray();   
    	charTable['�'] = "D".toCharArray();   
    	charTable['�'] = "E".toCharArray();   
    	charTable['�'] = "E".toCharArray();   
    	charTable['�'] = "J".toCharArray();   
    	charTable['�'] = "Z".toCharArray();   
    	charTable['�'] = "I".toCharArray();   
    	charTable['�'] = "Y".toCharArray();   
    	charTable['�'] = "K".toCharArray();   
    	charTable['�'] = "L".toCharArray();   
    	charTable['�'] = "M".toCharArray();   
    	charTable['�'] = "N".toCharArray();   
    	charTable['�'] = "O".toCharArray();   
    	charTable['�'] = "P".toCharArray();   
    	charTable['�'] = "R".toCharArray();   
    	charTable['�'] = "S".toCharArray();   
    	charTable['�'] = "T".toCharArray();   
    	charTable['�'] = "U".toCharArray();   
    	charTable['�'] = "F".toCharArray();   
    	charTable['�'] = "X".toCharArray();   
    	charTable['�'] ="TS".toCharArray();   
    	charTable['�'] ="CH".toCharArray();   
    	charTable['�'] ="SH".toCharArray();   
    	charTable['�'] ="SH".toCharArray();   
    	charTable['�'] = "'".toCharArray();   
    	charTable['�'] = "Y".toCharArray();   
    	charTable['�'] = "'".toCharArray();   
    	charTable['�'] = "E".toCharArray();   
    	charTable['�'] ="YU".toCharArray();   
    	charTable['�'] ="YA".toCharArray();   
    	charTable['�'] = "a".toCharArray();   
    	charTable['�'] = "b".toCharArray();   
    	charTable['�'] = "v".toCharArray();   
    	charTable['�'] = "g".toCharArray();   
    	charTable['�'] = "d".toCharArray();   
    	charTable['�'] = "e".toCharArray();   
    	charTable['�'] = "e".toCharArray();   
    	charTable['�'] = "j".toCharArray();   
    	charTable['�'] = "z".toCharArray();   
    	charTable['�'] = "i".toCharArray();   
    	charTable['�'] = "y".toCharArray();   
    	charTable['�'] = "k".toCharArray();   
    	charTable['�'] = "l".toCharArray();   
    	charTable['�'] = "m".toCharArray();   
    	charTable['�'] = "n".toCharArray();   
    	charTable['�'] = "o".toCharArray();   
    	charTable['�'] = "p".toCharArray();   
    	charTable['�'] = "r".toCharArray();   
    	charTable['�'] = "s".toCharArray();   
    	charTable['�'] = "t".toCharArray();   
    	charTable['�'] = "u".toCharArray();   
    	charTable['�'] = "f".toCharArray();   
    	charTable['�'] = "x".toCharArray();   
    	charTable['�'] ="ts".toCharArray();   
    	charTable['�'] ="ch".toCharArray();   
    	charTable['�'] ="sh".toCharArray();   
    	charTable['�'] ="sh".toCharArray();   
    	charTable['�'] = "'".toCharArray();   
    	charTable['�'] = "y".toCharArray();   
    	charTable['�'] = "'".toCharArray();   
    	charTable['�'] = "e".toCharArray();   
    	charTable['�'] ="yu".toCharArray();   
    	charTable['�'] ="ya".toCharArray();   

        //for (int i = 0; i < charTable.length; i++)
        //{
        //    char idx = (char) i;
        //    char lower = Character.toLowerCase(idx);
        //    if (charTable[i] != null)
        //    {
        //        charTable[lower] = toLowerCase(charTable[i]);
        //    }
        //}
    }

    public static boolean isEmpty(String[] src) {
		if (src == null || src[0] == null || src[0].isEmpty())
			return true;
		return false;
	}

	public static boolean isEmpty(String src) {
		if (src == null || src.isEmpty())
			return true;
		return false;
	}

	public static void serialize(Object f, String fileName) {
		try {
			write(f, fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void write(Object f, String filename) throws Exception {
		OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filename));
		XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filename)));
		encoder.writeObject(f);
		encoder.close();
		outputStream.close();
	}

	public static String dateToString(Date date) {
		return date != null ? df.format(date) : EMPTY_STRING;
	}

	public static String dateToStringWithMinutes(Date date){
	    return date != null ? dfM.format(date) : EMPTY_STRING;
    }

	public static Date stringToDate(String src) throws ParseException {
		return df.parse(src);
	}

	public static Calendar toCalendar(Date date){
	    if (date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            return cal;
        }
        return null;
    }

    public static Date toDate(Calendar calendar){
	    if (calendar != null)
	        return calendar.getTime();
	    return null;
    }

	
	public static String getBankType(String branch){
		String bankType = null;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select bank_type from s_mfo where bank_id = ?");
			ps.setString(1, branch);
			rs = ps.executeQuery();
			if(rs.next()){
				bankType = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				ConnectionPool.close(c);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return bankType;
	}
	
	public static Long getNextId(){
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Long id = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SEQ_SSI_PHYSICAL.NEXTVAL id FROM DUAL");
			rs = ps.executeQuery();
			if(rs.next()) id = rs.getLong(1);
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				ConnectionPool.close(c);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return id;
	}

	public static boolean isEmergencyModeOn(){
		String setting = ConnectionPool.getValue("SAP_IS_ON");
		return setting != null && setting.equals("1") ? true : false;
        //return true;
	}

	@SuppressWarnings("unchecked")
	public static void clearConstraintsRecursively(Component comp) {
		if (comp.getChildren().size() == 0) {
			if (comp instanceof InputElement) {
				((InputElement) comp).setConstraint("");
				return;
			}
		}
		List<Component> children = comp.getChildren();
		for (Component component : children) {
			clearConstraintsRecursively(component);
		}
	}

   
    public static String toTranslitNew(String text)
    {
        StringBuilder sb = new StringBuilder(text.length());
        for (int i = 0; i < text.length(); i++)
        {
            char[] replace = charTable[text.charAt(i)];
            if (replace == null)
            {
                sb.append(text.charAt(i));
            }
            else
            {
                sb.append(replace);
            }
        }
        return sb.toString();
    }
}
