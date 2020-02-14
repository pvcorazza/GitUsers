package com.pvcorazza.gitusers.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pvcorazza.gitusers.R
import com.pvcorazza.gitusers.adapter.GithubListAdapter
import com.pvcorazza.gitusers.network.GithubProperty
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    Picasso.get().load(imgUrl).placeholder(R.drawable.loading_animation).error(
        R.drawable.ic_connection_error
    ).fit().into(imgView)
}

@BindingAdapter("listDataUsers")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<GithubProperty>?) {
    val adapter = recyclerView.adapter as GithubListAdapter
    adapter.submitList(data)
}
