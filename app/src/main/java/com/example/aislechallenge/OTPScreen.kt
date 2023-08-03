package com.example.aislechallenge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class OTPScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otpscreen)

        val otp = findViewById<EditText>(R.id.otp)
        val number = intent.getStringExtra("number")

        if (number != null) {
            verifyOtp(number, otp.getText().toString())
        }
    }

    private fun verifyOtp(number: String, otp: String) {
        val queue = Volley.newRequestQueue(this)
        val url = "https://app.aisle.co/V1/users/verify_otp"
        var payload = JSONObject();
        payload.put("number", number);
        payload.put("otp", otp)
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST, url, payload,
            Response.Listener { response ->
                val intent = Intent(this, NotesScreen::class.java)
                intent.putExtra("token", response.getString("token"))
                startActivity(intent)
            },
            Response.ErrorListener { })

        queue.add(jsonObjectRequest);
    }
}