package universal_reader;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

public class Universal_reader
{
	public Universal_reader()
	{
		try
		{
			
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db;
			db = dbf.newDocumentBuilder();
			File f = new File(
			"D:\\file_reciever\\test\\test_config_universal_reader.xml");
			Document doc = db.parse(f);
			doc.getDocumentElement().normalize();
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
