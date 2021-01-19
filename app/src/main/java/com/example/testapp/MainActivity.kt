package com.example.testapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var loginButton = findViewById<Button>(R.id.btn_login)
        var nameInput = findViewById<TextInputEditText>(R.id.nameInput)

        loginButton.setOnClickListener{
            if(nameInput.text.toString().isEmpty()){
                Toast.makeText(this, "Name cannot be empty!", Toast.LENGTH_SHORT).show()
            }else{
                val intent = Intent(this, TestQuestionsActivity::class.java)
                startActivity(intent)
                finish()
            }

        }
    }
}