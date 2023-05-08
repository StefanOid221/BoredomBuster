package com.example.boredombuster.SecondActivity

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.boredombuster.MainActivity.MainActivity
import com.example.boredombuster.Model.Database.Task
import com.example.boredombuster.Model.Model
import com.example.boredombuster.R

class RecyclerActivityView: AppCompatActivity() {

    lateinit var presenter: RecyclerActivityPresenter
    private lateinit var recyclerView: RecyclerView
    lateinit var adapter: RecyclerAdapter
    private lateinit var button: Button
    var allTasks: ArrayList<Task> = ArrayList<Task>()
    private var dialog: Dialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_activity)

        supportActionBar?.title = "Favorite Activities List"

        presenter = RecyclerActivityPresenter(Model(applicationContext), this)

        button = findViewById(R.id.buttonGoBack)
        button.setOnClickListener{
            changeActivity()
        }

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = RecyclerAdapter(allTasks, onClickListener = {view, Task -> this.presenter.showDialog(Task)})
        recyclerView.adapter = adapter

        presenter.replenishAdapter()


    }

    fun showDialog(task:Task){
        dialog = Dialog(this)
        dialog?.setContentView(R.layout.dialog_custom)
        val okButton = dialog?.findViewById<Button>(R.id.button_ok)
        val imageButton = dialog?.findViewById<ImageButton>(R.id.imageFavorite)
        val subjectNameTextView = dialog?.findViewById<TextView>(R.id.taskActivity)
        subjectNameTextView?.text = task.activity
        okButton?.setOnClickListener {
            dialog?.dismiss()
        }
        if (task.favorite){
            imageButton?.setImageResource(R.drawable.star)
        }
        else if (!task.favorite){
            imageButton?.setImageResource(R.drawable.no_star)
        }
        imageButton?.setOnClickListener{
            if (task.favorite){
                imageButton.setImageResource(R.drawable.no_star)
                task.favorite = false
                presenter.updateFavorite(task)
            }
            else if (task.favorite == false){
                imageButton.setImageResource(R.drawable.star)
                task.favorite = true
                presenter.updateFavorite(task)
            }
        }

        dialog?.show()
    }

    fun changeActivity(){
        Intent(this, MainActivity::class.java).also{
            startActivity(it)
        }
    }
}