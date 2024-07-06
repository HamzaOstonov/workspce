package com.is.customer;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import com.is.ConnectionPool;
//import com.is.customer_.core.ConnectionUtils;
import com.is.customer.PhysicalAutoCompleteModel;
//import com.is.customer_.core.model.SessionAttributes;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.impl.InputElement;
import com.is.LtLogger;
import com.is.utils.CheckNull;


public class CustomerUtils {

	private static final SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	private static final String EMPTY_STRING = "";
    private static SimpleDateFormat dfM = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    private static final char[][] charTable = new char[65536][];

    static
    {
    	charTable['А'] = "A".toCharArray();   
    	charTable['Б'] = "B".toCharArray();   
    	charTable['В'] = "V".toCharArray();   
    	charTable['Г'] = "G".toCharArray();   
    	charTable['Д'] = "D".toCharArray();   
    	charTable['Е'] = "E".toCharArray();   
    	charTable['Ё'] = "E".toCharArray();   
    	charTable['Ж'] = "J".toCharArray();   
    	charTable['З'] = "Z".toCharArray();   
    	charTable['И'] = "I".toCharArray();   
    	charTable['Й'] = "Y".toCharArray();   
    	charTable['К'] = "K".toCharArray();   
    	charTable['Л'] = "L".toCharArray();   
    	charTable['М'] = "M".toCharArray();   
    	charTable['Н'] = "N".toCharArray();   
    	charTable['О'] = "O".toCharArray();   
    	charTable['П'] = "P".toCharArray();   
    	charTable['Р'] = "R".toCharArray();   
    	charTable['С'] = "S".toCharArray();   
    	charTable['Т'] = "T".toCharArray();   
    	charTable['У'] = "U".toCharArray();   
    	charTable['Ф'] = "F".toCharArray();   
    	charTable['Х'] = "X".toCharArray();   
    	charTable['Ц'] ="TS".toCharArray();   
    	charTable['Ч'] ="CH".toCharArray();   
    	charTable['Ш'] ="SH".toCharArray();   
    	charTable['Щ'] ="SH".toCharArray();   
    	charTable['Ъ'] = "'".toCharArray();   
    	charTable['Ы'] = "Y".toCharArray();   
    	charTable['Ъ'] = "'".toCharArray();   
    	charTable['Э'] = "E".toCharArray();   
    	charTable['Ю'] ="YU".toCharArray();   
    	charTable['Я'] ="YA".toCharArray();   
    	charTable['а'] = "a".toCharArray();   
    	charTable['б'] = "b".toCharArray();   
    	charTable['в'] = "v".toCharArray();   
    	charTable['г'] = "g".toCharArray();   
    	charTable['д'] = "d".toCharArray();   
    	charTable['е'] = "e".toCharArray();   
    	charTable['ё'] = "e".toCharArray();   
    	charTable['ж'] = "j".toCharArray();   
    	charTable['з'] = "z".toCharArray();   
    	charTable['и'] = "i".toCharArray();   
    	charTable['й'] = "y".toCharArray();   
    	charTable['к'] = "k".toCharArray();   
    	charTable['л'] = "l".toCharArray();   
    	charTable['м'] = "m".toCharArray();   
    	charTable['н'] = "n".toCharArray();   
    	charTable['о'] = "o".toCharArray();   
    	charTable['п'] = "p".toCharArray();   
    	charTable['р'] = "r".toCharArray();   
    	charTable['с'] = "s".toCharArray();   
    	charTable['т'] = "t".toCharArray();   
    	charTable['у'] = "u".toCharArray();   
    	charTable['ф'] = "f".toCharArray();   
    	charTable['х'] = "x".toCharArray();   
    	charTable['ц'] ="ts".toCharArray();   
    	charTable['ч'] ="ch".toCharArray();   
    	charTable['ш'] ="sh".toCharArray();   
    	charTable['щ'] ="sh".toCharArray();   
    	charTable['ъ'] = "'".toCharArray();   
    	charTable['ы'] = "y".toCharArray();   
    	charTable['ъ'] = "'".toCharArray();   
    	charTable['э'] = "e".toCharArray();   
    	charTable['ю'] ="yu".toCharArray();   
    	charTable['я'] ="ya".toCharArray();   

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

	public static String decode(String sql, String key, String alias) throws SQLException {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String output = null;
		try {
			
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement(sql);
			ps.setString(1, key);

			rs = ps.executeQuery();
			while (rs.next())
				output = rs.getString(1);
		} catch (SQLException e) {
			throw e;
		} finally {
			closePStatement(ps);
			closeResultSet(rs);
			ConnectionPool.close(c);
		}
		return output;
	}

	public static Date getOperDate(String alias) throws SQLException {
		Date date = null;
		Connection c = null;
		CallableStatement cs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			cs = c.prepareCall("{? = call info.getday()}");
			cs.registerOutParameter(1, java.sql.Types.DATE);
			cs.executeUpdate();
			date = cs.getDate(1);
		} catch (SQLException e) {
			LtLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			closeStatement(cs);
			ConnectionPool.close(c);
		}
		return date;
	}
	
