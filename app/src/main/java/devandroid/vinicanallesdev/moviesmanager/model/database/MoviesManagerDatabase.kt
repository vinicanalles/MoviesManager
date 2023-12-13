package devandroid.vinicanallesdev.moviesmanager.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import devandroid.vinicanallesdev.moviesmanager.model.dao.MovieDao
import devandroid.vinicanallesdev.moviesmanager.model.entity.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MoviesManagerDatabase: RoomDatabase() {

    companion object {
        const val MOVIES_MANAGER_DATABASE = "moviesManagerDatabase"
    }

    abstract fun getMovieDao(): MovieDao
}