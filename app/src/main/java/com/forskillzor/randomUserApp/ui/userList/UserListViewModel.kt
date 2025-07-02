package com.forskillzor.randomUserApp.ui.userList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.forskillzor.randomUserApp.ui.models.User
import com.forskillzor.randomUserApp.domain.usecases.GetUserListUseCase
import com.forskillzor.randomUserApp.domain.usecases.RefreshUserListUseCase
import com.forskillzor.randomUserApp.ui.mapper.toUI
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


sealed class UIState {
    object Loading : UIState()
    data class Success(val data: List<User>) : UIState()
    data class Error(val message: String) : UIState()
}

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val getUserListUseCase: GetUserListUseCase,
    private val refreshUserListUseCase: RefreshUserListUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<UIState>(UIState.Loading)
    val state = _state.asStateFlow()

    init {
        getUserList()
    }

    fun getUserList() {
        viewModelScope.launch {
            getUserListUseCase().collect { userList ->
                _state.value = UIState.Success(userList.map { it.toUI() })
            }
        }
    }

    fun refreshUserList() {
        viewModelScope.launch {
            _state.value = UIState.Loading
            try {
                refreshUserListUseCase()
            } catch (e: Exception) {
                _state.value = UIState.Error(e.message ?: "Refresh error")
            }
        }
    }
}