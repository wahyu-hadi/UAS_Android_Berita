package com.example.breakingnews
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.breakingnews.model.Berita

class BeritaAdapter(
    private val beritas: List<Berita>,
    private val onBeritaClick: (Berita) -> Unit // Function parameter to handle click
) : RecyclerView.Adapter<BeritaAdapter.BeritaViewHolder>() {

    class BeritaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val beritaImage: ImageView = itemView.findViewById(R.id.BeritaImage)
        val beritaTitle: TextView = itemView.findViewById(R.id.titleBerita)
        val beritaDescription: TextView = itemView.findViewById(R.id.beritaDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeritaViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_berita, parent, false)
        return BeritaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BeritaViewHolder, position: Int) {
        val berita = beritas[position]
        holder.beritaTitle.text = berita.title
        holder.beritaDescription.text = berita.description

        // Menggunakan Glide untuk memuat gambar
        Glide.with(holder.itemView.context)
            .load(berita.thumbnail)
            .into(holder.beritaImage)

        // Mengatur klik pada gambar berita
        holder.itemView.setOnClickListener {
            onBeritaClick(berita) // Panggil fungsi onBeritaClick
        }
    }

    override fun getItemCount(): Int {
        return beritas.size
        }
}