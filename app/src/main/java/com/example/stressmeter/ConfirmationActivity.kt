package com.example.stressmeter

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*


class ConfirmationActivity : AppCompatActivity() {
    private lateinit var imgView: ImageView
    private lateinit var confirmBtn: Button
    private lateinit var cancelBtn: Button

    private var value = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)

        imgView = findViewById(R.id.confirmImage)
        confirmBtn = findViewById(R.id.confirmationConfirm)
        cancelBtn = findViewById(R.id.confirmationCancel)

        val intent = intent

        if(intent.extras != null){
            value = intent.getIntExtra("imageValue", 0)
            val image = intent.getIntExtra("imagePath", 0)
            imgView.scaleType = ImageView.ScaleType.FIT_CENTER
            imgView.setImageResource(image)
        }

        cancelBtn.setOnClickListener{
            this.finish()
        }

        confirmBtn.setOnClickListener{
            saveRecord()
            Toast.makeText(this, "Entry Recorded", Toast.LENGTH_LONG).show()
            this.finishAffinity()
        }
    }

    fun saveRecord(){
        if(value != -1){
            try {
                val dateFormat = SimpleDateFormat("dd/M/yyyy HH:mm")
                val currDate = dateFormat.format(Date())
                val message = currDate + "," + value + "\n"
                val file = File(Environment.getExternalStorageDirectory(), "stressMeter.csv")
                file.createNewFile()
                FileOutputStream(
                    file,
                    true
                ).write(message.toByteArray())
            }
            catch (e: java.lang.Exception){
                e.printStackTrace()
            }
        }
    }
}