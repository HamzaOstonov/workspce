package com.is.tieto_globuz.merchants;

import java.util.List;
import org.zkoss.zkplus.databind.BindingListModel;
import com.is.utils.AbstractPagingListModel;

public class PagingListModelNew extends AbstractPagingListModel<Merchant> implements BindingListModel
{
	
	public PagingListModelNew(int startPageNumber, int pageSize, Object fl, String branch)
	{
		super(startPageNumber, pageSize, fl, branch);
	}
	
	@Override
	protected List<Merchant> getPageData(int itemStartNumber, int pageSize, Object fl, String branch)
	{
		MerchantFilter fc;
		if (fl != null)
		{
			fc = (MerchantFilter) fl;
		}
		else
		{
			fc = new MerchantFilter();
		}
		return MerchantService.getMerchant4Send();
	}
	
	@Override
	public int getTotalSize(Object fl, String branch)
	{
		MerchantFilter fc;
		if (fl != null)
		{
			fc = (MerchantFilter) fl;
		}
		else
		{
			fc = new MerchantFilter();
		}
		return MerchantService.getCountNew();
	}
	
	@Override
	public int indexOf(Object obj)
	{
		return 0;
	}
	
	@Override
	protected List<Merchant> getPageData(int itemStartNumber, int pageSize)
	{
		return null;
	}

	@Override
	public int getTotalSize()
	{
		// TODO Auto-generated method stub
		return 0;
	}
}
