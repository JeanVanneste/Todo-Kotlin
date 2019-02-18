package com.example.todo

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [(Todo::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
    companion object {
        private var INSTANCE: AppDatabase? = null
        private const val DB_NAME = "TodoDB"

        fun getDatabase(context: Context): AppDatabase {
            val i = INSTANCE
            if(i != null) return i
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DB_NAME
            ).build()
        }

        fun destroyInstance() { INSTANCE = null }
    }
}