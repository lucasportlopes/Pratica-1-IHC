package com.example.exec2prat1

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ActivitySendMessage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_message)

        val textViewMessage: TextView = findViewById(R.id.strSendMessage)

        val message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE)

        textViewMessage.text = message
    }
}
