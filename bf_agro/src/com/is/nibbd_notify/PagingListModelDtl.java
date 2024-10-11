package com.is.nibbd_notify;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModelDtl extends AbstractPagingListModel<Nibbd_dtl>
		implements BindingListModel {

	public PagingListModelDtl(int startPageNumber, int pageSize, Object fl,
			String alias) {
		super(startPageNumber, pageSize, fl, alias);
	}

	@Override
	protected List<Nibbd_dtl> getPageData(int itemStartNumber, int pageSize,
			Object fl, String alias) {
		NibbdFilterDtl fc;
		if (fl != null) {
			fc = (NibbdFilterDtl) fl;
		} else {
			fc = new NibbdFilterDtl();
		}
		return NibbdService.getNibbdDtlFl(itemStartNumber, pageSize, fc,
				alias);
	}

	@Override
	public int getTotalSize(Object fl, String alias) {
		NibbdFilterDtl fc;
		if (fl != null) {
			fc = (NibbdFilterDtl) fl;
		} else {
			fc = new NibbdFilterDtl();
		}
		return NibbdService.getCountDtl(fc, alias);
	}

	@Override
	public int indexOf(Object obj) {
		return 0;
	}

	@Override
	protected List<Nibbd_dtl> getPageData(int itemStartNumber, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}

}
