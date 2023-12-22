package com.is.tf.transcost;

public class TranscostFilter {
	 private Long id;
	    private String p0t11;
	    private String p1t11;
	    private String p2t11;
	    private String p3t11;
	    private Double p4t11;

	    public TranscostFilter() {
	    	super();
	    }

		public TranscostFilter(Long id, String p0t11, String p1t11, String p2t11,
				String p3t11, Double p4t11) {
			super();
			this.id = id;
			this.p0t11 = p0t11;
			this.p1t11 = p1t11;
			this.p2t11 = p2t11;
			this.p3t11 = p3t11;
			this.p4t11 = p4t11;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getP0t11() {
			return p0t11;
		}

		public void setP0t11(String p0t11) {
			this.p0t11 = p0t11;
		}

		public String getP1t11() {
			return p1t11;
		}

		public void setP1t11(String p1t11) {
			this.p1t11 = p1t11;
		}

		public String getP2t11() {
			return p2t11;
		}

		public void setP2t11(String p2t11) {
			this.p2t11 = p2t11;
		}

		public String getP3t11() {
			return p3t11;
		}

		public void setP3t11(String p3t11) {
			this.p3t11 = p3t11;
		}

		public Double getP4t11() {
			return p4t11;
		}

		public void setP4t11(Double p4t11) {
			this.p4t11 = p4t11;
		}
	    
}
