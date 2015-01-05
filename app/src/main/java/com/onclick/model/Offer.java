package com.onclick.model;

/**
 * Created by Ronald T on 12/28/2014.
 */
public class Offer {

    private String cardBrand;
    private float cardValue;
    private float cardCost;


    public Offer() {
    }

    public String getCardBrand() {
        return cardBrand;
    }

    public void setCardBrand(String cardBrand) {
        this.cardBrand = cardBrand;
    }

    public float getCardValue() {
        return cardValue;
    }

    public void setCardValue(float cardValue) {
        this.cardValue = cardValue;
    }

    public float getCardCost() {
        return cardCost;
    }

    public void setCardCost(float cardCost) {
        this.cardCost = cardCost;
    }
}
