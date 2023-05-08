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

        holder.textViewType.text = taskList[position].type
        holder.textViewActivity.text = taskList[position].activity
        holder.textViewParticipants.text = taskList[position].participants.toString()
        holder.textViewPrice.text = taskList[position].price.toString()

        val subject = taskList[position]
        holder.textViewActivity.setOnClickListener{view->
            onClickListener.invoke(view,subject)
        }
    }

    override fun getItemCount(): Int = taskList.size
    fun updateAdapter(allTasks: java.util.ArrayList<Task>) {
        taskList = allTasks
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewType : TextView = view.findViewById(R.id.taskCategory)
        val textViewActivity : TextView = view.findViewById(R.id.taskActivity)
        val textViewParticipants : TextView = view.findViewById(R.id.taskParticipants)
        val textViewPrice : TextView = view.findViewById(R.id.taskPrice)

    }
}