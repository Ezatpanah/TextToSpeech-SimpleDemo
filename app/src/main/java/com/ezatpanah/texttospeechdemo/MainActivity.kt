package com.ezatpanah.texttospeechdemo

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ezatpanah.texttospeechdemo.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var tts: TextToSpeech? = null
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tts = TextToSpeech(this, this)


        binding.btnSpeak.setOnClickListener { view ->
            if (binding.etEnteredText.text.isEmpty()) {
                Toast.makeText(this, "Enter a Text to Speak", Toast.LENGTH_SHORT).show()
            } else {
                speakOut(binding.etEnteredText.text.toString())
            }

        }

    }

    override fun onInit(p0: Int) {

        if(p0 == TextToSpeech.SUCCESS){
            val result = tts?.setLanguage(Locale.ENGLISH)
            if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Toast.makeText(this, "the language specified is not supported!", Toast.LENGTH_SHORT).show()

            }
        }else{
            Toast.makeText(this, "Initialization Failed!", Toast.LENGTH_SHORT).show()

        }
    }

   private fun speakOut(text : String){
        tts?.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")
    }

    override fun onDestroy() {
        super.onDestroy()
        if(tts != null){
            tts?.stop()
            tts?.shutdown()
        }
    }
}