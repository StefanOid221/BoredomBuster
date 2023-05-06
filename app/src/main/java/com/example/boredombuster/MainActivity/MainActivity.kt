package com.example.boredombuster.MainActivity

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.boredombuster.Model.Database.Task
import com.example.boredombuster.Model.Model
import com.example.boredombuster.R
import com.example.boredombuster.SecondActivity.RecyclerActivityView

class MainActivity : AppCompatActivity() {

    private lateinit var presenter: MainActivityPresenter
    private var dialog: Dialog? = null
    private lateinit var button: Button
    private lateinit var buttonChange: Button
    val tasks = arrayOf("random","education", "recreational", "social", "diy", "charity", "cooking", "relaxation", "music", "busywork")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "Boredom Buster"

        presenter = MainActivityPresenter(Model(applicationContext), this)

        button = findViewById(R.id.buttonDialogActivity)


        val spinner = findViewById<Spinner>(R.id.searchSpinner)
        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, tasks)
        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Toast.makeText(applicationContext, "selected activity "+ tasks[position], Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        button.setOnClickListener {
            var selected: String = spinner.selectedItem.toString()
            if (selected == tasks[0])
                presenter.getTask(false,null)
            else presenter.getTypeTask(selected)
        }
        button = findViewById(R.id.buttonFavoritesActivity)
        button.setOnClickListener{
            changeActivity()
        }
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

    fun changeActivity(){
        Intent(this, RecyclerActivityView::class.java).also{
            startActivity(it)
        }
    }
}