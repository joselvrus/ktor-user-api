package com.joselvrus.repository

import com.joselvrus.model.User
import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

class UserRepositoryImpl: UserRepository {

    private val logger = KotlinLogging.logger {}
    private val users :HashMap<Long, User> = hashMapOf(
        1L to User(1L, "Andres", "Lopez Garrido", "andreslp@gmail.com")
    )

    override suspend fun findAll(): Flow<User> = withContext(Dispatchers.IO){
        logger.debug { "findAll" }
        return@withContext users.values.asFlow()
    }

    override suspend fun findById(id: Long): User? = withContext(Dispatchers.IO) {
        logger.debug { "findById: $id" }

        return@withContext users[id]
    }

    override suspend fun save(entity: User): User = withContext(Dispatchers.IO) {
        logger.debug { "save: $entity" }

        if (entity.id == User.NEW_USER_ID) {
            create(entity)
        } else {
            update(entity)
        }
    }

    override suspend fun delete(entity: User): User? = withContext(Dispatchers.IO){
        logger.debug { "delete: $entity" }

        return@withContext users.remove(entity.id)
    }


    private fun create(entity: User): User {
        logger.debug { "create: $entity" }

        val id = users.keys.maxOrNull()?.plus(1) ?:1L
        val newEntity = entity.copy(
            id = id,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )
        users[id] = newEntity
        return newEntity
    }


    private fun update(entity: User): User {
        logger.debug { "update: $entity" }

        users[entity.id] = entity.copy(updatedAt = LocalDateTime.now())
        return entity
    }
}