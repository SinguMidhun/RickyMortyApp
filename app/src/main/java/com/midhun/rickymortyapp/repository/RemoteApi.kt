package com.midhun.rickymortyapp.repository

import com.midhun.rickymortyapp.models.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteApi {

    @GET("character/")
    suspend fun getListOfCharacters(@Query("page") page : Int) : Response<CharacterResponse>

}