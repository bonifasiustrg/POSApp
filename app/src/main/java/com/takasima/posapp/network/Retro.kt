package com.takasima.posapp.network


import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retro {
    private const val BASE_URL = "https://pos-bdi-app.my.id/api/"
    private val gson = GsonBuilder().setLenient().create()
    fun getRetroClientInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}
/*suspend fun fetchMenuData(): GetMenuListResponse {
    val retrofit = Retro.getRetroClientInstance()
    val apiService = retrofit.create(UserApi::class.java)
    return apiService.getMenuList().body() ?: GetMenuListResponse(false, "", emptyList())
}*/


