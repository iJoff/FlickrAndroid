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
import androidx.navigation.Navigation
import com.miniprojet.flickrandroid.R
import com.bumptech.glide.Glide
import com.miniprojet.flickrandroid.ui.liste.ListFragmentDirections

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

        // On crée le ViewModelProvider (syntaxe plus légère que celle des TD)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        // On récupère les boutons et l'image du layout
        // Si findViewById est utilisé dans un fragment, il faut écrire layout. devant
        // car il s'agit d'une méthode d'une Activity
        val imageTitle = layout.findViewById<TextView>(R.id.imageTitle)
        val imageView = layout.findViewById<ImageView>(R.id.imagePreview)
        val nextButton = layout.findViewById<Button>(R.id.nextButton)
        val allImagesButton = layout.findViewById<Button>(R.id.allImagesButton)

        // On observe le LiveData photoaffichee
        viewModel.photoaffichee.observe(viewLifecycleOwner, Observer { photo ->
            // Ici on modifie l'URL associée à l'image observée lorsqu'il y a un changement
            val url = "https://farm" + photo.farm + ".staticflickr.com/" + photo.server + "/" + photo.id + "_" + photo.secret + ".jpg"

            // Afficher le titre de l'image sur la TextView "imageTitle"
            imageTitle.text = photo.title
            getActivity()?.setTitle("Flickr - " + photo.title)

            // Schéma : Glide.with(fragment).load(url).into(imageView);
            // Ici le fragment choisi est notre layout
            Glide.with(layout).load(url).into(imageView)

            // Le clic sur "allImagesButton" doit renvoyer vers ListFragment
            allImagesButton.setOnClickListener {
                Navigation.findNavController(layout).navigate(R.id.versListeFragment)
            }

            // Le clic sur l'image doit l'afficher en plein écran
            imageView.setOnClickListener {
                val action = MainFragmentDirections.mainVersFull(url, photo.title)
                Navigation.findNavController(layout).navigate(action)
            }

            // Le clic sur "Next" doit afficher l'image suivante
            nextButton.setOnClickListener {
                viewModel.nextPhoto()
            }
        })

        return layout
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

}