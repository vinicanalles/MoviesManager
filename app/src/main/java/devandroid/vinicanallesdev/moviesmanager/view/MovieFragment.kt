package devandroid.vinicanallesdev.moviesmanager.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import devandroid.vinicanallesdev.moviesmanager.R
import devandroid.vinicanallesdev.moviesmanager.databinding.FragmentMovieBinding
import devandroid.vinicanallesdev.moviesmanager.model.entity.Movie
import devandroid.vinicanallesdev.moviesmanager.model.entity.Movie.Companion.MOVIE_WATCHED_FALSE
import devandroid.vinicanallesdev.moviesmanager.model.entity.Movie.Companion.MOVIE_WATCHED_TRUE
import devandroid.vinicanallesdev.moviesmanager.view.MainFragment.Companion.EXTRA_MOVIE
import devandroid.vinicanallesdev.moviesmanager.view.MainFragment.Companion.MOVIE_FRAGMENT_REQUEST_KEY

class MovieFragment : Fragment() {

    private lateinit var fmb: FragmentMovieBinding
    private val navigationArgs: MovieFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle =
            getString(R.string.movie_details)

        fmb = FragmentMovieBinding.inflate(inflater, container, false)

        val genders = arrayOf("Comédia", "Ação", "Terror", "Suspense", "Aventura", "Ficção Científica")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, genders)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        fmb.genderSp.adapter = adapter

        val receivedMovie = navigationArgs.movie
        receivedMovie?.also { movie ->
            with(fmb) {
                nameEt.setText(movie.name)
                releaseYearEt.setText(movie.releaseYear)
                producerEt.setText(movie.producer)
                durationEt.setText(movie.duration)
                if (movie.grade != null) gradeEt.setText(movie.grade.toString())
                genderSp.setSelection(genders.indexOf(movie.gender))
                watchedCb.isChecked = movie.watched == MOVIE_WATCHED_TRUE

                navigationArgs.editMovie.also { editMovie ->
                    nameEt.isEnabled = false
                    releaseYearEt.isEnabled = editMovie
                    producerEt.isEnabled = editMovie
                    durationEt.isEnabled = editMovie
                    gradeEt.isEnabled = editMovie
                    genderSp.isEnabled = editMovie
                    watchedCb.isEnabled = editMovie
                    saveBt.visibility = if (editMovie) VISIBLE else View.GONE
                }
            }
        }

        fmb.run {
            saveBt.setOnClickListener {
                setFragmentResult(MOVIE_FRAGMENT_REQUEST_KEY, Bundle().apply {
                    putParcelable(
                        EXTRA_MOVIE, Movie(
                            0,
                            nameEt.text.toString(),
                            releaseYearEt.text.toString(),
                            producerEt.text.toString(),
                            durationEt.text.toString(),
                            if (watchedCb.isChecked) MOVIE_WATCHED_TRUE else MOVIE_WATCHED_FALSE,
                            if (gradeEt.text.toString().isEmpty()) {
                                null
                            } else {
                                val grade = gradeEt.text.toString().toDouble()
                                if (grade in 0.0..10.0) {
                                    grade
                                } else {
                                    Toast.makeText(
                                        requireContext(),
                                        getString(R.string.nota_invalida),
                                        Toast.LENGTH_LONG
                                    ).show()
                                    null
                                }
                            },
                            genderSp.selectedItem.toString()
                        )
                    )
                })
                findNavController().navigateUp()
            }
        }

        return fmb.root
    }
}