package com.example.aislechallenge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val countryCode = findViewById<EditText>(R.id.countryCode)
        val mobileNumber = findViewById<EditText>(R.id.mobileNumber)
        val btn = findViewById<Button>(R.id.btnContinue)

        btn.setOnClickListener(View.OnClickListener {
            loginPhoneNumber(countryCode.getText().toString(), mobileNumber.getText().toString())
        })
    }

    private fun loginPhoneNumber(countryCode: String, mobileNumber: String) {
        val queue = Volley.newRequestQueue(this)
        val url = "https://app.aisle.co/V1/users/phone_number_login"
        var payload = JSONObject();
        payload.put("number", countryCode + mobileNumber);
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST, url, payload,
            Response.Listener { response ->
                val intent = Intent(this, OTPScreen::class.java)
                intent.putExtra("number", countryCode + mobileNumber)
                startActivity(intent)
            },
            Response.ErrorListener { })

        queue.add(jsonObjectRequest);
    }
}