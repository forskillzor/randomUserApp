package com.example.forskillzor.domain.repository

import com.example.forskillzor.domain.models.User

interface UserRepository {
    fun getUserList(): List<User>
}