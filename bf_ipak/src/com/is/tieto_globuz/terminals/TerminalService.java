package com.is.tieto_globuz.terminals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.RefData;
import com.is.utils.RefDataService;
import com.is.utils.Res;

public class TerminalService
{
	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql = "select * from BF_GLOBUZ_ACC_TR_ALL ";
	private static Res res = new Res();//123
	
	public static List<RefData> getMerchantId(String alias)
	{
		return com.is.utils.RefDataService.getRefData("select t.merchant data, SUBSTR(merchant, 6, 10) label from BF_GLOBUZ_MERCHANTS_ALL t", alias);
	}
	
	public static List<RefData> getTerminalKinds(String alias)
	{
		return com.is.utils.RefDataService.getRefData("select t.type data, t.label label from ss_empc_terminal_kind t", alias);
	}
	
	public static List<RefData> getSsPos(String alias) {
		return com.is.utils.RefDataService.getRefData("select t.type data, t.label label from ss_empc_pos t", alias);
	}
	
	public static List<RefData> getSsBankCashbox(String alias) {
		return com.is.utils.RefDataService.getRefData("select t.type data, t.label label from ss_empc_bank_cashbox t", alias);
	}
	public static List<RefData> getSsPinAssignment(String alias) {
		return com.is.utils.RefDataService.getRefData("select t.type data, t.label label from ss_empc_pin_assignment t", alias);
	}
	
	
	
	
	public static List<RefData> getTerminalModel(String alias) {
		return com.is.utils.RefDataService.getRefData("select t.model data, t.model_name label from ss_empc_terminal_model t", alias);
	}
	public static List<RefData> getConnectionInt(String alias) {
		return com.is.utils.RefDataService.getRefData("select t.combination data, t.connection_name label from ss_empc_connection_int t", alias);
	}
	public static List<RefData> getConnectionHost(String alias) {
		return com.is.utils.RefDataService.getRefData("select t.combination data, t.connection_host_name label from ss_empc_connection_host t", alias);
	}
	public static List<RefData> getTmcServer(String alias) {
		return com.is.utils.RefDataService.getRefData("select t.combination data, t.server_name label from ss_empc_tmc_server t", alias);
	}
	public static List<RefData> getTmcHost(String alias) {
		return com.is.utils.RefDataService.getRefData("select t.combination data, t.connection_name label from ss_empc_tmc_host t", alias);
	}
	public static List<RefData> getNotesTermType(String alias) {
		return com.is.utils.RefDataService.getRefData("select t.combination data, t.terminal_type label from ss_empc_notes_term_type t", alias);
	}
	public static List<RefData> getApplicationType(String alias) {
		return com.is.utils.RefDataService.getRefData("select t.application_type data, t.type_name label from ss_empc_application_type t", alias);
	}
	
	private static String getNextVal(String curVal) 
	{
		//получает текущий последний номер (0001-0009-000A-000Z-ZZZZ), генерит следующий
		
		
		String nextVal;
		char[] arrayChar = new char[4];
		
		if(curVal.equals(""))
		{
			curVal = "0001";
			return curVal;
		}
		
		if(curVal.equals("ZZZZ"))
		{
			curVal = "";
			return curVal;
		}
			
		for(int i = 0; i <= 3; ++i)
		{
			arrayChar[i] = curVal.charAt(i);
		}
				
		
		for(int i = 3; i >= 0; i--)
		{
			 if(arrayChar[i] >= 48 && arrayChar[i] <= 56 || arrayChar[i] >= 65 && arrayChar[i] <= 89)
			 {
				 arrayChar[i]++;
				 break;
			 }
			 else if(arrayChar[i] == 57)
			 {
				 arrayChar[i] = 65;
				 break;
			 }
			 else
			 {
				 arrayChar[i] = 48;
			 }
		}
						
		nextVal = String.valueOf(arrayChar);
		
		return nextVal;
	}
	
