package com.takasima.posapp.data.product

data class UpdateMenuImageResponse(
    val success: Boolean,
    val message: String,
    val data: MenuImageData
)

data class MenuImageData(
    val menu_image: String
)
