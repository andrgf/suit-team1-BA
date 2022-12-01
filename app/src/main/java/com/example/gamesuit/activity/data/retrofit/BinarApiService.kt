package com.example.gamesuit.activity.data.retrofit


import com.example.gamesuit.activity.data.db.user.User
import okhttp3.RequestBody
import retrofit2.http.*

interface BinarApiService {
    @POST("api/v1/auth/register")
    suspend fun register(@Body body: User.AuthDetails): AuthResponse

    @POST("api/v1/auth/login")
    suspend fun login(@Body loginDetails: User.AuthDetails): AuthResponse

    @GET("api/v1/users")
    suspend fun getUser(
        @Header("Authorization") auth: String,
    ): AuthResponse

    @PUT("api/v1/users")
    suspend fun updateProfile(
        @Header("Authorization") authorization: String,
        @Body body: RequestBody
    ): AuthResponse

    @GET("api/v1/battle")
    suspend fun getHistory(
        @Header("Authorization") authorization: String
    ): HistoryResponse

    @POST("api/v1/battle")
    suspend fun postGameResult(
        @Header("Authorization") authorization: String,
        @Body gameResult: GameResult
    ): HistoryResponse


}