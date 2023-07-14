package com.takasima.posapp.data.branch

data class CreateStaffResponse(
    val success: Boolean,
    val message: String,
    val data: StaffData
)

data class StaffData(
    val user: StaffDetail
)

data class StaffDetail(
    val name: String,
    val email: String,
    val updated_at: String,
    val created_at: String,
    val id: Int,
    val roles: List<Role>
)

data class Role(
    val id: Int,
    val name: String,
    val guard_name: String,
    val created_at: String,
    val updated_at: String,
    val pivot: RolePivot
)

data class RolePivot(
    val model_id: String,
    val role_id: String,
    val model_type: String
)
