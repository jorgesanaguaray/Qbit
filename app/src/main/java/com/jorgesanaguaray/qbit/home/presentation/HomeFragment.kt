package com.jorgesanaguaray.qbit.home.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.text.HtmlCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import coil.load
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.jorgesanaguaray.qbit.Constants.Companion.KEY_POST_ID
import com.jorgesanaguaray.qbit.R
import com.jorgesanaguaray.qbit.core.domain.Post
import com.jorgesanaguaray.qbit.databinding.DialogDetailBinding
import com.jorgesanaguaray.qbit.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var homeAdapter: HomeAdapter

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var bottomSheetDialog: BottomSheetDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homeViewModel = ViewModelProvider(this).get()
        homeAdapter = HomeAdapter(
            onMoreClick = {
                setupDetailDialog(it)
            },
            onPostClick = {
                openInApp(it)
            }
        )

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.mHeader.load(R.drawable.header) {
            placeholder(R.drawable.progress_animation)
            error(R.drawable.ic_error)
            crossfade(true)
            crossfade(400)
        }

        homeViewModel.posts.observe(viewLifecycleOwner) {
            setupRecyclerView(it)
        }

        homeViewModel.isLoading.observe(viewLifecycleOwner) {
            setupViewsVisibility(it)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mFloatingButton.setOnClickListener {
            goAddScreen()
        }

    }

    override fun onPause() {
        super.onPause()

        hideDetailDialog()

    }

    private fun setupRecyclerView(posts: List<Post>) {
        homeAdapter.setPosts(posts)
        binding.mRecyclerView.adapter = homeAdapter
    }

    private fun setupDetailDialog(post: Post) {

        bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme)
        val binding: DialogDetailBinding = DialogDetailBinding.inflate(layoutInflater)

        binding.apply {

            mTitle.text = post.title
            mImage.load(post.imageLink) {
                placeholder(R.drawable.progress_animation)
                error(R.drawable.ic_error)
                crossfade(true)
                crossfade(400)
            }
            mCategory.text = HtmlCompat.fromHtml("<b>" + resources.getString(R.string.category_) + "</b>" + " " + post.category, HtmlCompat.FROM_HTML_MODE_LEGACY)
            mCreatedBy.text = HtmlCompat.fromHtml("<b>" + resources.getString(R.string.created_by_) + "</b>" + " " + post.createdBy, HtmlCompat.FROM_HTML_MODE_LEGACY)
            mDescription.text = post.description

            mClose.setOnClickListener {
                hideDetailDialog()
            }
            mSharePost.setOnClickListener {
                sharePost(post.postLink)
            }
            mOpenInApp.setOnClickListener {
                openInApp(post.id!!)
                hideDetailDialog()
            }
            mOpenInBrowser.setOnClickListener {
                openInBrowser(post.postLink)
                hideDetailDialog()
            }

        }

        bottomSheetDialog?.setContentView(binding.root)
        bottomSheetDialog?.show()

    }

    private fun hideDetailDialog() {
        bottomSheetDialog?.dismiss()
    }

    private fun setupViewsVisibility(isLoading: Boolean) {

        if (isLoading) {
            binding.mProgressBar.visibility = View.VISIBLE
            binding.mNestedScroll.visibility = View.GONE
        } else {
            binding.mProgressBar.visibility = View.GONE
            binding.mNestedScroll.visibility = View.VISIBLE
        }

    }

    private fun sharePost(postLink: String) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, postLink)
        startActivity(Intent.createChooser(shareIntent, resources.getString(R.string.share)))
    }

    private fun openInApp(postId: Int) {
        val bundle = bundleOf(KEY_POST_ID to postId)
        findNavController().navigate(R.id.action_mHomeNavigation_to_mDetailNavigation, bundle)
    }

    private fun openInBrowser(postLink: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(postLink)
        startActivity(intent)
    }

    private fun goAddScreen() {
        findNavController().navigate(R.id.action_mHomeNavigation_to_mAddNavigation)
    }

}