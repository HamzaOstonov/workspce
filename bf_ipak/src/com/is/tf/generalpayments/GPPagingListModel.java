package com.is.tf.generalpayments;

import java.util.List;
import org.zkoss.zkplus.databind.BindingListModel;
import com.is.utils.AbstractPagingListModel;

@SuppressWarnings("serial")
public class GPPagingListModel extends AbstractPagingListModel<General> implements BindingListModel {

        public GPPagingListModel(int startPageNumber, int pageSize, Object fl, String alias) {
        super(startPageNumber, pageSize,fl, alias);
        }
    @Override
    protected List<General> getPageData(int itemStartNumber, int pageSize, Object fl, String alias) {
    	GeneralFilter fc;
        if(fl !=null){
            fc = (GeneralFilter)fl;
    }else{
            fc = new GeneralFilter();
    }
        return GeneralPaymentService.getGeneralsFl(itemStartNumber, pageSize,fc, alias);
    }

    @Override
    public int getTotalSize(Object fl, String alias)  {
        GeneralFilter fc;
        if(fl !=null){
            fc = (GeneralFilter)fl;
    }else{
            fc = new GeneralFilter();
    }
        return GeneralPaymentService.getCount(fc, alias);
    }

    @Override
    public int indexOf(Object obj) {
            return 0;
    }


        @Override
        protected List<General> getPageData(int itemStartNumber, int pageSize) {
                // TODO Auto-generated method stub
                return null;
        }
    
    	public int getTotalSize() {
    		// TODO Auto-generated method stub
    		return 0;
    	}
}

