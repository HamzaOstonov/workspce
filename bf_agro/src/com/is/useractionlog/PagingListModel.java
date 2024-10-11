package com.is.useractionlog;


import java.util.List;
import org.zkoss.zkplus.databind.BindingListModel;
import com.is.utils.AbstractPagingListModel;

public class PagingListModel  extends AbstractPagingListModel<UserActionLog> implements BindingListModel{
    public PagingListModel(int startPageNumber, int pageSize, Object fl, String alias) {
        super(startPageNumber, pageSize,fl,alias);
        }
    @Override
    protected List<UserActionLog> getPageData(int itemStartNumber, int pageSize, Object fl, String alias) {
        UserActionLogFilter fc;
        if(fl !=null){
            fc = (UserActionLogFilter)fl;
    }else{
            fc = new UserActionLogFilter();
    }
        return UserActionLogService.getUserActionLogsFl(itemStartNumber, pageSize,fc, alias);
    }

    @Override
    public int getTotalSize(Object fl,String alias)  {
        UserActionLogFilter fc;
        if(fl !=null){
            fc = (UserActionLogFilter)fl;
    }else{
            fc = new UserActionLogFilter();
    }
        return UserActionLogService.getCount(fc, alias);
    }

    @Override
    public int indexOf(Object obj) {
            return 0;
    }


        @Override
        protected List<UserActionLog> getPageData(int itemStartNumber, int pageSize) {
                // TODO Auto-generated method stub
                return null;
        }
		@Override
		public int getTotalSize() {
			// TODO Auto-generated method stub
			return 0;
		}
}
