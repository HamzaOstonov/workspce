package com.is.sd_books.report.basic;

import org.zkoss.util.media.AMedia;
import org.zkoss.zul.Filedownload;

public abstract class WordReport extends AbstractReport {

	@Override
	public void getReport() {
		AMedia media = getAmedia();
		Filedownload.save(media.getByteData(), "application/msword",
				this.getFileName() + ".doc");
	}

	public abstract AMedia getAmedia();
}
