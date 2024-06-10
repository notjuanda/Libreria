package com.example.libreriapro.ui.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.libreria.R
import com.example.libreriapro.model.Libro
import com.example.libreriapro.repositories.LibroRepository

class AddLibroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_libro)

        val etNombre: EditText = findViewById(R.id.et_nombre)
        val etAutor: EditText = findViewById(R.id.et_autor)
        val etEditorial: EditText = findViewById(R.id.et_editorial)
        val etIsbn: EditText = findViewById(R.id.et_isbn)
        val etImagen: EditText = findViewById(R.id.et_imagen)
        val etSinopsis: EditText = findViewById(R.id.et_sinopsis)
        val etCalificacion: EditText = findViewById(R.id.et_calificacion)
        val btnGuardar: Button = findViewById(R.id.btn_guardar)

        btnGuardar.setOnClickListener {
            val libro = Libro(
                nombre = etNombre.text.toString(),
                autor = etAutor.text.toString(),
                editorial = etEditorial.text.toString(),
                isbn = etIsbn.text.toString(),
                imagen = etImagen.text.toString(),
                sinopsis = etSinopsis.text.toString(),
                calificacion = etCalificacion.text.toString().toFloat() // Cambiado a toFloat()
            )
            LibroRepository.insertLibro(libro, {

            }, {

            })
            finish()
        }
    }
}