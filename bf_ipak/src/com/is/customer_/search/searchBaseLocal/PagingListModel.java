package com.is.customer_.search.searchBaseLocal;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;
import org.zkoss.zul.ListModelExt;

import com.is.customer_.core.model.Customer;
import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<Customer> implements
		BindingListModel, ListModelExt  {

	private static final long serialVersionUID = 1L;
	private List<Customer> _items;

	public PagingListModel(int startPageNumber, int pageSize, Object fl,
			String branch) {
		super(startPageNumber, pageSize, fl, branch);
	}

	@Override
	protected List<Customer> getPageData(int itemStartNumber, int pageSize,
			Object fl, String alias) {
		Customer fc;
		if (fl != null) {
			fc = (Customer) fl;
		} else {
			fc = new Customer();
		}
		//return SearchService.getInstance().initSearch(fc, itemStartNumber, pageSize, alias);
		_items=SearchService.getInstance().initSearch(fc, itemStartNumber, pageSize, alias);
		return _items;
	}

	@Override
	public int getTotalSize(Object fl, String branch) {
		Customer fc;
		if (fl != null) {
			fc = (Customer) fl;
		} else {
			fc = new Customer();
		}
		return SearchService.getInstance().getCount(fc,branch);
	}

	@Override
	public int indexOf(Object obj) {
		return 0;
	}

	@Override
	public int getTotalSize() {
		return 0;
	}

	@Override
	protected List<Customer> getPageData(int itemStartNumber, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sort(Comparator arg0, boolean arg1) {
		// TODO Auto-generated method stub
		Collections.sort(getItems() , arg0);
		fireEvent(org.zkoss.zul.event.ListDataEvent.CONTENTS_CHANGED, -1, -1);
		}
	
	private List<Customer> getItems(){
		return _items;
	}
	
}