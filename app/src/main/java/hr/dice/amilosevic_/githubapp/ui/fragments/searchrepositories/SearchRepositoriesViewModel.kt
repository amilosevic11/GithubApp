package hr.dice.amilosevic_.githubapp.ui.fragments.searchrepositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.dice.amilosevic_.githubapp.models.RecentSearch
import hr.dice.amilosevic_.githubapp.models.SearchRepositoriesResponse
import hr.dice.amilosevic_.githubapp.repos.GithubSearchApiRepository
import hr.dice.amilosevic_.githubapp.repos.RecentSearchesRepository
import kotlinx.coroutines.launch

class SearchRepositoriesViewModel(
    private val recentSearchesRepository: RecentSearchesRepository
) : ViewModel() {

    private var _recentSearches: MutableLiveData<ArrayList<String>> = MutableLiveData()
    var recentSearches: LiveData<ArrayList<String>> = _recentSearches

    fun getAllRecentSearches() {
        viewModelScope.launch {
            recentSearchesRepository.getAllRecentSearches().collect { recentSearchList ->
                val recentSearches: ArrayList<String> = ArrayList()
                recentSearchList.forEach {
                    recentSearches.add(it.query)
                }

                _recentSearches.postValue(recentSearches)
            }
        }
    }

    fun saveQuery(query: String) {
        viewModelScope.launch {
            recentSearchesRepository.addQuery(RecentSearch(query))
        }
    }

    fun deleteAllRecentSearches() {
        viewModelScope.launch {
            recentSearchesRepository.deleteAllRecentSearches()
            _recentSearches.postValue(arrayListOf())
        }
    }
}