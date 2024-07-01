package com.is.trtemplate;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<TrTemplate> implements BindingListModel {

    public PagingListModel(int startPageNumber, int pageSize, Object fl,String alias) {
    super(startPageNumber, pageSize,fl, alias);
    }
@Override
protected List<TrTemplate> getPageData(int itemStartNumber, int pageSize, Object fl,String alias) {
    TrTemplateFilter fc;
    if(fl !=null){
        fc = (TrTemplateFilter)fl;
}else{
        fc = new TrTemplateFilter();
}
    return TrTemplateService.getTrTemplatesFl(itemStartNumber, pageSize,fc, alias);
}

@Override
public int getTotalSize(Object fl,String alias)  {
    TrTemplateFilter fc;
    if(fl !=null){
        fc = (TrTemplateFilter)fl;
}else{
        fc = new TrTemplateFilter();
}
    return TrTemplateService.getCount(fc, alias);
}

@Override
public int indexOf(Object obj) {
        return 0;
}

    @Override
    protected List<TrTemplate> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	protected List<TrTemplate> getPageData(int itemStartNumber, int pageSize,
			Object fl, String alias, boolean sorted_desc)
	{
		// TODO Auto-generated method stub
		return null;
	}


}


