package com.is.accounting;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

@SuppressWarnings("serial")
public class PagingListModel extends AbstractPagingListModel<Accounting> implements BindingListModel {

    public PagingListModel(int startPageNumber, int pageSize, Object fl, String alias ) {
    super(startPageNumber, pageSize,fl, alias);
    }
@Override
protected List<Accounting> getPageData(int itemStartNumber, int pageSize, Object fl, String alias ) {
    AccountingFilter fc;
    if(fl !=null){
        fc = (AccountingFilter)fl;
}else{
        fc = new AccountingFilter();
}
    return AccountingService.getAccountingsFl(itemStartNumber, pageSize,fc,alias);
}

@Override
public int getTotalSize(Object fl,String alias )  {
    AccountingFilter fc;
    if(fl !=null){
        fc = (AccountingFilter)fl;
}else{
        fc = new AccountingFilter();
}
    return AccountingService.getCount(fc,alias);
}

@Override
public int indexOf(Object obj) {
        return 0;
}



    @Override
    protected List<Accounting> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	protected List<Accounting> getPageData(int itemStartNumber, int pageSize,
			Object fl, String alias, boolean sorted_desc)
	{
		// TODO Auto-generated method stub
		return null;
	}


}


