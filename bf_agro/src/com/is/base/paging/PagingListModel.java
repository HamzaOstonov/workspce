package com.is.base.paging;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.base.Dao;
import com.is.clients.models.SsDbLinkBranch;

public class PagingListModel<T> extends AbstractPagingListModel<T> implements
		BindingListModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1213437221547954020L;
	private Dao<T> dao;

	public PagingListModel(int startPageNumber, int pageSize, Dao<T> module) {
		super(startPageNumber,pageSize);
		this.dao = module;
		setItems();
	}
	@Override
	protected List<T> getPageData(int itemStartNumber, int pageSize) {
		return dao.getList(itemStartNumber, pageSize);
	}

	@Override
	public int getTotalSize(Object fl, String alias,
			List<SsDbLinkBranch> ss_dblink_branches) {
		return dao.getCount();
	}

	@Override
	public int indexOf(Object obj) {
		return 0;
	}

	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return dao.getCount();
	}

	@Override
	protected List<T> getPageData(int itemStartNumber, int pageSize, Object fl,
			String alias, List<SsDbLinkBranch> ss_dblink_branches) {
		// TODO Auto-generated method stub
		return null;
	}

}
