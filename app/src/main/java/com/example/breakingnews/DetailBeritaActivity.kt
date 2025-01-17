package com.example.breakingnews

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide


class DetailBeritaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_berita)
        // Ambil data Berita dari Intent
        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")
        val imageUrl = intent.getStringExtra("imageUrl")
        val pubDate = intent.getStringExtra("pubDate")
        val LinkBerita = intent.getStringExtra("LinkBerita")
        // Periksa jika data kosong atau null
        if (title.isNullOrEmpty() || description.isNullOrEmpty() || imageUrl.isNullOrEmpty()) {
            Toast.makeText(this, "Data berita tidak lengkap", Toast.LENGTH_SHORT).show()
            finish()  // Menutup activity jika data tidak lengkap
            return
        }

        // Inisialisasi komponen layout
        val beritaTitle: TextView = findViewById(R.id.title)
        val beritapub: TextView = findViewById(R.id.pub)
        val beritaDescription: TextView = findViewById(R.id.desc)
        val beritaImage: ImageView = findViewById(R.id.imageber)
        val beritalink: TextView = findViewById(R.id.LinkBerita)
        val imageShare: ImageView = findViewById(R.id.imageShere)

        // Set data ke komponen layout
        beritaTitle.text = title
        beritaDescription.text = description
        beritapub.text = pubDate
        beritalink.text = LinkBerita

        // Memuat gambar menggunakan Glide
        Glide.with(this)
            .load(imageUrl)
            .into(beritaImage)
        imageShare.setOnClickListener {
            shareNews(title, description, LinkBerita)
        }

    }

    private fun shareNews(title: String, description: String, link: String?) {
        val shareText = """
            $title
            
            $description
            
            Baca selengkapnya di: ${link ?: "Tidak ada tautan"}
        """.trimIndent()

        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareText)
            type = "text/plain"
        }
        startActivity(Intent.createChooser(shareIntent, "Bagikan berita melalui"))
        }
}