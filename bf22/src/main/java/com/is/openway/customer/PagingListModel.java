
package com.is.openway.customer;

import java.util.List;
import org.zkoss.zkplus.databind.BindingListModel;

import com.is.openway.model.UFXMsgReqClientResp;
import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<Customer> implements BindingListModel
{
    public PagingListModel(final int startPageNumber, final int pageSize, final Object fl, final String alias) {
        super(startPageNumber, pageSize, fl, alias);
    }
    
    protected List<Customer> getPageData(final int itemStartNumber, final int pageSize, final Object fl, final String alias) {
        CustomerFilter fc;
        if (fl != null) {
            fc = (CustomerFilter)fl;
        }
        else {
            fc = new CustomerFilter();
        }
        return CustomerService.getCustomersFl(itemStartNumber, pageSize, fc, alias);
    }
    
    public int getTotalSize(final Object fl, final String alias) {
        CustomerFilter fc;
        if (fl != null) {
            fc = (CustomerFilter)fl;
        }
        else {
            fc = new CustomerFilter();
        }
        return CustomerService.getCount(fc, alias);
    }
    
    public int indexOf(final Object obj) {
        return 0;
    }
    
    protected List<Customer> getPageData(final int itemStartNumber, final int pageSize) {
        return null;
    }
    
    public int getTotalSize() {
        return 0;
    }
}
