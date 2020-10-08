package com.efe.todo.viewholders

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.efe.todo.R

class TodoListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    var listTitleTextView = itemView.findViewById<TextView>(R.id.itemString)
    var deleteListButton = itemView.findViewById<Button>(R.id.delete_list_button)
}