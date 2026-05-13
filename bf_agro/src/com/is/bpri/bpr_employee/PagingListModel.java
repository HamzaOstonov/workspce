package com.is.bpri.bpr_employee;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<BprEmployee> implements BindingListModel{

	private static final long serialVersionUID = 1L;

	public PagingListModel(int startPageNumber, int pageSize, Object fl,
			String alias) {
		super(startPageNumber, pageSize, fl, alias);
	}

	@Override
	public int indexOf(Object arg0) {
		return 0;
	}

	@Override
	public int getTotalSize() {
		return 0;
	}

	@Override
	public int getTotalSize(Object fl, String alias) {
		BprEmployee fc;
		if(fl!=null) fc = (BprEmployee) fl;
		else fc = new BprEmployee(); 
		return BprEmployeeService.getCount(fc, alias);
	}

	@Override
	protected List<BprEmployee> getPageData(int itemStartNumber, int pageSize) {
		return null;
	}

	@Override
	protected List<BprEmployee> getPageData(int itemStartNumber, int pageSize,
			Object fl, String alias) {
		BprEmployee fc;
		if(fl!=null) fc = (BprEmployee) fl;
		else fc = new BprEmployee(); 
		return BprEmployeeService.getBproductsFl(itemStartNumber, pageSize, fc, alias);
	}
	
}
