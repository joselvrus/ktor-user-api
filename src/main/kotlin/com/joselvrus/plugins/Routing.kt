package com.joselvrus.plugins

import com.joselvrus.model.User
import com.joselvrus.repository.UserRepository
import com.joselvrus.repository.UserRepositoryImpl
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private const val ENDPOINT = "api/user"

fun Application.configureRouting() {

    val userRepository: UserRepository = UserRepositoryImpl()

    routing {
        route("/$ENDPOINT") {

            get {
                userRepository.findAll().run {
                    call.respond(HttpStatusCode.OK, this)
                }
            }

            get("{id}") {
                val id = call.parameters["id"]?.toLongOrNull()

                id?.let {
                    userRepository.findById(it)?.run { call.respond(HttpStatusCode.OK, this) }
                        ?: call.respond(HttpStatusCode.NotFound, "User not found with ID $id")
                } ?: call.respond(HttpStatusCode.BadRequest, "ID is not a number")
            }

            post {
                val user = call.receive<User>()

                userRepository.save(user).run { call.respond(HttpStatusCode.Created, this) }
            }

            put("{id}") {
                val id = call.parameters["id"]?.toLongOrNull()

                id?.let {
                    val user = call.receive<User>()

                    userRepository.findById(it)?.let {
                        userRepository.save(user)
                            .run { call.respond(HttpStatusCode.OK, this) }
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
