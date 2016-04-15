package com.example.andy.internetdictionary;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by andy on 1/23/2016.
 */
public class dbHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "dictionary.dbHandler";
    private static final String TABLE_DICTIONARY = "dictionary";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "word";
    private static final String COLUMN_DEFINITION = "definition";
    public dbHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String Create_Dictionary_Table = "CREATE TABLE IF NOT EXISTS " +
                TABLE_DICTIONARY + "(" +
                COLUMN_ID + " INT PRIMARY KEY," + COLUMN_NAME
                + " TEXT," + COLUMN_DEFINITION + " TEXT" +")";
        db.execSQL(Create_Dictionary_Table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DICTIONARY);
        onCreate(db);
    }

    public void addDictionary(Word word) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, word.getWord());
        values.put(COLUMN_DEFINITION, word.getDefinition());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_DICTIONARY, null, values);
        db.close();
    }

    public Word findWord(String name) {
        String query = "Select * FROM " + TABLE_DICTIONARY + " WHERE " + COLUMN_NAME + " = \"" + name + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        System.out.println("Success on getting the database");
        Cursor cursor = db.rawQuery(query, null);
        System.out.println("Query Syntax correct");
        Word word = new Word();
        System.out.println("Creating a temp word object");
        if(cursor.moveToFirst()) {
            cursor.moveToFirst();
            System.out.println("Move the cursor");
            word.setID(cursor.getInt(0));
            System.out.println("Saved the id");
            word.setWord(cursor.getString(1));
            System.out.println("Saved the word");
            word.setDefinition(cursor.getString(2));
            System.out.println("Saved the Meaning");
            cursor.close();
        } else {
            return null;
        }
        db.close();
        return word;
    }

    public boolean deleteWord(String name) {
        boolean result = false;

        String query = "SELECT * FROM " + TABLE_DICTIONARY + " WHERE " + COLUMN_NAME + " = \"" + name + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Word word = new Word();

        if(cursor.moveToFirst()) {
            word.setID(cursor.getInt(0));
            db.delete(TABLE_DICTIONARY, COLUMN_ID + " = ? ",
                    new String[]{String.valueOf(word.getID())});
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }
    public String[] getWords() {

        String query = "SELECT * FROM " + TABLE_DICTIONARY;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        cursor.moveToFirst();

        String[] words = new String[cursor.getCount()];

        if(cursor.moveToFirst()) {
            int i = 0;
            while(!cursor.isAfterLast()) {
                words[i] = cursor.getString(1);
                cursor.moveToNext();
                System.out.println(words[i]);
                i++;
            }
        }
        return words;
    }
}
