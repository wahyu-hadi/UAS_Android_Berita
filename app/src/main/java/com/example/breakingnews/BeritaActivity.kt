package com.example.breakingnews

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.breakingnews.databinding.ActivityBeritaBinding
import com.example.breakingnews.viewmodel.MainViewModel

class BeritaActivity : AppCompatActivity() {
    private var _binding: ActivityBeritaBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()
    private var username: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityBeritaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        username = intent.getStringExtra("username")

        // Set up RecyclerView with LinearLayoutManager
        binding.recyclerView.layoutManager = LinearLayoutManager(this)


        viewModel.getBeritas()
        viewModel.berita.observe(this) { data ->
            if (data != null && data.isNotEmpty()) {
                val adapter = BeritaAdapter(data) { berita ->
                    // Pastikan data yang dikirim tidak kosong atau null
                    if (berita.title.isNullOrEmpty() || berita.description.isNullOrEmpty() || berita.thumbnail.isNullOrEmpty()) {
                        Toast.makeText(this, "Data berita tidak lengkap", Toast.LENGTH_SHORT).show()
                    } else {
                        // Handle berita item click and navigate to DetailBeritaActivity
                        val intent = Intent(this, DetailBeritaActivity::class.java)
                        intent.putExtra("title", berita.title)
                        intent.putExtra("description", berita.description)
                        intent.putExtra("imageUrl", berita.thumbnail)
                        intent.putExtra("pubDate", berita.pubDate)
                        intent.putExtra("LinkBerita", berita.link)
                        startActivity(intent)
                    }
                }
                binding.recyclerView.adapter = adapter
            } else {
                Toast.makeText(this, "Data berita tidak ditemukan", Toast.LENGTH_SHORT).show()
            }
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding=null
        }
}