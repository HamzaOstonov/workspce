package com.is.tf.compensation;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<Compensation> implements BindingListModel {

    public PagingListModel(int startPageNumber, int pageSize, Object fl,String branch) {
    super(startPageNumber, pageSize,fl,branch);
    }
@Override
protected List<Compensation> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
    CompensationFilter fc;
    if(fl !=null){
        fc = (CompensationFilter)fl;
}else{
        fc = new CompensationFilter();
}
    return CompensationService.getCompensationsFl(itemStartNumber, pageSize,fc);
}

@Override
public int getTotalSize(Object fl, String branch)  {
    CompensationFilter fc;
    if(fl !=null){
        fc = (CompensationFilter)fl;
}else{
        fc = new CompensationFilter();
}
    return CompensationService.getCount(fc);
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<Compensation> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}



}
