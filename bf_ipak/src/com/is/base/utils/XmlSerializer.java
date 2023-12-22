package com.is.base.utils;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

import com.is.ISLogger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class XmlSerializer {
	public static void write(Object f, String filename){
        XMLEncoder encoder;
		try {
			File file = new File(filename);
			file.createNewFile();
			encoder = new XMLEncoder(
			      new BufferedOutputStream(
			        new FileOutputStream(file)));
			encoder.writeObject(f);
	        encoder.close();
		} catch (FileNotFoundException e) {
			ISLogger.getLogger().error(e.getStackTrace());
			e.printStackTrace();
		} catch (IOException e) {
			ISLogger.getLogger().error(e.getStackTrace());
			e.printStackTrace();
		}
        
    }

    public static Object read(String filename){
        XMLDecoder decoder;
        Object o = null;
		try {
			decoder = new XMLDecoder(new BufferedInputStream(
                new FileInputStream(filename)));
			o = (Object)decoder.readObject();
	        decoder.close();
		} catch (FileNotFoundException e) {
			ISLogger.getLogger().error(e.getStackTrace());
			e.printStackTrace();
		}
        
        return o;
    }

    public String toXmlString(Object object){
        String xmlString = "";
        try {
            JAXBContext context = JAXBContext.newInstance(Object.class);
            Marshaller m = context.createMarshaller();

            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML

            StringWriter sw = new StringWriter();
            m.marshal(object, sw);
            xmlString = sw.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return xmlString;
    }
}
