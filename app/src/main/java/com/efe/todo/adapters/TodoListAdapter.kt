package com.efe.todo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.efe.todo.R
import com.efe.todo.viewholders.TodoListViewHolder
import com.efe.todo.models.TaskList
import kotlinx.android.synthetic.main.todo_list_viewholder.view.*

class TodoListAdapter(private val lists:ArrayList<TaskList>, val clickListener: ToDoListClickListener): RecyclerView.Adapter<TodoListViewHolder>() {

    interface ToDoListClickListener{
        fun listItemClicked(list: TaskList)
        fun deleteButtonClicked(list: TaskList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.todo_list_viewholder, parent, false)
        return TodoListViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) {
        holder.listTitleTextView.text = lists[position].name
        holder.itemView.setOnClickListener{
            clickListener.listItemClicked(lists[position])
        }
        holder.itemView.delete_list_button.setOnClickListener{
            clickListener.deleteButtonClicked(lists[position])

        }
    }

    override fun getItemCount(): Int {
        return lists.size
    }

    fun addList(list: TaskList) {
        lists.add(list)
        notifyItemInserted(lists.size-1)
    }

    fun deleteList(list: TaskList) {
        lists.remove(list)
        notifyItemRemoved(lists.size)
    }
}