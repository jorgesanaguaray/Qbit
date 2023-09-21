package com.jorgesanaguaray.qbit.home.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import coil.load
import com.jorgesanaguaray.qbit.R
import com.jorgesanaguaray.qbit.core.domain.Post
import com.jorgesanaguaray.qbit.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var homeAdapter: HomeAdapter

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homeViewModel = ViewModelProvider(this).get()
        homeAdapter = HomeAdapter(
            onMoreClick = {
            },
            onPostClick = {
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

    private fun setupRecyclerView(posts: List<Post>) {
        homeAdapter.setPosts(posts)
        binding.mRecyclerView.adapter = homeAdapter
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

    private fun goAddScreen() {
        findNavController().navigate(R.id.action_mHomeNavigation_to_mAddNavigation)
    }

}