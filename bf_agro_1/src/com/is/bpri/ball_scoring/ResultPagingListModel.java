package com.is.bpri.ball_scoring;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class ResultPagingListModel extends AbstractPagingListModel<ResultScoringModel> implements BindingListModel{

	private static final long serialVersionUID = 1L;

	public ResultPagingListModel(int startPageNumber, int pageSize, Object fl,
			String alias) {
		super(startPageNumber, pageSize, fl, alias);
	}

	@Override
	public int indexOf(Object arg0) {
		return 0;
	}

	@Override
	public int getTotalSize() {
		return 0;
	}

	@Override
	public int getTotalSize(Object fl, String alias) {
		ResultScoringModel result;
		if(fl!=null) result = (ResultScoringModel) fl;
		else result = new ResultScoringModel();
		return BallScoringService.getCountResult(result, alias);
	}

	@Override
	protected List<ResultScoringModel> getPageData(int itemStartNumber,
			int pageSize) {
		return null;
	}

	@Override
	protected List<ResultScoringModel> getPageData(int itemStartNumber,
			int pageSize, Object fl, String alias) {
		ResultScoringModel result;
		if(fl!=null) result = (ResultScoringModel) fl;
		else result = new ResultScoringModel();
		return BallScoringService.getModel(result, itemStartNumber, pageSize, alias);
	}

}
