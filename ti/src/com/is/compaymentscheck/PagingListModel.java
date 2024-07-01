package com.is.compaymentscheck;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<ComPaymentsCheck> implements BindingListModel {

    public PagingListModel(int startPageNumber, int pageSize, Object fl,String alias) {
    super(startPageNumber, pageSize,fl, alias);
    }
@Override
protected List<ComPaymentsCheck> getPageData(int itemStartNumber, int pageSize, Object fl,String alias) {
    ComPaymentsCheckFilter fc;
    if(fl !=null){
        fc = (ComPaymentsCheckFilter)fl;
}else{
        fc = new ComPaymentsCheckFilter();
}
    return ComPaymentsCheckService.getComPaymentsChecksFl(itemStartNumber, pageSize,fc, alias);
}

@Override
public int getTotalSize(Object fl,String alias)  {
    ComPaymentsCheckFilter fc;
    if(fl !=null){
        fc = (ComPaymentsCheckFilter)fl;
}else{
        fc = new ComPaymentsCheckFilter();
}
    return ComPaymentsCheckService.getCount(fc, alias);
}


@Override
public int indexOf(Object obj) {
        // TODO Auto-generated method stub
        return 0;
}

    @Override
    protected List<ComPaymentsCheck> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	protected List<ComPaymentsCheck> getPageData(int itemStartNumber,
			int pageSize, Object fl, String alias, boolean sorted_desc)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
