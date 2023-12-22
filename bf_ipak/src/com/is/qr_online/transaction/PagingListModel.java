package com.is.qr_online.transaction;

import java.util.List;
import org.zkoss.zkplus.databind.BindingListModel;
import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<Transaction> implements BindingListModel {

	private static final long serialVersionUID = -8208065119156295836L;

	public PagingListModel(int startPageNumber, int pageSize, Object fl, String alias) {
		super(startPageNumber, pageSize, fl, alias);
	}

	@Override
	protected List<Transaction> getPageData(int itemStartNumber, int pageSize, Object fl, String alias) {
		TransactionFilter fc;
		if (fl != null) {
			fc = (TransactionFilter) fl;
		} else {
			fc = new TransactionFilter();
		}
		return TransactionService.getTransactionsFl(itemStartNumber, pageSize, fc);
	}

	@Override
	public int getTotalSize(Object fl, String alias) {
		TransactionFilter fc;
		if (fl != null) {
			fc = (TransactionFilter) fl;
		} else {
			fc = new TransactionFilter();
		}
		return TransactionService.getCount(fc);
	}

	@Override
	public int indexOf(Object obj) {
		return 0;
	}

	@Override
	protected List<Transaction> getPageData(int itemStartNumber, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}

}