	public static Res genTerminalId(String alias, String termKind, String handbookValue)
	{
		Connection c = null;
		String terminal_id, mfoCode = "", curVal = "", nextVal = "";
		try
		{
			c = ConnectionPool.getConnection(alias);
			String mfo = ConnectionPool.getValue("EMPC_BRANCH_ID", alias);
			String sql = "select t.mfo_code from BF_GLOBUZ_MFO t where t.mfo = '" + mfo + "'";
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery(sql);
			while (rs.next())
			{
				mfoCode = rs.getString("mfo_code");
			}
			rs.close();
			
			
			sql = "SELECT id, terminal_id FROM (SELECT id, terminal_id FROM (SELECT id, terminal_id FROM BF_GLOBUZ_ACC_TR_ALL WHERE SUBSTR(terminal_id, 1, 2) = '" + mfoCode + "') WHERE SUBSTR(terminal_id, 3, 1) = '" + termKind + "') WHERE SUBSTR(terminal_id, 4, 1) = '" + handbookValue + "' ORDER BY ID DESC";
			ps = c.prepareStatement(sql);
			rs = ps.executeQuery(sql);
			if(rs.next())
			{
				curVal = rs.getString("terminal_id").substring(4);
			}
			else
			{
				curVal = "";
			}			
			
			nextVal = getNextVal(curVal);
			
			if(nextVal.equals(""))
			{
				res.setCode(0);
				res.setName("Максимально предусмотренное количество терминалов уже зарегистрировано.");
				return res;			
			}
			
			terminal_id = mfoCode + termKind + handbookValue + nextVal;
			
			if (terminal_id.length() != 8)
			{
				res.setCode(0);
				res.setName("Invalid length of terminal_id...");
				return res;
			}
			
			res.setCode(1);
			res.setName(terminal_id);
		}
		catch (SQLException e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
			res.setCode(0);
			res.setName(e.getLocalizedMessage());
		}
		finally
		{
			ConnectionPool.close(c);
		}
		
		return res;
	}
	
	public static List<Terminal> getTerminal()
	{
		List<Terminal> list = new ArrayList<Terminal>();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(msql);
			while (rs.next())
			{
				list.add(new Terminal(
					rs.getString("terminal_id"),
					rs.getString("acceptor_id"),
					rs.getString("term_type"),
					rs.getString("point_code"),
					rs.getString("install_date"),
					rs.getString("status"),
					rs.getString("serial_nr"),
					rs.getString("inv_nr"),
					rs.getString("notes"),
					rs.getString("action"),
					rs.getString("acc")
					));
			}
		}
		catch (SQLException e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return list;
		
	}
	
	public static List<Terminal> getTerminal4Send()
	{
		List<Terminal> list = new ArrayList<Terminal>();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("select * from BF_GLOBUZ_ACC_TR_ALL a where a.action in ('I', 'U')");
			while (rs.next())
			{
				list.add(new Terminal(
					rs.getString("terminal_id"),
					rs.getString("acceptor_id"),
					rs.getString("term_type"),
					rs.getString("point_code"),
					rs.getString("install_date"),
					rs.getString("status"),
					rs.getString("serial_nr"),
					rs.getString("inv_nr"),
					rs.getString("notes"),
					rs.getString("action"),
					rs.getString("acc")
					));
			}
		}
		catch (SQLException e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return list;
		
	}
	
	private static String getCond(List<FilterField> flfields)
	{
		if (flfields.size() > 0)
		{
			return " and ";
		}
		else return " where ";
	}
	
	private static List<FilterField> getFilterFields(TerminalFilter filter)
	{
		List<FilterField> flfields = new ArrayList<FilterField>();
		
		if (!CheckNull.isEmpty(filter.getTerminal_id()))
		{
			flfields.add(new FilterField(getCond(flfields) + "terminal_id=?", filter.getTerminal_id()));
		}
		if (!CheckNull.isEmpty(filter.getAcceptor_id()))
		{
			flfields.add(new FilterField(getCond(flfields) + "acceptor_id=?", filter.getAcceptor_id()));
		}
		if (!CheckNull.isEmpty(filter.getTerm_type()))
		{
			flfields.add(new FilterField(getCond(flfields) + "term_type=?", filter.getTerm_type()));
		}
		if (!CheckNull.isEmpty(filter.getPoint_code()))
		{
			flfields.add(new FilterField(getCond(flfields) + "point_code=?", filter.getPoint_code()));
		}
		if (!CheckNull.isEmpty(filter.getInstall_date()))
		{
			flfields.add(new FilterField(getCond(flfields) + "install_date=?", filter.getInstall_date()));
		}
		if (!CheckNull.isEmpty(filter.getStatus()))
		{
			flfields.add(new FilterField(getCond(flfields) + "status=?", filter.getStatus()));
		}
		if (!CheckNull.isEmpty(filter.getSerial_nr()))
		{
			flfields.add(new FilterField(getCond(flfields) + "serial_nr=?", filter.getSerial_nr()));
		}
		if (!CheckNull.isEmpty(filter.getInv_nr()))
		{
			flfields.add(new FilterField(getCond(flfields) + "inv_nr=?", filter.getInv_nr()));
		}
		if (!CheckNull.isEmpty(filter.getNotes()))
		{
			flfields.add(new FilterField(getCond(flfields) + "notes=?", filter.getNotes()));
		}
		
		// flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));
		
		return flfields;
	}
	
