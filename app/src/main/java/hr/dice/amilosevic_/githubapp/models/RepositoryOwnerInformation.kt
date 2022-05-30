package hr.dice.amilosevic_.githubapp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RepositoryOwnerInformation(
    val id: Int,
    @SerializedName("login")
    val ownerName: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    val url: String,
    @SerializedName("html_url")
    val profileUrl: String,
    @SerializedName("repos_url")
    val reposUrl: String,
    val type: String
): Parcelable