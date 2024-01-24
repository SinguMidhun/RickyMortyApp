package com.midhun.rickymortyapp.utils

import com.midhun.rickymortyapp.repository.room.FavDatabase
import com.midhun.rickymortyapp.utils.Constants.baseUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkUtils {

    private var retrofit: Retrofit? = null

    fun getRetrofitInstance(): Retrofit {
        if (retrofit == null) {
            synchronized(this) {
                retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(baseUrl)
                    .build()
            }
        }
        return retrofit!!
    }

}