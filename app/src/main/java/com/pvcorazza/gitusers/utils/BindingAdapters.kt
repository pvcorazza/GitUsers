package com.pvcorazza.gitusers.utils

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pvcorazza.gitusers.R
import com.pvcorazza.gitusers.adapter.GithubListAdapter
import com.pvcorazza.gitusers.network.GithubProperty
import com.squareup.picasso.Picasso

@BindingAdapter("apiProgressStatus")
fun bindProgressStatus(statusProgress: ProgressBar, status: GithubApiStatus?) {
    when (status) {
        GithubApiStatus.LOADING -> {
            statusProgress.visibility = View.VISIBLE
        }
        GithubApiStatus.ERROR -> {
            statusProgress.visibility = View.GONE
        }
        GithubApiStatus.DONE -> {
            statusProgress.visibility = View.GONE
        }
    }
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    Picasso.get().load(imgUrl).placeholder(R.drawable.loading_animation).error(
        R.drawable.ic_connection_error
    ).fit().into(imgView)
}

@BindingAdapter("listDataUsers")
fun bindRecyclerViewPokemon(recyclerView: RecyclerView, data: List<GithubProperty>?) {
    val adapter = recyclerView.adapter as GithubListAdapter
    adapter.submitList(data)
}
