package com.is.customer_.sap.service;

import com.is.customer_.contact.Relationship;
import com.is.customer_.core.model.Customer;

/**
 * Created by root on 05.06.2017.
 * 17:17
 */
public interface RelationshipGateway {
    void createRelationship(Customer customer, Relationship relationship) throws Exception;

    void deleteRelationship(Customer customer,Relationship relationship) throws Exception;

    boolean isRelationshipExists(Customer contactPerson,Relationship relationship) throws Exception;
}
