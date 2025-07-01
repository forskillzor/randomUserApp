package com.forskillzor.randomUserApp.ui.userList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.forskillzor.randomUserApp.data.repository.NetworkException
import com.forskillzor.randomUserApp.data.repository.RepositoryException
import com.forskillzor.randomUserApp.ui.models.User
import com.forskillzor.randomUserApp.domain.usecases.GetUserListUseCase
import com.forskillzor.randomUserApp.domain.usecases.GetUserListUseCaseException
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
    private val getUserListUseCase: GetUserListUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<UIState>(UIState.Loading)
    val state = _state.asStateFlow()


    fun getUserList() {
        viewModelScope.launch {
            try {
                _state.value = UIState.Loading
                getUserListUseCase().collect { users ->
                    _state.value = UIState.Success(users.map { it.toUI() })
                }
            } catch (e: Exception) {
                _state.value = UIState.Error(
                    when(e) {
                        is NetworkException -> "Server unavailable"
                        is RepositoryException -> "Local data failed"
                        is GetUserListUseCaseException -> "Error during data processing"
                        else -> "Unknown error"
                    }
                )

            }
        }
    }
}