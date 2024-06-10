package com.example.libreriapro.ui

import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.libreria.R
import com.example.libreriapro.api.APILibrosService
import com.example.libreriapro.model.Genero
import com.example.libreriapro.model.Generos
import com.example.libreriapro.model.Libro
import com.example.libreriapro.repositories.LibroRepository
import com.example.libreriapro.repositories.RetrofitRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LibroDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_libro_detail)

        val libroId = intent.getIntExtra("libro_id", -1)
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val apiLibrosService = retrofit.create(APILibrosService::class.java)
        if (libroId == -1) {
            // Handle error
            return
        }

        LibroRepository.getLibro(libroId, success = { libro ->
            // Display the libro details
            val libroNombre: TextView = findViewById<TextView>(R.id.libro_nombre)
            val libroIsbn: TextView = findViewById<TextView>(R.id.libro_isbn)
            val libroImagen: ImageView = findViewById<ImageView>(R.id.libro_imagen)
            val libroSinopsis: TextView = findViewById<TextView>(R.id.libro_sinopsis)
            val libroCalificacion: TextView = findViewById<TextView>(R.id.libro_calificacion)
            val libroAutor: TextView = findViewById<TextView>(R.id.libro_autor)
            val libroEditorial: TextView = findViewById<TextView>(R.id.libro_editorial)
            val libroGeneros: TextView = findViewById<TextView>(R.id.libro_generos) // Asegúrate de tener este TextView en tu layout

            libro?.let {
                libroNombre.text = "Nombre: ${it.nombre}"
                libroAutor.text = "Autor: ${it.autor}"
                libroEditorial.text = "Editorial: ${it.editorial}"
                libroIsbn.text = "ISBN: ${it.isbn}"
                Glide.with(this)
                    .load(Uri.parse(it.imagen))
                    .into(libroImagen)
                libroSinopsis.text = "Sinopsis: ${it.sinopsis}"
                libroCalificacion.text = "Calificación: ${it.calificacion.toString()}"

                // Obtén todos los géneros de la API
                apiLibrosService.getGeneros().enqueue(object : Callback<List<Genero>> {
                    override fun onResponse(call: Call<List<Genero>>, response: Response<List<Genero>>) {
                        val generos = response.body()
                        if (generos != null) {
                            // Filtra los géneros que están asociados con el libro
                            val bookGenres = generos.filter { genero ->
                                libro.generos.contains(genero)
                            }
                            // Muestra los géneros del libro en tu UI
                            libroGeneros.text = "Géneros: ${bookGenres.joinToString { it.nombre }}"
                        }
                    }

                    override fun onFailure(call: Call<List<Genero>>, t: Throwable) {
                        // Handle error
                    }
                })
            }
        }, failure = { error ->
            // Handle error
        })
    }
}