package com.example.boredombuster.Model

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.boredombuster.Model.Database.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject

private const val url_NormalTask = "https://www.boredapi.com/api/activity/"
private const val url_TypeTask ="https://www.boredapi.com/api/activity?type="

class Network private constructor (context: Context) {

    companion object: SingletonHolder<Network, Context>(::Network)

    private val queue = Volley.newRequestQueue(context)
    var subjectsCount = 20

    fun getNormalTask(listener: Response.Listener<Task>, errorListener: Response.ErrorListener) = GlobalScope.launch(
        Dispatchers.Main) {
        val url = url_NormalTask
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response -> processNormalTask(response, listener) },
            { error -> errorListener.onErrorResponse(error) }
        )
        queue.add(jsonObjectRequest)
    }

    private fun processNormalTask(json: JSONObject, listener: Response.Listener<Task>) {
        val data: Task
        try {
            val key = json.getString("key")
            val activity = json.getString("activity")
            val type = json.getString("type")
            val participants = json.getInt("participants")
            val price = json.getInt("price")
            val link = json.getString("link")
            val accessibility = json.getInt("accessibility")
            data = Task(key,activity,type,participants,price,link,accessibility,false)
            listener.onResponse(data)
        } catch (e: JSONException) {
            Log.e("net", "Error extracting rowCount", e)
            listener.onResponse(null)
        }
    }
    fun getTypeTask(type: String,listener: Response.Listener<Task>, errorListener: Response.ErrorListener) = GlobalScope.launch(
        Dispatchers.Main) {
        val url = url_TypeTask + type
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response -> processTypeTask(response, listener) },
            { error -> errorListener.onErrorResponse(error) }
        )
        queue.add(jsonObjectRequest)
    }

    private fun processTypeTask(json: JSONObject, listener: Response.Listener<Task>) {
        val data: Task
        try {
            val key = json.getString("key")
            val activity = json.getString("activity")
            val type = json.getString("type")
            val participants = json.getInt("participants")
            val price = json.getInt("price")
            val link = json.getString("link")
            val accessibility = json.getInt("accessibility")
            data = Task(key,activity,type,participants,price,link,accessibility,false)
            listener.onResponse(data)
        } catch (e: JSONException) {
            Log.e("net", "Error extracting rowCount", e)
            listener.onResponse(null)
        }
    }
}