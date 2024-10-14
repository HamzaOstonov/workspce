package com.is.clients.ebp.models.individual;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.is.clients.ebp.models.Confirm;
@JsonIgnoreProperties(ignoreUnknown = true)
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

