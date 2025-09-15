package com.example.lab_week_02_b

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    companion object {
        private const val COLOR_KEY = "COLOR_KEY"
    }

    private val submitButton: Button
        get() = findViewById<Button>(R.id.submit_button) //findViewById(R.id.submit_button) will work just fine since the compiler can easily tell if it's a button type


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        submitButton.setOnClickListener {
            val colorCode =
                findViewById<TextInputEditText>(R.id.color_code_input_field).text.toString()
            if (colorCode.isNotEmpty()) { //when field is not empty
                if (colorCode.length > 6) { //when length is too long
                    Toast.makeText(
                        this,
                        getString(R.string.color_code_input_wrong_length),
                        Toast.LENGTH_LONG
                    ).show()
                }
                else { //when length is not too long
                    val ResultIntent = Intent(this, ResultActivity::class.java) //create new intent(context:this activity, class_of_activity)
                    ResultIntent.putExtra("COLOR_KEY",colorCode) //pass a value with certain name
                    startActivity(ResultIntent)
                }
            }
            else {//when field is empty
                Toast.makeText(
                    this,
                    getString(R.string.color_code_input_empty),
                    Toast.LENGTH_LONG
                    ).show()
            }
        }
    }

}