package com.is.tf.policytimechange;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<Policytimechange> implements BindingListModel {

    public PagingListModel(int startPageNumber, int pageSize, Object fl,String branch) {
    super(startPageNumber, pageSize,fl,branch);
    }
@Override
protected List<Policytimechange> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
    PolicytimechangeFilter fc;
    if(fl !=null){
        fc = (PolicytimechangeFilter)fl;
}else{
        fc = new PolicytimechangeFilter();
}
    return PolicytimechangeService.getPolicytimechangesFl(itemStartNumber, pageSize,fc);
}

@Override
public int getTotalSize(Object fl, String branch)  {
    PolicytimechangeFilter fc;
    if(fl !=null){
        fc = (PolicytimechangeFilter)fl;
}else{
        fc = new PolicytimechangeFilter();
}
    return PolicytimechangeService.getCount(fc);
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<Policytimechange> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}

}
