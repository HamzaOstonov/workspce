package com.is.tf.Incoterms;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<Incoterms> implements BindingListModel {

    public PagingListModel(int startPageNumber, int pageSize, Object fl,String branch) {
    super(startPageNumber, pageSize,fl,branch);
    }
@Override
protected List<Incoterms> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
    IncotermsFilter fc;
    if(fl !=null){
        fc = (IncotermsFilter)fl;
}else{
        fc = new IncotermsFilter();
}
    return IncotermsService.getIncotermssFl(itemStartNumber, pageSize,fc);
}

@Override
public int getTotalSize(Object fl, String branch)  {
    IncotermsFilter fc;
    if(fl !=null){
        fc = (IncotermsFilter)fl;
}else{
        fc = new IncotermsFilter();
}
    return IncotermsService.getCount(fc);
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<Incoterms> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}

}
