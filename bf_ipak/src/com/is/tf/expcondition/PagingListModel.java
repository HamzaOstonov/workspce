package com.is.tf.expcondition;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<Expcondition> implements BindingListModel {

    public PagingListModel(int startPageNumber, int pageSize, Object fl,String branch) {
    super(startPageNumber, pageSize,fl,branch);
    }
@Override
protected List<Expcondition> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
    ExpconditionFilter fc;
    if(fl !=null){
        fc = (ExpconditionFilter)fl;
}else{
        fc = new ExpconditionFilter();
}
    return ExpconditionService.getExpconditionsFl(itemStartNumber, pageSize,fc);
}

@Override
public int getTotalSize(Object fl, String branch)  {
    ExpconditionFilter fc;
    if(fl !=null){
        fc = (ExpconditionFilter)fl;
}else{
        fc = new ExpconditionFilter();
}
    return ExpconditionService.getCount(fc);
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<Expcondition> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}
}
