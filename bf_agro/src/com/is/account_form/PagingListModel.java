package com.is.account_form;

import java.util.List;
import org.zkoss.zkplus.databind.BindingListModel;
import com.is.utils.AbstractPagingListModel;

@SuppressWarnings("serial")
public class PagingListModel extends AbstractPagingListModel<AccountForm> implements BindingListModel {

        public PagingListModel(int startPageNumber, int pageSize, Object fl, String alias) {
        super(startPageNumber, pageSize,fl, alias);
        }
    @Override
    protected List<AccountForm> getPageData(int itemStartNumber, int pageSize, Object fl, String alias) {
        AccountForm fc;
        if(fl !=null){
            fc = (AccountForm)fl;
    }else{
            fc = new AccountForm();
    }
       // return AccountFormService.getAccountFormsFl(itemStartNumber, pageSize,fc, alias);
		return null;
    }

    @Override
    public int getTotalSize(Object fl, String alias)  {
        AccountForm fc;
        if(fl !=null){
            fc = (AccountForm)fl;
    }else{
            fc = new AccountForm();
    }
     //   return AccountFormService.getCount(fc, alias);
		return 0;
    }

    @Override
    public int indexOf(Object obj) {
            return 0;
    }


        @Override
        protected List<AccountForm> getPageData(int itemStartNumber, int pageSize) {
                // TODO Auto-generated method stub
                return null;
        }
    
    	public int getTotalSize() {
    		// TODO Auto-generated method stub
    		return 0;
    	}
}

