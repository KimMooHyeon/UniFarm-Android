package com.song2.unifarm.Network

import com.google.gson.JsonObject
import com.song2.unifarm.Network.GET.GetDetaliedResponse
import com.song2.unifarm.Network.GET.GetProgramsResonse
import com.song2.unifarm.Network.GET.GetSearchResponse
import com.song2.unifarm.Network.POST.*
import com.song2.unifarm.Data.keywordsList
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

    @GET("/programs/{programIdx}")
    fun getDetailedResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") authorization: String,
        @Path("programIdx") programIdx: Int?
    ) : Call<GetDetaliedResponse>

    @GET("/searches")
    fun getSearchResponse(
        @Header("Content-Type") content_type: String,
        @Query("keyword") keyword: String
    ): Call<GetSearchResponse>

    @POST("/programs")
    fun postProgramResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") authorization: String,
        @Body postProgram: PostProgram
    ): Call<PostResponse>

    @GET("/programs/major")
    fun getProgramsMajorResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") authorization: String
    ): Call<GetProgramsResonse>


    @GET("/programs/keyword")
    fun getProgramsKeywordResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") authorization: String
    ): Call<GetProgramsResonse>

    @GET("/users/check")
    fun getCheckEmailResponse(
        @Header("Content-Type") content_type: String,
        @Query("email") email: String
    ): Call<getCheckEmailResponse>

}