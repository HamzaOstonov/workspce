package com.is.creditanket;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.creditanket.table_models.CurrentCredit;
import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<CurrentCredit> implements BindingListModel {

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
		CurrentCredit fc;
		if(fl != null) fc = (CurrentCredit) fl;
		else fc = new CurrentCredit();
		return CreditService.getCount(fc, alias);
	}

	@Override
	protected List<CurrentCredit> getPageData(int itemStartNumber, int pageSize) {
		return null;
	}

	@Override
	protected List<CurrentCredit> getPageData(int itemStartNumber,
			int pageSize, Object fl, String alias) {
		CurrentCredit fc;
		if(fl != null) fc = (CurrentCredit) fl;
		else fc = new CurrentCredit();
		return CreditService.getCreditFl(itemStartNumber, pageSize, fc, alias);
	}

}
