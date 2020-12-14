package com.example.roomexample

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.LinearLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.task_list_fragment.*


class TaskListFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.task_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
           with(task_list){
               layoutManager = LinearLayoutManager(activity)
               adapter = TaskAdapter{
                   findNavController().navigate(
                       TaskListFragmentDirections.actionTaskListFragmentToTaskDetailFragment(it))
               }
           }

        add_task.setOnClickListener{
            findNavController().navigate(
                TaskListFragmentDirections.actionTaskListFragmentToTaskDetailFragment(0))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, menuinflater: MenuInflater) {
        menuinflater.inflate(R.menu.menu_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_sort_priority -> {
                item.isChecked= true
                true
            }
            R.id.menu_sort_title -> {
                item.isChecked= true
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}