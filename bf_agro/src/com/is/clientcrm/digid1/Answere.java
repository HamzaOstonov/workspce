package com.is.clientcrm.digid1;

public class Answere {
private int AnswereId;
private String AnswereComment;

public Answere(int answereId, String answereComment) {
	super();
	AnswereId = answereId;
	AnswereComment = answereComment;
}

public int getAnswereId() {
	return AnswereId;
}

public void setAnswereId(int answereId) {
	AnswereId = answereId;
}

public String getAnswereComment() {
	return AnswereComment;
}

public void setAnswereComment(String answereComment) {
	AnswereComment = answereComment;
}

}
