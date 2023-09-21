package com.jorgesanaguaray.qbit.detail.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.jorgesanaguaray.qbit.Constants.Companion.KEY_POST_ID
import com.jorgesanaguaray.qbit.R
import com.jorgesanaguaray.qbit.core.domain.Post
import com.jorgesanaguaray.qbit.databinding.FragmentDetailBinding
import com.monstertechno.adblocker.AdBlockerWebView
import com.monstertechno.adblocker.util.AdBlocker
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var detailViewModel: DetailViewModel
    private lateinit var navController: NavController

    private var postId = 0

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        detailViewModel = ViewModelProvider(this).get()
        navController = findNavController()

        postId = arguments?.getInt(KEY_POST_ID)!!
        detailViewModel.getPostById(postId)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        detailViewModel.post.observe(viewLifecycleOwner) {

            setupTopBar(it)
            setupWebView(it)

            binding.mSharePost.setOnClickListener { _ ->
                sharePost(it.postLink)
            }

            binding.mOpenInBrowser.setOnClickListener { _ ->
                openInBrowser(it.postLink)
            }

        }
        detailViewModel.isLoading.observe(viewLifecycleOwner) {
            setupViewsVisibility(it)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mBack.setOnClickListener {
            navController.navigateUp()
        }

        binding.mSwipeRefresh.setOnRefreshListener {
            detailViewModel.getPostById(postId)
            binding.mSwipeRefresh.isRefreshing = false
        }

    }

    private fun setupTopBar(post: Post) {

        binding.mTitle.text = post.title

    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView(post: Post) {

        AdBlockerWebView.init(requireContext()).initializeWebView(binding.mWebView)
        binding.mWebView.settings.javaScriptEnabled = true
        binding.mWebView.loadUrl(post.postLink)
        binding.mWebView.isHorizontalScrollBarEnabled = false
        binding.mWebView.webViewClient = Browser()

    }

    private fun setupViewsVisibility(isLoading: Boolean) {

        if (isLoading) {
            binding.mProgressBar.visibility = View.VISIBLE
            binding.mWebView.visibility = View.GONE
        } else {
            binding.mProgressBar.visibility = View.GONE
            binding.mWebView.visibility = View.VISIBLE
        }

    }

    private fun sharePost(articleLink: String) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, articleLink)
        startActivity(Intent.createChooser(shareIntent, resources.getString(R.string.share)))
    }

    private fun openInBrowser(postLink: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(postLink)
        startActivity(intent)
    }

}

private class Browser : WebViewClient() {

    override fun shouldInterceptRequest(webView: WebView, request: WebResourceRequest): WebResourceResponse? {

        val url = request.url.toString()

        return if (AdBlockerWebView.blockAds(webView, url)) {
            AdBlocker.createEmptyResource()
        } else {
            super.shouldInterceptRequest(webView, request)
        }

    }

}