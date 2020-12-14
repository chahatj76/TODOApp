package com.example.roomexample.UI

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomexample.R
import com.example.roomexample.Data.SortColumn
import kotlinx.android.synthetic.main.task_list_fragment.*


class TaskListFragment : Fragment() {

    private lateinit var viewModel: TaskListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)

        viewModel = ViewModelProviders.of(this)
                .get(TaskListViewModel::class.java)
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

        viewModel.tasks.observe(viewLifecycleOwner, Observer {
            (task_list.adapter as TaskAdapter).submitList(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, menuinflater: MenuInflater) {
        menuinflater.inflate(R.menu.menu_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_sort_priority -> {
                viewModel.changeSortOrder(SortColumn.Priority)
                item.isChecked= true
                true
            }
            R.id.menu_sort_title -> {
                viewModel.changeSortOrder(SortColumn.Title)
                item.isChecked= true
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}