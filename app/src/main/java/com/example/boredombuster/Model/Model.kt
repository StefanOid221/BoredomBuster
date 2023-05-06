package com.example.boredombuster.Model

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import com.android.volley.Response
import com.example.boredombuster.Model.Database.Task
import com.example.boredombuster.Model.Database.TaskDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Model (context: Context) {

    private val network = Network.getInstance(context)
    private val dao = TaskDatabase.getInstance(context).dao

    fun getTask(
        fromNetwork: Boolean,
        key : String?,
        listener: Response.Listener<Task>,
        errorListener: Response.ErrorListener) = GlobalScope.launch(Dispatchers.Main)
    {
        if (!fromNetwork && key != null){
            val task = withContext(Dispatchers.IO) {
                dao.getTask(key)
            }
            if (task != null)
                listener.onResponse(task)
            else listener.onResponse(null)
        }
        else{
            network.getNormalTask(Response.Listener {
                GlobalScope.launch {
                    dao.insertTask(it)
                }
                listener.onResponse(it)
            }, errorListener
            )
        }
        }

    fun getTypeTask(
        type: String,
        listener: Response.Listener<Task>,
        errorListener: Response.ErrorListener) = GlobalScope.launch(Dispatchers.Main)
    {

            network.getTypeTask(
                type,
                Response.Listener {
                GlobalScope.launch {
                    dao.insertTask(it)
                }
                listener.onResponse(it)
            }, errorListener
            )
        }

    fun updateFavorite(
        task: Task
    ) {
        AsyncTask.execute {
            dao.updateFavorite(task)
        }
    }

    fun getAllTasks(
        listenerAllTasks: Response.Listener<ArrayList<Task>>,
        errorListener: Response.ErrorListener
    ) = GlobalScope.launch(Dispatchers.Main) {

        val tasks = withContext(Dispatchers.IO) {
            dao.getTaskList()
        }
        Log.d("·",tasks.toString())
        listenerAllTasks.onResponse(ArrayList(tasks))

    }


}