package com.joselvrus.data.model

import java.time.LocalDateTime

data class User(
    val id: Long = NEW_USER_ID,
    val name:String,
    val lastname:String,
    val email:String,
    val image :String = DEFAULT_IMAGE_URL,
    val isActive:Boolean = true,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
){
    companion object{
        const val NEW_USER_ID = -1L
        private const val DEFAULT_IMAGE_URL ="https://www.pngitem.com/pimgs/m/130-1300253_female-user-icon-png-download-user-image-color.png"
    }
}
