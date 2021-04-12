package com.example.mynotificationapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity__second.*
import java.util.prefs.BackingStoreException


class Activity_Second : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__second)

        var recievedText: Bundle? = getIntent().getExtras()
        Log.d("Recieved", "$recievedText")
        if(recievedText !== null){
            val message = recievedText.getString("MESSAGE")
            tv_text.text = message
        }

        btnBack.setOnClickListener {
            finish()
        }

    }
}