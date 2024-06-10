package com.example.libreriapro.ui.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.libreria.databinding.ItemLibroBinding
import com.example.libreriapro.model.Libro
import com.example.libreriapro.ui.LibroDetailActivity

class LibroAdapter(val libroList: MutableList<Libro>, val listener: OnLibroClickListener) :
    RecyclerView.Adapter<LibroAdapter.LibroViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibroViewHolder {
        Log.d("LibroAdapter", "onCreateViewHolder called")
        val binding =
            ItemLibroBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return LibroViewHolder(binding)
    }

    override fun getItemCount(): Int {
        Log.d("LibroAdapter", "getItemCount called, size: ${libroList.size}")
        return libroList.size
    }

    override fun onBindViewHolder(holder: LibroViewHolder, position: Int) {
        Log.d("LibroAdapter", "onBindViewHolder called, position: $position")
        val libro = libroList[position]
        holder.bind(libro, listener)
    }

    fun updateData(libroList: MutableList<Libro>) {
        Log.d("LibroAdapter", "updateData called, new data size: ${libroList.size}")
        this.libroList.clear()
        this.libroList.addAll(libroList)
        notifyDataSetChanged()
    }

    class LibroViewHolder(private val binding: ItemLibroBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(libro: Libro, listener: OnLibroClickListener) {
            Log.d("LibroAdapter", "bind called, libro id: ${libro.id}, libro: $libro")
            Log.d("LibroAdapter", "bind called, libro: $libro")
            binding.apply {
                Glide.with(ivLibroImagen.context)
                    .load(libro.imagen)
                    .into(ivLibroImagen)

                tvLibroNombre.text = libro.nombre

                btnEditarLibro.setOnClickListener {
                    Log.d("LibroAdapter", "Edit button clicked, libro: $libro")
                    listener.onLibroEdit(libro)
                }

                btnDeleteLibro.setOnClickListener {
                    Log.d("LibroAdapter", "Delete button clicked, libro: $libro")
                    listener.onLibroDelete(libro)
                }

                root.setOnClickListener {
                    Log.d("LibroAdapter", "Root view clicked, libro: $libro")
                    val intent = Intent(it.context, LibroDetailActivity::class.java).apply {
                        putExtra("libro_id", libro.id)
                    }
                    it.context.startActivity(intent)
                }
            }
        }
    }

    interface OnLibroClickListener {
        fun onLibroClick(libro: Libro)
        fun onLibroDelete(libro: Libro)
        fun onLibroEdit(libro: Libro)
    }
}