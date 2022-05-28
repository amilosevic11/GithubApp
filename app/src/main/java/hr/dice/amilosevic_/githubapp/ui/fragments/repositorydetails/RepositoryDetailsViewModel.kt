package hr.dice.amilosevic_.githubapp.ui.fragments.repositorydetails

import androidx.lifecycle.ViewModel
import hr.dice.amilosevic_.githubapp.helpers.BASE_URL
import hr.dice.amilosevic_.githubapp.helpers.USER

class RepositoryDetailsViewModel : ViewModel() {

    fun getRepositoryUrl(profileUrl: String, accountType: String, ownerName: String) : String {
        return if(accountType == USER)
            "${profileUrl}?tab=repositories"
        else
            "${BASE_URL}orgs/${ownerName}/repositories"
    }

}