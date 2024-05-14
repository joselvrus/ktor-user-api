package com.joselvrus.repository

import com.joselvrus.data.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun findAll(): Flow<User>
    suspend fun findById(id: Long): User?
    suspend fun save(entity: User): User
    suspend fun delete(entity: User): User?
}