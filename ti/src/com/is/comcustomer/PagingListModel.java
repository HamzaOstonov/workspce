package com.is.comcustomer;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel  extends AbstractPagingListModel<ComCustomer> implements BindingListModel {

    public PagingListModel(int startPageNumber, int pageSize, Object fl, String alias) {
    super(startPageNumber, pageSize,fl,alias);
    }
@Override
protected List<ComCustomer> getPageData(int itemStartNumber, int pageSize, Object fl,String alias) {
    ComCustomerFilter fc;
    if(fl !=null){
        fc = (ComCustomerFilter)fl;
}else{
        fc = new ComCustomerFilter();
}
    return ComCustomerService.getComCustomersFl(itemStartNumber, pageSize,fc,alias);
}

@Override
public int getTotalSize(Object fl,String alias)  {
    ComCustomerFilter fc;
    if(fl !=null){
        fc = (ComCustomerFilter)fl;
}else{
        fc = new ComCustomerFilter();
}
    return ComCustomerService.getCount(fc,alias);
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<ComCustomer> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	protected List<ComCustomer> getPageData(int itemStartNumber, int pageSize,
			Object fl, String alias, boolean sorted_desc)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
