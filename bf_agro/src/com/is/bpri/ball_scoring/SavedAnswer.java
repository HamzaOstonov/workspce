package com.is.bpri.ball_scoring;

public class SavedAnswer {

	private Long id;
	private String question;
	private String selected_answer;
	private Long ball;
	
	public SavedAnswer() {
	
	}

	public SavedAnswer(Long id, String question, String selected_answer,
			Long ball) {
		super();
		this.id = id;
		this.question = question;
		this.selected_answer = selected_answer;
		this.ball = ball;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getSelected_answer() {
		return selected_answer;
	}

	public void setSelected_answer(String selected_answer) {
		this.selected_answer = selected_answer;
	}

	public Long getBall() {
		return ball;
	}

	public void setBall(Long ball) {
		this.ball = ball;
	}
	
}
