package hr.dice.amilosevic_.githubapp.ui.fragments.searchrepositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.dice.amilosevic_.githubapp.models.RecentSearch
import hr.dice.amilosevic_.githubapp.repos.RecentSearchesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchRepositoriesViewModel(
    private val recentSearchesRepository: RecentSearchesRepository
) : ViewModel() {

    private val _buttonEnabled: MutableLiveData<Boolean> = MutableLiveData()
    val buttonEnabled: LiveData<Boolean>
        get() = _buttonEnabled

    private val _recentSearches: MutableLiveData<List<String>> = MutableLiveData()
    val recentSearches: LiveData<List<String>>
        get() = _recentSearches

    fun getRecentSearchesByKeyword(keyword: String) {
        viewModelScope.launch(Dispatchers.IO) {
            recentSearchesRepository.getRecentSearchesByKeyword(keyword).collect {
                _recentSearches.postValue(it)
            }
        }
    }

    fun saveQuery(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            recentSearchesRepository.addQuery(RecentSearch(query))
        }
    }

    fun deleteAllRecentSearches() {
        viewModelScope.launch(Dispatchers.IO) {
            recentSearchesRepository.deleteAllRecentSearches()
            _recentSearches.postValue(arrayListOf())
        }
    }

    fun shouldEnableButton(count: Int) {
        if(count >= 2) {
            _buttonEnabled.postValue(true)
        }
        else {
            _buttonEnabled.postValue(false)
        }
    }
}