package com.example.decklist;

import java.util.ArrayList;

import org.xml.sax.ext.DeclHandler;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ActionBar.Tab;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

public class CardListActivity extends Activity{
	ArrayList<Card> cards; 
	Deck deck;
	ListView listViewCards;
	TextView textViewDeckName;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cardlist_fragment);
        cards = new ArrayList<Card>();
        
        //Get the bundle
        Bundle bundle = getIntent().getExtras();

        DBHelper db = new DBHelper(this);
        deck = new Deck();
        String deck_name = bundle.getString("deckName");
        deck = db.getDeckByName(deck_name);
        
        listViewCards = (ListView) findViewById(R.id.listViewCards);
        textViewDeckName = (TextView) findViewById(R.id.textViewDeckName);
        
        cards = deck.mainDeckCards;
        textViewDeckName.setText(deck_name);
        CardArrayAdapter cardArrayAdapter = new CardArrayAdapter(this, android.R.layout.simple_list_item_1, cards);
        listViewCards.setAdapter(cardArrayAdapter);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_cardlist, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.add_card:
                addCard();
                return true;
            case R.id.statistics:
                // exportDeck();
                return true;
            case R.id.draw_hand:
                // exportDeck();
                return true;                
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    public void addCard(){}
    
    public void editQuantity(){}
    
    public void deleteCard(){}

}
