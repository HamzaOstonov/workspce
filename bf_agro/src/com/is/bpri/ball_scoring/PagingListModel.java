package com.is.bpri.ball_scoring;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<BallScoringModel> implements BindingListModel{

	private static final long serialVersionUID = 1L;

	public PagingListModel(int startPageNumber, int pageSize, Object fl,
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
		BallScoringModel ball;
		if(fl!=null) ball = (BallScoringModel) fl;
		else ball = new BallScoringModel();
		return BallScoringService.getCount(ball, alias);
	}

	@Override
	protected List<BallScoringModel> getPageData(int itemStartNumber,
			int pageSize) {
		return null;
	}

	@Override
	protected List<BallScoringModel> getPageData(int itemStartNumber,
			int pageSize, Object fl, String alias) {
		BallScoringModel ball;
		if(fl!=null) {
			ball = (BallScoringModel) fl;
		} else {
			ball = new BallScoringModel();
		}
		return BallScoringService.getModel(ball,itemStartNumber,pageSize,alias);
	}

}
