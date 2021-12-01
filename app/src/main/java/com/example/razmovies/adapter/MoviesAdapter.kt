package com.example.razmovies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.razmovies.PopularMoviesFragmentDirections
import com.example.razmovies.R
import com.example.razmovies.data.ResultsItem
import com.example.razmovies.databinding.GridViewItemBinding
import com.example.razmovies.model.MovieApiStatus
import com.example.razmovies.model.MovieViewModel
import com.example.razmovies.network.MovieApiService

class MoviesAdapter: ListAdapter<ResultsItem,
        MoviesAdapter.ItemViewHolder>(DiffCallback) {


    companion object DiffCallback : DiffUtil.ItemCallback<ResultsItem>() {
        override fun areItemsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
            return oldItem.posterPath == newItem.posterPath
        }
    }



    class ItemViewHolder(var binding: GridViewItemBinding)
        : RecyclerView.ViewHolder(binding.root){

        fun bind(moviesData: ResultsItem){
            binding.data = moviesData
            //update immediately
            binding.movieImage.setOnClickListener{

                //navigate and send the id of the movie to display the movie details.
                val action = PopularMoviesFragmentDirections
                    .actionPopularMoviesFragmentToDetailsFragment(
                        id = moviesData.id.toString()
                    )


                //perform navigation action
                itemView.findNavController().navigate(action)
            }
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        return ItemViewHolder(
            GridViewItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val movieDetails = getItem(position)
        holder.bind(movieDetails)



    }
}



