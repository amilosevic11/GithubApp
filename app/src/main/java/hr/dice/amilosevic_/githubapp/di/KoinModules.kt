package hr.dice.amilosevic_.githubapp.di

import androidx.room.Room
import hr.dice.amilosevic_.githubapp.BuildConfig
import hr.dice.amilosevic_.githubapp.data.api.GithubSearchApi
import hr.dice.amilosevic_.githubapp.data.roomdatabase.RecentSearchesDatabase
import hr.dice.amilosevic_.githubapp.helpers.DB_NAME
import hr.dice.amilosevic_.githubapp.repos.GithubSearchApiRepository
import hr.dice.amilosevic_.githubapp.repos.RecentSearchesRepository
import hr.dice.amilosevic_.githubapp.ui.repositorydetails.RepositoryDetailsViewModel
import hr.dice.amilosevic_.githubapp.ui.repositorylist.RepositoryListViewModel
import hr.dice.amilosevic_.githubapp.ui.searchrepositories.SearchRepositoriesViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val repositoryModule = module {
    single { GithubSearchApiRepository(get()) }
    single { RecentSearchesRepository(get()) }
}

val viewModelModule = module {
    viewModel { SearchRepositoriesViewModel(get()) }
    viewModel { RepositoryListViewModel(get()) }
    viewModel { RepositoryDetailsViewModel() }
}

val roomDatabaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            RecentSearchesDatabase::class.java,
            DB_NAME
        ).build()
    }
    single {
        get<RecentSearchesDatabase>().recentSearchDao()
    }
}

val networkModule = module {
    single {
        Retrofit.Builder()
            .client(OkHttpClient.Builder().build())
            .baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single { get<Retrofit>().create(GithubSearchApi::class.java) }
}