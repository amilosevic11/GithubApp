package hr.dice.amilosevic_.githubapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Repository(
    val id: Int,
    val name: String,
    val full_name: String,
    val owner: RepositoryOwnerInformation,
    val description: String,
    val open_issues_count: Int,
    val watchers_count: Int,
    val stargazers_count: Int,
    val private: Boolean
): Parcelable