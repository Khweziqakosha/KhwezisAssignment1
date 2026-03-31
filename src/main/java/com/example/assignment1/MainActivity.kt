package com.example.assignment1

import android.annotation.SuppressLint
import android.os.Bundle
import android.telecom.Call
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

private fun String.lowerCase() {
    TODO("Not yet implemented")
}

class MainActivity : AppCompatActivity() {
    private lateinit var editTextTimeOfDay: EditText
    private lateinit var buttonGetSuggestion: Button
    private lateinit var textViewSuggestion: TextView
    private lateinit var buttonReset: Button

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Initialize views
        editTextTimeOfDay = findViewById(R.id.editTextTimeOfDay)
        buttonGetSuggestion = findViewById(R.id.buttonGetSUggestion)
        textViewSuggestion = findViewById(R.id.textViewSuggestion)
        buttonReset = findViewById(R.id.buttonReset)

// Set up onClick listener for Get Suggestion button
        buttonGetSuggestion.setOnClickListener { it: View? ->
            val timeOfDay: android.R.string = editTextTimeOfDay.text.toString().trim().lowerCase()

// Get suggestion based on input time of day
            val suggestion = getsocialsparksuggestion(timeOfDay)

            if (suggestion.isNotEmpty) {
                textViewSuggestion.text = suggestion
            } else {
                Toast.makeText(
                    this,
                    "please enter a valid time of day(morning,afternoon),Toast.LENGTH_SHORT."
                ).show()

            }
        }
        //set up onclick listener for Reset button
        buttonReset.setOnClickListener {
            editTextTimeOfDay.text.clear()
            textViewSuggestion.text = "suggestion will appear here"
        }
    }

}
//function to provide a suggestion based on the input time of day
@Suppress("KotlinConstantConditions")
private fun  getsocialsparksuggestion(timeofday: android.R.string): android.R.string {
    return when (timeofday)


    "morning"-> "send a 'Good morning'text to a family member."
    "mid-morning"->"Reach out to a college with a quick 'Thank you.'"
    "afternoon" -> "share a funny meme or interesting link with a friend."
    "afternoon snack time"-> "send a quick'thinking of you'message. "
    "dinner"->"call a friend or relative for a 5 minute catch up."
    "after dinner", "night"->"Leave a thoughtful comment on a friends post."
    else -> "" //return empty if invalid input
}








