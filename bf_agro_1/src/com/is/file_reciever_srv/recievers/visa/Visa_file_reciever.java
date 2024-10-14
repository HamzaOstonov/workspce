package com.is.file_reciever_srv.recievers.visa;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import com.is.ISLogger;
import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.file_reciever_srv.recievers.EMPC.b_file.B_file_reciever;
import com.is.file_reciever_srv.recievers.visa.onus.Onus_reciever;
import com.is.file_reciever_srv.simple.reciever_class.Reciever_class;

public class Visa_file_reciever extends Reciever_class
{
	
//	static String pt="yyyy/MM/dd";
//	
//	private static SimpleDateFormat path_df = 
//		new SimpleDateFormat(pt);
	
	
		
	private static SimpleDateFormat path_df = 
		new SimpleDateFormat(
				"yyyy" + File.separator + "MM" + File.separator + "dd" + File.separator
				);
	
	
	public static boolean isExsistFile(String fileName) throws SQLException{
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		Connection c=null;
		boolean isExsist=false;
		
		try{
		c = ConnectionPool.getConnection();
		ps=c.prepareStatement("select t.file_name from v_empc_files t where t.file_name like ?");
		
		ps.setString(1, "%"+fileName+"%");
		
		rs=ps.executeQuery();
		
		while (rs.next()){
			
			isExsist=true;
		}
		
		}catch(Exception e){
			
			ISLogger.getLogger().error(e);
			e.printStackTrace();
			
		}
				
		finally{
			if(rs!=null){rs.close();};
			if(ps!=null){ps.close();}
			if(c!=null){ConnectionPool.close(c);}
			
		}
		return isExsist;
		
	}
	
	
	@Override
	public void Recieve_file(String input_file, long fr_file_id)
	{
		try{
		//ISLogger.getLogger().error("Recieve file started");
		ISLogger.getLogger().info("EMPC file reciever: Recievers file - "+input_file);
		
		if(isExsistFile(input_file))
		{
			ISLogger.getLogger().info("HUMO файл "+input_file+" существует");
			
			String foundFiles =					
					ConnectionPool.getValue("empc_file") +
                    "foundFiles\\"+
					(input_file.substring(input_file.lastIndexOf(File.separator)+1));
			
			System.out.println("input_file "+input_file);
			System.out.println("foundFiles "+foundFiles);
			(new File(foundFiles)).getParentFile().mkdirs();
			new File(input_file).renameTo(new File(foundFiles));
		    return;
		}
		else{
		Reciever_class bRec_cl = null;
		Reciever_class exptRec_cl = null;
		if(input_file.substring(
				input_file.lastIndexOf(File.separator)+1, 
				input_file.lastIndexOf(File.separator)+2).equals("B")
				)
			bRec_cl = new B_file_reciever();
		
		if(bRec_cl!=null)bRec_cl.Recieve_file(input_file, fr_file_id);
		
		
		ISLogger.getLogger().info("EMPC file reciever: line 94 ");	
		
		if(input_file.substring(
				input_file.lastIndexOf(File.separator)+1, 
				input_file.lastIndexOf(File.separator)+5).equals("onus")
				)
			exptRec_cl = new Onus_reciever();
		
		if(exptRec_cl!=null)
			exptRec_cl.Recieve_file(input_file, fr_file_id);
		
		}} catch (Exception e){
			com.is.ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		}
		
		ISLogger.getLogger().info("EMPC file reciever: line 109 ");
		
	}
	
	public static void move_file_to_archive(String in_file)
	{
		
		String arch_dest="c:/";
	
		try {
			 arch_dest = 
		
			ConnectionPool.getValue("empc_file_arch_dir") + 
			path_df.format(new java.util.Date()) + 
			(in_file.substring(in_file.lastIndexOf(File.separator)+1));
		
		}catch(Exception e){
			ISLogger.getLogger().error("bad arch dir");
		}
		
		
		(new File(arch_dest)).getParentFile().mkdirs();
		if (new File(in_file).renameTo(new File(arch_dest)))
		
		ISLogger.getLogger().info("renamed from "+ in_file + " to " + arch_dest);
		else {
			//System.out.println("delete status: "+new File(in_file).delete());
			
			ISLogger.getLogger().info("delete status: "+new File(in_file).delete());
			ISLogger.getLogger().info(in_file+" deleted");
			}
		
	}

}
