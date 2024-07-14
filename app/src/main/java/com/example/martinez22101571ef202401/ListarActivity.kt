package com.example.martinez22101571ef202401

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ListarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_listar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnRegistrarEq: Button = findViewById(R.id.btnRegistrarEq)
        val btnRegistrarEnfre: Button = findViewById(R.id.btnRegistrarEnfre)

        btnRegistrarEq.setOnClickListener {
            startActivity(Intent(this, REquiposActivity::class.java))
        }

        btnRegistrarEnfre.setOnClickListener {
            startActivity(Intent(this, MatchActivity::class.java))
        }

    }
}