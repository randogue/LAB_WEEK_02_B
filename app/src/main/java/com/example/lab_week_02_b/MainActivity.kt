package com.example.lab_week_02_b

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    companion object {
        private const val COLOR_KEY = "COLOR_KEY"
    }

    //added for ActivityResultLauncher
    private lateinit var launcher: ActivityResultLauncher<Intent> //in kotlin, you specify data type from colon ':'

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


        //added for ActivityResultLauncher, make a class to handle the result passed from second Act
        val resultCallback = object: ActivityResultCallback<ActivityResult> { //ActivityResultCallback<ActivityResult> is an interface
            override fun onActivityResult(result: ActivityResult) { //method override
                val data: Intent? = result.data //data is nullable, pull ActivityResult.data
                if (data != null) { //to ensure that data is not null while executing
                    val error = data?.getBooleanExtra("ERROR_KEY", false) //get data with name:"ERROR_KEY", in case data is not found, use default value (from the second param)

                    if (error == true) { //if boolean is true
                        //show pop up window warning
                        Toast.makeText(this@MainActivity,
                            getString(R.string.color_code_input_invalid),
                            Toast.LENGTH_LONG
                            ).show()
                    }
                }
            }
        }


        //Main content of activity
        submitButton.setOnClickListener {
            val colorCode =
                findViewById<TextInputEditText>(R.id.color_code_input_field).text.toString()
            if (colorCode.isNotEmpty()) { //when field is not empty
                if (colorCode.length != 6) { //when length is too long
                    if (colorCode.length > 6) {
                        Toast.makeText(
                            this@MainActivity,
                            getString(R.string.color_code_input_wrong_length),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    else {
                        Toast.makeText(
                            this@MainActivity,
                            getString(R.string.color_code_input_wrong_length),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
                else if (colorCode.length == 6) { //when length is just right
                    val ResultIntent = Intent(
                        this@MainActivity,
                        ResultActivity::class.java) //create new intent(context:this activity, class_of_activity)
                    ResultIntent.putExtra(
                        "COLOR_KEY",
                        colorCode) //pass a value with certain name
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