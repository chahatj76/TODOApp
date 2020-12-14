package com.example.roomexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.task_detail_fragment.*

class TaskDetailFragment : Fragment() {


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