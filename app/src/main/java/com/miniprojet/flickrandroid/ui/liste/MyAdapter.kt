package com.miniprojet.flickrandroid.ui.liste

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.miniprojet.flickrandroid.R
import com.miniprojet.flickrandroid.model.Photo
import com.miniprojet.flickrandroid.ui.main.MainFragmentDirections

class MyAdapter (val photos : List<Photo>, val callback: (Int) -> Unit) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
        // un ViewHolder permet de stocker la vue de chaque item de la liste
        class MyViewHolder(val v: GridLayout) : RecyclerView.ViewHolder(v)

        // appelé quand le ViewHolder doit être créé (probablement parce que l'item devient visible)
        // on crée (inflate) le layout "user" et on le place dans le ViewHolder
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
            val layout = LayoutInflater.from(parent.context).inflate(R.layout.photo,parent,false)
            val holder = MyViewHolder(layout as GridLayout)

            layout.setOnClickListener {
                // ici on sait que l'utilisateur a cliqué sur l'item
                callback(holder.adapterPosition)
            }

            return holder
        }

        // appelé quand le recycerview a besoin de connaître la taille de la
        // liste qu'il doit afficher
        override fun getItemCount(): Int = photos.size

        // appelé quand on doit peupler le ViewHolder avec le contenu de l'élément numéro "position"
        override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position:Int) {
            val photoImageView = holder.v.findViewById<ImageView>(R.id.photo)
            val photo = photos[position]

            val url = "https://farm" + photo.farm + ".staticflickr.com/" + photo.server + "/" + photo.id + "_" + photo.secret + ".jpg"

            Glide.with(holder.v).load(url).into(photoImageView)

            photoImageView.setOnClickListener {
                val action = ListFragmentDirections.listVersFull(url, photo.title)
                Navigation.findNavController(holder.v).navigate(action)
            }
        }
    }