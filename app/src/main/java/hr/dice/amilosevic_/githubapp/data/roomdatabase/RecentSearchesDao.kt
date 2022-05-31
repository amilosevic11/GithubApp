package hr.dice.amilosevic_.githubapp.data.roomdatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import hr.dice.amilosevic_.githubapp.models.RecentSearch
import kotlinx.coroutines.flow.Flow

@Dao
interface RecentSearchesDao {
    @Insert(onConflict = REPLACE)
    suspend fun insert(recentSearch: RecentSearch)

    @Query("SELECT `query` FROM recentSearches WHERE `query` LIKE '%' || :keyword || '%'")
    fun getRecentSearchesByKeyword(keyword: String): Flow<List<String>>

    @Query("DELETE FROM recentSearches")
    suspend fun deleteAll()
}