package com.example.boredombuster.Model.Database

import androidx.room.*

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(task: Task)
    @Query("SELECT * FROM task WHERE key=:key")
    fun getTask(key: String): Task

    @Query("SELECT * FROM task WHERE favorite= 1")
    fun getTaskList(): List<Task>

    @Query("SELECT * FROM task WHERE type=:type")
    fun getTaskDetail(type: String): Task

    @Update
    fun updateFavorite(task: Task)

}