package com.is.doc;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

@SuppressWarnings("serial")
public class PagingListModel extends AbstractPagingListModel<Doc> implements BindingListModel {

    public PagingListModel(int startPageNumber, int pageSize, Object fl, String alias) {
    super(startPageNumber, pageSize,fl,alias);
    }
@Override
protected List<Doc> getPageData(int itemStartNumber, int pageSize, Object fl, String alias) {
    DocFilter fc;
    if(fl !=null){
        fc = (DocFilter)fl;
}else{
        fc = new DocFilter();
}
    return DocService.getDocsFl(itemStartNumber, pageSize,fc,alias);
}

@Override
public int getTotalSize(Object fl,String alias)  {
    DocFilter fc;
    if(fl !=null){
        fc = (DocFilter)fl;
}else{
        fc = new DocFilter();
}
    return DocService.getCount(fc, alias);
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<Doc> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	protected List<Doc> getPageData(int itemStartNumber, int pageSize,
			Object fl, String alias, boolean sorted_desc)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
