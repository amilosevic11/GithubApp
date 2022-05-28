package hr.dice.amilosevic_.githubapp

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hr.dice.amilosevic_.githubapp.di.networkModule
import hr.dice.amilosevic_.githubapp.di.repositoryModule
import hr.dice.amilosevic_.githubapp.di.roomDatabaseModule
import hr.dice.amilosevic_.githubapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GithubApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@GithubApp)
            modules(listOf(
                roomDatabaseModule,
                networkModule,
                repositoryModule,
                viewModelModule
            ))
        }
    }
}