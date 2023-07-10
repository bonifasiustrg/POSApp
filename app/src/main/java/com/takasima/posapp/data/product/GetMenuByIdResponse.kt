package com.takasima.posapp.data.product

data class GetMenuByIdList(
    val success: Boolean,
    val message: String,
    val data: List<MenuById>
)

/*data class MenuById(
    val menuId: Int,
    val menuName: String,
    val menuPrice: String,
    val menuQty: String,
    val menuType: String,
    val menuStatus: String,
    val menuImage: String,
    val menuDescription: String,
    val branchName: String
)*/
data class MenuById(
    val menu_id: Int?,
    val menu_name: String?,
    val menu_price: String?,
    val menu_qty: String?,
    val menu_type: String?,
    val menu_status: String?,
    val menu_image: String?,
    val menu_description: String?,
    val branch_name: String?
)