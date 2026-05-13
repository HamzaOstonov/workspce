package com.is.nibbd_liquidation;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<GeneralRecord>
		implements BindingListModel {

	public PagingListModel(int startPageNumber, int pageSize, Object fl,
			String alias) {
		super(startPageNumber, pageSize, fl, alias);
	}

	@Override
	protected List<GeneralRecord> getPageData(int itemStartNumber, int pageSize,
			Object fl, String alias) {
		FilterRecord fc;
		if (fl != null) {
			fc = (FilterRecord) fl;
		} else {
			fc = new FilterRecord();
		}
		return LiqService.getRecordsFl(itemStartNumber, pageSize, fc,
				alias);
	}

	@Override
	public int getTotalSize(Object fl, String alias) {
		FilterRecord fc;
		if (fl != null) {
			fc = (FilterRecord) fl;
		} else {
			fc = new FilterRecord();
		}
		return LiqService.getCount(fc, alias);
	}

	@Override
	public int indexOf(Object obj) {
		return 0;
	}

	@Override
	protected List<GeneralRecord> getPageData(int itemStartNumber, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}

}
