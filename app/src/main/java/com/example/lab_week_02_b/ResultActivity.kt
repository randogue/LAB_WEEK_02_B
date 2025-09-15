package com.example.lab_week_02_b

import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultActivity : AppCompatActivity() {
    companion object {
        private const val COLOR_KEY = "COLOR_KEY"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.background_screen)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if (intent != null) {
            val colorCode = intent.getStringExtra("COLOR_KEY") //get the extra passed through intent

            val bgScreenDW = findViewById<ConstraintLayout>(R.id.background_screen) //xml layouts are ConstraintLayout
            bgScreenDW.setBackgroundColor(Color.parseColor("#$colorCode")) //$ is used to insert str variable into "quotation/string"
            val resultMSG = findViewById<TextView>(R.id.color_code_result_message)
            resultMSG.text = getString(R.string.color_code_result_message,colorCode?.uppercase()) //%s in xml is for formatting
        }
    }

}