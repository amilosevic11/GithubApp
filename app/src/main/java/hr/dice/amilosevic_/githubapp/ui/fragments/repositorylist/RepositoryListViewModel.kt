package hr.dice.amilosevic_.githubapp.ui.fragments.repositorylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.dice.amilosevic_.githubapp.models.SearchRepositoriesResponse
import hr.dice.amilosevic_.githubapp.repos.GithubSearchApiRepository
import kotlinx.coroutines.launch

class RepositoryListViewModel(private val searchApiRepository: GithubSearchApiRepository) : ViewModel() {

    private var _repositoriesResponse: MutableLiveData<SearchRepositoriesResponse> = MutableLiveData()
    val repositoriesResponse: LiveData<SearchRepositoriesResponse> = _repositoriesResponse

    fun searchRepositories(query: String, sort: String) {
        viewModelScope.launch {
            searchApiRepository.searchRepositories(query, sort).collect {
                _repositoriesResponse.postValue(it)
            }
        }
    }
}