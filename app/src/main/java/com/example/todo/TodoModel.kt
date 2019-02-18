package com.example.todo

import android.app.Application
import android.arch.lifecycle.*
import android.os.AsyncTask

class TodoModel(app: Application) : AndroidViewModel(app) {
    private val database = AppDatabase.getDatabase(getApplication())

    val data = database.todoDao().findAllTodos()

    fun addTodo(todo: String) {
        // Don't Work on the main thread => AsyncTask
        // database.todoDao().insert(Todo(0, todo))

        // Can be shorter in Kotlin !!!
        // InsertTodoAsyncTask(database.todoDao()).execute(Todo(0, todo))
        AsyncTask.execute {
            database.todoDao().insert(Todo(0, todo))
        }
    }

//    private class InsertTodoAsyncTask(todoDao: TodoDao) : AsyncTask<Todo, Unit, Unit>() {
//        val todoDao = todoDao
//
//        override fun doInBackground(vararg p0: Todo?) {
//            p0[0]?.let {
//                todoDao.insert(it)
//            }
//        }
//    }
}