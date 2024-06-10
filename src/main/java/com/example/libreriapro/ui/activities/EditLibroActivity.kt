package com.example.libreriapro.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.libreria.R
import com.example.libreria.databinding.ActivityEditLibroBinding
import com.example.libreriapro.model.Libro
import com.example.libreriapro.repositories.LibroRepository

class EditLibroActivity : AppCompatActivity() {
    private lateinit var libro: Libro
    private lateinit var binding: ActivityEditLibroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditLibroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        libro = intent.getSerializableExtra("libro") as Libro

        fillFields()

        binding.btnGuardar.setOnClickListener {
            saveChanges()
            finish() // Cierra esta actividad y vuelve a MainActivity
        }
    }

    private fun fillFields() {
        binding.editNombre.setText(libro.nombre)
        binding.editAutor.setText(libro.autor)
        binding.editEditorial.setText(libro.editorial)
        binding.editISBN.setText(libro.isbn)
        binding.editSinopsis.setText(libro.sinopsis)
        binding.editCalificacion.setText(libro.calificacion.toString())
    }

    private fun saveChanges() {
        libro.nombre = binding.editNombre.text.toString()
        libro.autor = binding.editAutor.text.toString()
        libro.editorial = binding.editEditorial.text.toString()
        libro.isbn = binding.editISBN.text.toString()
        libro.sinopsis = binding.editSinopsis.text.toString()
        try {
            libro.calificacion = binding.editCalificacion.text.toString().toFloat()
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Por favor, introduce una calificación válida", Toast.LENGTH_SHORT).show()
            return
        }

        LibroRepository.updateLibro(
            libro,
            success = {
                Toast.makeText(this, "Cambios guardados", Toast.LENGTH_SHORT).show()
                finish() // Cierra esta actividad y vuelve a MainActivity
            },
            failure = { error ->
                Toast.makeText(this, "Error al guardar cambios: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        )
    }
}