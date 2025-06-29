package com.example.forskillzor.ui.userList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.forskillzor.domain.models.User
import com.example.forskillzor.domain.usecases.GetUserListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val getUserListUseCase: GetUserListUseCase
): ViewModel() {
    private val _userList = MutableStateFlow<List<User>>(emptyList())
    val userList = _userList.asStateFlow()


    fun getUserList(){
        viewModelScope.launch {
            getUserListUseCase().collect { list ->
                _userList.value = list
                Log.d("TAGRTRT","user list: $list")
            }
        }
    }
}