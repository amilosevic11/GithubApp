package hr.dice.amilosevic_.githubapp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RepositoryOwnerInformation(
    val id: Int,
    @SerializedName("login")
    val ownerName: String,
    val avatar_url: String,
    val url: String,
    @SerializedName("html_url")
    val profileUrl: String,
    val repos_url: String,
    val type: String
): Parcelable