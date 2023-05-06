package com.example.boredombuster.Model.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Task::class],
    version = 4
)
private abstract class AbstractGradeDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao
}

class TaskDatabase private constructor(context: Context) {
    val dao = Room.databaseBuilder(context, AbstractGradeDatabase::class.java, "tasks")
        .fallbackToDestructiveMigration()
        .build()
        .taskDao()

    companion object {
        @Volatile
        private var instance: TaskDatabase? = null

        fun getInstance(context: Context): TaskDatabase =
            instance ?: synchronized(this) {
                instance ?: TaskDatabase(context).also { instance = it }
            }
    }
}