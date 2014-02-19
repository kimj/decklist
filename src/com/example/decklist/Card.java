package com.example.decklist;

public class Card {
	Integer card_id, deck_id;
	String card_name;
	Integer quantity;
	String sideboard;
	
	public Card() {}
	
	public Card(String card_name, Integer quantity, String sideboard) {
		this.card_name = card_name;
		this.quantity = quantity;
		this.sideboard = sideboard;
		
	}
}