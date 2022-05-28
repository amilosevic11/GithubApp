package hr.dice.amilosevic_.githubapp.data.roomdatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import hr.dice.amilosevic_.githubapp.models.RecentSearch

@Dao
interface RecentSearchesDao {
    @Insert(onConflict = REPLACE)
    suspend fun insert(recentSearch: RecentSearch)

    @Query("SELECT * FROM recentSearches")
    suspend fun getAll(): List<RecentSearch>

    @Query("DELETE FROM recentSearches")
    suspend fun deleteAll()
}