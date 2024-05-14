package com.joselvrus.data.dto.validation

import com.joselvrus.data.dto.UserRequestDTO
import io.ktor.server.plugins.requestvalidation.*

fun RequestValidationConfig.userValidation() {
   validate<UserRequestDTO> { user ->
       val reasons = listOf(
           Pair("name", user.name),
           Pair("lastname", user.lastname),
           Pair("email", user.email)
       ).isTextEmpty()

       if (reasons.isNotEmpty()){
           ValidationResult.Invalid(reasons)
       } else {
           ValidationResult.Valid
       }
   }
}