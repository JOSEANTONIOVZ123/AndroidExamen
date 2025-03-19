package com.example.examenrecuperacion

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetalleActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)

        val imageViewDetalle: ImageView = findViewById(R.id.imageViewDetalle)
        val textViewNombre: TextView = findViewById(R.id.textViewNombreDetalle)
        val textViewTelefono: TextView = findViewById(R.id.textViewTelefonoDetalle)
        val textViewPoblacion: TextView = findViewById(R.id.textViewPoblacionDetalle)
        val imageEu: ImageView = findViewById(R.id.imageViewEU)

        val contactoId = intent.getIntExtra("id_contacto", -1)
        if (contactoId != -1) {
            val contacto = PaisProvider.lista[contactoId]
            imageEu.setImageResource(contacto.europa)
            imageViewDetalle.setImageResource(contacto.bandera)
            textViewNombre.text = contacto.nombre
            textViewTelefono.text = contacto.capital
            textViewPoblacion.text = "Población: ${contacto.poblacion}"
        }
    }
}