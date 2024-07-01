package com.is.trpay;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;
import org.zkoss.zul.ListModelExt;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel  extends AbstractPagingListModel<TrPay> implements BindingListModel , 	
ListModelExt{
	
	private List<TrPay> innerList_;

    public PagingListModel(int startPageNumber, int pageSize,String alias ) {
    super(startPageNumber, pageSize,null, alias );
    }
    public PagingListModel(int startPageNumber, int pageSize, Object fl,String alias ) {
        super(startPageNumber, pageSize,fl, alias );
        }
    public PagingListModel(int startPageNumber, int pageSize, Object fl,String alias, boolean sorted_desc ) {
        super(startPageNumber, pageSize,fl, alias,  sorted_desc);
        }
    @Override
protected List<TrPay> getPageData(int itemStartNumber, int pageSize, Object fl,String alias,boolean sorted_desc ) {
    TrPayFilter fc;
    if(fl !=null){
        fc = (TrPayFilter)fl;
}else{
        fc = new TrPayFilter();
}
    innerList_ = TrPayService.getTrPaysFl(itemStartNumber, pageSize,fc, alias, sorted_desc );
    return innerList_;//TrPayService.getTrPaysFl(itemStartNumber, pageSize,fc);
}

@Override
public int getTotalSize(Object fl,String alias )  {
    TrPayFilter fc;
    if(fl !=null){
        fc = (TrPayFilter)fl;
}else{
        fc = new TrPayFilter();
}
    return TrPayService.getCount(fc,alias );
}


public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<TrPay> getPageData(int itemStartNumber, int pageSize) {
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
	
	private List<TrPay> getInnerList(){
		return innerList_;
	}
	@Override
	protected List<TrPay> getPageData(int itemStartNumber, int pageSize,
			Object fl, String alias)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
