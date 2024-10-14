package com.is.tieto_capital.accApproval;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<AccApproval> implements BindingListModel {

	public PagingListModel(int startPageNumber, int pageSize, Object fl, String branch) {
		super(startPageNumber, pageSize, fl, branch);
	}

	@Override
	protected List<AccApproval> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
		AccApprovalFilter fc;
		if (fl != null) {
			fc = (AccApprovalFilter) fl;
		} else {
			fc = new AccApprovalFilter();
		}
		return AccApprovalService.getAccApprovalsFl(itemStartNumber, pageSize, fc);
	}

	@Override
	public int getTotalSize(Object fl, String branch) {
		AccApprovalFilter fc;
		if (fl != null) {
			fc = (AccApprovalFilter) fl;
		} else {
			fc = new AccApprovalFilter();
		}
		return AccApprovalService.getCount(fc);
	}

	@Override
	public int indexOf(Object obj) {
		return 0;
	}

	@Override
	protected List<AccApproval> getPageData(int itemStartNumber, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}

}
