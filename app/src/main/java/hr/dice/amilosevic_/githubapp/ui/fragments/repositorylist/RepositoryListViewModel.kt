package hr.dice.amilosevic_.githubapp.ui.fragments.repositorylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.dice.amilosevic_.githubapp.models.SearchRepositoriesResponse
import hr.dice.amilosevic_.githubapp.repos.GithubSearchApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RepositoryListViewModel(private val searchApiRepository: GithubSearchApiRepository) : ViewModel() {

    private val _repositoriesResponse: MutableLiveData<SearchRepositoriesResponse> = MutableLiveData()
    val repositoriesResponse: LiveData<SearchRepositoriesResponse>
        get() = _repositoriesResponse

    fun searchRepositories(query: String, sort: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _repositoriesResponse.postValue(searchApiRepository.searchRepositories(query, sort))
        }
    }
}