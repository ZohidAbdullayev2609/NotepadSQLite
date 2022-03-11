package com.example.notepadsqlite.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.notepadsqlite.model.Notepad

class DBHelper(context: Context) :
    SQLiteOpenHelper(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION),
    DatabaseInterface {

    override fun onCreate(db: SQLiteDatabase?) {
        val quary =
            "create table ${Constants.TABLE_NAME}(${Constants.ID} integer primary key autoincrement, ${Constants.TITLE} text,${Constants.DESCRIPTION} text)"
        db?.execSQL(quary)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    override fun insert(notepad: Notepad) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(Constants.TITLE, notepad.title)
        contentValues.put(Constants.DESCRIPTION, notepad.description)
        db.insert(Constants.TABLE_NAME, null, contentValues)
        db.close()
    }

    override fun readAllUsers(): ArrayList<Notepad> {
        val list = ArrayList<Notepad>()
        val quary = "select * from ${Constants.TABLE_NAME}"
        val db = this.writableDatabase
        val cursor = db.rawQuery(quary, null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(0)
                val title = cursor.getString(1)
                val description = cursor.getString(2)
                val notepad = Notepad(title, description, id)
                list.add(notepad)
            } while (cursor.moveToNext())
        }
        return list
    }

    override fun delete(id: String?) {
        val db = this.writableDatabase
        db.delete(Constants.TABLE_NAME, "${Constants.ID} LIKE ? ", arrayOf(id))
        db.close()
    }

    override fun update(notepad: Notepad, id: String?) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(Constants.TITLE, notepad.title)
        contentValues.put(Constants.DESCRIPTION, notepad.description)
        db.update(Constants.TABLE_NAME, contentValues, "${Constants.ID} = $id", null)
        db.close()
    }

}