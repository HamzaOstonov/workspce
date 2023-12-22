package com.is.qr_online.merchant;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;


public class PagingListModel extends AbstractPagingListModel<Merchant> implements BindingListModel {

	private static final long serialVersionUID = -5671835678154164734L;

	public PagingListModel(int startPageNumber, int pageSize, Object fl, String alias) {
		super(startPageNumber, pageSize, fl, alias);
	}

	@Override
	protected List<Merchant> getPageData(int itemStartNumber, int pageSize, Object fl, String alias) {
		MerchantFilter fc;
		if (fl != null) {
			fc = (MerchantFilter) fl;
		} else {
			fc = new MerchantFilter();
		}
		return MerchantService.getMerchantsFl(itemStartNumber, pageSize, fc);
	}

	@Override
	public int getTotalSize(Object fl, String alias) {
		MerchantFilter fc;
		if (fl != null) {
			fc = (MerchantFilter) fl;
		} else {
			fc = new MerchantFilter();
		}
		return MerchantService.getCount(fc);
	}

	@Override
	public int indexOf(Object obj) {
		return 0;
	}

	@Override
	protected List<Merchant> getPageData(int itemStartNumber, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}

}
