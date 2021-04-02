package ru.practice.dev_technology;

public class CardFiller {
    /**Strings for the card representing converter result */
    private String cardValue;

    public CardFiller(String numValue1,String value1,String numValue2,String value2) {
        cardValue = numValue1+" "+value1+" = "+numValue2+" "+value2;
        }

    public String getCardValue() {
        return cardValue;
    }

    public void setCardValue(String cardValue) {
        this.cardValue = cardValue;
    }
}
