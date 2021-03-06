package com.miniprojet.flickrandroid.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miniprojet.flickrandroid.model.Photo
import com.miniprojet.flickrandroid.model.SearchResult
import com.miniprojet.flickrandroid.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    val photoaffichee = MutableLiveData<Photo>()
    var listephotos = listOf<Photo>()
    var numPhoto: Int = 0

    init {
        val repository = Repository()
        repository.getPhotos(object : Callback<SearchResult> {
                override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
                    // On accède à la réponse via response.body de classe SearchResult
                    // Un objet SearchResult inclut un objet photos de classe Photos
                    // Un objet Photos inclut un objet photo de classe List<Photo>
                    // Cet objet photo correspond à la liste des photos récupérée sur le serveur

                    Log.v("Success", "Success on the repository loading")

                    // Si elle existe, la liste des photos est récupérées
                    // Attention : "photo" est bien une liste de photos, non une photo
                    val listephotostemporaire = response.body()?.photos?.photo

                    // La liste peut être nulle, il faut donc vérifier qu'elle ne le soit pas
                    if (listephotostemporaire != null) {
                        // Comme il s'agit d'un init, on affecte directement la première photo
                        listephotos = listephotostemporaire
                        photoaffichee.value = listephotostemporaire[numPhoto]
                    }
                }

                override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                    Log.v("Error", "Failure on the repository loading")
                }
            }
        )
    }

    fun nextPhoto() {
        if (numPhoto == listephotos.size - 1) {
            numPhoto = 0
        } else {
            numPhoto += 1
        }

        photoaffichee.value = listephotos[numPhoto]
    }
}