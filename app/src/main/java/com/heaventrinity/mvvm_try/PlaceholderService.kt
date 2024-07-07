package com.heaventrinity.mvvm_try

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PlaceholderService {

    @GET("photos")
    fun getPhotos(): Call<List<Photo>>

    companion object {
        fun create(): PlaceholderService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(PlaceholderService::class.java)
        }
    }
}





