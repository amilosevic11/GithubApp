package hr.dice.amilosevic_.githubapp.data.roomdatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import hr.dice.amilosevic_.githubapp.models.RecentSearch

@Database(entities = [RecentSearch::class], version = 1, exportSchema = false)
abstract class RecentSearchesDatabase : RoomDatabase() {
    abstract fun recentSearchDao() : RecentSearchesDao
}