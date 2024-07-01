package com.is.tracc;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

@SuppressWarnings("serial")
public class PagingListModel extends AbstractPagingListModel<TrAcc> implements BindingListModel {

    public PagingListModel(int startPageNumber, int pageSize, Object fl, String alias) {
    super(startPageNumber, pageSize,fl, alias);
    }
@Override
protected List<TrAcc> getPageData(int itemStartNumber, int pageSize, Object fl,String alias) {
    TrAccFilter fc;
    if(fl !=null){
        fc = (TrAccFilter)fl;
}else{
        fc = new TrAccFilter();
}
    return TrAccService.getTrAccsFl(itemStartNumber, pageSize,fc, alias);
}

@Override
public int getTotalSize(Object fl,String alias)  {
    TrAccFilter fc;
    if(fl !=null){
        fc = (TrAccFilter)fl;
}else{
        fc = new TrAccFilter();
}
    return TrAccService.getCount(fc, alias);
}

@Override
public int indexOf(Object obj) {
        return 0;
}

    @Override
    protected List<TrAcc> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	protected List<TrAcc> getPageData(int itemStartNumber, int pageSize,
			Object fl, String alias, boolean sorted_desc)
	{
		// TODO Auto-generated method stub
		return null;
	}


}


