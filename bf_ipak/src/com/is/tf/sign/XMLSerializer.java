package com.is.tf.sign;

import java.beans.XMLEncoder;
import java.beans.XMLDecoder;
import java.io.*;

public class XMLSerializer {
	 	public static String write(Object f) throws Exception{
	 		String res = "";
	 		ByteArrayOutputStream os = new ByteArrayOutputStream();
	 		XMLEncoder encoder =
	           new XMLEncoder(os);
	        encoder.writeObject(f);
	        encoder.close();
	        res = os.toString();
	        os.close();
	        return res;
	    }

	    public static Object read(String filename) throws Exception {
	        XMLDecoder decoder =
	            new XMLDecoder(new BufferedInputStream(
	                new FileInputStream(filename)));
	        Object o = (Object)decoder.readObject();
	        decoder.close();
	        return o;
	    }
}
