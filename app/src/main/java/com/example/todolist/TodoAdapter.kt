package com.example.todolist
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

class TodoAdapter (
    private val todos: MutableList<Todo>
        ): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {// herda de recyclerView.adapter com
    // a classe criada abaixo

    class TodoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)//classe criada como holder
    // herda de view holder e recebe itemView como parametro

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo, //pegando o layout para usar (layout == xml)
                parent, //variável "this", que veio com o onCreateViewHolder do tipo ViewGroup
                false //não grudar no root
            )
        )
    }

    fun addTodo(newTodoItem: Todo){
        todos.add(newTodoItem)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteDoneTodos(){
        todos.removeAll{e -> e.checked }
        notifyDataSetChanged()
    }

    private fun toogleRiscaTexto(textView: TextView, isChecked: Boolean){
        if(isChecked){
            textView.paintFlags = textView.paintFlags or STRIKE_THRU_TEXT_FLAG
        }else{
            textView.paintFlags = textView.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentTodo = todos[position]
        holder.itemView.apply {
            tvItemName.text = currentTodo.title
            cbDone.isChecked = currentTodo.checked
            toogleRiscaTexto(tvItemName,currentTodo.checked)
            cbDone.setOnCheckedChangeListener { _, isChecked ->
                toogleRiscaTexto(tvItemName, isChecked)
                currentTodo.checked = !currentTodo.checked //toogle true e false para
            // atualizar com o de cima
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}