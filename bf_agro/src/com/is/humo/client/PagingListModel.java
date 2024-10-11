package com.is.humo.client;

import java.util.List;
import org.zkoss.zkplus.databind.BindingListModel;
import com.is.utils.AbstractPagingListModel;

@SuppressWarnings("serial")
public class PagingListModel extends AbstractPagingListModel<HumoClient> implements BindingListModel {

        public PagingListModel(int startPageNumber, int pageSize, Object fl, String alias) {
        super(startPageNumber, pageSize,fl, alias);
        }
    protected List<HumoClient> getPageData(int itemStartNumber, int pageSize, Object fl) {
    	HumoClient fc;
        if(fl !=null){
            fc = (HumoClient)fl;
    }else{
            fc = new HumoClient();
    }
        return ClientService.getClientsFl(itemStartNumber, pageSize,fc);
    }

    @Override
    public int getTotalSize(Object fl)  {
    	HumoClient fc;
        if(fl !=null){
            fc = (HumoClient)fl;
    }else{
            fc = new HumoClient();
    }
        return ClientService.getCount(fc);
    }

    @Override
    public int indexOf(Object obj) {
            return 0;
    }
   

        @Override
        protected List<HumoClient> getPageData(int itemStartNumber, int pageSize) {
                // TODO Auto-generated method stub
                return null;
        }
		@Override
		public int getTotalSize() {
			// TODO Auto-generated method stub
			return 0;
		}
		@Override
		public int getTotalSize(Object fl, String alias) {
			// TODO Auto-generated method stub
			return 0;
		}
		@Override
		protected List<HumoClient> getPageData(int itemStartNumber, int pageSize, Object fl, String alias) {
			// TODO Auto-generated method stub
			return null;
		}
    

}

