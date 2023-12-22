package com.is.clients.ebp.models.legalentity;
//import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.is.clients.ebp.models.Confirm;

/**
 * Created by DEN on 27.03.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LegalEntity {
    private Confirm confirm;
    private SubjectLegalEntity subject;

    public LegalEntity() {
    }

    public Confirm getConfirm() {
        return confirm;
    }

    public void setConfirm(Confirm confirm) {
        this.confirm = confirm;
    }

    public SubjectLegalEntity getSubject() {
        return subject;
    }

    public void setSubject(SubjectLegalEntity subject) {
        this.subject = subject;
    }
}
