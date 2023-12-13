package devandroid.vinicanallesdev.moviesmanager.model.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Movie(
    @PrimaryKey
    var name: String = "",
    var releaseYear: String = "",
    var producer: String = "",
    var duration: Long = 0,
    var watched: Int = MOVIE_WATCHED_FALSE,
    var grade: Long = 0,
    var gender: String = ""
) : Parcelable {
    companion object {
        const val MOVIE_WATCHED_TRUE = 1
        const val MOVIE_WATCHED_FALSE = 0
    }
}