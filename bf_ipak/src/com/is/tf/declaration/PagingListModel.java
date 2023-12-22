package com.is.tf.declaration;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<Declaration> implements BindingListModel {

    public PagingListModel(int startPageNumber, int pageSize, Object fl,String branch) {
    super(startPageNumber, pageSize,fl,branch);
    }
@Override
protected List<Declaration> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
    DeclarationFilter fc;
    if(fl !=null){
        fc = (DeclarationFilter)fl;
}else{
        fc = new DeclarationFilter();
}
    return DeclarationService.getDeclarationsFl(itemStartNumber, pageSize,fc);
}

@Override
public int getTotalSize(Object fl, String branch)  {
    DeclarationFilter fc;
    if(fl !=null){
        fc = (DeclarationFilter)fl;
}else{
        fc = new DeclarationFilter();
}
    return DeclarationService.getCount(fc);
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<Declaration> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}



}
