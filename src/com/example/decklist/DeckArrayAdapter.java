package com.example.decklist;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.jinn.listviewheaders.Item;

public class DeckArrayAdapter extends ArrayAdapter<Item> {
	private Context context;
	
	private LayoutInflater mInflater;
	
    public enum RowType {
        LIST_ITEM, HEADER_ITEM
    }
	
    public DeckArrayAdapter(Context context, List<Item> items) {
        super(context, 0, items);
        mInflater = LayoutInflater.from(context);
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        view = getItem(position).getView(mInflater, convertView);
        return view;
    }
    
    @Override
    public int getViewTypeCount() {
        return RowType.values().length;
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getViewType();
    }
}
