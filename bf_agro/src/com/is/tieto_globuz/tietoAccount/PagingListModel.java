package com.is.tieto_globuz.tietoAccount;

import java.util.List;
import org.zkoss.zkplus.databind.BindingListModel;
import com.is.utils.AbstractPagingListModel;

@SuppressWarnings("serial")
public class PagingListModel extends AbstractPagingListModel<GlobuzAccount> implements BindingListModel {

        public PagingListModel(int startPageNumber, int pageSize, Object fl, String alias) {
        super(startPageNumber, pageSize,fl, alias);
        }
    @Override
    protected List<GlobuzAccount> getPageData(int itemStartNumber, int pageSize, Object fl, String alias) {
    	System.out.println("itemStartNumber: "+itemStartNumber);
        GlobuzAccountFilter fc;
        if(fl !=null){
            fc = (GlobuzAccountFilter)fl;
    }else{
            fc = new GlobuzAccountFilter();
    }
        return GlobuzAccountService.getAccountsFl(itemStartNumber, pageSize,fc, alias);
    }

    @Override
    public int getTotalSize(Object fl, String alias)  {
        GlobuzAccountFilter fc;
        if(fl !=null){
            fc = (GlobuzAccountFilter)fl;
    }else{
            fc = new GlobuzAccountFilter();
    }
        return GlobuzAccountService.getCount(fc, alias);
    }

    @Override
    public int indexOf(Object obj) {
            return 0;
    }


        @Override
        protected List<GlobuzAccount> getPageData(int itemStartNumber, int pageSize) {
                // TODO Auto-generated method stub
                return null;
        }
    
    	public int getTotalSize() {
    		// TODO Auto-generated method stub
    		return 0;
    	}
}

