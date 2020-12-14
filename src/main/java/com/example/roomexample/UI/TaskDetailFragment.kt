package com.example.roomexample.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.roomexample.*
import com.example.roomexample.Data.PriorityLevel
import com.example.roomexample.Data.Task
import com.example.roomexample.Data.TaskStatus
import kotlinx.android.synthetic.main.task_detail_fragment.*

class TaskDetailFragment : Fragment() {

    private lateinit var viewModel: TaskDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this)
                .get(TaskDetailViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.task_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val priorities = mutableListOf<String>()
        PriorityLevel.values().forEach { priorities.add(it.name)}
        val arrayAdapter= ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, priorities)
        spinner.adapter = arrayAdapter

        spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                updateTaskPriorityView(position)
            }
        }
        val id = TaskDetailFragmentArgs.fromBundle(requireArguments()).id
        viewModel.setTaskId(id)

        viewModel.task.observe(viewLifecycleOwner,Observer{
            it?.let{
                setData(it)
            }
        })

        task_save.setOnClickListener{
            saveTask()
        }

        task_delete.setOnClickListener{
            deleteTask()
        }
    }

    private fun setData(task: Task){
        updateTaskPriorityView(task.priority)
        entertask_title.setText(task.title)
        entertask_desc.setText(task.detail)
        entertask_category.setText(task.category)
        if(task.status == TaskStatus.Open.ordinal){
            status_open.isChecked=true
        }else{
            status_closed.isChecked=true
        }
        spinner.setSelection(task.priority)
    }

    private fun saveTask(){
        val title = entertask_title.text.toString()
        val detail = entertask_desc.text.toString()
        val cate = entertask_category.text.toString()
        val priority = spinner.selectedItemPosition

        val selectedStatusButton = status_group.findViewById<RadioButton>(status_group.checkedRadioButtonId)
        var status = TaskStatus.Open.ordinal
        if(selectedStatusButton.text == TaskStatus.Closed.name){
            status = TaskStatus.Closed.ordinal
        }

        val task = Task(viewModel.taskId.value!!,title,detail,cate,priority,status)
        viewModel.saveTask(task)

        requireActivity().onBackPressed()
    }

    private fun deleteTask(){
       viewModel.deleteTask()

        requireActivity().onBackPressed()
    }

    private fun updateTaskPriorityView(priority: Int){
        when(priority){
            PriorityLevel.High.ordinal ->{
                priority_view.setBackgroundColor(
                    ContextCompat.getColor(requireActivity(), R.color.colorPriorityHigh))
            }
            PriorityLevel.Medium.ordinal ->{
               priority_view.setBackgroundColor(
                    ContextCompat.getColor(requireActivity(), R.color.colorPriorityMedium))
            }
            else ->  priority_view.setBackgroundColor(
                ContextCompat.getColor(requireActivity(), R.color.colorPriorityLow))
        }
    }
}