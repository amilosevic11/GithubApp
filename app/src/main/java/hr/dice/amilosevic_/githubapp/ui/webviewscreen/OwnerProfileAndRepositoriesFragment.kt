package hr.dice.amilosevic_.githubapp.ui.webviewscreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import hr.dice.amilosevic_.githubapp.databinding.FragmentOwnerProfileAndRepositoriesBinding
import hr.dice.amilosevic_.githubapp.ui.base.BaseFragment


class OwnerProfileAndRepositoriesFragment : BaseFragment<FragmentOwnerProfileAndRepositoriesBinding>() {

    private val ownerProfileAndRepositoriesFragmentArgs: OwnerProfileAndRepositoriesFragmentArgs by navArgs()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentOwnerProfileAndRepositoriesBinding
        get() = FragmentOwnerProfileAndRepositoriesBinding::inflate

    override fun setupUi() {
        shouldShowProgressBar(true)
        with(binding) {
            mtvRepositoryName.text = ownerProfileAndRepositoriesFragmentArgs.name
            webView.loadUrl(ownerProfileAndRepositoriesFragmentArgs.url)

            webView.webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView, url: String) {
                    shouldShowProgressBar(false)
                }
            }
        }
        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding.sivBack.setOnClickListener {
            findNavController().navigateUp()
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
}