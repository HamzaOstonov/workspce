package com.is.tf.lease;

import java.util.Date;

public class Lease {
	  private Long id;
	    private String p1t50;
	    private String p0t50;
	    private Date p2t50;
	    private String p3t50;
	    private Double p4t50;
	    private String p5t50;
	    private String p6t50;
	    private Date p9t50;
	    private String p100t50;

	    public Lease() {
	    	super();
	    }

		public Lease(Long id, String p1t50, String p0t50, Date p2t50,
				String p3t50, Double p4t50, String p5t50, String p6t50,
				Date p9t50, String p100t50) {
			super();
			this.id = id;
			this.p1t50 = p1t50;
			this.p0t50 = p0t50;
			this.p2t50 = p2t50;
			this.p3t50 = p3t50;
			this.p4t50 = p4t50;
			this.p5t50 = p5t50;
			this.p6t50 = p6t50;
			this.p9t50 = p9t50;
			this.p100t50 = p100t50;
		}
		



		public Lease(Date p2t50, String p3t50, Double p4t50,
				String p6t50) {
			super();
			this.p2t50 = p2t50;
			this.p3t50 = p3t50;
			this.p4t50 = p4t50;
			
			
			this.p6t50 = p6t50;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getP1t50() {
			return p1t50;
		}

		public void setP1t50(String p1t50) {
			this.p1t50 = p1t50;
		}

		public String getP0t50() {
			return p0t50;
		}

		public void setP0t50(String p0t50) {
			this.p0t50 = p0t50;
		}

		public Date getP2t50() {
			return p2t50;
		}

		public void setP2t50(Date p2t50) {
			this.p2t50 = p2t50;
		}

		public String getP3t50() {
			return p3t50;
		}

		public void setP3t50(String p3t50) {
			this.p3t50 = p3t50;
		}

		public Double getP4t50() {
			return p4t50;
		}

		public void setP4t50(Double p4t50) {
			this.p4t50 = p4t50;
		}

		public String getP5t50() {
			return p5t50;
		}

		public void setP5t50(String p5t50) {
			this.p5t50 = p5t50;
		}

		public String getP6t50() {
			return p6t50;
		}

		public void setP6t50(String p6t50) {
			this.p6t50 = p6t50;
		}

		public Date getP9t50() {
			return p9t50;
		}

		public void setP9t50(Date p9t50) {
			this.p9t50 = p9t50;
		}

		public String getP100t50() {
			return p100t50;
		}

		public void setP100t50(String p100t50) {
			this.p100t50 = p100t50;
		}
	    
}