	public static int getCount(TerminalFilter filter)
	{
		
		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM BF_GLOBUZ_ACC_TR_ALL ");
		if (flFields.size() > 0)
		{
			
			for (int i = 0; i < flFields.size(); i++)
			{
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement(sql.toString());
			
			for (int k = 0; k < flFields.size(); k++)
			{
				ps.setObject(k + 1, flFields.get(k).getColobject());
			}
			ResultSet rs = ps.executeQuery();
			
			if (rs.next())
			{
				n = rs.getInt(1);
			}
		}
		catch (SQLException e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();			
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return n;
		
	}
	
	public static List<Terminal> getTerminalsFl(int pageIndex, int pageSize, TerminalFilter filter)
	{
		
		List<Terminal> list = new ArrayList<Terminal>();
		Connection c = null;
		int v_lowerbound = pageIndex + 1;
		int v_upperbound = v_lowerbound + pageSize - 1;
		int params;
		List<FilterField> flFields = getFilterFields(filter);
		
		StringBuffer sql = new StringBuffer();
		sql.append(psql1);
		sql.append(msql);
		if (flFields.size() > 0)
		{
			
			for (int i = 0; i < flFields.size(); i++)
			{
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		sql.append(psql2);
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement(sql.toString());
			for (params = 0; params < flFields.size(); params++)
			{
				ps.setObject(params + 1, flFields.get(params).getColobject());
			}
			params++;
			ps.setInt(params++, v_upperbound);
			ps.setInt(params++, v_lowerbound);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				list.add(new Terminal(
								rs.getString("terminal_id"),
								rs.getString("acceptor_id"),
								rs.getString("term_type"),
								rs.getString("point_code"),
								rs.getString("install_date"),
								rs.getString("status"),
								rs.getString("serial_nr"),
								rs.getString("inv_nr"),
								rs.getString("notes"),
								rs.getString("action"),
								rs.getString("acc")
								));
			}
		}
		catch (SQLException e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();			
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return list;
		
	}
	
	public Terminal getTerminal(int terminalId)
	{
		
		Terminal terminal = new Terminal();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM BF_GLOBUZ_ACC_TR_ALL WHERE terminal_id=?");
			ps.setInt(1, terminalId);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				terminal = new Terminal();
				
				terminal.setTerminal_id(rs.getString("terminal_id"));
				terminal.setAcceptor_id(rs.getString("acceptor_id"));
				terminal.setTerm_type(rs.getString("term_type"));
				terminal.setPoint_code(rs.getString("point_code"));
				terminal.setInstall_date(rs.getString("install_date"));
				terminal.setStatus(rs.getString("status"));
				terminal.setSerial_nr(rs.getString("serial_nr"));
				terminal.setInv_nr(rs.getString("inv_nr"));
				terminal.setNotes(rs.getString("notes"));
				terminal.setAction(rs.getString("action"));
				terminal.setAcc(rs.getString("acc"));
			}
		}
		catch (Exception e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return terminal;
	}
	
	public static Res create(Terminal terminal)
	{
		Connection c = null;
		PreparedStatement ps = null;
		try
		{
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("INSERT INTO BF_GLOBUZ_ACC_TR_ALL (id, terminal_id, acceptor_id, term_type, point_code, install_date, status, serial_nr, inv_nr	, notes, action, trdt, acc) VALUES (seq_BF_GLOBUZ_ACC_TR_ALL.nextval, ?,?,?,?,?,?,?,?,?,?,?,?)");
					
			ps.setString(1, terminal.getTerminal_id());
			ps.setString(2, terminal.getAcceptor_id());
			ps.setString(3, terminal.getTerm_type());
			ps.setString(4, terminal.getPoint_code());
			ps.setString(5, terminal.getInstall_date());
			ps.setString(6, terminal.getStatus());
			ps.setString(7, terminal.getSerial_nr());
			ps.setString(8, terminal.getInv_nr());
			ps.setString(9, terminal.getNotes());
			ps.setString(10, terminal.getAction());
			ps.setDate(11, new java.sql.Date(new Date().getTime()));
			ps.setString(12, terminal.getAcc());
			
			ps.executeUpdate();
			c.commit();
			res.setCode(1);
			res.setName("");
		}
		catch (Exception e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
			res.setCode(0);
			res.setName(e.getLocalizedMessage());
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return res;
	}
	
	public static Res update(Terminal terminal)
	{
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("UPDATE BF_GLOBUZ_ACC_TR_ALL SET terminal_id=?, acceptor_id=?, term_type=?, point_code=?, install_date=?, status	=?, serial_nr=?, inv_nr	=?, notes	=?, action = ?, acc=?  WHERE terminal_id=?");
			
			ps.setString(1, terminal.getTerminal_id());
			ps.setString(2, terminal.getAcceptor_id());
			ps.setString(3, terminal.getTerm_type());
			ps.setString(4, terminal.getPoint_code());
			ps.setString(5, terminal.getInstall_date());
			ps.setString(6, terminal.getStatus());
			ps.setString(7, terminal.getSerial_nr());
			ps.setString(8, terminal.getInv_nr());
			ps.setString(9, terminal.getNotes());
			ps.setString(10, terminal.getAction());
			ps.setString(11, terminal.getAcc());
			ps.setString(12, terminal.getTerminal_id());
			ps.executeUpdate();
			c.commit();
			res.setCode(1);
			res.setName("");
		}
		catch (SQLException e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
			res.setCode(0);
			res.setName(e.getLocalizedMessage());
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return res;
	}
	
	public static void remove(Terminal terminal)
	{
		
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("DELETE FROM BF_GLOBUZ_ACC_TR_ALL WHERE terminal_id=?");
			ps.setString(1, terminal.getTerminal_id());
			ps.executeUpdate();
			c.commit();
			res.setCode(1);
			res.setName("");
		}
		catch (Exception e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
			res.setCode(0);
			res.setName(e.getLocalizedMessage());
		}
		finally
		{
			ConnectionPool.close(c);
		}
	}
	
	public static Res updateSts(List<Terminal> terminals, String FileName, String action)
	{
		Connection c = null;
		PreparedStatement ps;
		
		try
		{
			c = ConnectionPool.getConnection();
			for (int i = 0; i < terminals.size(); i++)
			{
				ps = c.prepareStatement("UPDATE BF_GLOBUZ_ACC_TR_ALL SET action=?, filename=?, file_date=? " +
						"WHERE terminal_id=? and acceptor_id=? ");
				
				ps.setString(1, action);
				ps.setString(2, FileName);
				ps.setTimestamp(3, new java.sql.Timestamp(new Date().getTime()));
				ps.setString(4, terminals.get(i).getTerminal_id());
				ps.setString(5, terminals.get(i).getAcceptor_id());
				
				ps.executeUpdate();
				c.commit();
			}
			res.setCode(1);
			res.setName(FileName + " sent");
		}
		catch (SQLException e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
			res.setCode(0);
			res.setName(e.getMessage());			
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return res;
	}

	
	
	public static List<RefData> getTerminalTypes(String alias) {
	    return RefDataService.getRefData(
	    	"select type data, label label from ss_empc_terminal_type", alias);
	}
	
	public static List<RefData> getTerminalStatus(String alias) {
	    return RefDataService.getRefData(
	    	"select status data, label label from ss_empc_terminal_status", alias);
	}
	
	
	public static String getPointCode(String terminalType) {
		String pointCode = null;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try	{
			c = ConnectionPool.getConnection();
			
			ps = c.prepareStatement("SELECT point_code FROM ss_empc_point_code WHERE terminal_type = ?");
			ps.setString(1, terminalType);			
			rs = ps.executeQuery();	
			
			if(rs.next()) {
				pointCode = rs.getString("point_code");
			}
		}
		catch (SQLException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));		
		}
		finally	{
			ConnectionPool.close(c);
		}
		
		return pointCode;
	}
}
