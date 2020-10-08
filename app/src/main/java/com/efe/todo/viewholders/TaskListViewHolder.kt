package com.efe.todo.viewholders

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.efe.todo.R

class TaskListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val taskTextView = itemView.findViewById<TextView>(R.id.textView_task) as TextView
    val deleteTask = itemView.findViewById<Button>(R.id.delete_task_button) as Button

}