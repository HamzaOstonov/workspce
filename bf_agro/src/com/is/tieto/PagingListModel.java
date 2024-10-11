package com.is.tieto;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

@SuppressWarnings("serial")
public class PagingListModel extends AbstractPagingListModel<Tclient> implements BindingListModel {

        public PagingListModel(int startPageNumber, int pageSize, Object fl) {
        super(startPageNumber, pageSize,fl,"");
        }
    @Override
    protected List<Tclient> getPageData(int itemStartNumber, int pageSize, Object fl,String alias) {
        TclientFilter fc;
        if(fl !=null){
            fc = (TclientFilter)fl;
    }else{
            fc = new TclientFilter();
    }
        return TclientService.getTclientsFl(itemStartNumber, pageSize,fc,alias);
    }

    @Override
    public int getTotalSize(Object fl, String alias)  {
        TclientFilter fc;
        if(fl !=null){
            fc = (TclientFilter)fl;
    }else{
            fc = new TclientFilter();
    }
        return TclientService.getCount(fc);
    }

    @Override

    public int indexOf(Object obj) {
            // TODO Auto-generated method stub
            return 0;
    }

        @Override
        protected List<Tclient> getPageData(int itemStartNumber, int pageSize) {
                // TODO Auto-generated method stub
                return null;
        }
		@Override
		public int getTotalSize() {
			// TODO Auto-generated method stub
			return 0;
		}
}
