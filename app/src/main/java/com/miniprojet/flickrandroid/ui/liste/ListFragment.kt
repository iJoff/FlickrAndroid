package com.miniprojet.flickrandroid.ui.liste

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miniprojet.flickrandroid.R
import com.miniprojet.flickrandroid.ui.main.MainViewModel

class ListFragment : Fragment() {

    companion object {
        fun newInstance() = ListFragment()
    }

    private lateinit var viewModel: ListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.list_fragment, container, false)
        getActivity()?.setTitle("Flickr - Galerie")

        // On crée le ViewModelProvider
        viewModel = ListViewModel()

        // On observe les LiveData
        viewModel.photos.observe(this, Observer { photo ->
            val recycler = layout.findViewById<RecyclerView>(R.id.galleryrecyclerview)
            recycler.layoutManager = GridLayoutManager(requireActivity(), 2)
            recycler.adapter = MyAdapter(photo) { position ->   }
        })

        return layout
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}

