package com.example.myapplication

import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface APIService {

    @POST("loginApp/")
    fun signIn(
        @Body data:DataRequest
    ): Call<JsonElement>

}