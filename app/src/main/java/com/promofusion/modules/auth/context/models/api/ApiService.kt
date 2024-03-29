package com.promofusion.modules.auth.context.models.api

import com.promofusion.modules.auth.context.models.data.BaseResponse
import com.promofusion.modules.auth.context.models.data.LoginRequest
import com.promofusion.modules.auth.context.models.data.LoginResponse
import com.promofusion.modules.auth.context.models.data.RegisterRequest
import com.promofusion.modules.auth.context.models.data.RegisterResponse
import com.promofusion.modules.auth.context.models.data.UsersResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
//import retrofit2.http.Multipart
import retrofit2.http.POST

interface ApiService {

    @GET("ping")
    fun ping(): Call<BaseResponse>

    @GET("users")
    fun getUsers(@Header("Authorization") token: String): Call<UsersResponse>

    @POST("auth/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST("auth/register")
    fun register(@Body request: RegisterRequest): Call<RegisterResponse>
}