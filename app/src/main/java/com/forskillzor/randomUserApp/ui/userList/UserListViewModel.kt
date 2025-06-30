package com.forskillzor.randomUserApp.ui.userList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.forskillzor.randomUserApp.ui.models.User
import com.forskillzor.randomUserApp.domain.usecases.GetUserListUseCase
import com.forskillzor.randomUserApp.ui.mapper.toUI
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
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
                _userList.value = list.map{user -> user.toUI()}
                Log.d("TAGRTRT","user list: $list")
            }
        }
    }
}