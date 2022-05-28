package hr.dice.amilosevic_.githubapp.ui.fragments.repositorydetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import hr.dice.amilosevic_.githubapp.R
import hr.dice.amilosevic_.githubapp.databinding.FragmentRepositoryDetailsBinding
import hr.dice.amilosevic_.githubapp.models.Repository
import hr.dice.amilosevic_.githubapp.ui.baseFragment.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepositoryDetailsFragment : BaseFragment<FragmentRepositoryDetailsBinding>() {

    private val repositoryDetailsViewModel: RepositoryDetailsViewModel by viewModel()
    private val repositoryDetailsFragmentArgs: RepositoryDetailsFragmentArgs by navArgs()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRepositoryDetailsBinding
        get() = FragmentRepositoryDetailsBinding::inflate

    override fun setupUi() {
        setOnClickListener()
        fillData(repository = repositoryDetailsFragmentArgs.repository)
    }

    private fun setOnClickListener() {
        binding.sivBack.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.btnOwnerRepos.setOnClickListener {
            navigateToOwnerRepositoriesWebView(
                repositoryDetailsFragmentArgs.repository.owner.ownerName,
                repositoryDetailsViewModel.getRepositoryUrl(
                    repositoryDetailsFragmentArgs.repository.owner.profileUrl,
                    repositoryDetailsFragmentArgs.repository.owner.type,
                    repositoryDetailsFragmentArgs.repository.owner.ownerName
                )
            )
        }
        binding.btnOwnerProfile.setOnClickListener {
            navigateToOwnerRepositoriesWebView(
                repositoryDetailsFragmentArgs.repository.owner.ownerName,
                repositoryDetailsFragmentArgs.repository.owner.profileUrl
            )
        }
    }

    private fun fillData(repository: Repository) {
        with(binding) {
            sivOwnerAvatar.load(repository.owner.avatar_url)
            mtvRepositoryName.text = repository.name
            mtvOwnerName.text = repository.owner.ownerName
            mtvDescription.text = repository.description
            mtvOpenedIssuesCount.text = repository.open_issues_count.toString()
            mtvWatchersCount.text = repository.watchers_count.toString()
            mtvStarsCount.text = repository.stargazers_count.toString()

            if (repository.private)
                mtvIsLocked.text = getString(R.string.yes_label)
            else
                mtvIsLocked.text = getString(R.string.no_label)
        }
    }

    private fun navigateToOwnerRepositoriesWebView(name: String, url: String) {
        findNavController().navigate(
            RepositoryDetailsFragmentDirections.actionRepositoryDetailsFragmentToOwnerProfileAndRepositoriesFragment(name, url)
        )
    }
}