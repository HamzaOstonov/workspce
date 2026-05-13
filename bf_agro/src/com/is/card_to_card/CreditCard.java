package com.is.card_to_card;

public class CreditCard {
    private String type;
    private String number;
    private String expiration;
    private String owner;

    public CreditCard(String type, String number, String expiration, String owner) {
        this.type = type;
        this.number = number;
        this.expiration = expiration;
        this.owner = owner;
    }

    public String getType() {
        return type;
    }

    public String getNumber() {
        return number;
    }

    public String getExpiration() {
        return expiration;
    }

    public String getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "type='" + type + '\'' +
                ", number='" + number + '\'' +
                ", expiration='" + expiration + '\'' +
                ", owner='" + owner + '\'' +
                '}';
    }
}
