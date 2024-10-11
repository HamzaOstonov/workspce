package com.is.SwiftBuffer;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<SwiftBuffer> implements BindingListModel {

    public PagingListModel(int startPageNumber, int pageSize, Object fl,String branch) {
    super(startPageNumber, pageSize,fl,branch);
    }
@Override
protected List<SwiftBuffer> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
    SwiftBufferFilter fc;
    if(fl !=null){
        fc = (SwiftBufferFilter)fl;
}else{
        fc = new SwiftBufferFilter();
}
    return SwiftBufferService.getSwiftBuffersFl(itemStartNumber, pageSize,fc, branch);
}

@Override
public int getTotalSize(Object fl, String branch)  {
    SwiftBufferFilter fc;
    if(fl !=null){
        fc = (SwiftBufferFilter)fl;
}else{
        fc = new SwiftBufferFilter();
}
    return SwiftBufferService.getCount(fc,branch);
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<SwiftBuffer> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}


}
