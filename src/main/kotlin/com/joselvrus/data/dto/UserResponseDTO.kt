package com.joselvrus.data.dto

import com.joselvrus.data.dto.serializer.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class UserResponseDTO(
    val id: Long,
    val name:String,
    val lastname:String,
    val email:String,
    val image :String,
    val isActive:Boolean = true,
    @Serializable(with = LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Serializable(with = LocalDateTimeSerializer::class)
    val updatedAt: LocalDateTime = LocalDateTime.now()
)
