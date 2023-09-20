package com.jorgesanaguaray.qbit.home.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.jorgesanaguaray.qbit.R
import com.jorgesanaguaray.qbit.core.domain.Post
import com.jorgesanaguaray.qbit.databinding.ItemHomeBinding

/**
 * Created by Jorge Sanaguaray
 */

class HomeAdapter(

    private val onMoreClick: (Post) -> Unit,
    private val onPostClick: (Int) -> Unit

) : RecyclerView.Adapter<HomeAdapter.MyHomeViewHolder>() {

    private var posts: List<Post> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHomeViewHolder {
        return MyHomeViewHolder(ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyHomeViewHolder, position: Int) {

        val post = posts[position]

        holder.binding.apply {

            mTitle.text = post.title
            mImage.load(post.imageLink) {
                placeholder(R.drawable.progress_animation)
                error(R.drawable.ic_error)
                crossfade(true)
                crossfade(400)
            }
            mDescription.text = post.description

            mMore.setOnClickListener {
                onMoreClick(post)
            }
            mPost.setOnClickListener {
                onPostClick(post.id!!)
            }

        }

    }

    override fun getItemCount(): Int {
        return posts.size
    }

    class MyHomeViewHolder(val binding: ItemHomeBinding) : RecyclerView.ViewHolder(binding.root)

    fun setPosts(posts: List<Post>) {
        this.posts = posts
    }

}