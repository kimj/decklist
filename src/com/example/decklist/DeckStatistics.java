package com.example.decklist;

import java.util.ArrayList;
import java.util.HashMap;

import android.database.Cursor;

public class DeckStatistics {
	Integer type_artifacts, type_creatures, type_enchantments, 
	type_land, type_planeswalker, type_instants, type_sorceries, type_spell;
	
	Integer mana_symbol_white, mana_symbol_blue, mana_symbol_black, 
	mana_symbol_red, mana_symbol_green, mana_symbol_colorless;

	String mana_distribution; 
	
	Double mana_cost_average;
	HashMap<String, Integer> mana_curve = new HashMap<String, Integer> ();
	double estimated_deck_value_low, estimated_deck_value_mid, estimated_deck_value_high;
	
	HashMap<String, Integer> mana_sources = new HashMap<String, Integer> ();
	
	/*Average Casting Cost
	Mana Curve
		CC0-CC12
		
	Card Type Distribution 
		Creature, Land, Spell
	Mana Distrubution
	Mana Sources
	Card Prices*/
	
	private static ArrayList<Cursor> card_dataArrayList = new ArrayList<Cursor>();  
	
	
	public DeckStatistics() {
		// TODO Auto-generated constructor stub
	}
	
	public static void getCardInformation(ArrayList<Card> cards){
		for (Card card : cards) {
			/* Cursor card_data = fetchCardByName(card.card_name, new String[] { CardDbAdapter.KEY_SET, CardDbAdapter.KEY_ID });
			card_dataArrayList.add(card_data); */
		}
	}
	
	public static void calculateStatistics(){
		for (Cursor c : card_dataArrayList) {
			// tally everything up in here
		}
	}
}
