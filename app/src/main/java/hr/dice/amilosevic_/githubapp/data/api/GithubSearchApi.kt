package hr.dice.amilosevic_.githubapp.data.api

import hr.dice.amilosevic_.githubapp.models.SearchRepositoriesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface GithubSearchApi {

    @GET("search/repositories")
    suspend fun searchRepositories(@Query("q") query: String, @Query("sort") sort: String) : SearchRepositoriesResponse
}