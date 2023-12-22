package com.is.utils;
import java.util.ArrayList;
import java.util.List;

import org.zkoss.zul.AbstractListModel;

public abstract class AbstractPagingListModel<T> extends AbstractListModel {
    /**
     *
     */
    private static final long serialVersionUID = 6613208067174831719L;

    private int _startPageNumber;
    private int _pageSize;
    private int _itemStartNumber;

    //internal use only
    private List<T> _items = new ArrayList<T>();

    public AbstractPagingListModel(int startPageNumber, int pageSize) {
        super();

        this._startPageNumber = startPageNumber;
        this._pageSize = pageSize;
        this._itemStartNumber = startPageNumber * pageSize;

        _items = getPageData(_itemStartNumber, _pageSize);
}

    public AbstractPagingListModel(int startPageNumber, int pageSize, Object fl, String alias) {
            super();

            this._startPageNumber = startPageNumber;
            this._pageSize = pageSize;
            this._itemStartNumber = startPageNumber * pageSize;

            _items = getPageData(_itemStartNumber, _pageSize,fl, alias);
    }

    public abstract int getTotalSize();
    public abstract int getTotalSize(Object fl, String alias);
    protected abstract List<T> getPageData(int itemStartNumber, int pageSize);
    protected abstract List<T> getPageData(int itemStartNumber, int pageSize,Object fl, String alias);

    @Override
    public Object getElementAt(int index) {
            return _items.get(index);
    }

    @Override
    public int getSize() {
            return _items.size();
    }

    public int getStartPageNumber() {
            return this._startPageNumber;
    }

    public int getPageSize() {
            return this._pageSize;
    }

    public int getItemStartNumber() {
            return _itemStartNumber;
    }
}