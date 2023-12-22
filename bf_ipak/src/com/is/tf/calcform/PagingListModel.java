package com.is.tf.calcform;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<Calcform> implements BindingListModel {

    public PagingListModel(int startPageNumber, int pageSize, Object fl,String branch) {
    super(startPageNumber, pageSize,fl,branch);
    }
@Override
protected List<Calcform> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
    CalcformFilter fc;
    if(fl !=null){
        fc = (CalcformFilter)fl;
}else{
        fc = new CalcformFilter();
}
    return CalcformService.getCalcformsFl(itemStartNumber, pageSize,fc);
}

@Override
public int getTotalSize(Object fl, String branch)  {
    CalcformFilter fc;
    if(fl !=null){
        fc = (CalcformFilter)fl;
}else{
        fc = new CalcformFilter();
}
    return CalcformService.getCount(fc);
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<Calcform> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}
}
