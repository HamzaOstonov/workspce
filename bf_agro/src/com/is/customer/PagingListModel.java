package com.is.customer;

import java.util.List;
import org.zkoss.zkplus.databind.BindingListModel;
import com.is.utils.AbstractPagingListModel;

@SuppressWarnings("serial")
public class PagingListModel extends AbstractPagingListModel<Customer> implements BindingListModel {

        public PagingListModel(int startPageNumber, int pageSize, Object fl, String alias) {
        super(startPageNumber, pageSize,fl,alias);
        }
    @Override
    protected List<Customer> getPageData(int itemStartNumber, int pageSize, Object fl, String alias) {
        CustomerFilter fc;
        if(fl !=null){
            fc = (CustomerFilter)fl;
    }else{
            fc = new CustomerFilter();
    }
        return CustomerService.getCustomersFl(itemStartNumber, pageSize,fc, alias);
    }

    @Override
    public int getTotalSize(Object fl,String alias)  {
        CustomerFilter fc;
        if(fl !=null){
            fc = (CustomerFilter)fl;
    }else{
            fc = new CustomerFilter();
    }
        return CustomerService.getCount(fc,alias);
    }

    @Override
    public int indexOf(Object obj) {
            return 0;
    }


        @Override
        protected List<Customer> getPageData(int itemStartNumber, int pageSize) {
                // TODO Auto-generated method stub
                return null;
        }
		@Override
		public int getTotalSize() {
			// TODO Auto-generated method stub
			return 0;
		}
    

}