	public static String getBankType(String branch, String alias){
		String bankType = null;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select bank_type from s_mfo where bank_id = ?");
			ps.setString(1, branch);
			rs = ps.executeQuery();
			if(rs.next()){
				bankType = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LtLogger.getLogger().error(CheckNull.getPstr(e));
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
	
	public static PhysicalAutoCompleteModel getCustomerModel(Long id, String alias){
		PhysicalAutoCompleteModel customer = null;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select * from SSI_PHYSICAL where id = ? order by Inquire desc");
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				if(rs.getString("code")!=null&&rs.getString("code").equals("02000")){
					customer = new PhysicalAutoCompleteModel();
					customer.setBirth_country(rs.getString("birth_country"));
					customer.setBirth_country_name(rs.getString("birth_country_name"));
					customer.setBirth_date(rs.getDate("birth_date"));
					customer.setBirth_place(rs.getString("birth_place"));
					customer.setCitizenship(rs.getString("citizenship"));
					customer.setCitizenship_name(rs.getString("citizenship_name"));
					customer.setDate_expiry(rs.getDate("date_expiry"));
					customer.setDate_issue(rs.getDate("date_issue"));
					customer.setDomicile_address(rs.getString("domicile_address"));
					customer.setDomicile_begin(rs.getString("domicile_begin"));
					customer.setDomicile_block(rs.getString("domicile_block"));
					customer.setDomicile_country(rs.getString("domicile_country"));
					customer.setDomicile_district(rs.getString("domicile_district"));
					customer.setDomicile_flat(rs.getString("domicile_flat"));
					customer.setDomicile_house(rs.getString("domicile_house"));
					customer.setDomicile_place_desc(rs.getString("domicile_place_desc"));
					customer.setDomicile_region(rs.getString("domicile_region"));
					customer.setDomicile_street_desc(rs.getString("domicile_street_desc"));
					customer.setFirst_name(rs.getString("first_name"));
					customer.setGive_place(rs.getString("give_place"));
					customer.setGive_place_name(rs.getString("give_place_name"));
					customer.setGivenname(rs.getString("givenname"));
					customer.setInn(rs.getString("inn"));
					customer.setInn_registrated(rs.getString("inn_registrated"));
					customer.setInn_registrated_gni(rs.getString("inn_registrated_gni"));
					customer.setLast_name(rs.getString("last_name"));
					customer.setNationality(rs.getString("nationality"));
					customer.setNationality_desc(rs.getString("nationality_desc"));
					customer.setPassport_number(rs.getString("passport_number"));
					customer.setPassport_seria(rs.getString("passport_seria"));
					customer.setPatronym(rs.getString("patronym"));
					customer.setPin(rs.getString("pin"));
					customer.setSex(rs.getInt("sex"));
					customer.setSubject_state(rs.getString("subject_state"));
					customer.setSurname(rs.getString("surname"));
					customer.setTemp_address(rs.getString("temp_address"));
					customer.setTemp_begin(rs.getString("temp_begin"));
					customer.setTemp_block(rs.getString("temp_block"));
					customer.setTemp_country(rs.getString("temp_country"));
					customer.setTemp_district(rs.getString("temp_district"));
					customer.setTemp_end(rs.getString("temp_end"));
					customer.setTemp_flat(rs.getString("temp_flat"));
					customer.setTemp_house(rs.getString("temp_house"));
					customer.setTemp_place_desc(rs.getString("temp_place_desc"));
					customer.setTemp_region(rs.getString("temp_region"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LtLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			closeResultSet(rs);
			closePStatement(ps);
			ConnectionPool.close(c);
		}
		return customer;
	}
	
	public static Long getNextId(String alias){
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Long id = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("SELECT SEQ_SSI_PHYSICAL.NEXTVAL id FROM DUAL");
			rs = ps.executeQuery();
			if(rs.next()) id = rs.getLong(1);
		} catch (Exception e) {
			e.printStackTrace();
			LtLogger.getLogger().error(CheckNull.getPstr(e));
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

	public static PhysicalAutoCompleteModel getSomeData(String soato, String alias){
		PhysicalAutoCompleteModel temp = null;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select r.region_nam||' '||d.distr_name region_distr_give_place, s.kod_gni from s_soato s, s_region r, s_distr d where s.kod_soat=? and s.act='A' and r.region_id=s.region_id and r.act='A' and d.distr=s.distr and d.act='A'");
			ps.setString(1, soato);
			rs = ps.executeQuery();
			if(rs.next()){
				
					temp = new PhysicalAutoCompleteModel();
					temp.setGive_place(rs.getString("region_distr_give_place"));
					temp.setInn_registrated_gni(rs.getString("kod_gni"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			LtLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			closeResultSet(rs);
			closePStatement(ps);
			ConnectionPool.close(c);
		}
		return temp;
	}
	
	public static boolean isEmergencyModeOn(String alias){
		String setting = ConnectionPool.getValue("SAP_IS_ON", alias);
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

    public static Date decodeDate(String sql, String key, String alias) throws SQLException {
        Connection c = null;
        Date output = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
        	c = ConnectionPool.getConnection(alias);
            ps = c.prepareStatement(sql);
            ps.setString(1, key);

            rs = ps.executeQuery();
            while (rs.next())
                output = rs.getDate(1);

        } catch (SQLException e) {
            throw e;
        } finally {
            closeResultSet(rs);
			closeStatement(ps);
			ConnectionPool.close(c);
        }
        return output;
    }
    
    public static void closeResultSet(ResultSet resultSet) {
        try {
            if (resultSet != null)
                resultSet.close();
        } catch (SQLException e) {
            LtLogger.getLogger().error(CheckNull.getPstr(e));
        }
    }

    public static void closeStatement(Statement statement) {
        try {
            if (statement != null)
                statement.close();
        } catch (SQLException e) {
            LtLogger.getLogger().error(CheckNull.getPstr(e));
        }
    }
    
    public static void closePStatement(PreparedStatement statement) {
        try {
            if (statement != null)
                statement.close();
        } catch (SQLException e) {
            LtLogger.getLogger().error(CheckNull.getPstr(e));
        }
    }
    
    public static void closeCStatement(CallableStatement statement) {
        try {
            if (statement != null)
                statement.close();
        } catch (SQLException e) {
            LtLogger.getLogger().error(CheckNull.getPstr(e));
        }
    }
    
}
