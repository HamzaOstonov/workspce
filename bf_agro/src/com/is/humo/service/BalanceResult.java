// 
// Decompiled by Procyon v0.5.36
// 

package com.is.humo.service;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "listSize", "card", "account", "balance", "mb" })
public class BalanceResult
{
    @JsonProperty("listSize")
    private Integer listSize;
    @JsonProperty("card")
    private Card card;
    @JsonProperty("account")
    private List<Account> account;
    @JsonProperty("balance")
    private Balance balance;
    @JsonProperty("mb")
    private Mb mb;
    
    public BalanceResult() {
        this.account = null;
    }
    
    public BalanceResult(final Integer listSize, final Card card, final List<Account> account, final Balance balance, final Mb mb) {
        this.account = null;
        this.listSize = listSize;
        this.card = card;
        this.account = account;
        this.balance = balance;
        this.mb = mb;
    }
    
    @JsonProperty("listSize")
    public Integer getListSize() {
        return this.listSize;
    }
    
    @JsonProperty("listSize")
    public void setListSize(final Integer listSize) {
        this.listSize = listSize;
    }
    
    @JsonProperty("card")
    public Card getCard() {
        return this.card;
    }
    
    @JsonProperty("card")
    public void setCard(final Card card) {
        this.card = card;
    }
    
    @JsonProperty("account")
    public List<Account> getAccount() {
        return this.account;
    }
    
    @JsonProperty("account")
    public void setAccount(final List<Account> account) {
        this.account = account;
    }
    
    @JsonProperty("balance")
    public Balance getBalance() {
        return this.balance;
    }
    
    @JsonProperty("balance")
    public void setBalance(final Balance balance) {
        this.balance = balance;
    }
    
    @JsonProperty("mb")
    public Mb getMb() {
        return this.mb;
    }
    
    @JsonProperty("mb")
    public void setMb(final Mb mb) {
        this.mb = mb;
    }
}
