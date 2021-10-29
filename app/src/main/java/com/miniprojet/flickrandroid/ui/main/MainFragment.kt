package com.miniprojet.flickrandroid.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import com.miniprojet.flickrandroid.R
import com.bumptech.glide.Glide

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

        // On crée le ViewModelProvider
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        // On récupère l'image du layout
        // Si findViewById est utilisé dans un fragment, il faut écrire layout. devant
        // car il s'agit d'une méthode d'une Activity

        // On observe le LiveData photoaffichee
        viewModel.photoaffichee.observe(viewLifecycleOwner, Observer { photo ->
            // Ici on modifie l'URL associée à l'image observée lorsqu'il y a un changement
            val url = "https://farm" + photo.farm + ".staticflickr.com/" + photo.server + "/" + photo.id + "_" + photo.secret + ".jpg"

            val imageTitle = layout.findViewById<TextView>(R.id.imageTitle)
            imageTitle.text = photo.title

            val imageView = layout.findViewById<ImageView>(R.id.imagePreview)
            val nextButton = layout.findViewById<Button>(R.id.nextButton)
            val allImagesButton = layout.findViewById<Button>(R.id.allImagesButton)

            // Schéma : Glide.with(fragment).load(url).into(imageView);
            // Ici le fragment choisi est notre layout
            Glide.with(layout).load(url).into(imageView)
        })
        return layout
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

}