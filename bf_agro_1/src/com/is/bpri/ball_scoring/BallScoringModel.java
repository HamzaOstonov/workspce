package com.is.bpri.ball_scoring;

import java.util.List;

public class BallScoringModel {

	private Integer bpr_id;
	private Long q_id;
	private String question_name;
	private Long q_order;
	private Long map_id;
	private List<AnswerModel> answers;
	private boolean isDeleted = false;
	private boolean isNew = false;
	private boolean isVisible = true;
	
	public BallScoringModel() {

	}

	public BallScoringModel(Integer bpr_id, Long q_id, String question_name,
			Long q_order, Long map_id, List<AnswerModel> answers) {
		super();
		this.bpr_id = bpr_id;
		this.q_id = q_id;
		this.question_name = question_name;
		this.q_order = q_order;
		this.map_id = map_id;
		this.answers = answers;
	}

	public Integer getBpr_id() {
		return bpr_id;
	}

	public void setBpr_id(Integer bpr_id) {
		this.bpr_id = bpr_id;
	}

	public Long getQ_id() {
		return q_id;
	}

	public void setQ_id(Long q_id) {
		this.q_id = q_id;
	}

	public String getQuestion_name() {
		return question_name;
	}

	public void setQuestion_name(String question_name) {
		this.question_name = question_name;
	}

	public Long getQ_order() {
		return q_order;
	}

	public void setQ_order(Long q_order) {
		this.q_order = q_order;
	}

	public Long getMap_id() {
		return map_id;
	}

	public void setMap_id(Long map_id) {
		this.map_id = map_id;
	}

	public List<AnswerModel> getAnswers() {
		return answers;
	}

	public void setAnswers(List<AnswerModel> answers) {
		this.answers = answers;
	}
	
	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isNew = !isDeleted;
		if(getAnswers()!=null){
			for (int i = 0; i < getAnswers().size(); i++) {
				getAnswers().get(i).setDeleted(isDeleted);
			}
		}
		this.isDeleted = isDeleted;
	}
	
	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}
	
	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	@Override
	public String toString() {
		return "BallScoringModel [bpr_id=" + bpr_id + ", q_id=" + q_id
				+ ", question_name=" + question_name + ", q_order=" + q_order
				+ ", map_id=" + map_id + ", answers=" + answers + "]";
	}
	
}
