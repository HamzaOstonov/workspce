package com.is.hr;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModelpay_calc_LOG extends AbstractPagingListModel<pay_calc_LOG> implements BindingListModel {

    public PagingListModelpay_calc_LOG(int startPageNumber, int pageSize, Object fl,String branch) {
    super(startPageNumber, pageSize,fl,branch);
    }
@Override
protected List<pay_calc_LOG> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
    pay_calc_LOGFilter fc;
    if(fl !=null){
        fc = (pay_calc_LOGFilter)fl;
}else{
        fc = new pay_calc_LOGFilter();
}
    return pay_calc_LOGService.getpay_calc_LOGsFl(itemStartNumber, pageSize,fc);
}

@Override
public int getTotalSize(Object fl, String branch)  {
    pay_calc_LOGFilter fc;
    if(fl !=null){
        fc = (pay_calc_LOGFilter)fl;
}else{
        fc = new pay_calc_LOGFilter();
}
    return pay_calc_LOGService.getCount(fc);
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<pay_calc_LOG> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}


}

