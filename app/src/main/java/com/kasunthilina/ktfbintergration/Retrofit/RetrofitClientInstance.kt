package com.kasunthilina.ktfbintergration.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientInstance {
    private var retrofit:Retrofit?=null
    private val BaseURL="https://dl.dropboxusercontent.com/s/6nt7fkdt7ck0lue/"

    val retrofitInstance:Retrofit?
        get() {
            if (retrofit==null)
            {
                retrofit=retrofit2.Retrofit.Builder()
                    .baseUrl(BaseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
}