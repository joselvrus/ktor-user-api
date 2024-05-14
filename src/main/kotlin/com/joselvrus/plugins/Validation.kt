package com.joselvrus.plugins

import com.joselvrus.data.dto.validation.userValidation
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*

fun Application.configureValidation() {
    install(RequestValidation) {
        userValidation()
    }
}