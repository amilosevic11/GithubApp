package hr.dice.amilosevic_.githubapp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Repository(
    val id: Int,
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    val owner: RepositoryOwnerInformation,
    val description: String,
    @SerializedName("open_issues_count")
    val openIssuesCount: Int,
    @SerializedName("watchers_count")
    val watchersCount: Int,
    @SerializedName("stargazers_count")
    val stargazersCount: Int,
    val private: Boolean
): Parcelable