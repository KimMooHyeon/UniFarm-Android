package com.song2.unifarm.Network

import com.google.gson.JsonObject
import com.song2.unifarm.Network.POST.PostLoginResponse
import com.song2.unifarm.Network.POST.PostSignupResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface NetworkService {

    @POST("/users/login")
    fun postLoginResponse(
        @Header("Content-Type") content_type: String,
        @Body() body: JsonObject
    ): Call<PostLoginResponse>
    @POST("/users/signup")
    fun postSignupResponse(
        @Header("Content-Type") content_type: String,
        @Body() body: JsonObject
    ): Call<PostSignupResponse>
}