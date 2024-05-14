package com.joselvrus.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserRequestDTO(
    val name:String,
    val lastname:String,
    val email:String,
    val image :String,
    val isActive:Boolean = true
)
