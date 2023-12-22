package com.is.tf.garant;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<Garant> implements BindingListModel {

    public PagingListModel(int startPageNumber, int pageSize, Object fl,String branch) {
    super(startPageNumber, pageSize,fl,branch);
    }
@Override
protected List<Garant> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
    GarantFilter fc;
    if(fl !=null){
        fc = (GarantFilter)fl;
}else{
        fc = new GarantFilter();
}
    return GarantService.getGarantsFl(itemStartNumber, pageSize,fc);
}

@Override
public int getTotalSize(Object fl, String branch)  {
    GarantFilter fc;
    if(fl !=null){
        fc = (GarantFilter)fl;
}else{
        fc = new GarantFilter();
}
    return GarantService.getCount(fc);
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<Garant> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}

}
