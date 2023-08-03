package com.example.aislechallenge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class NotesScreen : AppCompatActivity() {

    var responseText: TextView = findViewById<TextView>(R.id.NotesResponse);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_screen)

        val token = intent.getStringExtra("token")
        if (token != null) {
            getProfileList(token)
        }
    }

    private fun getProfileList(token: String) {
        val queue = Volley.newRequestQueue(this)
        val url = "https://app.aisle.co/V1/users/test_profile_list"
        val jsonObjectRequest = object: JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                responseText.setText(response.toString())
            },
            Response.ErrorListener { }) {
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params["token"] = token

                return params
            }
        }

        queue.add(jsonObjectRequest);


    }
}