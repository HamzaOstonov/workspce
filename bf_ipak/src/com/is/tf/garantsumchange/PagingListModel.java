package com.is.tf.garantsumchange;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<Garantsumchange> implements BindingListModel {

    public PagingListModel(int startPageNumber, int pageSize, Object fl,String branch) {
    super(startPageNumber, pageSize,fl,branch);
    }
@Override
protected List<Garantsumchange> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
    GarantsumchangeFilter fc;
    if(fl !=null){
        fc = (GarantsumchangeFilter)fl;
}else{
        fc = new GarantsumchangeFilter();
}
    return GarantsumchangeService.getGarantsumchangesFl(itemStartNumber, pageSize,fc);
}

@Override
public int getTotalSize(Object fl, String branch)  {
    GarantsumchangeFilter fc;
    if(fl !=null){
        fc = (GarantsumchangeFilter)fl;
}else{
        fc = new GarantsumchangeFilter();
}
    return GarantsumchangeService.getCount(fc);
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<Garantsumchange> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}

}
