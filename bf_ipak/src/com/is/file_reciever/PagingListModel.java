package com.is.file_reciever;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.account.AccountFilter;
import com.is.account.AccountService;
import com.is.file_reciever.simple.File_filter;
import com.is.file_reciever.simple.Fr_file;
import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<Fr_file> implements BindingListModel
{

	public PagingListModel(int startPageNumber, int pageSize, Object fl, String alias) {
        super(startPageNumber, pageSize,fl, alias);
        }
	
	public PagingListModel(int startPageNumber, int pageSize)
	{
		super(startPageNumber, pageSize);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getTotalSize()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalSize(Object fl, String alias)
	{
		return File_service.getTotalSize(fl, alias);
	}

	@Override
	protected List<Fr_file> getPageData(int itemStartNumber, int pageSize)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<Fr_file> getPageData(int itemStartNumber, int pageSize,
			Object fl, String alias)
	{
		File_filter fc;
		    if(fl !=null){
		        fc = (File_filter)fl;
		}else{
		        fc = new File_filter();
		}
		return File_service.getFilesFl(itemStartNumber, pageSize,fc, alias);
	}

	@Override
	public int indexOf(Object obj)
	{
		// TODO Auto-generated method stub
		return 0;
	}

}
