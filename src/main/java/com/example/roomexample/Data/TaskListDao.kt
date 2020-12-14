package com.example.roomexample.Data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface TaskListDao {
    @Query( "SELECT * FROM task WHERE status = :status ORDER BY priority DESC")
    fun getTasksbyPriority(status: Int) : LiveData<List<Task>>

    @Query( "SELECT * FROM task WHERE status = :status ORDER BY title")
    fun getTasksbyTitle(status: Int) : LiveData<List<Task>>
}