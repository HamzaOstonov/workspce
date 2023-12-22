package com.is.tf.exchangepayment;

import java.util.Date;
//Оплата по контракту через биржу» (ИМ)
public class ExchangePayment {
	  private Long id;
	    private String p1t51;
	    private String p0t51;
	    private Date p2t51;
	    private Double p3t51;
	    private Double p4t51;
	    private Double p5t51;
	    private Double p6t51;
	    private Double p7t51;
	    private String p8t51;

	    public ExchangePayment() {
	    	super();
	    }

		public ExchangePayment(Long id, String p1t51, String p0t51, Date p2t51,
				Double p3t51, Double p4t51, Double p5t51, Double p6t51,
				Double p7t51, String p8t51) {
			super();
			this.id = id;
			this.p1t51 = p1t51;
			this.p0t51 = p0t51;
			this.p2t51 = p2t51;
			this.p3t51 = p3t51;
			this.p4t51 = p4t51;
			this.p5t51 = p5t51;
			this.p6t51 = p6t51;
			this.p7t51 = p7t51;
			this.p8t51 = p8t51;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getP1t51() {
			return p1t51;
		}

		public void setP1t51(String p1t51) {
			this.p1t51 = p1t51;
		}

		public String getP0t51() {
			return p0t51;
		}

		public void setP0t51(String p0t51) {
			this.p0t51 = p0t51;
		}

		public Date getP2t51() {
			return p2t51;
		}

		public void setP2t51(Date p2t51) {
			this.p2t51 = p2t51;
		}

		public Double getP3t51() {
			return p3t51;
		}

		public void setP3t51(Double p3t51) {
			this.p3t51 = p3t51;
		}

		public Double getP4t51() {
			return p4t51;
		}

		public void setP4t51(Double p4t51) {
			this.p4t51 = p4t51;
		}

		public Double getP5t51() {
			return p5t51;
		}

		public void setP5t51(Double p5t51) {
			this.p5t51 = p5t51;
		}

		public Double getP6t51() {
			return p6t51;
		}

		public void setP6t51(Double p6t51) {
			this.p6t51 = p6t51;
		}

		public Double getP7t51() {
			return p7t51;
		}

		public void setP7t51(Double p7t51) {
			this.p7t51 = p7t51;
		}

		public String getP8t51() {
			return p8t51;
		}

		public void setP8t51(String p8t51) {
			this.p8t51 = p8t51;
		}
	    
}
