package com.is.openwayutils.user;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.openwayutils.utils.AbstractPagingListModel;

@SuppressWarnings("serial")
public class PagingListModel extends AbstractPagingListModel<User> implements BindingListModel {
	//private String branch;

    public PagingListModel(int startPageNumber, int pageSize, Object fl , String alias) {
    super(startPageNumber, pageSize,fl, alias);
    }

@Override
protected List<User> getPageData(int itemStartNumber, int pageSize, Object fl, String alias) {
    UserFilter fc;
    if(fl !=null){
        fc = (UserFilter)fl;
}else{
        fc = new UserFilter();
}
    return UserService.getUsersFl(itemStartNumber, pageSize,fc,alias);
}

@Override
public int getTotalSize(Object fl, String alias)  {
    UserFilter fc;
    if(fl !=null){
        fc = (UserFilter)fl;
}else{
        fc = new UserFilter();
}
    return UserService.getCount(fc, alias);
}


@Override
public int indexOf(Object obj) {
    
        return 0;
}

    @Override
    protected List<User> getPageData(int itemStartNumber, int pageSize) {

            return null;
    }
	@Override
	public int getTotalSize() {

		return 0;
	}

	@Override
	protected List<User> getPageData(int itemStartNumber, int pageSize,
			Object fl, String alias, boolean sorted_desc)
	{

		return null;
	}


}

