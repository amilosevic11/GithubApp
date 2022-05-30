package hr.dice.amilosevic_.githubapp.ui.fragments.repositorylist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import hr.dice.amilosevic_.githubapp.R
import hr.dice.amilosevic_.githubapp.databinding.FragmentRepositoryListBinding
import hr.dice.amilosevic_.githubapp.helpers.SORT_BY_STARS
import hr.dice.amilosevic_.githubapp.helpers.formatInteger
import hr.dice.amilosevic_.githubapp.models.Repository
import hr.dice.amilosevic_.githubapp.ui.adapters.RepositoriesRecyclerViewAdapter
import hr.dice.amilosevic_.githubapp.ui.baseFragment.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepositoryListFragment : BaseFragment<FragmentRepositoryListBinding>(), RepositoriesRecyclerViewAdapter.OnItemClickListener {

    private val repositoryListViewModel: RepositoryListViewModel by viewModel()
    private val repositoryListFragmentArgs: RepositoryListFragmentArgs by navArgs()
    private lateinit var repositoriesRecyclerViewAdapter: RepositoriesRecyclerViewAdapter

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRepositoryListBinding
        get() = FragmentRepositoryListBinding::inflate

    override fun setupUi() {
        shouldShowProgressBar(true)
        setupRecyclerView()
        searchRepositories()
        observeData()
    }

    private fun setupRecyclerView() {
        repositoriesRecyclerViewAdapter = RepositoriesRecyclerViewAdapter(this)
        binding.rvRepositories.also {
            it.layoutManager = LinearLayoutManager(requireContext())
            it.adapter = repositoriesRecyclerViewAdapter
        }
    }

    private fun searchRepositories() {
        repositoryListViewModel.searchRepositories(repositoryListFragmentArgs.query, SORT_BY_STARS)
    }

    private fun observeData() {
        repositoryListViewModel.repositoriesResponse.observe(viewLifecycleOwner) {
            repositoriesRecyclerViewAdapter.submitList(it.repositories)
            binding.mtvRepositoriesCount.text = getString(R.string.repository_results, formatInteger(it.overallNumberOfRepos))
            shouldShowProgressBar(false)
        }
    }

    private fun shouldShowProgressBar(show: Boolean) {
        if(show) {
            binding.circularProgressIndicator.show()
        }
        else {
            binding.circularProgressIndicator.visibility = View.GONE
        }
    }

    override fun onItemClick(repository: Repository) {
        navigateToRepositoryDetailsFragment(repository)
    }

    private fun navigateToRepositoryDetailsFragment(clickedRepository: Repository) {
        findNavController().navigate(
            RepositoryListFragmentDirections.actionRepositoryListFragmentToRepositoryDetailsFragment(clickedRepository)
        )
    }
}