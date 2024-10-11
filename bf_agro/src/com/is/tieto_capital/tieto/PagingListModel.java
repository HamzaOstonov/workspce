package com.is.tieto_capital.tieto;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;
import org.zkoss.zul.ListModelExt;

import com.is.utils.AbstractPagingListModel;

@SuppressWarnings("serial")
public class PagingListModel extends AbstractPagingListModel<Tclient> implements BindingListModel, ListModelExt
{
	private static capitalBank.IssuingWS.IssuingPortProxy issuingPortProxy;
	private List<Tclient> _items;
	
	public List<Tclient> get_items()
	{
		return this._items;
	}
	
	public void set_items(List<Tclient> _items)
	{
		this._items = _items;
	}
	
	public PagingListModel(int startPageNumber, int pageSize, Object fl
		, String alias, capitalBank.IssuingWS.IssuingPortProxy PortProxy)
	{
		super(startPageNumber, pageSize, fl, setPortProxy(alias, PortProxy));
	}
	
	public static String setPortProxy(String alias
		, capitalBank.IssuingWS.IssuingPortProxy PortProxy)
	{
		issuingPortProxy = PortProxy;
		return alias;
	}
	
	public PagingListModel(int startPageNumber, int pageSize, Object fl
		, String alias)
	{
		super(startPageNumber, pageSize, fl, alias);
	}
	
	@Override
	protected List<Tclient> getPageData(int itemStartNumber, int pageSize, Object fl, String alias)
	{
		TclientFilter fc;
		if (fl != null)
		{
			fc = (TclientFilter) fl;
		}
		else
		{
			fc = new TclientFilter();
		}
		_items = TclientService.getTclientsFl(itemStartNumber, pageSize, fc, alias, issuingPortProxy);
		return _items;
	}
	
	@Override
	public int getTotalSize(Object fl, String alias)
	{
		TclientFilter fc;
		if (fl != null)
		{
			fc = (TclientFilter) fl;
		}
		else
		{
			fc = new TclientFilter();
		}
		return TclientService.getCount(fc, alias, issuingPortProxy);
	}
	
	@Override
	public int indexOf(Object obj)
	{
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	protected List<Tclient> getPageData(int itemStartNumber, int pageSize)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int getTotalSize()
	{
		// TODO Auto-generated method stub
		return 0;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void sort(Comparator cmpr, boolean ascending)
	{
		// TODO Auto-generated method stub
		Collections.sort(getItems(), cmpr);
		fireEvent(org.zkoss.zul.event.ListDataEvent.CONTENTS_CHANGED, -1, -1);
	}
	
	public List<Tclient> getItems()
	{
		return _items;
	}
	
}
