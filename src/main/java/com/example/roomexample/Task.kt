package com.example.roomexample

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

enum class SortColumn{
    Title,Priority
}

enum class TaskStatus{
    Open,Closed
}

enum class PriorityLevel{
    High,Medium,Low
}


@Entity(tableName = "task")
data class Task(@PrimaryKey(autoGenerate = true) val id: Long,
                val title: String,
                val detail: String,
                val category: String,
                @ColumnInfo(name = "priority") val priority : Int,
                val status: Int)