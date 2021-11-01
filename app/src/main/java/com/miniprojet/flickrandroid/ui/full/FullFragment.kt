package com.miniprojet.flickrandroid.ui.full

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.miniprojet.flickrandroid.R

class FullFragment : Fragment() {

    companion object {
        fun newInstance() = FullFragment()
    }

    private lateinit var viewModel: FullViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.full_fragment, container, false)
        val fullImage = layout.findViewById<ImageView>(R.id.fullImagePreview)
        val url = FullFragmentArgs.fromBundle(requireArguments()).url
        val title = FullFragmentArgs.fromBundle(requireArguments()).title

        getActivity()?.setTitle("Flickr - " + title)
        Glide.with(layout).load(url).into(fullImage)

        return layout
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FullViewModel::class.java)
        // TODO: Use the ViewModel
    }

}