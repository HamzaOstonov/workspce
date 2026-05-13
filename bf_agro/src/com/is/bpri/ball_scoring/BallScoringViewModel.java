package com.is.bpri.ball_scoring;

public class BallScoringViewModel {

	private Long id;
	private String question;
	private String selectedAnswer;
	private Long result_ball;
	private boolean isVisible = false;
	
	public BallScoringViewModel() {
	
	}

	public BallScoringViewModel(Long id, String question,
			String selectedAnswer, Long result_ball) {
		super();
		this.id = id;
		this.question = question;
		this.selectedAnswer = selectedAnswer;
		this.result_ball = result_ball;
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

	public String getSelectedAnswer() {
		return selectedAnswer;
	}

	public void setSelectedAnswer(String selectedAnswer) {
		this.selectedAnswer = selectedAnswer;
	}

	public Long getResult_ball() {
		return result_ball;
	}

	public void setResult_ball(Long result_ball) {
		this.result_ball = result_ball;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	
}
