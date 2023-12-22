package com.is.clients.ebp.models.individual;

//import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.is.clients.ebp.models.Confirm;

import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class Individual {
    private Confirm confirm;
    private SubjectIndividual subject;

    public Individual() {
    }

    public Confirm getConfirm() {
        return confirm;
    }

    public void setConfirm(Confirm confirm) {
        this.confirm = confirm;
    }

    public SubjectIndividual getSubject() {
        return subject;
    }

    public void setSubject(SubjectIndividual subject) {
        this.subject = subject;
    }
}

