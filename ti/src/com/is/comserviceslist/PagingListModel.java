package com.is.comserviceslist;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<ComServicesList> implements BindingListModel {

    public PagingListModel(int startPageNumber, int pageSize, Object fl,String alias) {
    super(startPageNumber, pageSize,fl,alias);
    }
@Override
protected List<ComServicesList> getPageData(int itemStartNumber, int pageSize, Object fl,String alias) {
    ComServicesListFilter fc;
    if(fl !=null){
        fc = (ComServicesListFilter)fl;
}else{
        fc = new ComServicesListFilter();
}
    return ComServicesListService.getComServicesListsFl(itemStartNumber, pageSize,fc,alias);
}

@Override
public int getTotalSize(Object fl,String alias)  {
    ComServicesListFilter fc;
    if(fl !=null){
        fc = (ComServicesListFilter)fl;
}else{
        fc = new ComServicesListFilter();
}
    return ComServicesListService.getCount(fc,alias);
}

@Override
public int indexOf(Object obj) {
        return 0;
}
@Override
public int getTotalSize() {
	// TODO Auto-generated method stub
	return 0;
}
@Override
protected List<ComServicesList> getPageData(int itemStartNumber, int pageSize) {
	// TODO Auto-generated method stub
	return null;
}
@Override
protected List<ComServicesList> getPageData(int itemStartNumber, int pageSize,
		Object fl, String alias, boolean sorted_desc)
{
	// TODO Auto-generated method stub
	return null;
}

}
