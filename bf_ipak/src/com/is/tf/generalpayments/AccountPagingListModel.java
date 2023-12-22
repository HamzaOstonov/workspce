package com.is.tf.generalpayments;

import java.util.List;
import org.zkoss.zkplus.databind.BindingListModel;
import com.is.utils.AbstractPagingListModel;

@SuppressWarnings("serial")
public class AccountPagingListModel extends AbstractPagingListModel<Account> implements BindingListModel {

        public AccountPagingListModel(int startPageNumber, int pageSize, Object fl, String alias) {
        super(startPageNumber, pageSize,fl, alias);
        }
    @Override
    protected List<Account> getPageData(int itemStartNumber, int pageSize, Object fl, String alias) {
        AccountFilter fc;
        if(fl !=null){
            fc = (AccountFilter)fl;
    }else{
            fc = new AccountFilter();
    }
        return GeneralPaymentService.getAccountsFl(itemStartNumber, pageSize,fc, alias);
    }

    @Override
    public int getTotalSize(Object fl, String alias)  {
        AccountFilter fc;
        if(fl !=null){
            fc = (AccountFilter)fl;
    }else{
            fc = new AccountFilter();
    }
        return GeneralPaymentService.getCount(fc, alias);
    }

    @Override
    public int indexOf(Object obj) {
            return 0;
    }


        @Override
        protected List<Account> getPageData(int itemStartNumber, int pageSize) {
                // TODO Auto-generated method stub
                return null;
        }
    
    	public int getTotalSize() {
    		// TODO Auto-generated method stub
    		return 0;
    	}
}

