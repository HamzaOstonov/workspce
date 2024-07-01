package com.is.log;

	import java.util.Collections;
	import java.util.Comparator;
	import java.util.List;

	import org.zkoss.zkplus.databind.BindingListModel;
	import org.zkoss.zul.ListModelExt;

import com.is.utils.AbstractPagingListModel;

	public class PagingListModel  extends AbstractPagingListModel<Log> implements BindingListModel , 	
	ListModelExt{
		
		private List<Log> innerList_;

	    public PagingListModel(int startPageNumber, int pageSize, Object fl,String alias ) {
	    super(startPageNumber, pageSize,fl, alias );
	    }
	@Override
	protected List<Log> getPageData(int itemStartNumber, int pageSize, Object fl,String alias ) {
		LogFilter fc;
	    if(fl !=null){
	        fc = (LogFilter)fl;
	}else{
	        fc = new LogFilter();
	}
	    innerList_ = LogService.getLogFl(itemStartNumber, pageSize,fc, alias );
	    return innerList_;//TrPayService.getTrPaysFl(itemStartNumber, pageSize,fc);
	}

	@Override
	public int getTotalSize(Object fl,String alias )  {
		LogFilter fc;
	    if(fl !=null){
	        fc = (LogFilter)fl;
	}else{
	        fc = new LogFilter();
	}
	    return LogService.getCount(fc,alias );
	}


	public int indexOf(Object obj) {
	        return 0;
	}


	    @Override
	    protected List<Log> getPageData(int itemStartNumber, int pageSize) {
	            // TODO Auto-generated method stub
	            return null;
	    }
		@Override
		public int getTotalSize() {
			// TODO Auto-generated method stub
			return 0;
		}
		@Override
		public void sort(Comparator cmpr, boolean ascending) {
			// TODO Auto-generated method stub
			Collections.sort(getInnerList() , cmpr);
			fireEvent(org.zkoss.zul.event.ListDataEvent.CONTENTS_CHANGED, -1, -1);
			
		}
		
		private List<Log> getInnerList(){
			return innerList_;
		}
		@Override
		protected List<Log> getPageData(int itemStartNumber, int pageSize,
				Object fl, String alias, boolean sorted_desc)
		{
			// TODO Auto-generated method stub
			return null;
		}
}
