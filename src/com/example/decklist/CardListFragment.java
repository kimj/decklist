package com.example.decklist;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class CardListFragment extends Fragment{
	ArrayList<Card> cards; 
	Deck deck;
	ListView listViewCards;
	TextView textViewDeckName;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		return inflater.inflate(R.layout.cardlist_fragment, container, false);
	}
	
    @Override
    public void onStart() {
        super.onStart();

        cards = new ArrayList<Card>();
        
        //Get the bundle
        Bundle bundle = getActivity().getIntent().getExtras();

        DBHelper db = new DBHelper(getActivity());
        deck = new Deck();
        String deck_name = bundle.getString("deckName");
        deck = db.getDeckByName(deck_name);
        
        listViewCards = (ListView) getView().findViewById(R.id.listViewCards);
        textViewDeckName = (TextView) getView().findViewById(R.id.textViewDeckName);
        
        cards = deck.mainDeckCards;
        textViewDeckName.setText(deck_name);
        CardArrayAdapter cardArrayAdapter = new CardArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, cards);
        listViewCards.setAdapter(cardArrayAdapter);
    }
    
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.activity_decklist, menu);
        super.onCreateOptionsMenu(menu,inflater);
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
