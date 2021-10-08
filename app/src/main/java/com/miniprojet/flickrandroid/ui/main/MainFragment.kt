package com.miniprojet.flickrandroid.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import com.miniprojet.flickrandroid.R

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val layout = inflater.inflate(R.layout.main_fragment, container, false)

        // On récupère l'image du layout
        // Si findViewById est utilisé dans un fragment, il faut écrire layout. devant
        // car il s'agit d'une méthode d'une Activity
        val imageview = layout.findViewById<ImageView>(R.id.imagePreview)

        // On crée le ViewModelProvider
        val model = ViewModelProvider(this).get(MainViewModel::class.java)

        // On observe le LiveData photoaffichee
        model.photoaffichee.observe(this, Observer { photo ->
            // Ici on modifie l'URL associée à l'image observée lorsqu'il y a un changement
            val url = "https://farm" + photo.farm + ".staticflickr.com/" + photo.server + "/" + photo.id+"_"+photo.secret + ".jpg"
            Glide.with(getActivity()).load(url).into(imageview)
        })

        return layout
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

}