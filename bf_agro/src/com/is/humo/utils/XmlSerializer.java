package com.is.humo.utils;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.is.ISLogger;

public class XmlSerializer {
	public static void write(Object f, String filename){
        XMLEncoder encoder;
		try {
			encoder = new XMLEncoder(
			      new BufferedOutputStream(
			        new FileOutputStream(filename)));
			encoder.writeObject(f);
	        encoder.close();
		} catch (FileNotFoundException e) {
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
}
