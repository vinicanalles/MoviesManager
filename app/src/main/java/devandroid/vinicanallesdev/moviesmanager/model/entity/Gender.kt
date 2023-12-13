package devandroid.vinicanallesdev.moviesmanager.model.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Gender(
    @PrimaryKey(autoGenerate = true)
    var id: Int = INITIAL_ID,
    var name: String = ""
): Parcelable {
    companion object {
        const val INITIAL_ID = -1
    }
}