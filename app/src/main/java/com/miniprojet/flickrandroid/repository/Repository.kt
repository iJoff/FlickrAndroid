package com.miniprojet.flickrandroid.repository

import com.miniprojet.flickrandroid.model.SearchResult
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class Repository {

    // Cette fonction utilise un Callback : il s'agit donc par défaut d'une interface
    // Elle est dotée de deux méthodes par défaut : onResponse et onFailure

    fun getPhotos(callback : Callback<SearchResult>) {
        val url = "https://www.flickr.com"
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        val service = retrofit.create(FlickrAPI::class.java)
        service.getInterestingPhotos(
            "flickr.interestingness.getList",
            "34b3c6c1b435ac9b6b4206e3ca8bc32d",
            "20"
        ).enqueue(callback)
    }
}