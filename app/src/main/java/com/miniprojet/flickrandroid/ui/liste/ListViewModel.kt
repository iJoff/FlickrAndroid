package com.miniprojet.flickrandroid.ui.liste

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miniprojet.flickrandroid.model.Photo
import com.miniprojet.flickrandroid.model.SearchResult
import com.miniprojet.flickrandroid.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListViewModel : ViewModel() {
    val photos = MutableLiveData<List<Photo>>()
    var listephotos = listOf<Photo>()

    init {
        val repository = Repository()
        repository.getPhotos(object : Callback<SearchResult> {
            override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
                val listephotostemporaire = response.body()?.photos?.photo

                if (listephotostemporaire != null) {
                    listephotos = listephotostemporaire
                }
                photos.value = listephotos
            }

            override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                Log.v("Error", "Failure on the repository loading")
            }
        })
    }
}