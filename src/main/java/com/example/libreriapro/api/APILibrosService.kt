package com.example.libreriapro.api

import com.example.libreriapro.model.Genero
import com.example.libreriapro.model.Libro
import com.example.libreriapro.model.LibroGenero
import com.example.libreriapro.model.Libros
import com.example.libreriapro.model.Generos
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface APILibrosService {
    @GET("libros")
    fun getLibros(): Call<Libros>

    @GET("libros/{id}")
    fun getLibroById(
        @Path("id") id: Int
    ): Call<Libro?>

    @POST("libros")
    fun insertLibro(
        @Body libro: Libro
    ): Call<Libro>

    @PUT("libros/{id}")
    fun updateLibro(
        @Body libro: Libro,
        @Path("id") id: Int
    ): Call<Libro>

    @HTTP(method = "DELETE", path = "libros/{id}", hasBody = false)
    fun deleteLibro(
        @Path("id") id: Int
    ): Call<Void>

    @GET("generos")
    fun getGeneros(): Call<List<Genero>>

    @GET("generos/{id}")
    fun getGeneroById(
        @Path("id") id: Int
    ): Call<Genero?>

    @POST("generos")
    fun insertGenero(
        @Body genero: Genero
    ): Call<Genero>

    @PUT("generos/{id}")
    fun updateGenero(
        @Body genero: Genero,
        @Path("id") id: Int
    ): Call<Genero>

    @DELETE("generos/{id}")
    fun deleteGenero(
        @Path("id") id: Int
    ): Call<Void>

    @POST("libro-generos")
    fun addGeneroToLibro(@Body libroGenero: LibroGenero): Call<LibroGenero>

    @HTTP(method = "DELETE", path = "libro-generos", hasBody = true)
    fun removeGeneroFromLibro(@Body libroGenero: LibroGenero): Call<Void>
}