package com.example.decklist;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CardArrayAdapter extends ArrayAdapter<Card> {

    private Context context;

    public CardArrayAdapter(Context context, int textViewResourceId, ArrayList<Card> items) {
        super(context, textViewResourceId, items);
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.card_layout, null);
        }

        Card card = getItem(position);
        if (card!= null) {
            // My layout has only one TextView
            TextView cardName = (TextView) view.findViewById(R.id.textViewCardName);
            TextView quantity = (TextView) view.findViewById(R.id.textViewQuantity);
            if (cardName != null && quantity != null) {
                // do whatever you want with your string and long
            	cardName.setText(String.format("%s", card.card_name));
            	quantity.setText(String.format("%d", card.quantity));
            }
         }

        return view;
    }
}