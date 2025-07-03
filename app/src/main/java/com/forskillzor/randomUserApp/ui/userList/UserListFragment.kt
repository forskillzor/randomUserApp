package com.forskillzor.randomUserApp.ui.userList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.forskillzor.randomUserApp.R
import com.forskillzor.randomUserApp.databinding.FragmentUserListBinding
import com.forskillzor.randomUserApp.ui.models.User
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserListFragment : Fragment() {
    private lateinit var binding: FragmentUserListBinding
    private lateinit var recyclerListAdapter: UserListAdapter
    private val viewModel: UserListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserListBinding.inflate(layoutInflater, container, false)
        binding.swiperefresh.setOnRefreshListener {
            lifecycleScope.launch {
                viewModel.refreshUserList()
                binding.swiperefresh.isRefreshing = false
            }
        }
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
            viewModel.state.collect { state ->
                when (state) {
                    is UIState.Loading -> onLoading()
                    is UIState.Error -> onError(state.message)
                    is UIState.Success -> onSuccess(state.data)
                }
            }
        }
        return binding.root
    }

    fun onLoading() {
        binding.progressCircular.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.GONE
    }

    fun onSuccess(data: List<User>) {
        recyclerListAdapter.submitList(data)
        binding.recyclerView.postDelayed({
            binding.progressCircular.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
            val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.recycler_fade_in_scale)
            binding.recyclerView.startAnimation(animation)
        }, 100)
    }

    fun onError(message: String) {
        binding.progressCircular.visibility = View.GONE
        viewModel.getUserList()
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG)
            .setAction("Retry") { viewModel.refreshUserList()}
            .show()
    }
}