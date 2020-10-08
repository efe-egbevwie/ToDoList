package com.efe.todo.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.preference.PreferenceManager

class ListDataManager(application: Application): AndroidViewModel(application) {

    private val context = application.applicationContext

    fun saveList(list: TaskList) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context).edit()
        //sharedprefs don't store array so we convert the Tasklist toHashSet
        sharedPreferences.putStringSet(list.name, list.tasks.toHashSet())
        sharedPreferences.apply()
    }

    fun removeList(list: TaskList) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context).edit()
        sharedPreferences.remove(list.name)
        sharedPreferences.apply()
    }




    fun readList():ArrayList<TaskList> {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val contents = sharedPreferences.all
        //use the taskarraylist to retrieve the tasklists but cast it to a hashset
        val taskArrayLists = ArrayList<TaskList>()

        for (taskList in contents) {
            val taskItems = ArrayList(taskList.value as HashSet<String>)
            val list = TaskList(taskList.key, taskItems)
            taskArrayLists.add(list)
        }
        return taskArrayLists
    }
}