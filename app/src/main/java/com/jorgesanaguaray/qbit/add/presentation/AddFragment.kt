package com.jorgesanaguaray.qbit.add.presentation

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.jorgesanaguaray.qbit.R
import com.jorgesanaguaray.qbit.core.domain.Post
import com.jorgesanaguaray.qbit.databinding.FragmentAddBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddFragment : Fragment() {

    private lateinit var addViewModel: AddViewModel

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addViewModel = ViewModelProvider(this).get()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mAdd.setOnClickListener {
            validateCredentials()
        }

    }

    private fun validateCredentials() {

        when {

            TextUtils.isEmpty(binding.mEditTextTitle.text.toString()) -> {
                binding.mEditTextTitle.error = resources.getString(R.string.enter_a_title)
            }

            TextUtils.isEmpty(binding.mEditTextDescription.text.toString()) -> {
                binding.mEditTextDescription.error = resources.getString(R.string.enter_a_description)
            }

            TextUtils.isEmpty(binding.mEditTextCategory.text.toString()) -> {
                binding.mEditTextCategory.error = resources.getString(R.string.enter_a_category)
            }

            TextUtils.isEmpty(binding.mEditTextCreatedBy.text.toString()) -> {
                binding.mEditTextCreatedBy.error = resources.getString(R.string.enter_a_name)
            }

            TextUtils.isEmpty(binding.mEditTextReadingTime.text.toString()) -> {
                binding.mEditTextReadingTime.error = resources.getString(R.string.enter_the_reading_time)
            }

            TextUtils.isEmpty(binding.mEditTextDate.text.toString()) -> {
                binding.mEditTextDate.error = resources.getString(R.string.enter_a_date)
            }

            TextUtils.isEmpty(binding.mEditTextThumbnailLink.text.toString()) -> {
                binding.mEditTextThumbnailLink.error = resources.getString(R.string.enter_the_thumbnail_link)
            }

            TextUtils.isEmpty(binding.mEditTextImageLink.text.toString()) -> {
                binding.mEditTextImageLink.error = resources.getString(R.string.enter_the_image_link)
            }

            TextUtils.isEmpty(binding.mEditTextPostLink.text.toString()) -> {
                binding.mEditTextPostLink.error = resources.getString(R.string.enter_the_post_link)
            }

            else -> {

                insertPost()

            }

        }

    }

    private fun insertPost() {

        val post = Post(
            id = null,
            title = binding.mEditTextTitle.text.toString().trim(),
            description = binding.mEditTextDescription.text.toString().trim(),
            category = binding.mEditTextCategory.text.toString().trim(),
            createdBy = binding.mEditTextCreatedBy.text.toString().trim(),
            readingTime = binding.mEditTextReadingTime.text.toString().trim(),
            date = binding.mEditTextDate.text.toString().trim(),
            thumbnailLink = binding.mEditTextThumbnailLink.text.toString().trim(),
            imageLink = binding.mEditTextImageLink.text.toString().trim(),
            postLink = binding.mEditTextPostLink.text.toString().trim()
        )

        addViewModel.insertPost(post)
        clearEditTexts()
        Toast.makeText(context, resources.getString(R.string.the_post_has_been_added_successfully), Toast.LENGTH_SHORT).show()

    }

    private fun clearEditTexts() {

        binding.mEditTextTitle.setText("")
        binding.mEditTextDescription.setText("")
        binding.mEditTextCategory.setText("")
        binding.mEditTextCreatedBy.setText("")
        binding.mEditTextReadingTime.setText("")
        binding.mEditTextDate.setText("")
        binding.mEditTextThumbnailLink.setText("")
        binding.mEditTextImageLink.setText("")
        binding.mEditTextPostLink.setText("")

    }

}