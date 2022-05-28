package hr.dice.amilosevic_.githubapp.di

import androidx.room.Room
import hr.dice.amilosevic_.githubapp.BuildConfig
import hr.dice.amilosevic_.githubapp.data.api.GithubSearchApi
import hr.dice.amilosevic_.githubapp.data.roomdatabase.RecentSearchesDao
import hr.dice.amilosevic_.githubapp.data.roomdatabase.RecentSearchesDatabase
import hr.dice.amilosevic_.githubapp.repos.GithubSearchApiRepository
import hr.dice.amilosevic_.githubapp.repos.RecentSearchesRepository
import hr.dice.amilosevic_.githubapp.ui.fragments.repositorydetails.RepositoryDetailsViewModel
import hr.dice.amilosevic_.githubapp.ui.fragments.repositorylist.RepositoryListViewModel
import hr.dice.amilosevic_.githubapp.ui.fragments.searchrepositories.SearchRepositoriesViewModel
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
        Room.databaseBuilder(androidContext(), RecentSearchesDatabase::class.java, "RecentSearchesDatabase")
            .build()
    }
    single {
        provideRecentSearchesDao(get())
    }
}
fun provideRecentSearchesDao(recentSearchesDatabase: RecentSearchesDatabase): RecentSearchesDao {
    return recentSearchesDatabase.recentSearchDao()
}

val networkModule = module {
    single { provideRetrofit() }
    single { provideNetwork(get()) }
}
fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .client(OkHttpClient.Builder().build())
        .baseUrl(BuildConfig.SERVER_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
fun provideNetwork(retrofit: Retrofit): GithubSearchApi = retrofit.create(GithubSearchApi::class.java)