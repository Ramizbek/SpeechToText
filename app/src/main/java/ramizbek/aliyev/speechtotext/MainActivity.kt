package ramizbek.aliyev.speechtotext

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {
    private val RQ_SPEECH_REC = 102
    private lateinit var btn_button: Button
    private lateinit var tv_text: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_button = findViewById(R.id.btn_button)
        tv_text = findViewById(R.id.tv_text)

        btn_button.setOnClickListener {
            askSpeechInput()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ( requestCode == RQ_SPEECH_REC &&  resultCode == Activity.RESULT_OK){
            val result = data?.getStringArrayExtra(RecognizerIntent.EXTRA_RESULTS)
            tv_text.text = result?.get(0).toString()
        }
    }

    private fun askSpeechInput() {
        if (!SpeechRecognizer.isRecognitionAvailable(this)){
            Toast.makeText(this, "Speech recognition is not available", Toast.LENGTH_SHORT).show()
        }else{
            val i = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say Something!")
            startActivityForResult(i, RQ_SPEECH_REC)
        }
    }
}