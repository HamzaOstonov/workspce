package com.is.sd_books.paging;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.sd_books.model.SD_Book;
import com.is.sd_books.model.SD_BookFilter;
import com.is.sd_books.service.FrmService;
import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<SD_Book>
		implements BindingListModel {
	private static final long serialVersionUID = 1L;
	
	public PagingListModel(int startPageNumber, int pageSize, Object fl,
			String alias) {
		super(startPageNumber, pageSize, fl, alias);
	}
	
	@Override
	protected List<SD_Book> getPageData(int itemStartNumber, int pageSize, Object fl,
			String alias) {
		SD_BookFilter fc;
		if (fl != null)
			fc = (SD_BookFilter)fl;
		else
			fc = new SD_BookFilter();
		return FrmService.getSdBooksF1(itemStartNumber, pageSize, fc, alias);
	}

	@Override
	public int getTotalSize(Object fl, String alias) {
		SD_BookFilter fc;
		if (fl != null)
			fc = (SD_BookFilter)fl;
		else
			fc = new SD_BookFilter();
		return FrmService.getCount(fc, alias);
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
	protected List<SD_Book> getPageData(int itemStartNumber, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}
}
