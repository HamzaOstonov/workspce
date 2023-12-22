package com.is.file_reciever_view.tieto_transations;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.file_reciever_view.energo.Ext_in_file_records;
import com.is.file_reciever_view.energo.Ext_in_file_recordsFilter;
import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<Ext_in_file_records> implements BindingListModel 
{
	
	
 public PagingListModel(int startPageNumber, int pageSize, Object fl,
			String alias)
	{
		super(startPageNumber, pageSize, fl, alias);
		// TODO Auto-generated constructor stub
	}

@Override
protected List<Ext_in_file_records> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
    Ext_in_file_recordsFilter fc;
    if(fl !=null){
        fc = (Ext_in_file_recordsFilter)fl;
}else{
        fc = new Ext_in_file_recordsFilter();
}
    return Tieto_transactions_service.getExt_in_file_recordssFl(itemStartNumber, pageSize,fc);
}

@Override
public int getTotalSize(Object fl, String branch)  {
    Ext_in_file_recordsFilter fc;
    if(fl !=null){
        fc = (Ext_in_file_recordsFilter)fl;
}else{
        fc = new Ext_in_file_recordsFilter();
}
    return Tieto_transactions_service.getCount(fc);
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<Ext_in_file_records> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize()
	{
		// TODO Auto-generated method stub
		return 0;
	}


}
