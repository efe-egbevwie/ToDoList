package com.efe.todo.fragments

import android.content.Context
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.efe.todo.*
import com.efe.todo.adapters.TodoListAdapter
import com.efe.todo.models.ListDataManager
import com.efe.todo.models.TaskList
import kotlinx.android.synthetic.main.fragment_to_do_list.*


class ToDoListFragment : Fragment(), TodoListAdapter.ToDoListClickListener {

    private lateinit var todoListRecyclerView: RecyclerView
    private lateinit var  listDataManager: ListDataManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_to_do_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {  listDataManager = ViewModelProvider(this).get(ListDataManager::class.java)
        }
        val lists = listDataManager.readList()
        todoListRecyclerView = view.findViewById(R.id.list_recyclerview)
        todoListRecyclerView.layoutManager = LinearLayoutManager(activity)
        todoListRecyclerView.adapter = TodoListAdapter(lists, this)

        floatingActionButton.setOnClickListener{ _ ->
            showToDoListDialog()
        }


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    interface OnFragmentInteractionListener {
        fun onTodoListClicked(list: TaskList)
    }

    companion object {

        fun newInstance(): ToDoListFragment {
            return ToDoListFragment()
        }
    }

    override fun listItemClicked(list: TaskList) {
        showTodoItem(list)
    }

    override fun deleteButtonClicked(list: TaskList) {
        deleteList(list)
    }

    private fun addList(list: TaskList) {
        listDataManager.saveList(list)
        val adapter = todoListRecyclerView.adapter as TodoListAdapter
        adapter.addList(list)
    }

    private fun deleteList(list: TaskList) {
        listDataManager.removeList(list)
        val adapter = todoListRecyclerView.adapter as TodoListAdapter
        adapter.deleteList(list)
    }

    fun saveList(list: TaskList) {
        listDataManager.saveList(list)
        updateLists()
    }
    private fun showToDoListDialog() {
        activity?.let {
            val dialogTitle = getString(R.string.dialog_title)
            val positiveButtonTitle = getString(R.string.create_list)
            val dialog = AlertDialog.Builder(it)
            val toDoListEditText = EditText(it)
            toDoListEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_WORDS

            dialog.setTitle(dialogTitle)
                .setView(toDoListEditText)
                .setPositiveButton(positiveButtonTitle) {
                        dialog, _ ->
                    val list = TaskList(name = toDoListEditText.text.toString())
                    addList(list)

                    dialog.dismiss()
                    showTodoItem(list)
                }
            dialog.create().show()
        }

    }
    private fun showTodoItem(list: TaskList) {
        view?.let {
            val action = ToDoListFragmentDirections.actionToDetailFragment(list.name)
            it.findNavController().navigate(action)
        }

    }

    private fun updateLists() {
        val lists  = listDataManager.readList()
        todoListRecyclerView.adapter = TodoListAdapter(lists,this)
    }

}