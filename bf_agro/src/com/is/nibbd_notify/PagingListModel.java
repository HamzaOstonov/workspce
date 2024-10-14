package com.is.nibbd_notify;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<Nibbd_idx>
		implements BindingListModel {

	public PagingListModel(int startPageNumber, int pageSize, Object fl,
			String alias) {
		super(startPageNumber, pageSize, fl, alias);
	}

	@Override
	protected List<Nibbd_idx> getPageData(int itemStartNumber, int pageSize,
			Object fl, String alias) {
		NibbdFilter fc;
		if (fl != null) {
			fc = (NibbdFilter) fl;
		} else {
			fc = new NibbdFilter();
		}
		return NibbdService.getTrTemplatesFl(itemStartNumber, pageSize, fc,
				alias);
	}

	@Override
	public int getTotalSize(Object fl, String alias) {
		NibbdFilter fc;
		if (fl != null) {
			fc = (NibbdFilter) fl;
		} else {
			fc = new NibbdFilter();
		}
		return NibbdService.getCount(fc, alias);
	}

	@Override
	public int indexOf(Object obj) {
		return 0;
	}

	@Override
	protected List<Nibbd_idx> getPageData(int itemStartNumber, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}

}
