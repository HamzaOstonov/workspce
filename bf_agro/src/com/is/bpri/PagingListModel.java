package com.is.bpri;


import java.util.List;
import org.zkoss.zkplus.databind.BindingListModel;
import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<Bproduct> implements BindingListModel {

    public PagingListModel(int startPageNumber, int pageSize, Object fl, String alias) {
    super(startPageNumber, pageSize,fl, alias);
    }
@Override
protected List<Bproduct> getPageData(int itemStartNumber, int pageSize, Object fl, String alias) {
    BproductFilter fc;
    if(fl !=null){
        fc = (BproductFilter)fl;
}else{
        fc = new BproductFilter();
}
    return BproductService.getBproductsFl(itemStartNumber, pageSize,fc, alias);
}

@Override
public int getTotalSize(Object fl, String alias)  {
    BproductFilter fc;
    if(fl !=null){
        fc = (BproductFilter)fl;
}else{
        fc = new BproductFilter();
}
    return BproductService.getCount(fc,alias);
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<Bproduct> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}


}
