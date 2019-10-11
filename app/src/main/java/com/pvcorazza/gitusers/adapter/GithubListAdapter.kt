package com.pvcorazza.gitusers.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pvcorazza.gitusers.databinding.ListItemUserBinding
import com.pvcorazza.gitusers.network.GithubProperty


// Adapter for RecyclerView of Users of Github

class GithubListAdapter(private val clickListener: UserListener) :
    ListAdapter<GithubProperty, GithubListAdapter.GithubPropertyViewHolder>(
        DiffCallback
    ) {

    class GithubPropertyViewHolder(private var binding: ListItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: UserListener, githubProperty: GithubProperty) {
            binding.githubProperty = githubProperty
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<GithubProperty>() {
        override fun areItemsTheSame(oldItem: GithubProperty, newItem: GithubProperty): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GithubProperty, newItem: GithubProperty): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubPropertyViewHolder {
        return GithubPropertyViewHolder(
            ListItemUserBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: GithubPropertyViewHolder, position: Int) {
        val githubProperty = getItem(position)
        holder.bind(clickListener, githubProperty)
    }


}

// Handle click listeners
class UserListener(val clickListener: (typeId: GithubProperty) -> Unit) {

    fun onClick(type: GithubProperty) {
        clickListener(type)
    }

}