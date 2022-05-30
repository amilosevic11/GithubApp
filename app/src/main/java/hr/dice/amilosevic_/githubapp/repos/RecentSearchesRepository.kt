package hr.dice.amilosevic_.githubapp.repos

import hr.dice.amilosevic_.githubapp.data.roomdatabase.RecentSearchesDao
import hr.dice.amilosevic_.githubapp.models.RecentSearch
import kotlinx.coroutines.flow.Flow

class RecentSearchesRepository(private val recentSearchesDao: RecentSearchesDao) {

    fun addQuery(recentSearch: RecentSearch) {
        recentSearchesDao.insert(recentSearch)
    }

    fun deleteAllRecentSearches() {
        recentSearchesDao.deleteAll()
    }

    fun getRecentSearchesByKeyword(keyword: String): Flow<List<String>> = recentSearchesDao.getRecentSearchesByKeyword(keyword)
}