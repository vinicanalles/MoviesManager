package devandroid.vinicanallesdev.moviesmanager.model.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(indices = [Index(value = ["name"], unique = true)])
data class Movie(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val releaseYear: String,
    val producer: String,
    val duration: String,
    var watched: Int,
    val grade: Double?,
    val gender: String
) : Parcelable {
    companion object {
        const val MOVIE_WATCHED_TRUE = 1
        const val MOVIE_WATCHED_FALSE = 0
    }
}