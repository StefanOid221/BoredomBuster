package com.example.boredombuster.SecondActivity

import android.app.Dialog
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.boredombuster.Model.Database.Task
import com.example.boredombuster.Model.Model
import com.example.boredombuster.R

class RecyclerActivityView: AppCompatActivity() {

    lateinit var presenter: RecyclerActivityPresenter
    private lateinit var recyclerView: RecyclerView
    lateinit var adapter: RecyclerAdapter
    var allTasks: ArrayList<Task> = ArrayList<Task>()
    private var dialog: Dialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_activity)

        Log.d("e","se inicia")
        presenter = RecyclerActivityPresenter(Model(applicationContext), this)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = RecyclerAdapter(allTasks, onClickListener = {view, Task -> this.presenter.showDialog(Task)})
        recyclerView.adapter = adapter

        presenter.replenishAdapter()


    }

    fun showDialog(task:Task){
        dialog = Dialog(this)
        dialog?.setContentView(R.layout.dialog_custom)
        val subjectCodeTextView = dialog?.findViewById<TextView>(R.id.taskKey)
        subjectCodeTextView?.text = task.key
        val okButton = dialog?.findViewById<Button>(R.id.button_ok)
        val imageButton = dialog?.findViewById<ImageButton>(R.id.imageFavorite)
        val subjectNameTextView = dialog?.findViewById<TextView>(R.id.taskActivity)
        subjectNameTextView?.text = task.activity
        okButton?.setOnClickListener {
            dialog?.dismiss()
        }
        if (task.favorite){
            imageButton?.setBackgroundResource(R.drawable.ic_baseline_star)
        }
        else if (!task.favorite){
            imageButton?.setBackgroundResource(R.drawable.ic_baseline_no_fav_star)
        }
        imageButton?.setOnClickListener{
            if (task.favorite){
                imageButton.setBackgroundResource(R.drawable.ic_baseline_no_fav_star)
                task.favorite = false
                presenter.updateFavorite(task)
            }
            else if (task.favorite == false){
                imageButton.setBackgroundResource(R.drawable.ic_baseline_star)
                task.favorite = true
                presenter.updateFavorite(task)
            }
        }

        dialog?.show()
    }
}