package com.example.boredombuster.MainActivity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.boredombuster.R

class DialogTask : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_custom, container, false)
        var button_ok = view.findViewById<Button>(R.id.button_ok)
        var button_update = view.findViewById<Button>(R.id.imageFavorite)
        var activity = view.findViewById<TextView>(R.id.taskActivity)


        button_ok.setOnClickListener {
            dismiss()
        }
        button_update.setOnClickListener {
            dismiss()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Add click listeners for the buttons here
    }
}