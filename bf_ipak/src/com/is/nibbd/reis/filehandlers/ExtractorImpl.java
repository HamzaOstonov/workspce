package com.is.nibbd.reis.filehandlers;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import net.sf.sevenzipjbinding.IInArchive;
import net.sf.sevenzipjbinding.ISequentialOutStream;
import net.sf.sevenzipjbinding.SevenZip;
import net.sf.sevenzipjbinding.SevenZipException;
import net.sf.sevenzipjbinding.impl.RandomAccessFileInStream;

import com.is.ISLogger;
import com.is.nibbd.models.ArchiveItem;

public class ExtractorImpl implements Extractor{
	private byte[] data;
	private String path;
	
	private ExtractorImpl(String path){
		this.path = path;
	}
	
	public static ExtractorImpl getInstance(String path){
		return new ExtractorImpl(path);
	}
	
	public List<ArchiveItem> getQueries(){
		List<ArchiveItem> list = new ArrayList<ArchiveItem>();
		for(File file: listFiles()){
			list.addAll(extractAcrchive(file));
		}
		return list;
	}
	
	private File[] listFiles(){
		File dir = new File(path);
		return dir.listFiles();
	}
	private List<ArchiveItem> extractAcrchive(File file){
		RandomAccessFile randomAccessFile = null;
		List<ArchiveItem> list = new ArrayList<ArchiveItem>();
		IInArchive inArchive = null;
		int count;
		try {
			randomAccessFile = new RandomAccessFile(file, "r");
			inArchive = SevenZip.openInArchive(null, new RandomAccessFileInStream(randomAccessFile));
			count = inArchive.getNumberOfItems();
			for(int i = 0; i < count; i++) {
				inArchive.extractSlow(i, new ISequentialOutStream() {
					
					@Override
					public int write(byte[] arg0) throws SevenZipException {
						data = arg0;
						return 0;
					}
				});
				list.add(new ArchiveItem(file.getName(),file.getCanonicalPath(),null,new String(data,"Cp866"),null));
			}
		} catch (FileNotFoundException e) {
			ISLogger.getLogger().error(e.getStackTrace());
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (SevenZipException e) {
			ISLogger.getLogger().error(e.getStackTrace());
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			ISLogger.getLogger().error(e.getStackTrace());
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IOException e) {
			ISLogger.getLogger().error(e.getStackTrace());
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return list;
	}
}
