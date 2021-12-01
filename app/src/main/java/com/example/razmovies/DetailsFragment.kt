package com.example.razmovies

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.razmovies.adapter.MoviesAdapter
import com.example.razmovies.databinding.FragmentDetailsBinding
import com.example.razmovies.databinding.FragmentPopularMoviesBinding
import com.example.razmovies.model.MovieDetailsViewModel
import com.example.razmovies.model.MovieViewModel


class DetailsFragment : Fragment() {

    private val movieDetailsviewModel: MovieDetailsViewModel by activityViewModels()

    private var binding: FragmentDetailsBinding? = null

    //to set the key in variable
    companion object {
            const val movieId = "id"
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDetailsBinding.inflate(inflater)

        binding?.apply {
            // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
            lifecycleOwner = viewLifecycleOwner
            viewModel = movieDetailsviewModel
            //@ because inside binding.apply this revers to the binding instance
            // not the class DetailsFragment
            detailsFragment = this@DetailsFragment
        }
        binding.share.setOnClickListener {
            val shred = Intent().apply {
                this.action = Intent.ACTION_SEND
                this.putExtra(Intent.EXTRA_TEXT, "https://www.themoviedb.org/movie/${id}")
                this.type = "text/plain"
            }
            startActivity(shred)
        }

        return binding.root
    }






    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            // set the chosen movie id.
            movieDetailsviewModel.setMovieId( it.getString(movieId).toString())
        }

    }




}