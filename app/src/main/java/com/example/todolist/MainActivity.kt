package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoAdapter = TodoAdapter(mutableListOf())
        rvMainView.adapter = todoAdapter
        rvMainView.layoutManager = LinearLayoutManager(this)

        btnAddTodoItem.setOnClickListener {
            val todoTitle = etNewTodoItem.text.toString()
            if(todoTitle.isNotEmpty()){
                val todoItem = Todo(todoTitle)
                todoAdapter.addTodo(todoItem)
                etNewTodoItem.text.clear()
            }
        }

        btnDeleteTodoItem.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }
    }


}