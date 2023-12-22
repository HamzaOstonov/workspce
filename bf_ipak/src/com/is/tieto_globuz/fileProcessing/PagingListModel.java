package com.is.tieto_globuz.fileProcessing;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<File> implements BindingListModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PagingListModel(int startPageNumber, int pageSize, Object fl, String branch) {
		super(startPageNumber, pageSize, fl, branch);
	}

	@Override
	protected List<File> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
		FileFilter fc;
		if (fl != null) {
			fc = (FileFilter) fl;
		} else {
			fc = new FileFilter();
		}
		return FileService.getFilesFl(itemStartNumber, pageSize, fc);
	}

	@Override
	public int getTotalSize(Object fl, String branch) {
		FileFilter fc;
		if (fl != null) {
			fc = (FileFilter) fl;
		} else {
			fc = new FileFilter();
		}
		return FileService.getCount(fc);
	}

	@Override
	public int indexOf(Object obj) {
		return 0;
	}

	@Override
	protected List<File> getPageData(int itemStartNumber, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}

}
