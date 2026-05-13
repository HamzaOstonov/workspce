package com.is.bpri.operations;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<Operation> implements BindingListModel{

	private static final long serialVersionUID = 3492253120121470579L;

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
		Operation fc;
		if(fl!=null) fc = (Operation) fl;
		else fc = new Operation();
		return OperationService.getCount(fc, alias);
	}

	@Override
	protected List<Operation> getPageData(int itemStartNumber, int pageSize) {
		return null;
	}

	@Override
	protected List<Operation> getPageData(int itemStartNumber, int pageSize,
			Object fl, String alias) {
		Operation fc;
		if(fl!=null) fc = (Operation) fl;
		else fc = new Operation();
		return OperationService.getOperationsFl(itemStartNumber, pageSize, fc, alias);
	}

}
