package com.pvcorazza.gitusers.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.pvcorazza.gitusers.adapter.GithubListAdapter
import com.pvcorazza.gitusers.adapter.UserListener
import com.pvcorazza.gitusers.databinding.FragmentHomeBinding
import com.pvcorazza.gitusers.viewmodel.HomeViewModel

/**
 * Fragment for the opening screen
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Initialize a ViewModel
        val viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        setHasOptionsMenu(true)

        val binding = FragmentHomeBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the GithubViewModel
        binding.viewModel = viewModel

        val clickListener = UserListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailsFragment(
                    it.id
                )
            )
        }

        binding.recyclerUsers.adapter = GithubListAdapter(clickListener)

        return binding.root

    }
}
