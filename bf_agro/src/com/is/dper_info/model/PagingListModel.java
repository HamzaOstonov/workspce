package com.is.dper_info.model;

import java.util.Comparator;
import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;
import org.zkoss.zul.ListModelExt;

import com.is.clb.bankclients.AbstractPagingListModel;
import com.is.clb.bankclients.BankClient;
import com.is.dper_info.DperInfoDao;

public class PagingListModel extends AbstractPagingListModel<dper_info> implements BindingListModel, ListModelExt {

	public PagingListModel(int startPageNumber, int pageSize, Object fl,
			String alias) {
		super(startPageNumber, pageSize, fl, alias);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void sort(Comparator cmpr, boolean ascending) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int indexOf(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalSize(Object fl, String alias) {
		// TODO Auto-generated method stub
		dper_infoFilter filter  = (dper_infoFilter) fl;
		return DperInfoDao.getCount(filter,alias);
	}

	@Override
	protected List<dper_info> getPageData(int itemStartNumber, int pageSize) {
		
		return DperInfoDao.getList(itemStartNumber, pageSize);
	}

	@Override
	protected List<dper_info> getPageData(int itemStartNumber, int pageSize,
			Object fl, String alias) {
		dper_infoFilter filter  = (dper_infoFilter) fl;
		return  DperInfoDao.getList(itemStartNumber, pageSize, filter, alias);
	}

	

}
