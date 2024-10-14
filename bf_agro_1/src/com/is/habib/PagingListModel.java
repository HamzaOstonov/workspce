package com.is.habib;

import java.util.List;
import org.zkoss.zkplus.databind.BindingListModel;
import com.is.utils.AbstractPagingListModel;

@SuppressWarnings("serial")
public class PagingListModel extends AbstractPagingListModel<Movie> implements BindingListModel {

        public PagingListModel(int startPageNumber, int pageSize, Object fl, String alias) {
        super(startPageNumber, pageSize,fl, alias);
        }
    @Override
    protected List<Movie> getPageData(int itemStartNumber, int pageSize, Object fl, String alias) {
    	Movie fc;
        if(fl !=null){
            fc = (Movie)fl;
    }else{
            fc = new Movie();
    }
        //return HabibService.getAccountsFl(itemStartNumber, pageSize,fc, alias);
        return null;
    }

    @Override
    public int getTotalSize(Object fl, String alias)  {
    	Movie fc;
        if(fl !=null){
            fc = (Movie)fl;
    }else{
            fc = new Movie();
    }
        //return HabibService.getCount(fc, alias);
        return 0;
    }

    @Override
    public int indexOf(Object obj) {
            return 0;
    }


        @Override
        protected List<Movie> getPageData(int itemStartNumber, int pageSize) {
                // TODO Auto-generated method stub
                return null;
        }
    
    	public int getTotalSize() {
    		// TODO Auto-generated method stub
    		return 0;
    	}
}

