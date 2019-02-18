package com.example.todo

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // Kotlin Android Extensions do FindViewById automatically !!
    // private lateinit var et: EditText
    // private lateinit var recyclerView: RecyclerView

    private lateinit var viewAdapter: MyAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    private lateinit var todoModel: TodoModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewManager = LinearLayoutManager(this)
        viewAdapter = MyAdapter()

        todoModel = ViewModelProviders.of(this).get(TodoModel::class.java)

        todoModel.data.observe(this, Observer<List<Todo>> {
            it?.let {
                viewAdapter.setData(it)

            }
        })

        recyclerView.apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }

        //et = findViewById(R.id.editText)
    }

    fun addButton(view: View) {

        if(editText.text.isNotEmpty()) {
            todoModel.addTodo(editText.text.toString())
        }
    }
}
