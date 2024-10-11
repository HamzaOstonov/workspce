package com.is.hr;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;
import org.zkoss.zul.event.ListDataListener;
import com.is.utils.AbstractPagingListModel;

public class PagingListModel_ok_academic extends AbstractPagingListModel<ok_academic> implements BindingListModel {

	public PagingListModel_ok_academic(int startPageNumber, int pageSize, Object fl, String branch) {
		super(startPageNumber, pageSize, fl, branch);
	}

	protected List<ok_academic> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
		ok_academicFilter fc;
		if (fl != null) {
			fc = (ok_academicFilter) fl;
		} else {
			fc = new ok_academicFilter();
		}
		return ok_academicService.getok_academicsFl(itemStartNumber, pageSize, fc);
	}

	public int getTotalSize(Object fl, String branch) {
		ok_academicFilter fc;
		if (fl != null) {
			fc = (ok_academicFilter) fl;
		} else {
			fc = new ok_academicFilter();
		}
		return ok_academicService.getCount(fc);
	}

	public int indexOf(Object obj) {
		return 0;
	}

	protected List<ok_academic> getPageData(int itemStartNumber, int PageSize) {
		return null;
	}

	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}

}
