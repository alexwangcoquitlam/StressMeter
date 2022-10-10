package com.example.stressmeter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class ConfirmationActivity : AppCompatActivity() {
    private lateinit var imgView: ImageView
    private lateinit var confirmBtn: Button
    private lateinit var cancelBtn: Button

    private var value = 0

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

            imgView.setImageResource(image)
        }

        cancelBtn.setOnClickListener{
            this.finish()
        }

        confirmBtn.setOnClickListener{
            saveRecord()
            this.finishAffinity()
        }
    }

    fun saveRecord(){
        // TODO: Figure out how to implement this
    }
}