package com.example.decklist;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Tools {
	public ArrayList<Card> drawHand(Deck d, Integer handCount){
		ArrayList<Card> randomHand = new ArrayList<Card>();
		ArrayList<Card> unshuffledDeck = new ArrayList<Card>();
		
		// expand the deck
		for (Card card : d.mainDeckCards) {
			for (Integer i = 0; i < card.quantity; i++){
				unshuffledDeck.add(card);
			}
		}
		
		Collections.shuffle(Arrays.asList(unshuffledDeck));
		
		for (Integer j = 0; j < handCount; j++){
			randomHand.add(unshuffledDeck.get(j));
		}
		
		return randomHand;
	} 
	
	public static void importFile(File file){
		
	}
	
	public static void exportFile(String fileLocation){
		
	}
	
	public static String convertStreamToString(InputStream is) throws Exception {
	    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	    StringBuilder sb = new StringBuilder();
	    String line = null;
	    while ((line = reader.readLine()) != null) {
	      sb.append(line).append("\n");
	    }
	    return sb.toString();
	}

	public static String getStringFromFile (String filePath) throws Exception {
	    File fl = new File(filePath);
	    FileInputStream fin = new FileInputStream(fl);
	    String ret = convertStreamToString(fin);
	    //Make sure you close all streams.
	    fin.close();        
	    return ret;
	}
	
	/*public static void createFileFromString(String string ){
		   File file = ...;
				   FileOutputStream out = null;
				   try {
				     out = new BufferedOutputStream(new FileOutputStream(file));
				     ...
				    finally {
				     if (out != null) {
				       out.close();
				     }
				   }
	}*/
	
	public Deck listToDeck(ArrayList<String> listCards){
		Deck deck = new Deck();
		for (String s : listCards){
			if(s.startsWith("//")){
				
			}
			else if(s.startsWith("Sideboard")){
				
			}
		}
		return deck;
	}
}
