package com.example.project_2_student.Models;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;


public class GeneralNotesDB extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "general_notes";
    private static final String DATABASE_TABLE  ="general_note_table";


    //columns name for database tables
    private static final String KEY_ID = "general_note_id" ;
    private static final String KEY_MESSAGE = "general_note_message" ;
    private static final String KEY_TITLE = "general_note_title" ;
    private static final String KEY_EXPIRED_DATE = "general_note_expired_date" ;
    public GeneralNotesDB(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //create table
        String query = "CREATE TABLE " + DATABASE_TABLE + "(" + KEY_ID + "INT PRIMARY KEY,"+
                KEY_TITLE + " TEXT,"+
                KEY_MESSAGE + " TEXT," +
                KEY_EXPIRED_DATE + " TEXT " + ")";
        //For execute the query and create the table
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion >= newVersion)
            return;

        db.execSQL(" DROP TABLE IF EXISTS " + DATABASE_TABLE);
    }

    public void deleteTable()
    {
        SQLiteDatabase db = getWritableDatabase() ;
        db.execSQL(" DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }

    public long addGeneralNote(GeneralNotes generalNotes)
    {
        //For write in the data base

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TITLE , generalNotes.getTitle());
        contentValues.put(KEY_MESSAGE , generalNotes.getMessage());
        contentValues.put(KEY_EXPIRED_DATE , generalNotes.getExp_date());

        long ID = sqLiteDatabase.insert(DATABASE_TABLE , null , contentValues );

        return ID ;
    }

    public boolean isExists(int id )
    {
        //select * from databaseTable where id = 1
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(DATABASE_TABLE , new String[]{KEY_ID , KEY_TITLE , KEY_MESSAGE , KEY_EXPIRED_DATE }, KEY_ID +"=?" ,
                new String[]{ String.valueOf(id)},null,null,null);

        if (cursor !=null) {
            cursor.moveToFirst();
            return true;
        }else
            return false ;

    }

    public GeneralNotes getGeneralNote(long id)
    {
        //select * from databaseTable where id = 1
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(DATABASE_TABLE , new String[]{KEY_ID , KEY_TITLE , KEY_MESSAGE , KEY_EXPIRED_DATE }, KEY_ID +"=?" ,
                new String[]{ String.valueOf(id)},null,null,null);

        if (cursor !=null)
            cursor.moveToFirst();

        return new GeneralNotes(cursor.getInt(0),cursor.getString(1) , cursor.getString(2) , cursor.getString(3));
    }

    //@SuppressLint("Range")
    @SuppressLint("Range")
    public ArrayList<GeneralNotes> getAllGeneralNotes()
    {
        SQLiteDatabase sqLiteDatabase =this.getReadableDatabase();
        ArrayList<GeneralNotes> allGeneralNotes =new ArrayList<>();

        //select * from databaseName
        String query = "SELECT * FROM " + DATABASE_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if (cursor!=null && cursor.moveToFirst())
        {
            do {

                GeneralNotes generalNote = new GeneralNotes();
                generalNote.setId(cursor.getInt(cursor.getColumnIndex(GeneralNotesDB.KEY_ID)));
                generalNote.setTitle(cursor.getString(cursor.getColumnIndex(GeneralNotesDB.KEY_TITLE)));
                generalNote.setMessage(cursor.getString(cursor.getColumnIndex(GeneralNotesDB.KEY_MESSAGE)));
                generalNote.setExp_date(cursor.getString(cursor.getColumnIndex(GeneralNotesDB.KEY_EXPIRED_DATE)));

                allGeneralNotes.add(generalNote);

            }while (cursor.moveToNext());

        }

        cursor.close();
        return allGeneralNotes;
    }
}
