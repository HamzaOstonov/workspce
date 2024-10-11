package com.is.client_acc.fileProcessing;

import com.is.utils.AbstractPagingListModel;
import java.util.List;
import org.zkoss.zkplus.databind.BindingListModel;

public class PagingListModel extends AbstractPagingListModel<File> implements BindingListModel {

	private static final long serialVersionUID = 1L;

    public PagingListModel(int startPageNumber, int pageSize, Object fl, String branch) {
        super(startPageNumber, pageSize, fl, branch);
    }

    protected List<File> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
        FileFilter fc;
        if (fl != null) {
            fc = (FileFilter)fl;
        } else {
            fc = new FileFilter();
        }

        return FileService.getFilesFl(itemStartNumber, pageSize, fc);
    }

    public int getTotalSize(Object fl, String branch) {
        FileFilter fc;
        if (fl != null) {
            fc = (FileFilter)fl;
        } else {
            fc = new FileFilter();
        }

        return FileService.getCount(fc);
    }

    public int indexOf(Object obj) {
        return 0;
    }

    protected List<File> getPageData(int itemStartNumber, int pageSize) {
        return null;
    }

    public int getTotalSize() {
        return 0;
    }
	
}
