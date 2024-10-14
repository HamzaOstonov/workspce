
package com.is.tietovisa.customer;

public class CustomerFilter extends Customer
{
    private int tietoIdIsNotNull;
    //private String card;
    //private String client_b;
    private String endpoint;
    private String filter_type;
    
    public int getTietoIdIsNotNull() {
        return this.tietoIdIsNotNull;
    }
    
    public void setTietoIdIsNotNull(final int tietoIdIsNotNull) {
        this.tietoIdIsNotNull = tietoIdIsNotNull;
    }

	/*public void setCard(String card) {
		this.card = card;
	}

	public String getCard() {
		return card;
	}*/

	//public void setClient_b(String client_b) {
	//	this.client_b = client_b;
	//}

	//public String getClient_b() {
	//	return client_b;
	//}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setFilter_type(String filter_type) {
		this.filter_type = filter_type;
	}

	public String getFilter_type() {
		return filter_type;
	}
}
