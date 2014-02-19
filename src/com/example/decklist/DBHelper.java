package com.example.decklist;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper 
{
	public static final String KEY_DECK_ID = "deck_id";
	public static final String KEY_DECK_NAME = "deck_name";
	public static final String KEY_DECK_AUTHOR = "author";
	public static final String KEY_DECK_FORMAT_ID = "format_id";
	public static final String KEY_DECK_CARD_COUNT = "card_count";
	public static final String KEY_DECK_CREATED_DATE = "created_date";
	public static final String KEY_DECK_LAST_CHANGED_DATE = "last_changed_date";

	public static final String KEY_CARDS_CARD_ID = "card_id";
	public static final String KEY_CARDS_CARD_NAME = "card_name";
	public static final String KEY_CARDS_QUANTITY = "quantity";
	public static final String KEY_CARDS_DECK_ID = "deck_id";
	public static final String KEY_CARDS_SIDEBOARD = "sideboard";
	public static final String KEY_CARDS_CREATED_DATE = "created_date";
	public static final String KEY_CARDS_LAST_CHANGED_DATE = "last_changed_date";

	public static final String KEY_STATISTICS_TYPE_ARTIFACTS = "type_artifacts";
	public static final String KEY_STATISTICS_TYPE_CREATURES = "type_creatures";
	public static final String KEY_STATISTICS_TYPE_ENCHANTMENTS = "type_enchantments";
	public static final String KEY_STATISTICS_TYPE_LAND = "type_land";
	public static final String KEY_STATISTICS_TYPE_PLANESWALKER = "type_planeswalker";
	public static final String KEY_STATISTICS_TYPE_INSTANTS = "type_instants";
	public static final String KEY_STATISTICS_TYPE_SOCERIES = "type_soceries";
	public static final String KEY_STATISTICS_TYPE_SPELL = "type_spell";
	public static final String KEY_STATISTICS_TYPE_WHITE = "mana_symbol_white";
	public static final String KEY_STATISTICS_TYPE_BLUE = "mana_symbol_blue";
	public static final String KEY_STATISTICS_TYPE_BLACK = "mana_symbol_black";
	public static final String KEY_STATISTICS_TYPE_RED = "mana_symbol_red";
	public static final String KEY_STATISTICS_TYPE_GREEN = "mana_symbol_green";
	public static final String KEY_STATISTICS_TYPE_MANA_COST_0 = "mana_curve_0";
	public static final String KEY_STATISTICS_TYPE_MANA_COST_1 = "mana_curve_1";
	public static final String KEY_STATISTICS_TYPE_MANA_COST_2 = "mana_curve_2";
	public static final String KEY_STATISTICS_TYPE_MANA_COST_3 = "mana_curve_3";
	public static final String KEY_STATISTICS_TYPE_MANA_COST_4 = "mana_curve_4";
	public static final String KEY_STATISTICS_TYPE_MANA_COST_5 = "mana_curve_5";
	public static final String KEY_STATISTICS_TYPE_MANA_COST_6 = "mana_curve_6";
	public static final String KEY_STATISTICS_TYPE_MANA_COST_7plus = "mana_curve_7_plus";
	public static final String KEY_STATISTICS_TYPE_estimated_deck_value_low = "estimated_deck_value_low";
	public static final String KEY_STATISTICS_TYPE_estimated_deck_value_mid = "estimated_deck_value_mid";
	public static final String KEY_STATISTICS_TYPE_estimated_deck_value_high = "estimated_deck_value_high";

	private static final String DATABASE_NAME = "Decklist";
	private static final String DATABASE_TABLE_DECKS = "decks";
	private static final String DATABASE_TABLE_CARDS = "cards";
	private static final String DATABASE_TABLE_STATISTICS = "statistics";
	private static final String TAG = "DBAdapter";
	private static final int DATABASE_VERSION = 1;
	
	private static String DB_PATH = "/data/data/com.example.decklist/databases/";
	private SQLiteDatabase decklistDatabase;
	private final Context context= null;

	private static final String DATABASE_CREATE = "CREATE TABLE decks (deck_id INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ "deck_name TEXT NOT NULL, author TEXT NOT NULL,"
			+ "format_id INTEGER  NOT NULL, created DATETIME,"
			+ "last_changed DATETIME);" 
			+ "CREATE TABLE cards (card_id INTEGER PRIMARY KEY, " 
			+ "card_name VARCHAR, deck_id INTEGER, quantity  INTEGER," 
			+ "sideboard BOOLEAN );" 
			+ "CREATE TABLE statistics (deck_id INTEGER,"
			+ "type_artifacts INTEGER, type_creatures INTEGER,"
			+ "type_enchantments INTEGER, type_land INTEGER,"
			+ "type_planeswalker INTEGER, type_spell INTEGER,"
			+ "type_other INTEGER, mana_symbol_white INTEGER,"
			+ "mana_symbol_blue INTEGER, mana_symbol_black INTEGER,"
			+ "mana_symbol_red INTEGER, mana_symbol_green INTEGER,"
			+ "mana_curve_0 INTEGER, mana_curve_1 INTEGER,"
			+ "mana_curve_2 INTEGER, mana_curve_3 INTEGER,"
			+ "mana_curve_4 INTEGER, mana_curve_5 INTEGER,"
			+ "mana_curve_6 INTEGER, mana_curve_7_plus INTEGER,"
			+ "estimated_deck_value_low  DOUBLE, estimated_deck_value_mid  DOUBLE,"
			+ "estimated_deck_value_high DOUBLE)";


	public DBHelper(Context context) 
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		// db.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, 
			int newVersion) 
	{
		Log.w(TAG, "Upgrading database from version " + oldVersion 
				+ " to "
				+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS decks");
		db.execSQL("DROP TABLE IF EXISTS cards");
		db.execSQL("DROP TABLE IF EXISTS statistics");
		onCreate(db);
	}

	@Override
	public void onOpen(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		//Open the database
		String myPath = DB_PATH + DATABASE_NAME;
		// myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

		super.onOpen(SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY));
	}

	//---retrieves a particular title---
	public Deck getDeckByName(String deck_name){
		Deck deck = new Deck();
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor deckCursor =
				db.query(true, DATABASE_TABLE_DECKS, new String[] {
						KEY_DECK_ID, KEY_DECK_NAME, 
						KEY_DECK_AUTHOR, KEY_DECK_FORMAT_ID }, 
						KEY_DECK_NAME + " = ? ", new String[] { deck_name }, null,null, null, null, null);
		if (deckCursor != null) {
			deckCursor.moveToFirst();
		}

		try {
			deck.deck_name = deckCursor.getString(deckCursor.getColumnIndex(KEY_DECK_NAME));
			deck.author = deckCursor.getString(deckCursor.getColumnIndex(KEY_DECK_AUTHOR));
			deck.format_id = deckCursor.getInt(deckCursor.getColumnIndex(KEY_DECK_FORMAT_ID));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		deck.mainDeckCards = getCardsforDeck(deck_name);
		deck.deckStatistics = getDeckStatistics(deck_name);
		
		return deck;
	}

	public Integer getDeckIdfromDeckName (String deck_name){
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor deck_id_from_name = db.query(DATABASE_TABLE_DECKS, new String[] {
        		KEY_DECK_ID}, KEY_DECK_NAME + " = ?", new String[]{ deck_name}, null, null, null, null);
		deck_id_from_name.moveToFirst();
		return deck_id_from_name.getInt(deck_id_from_name.getColumnIndex(KEY_DECK_ID));
	}
	
	public ArrayList<Card> getCardsforDeck (String deck_name){
		SQLiteDatabase db = this.getReadableDatabase();

		Integer deck_id = getDeckIdfromDeckName(deck_name); 
		
		Cursor cardlistCursor = db.query(DATABASE_TABLE_CARDS, new String[] {
        		KEY_CARDS_CARD_ID, KEY_CARDS_CARD_NAME, 
                KEY_CARDS_QUANTITY, KEY_CARDS_DECK_ID, 
                KEY_CARDS_SIDEBOARD},  KEY_CARDS_DECK_ID + " = ?", new String[] { Integer.toString(deck_id) }, null, null, null); 

		ArrayList<Card> cardlist = new ArrayList<Card>();
		cardlistCursor.moveToFirst();
		while(!cardlistCursor.isAfterLast()) {
			Card card = new Card();
			card.card_name = cardlistCursor.getString(cardlistCursor.getColumnIndex(KEY_CARDS_CARD_NAME));
			card.quantity = cardlistCursor.getInt(cardlistCursor.getColumnIndex(KEY_CARDS_QUANTITY));

			cardlist.add(card); //add the item
			cardlistCursor.moveToNext();
		}
		return cardlist;
	}

	public DeckStatistics getDeckStatistics(String deck_name) 
	{
		SQLiteDatabase db = this.getReadableDatabase();
		
		Integer deck_id = getDeckIdfromDeckName(deck_name);
		
		Cursor deck_statisticsCursor = db.query(DATABASE_TABLE_STATISTICS, new String[] {
				KEY_STATISTICS_TYPE_ARTIFACTS, KEY_STATISTICS_TYPE_CREATURES,
				KEY_STATISTICS_TYPE_ENCHANTMENTS, KEY_STATISTICS_TYPE_LAND,
				KEY_STATISTICS_TYPE_PLANESWALKER, KEY_STATISTICS_TYPE_SPELL,
				KEY_STATISTICS_TYPE_WHITE, KEY_STATISTICS_TYPE_BLUE, 
				KEY_STATISTICS_TYPE_BLACK, KEY_STATISTICS_TYPE_RED, 
				KEY_STATISTICS_TYPE_GREEN,
				KEY_STATISTICS_TYPE_MANA_COST_0, KEY_STATISTICS_TYPE_MANA_COST_1,
				KEY_STATISTICS_TYPE_MANA_COST_2, KEY_STATISTICS_TYPE_MANA_COST_3,
				KEY_STATISTICS_TYPE_MANA_COST_4, KEY_STATISTICS_TYPE_MANA_COST_5,
				KEY_STATISTICS_TYPE_MANA_COST_6, KEY_STATISTICS_TYPE_MANA_COST_7plus,
				KEY_STATISTICS_TYPE_estimated_deck_value_low, 
				KEY_STATISTICS_TYPE_estimated_deck_value_mid,
				KEY_STATISTICS_TYPE_estimated_deck_value_high}, 
				KEY_DECK_ID + " = ?", new String[] { (deck_id.toString()) }, null, null, null);
		if (deck_statisticsCursor != null) {
			deck_statisticsCursor.moveToFirst();
		}

		DeckStatistics deck_statistics = new DeckStatistics();

		try {
			deck_statistics.type_artifacts = deck_statisticsCursor.getInt(deck_statisticsCursor.getColumnIndex(KEY_STATISTICS_TYPE_ARTIFACTS));
			deck_statistics.type_creatures = deck_statisticsCursor.getInt(deck_statisticsCursor.getColumnIndex(KEY_STATISTICS_TYPE_CREATURES));
			deck_statistics.type_enchantments = deck_statisticsCursor.getInt(deck_statisticsCursor.getColumnIndex(KEY_STATISTICS_TYPE_ENCHANTMENTS));
			deck_statistics.type_land = deck_statisticsCursor.getInt(deck_statisticsCursor.getColumnIndex(KEY_STATISTICS_TYPE_LAND));
			deck_statistics.type_planeswalker = deck_statisticsCursor.getInt(deck_statisticsCursor.getColumnIndex(KEY_STATISTICS_TYPE_PLANESWALKER));
			deck_statistics.type_spell = deck_statisticsCursor.getInt(deck_statisticsCursor.getColumnIndex(KEY_STATISTICS_TYPE_SPELL));

			deck_statistics.mana_symbol_white = deck_statisticsCursor.getInt(deck_statisticsCursor.getColumnIndex(KEY_STATISTICS_TYPE_WHITE));
			deck_statistics.mana_symbol_blue = deck_statisticsCursor.getInt(deck_statisticsCursor.getColumnIndex(KEY_STATISTICS_TYPE_BLUE));
			deck_statistics.mana_symbol_black = deck_statisticsCursor.getInt(deck_statisticsCursor.getColumnIndex(KEY_STATISTICS_TYPE_BLACK));
			deck_statistics.mana_symbol_red = deck_statisticsCursor.getInt(deck_statisticsCursor.getColumnIndex(KEY_STATISTICS_TYPE_RED));
			deck_statistics.mana_symbol_green = deck_statisticsCursor.getInt(deck_statisticsCursor.getColumnIndex(KEY_STATISTICS_TYPE_GREEN));

			deck_statistics.mana_symbol_white = deck_statisticsCursor.getInt(deck_statisticsCursor.getColumnIndex(KEY_STATISTICS_TYPE_MANA_COST_0));
			deck_statistics.mana_symbol_blue = deck_statisticsCursor.getInt(deck_statisticsCursor.getColumnIndex(KEY_STATISTICS_TYPE_MANA_COST_1));
			deck_statistics.mana_symbol_black = deck_statisticsCursor.getInt(deck_statisticsCursor.getColumnIndex(KEY_STATISTICS_TYPE_MANA_COST_2));
			deck_statistics.mana_symbol_red = deck_statisticsCursor.getInt(deck_statisticsCursor.getColumnIndex(KEY_STATISTICS_TYPE_MANA_COST_3));
			deck_statistics.mana_symbol_green = deck_statisticsCursor.getInt(deck_statisticsCursor.getColumnIndex(KEY_STATISTICS_TYPE_MANA_COST_4));
			deck_statistics.mana_symbol_green = deck_statisticsCursor.getInt(deck_statisticsCursor.getColumnIndex(KEY_STATISTICS_TYPE_MANA_COST_5));
			deck_statistics.mana_symbol_green = deck_statisticsCursor.getInt(deck_statisticsCursor.getColumnIndex(KEY_STATISTICS_TYPE_MANA_COST_6));
			deck_statistics.mana_symbol_green = deck_statisticsCursor.getInt(deck_statisticsCursor.getColumnIndex(KEY_STATISTICS_TYPE_MANA_COST_7plus));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return deck_statistics;
	}
	
	public long insertDeck(Deck deck) 
    {
		SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        
        initialValues.put(KEY_DECK_NAME, deck.deck_name);
        initialValues.put(KEY_DECK_AUTHOR, deck.author);
        initialValues.put(KEY_DECK_FORMAT_ID, deck.format_id);
         
        return db.insert(DATABASE_TABLE_DECKS, null, initialValues);
    }
 
    //---deletes a particular deck---
    public boolean deleteDeck(long rowId) 
    {
    	SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(DATABASE_TABLE_DECKS, KEY_DECK_ID + "=" + rowId, null) > 0;
    }
    
    //---retrieves all the decks---
    public Cursor getAllDecks() 
    {
    	SQLiteDatabase db = this.getReadableDatabase();
        return db.query(DATABASE_TABLE_DECKS, new String[] {
        		KEY_DECK_ID, KEY_DECK_NAME,
        		KEY_DECK_AUTHOR, KEY_DECK_FORMAT_ID}, null, null, null, null, KEY_DECK_FORMAT_ID);
    }
}