package com.example.libreriapro.repositories

import android.util.Log
import com.example.libreriapro.api.APILibrosService
import com.example.libreriapro.model.Libro
import com.example.libreriapro.model.Libros
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object LibroRepository {

    private val retrofit = RetrofitRepository.getRetrofitInstance()
    private val service: APILibrosService = retrofit.create(APILibrosService::class.java)

    fun getLibroList(success: (Libros) -> Unit, failure: (Throwable) -> Unit) {
        Log.d("LibroRepository", "getLibroList called")
        service.getLibros().enqueue(object : Callback<Libros> {
            override fun onResponse(call: Call<Libros>, response: Response<Libros>) {
                Log.d("LibroRepository", "getLibroList onResponse called, response: ${response.body()}")
                response.body()?.let { success(it) } ?: failure(Throwable("Response body is null"))
            }

            override fun onFailure(call: Call<Libros>, t: Throwable) {
                Log.e("LibroRepository", "getLibroList onFailure called, error: ${t.message}", t)
                failure(t)
            }
        })
    }

    fun insertLibro(libro: Libro, success: (Libro) -> Unit, failure: (Throwable) -> Unit) {
        Log.d("LibroRepository", "insertLibro called with libro: $libro")
        service.insertLibro(libro).enqueue(object : Callback<Libro> {
            override fun onResponse(call: Call<Libro>, response: Response<Libro>) {
                Log.d("LibroRepository", "insertLibro onResponse called, response: ${response.body()}")
                response.body()?.let { success(it) } ?: failure(Throwable("Response body is null"))
            }

            override fun onFailure(call: Call<Libro>, t: Throwable) {
                Log.e("LibroRepository", "insertLibro onFailure called", t)
                failure(t)
            }
        })
    }

    fun getLibro(id: Int, success: (Libro?) -> Unit, failure: (Throwable) -> Unit) {
        Log.d("LibroRepository", "getLibro called with id: $id")
        service.getLibroById(id).enqueue(object : Callback<Libro?> {
            override fun onResponse(call: Call<Libro?>, response: Response<Libro?>) {
                Log.d("LibroRepository", "getLibro onResponse called, response: ${response.body()}")
                if (response.isSuccessful) {
                    success(response.body())
                } else {
                    failure(Throwable("Response was not successful"))
                }
            }

            override fun onFailure(call: Call<Libro?>, t: Throwable) {
                Log.e("LibroRepository", "getLibro onFailure called", t)
                failure(t)
            }
        })
    }

    fun updateLibro(libro: Libro, success: (Libro) -> Unit, failure: (Throwable) -> Unit) {
        val libroId = libro.id ?: 0
        Log.d("LibroRepository", "updateLibro called with libro: $libro and id: $libroId")
        service.updateLibro(libro, libroId).enqueue(object : Callback<Libro> {
            override fun onResponse(call: Call<Libro>, response: Response<Libro>) {
                Log.d("LibroRepository", "updateLibro onResponse called, response: ${response.body()}")
                response.body()?.let { success(it) } ?: failure(Throwable("Response body is null"))
            }

            override fun onFailure(call: Call<Libro>, t: Throwable) {
                Log.e("LibroRepository", "updateLibro onFailure called", t)
                failure(t)
            }
        })
    }

    fun deleteLibro(id: Int, success: () -> Unit, failure: (Throwable) -> Unit) {
        Log.d("LibroRepository", "deleteLibro called with id: $id")
        service.deleteLibro(id).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Log.d("LibroRepository", "deleteLibro onResponse called")
                if (response.isSuccessful) {
                    Log.d("LibroRepository", "deleteLibro successful")
                    success()
                } else {
                    Log.e("LibroRepository", "deleteLibro failed: ${response.code()}")
                    failure(Throwable("Failed to delete libro: ${response.code()}"))
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("LibroRepository", "deleteLibro onFailure called", t)
                failure(t)
            }
        })
    }



}