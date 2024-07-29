package com.example.boccati

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.MediaController
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main);

        val btnIntro = findViewById<Button>(R.id.Btn_intro)
        btnIntro.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)

        }

    }
}