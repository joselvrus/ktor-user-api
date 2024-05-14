package com.joselvrus.plugins

import com.joselvrus.data.dto.UserRequestDTO
import com.joselvrus.data.mapper.toDTO
import com.joselvrus.data.mapper.toModel
import com.joselvrus.repository.UserRepository
import com.joselvrus.repository.UserRepositoryImpl
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.flow.map

private const val ENDPOINT = "api/user"

fun Application.configureRouting() {

    val userRepository: UserRepository = UserRepositoryImpl()

    routing {
        route("/$ENDPOINT") {

            get {
                userRepository.findAll().run {
                    call.respond(HttpStatusCode.OK, this.map { it.toDTO() })
                }
            }

            get("{id}") {
                val id = call.parameters["id"]?.toLongOrNull()

                id?.let {
                    userRepository.findById(it)?.run { call.respond(HttpStatusCode.OK, this.toDTO()) }
                        ?: call.respond(HttpStatusCode.NotFound, "User not found with ID $id")
                } ?: call.respond(HttpStatusCode.BadRequest, "ID is not a number")
            }

            post {
                val user = call.receive<UserRequestDTO>()

                userRepository.save(user.toModel()).run { call.respond(HttpStatusCode.Created, this.toDTO()) }
            }

            put("{id}") {
                val id = call.parameters["id"]?.toLongOrNull()

                id?.let {
                    val user = call.receive<UserRequestDTO>()

                    userRepository.findById(it)?.let {
                        val userEdited = user.toModel().copy(id = it.id, createdAt = it.createdAt, updatedAt = it.updatedAt)
                        userRepository.save(userEdited)
                            .run { call.respond(HttpStatusCode.OK, this.toDTO()) }
                    } ?: call.respond(HttpStatusCode.NotFound, "User not found with ID $id")
                } ?: call.respond(HttpStatusCode.BadRequest, "ID is not a number")
            }

            delete("{id}") {
                val id = call.parameters["id"]?.toLongOrNull()

                id?.let {

                    userRepository.findById(it)?.let { racquet ->
                        userRepository.delete(racquet)
                            .run { call.respond(HttpStatusCode.NoContent) }
                    } ?: call.respond(HttpStatusCode.NotFound, "User not found with ID $id")
                } ?: call.respond(HttpStatusCode.BadRequest, "ID is not a number")
            }

        }
    }
}
