package com.example.martinez22101571ef202401

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.firestore.FirebaseFirestore

class MatchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_match)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val spLocal: Spinner = findViewById(R.id.spLocal)
        val spVisita: Spinner = findViewById(R.id.spVisita)
        val etClocal: EditText = findViewById(R.id.etClocal)
        val etCvisita: EditText = findViewById(R.id.etCvisita)
        val etCempate: EditText = findViewById(R.id.etCempate)
        val btnCuotas: Button = findViewById(R.id.btnCuotas)
        val btnRegistrarE: Button = findViewById(R.id.btnRegistrarE)
        val db = FirebaseFirestore.getInstance()
        val teamsCollection = db.collection("teams")
        val teamNames = ArrayList<String>()

        teamsCollection.get().addOnSuccessListener { documents ->
            for (document in documents) {
                document.getString("tname")?.let { teamNames.add(it) }
            }

            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, teamNames)

            spLocal.adapter = adapter
            spVisita.adapter = adapter
        }.addOnFailureListener { exception ->

            Log.w(TAG, "Error getting documents: ", exception)
        }

        btnCuotas.setOnClickListener {
            val local = spLocal.selectedItem.toString()
            val visita = spVisita.selectedItem.toString()
            val clocal = etClocal.text.toString().toFloat()
            val cvisita = etCvisita.text.toString().toFloat()
            val cempate = etCempate.text.toString().toFloat()

            val match = hashMapOf(
                "tlocal" to local,
                "tvisita" to visita,
                "clocal" to clocal,
                "cvisita" to cvisita,
                "cempate" to cempate
            )

            db.collection("cuotas")
                .add(match)
                .addOnSuccessListener { documentReference ->
                    Toast.makeText(this, "Cuotas añadidas", Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "Documento ADD: ${documentReference.id}")
                    //Limpiar campos
                    etClocal.text.clear()
                    etCvisita.text.clear()
                    etCempate.text.clear()

                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                    Toast.makeText(this, "Error al añadir cuotas", Toast.LENGTH_SHORT).show()
                }
        }
        btnRegistrarE.setOnClickListener {
            startActivity(Intent(this, REquiposActivity::class.java))
        }


    }
}