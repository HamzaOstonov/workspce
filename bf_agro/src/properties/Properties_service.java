package properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Properties_service
{
	private static List<Long> file_types_to_preceed = null;
	private static List<String> handlers_to_proceed = null;
	private static List<Long> senders_to_proceed = null;
	
	private static void init() throws Exception
	{
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(System.getProperty("catalina.base")+
					"\\conf\\file-reciever.properties");
			prop.load(input);

			file_types_to_preceed = new ArrayList<Long>();
			
			if (prop.getProperty("files_types_to_recieve") == null )
				throw new Property_not_found_exception(
						"property 'files_types_to_recieve' not found in "+
						System.getProperty("catalina.base")+
						"\\conf\\file-reciever.properties");
			
			if (prop.getProperty("files_types_to_recieve").length() > 0)
			for (String retval: prop.getProperty("files_types_to_recieve").split(", "))
			{
				file_types_to_preceed.add(Long.parseLong(retval));
		    }
			
			handlers_to_proceed = new ArrayList<String>();
			if (prop.getProperty("handlers_to_run") == null )
				throw new Property_not_found_exception(
						"property 'handlers_to_run' not found in "+
						System.getProperty("catalina.base")+
						"\\conf\\file-reciever.properties");
				
			if (prop.getProperty("handlers_to_run").length() > 0)
			for (String retval: prop.getProperty("handlers_to_run").split(", "))
			{
				handlers_to_proceed.add(retval);
		    }
			
			senders_to_proceed = new ArrayList<Long>();
			if (prop.getProperty("senders_to_run") == null )
				throw new Property_not_found_exception(
						"property 'senders_to_run' not found in "+
						System.getProperty("catalina.base")+
						"\\conf\\file-reciever.properties");
			
			if (prop.getProperty("senders_to_run").length() > 0)
			for (String retval: prop.getProperty("senders_to_run").split(", "))
			{
				senders_to_proceed.add(Long.parseLong(retval));
		    }

		} catch (Exception e) {
			throw e;
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static List<Long> getFile_types_to_preceed() throws Exception
	{
		if (file_types_to_preceed == null) init();
		return file_types_to_preceed;
	}

	public static List<String> getHandlers_to_proceed() throws Exception
	{
		if (handlers_to_proceed == null) init();
		return handlers_to_proceed;
	}
	
	public static List<Long> getSenders_to_proceed() throws Exception
	{
		if (senders_to_proceed == null) init();
		return senders_to_proceed;
	}
}
