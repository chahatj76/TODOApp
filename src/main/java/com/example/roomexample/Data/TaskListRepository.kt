package com.example.roomexample.Data

import android.app.Application
import androidx.lifecycle.LiveData

class TaskListRepository(context: Application) {
    private val taskListDao: TaskListDao = TaskDatabase.getDatabase(context).taskListDao()

            fun getTasks(sort: SortColumn = SortColumn.Priority
            ): LiveData<List<Task>> {
                return if(sort== SortColumn.Priority){
                    taskListDao.getTasksbyPriority(TaskStatus.Open.ordinal)
                }else {
                    taskListDao.getTasksbyTitle(TaskStatus.Open.ordinal)
                }
            }
}