package com.is.clients.models;

import java.sql.CallableStatement;
import java.sql.Connection;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.base.utils.DbUtils;
import com.is.client_personmap.model.LegalEntity;
import com.is.client_personmap.model.Person;
import com.is.utils.CheckNull;

import lombok.Getter;
import lombok.ToString;

@ToString
public class SapLogger {
	
    private final String alias;
    private final String branch;
    private final String idClient;
    private final String idSap;
    private final int action;
    private String customer_type;
    private int state;
    private String message;
    
    public static final int STATE_INSERTED = 1;
    public static final int STATE_SENT = 2;
    public static final int STATE_ERROR = 3;
    public static final int STATE_EMM_MODE = 4;
    public static final int ACTION_CREATE = 1;
    public static final int ACTION_EDIT = 19;
    
    public static final String CUSTOMER_TYPE_CLIENT_J = "02";
    public static final String CUSTOMER_TYPE_LEGAL_FOUNDER = "06";
    public static final String CUSTOMER_TYPE_PERSON_FOUNDER = "05";
    public static final String CUSTOMER_TYPE_CONTACT_PERSON = "04";
	
    public SapLogger(Builder builder) {
    	this.alias = builder.getAlias();
    	this.branch = builder.getBranch();
    	this.idClient = builder.getIdClient();
    	this.action = builder.getAction();
    	this.idSap = builder.getIdSap();
    	this.customer_type = builder.getCustomer_type();
    	this.state = builder.getState();
    	this.message = builder.getMessage();
    }
    
    
    public void log(){
    	Connection c = null;
    	CallableStatement callableStatement = null;
        try {
        	if(alias != null) {
        		c = ConnectionPool.getConnection(alias);
        	} else {
        		c = ConnectionPool.getConnection();
        	}
            callableStatement = c.prepareCall
                    ("{call delta_cl_load_service.log(?,?,?,?,?,?,?)}");
            callableStatement.setString(1, branch);
            callableStatement.setString(2, idClient);
            callableStatement.setString(3, customer_type);
            callableStatement.setString(4, idSap);
            callableStatement.setInt(5, action == 0 ? ACTION_CREATE : action);
            callableStatement.setInt(6, state);
            callableStatement.setString(7, message);
            callableStatement.execute();
            c.commit();
        }
        catch (Exception e){
        	ISLogger.getLogger().error(this);
            ISLogger.getLogger().error(CheckNull.getPstr(e));
            
        }
        finally{
        	DbUtils.closeStmt(callableStatement);
            ConnectionPool.close(c);
        }
    }
    
    
    public static class Builder {
    	@Getter private String alias;
    	@Getter private String branch;
    	@Getter private String idClient;
    	@Getter private int action;
    	@Getter private String customer_type;
    	@Getter private String idSap;
    	@Getter private int state;
    	@Getter private String message;
        
        public Builder() {
		}
        public Builder(LegalEntity legal) {
        	initLegalEntity(legal);
        }
        public Builder(Person person) {
        	initPerson(person);
        }
        public Builder alias(String alias) {
        	this.alias = alias;
        	return this;
        }
        public Builder branch(String alias) {
        	this.branch = alias;
        	return this;
        }
        public Builder idClient(String alias) {
        	this.idClient = alias;
        	return this;
        }
        public Builder action(int alias) {
        	this.action = alias;
        	return this;
        }
        public Builder customer_type(String code) {
        	this.customer_type = code;
        	return this;
        }
        public Builder idSap(String idSap) {
        	this.idSap = idSap;
        	return this;
        }
        public Builder message(String alias) {
        	this.message = alias;
        	return this;
        }
        public Builder state(int state) {
        	this.state = state;
        	return this;
        }
        
        public Builder createLegalEntity(LegalEntity legal) {
        	this.action = ACTION_CREATE;
        	this.customer_type = CUSTOMER_TYPE_LEGAL_FOUNDER;
        	initLegalEntity(legal);
        	return this;
        }
        public Builder editLegalEntity(LegalEntity legal) {
        	this.action = ACTION_EDIT;
        	this.customer_type = CUSTOMER_TYPE_LEGAL_FOUNDER;
        	initLegalEntity(legal);
        	return this;
        }
        public Builder createPerson(Person person) {
        	this.action = ACTION_CREATE;
        	this.customer_type = CUSTOMER_TYPE_PERSON_FOUNDER;
        	initPerson(person);
        	return this;
        }
        public Builder createIpPerson(Person person) {
        	this.action = ACTION_CREATE;
        	this.customer_type = CUSTOMER_TYPE_CONTACT_PERSON;
        	initPerson(person);
        	return this;
        }
        public Builder editPerson(Person person) {
        	this.action = ACTION_EDIT;
        	this.customer_type = CUSTOMER_TYPE_PERSON_FOUNDER;
        	initPerson(person);
        	return this;
        }
        public Builder editIpPerson(Person person) {
        	this.action = ACTION_EDIT;
        	initPerson(person);
        	this.customer_type = CUSTOMER_TYPE_CONTACT_PERSON;
        	return this;
        }
        public Builder clientJ(ClientJ client) {
        	this.branch = client.getBranch();
        	this.idClient = client.getId_client();
        	this.customer_type = CUSTOMER_TYPE_CLIENT_J;
        	return this;
        }
        public void initLegalEntity(LegalEntity legal) {
        	this.branch = legal.getBranch();
        	this.idClient = legal.getId();
        	this.customer_type = CUSTOMER_TYPE_LEGAL_FOUNDER;
        }
        public void initPerson(Person person) {
        	this.branch = person.getBranch();
        	this.idClient = person.getId();
        	this.customer_type = CUSTOMER_TYPE_PERSON_FOUNDER;
        }
        public SapLogger build() {
        	return new SapLogger(this);
        }
        
    }
}
