
package com.is.tietovisa.accpay;

import java.util.List;
import org.zkoss.zkplus.databind.BindingListModel;


import com.is.tietovisa.model.CardIbs;
import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<CardIbs> implements BindingListModel
{
    public PagingListModel(final int startPageNumber, final int pageSize, final Object fl, final String alias) {
        super(startPageNumber, pageSize, fl, alias);
    }
    
    protected List<CardIbs> getPageData(final int itemStartNumber, final int pageSize, final Object fl, final String alias) {
    	CardIbs fc;
        if (fl != null) {
            fc = (CardIbs)fl;
        }
        else {
            fc = new CardIbs();
        }
    
          return AccPayService.getCustomersFl(itemStartNumber, pageSize, fc, alias);        
    }
    
    public int getTotalSize(final Object fl, final String alias) {
    	CardIbs fc;
        if (fl != null) {
            fc = (CardIbs)fl;
        }
        else {
            fc = new CardIbs();
        }
        return AccPayService.getCount(fc, alias);
    }
    
    public int indexOf(final Object obj) {
        return 0;
    }
    
    protected List<CardIbs> getPageData(final int itemStartNumber, final int pageSize) {
        return null;
    }
    
    public int getTotalSize() {
        return 0;
    }
}
