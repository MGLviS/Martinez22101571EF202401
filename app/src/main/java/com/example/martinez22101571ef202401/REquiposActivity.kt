package com.example.martinez22101571ef202401

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.martinez22101571ef202401.model.TeamModel
import com.google.firebase.firestore.FirebaseFirestore

class REquiposActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_requipos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etTname: EditText = findViewById(R.id.etTname)
        val etTimage: EditText = findViewById(R.id.etTimage)
        val btnAddTeam: Button = findViewById(R.id.btnRegistrarTeam)

        btnAddTeam.setOnClickListener {
            val teamName = etTname.text.toString()
            val teamImage = etTimage.text.toString()

            // Guardar Firestore

            val db = FirebaseFirestore.getInstance()
            val teamModel = TeamModel(teamName, teamImage)

            db.collection("teams").add(teamModel)
                .addOnSuccessListener {
                    Toast.makeText(this, "Equipo registrado", Toast.LENGTH_SHORT).show()
                    etTname.text.clear()
                    etTimage.text.clear()
                }
                .addOnFailureListener { error ->
                    Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
                    // Error
                }

        }

    }
}