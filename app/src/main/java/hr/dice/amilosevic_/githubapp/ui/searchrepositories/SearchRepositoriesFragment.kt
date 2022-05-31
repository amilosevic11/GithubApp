package hr.dice.amilosevic_.githubapp.ui.searchrepositories

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.PopupMenu
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import hr.dice.amilosevic_.githubapp.R
import hr.dice.amilosevic_.githubapp.databinding.FragmentSearchRepositoriesBinding
import hr.dice.amilosevic_.githubapp.helpers.initPopupMenu
import hr.dice.amilosevic_.githubapp.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchRepositoriesFragment : BaseFragment<FragmentSearchRepositoriesBinding>(), PopupMenu.OnMenuItemClickListener {

    private val searchRepositoriesViewModel: SearchRepositoriesViewModel by viewModel()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchRepositoriesBinding
        get() = FragmentSearchRepositoriesBinding::inflate

    override fun setupUi() {
        setOnClickListeners()
        initTextWatcher()
        observeData()
    }

    private fun setOnClickListeners() {
        binding.sivMenu.setOnClickListener {
            initPopupMenu(it, requireContext(), this)
        }
        binding.btnSearch.setOnClickListener {
            searchRepositoriesViewModel.saveQuery(binding.textInputEditText.text.toString())
            goToRepositoryList(binding.textInputEditText.text.toString())
        }
        binding.textInputEditText.onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->
            goToRepositoryList(parent.getItemAtPosition(position).toString())
        }
    }

    private fun observeData() {
        searchRepositoriesViewModel.recentSearches.observe(viewLifecycleOwner) {
            initAutocompleteItems(it)
        }
        searchRepositoriesViewModel.buttonEnabled.observe(viewLifecycleOwner) {
            binding.btnSearch.isEnabled = it
        }
    }

    private fun initAutocompleteItems(recentSearches: List<String>) {
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, recentSearches)
        binding.textInputEditText.setAdapter(adapter)
    }

    private fun initTextWatcher() {
        binding.textInputEditText.doAfterTextChanged {
            if (it != null) {
                searchRepositoriesViewModel.getRecentSearchesByKeyword(it.toString())
                searchRepositoriesViewModel.shouldEnableButton(it.count())
            }
        }
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        initAlertDialog()
        return true
    }

    private fun initAlertDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.alert_dialog_title))
            .setPositiveButton(getString(R.string.ok_label)) { _, _ ->
                searchRepositoriesViewModel.deleteAllRecentSearches()
            }
            .setNegativeButton(getString(R.string.cancel_label)) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun goToRepositoryList(query: String) {
        findNavController().navigate(
            SearchRepositoriesFragmentDirections.actionSearchRepositoriesFragmentToRepositoryListFragment(query)
        )
    }
}