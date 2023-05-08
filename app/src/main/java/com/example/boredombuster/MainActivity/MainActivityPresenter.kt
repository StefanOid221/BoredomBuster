package com.example.boredombuster.MainActivity

import android.util.Log
import com.android.volley.Response
import com.example.boredombuster.Model.Database.Task
import com.example.boredombuster.Model.Model

class MainActivityPresenter (private val model: Model, private val view: MainActivity) {

    init {



    }
    fun getTask(fromNetwork: Boolean, key : String?) {
        var errorListener = Response.ErrorListener {
        }
        var listenerTask = object: Response.Listener<Task> {
            override fun onResponse(response: Task){
                view.showDialog(response)
            }
        }
        model.getTask(fromNetwork,key,listenerTask,errorListener)
    }

    fun getTypeTask(type: String) {
        var errorListener = Response.ErrorListener {
        }
        var listenerTask = object: Response.Listener<Task> {
            override fun onResponse(response: Task){
                view.showDialog(response)
            }
        }
        model.getTypeTask(type,listenerTask,errorListener)
    }

    fun updateFavorite(task: Task) {
        model.updateFavorite(task)
    }
    fun getAllTasks(){
        var errorListener = Response.ErrorListener {
        }
        var listenerAllTasks = object: Response.Listener<ArrayList<Task>> {
            override fun onResponse(response: ArrayList<Task>){
                view.allTasks = response
            }
        }
        model.getAllTasks(listenerAllTasks,errorListener)
    }
}