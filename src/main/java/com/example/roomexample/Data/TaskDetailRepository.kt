package com.example.roomexample.Data

import android.app.Application
import androidx.lifecycle.LiveData


class TaskDetailRepository (context:Application){
      private val taskDetailDao: TaskDetailDao = TaskDatabase.getDatabase(context).taskDetailDao()

    fun getTask(id: Long): LiveData<Task>{
        return taskDetailDao.getTask(id)
    }

    suspend fun insertTask(task: Task): Long{
        return taskDetailDao.insertTask(task)
    }


    suspend fun updateTask(task: Task){
        return taskDetailDao.updateTask(task)
    }

    suspend fun deleteTask(task: Task){
        return taskDetailDao.deleteTask(task)
    }
}