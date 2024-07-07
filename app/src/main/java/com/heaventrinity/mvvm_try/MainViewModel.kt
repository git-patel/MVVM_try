package com.heaventrinity.mvvm_try

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _photos = MutableLiveData<List<Photo>>()
    val photos: LiveData<List<Photo>>
        get() = _photos

    init {
        fetchPhotos()
    }

    private fun fetchPhotos() {
        val service = PlaceholderService.create()
        service.getPhotos().enqueue(object : Callback<List<Photo>> {
            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                if (response.isSuccessful) {
                    _photos.value = response.body()
                } else {
                    // Handle error
                }
            }

            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                // Handle failure
            }
        })
    }
}
