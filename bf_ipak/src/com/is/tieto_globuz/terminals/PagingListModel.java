package com.is.tieto_globuz.terminals;

import java.util.List;
import org.zkoss.zkplus.databind.BindingListModel;
import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<Terminal> implements BindingListModel
{
	
	public PagingListModel(int startPageNumber, int pageSize, Object fl, String branch)
	{
		super(startPageNumber, pageSize, fl, branch);
	}
	
	@Override
	protected List<Terminal> getPageData(int itemStartNumber, int pageSize, Object fl, String branch)
	{
		TerminalFilter fc;
		if (fl != null)
		{
			fc = (TerminalFilter) fl;
		}
		else
		{
			fc = new TerminalFilter();
		}
		return TerminalService.getTerminalsFl(itemStartNumber, pageSize, fc);
	}
	
	@Override
	public int getTotalSize(Object fl, String branch)
	{
		TerminalFilter fc;
		if (fl != null)
		{
			fc = (TerminalFilter) fl;
		}
		else
		{
			fc = new TerminalFilter();
		}
		return TerminalService.getCount(fc);
	}
	
	@Override
	public int indexOf(Object obj)
	{
		return 0;
	}
	
	@Override
	protected List<Terminal> getPageData(int itemStartNumber, int pageSize)
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
