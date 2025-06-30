package com.forskillzor.randomUserApp.ui.userList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.forskillzor.randomUserApp.databinding.FragmentUserListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserListFragment : Fragment() {
    private lateinit var binding: FragmentUserListBinding
    private lateinit var recyclerListAdapter: UserListAdapter
    private val viewModel: UserListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (viewModel.userList.value.isEmpty())
            viewModel.getUserList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserListBinding.inflate(layoutInflater, container, false)
        // todo add adapter here
        binding.recyclerView.apply {

            recyclerListAdapter = UserListAdapter { user ->
                UserListFragmentDirections.actionUserListFragmentToUserDetailsFragment(user)
                    .let { binding.root.findNavController().navigate(it) }
            }
            adapter = recyclerListAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }
        lifecycleScope.launch {
            viewModel.userList.collect { list ->
                recyclerListAdapter.submitList(list)
            }
        }
        return binding.root
    }
}