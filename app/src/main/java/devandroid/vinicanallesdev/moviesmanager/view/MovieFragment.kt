package devandroid.vinicanallesdev.moviesmanager.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import devandroid.vinicanallesdev.moviesmanager.R
import devandroid.vinicanallesdev.moviesmanager.databinding.FragmentMovieBinding

class MovieFragment : Fragment() {

    private lateinit var fmb: FragmentMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }
}