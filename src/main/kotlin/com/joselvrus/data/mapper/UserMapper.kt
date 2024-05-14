package com.joselvrus.data.mapper

import com.joselvrus.data.dto.UserRequestDTO
import com.joselvrus.data.dto.UserResponseDTO
import com.joselvrus.data.model.User

fun User.toDTO() = UserResponseDTO(
    id = id,
    name = name,
    lastname = lastname,
    email = email,
    image = image,
    isActive = isActive,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun UserRequestDTO.toModel() = User(
    name = name,
    lastname = lastname,
    email = email,
    image = image,
    isActive = isActive
)