package com.example.boredombuster.SecondActivity

import android.util.Log
import com.android.volley.Response
import com.example.boredombuster.Model.Database.Task
import com.example.boredombuster.Model.Model

class RecyclerActivityPresenter (private val model: Model, private val view: RecyclerActivityView) {

    init{


    }

    fun showDialog(task: Task){
        var errorListener = Response.ErrorListener {
        }
        var listenerTask = object : Response.Listener<Task> {
            override fun onResponse(response: Task) {
                view.showDialog(response)
            }
        }
        model.getTask(true,task.key,listenerTask,errorListener)
    }
    fun updateFavorite(task: Task) {
        model.updateFavorite(task)
    }
    fun updateAdapter(function: () -> Unit) {
        view.adapter.updateAdapter(view.allTasks)
    }
    fun replenishAdapter(){
        var errorListener = Response.ErrorListener {
        }
        var listenerAllTasks = object: Response.Listener<ArrayList<Task>> {
            override fun onResponse(response: ArrayList<Task>){
                view.allTasks = response
                for (i in 0 until response.size){
                    Log.d("e", response[i].favorite.toString())
                }
                view.adapter.updateAdapter(response)
            }
        }
        model.getAllTasks(listenerAllTasks,errorListener)
    }
}