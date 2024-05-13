package com.joselvrus.model

import com.joselvrus.serializers.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class User(
    val id: Long = NEW_USER_ID,
    val name:String,
    val lastname:String,
    val email:String,
    val image :String = DEFAULT_IMAGE_URL,
    val isActive:Boolean = true,
    @Serializable(with = LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Serializable(with = LocalDateTimeSerializer::class)
    val updatedAt: LocalDateTime = LocalDateTime.now()
){
    companion object{
        const val NEW_USER_ID = -1L
        private const val DEFAULT_IMAGE_URL ="https://www.pngitem.com/pimgs/m/130-1300253_female-user-icon-png-download-user-image-color.png"
    }
}
