package com.is.bpri.user_actions;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

@SuppressWarnings("serial")
public class PagingListModel extends AbstractPagingListModel<User_actions> implements BindingListModel{

	public PagingListModel(int startPageNumber, int pageSize, Object fl,String alias) {
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
		User_actionsFilter fc;
		if(fl != null){
			fc = (User_actionsFilter) fl;
		} else {
			fc = new User_actionsFilter();
		}
		return User_actionsService.getCount(fc, alias);
	}

	@Override
	protected List<User_actions> getPageData(int itemStartNumber, int pageSize) {
		return null;
	}

	@Override
	protected List<User_actions> getPageData(int itemStartNumber, int pageSize,Object fl, String alias) {
		User_actionsFilter fc;
		if(fl != null){
			fc = (User_actionsFilter) fl;
		} else {
			fc = new User_actionsFilter();
		}
		return User_actionsService.getBproductsFl(itemStartNumber, pageSize, fc, alias);
	}
	
}
