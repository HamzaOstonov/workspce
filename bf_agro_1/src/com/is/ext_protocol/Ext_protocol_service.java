package com.is.ext_protocol;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.file_reciever.simple.Ext_out_file;

public class Ext_protocol_service
{
	public static List<Ext_protocol> get_protocols(long deal_group_id, 
			long s_deal_id, long obj_id)
	{
		List<Ext_protocol> res = new ArrayList<Ext_protocol>();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{//and t.s_deal_id = ? 
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select t.deal_group_id, t.s_deal_id, " +
					"t.obj_id, t.v_date, t.text, t.message_id, bn.name message_text " +
					"from ext_protocols t, ss_lang_base_names bn " +
					"where t.deal_group_id = ? " +
					"and t.obj_id = ? and bn.id(+) = t.message_id order by t.v_date desc");
			ps.setLong(1, deal_group_id);
			//ps.setLong(2, s_deal_id);
			ps.setLong(2, obj_id);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				res.add(new Ext_protocol(
						deal_group_id, 
						s_deal_id, 
						obj_id, 
						rs.getTimestamp("v_date"), 
						rs.getString("text"), 
						rs.getLong("message_id"), 
						rs.getString("message_text")
						));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		}
		finally
		{
			try{if(rs!=null)rs.close();}catch(Exception e){}
			try{if(ps!=null)ps.close();}catch(Exception e){}
			try{if(c!=null)c.close();}catch(Exception e){}
		}
		return res;
	}
}
