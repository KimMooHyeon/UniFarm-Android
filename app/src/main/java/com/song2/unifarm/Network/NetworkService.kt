package com.song2.unifarm.Network

import com.google.gson.JsonObject
import com.song2.unifarm.Data.keywordsList
import com.song2.unifarm.Data.userData
import com.song2.unifarm.Data.userData2
import com.song2.unifarm.Network.GET.getCheckEmailResponse
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
        @Body() user: userData2,
        @Body() keywords : keywordsList?
    ): Call<PostSignupResponse>

    @GET("/users/check")
    fun getCheckEmailResponse(
        @Header("Content-Type") content_type: String,
        @Query("email") email: String
    ): Call<getCheckEmailResponse>

}