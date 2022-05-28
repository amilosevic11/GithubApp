package hr.dice.amilosevic_.githubapp.repos

import hr.dice.amilosevic_.githubapp.data.roomdatabase.RecentSearchesDao
import hr.dice.amilosevic_.githubapp.models.RecentSearch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RecentSearchesRepository(private val recentSearchesDao: RecentSearchesDao) {

    suspend fun addQuery(recentSearch: RecentSearch) {
        recentSearchesDao.insert(recentSearch)
    }

    suspend fun deleteAllRecentSearches() {
        recentSearchesDao.deleteAll()
    }

    suspend fun getAllRecentSearches(): Flow<List<RecentSearch>> = flow {
        emit(recentSearchesDao.getAll())
    }
}