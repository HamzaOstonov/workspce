package com.is.uzcard;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zul.AbstractListModel;

import com.is.uzcard.Constants.FILTER_FIELD_TYPE;
import com.is.uzcard.model.AccountInfo;


public class PagingListModelAccInfo extends AbstractListModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5745439335696291146L;
	
	private int _activePageNumber;
    private int _pageSize;
    private int _itemStartNumber;
    private String _filter;
    private String _branch;
    private List<AccountInfo> _items = new ArrayList<AccountInfo>();
    
    public PagingListModelAccInfo(int activePage, int rowNum, String filter, String branch, FILTER_FIELD_TYPE type){
    	_activePageNumber = activePage;
    	_pageSize = rowNum;
    	_filter = filter;
    	_branch = branch;

    	_items = UzCardService.getPageData(_activePageNumber, _pageSize, _filter, _branch, type);
    }
    
    
    public int getTotalSize(String filter, String branch, FILTER_FIELD_TYPE fieldType){
    	return UzCardService.getCount(filter, branch, fieldType);
    }


	 @Override
	    public Object getElementAt(int index) {
	            return _items.get(index);
	    }

	    @Override
	    public int getSize() {
	            return _items.size();
	    }

	    public int getStartPageNumber() {
	            return this._activePageNumber;
	    }

	    public int getPageSize() {
	            return this._pageSize;
	    }

	    public int getItemStartNumber() {
	            return _itemStartNumber;
	    }
	
	
}
