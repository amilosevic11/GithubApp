package hr.dice.amilosevic_.githubapp.repos

import hr.dice.amilosevic_.githubapp.data.api.GithubSearchApi
import hr.dice.amilosevic_.githubapp.models.SearchRepositoriesResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class GithubSearchApiRepository(private val githubSearchApi: GithubSearchApi) {

    suspend fun searchRepositories(query: String, sort: String): SearchRepositoriesResponse {
         return githubSearchApi.searchRepositories(query, sort)
    }
}