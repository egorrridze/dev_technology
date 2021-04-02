package ru.practice.dev_technology;

public class CardFiller {
    /**Strings for the card representing converter result */
    private String cardValue;

    public CardFiller(String converter_result) {
        cardValue = converter_result;
        }

    public String getCardValue() {
        return cardValue;
    }

    public void setCardValue(String cardValue) {
        this.cardValue = cardValue;
    }
}
