package com.joselvrus.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserRequestDTO(
    val name:String? = null,
    val lastname:String? = null,
    val email:String? = null,
    val image :String? = null,
    val isActive:Boolean? = null
)
