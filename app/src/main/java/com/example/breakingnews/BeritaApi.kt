package com.example.breakingnews

import com.example.breakingnews.model.Berita
import retrofit2.Response
import retrofit2.http.GET

interface BeritaApi {
    @GET("berita.json")
    suspend fun getBeritas(): Response<List<Berita>>

}