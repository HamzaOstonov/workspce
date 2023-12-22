package com.is.tf.Accreditivtimechange;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<Accreditivtimechange> implements BindingListModel {

    public PagingListModel(int startPageNumber, int pageSize, Object fl,String branch) {
    super(startPageNumber, pageSize,fl,branch);
    }
@Override
protected List<Accreditivtimechange> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
    AccreditivtimechangeFilter fc;
    if(fl !=null){
        fc = (AccreditivtimechangeFilter)fl;
}else{
        fc = new AccreditivtimechangeFilter();
}
    return AccreditivtimechangeService.getAccreditivtimechangesFl(itemStartNumber, pageSize,fc);
}

@Override
public int getTotalSize(Object fl, String branch)  {
    AccreditivtimechangeFilter fc;
    if(fl !=null){
        fc = (AccreditivtimechangeFilter)fl;
}else{
        fc = new AccreditivtimechangeFilter();
}
    return AccreditivtimechangeService.getCount(fc);
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<Accreditivtimechange> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}

}
