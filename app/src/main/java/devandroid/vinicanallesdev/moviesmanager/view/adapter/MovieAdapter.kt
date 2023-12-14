package devandroid.vinicanallesdev.moviesmanager.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import devandroid.vinicanallesdev.moviesmanager.R
import devandroid.vinicanallesdev.moviesmanager.databinding.TileMovieBinding
import devandroid.vinicanallesdev.moviesmanager.model.entity.Movie
import devandroid.vinicanallesdev.moviesmanager.model.entity.Movie.Companion.MOVIE_WATCHED_TRUE

class MovieAdapter (
    private val movieList: List<Movie>,
    private val onMovieClickListener: OnMovieClickListener
) : RecyclerView.Adapter<MovieAdapter.MovieTileViewHolder>() {

    inner class MovieTileViewHolder(tileMovieBinding: TileMovieBinding) : RecyclerView.ViewHolder(tileMovieBinding.root) {
        val nameTv: TextView = tileMovieBinding.nameTv
        val watchedCb: CheckBox = tileMovieBinding.watchedCb

        init {
            tileMovieBinding.apply {
                root.run {
                    setOnCreateContextMenuListener { menu, _, _ ->
                        (onMovieClickListener as? Fragment)?.activity?.menuInflater?.inflate(
                            R.menu.context_menu_movie,
                            menu
                        )
                        menu?.findItem(R.id.removeMovieMi)?.setOnMenuItemClickListener {
                            onMovieClickListener.onRemoveMovieMenuItemClick(adapterPosition)
                            true
                        }
                        menu?.findItem(R.id.editMovieMi)?.setOnMenuItemClickListener {
                            onMovieClickListener.onEditMovieMenuItemClick(adapterPosition)
                            true
                        }
                    }
                    setOnClickListener {
                        onMovieClickListener.onMovieClick(adapterPosition)
                    }
                }
                watchedCb.run {
                    setOnClickListener {
                        onMovieClickListener.onWatchedCheckBoxClick(adapterPosition, isChecked)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TileMovieBinding.inflate(
        LayoutInflater.from(parent.context), parent, false
    ).run { MovieTileViewHolder(this) }

    override fun onBindViewHolder(holder: MovieTileViewHolder, position: Int) {
        movieList[position].let {movie ->
            with(holder) {
                nameTv.text = movie.name
                watchedCb.isChecked = movie.watched == MOVIE_WATCHED_TRUE
            }
        }
    }

    override fun getItemCount() = movieList.size
}