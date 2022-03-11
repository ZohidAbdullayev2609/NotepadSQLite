package com.example.notepadsqlite.database

import com.example.notepadsqlite.model.Notepad

interface DatabaseInterface {

    fun insert(notepad: Notepad)

    fun readAllUsers(): ArrayList<Notepad>

    fun delete(id:String?)

    fun update(notepad: Notepad, id: String?)

}