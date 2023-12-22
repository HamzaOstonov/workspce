package com.is.nibbd.reis.filehandlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.Date;

import net.sf.sevenzipjbinding.IOutCreateArchiveZip;
import net.sf.sevenzipjbinding.IOutCreateCallback;
import net.sf.sevenzipjbinding.IOutItemZip;
import net.sf.sevenzipjbinding.ISequentialInStream;
import net.sf.sevenzipjbinding.SevenZip;
import net.sf.sevenzipjbinding.SevenZipException;
import net.sf.sevenzipjbinding.impl.OutItemFactory;
import net.sf.sevenzipjbinding.impl.RandomAccessFileOutStream;
import net.sf.sevenzipjbinding.util.ByteArrayStream;

import com.is.ISLogger;
import com.is.nibbd.models.ArchiveItem;
import com.is.nibbd.util.FilePaths;

public class ArchiveCreator {
	
	static class CreationCallBack implements IOutCreateCallback<IOutItemZip> {
		private final byte[] bytesToCompress;
		private Date archiveDate;
		
		private CreationCallBack(byte[] bytesToCompress,Date date) {
			this.bytesToCompress = bytesToCompress;
			this.archiveDate = date;
		}
		
		@Override
		public void setTotal(long total) throws SevenZipException {}

		@Override
		public void setCompleted(long complete) throws SevenZipException {}

		@Override
		public void setOperationResult(boolean operationResultOk)
				throws SevenZipException {}

		@Override
		public IOutItemZip getItemInformation(int index,
				OutItemFactory<IOutItemZip> outItemFactory)
				throws SevenZipException {
			IOutItemZip outItem =outItemFactory.createOutItem();
			outItem.setDataSize((long)bytesToCompress.length);
			
			outItem.setPropertyPath("NIBBD.INP");
			outItem.setPropertyCreationTime(archiveDate);
			return null;
		}

		@Override
		public ISequentialInStream getStream(int index)
				throws SevenZipException {
			return new ByteArrayStream(bytesToCompress, true);
		}
		
	}
	
	private ArchiveItem archiveItem;
	private ArchiveCreator(ArchiveItem archiveItem) {
		this.archiveItem = archiveItem;
	}
	public static ArchiveCreator getInstance(ArchiveItem archiveItem) {
		return new ArchiveCreator(archiveItem);
	}
	
	public void createArchive(){
		File file = new File(FilePaths.OUT+archiveItem.getArchiveName());
		RandomAccessFile raf = null;
		IOutCreateArchiveZip outArchive = null;
		
		try {
			raf = new RandomAccessFile(file, "rw");
			outArchive = SevenZip.openOutArchiveZip();
			outArchive.setLevel(5);
			outArchive.createArchive(new RandomAccessFileOutStream(raf)
									, 1
									, new CreationCallBack(
											archiveItem.getTextData().getBytes(), 
											archiveItem.getFileDate()));
		} catch (SevenZipException e) {
			ISLogger.getLogger().error(e.getStackTrace());
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			ISLogger.getLogger().error(e.getStackTrace());
			e.printStackTrace();
		}
	}
	
}
