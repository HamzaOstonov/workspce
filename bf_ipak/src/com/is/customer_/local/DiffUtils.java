package com.is.customer_.local;

import com.is.customer_.core.model.Customer;
import de.danielbechler.diff.ObjectDifferBuilder;
import de.danielbechler.diff.differ.DifferDispatcher;
import de.danielbechler.diff.node.DiffNode;
import de.danielbechler.diff.node.PrintingVisitor;
import de.danielbechler.diff.node.Visit;

public class DiffUtils {
    public static boolean hasChanges(Object obj1, Object obj2) {
        DiffNode diff = ObjectDifferBuilder.startBuilding().inclusion().
                exclude().propertyName("longId").
                also().exclude().propertyName("idSap").
                also().exclude().propertyName("id_client").
                also().exclude().propertyName("branch").
                also().exclude().propertyName("state").
                also().exclude().propertyName("p_code_bank").
                also().exclude().propertyName("name").
                also().exclude().propertyName("code_subject").
                also().exclude().propertyName("sign_registr").
                also().exclude().propertyName("code_type").
                also().exclude().propertyName("date_close").
                also().exclude().propertyName("date_open").
                also().exclude().propertyName("kod_err").
                also().exclude().propertyName("file_name").
                also().exclude().propertyName("p_code_class_credit").
                also().exclude().propertyName("p_passport_type").
                also().exclude().propertyName("internalControl").
                also().exclude().propertyName("account").
                also().exclude().propertyName("sign_100").
                also().exclude().propertyName("j_327").
                also().exclude().propertyName("SAPCustomer").
                also().exclude().propertyName("isDocumentDateRegistrationValid").
                also().exclude().propertyName("union_id").
                also().exclude().propertyName("isForceCreated").
                also().exclude().propertyName("i014").
                and().build().compare(obj1, obj2);
        ;
        return diff.hasChanges();
    }

    public static String printChanges(final Object obj1, final Object obj2) {
        DiffNode diff = ObjectDifferBuilder.startBuilding().inclusion().
                exclude().propertyName("longId").
                also().exclude().propertyName("idSap").
                also().exclude().propertyName("id_client").
                also().exclude().propertyName("branch").
                also().exclude().propertyName("state").
                also().exclude().propertyName("p_code_bank").
                also().exclude().propertyName("name").
                also().exclude().propertyName("code_subject").
                also().exclude().propertyName("sign_registr").
                also().exclude().propertyName("code_type").
                also().exclude().propertyName("date_close").
                also().exclude().propertyName("date_open").
                also().exclude().propertyName("kod_err").
                also().exclude().propertyName("file_name").
                also().exclude().propertyName("p_code_class_credit").
                also().exclude().propertyName("p_passport_type").
                also().exclude().propertyName("internalControl").
                also().exclude().propertyName("sign_100").
                also().exclude().propertyName("j_327").
                and().build().compare(obj1, obj2);
        final StringBuilder builder = new StringBuilder();
        diff.visit(new DiffNode.Visitor() {
            @Override
            public void node(DiffNode node, Visit visit) {
                final Object baseValue = node.canonicalGet(obj1);
                final Object workingValue = node.canonicalGet(obj2);
                if (baseValue instanceof Customer)
                    return;
                final String message = node.getPropertyName() + " changed from " +
                        baseValue + " to " + workingValue;
                builder.append(message);
                builder.append("\n");
            }
        });
        return builder.toString();
    }

    public static boolean exclude(String fieldName) {
        if (fieldName.equalsIgnoreCase("idSap"))
            return true;
        if (fieldName.equalsIgnoreCase("id"))
            return true;
        if (fieldName.equalsIgnoreCase("name"))
            return true;
        if (fieldName.equalsIgnoreCase("code_subject"))
            return true;
        if (fieldName.equals("kod_err"))
            return true;
        if (fieldName.equalsIgnoreCase("file_name"))
            return true;
        if (fieldName.equalsIgnoreCase("code_subject"))
            return true;
        if (fieldName.equalsIgnoreCase("state"))
            return true;
        if (fieldName.equalsIgnoreCase("date_open"))
            return true;
        if (fieldName.equalsIgnoreCase("date_close"))
            return true;
        if (fieldName.equals("p_code_bank"))
            return true;
        if (fieldName.equalsIgnoreCase("branch"))
            return true;
        if (fieldName.equals("sign_registr"))
            return true;
        if (fieldName.equalsIgnoreCase("code_type"))
            return true;
        if (fieldName.equalsIgnoreCase("code_form"))
            return true;
        if (fieldName.equalsIgnoreCase("p_passport_type"))
            return true;
        if (fieldName.equalsIgnoreCase("p_code_birth_region"))
            return true;
        if (fieldName.equalsIgnoreCase("p_code_birth_distr"))
            return true;
        if (fieldName.equalsIgnoreCase("p_post_address_street_code"))
            return true;
        if (fieldName.equalsIgnoreCase("p_code_class_credit"))
            return true;
        if (fieldName.equalsIgnoreCase("p_pass_place_region"))
            return true;
        if (fieldName.equalsIgnoreCase("p_pass_place_district"))
            return true;
        if (fieldName.equalsIgnoreCase("p_post_address_street"))
            return true;
        if (fieldName.equalsIgnoreCase("p_post_address_house"))
            return true;
        if (fieldName.equalsIgnoreCase("p_post_address_flat"))
            return true;
        if (fieldName.equalsIgnoreCase("p_post_address_quarter"))
            return true;
        if (fieldName.equalsIgnoreCase("union_id"))
            return true;
        return false;
    }
    
    public static boolean hasPassportChanges(Object obj1, Object obj2) {
    	ObjectDifferBuilder builder = ObjectDifferBuilder.startBuilding();
    	builder.inclusion().include().propertyName("p_type_document")
    	.also().include().propertyName("p_passport_serial")
    	.also().include().propertyName("p_passport_number")
    	.also().include().propertyName("p_passport_place_registration")
    	.also().include().propertyName("p_passport_date_registration")
    	.also().include().propertyName("p_passport_date_expiration");
    	
        DiffNode diff = builder.build().compare(obj1, obj2);
        //diff.visit(new PrintingVisitor(usuario2, usuario1));
        return diff.hasChanges();
    }
}
