package com.is.base.utils;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.is.ISLogger;

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
}
