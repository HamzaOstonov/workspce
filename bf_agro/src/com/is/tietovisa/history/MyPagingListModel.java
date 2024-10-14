
package com.is.tietovisa.history;

import java.util.List;
import org.zkoss.zkplus.databind.BindingListModel;


import com.is.tietovisa.model.MyAbstractPagingListModel;

public class MyPagingListModel extends MyAbstractPagingListModel<History> implements BindingListModel
{
    public MyPagingListModel(final int startPageNumber, final int pageSize, final Object fl, final String alias) {
        super(startPageNumber, pageSize, fl, alias);
    }
    
    protected List<History> getPageData(final int itemStartNumber, final int pageSize, final Object fl, final String alias) {
        CustomerFilter fc;
        if (fl != null) {
            fc = (CustomerFilter)fl;
        }
        else {
            fc = new CustomerFilter();
        }
        return HistoryService.getCustomersFl(itemStartNumber, pageSize, fc, alias);
    }
    
    public int getTotalSize(final Object fl, final String alias) {
        CustomerFilter fc;
        if (fl != null) {
            fc = (CustomerFilter)fl;
        }
        else {
            fc = new CustomerFilter();
        }
        return HistoryService.getCount(fc, alias);
    }
    
    public int indexOf(final Object obj) {
        return 0;
    }
    
    protected List<History> getPageData(final int itemStartNumber, final int pageSize) {
        return null;
    }
    
    public int getTotalSize() {
        return 0;
    }
}
