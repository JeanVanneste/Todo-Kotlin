package com.example.todo

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
interface TodoDao {

    @Query("SELECT * FROM Todo")
    fun findAllTodos(): LiveData<List<Todo>>

    @Insert
    fun insert(todo: Todo)

    @Query("DELETE FROM Todo")
    fun deleteAll()
}