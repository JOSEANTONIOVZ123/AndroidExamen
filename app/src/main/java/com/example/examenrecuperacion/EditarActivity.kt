package com.example.examenrecuperacion

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class EditarActivity : AppCompatActivity() {

    private lateinit var imageViewEditar: ImageView
    private lateinit var editTextNombre: EditText
    private lateinit var editTextTelefono: EditText

    private lateinit var buttonGuardar: Button
    private var contactoId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar)

        imageViewEditar = findViewById(R.id.imageViewEditar)
        editTextNombre = findViewById(R.id.editTextNombre)
        editTextTelefono = findViewById(R.id.editTextTelefono)
        buttonGuardar = findViewById(R.id.buttonGuardar)

        contactoId = intent.getIntExtra("id_contacto", -1)
        if (contactoId != -1) {
            val contacto = PaisProvider.lista[contactoId]
            imageViewEditar.setImageResource(contacto.bandera)
            editTextNombre.setText(contacto.nombre)
            editTextTelefono.setText(contacto.capital)

        }

        buttonGuardar.setOnClickListener {
            val nuevoNombre = editTextNombre.text.toString().trim()
            val nuevaCapital = editTextTelefono.text.toString().trim()

            if (contactoId != -1 && nuevoNombre.isNotEmpty() && nuevaCapital.isNotEmpty()) {
                PaisProvider.lista[contactoId].nombre = nuevoNombre
                PaisProvider.lista[contactoId].capital = nuevaCapital


                val resultIntent = Intent()
                resultIntent.putExtra("id_contacto", contactoId)
                resultIntent.putExtra("nuevo_nombre", nuevoNombre)
                resultIntent.putExtra("nuevo_telefono", nuevaCapital)
                setResult(Activity.RESULT_OK, resultIntent)
                finish() // Volvemos a MainActivity
            }
        }
    }
}
