package com.takasima.posapp.ui.screen.owner.branch

data class GetBranchListResponse(
    val success: Boolean,
    val message: String,
    val data: List<Branch>
)

data class Branch(
    // Definisikan atribut-atribut menu yang sesuai dengan struktur JSON
    val branch_name: String,
    val branch_address: String,
    val branch_phone: String,
    val branch_email: String,
    val branch_status: String
)