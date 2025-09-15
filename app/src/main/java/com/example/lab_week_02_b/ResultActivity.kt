package com.example.lab_week_02_b

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultActivity : AppCompatActivity() {
    companion object { //was not used in order to make future me undestand how some function works
        private const val COLOR_KEY = "COLOR_KEY"
        private const val ERROR_KEY = "ERROR_KEY"
    } //basically these are like aliases (in this case as a variable that contains string as key) so that any changes will reflect to all keys.


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.background_screen)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        if (intent != null) { //when accessed via intent
            val colorCode = intent.getStringExtra("COLOR_KEY") //get the extra passed through intent

            val bgScreenDW = findViewById<ConstraintLayout>(R.id.background_screen) //xml layouts are ConstraintLayout
            try {
                bgScreenDW.setBackgroundColor(
                    Color.parseColor("#$colorCode")
                ) //$ is used to insert str variable into "quotation/string"
            }
            catch(exception : IllegalArgumentException) {
                val resultIntent = Intent() //create an Intent Object
                resultIntent.putExtra(
                    "ERROR_KEY",
                    true
                ) //put value to "ERROR_KEY"
                setResult(
                    Activity.RESULT_OK,
                    resultIntent
                ) //send intent object with the status (-1 means success or RESULT_OK)
                finish() //closes current activity, then sends it to the back stack or destroy it.
                //basically finish() close this activity and go back to the previous existing Activity (in this case its Main Activity).
            }
            val resultMSG = findViewById<TextView>(
                R.id.color_code_result_message
            )
            resultMSG.text = getString(
                R.string.color_code_result_message,
                colorCode?.uppercase()
            ) //%s in xml is for formatting
        }
    }

}