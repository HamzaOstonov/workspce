package com.is.humoOperation;

import java.util.List;
import org.zkoss.zkplus.databind.BindingListModel;
import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<HumoOperation>
		implements BindingListModel {

	public PagingListModel(int startPageNumber, int pageSize, Object fl,
			String alias) {
		super(startPageNumber, pageSize, fl, alias);
	}

	@Override
	protected List<HumoOperation> getPageData(int itemStartNumber,
			int pageSize, Object fl, String alias) {
		HumoOperationFilter fc;
		if (fl != null) {
			fc = (HumoOperationFilter) fl;
		} else {
			fc = new HumoOperationFilter();
		}
		return HumoOperationService.getHumoOperationsFl(itemStartNumber,
				pageSize, fc);
	}

	@Override
	public int getTotalSize(Object fl, String alias) {
		HumoOperationFilter fc;
		if (fl != null) {
			fc = (HumoOperationFilter) fl;
		} else {
			fc = new HumoOperationFilter();
		}
		return HumoOperationService.getCount(fc);
	}

	@Override
	public int indexOf(Object obj) {
		return 0;
	}

	@Override
	protected List<HumoOperation> getPageData(int itemStartNumber, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}

}
