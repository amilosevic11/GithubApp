package hr.dice.amilosevic_.githubapp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchRepositoriesResponse(
    @SerializedName("total_count")
    val overallNumberOfRepos: Int,
    @SerializedName("items")
    val repositories: ArrayList<Repository>
): Parcelable
