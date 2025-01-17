package com.example.breakingnews.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breakingnews.BeritaApi
import com.example.breakingnews.RetrofitHelper
import com.example.breakingnews.model.Berita
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _berita = MutableLiveData<List<Berita>?>()
    val berita: LiveData<List<Berita>?>
        get() = _berita

    fun getBeritas() {
        viewModelScope.launch {
            try {
                val api = RetrofitHelper.getInstance().create(BeritaApi::class.java)
                val response = api.getBeritas()

                if (response.isSuccessful) {
                    Log.d("MainViewModel", "Data Berita: ${response.body()}")
                    _berita.postValue(response.body())
                } else {
                    Log.e("MainViewModel", "Response failed: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("MainViewModel", "Exception occurred: ${e.message}")
            }
            }
        }
}