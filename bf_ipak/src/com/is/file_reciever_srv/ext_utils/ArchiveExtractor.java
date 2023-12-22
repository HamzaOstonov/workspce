package com.is.file_reciever_srv.ext_utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import net.sf.sevenzipjbinding.ArchiveFormat;
import net.sf.sevenzipjbinding.ISequentialOutStream;
import net.sf.sevenzipjbinding.ISevenZipInArchive;
import net.sf.sevenzipjbinding.PropID;
import net.sf.sevenzipjbinding.SevenZip;
import net.sf.sevenzipjbinding.SevenZipException;
import net.sf.sevenzipjbinding.impl.RandomAccessFileInStream;
import net.sf.sevenzipjbinding.simple.ISimpleInArchive;
import net.sf.sevenzipjbinding.simple.ISimpleInArchiveItem;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.arj.ArjArchiveInputStream;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;

import com.is.ISLogger;
import com.is.utils.CheckNull;


public class ArchiveExtractor {
	private static int hash = 0; 			//arjEntry hash
	private static int size = 0; 			//arjEntry size
	private static byte[] archEntry = null; //arjEntry
    
	private static Logger logger = ISLogger.getLogger();
	
	public static List<ArchEntryFile> getReestrFileFromArj(File file) throws Exception {
		List<ArchEntryFile> list = new ArrayList<ArchEntryFile>();
		String file_name = "";
		RandomAccessFile randomAccessFile = null;
		ISevenZipInArchive inArchive = null;
		try {
//		    System.out.println("Start extracting file: "+file.getName());
//		    ISLogger.getLogger().info("Start extracting file: "+file.getName());
		    SevenZip.initSevenZipFromPlatformJAR();
		    //System.out.println("7-Zip-JBinding library was initialized");
		    randomAccessFile = new RandomAccessFile(file.getAbsolutePath(), "r");
			inArchive = SevenZip.openInArchive(ArchiveFormat.ARJ, new RandomAccessFileInStream(randomAccessFile));
			int count = inArchive.getNumberOfItems();
			file_name = "";
			for (int entryId = 0; entryId < count; entryId++) {
				if (!((Boolean) inArchive.getProperty(entryId, PropID.IS_FOLDER)).booleanValue() ) {
					file_name = (String) inArchive.getProperty(entryId,PropID.PATH);
					//System.out.println("fileName = " + (String) inArchive.getProperty(entryId,PropID.NAME)+" = " + (String) inArchive.getProperty(entryId,PropID.PATH));
					archEntry = new byte[0];
					hash = 0;
					size = 0;
					inArchive.extractSlow(Integer.valueOf(entryId), 
					            		new ISequentialOutStream() {
											@Override
											public int write(byte[] data) throws SevenZipException {
												hash ^= Arrays.hashCode(data);
									            size += data.length;
									            try {
									                byte[] concat = ArrayUtils.addAll(archEntry,data);
									                //new byte[archEntry.length + data.length];
									                archEntry = concat;
									            } catch (Exception e) {
													e.printStackTrace();
												}
									            return data.length;
											}
										});
					list.add(new ArchEntryFile(file.getName(), file_name, archEntry, hash, size, 0));
					if (inArchive != null) {try {inArchive.close();} catch (SevenZipException e) { e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e)); } }
					if (randomAccessFile != null) {try {randomAccessFile.close();} catch (IOException e) { e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e)); } }
				}
			}
		} catch (Exception e) {
		    e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e)); 
		} finally {
		    if (inArchive != null) {try {inArchive.close();} catch (SevenZipException e) { } }
		    if (randomAccessFile != null) {try {randomAccessFile.close();} catch (IOException e) { e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e)); } }
		    try { size = 0; hash = 0; archEntry = null; } catch(Exception e) { e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e)); }
		}
//		System.out.println("Stop extracting file: "+file.getName());
//	    ISLogger.getLogger().info("Stop extracting file: "+file.getName());
	    return list;
	}
	
		
	
}
