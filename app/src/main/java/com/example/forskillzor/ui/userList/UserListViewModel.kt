package com.example.forskillzor.ui.userList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.forskillzor.domain.models.User
import com.example.forskillzor.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val repository: UserRepository
): ViewModel() {
    private val _userList = MutableStateFlow<List<User>>(emptyList())
    val userList = _userList.asStateFlow()


    fun getUserList(){
        viewModelScope.launch {
            repository.getUserList().collect { list ->
                _userList.value = list
            }
        }
    }
}