package com.is.tietovisa.customer;

public class CardActions {
	   private String card;
	   private String date;
	   private String action;
	   private String user;

	   public CardActions() {
	   }

	   public CardActions(String card, String date, String action, String user) {
	      this.card = card;
	      this.date = date;
	      this.action = action;
	      this.setUser(user);
	   }

	   public String getCard() {
	      return this.card;
	   }

	   public void setCard(String card) {
	      this.card = card;
	   }

	   public String getDate() {
	      return this.date;
	   }

	   public void setDate(String date) {
	      this.date = date;
	   }

	   public String getAction() {
	      return this.action;
	   }

	   public void setAction(String action) {
	      this.action = action;
	   }

	   public void setUser(String user) {
	      this.user = user;
	   }

	   public String getUser() {
	      return this.user;
	   }
	}