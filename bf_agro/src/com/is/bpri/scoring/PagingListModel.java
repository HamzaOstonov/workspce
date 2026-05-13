package com.is.bpri.scoring;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

@SuppressWarnings("serial")
public class PagingListModel extends AbstractPagingListModel<Scoring> implements BindingListModel{

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
		return 0;
	}

	@Override
	protected List<Scoring> getPageData(int itemStartNumber, int pageSize) {
		return null;
	}

	@Override
	protected List<Scoring> getPageData(int itemStartNumber, int pageSize,
			Object fl, String alias) {
		return ScoringService.getModel(itemStartNumber, alias);
	}

}
