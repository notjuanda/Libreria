package com.example.libreriapro.model

import java.io.Serializable

typealias Libros = ArrayList<Libro>

data class Libro(
    var nombre: String,
    var autor: String,
    var editorial: String,
    var isbn: String,
    var imagen: String,
    var sinopsis: String,
    var calificacion: Float
) : Serializable {
    var generos: ArrayList<Genero> = ArrayList()
    var id: Int? = null
}