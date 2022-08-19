package com.example.project_2_student.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class MarksNoteDB extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "private_notes";
    private static final String DATABASE_TABLE = "marks_notes_table";


    //columns name for database tables

    private static final String KEY_ID = "marks_note_id";
    private static final String KEY_MESSAGE = "marks_note_message";
    private static final String KEY_START_DATE = "marks_note_start_date";

    public MarksNoteDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //create table
        String query = "CREATE TABLE " + DATABASE_TABLE + "(" + KEY_ID + "INT PRIMARY KEY," +
                KEY_MESSAGE + " TEXT," +
                KEY_START_DATE + " TEXT " + ")";
        //For execute the query and create the table
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //To upgrade the data base version

        if (oldVersion >= newVersion)
            return;

        db.execSQL(" DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }



    public void deleteTable()
    {
        SQLiteDatabase db = getWritableDatabase() ;
        db.execSQL(" DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }

    public boolean isExists(int id )
    {
        //select * from databaseTable where id = 1
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(DATABASE_TABLE , new String[]{KEY_ID , KEY_MESSAGE , KEY_START_DATE }, KEY_ID +"=?" ,
                new String[]{ String.valueOf(id)},null,null,null);

        if (cursor !=null) {
            cursor.moveToFirst();
            return true;
        }else
            return false ;

    }
    public long addNote(PrivateNotes privateNote) {
        //For write in the data base
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_MESSAGE, privateNote.getMessage());
        contentValues.put(KEY_START_DATE, privateNote.getStart_date());

        long ID = sqLiteDatabase.insert(DATABASE_TABLE, null, contentValues);
        Log.d("inserted", "ID -> " + ID);

        return ID;
    }

    public PrivateNotes getPrivateNote(long id) {
        //select * from databaseTable where id = 1
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(DATABASE_TABLE, new String[]{KEY_ID, KEY_MESSAGE, KEY_START_DATE}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        return new PrivateNotes(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
    }

    public ArrayList<PrivateNotes> getAllPrivateNotes() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<PrivateNotes> allPovateNotes = new ArrayList<>();

        //select * from databaseName
        String query = "SELECT * FROM " + DATABASE_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {

                PrivateNotes privateNote = new PrivateNotes();
                privateNote.setId(cursor.getInt(0));
                privateNote.setMessage(cursor.getString(1));
                privateNote.setStart_date(cursor.getString(2));

                allPovateNotes.add(privateNote);

            } while (cursor.moveToNext());

        }

        cursor.close();
        return allPovateNotes;
    }
}
