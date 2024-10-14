package com.is.file_reciever.energo;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class Out_file_PagingListModel extends AbstractPagingListModel<Ext_out_file_records> implements BindingListModel {

    public Out_file_PagingListModel(int startPageNumber, int pageSize, Object fl,String branch) {
    super(startPageNumber, pageSize,fl,branch);
    }
@Override
protected List<Ext_out_file_records> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
    Ext_out_file_recordsFilter fc;
    if(fl !=null){
        fc = (Ext_out_file_recordsFilter)fl;
}else{
        fc = new Ext_out_file_recordsFilter();
}
    return Ext_out_file_recordsService.getExt_out_file_recordssFl(itemStartNumber, pageSize,fc);
}

@Override
public int getTotalSize(Object fl, String branch)  {
    Ext_out_file_recordsFilter fc;
    if(fl !=null){
        fc = (Ext_out_file_recordsFilter)fl;
}else{
        fc = new Ext_out_file_recordsFilter();
}
    return Ext_out_file_recordsService.getCount(fc);
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<Ext_out_file_records> getPageData(int itemStartNumber, int pageSize) {
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
