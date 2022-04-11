package com.samuel.ttsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var tts : TextToSpeech? = null // ? -> For make it nullable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tts = TextToSpeech(this, this)

        findViewById<Button>(R.id.btn_play).setOnClickListener{
            speak()
        }
    }

    //Implementing methods
    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS){
            findViewById<TextView>(R.id.tts_status).text = getString(R.string.ready_message)
            tts!!.language = Locale.getDefault() //Getting default lenguage
        }
        else
        {
            findViewById<TextView>(R.id.tts_status).text = getString(R.string.service_not_aviable_message)
        }
    }

    private fun speak(){
        val message : String = findViewById<EditText>(R.id.oration2reproduce).text.toString()
        if(message.isEmpty()){
            findViewById<TextView>(R.id.tts_status).text = getString(R.string.empty_edit_text_warning)     //Validate if our edit text has something
        }
        tts!!.speak(message, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    //Better practices (Lifecycle)

    override fun onDestroy() {
        if (tts!! != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }



}