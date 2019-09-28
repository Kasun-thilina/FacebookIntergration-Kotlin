package com.kasunthilina.ktfbintergration.Retrofit

import retrofit2.Call
import retrofit2.http.GET

interface FetchData {
    @GET("hotels.json")
    fun getAllData():Call<ListDTO>
}