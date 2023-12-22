package com.is.tf.specification;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<Specification> implements BindingListModel {

    public PagingListModel(int startPageNumber, int pageSize, Object fl,String branch) {
    super(startPageNumber, pageSize,fl,branch);
    }
@Override
protected List<Specification> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
    SpecificationFilter fc;
    if(fl !=null){
        fc = (SpecificationFilter)fl;
}else{
        fc = new SpecificationFilter();
}
    return SpecificationService.getSpecificationsFl(itemStartNumber, pageSize,fc);
}

@Override
public int getTotalSize(Object fl, String branch)  {
    SpecificationFilter fc;
    if(fl !=null){
        fc = (SpecificationFilter)fl;
}else{
        fc = new SpecificationFilter();
}
    return SpecificationService.getCount(fc);
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<Specification> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}

}
