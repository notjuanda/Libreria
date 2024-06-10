package com.example.libreriapro.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.libreria.R
import com.example.libreria.databinding.ActivityMainBinding
import com.example.libreriapro.model.Libro
import com.example.libreriapro.ui.adapters.LibroAdapter
import com.example.libreriapro.ui.viewmodels.MainViewModel

class MainActivity : AppCompatActivity(), LibroAdapter.OnLibroClickListener {
    private lateinit var binding: ActivityMainBinding
    private val model: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddLibro.setOnClickListener {
            val intent = Intent(this, AddLibroActivity::class.java)
            startActivity(intent)
        }

        setupRecyclerView()
        setupViewModelListeners()
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "onResume called")
        model.fetchListaLibros()
    }

    private fun setupViewModelListeners() {
        model.libroList.observe(this) {
            Log.d("MainActivity", "Lista de libros actualizada: $it")
            val adapter = (binding.recyclerView.adapter as LibroAdapter)
            adapter.updateData(it)
        }
    }

    private fun setupRecyclerView() {
        Log.d("MainActivity", "setupRecyclerView called")
        binding.recyclerView.apply {
            this.adapter = LibroAdapter(mutableListOf(), this@MainActivity)
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    override fun onLibroClick(libro: Libro) {
        Log.d("MainActivity", "onLibroClick called with libro: $libro")
        // Implementa la lógica aquí
    }

    override fun onLibroDelete(libro: Libro) {
        Log.d("MainActivity", "onLibroDelete called with libro: $libro")
        libro.id?.let { id ->
            model.deleteLibro(id)
        } ?: run {
            Log.e("MainActivity", "Cannot delete libro: $libro because it has no id")
        }
    }

    override fun onLibroEdit(libro: Libro) {
        Log.d("MainActivity", "onLibroEdit called with libro: $libro")
        val intent = Intent(this, EditLibroActivity::class.java)
        intent.putExtra("libro", libro)
        startActivity(intent)
    }
}