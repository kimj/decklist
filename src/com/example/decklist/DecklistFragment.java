package com.example.decklist;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.jinn.listviewheaders.Header;
import com.jinn.listviewheaders.Item;

public class DecklistFragment extends Fragment implements OnItemClickListener{
	ListView listViewDecks; 
	TextView textViewDeckName;
	DBHelper db = new DBHelper(getActivity());
	ArrayList<Item> decks;
	DeckArrayAdapter deckArrayAdapter;
	private String selectedDeckName;
	private int selectedDeckPosition;

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		return inflater.inflate(R.layout.decklist_activity, container, false);
	}
	
    @Override
    public void onStart() {
        super.onStart();

        decks = new ArrayList<Item>();
        listViewDecks = (ListView) getView().findViewById(R.id.listViewDecks);
        textViewDeckName = (TextView) getView().findViewById(R.id.textViewDeckName);
        
        Cursor decklistCursor = db.getAllDecks();
        
        registerForContextMenu(listViewDecks);
        
        decklistCursor.moveToFirst();
        if (decklistCursor.getCount() > 0){
	        while(!decklistCursor.isAfterLast()) {
	        	Deck deck = new Deck();
	        	deck.deck_name = decklistCursor.getString(decklistCursor.getColumnIndex("deck_name"));
	        	deck.author = decklistCursor.getString(decklistCursor.getColumnIndex(db.KEY_DECK_AUTHOR));
	        	deck.format_id = decklistCursor.getInt(decklistCursor.getColumnIndex(db.KEY_DECK_FORMAT_ID));
	        	
	            decks.add(deck); //add the item
	            decks.add(new Header("Header 1"));
	            decklistCursor.moveToNext();
	        }
        }
        
        deckArrayAdapter = new DeckArrayAdapter(getActivity(), decks);
        listViewDecks.setAdapter(deckArrayAdapter);
        
        listViewDecks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        	@Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        		TextView textViewDeckName = (TextView) view.findViewById(R.id.textViewDeckName); 
        		loadCardActivity(textViewDeckName.getText().toString());
            }
        });
    }

    public void loadCardActivity(String deckName){
        Intent i = new Intent(getActivity(), CardListActivity.class);
        
        //Create the bundle
        Bundle bundle = new Bundle();
        //Add your data to bundle
        bundle.putString("deckName", deckName);
        //Add the bundle to the intent
        i.putExtras(bundle);
        
        //Fire that second activity
        startActivity(i);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) menuInfo;
        selectedDeckName = ((TextView) info.targetView.findViewById(R.id.textViewDeckName)).getText().toString();
        selectedDeckPosition = info.position;
    	
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.decklist_context_menu, menu);
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.delete:
                deleteDeck();
                return true;
            case R.id.exportdeck:
            	exportDeck();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
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
            case R.id.create_new:
                createNewDeck();
                return true;
            case R.id.import_deck:
                importDeck();
                return true;              
            default:
                return super.onOptionsItemSelected(item);
        }
    }
	
	private void createNewDeck(){
		/*new AlertDialog.Builder(this)
		.setTitle("Add a Deck")	
		.setView(newDeckView)
		.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				ListAdapter adapter= listViewDecks.getAdapter();
				EditText title = (EditText) newDeckView.findViewById(R.id.title);
				EditText author = (EditText) newDeckView.findViewById(R.id.author);
				
				Deck deck = new Deck(){};
				adapter.add(deck);
				db.insertDeck(title.getText().toString(), author.getText().toString());
			}
		})*/
	}
	
	private void deleteDeck(){}
	
	private void importDeck(){}
	
	private void exportDeck(){}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}
}
