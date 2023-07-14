package com.takasima.posapp.data.branch

data class GetStaffResponse(
    val success: Boolean,
    val message: String,
    val data: List<Staff>
)

data class Staff(
    val staff_id: Int,
    val user_id: String,
    val branch_id: String,
    val no_hp: String,
    val address: String,
    val created_at: String,
    val updated_at: String,
    val user: User
)

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val email_verified_at: String?,
    val created_at: String,
    val updated_at: String
)

