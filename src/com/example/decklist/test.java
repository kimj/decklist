//package com.menuex;
//
//import android.app.AlertDialog;
//import android.app.ListActivity;
//import android.content.DialogInterface;
//import android.os.Bundle;
//import android.view.ContextMenu;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.EditText;
//import java.util.ArrayList;
//public class MenuEx extends ListActivity {
//	private static final String[] items={"lorem", "ipsum", "dolor",
//		"sit","amet", "consectetuer", "adipiscing", "elit",
//		"morbi", "vel", "ligula", "vitae", "arcu", "aliquet",
//		"mollis", "etiam", "vel", "erat", "placerat", "ante",
//		"porttitor", "sodales", "pellentesque", "augue"
//	};
//
//	public static final int MENU_ADD = Menu.FIRST+1;
//	public static final int MENU_RESET = Menu.FIRST+2;
//	public static final int MENU_CAP = Menu.FIRST+3;
//	public static final int MENU_REMOVE = Menu.FIRST+4;
//	private ArrayList words=null;
//	@Override
//	public void onCreate(Bundle icicle) {
//		super.onCreate(icicle);
//		initAdapter();
//		registerForContextMenu(getListView());
//	}
//
//	public boolean onCreateOptionsMenu(Menu menu) {
//		menu
//		.add(Menu.NONE, MENU_ADD, Menu.NONE, "Add")
//		.setIcon(R.drawable.add1);
//		menu
//		.add(Menu.NONE, MENU_RESET, Menu.NONE, "Reset")
//		.setIcon(R.drawable.ref);
//		return(super.onCreateOptionsMenu(menu));
//	}
//	
//	@Override
//	public void onCreateContextMenu(ContextMenu menu, View v,
//			ContextMenu.ContextMenuInfo menuInfo) {
//		menu.add(Menu.NONE, MENU_CAP, Menu.NONE, "Capitalize");
//		menu.add(Menu.NONE, MENU_REMOVE, Menu.NONE, "Remove");
//	}
//	
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		switch (item.getItemId()) {
//		case MENU_ADD:
//			add();
//			return(true);
//		case MENU_RESET:
//			initAdapter();
//			return(true);
//		}
//		return(super.onOptionsItemSelected(item));
//	}
//	
//	@Override
//	public boolean onContextItemSelected(MenuItem item) {
//		AdapterView.AdapterContextMenuInfo info=
//				(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
//		ArrayAdapter adapter=(ArrayAdapter)getListAdapter();
//		switch (item.getItemId()) {
//		case MENU_CAP:
//			String word=words.get(info.position);
//			word=word.toUpperCase();
//			adapter.remove(words.get(info.position));
//			adapter.insert(word, info.position);
//			return(true);
//		case MENU_REMOVE:
//			adapter.remove(words.get(info.position));
//			return(true);
//		}
//		return(super.onContextItemSelected(item));
//	}
//	
//	private void initAdapter() {
//		words=new ArrayList();
//		for (String s : items) {
//			words.add(s);
//		}
//		setListAdapter(new ArrayAdapter(this,
//				android.R.layout.simple_list_item_1, words));	
//	}
//	
//	private void add() {
//		final View addView=getLayoutInflater().inflate(R.layout.add, null);
//		new AlertDialog.Builder(this)
//		.setTitle("Add a Word")
//		.setView(addView)
//		.setPositiveButton("OK",
//				new DialogInterface.OnClickListener() {
//			public void onClick(DialogInterface dialog,
//					int whichButton) {
//				ArrayAdapter adapter=(ArrayAdapter)getListAdapter();
//				EditText title=(EditText)addView.findViewById(R.id.title);
//				adapter.add(title.getText().toString());
//			}
//
//		})
//		.setNegativeButton("Cancel", null)
//		.show();
//	}
//}