package com.practice.cardcollection;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Card {
    private @Id
    @GeneratedValue Long id;
    private String pokemonName;
    private String expansionName;
    private String cardNumber;
    private int quantity;

    Card() {}

    Card(String pokemonName, String expansionName, String cardNumber) {
        this.pokemonName = pokemonName;
        this.expansionName = expansionName;
        this.cardNumber = cardNumber;
        this.quantity = 1;
    }

    public Long getId() {
        return this.id;
    }

    public String getPokemonName() {
        return this.pokemonName;
    }

    public String getExpansionName() {
        return this.expansionName;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setPokemonName(String pokemonName) {
        this.pokemonName = pokemonName;
    }

    public void setExpansionName(String expansionName) {
        this.expansionName = expansionName;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return Objects.equals(this.id, card.id) &&
                Objects.equals(this.pokemonName, card.pokemonName) &&
                Objects.equals(this.expansionName, card.expansionName) &&
                Objects.equals(this.cardNumber, card.cardNumber) &&
                Objects.equals(this.quantity, card.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pokemonName, expansionName, cardNumber, quantity);
    }

    @Override
    public String toString() {
        return "Card{" + "id=" + this.id + ", name=" + this.pokemonName + ", expansionName=" + this.expansionName + ", cardNumber=" + this.cardNumber + ", quantity=" + this.quantity + '}';
    }
}
