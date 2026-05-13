package com.is.tieto_agro.fileProcessing;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<FileProcessing> implements BindingListModel {

	private static final long serialVersionUID = 1L;

	public PagingListModel(int startPageNumber, int pageSize, Object fl, String branch) {
		super(startPageNumber, pageSize, fl, branch);
	}

	@Override
	protected List<FileProcessing> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
		FileProcessingFilter fpFilter;
		FileProcessingService fpService = new FileProcessingService();
		if (fl != null) {
			fpFilter = (FileProcessingFilter) fl;
		} else {
			fpFilter = new FileProcessingFilter();
		}
		return fpService.getFileProcessingFl(itemStartNumber, pageSize, fpFilter);
	}

	@Override
	public int getTotalSize(Object fl, String branch) {
		FileProcessingFilter fpFilter;
		FileProcessingService fp_service = new FileProcessingService();
		if (fl != null) {
			fpFilter = (FileProcessingFilter) fl;
		} else {
			fpFilter = new FileProcessingFilter();
		}
		fpFilter.setParent_group_id(Constants.PARENT_GROUP_ID);
		return fp_service.getCount(fpFilter);
	}

	@Override
	public int indexOf(Object obj) {
		return 0;
	}

	@Override
	protected List<FileProcessing> getPageData(int itemStartNumber, int pageSize) {
		return null;
	}

	@Override
	public int getTotalSize() {
		return 0;
	}

}
