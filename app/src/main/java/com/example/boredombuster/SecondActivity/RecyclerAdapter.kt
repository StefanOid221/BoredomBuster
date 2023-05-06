package com.example.boredombuster.SecondActivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.boredombuster.Model.Database.Task
import com.example.boredombuster.R

class RecyclerAdapter(var taskList: ArrayList<Task>, private val onClickListener:(View, Task)->Unit): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_line, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewCode.text = taskList[position].activity
        holder.textViewName.text = taskList[position].key
        val subject = taskList[position]
        holder.textViewName.setOnClickListener{view->
            onClickListener.invoke(view,subject)
        }
    }

    override fun getItemCount(): Int = taskList.size
    fun updateAdapter(allTasks: java.util.ArrayList<Task>) {
        taskList = allTasks
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewCode : TextView = view.findViewById(R.id.subjectCode)
        val textViewName : TextView = view.findViewById(R.id.subjectName)
    }
}