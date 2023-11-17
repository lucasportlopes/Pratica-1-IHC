package com.example.exec2prat1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextMessage: EditText = findViewById(R.id.strMessage)
        val buttonSend: Button = findViewById(R.id.buttonSend)

        buttonSend.setOnClickListener {
            val message = editTextMessage.text.toString()

            val intent = Intent(this, ActivitySendMessage::class.java)

            intent.putExtra(EXTRA_MESSAGE, message)

            startActivity(intent)
        }
    }
    companion object {
        const val EXTRA_MESSAGE = "com.example.exec2prat1.MESSAGE"
    }
}