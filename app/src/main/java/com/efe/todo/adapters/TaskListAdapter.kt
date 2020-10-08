package com.efe.todo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.efe.todo.R
import com.efe.todo.viewholders.TaskListViewHolder
import com.efe.todo.models.TaskList

class TaskListAdapter(var list: TaskList) : RecyclerView.Adapter<TaskListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListViewHolder {
      val view = LayoutInflater.from(parent.context)
          .inflate(R.layout.task_view_holder, parent, false)
        return TaskListViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskListViewHolder, position: Int) {
        holder.taskTextView.text = list.tasks[position]
    }

    override fun getItemCount(): Int {
        return list.tasks.size
    }
}