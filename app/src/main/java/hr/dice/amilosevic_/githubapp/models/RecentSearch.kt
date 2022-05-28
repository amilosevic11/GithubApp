package hr.dice.amilosevic_.githubapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recentSearches")
data class RecentSearch(
    @PrimaryKey
    var query: String
)