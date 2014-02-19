package com.example.decklist;

import java.util.ArrayList;
import java.util.Date;

import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.decklist.DeckArrayAdapter.RowType;
import com.jinn.listviewheaders.Item;

enum LegalFormats {
	VINTAGE, LEGACY, STANDARD, MODERN, LIMITED, BLOCKCONSTRUCTED, EDHCOMMANDER, TWOHEADEDGIANT 
}

public class Deck implements Item{ 
	Integer deck_id;
	String deck_name;
	String author;
	Integer format_id;
	Date created_date, last_changed; 
	Integer card_count;

	LegalFormats format;
	ArrayList<String> colors = new ArrayList<String>(); 

	ArrayList<Card> mainDeckCards = new ArrayList<Card>();
	ArrayList<Card> sideBoardCards = new ArrayList<Card>();

	DeckStatistics deckStatistics = new DeckStatistics();
	public Deck() { super(); }

	public Deck(Integer deck_id, String deck_name, String author,
			Integer format_id, Date created, Date last_changed) {
		super();
		this.deck_id = deck_id;
		this.deck_name = deck_name;
		this.author = author;
		this.format_id = format_id;
		this.created_date = created;
		this.last_changed = last_changed;
	}
	
    public Deck(Parcel in){
        String[] data = new String[5];

        in.readStringArray(data);
        this.deck_id = Integer.parseInt(data[0]);
        this.deck_name = data[1];
        this.author = data[2];
        this.format_id = Integer.parseInt(data[3]);
        this.card_count = Integer.parseInt(data[4]);
    }

	public int getViewType() {
		return RowType.LIST_ITEM.ordinal();
	}

	@Override
	public View getView(LayoutInflater inflater, View convertView) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.deck_layout, null);
        }

        TextView deckName = (TextView) view.findViewById(R.id.textViewDeckName);
        TextView author = (TextView) view.findViewById(R.id.textViewAuthor);
        ImageView colors = (ImageView) view.findViewById(R.id.imageViewColors);
        if (deckName != null && author != null) {
        	// do whatever you want with your string and long
        	deckName.setText(String.format("%s", deck_name));
        	author.setText(String.format("%s", "Jinn Kim"));
        }

        return view;
	}
}

