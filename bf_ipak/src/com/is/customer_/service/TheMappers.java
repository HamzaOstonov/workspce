package com.is.customer_.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.is.ISLogger;
import com.is.base.utils.DbUtils;
import com.is.client_personmap.PersonMapUtil;
import com.is.client_personmap.model.FounderCapital;
import com.is.client_personmap.model.LegalEntity;
import com.is.client_personmap.model.Person;
import com.is.client_personmap.model.PersonMap;
import com.is.clients.ebp.EbpMappers;
import com.is.clients.ebp.models.individualdetails.IndividualDetails;
import com.is.clients.ebp.models.legalentitydetails.Client_founders;
import com.is.clients.ebp.models.legalentitydetails.LegalEntityDetails;
import com.is.clients.models.ClientJ;
import com.is.customer_.core.model.Customer;
import com.is.customer_.service.model.FizAddressResponse;
import com.is.utils.CheckNull;

public class TheMappers {

	  private final static Logger logger = Logger.getLogger(EbpMappers.class);

	    private static SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");

	    public static Customer mapCustomer(FizAddressResponse entityDetails) {
	        if (entityDetails == null) {
	            return null;
	        }
	        Customer client = new Customer();
            client.setFile_name(entityDetails.getAnswereMessage());
	        return client;
	    }
}
