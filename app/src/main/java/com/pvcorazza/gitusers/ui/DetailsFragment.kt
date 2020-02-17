package com.pvcorazza.gitusers.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.pvcorazza.gitusers.databinding.FragmentDetailsBinding
import com.pvcorazza.gitusers.viewmodel.DetailsViewModel
import com.pvcorazza.gitusers.viewmodel.DetailsViewModelFactory

/**
 * Fragment for the opening screen
 */

class DetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)

        // Get "id" from previous fragment
        val id = arguments?.let { DetailsFragmentArgs.fromBundle(it).id }

        // Initialize a ViewModel
        val viewModel = ViewModelProvider(this, DetailsViewModelFactory(id!!)).get(
            DetailsViewModel::class.java
        )

        //Inflate layout for DataBinding
        val binding = FragmentDetailsBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the DetailsViewModel
        binding.viewModel = viewModel

        return binding.root

    }
}
