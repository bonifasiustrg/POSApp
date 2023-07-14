package com.takasima.posapp.data.branch

data class GetBranchListResponse(
    val success: Boolean,
    val message: String,
    val data: List<Branch>
)

data class Branch(
    // Definisikan atribut-atribut menu yang sesuai dengan struktur JSON
    val branch_id: Int,
//    val owner_id: String,
    val branch_name: String,
    val branch_address: String,
    val branch_phone: String,
    val branch_email: String,
    val branch_status: String
)