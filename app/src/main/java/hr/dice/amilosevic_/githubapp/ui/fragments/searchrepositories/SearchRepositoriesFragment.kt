package hr.dice.amilosevic_.githubapp.ui.fragments.searchrepositories

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
import hr.dice.amilosevic_.githubapp.ui.baseFragment.BaseFragment
import org.koin.android.ext.android.inject

class SearchRepositoriesFragment : BaseFragment<FragmentSearchRepositoriesBinding>(), PopupMenu.OnMenuItemClickListener {

    private val searchRepositoriesViewModel: SearchRepositoriesViewModel by inject()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchRepositoriesBinding
        get() = FragmentSearchRepositoriesBinding::inflate

    override fun setupUi() {
        getRecentSearches()
        setOnClickListeners()
        initTextWatcher()
        observeData()
    }

    private fun getRecentSearches() {
        searchRepositoriesViewModel.getAllRecentSearches()
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
    }

    private fun initAutocompleteItems(strings: ArrayList<String>) {
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, strings)
        binding.textInputEditText.setAdapter(adapter)
    }

    private fun initTextWatcher() {
        binding.textInputEditText.doAfterTextChanged {
            if (it != null)
                binding.btnSearch.isEnabled = it.count() >= 2
        }
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        initAlertDialog()
        return true
    }

    private fun initAlertDialog() {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle(getString(R.string.alert_dialog_title))
        alertDialogBuilder.setPositiveButton(getString(R.string.ok_label)) { _, _ ->
            searchRepositoriesViewModel.deleteAllRecentSearches()
        }
        alertDialogBuilder.setNegativeButton(getString(R.string.cancel_label)) { dialog, _ ->
            dialog.dismiss()
        }
        alertDialogBuilder.show()
    }

    private fun goToRepositoryList(query: String) {
        findNavController().navigate(
            SearchRepositoriesFragmentDirections.actionSearchRepositoriesFragmentToRepositoryListFragment(query)
        )
    }
}