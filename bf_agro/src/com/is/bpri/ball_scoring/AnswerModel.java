package com.is.bpri.ball_scoring;

import java.util.List;

public class AnswerModel {

	private Long q_id;
	private Long a_id;
	private String a_name;
	private Long a_ball;
	private Integer type_answer;
	private List<BallScoringModel> ballScoringModels;
	private boolean isDeleted = false;
	private boolean isNew = false;
	
	public AnswerModel() {
	
	}

	public AnswerModel(Long q_id, Long a_id, String a_name, Long a_ball,Integer type_answer,List<BallScoringModel> ballScoringModels) {
		super();
		this.q_id = q_id;
		this.a_id = a_id;
		this.a_name = a_name;
		this.a_ball = a_ball;
		this.type_answer = type_answer;
		this.ballScoringModels = ballScoringModels;
	}

	public Long getQ_id() {
		return q_id;
	}

	public void setQ_id(Long q_id) {
		this.q_id = q_id;
	}

	public Long getA_id() {
		return a_id;
	}

	public void setA_id(Long a_id) {
		this.a_id = a_id;
	}

	public String getA_name() {
		return a_name;
	}

	public void setA_name(String a_name) {
		this.a_name = a_name;
	}

	public Long getA_ball() {
		return a_ball;
	}

	public void setA_ball(Long a_ball) {
		this.a_ball = a_ball;
	}
	
	public Integer getType_answer() {
		return type_answer;
	}

	public void setType_answer(Integer type_answer) {
		this.type_answer = type_answer;
	}

	public List<BallScoringModel> getBallScoringModels() {
		return ballScoringModels;
	}

	public void setBallScoringModels(List<BallScoringModel> ballScoringModels) {
		this.ballScoringModels = ballScoringModels;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isNew = !isDeleted;
		if(getBallScoringModels()!=null){
			for (int i = 0; i < getBallScoringModels().size(); i++) {
				getBallScoringModels().get(i).setDeleted(isDeleted);
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
	
}
