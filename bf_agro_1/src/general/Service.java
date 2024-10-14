package general;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Service
{
	public static General get_general(Long general_id, Connection c) throws SQLException
	{
		General gn = null;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
			ps = c.prepareStatement(
					"select * from general t where t.id = ?");
			ps.setLong(1, general_id);
			rs = ps.executeQuery();
			
			if(rs.next())
			{
				gn = new General(
						rs.getLong   ("id"),               
						rs.getString ("branch"),           
						rs.getString ("doc_num"),          
						rs.getDate   ("d_date"),           
						rs.getString ("bank_cl"),          
						rs.getString ("acc_cl"),           
						rs.getString ("name_cl"),          
						rs.getString ("bank_co"),          
						rs.getString ("acc_co"),           
						rs.getString ("name_co"),          
						rs.getString ("purpose"),          
						rs.getLong   ("summa"),            
						rs.getString ("currency"),         
						rs.getString ("type_doc"),         
						rs.getLong   ("s_deal_id"),        
						rs.getDate   ("v_date"),           
						rs.getString ("pdc"),              
						rs.getString ("cash_code"),        
						rs.getLong   ("state"),            
						rs.getLong   ("parent_group_id"),  
						rs.getLong   ("parent_id"),        
						rs.getLong   ("child_id"),         
						rs.getLong   ("kod_err"),          
						rs.getString ("file_name"),        
						rs.getLong   ("err_general"),      
						rs.getLong   ("emp_id"),           
						rs.getLong   ("id_transh"),        
						rs.getLong   ("id_transh_purp"),   
						rs.getDate   ("val_date"),
						3l
						);
			}
		}
		catch(SQLException e)
		{
			throw e;
		}
		finally
		{
			try{if(rs!=null)rs.close();}catch(Exception e){}
			try{if(ps!=null)ps.close();}catch(Exception e){}
		}
		
		return gn;
	}
}
