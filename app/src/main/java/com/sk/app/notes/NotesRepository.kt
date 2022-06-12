package com.sk.app.notes

import androidx.lifecycle.LiveData

class NotesRepository(val dao: NotesDao) {
    fun getAllNotes():LiveData<List<Notes>> = dao.getNotes()
    fun getHighNotes():LiveData<List<Notes>> = dao.getHighNotes()
    fun getMediumNotes():LiveData<List<Notes>> = dao.getMediumNotes()
    fun getLowNotes():LiveData<List<Notes>> = dao.getLowNotes()


    fun insertNotes(notes: Notes){
        dao.insertNotes(notes)
    }

    fun deleteNotes(id:Int){
        dao.deleteNotes(id)
    }

    fun updateNotes(notes: Notes){
        dao.updateNotes(notes)
    }
}