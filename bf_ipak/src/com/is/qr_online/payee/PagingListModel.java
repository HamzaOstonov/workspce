package com.is.qr_online.payee;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;


public class PagingListModel extends AbstractPagingListModel<Payee> implements BindingListModel {

	private static final long serialVersionUID = -6011582584332699649L;

	public PagingListModel(int startPageNumber, int pageSize, Object fl, String alias) {
		super(startPageNumber, pageSize, fl, alias);
	}

	@Override
	protected List<Payee> getPageData(int itemStartNumber, int pageSize, Object fl, String alias) {
		PayeeFilter fc;
		if (fl != null) {
			fc = (PayeeFilter) fl;
		} else {
			fc = new PayeeFilter();
		}
		return PayeeService.getPayeesFl(itemStartNumber, pageSize, fc);
	}

	@Override
	public int getTotalSize(Object fl, String alias) {
		PayeeFilter fc;
		if (fl != null) {
			fc = (PayeeFilter) fl;
		} else {
			fc = new PayeeFilter();
		}
		return PayeeService.getCount(fc);
	}

	@Override
	public int indexOf(Object obj) {
		return 0;
	}

	@Override
	protected List<Payee> getPageData(int itemStartNumber, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}

}
