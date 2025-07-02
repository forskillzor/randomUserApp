package com.forskillzor.randomUserApp.ui.userList

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.forskillzor.randomUserApp.databinding.ItemUserBinding
import com.forskillzor.randomUserApp.ui.models.User
import com.forskillzor.randomUserApp.ui.userList.UserListAdapter.UserViewHolder

class UserListAdapter(
    private val onItemClick: (user: User) -> Unit
) : ListAdapter<User, UserViewHolder>(DIFF_CALLBACK) {
    private var lastPosition = -1
    object DIFF_CALLBACK : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(
            oldItem: User,
            newItem: User
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: User,
            newItem: User
        ): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: UserViewHolder,
        position: Int
    ) {
        val item = getItem(position)?: return
        holder.bind(item)
    }

    inner class UserViewHolder(val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(user: User) {
            with(binding) {
                first.text = user.firstName
                second.text = user.lastName
                country.text = user.country
                city.text = user.city
                phone.text = "+${user.phone}"
                email.text = user.email
                Glide.with(avatar.context)
                    .load(user.pictureMedium)
                    .circleCrop()
                    .into(avatar)
                root.setOnClickListener {
                    onItemClick(user)
                }
            }
        }
    }
}