package devandroid.vinicanallesdev.moviesmanager.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import devandroid.vinicanallesdev.moviesmanager.model.entity.Movie

@Dao
interface MovieDao {

    companion object {
        const val MOVIE_TABLE = "movie"
    }

    @Insert
    fun createMovie(movie: Movie)

    @Query("SELECT * FROM $MOVIE_TABLE")
    fun retrieveMovies() : List<Movie>

    @Update
    fun updateMovie(movie: Movie)

    @Delete
    fun deleteMovie(movie: Movie)
}