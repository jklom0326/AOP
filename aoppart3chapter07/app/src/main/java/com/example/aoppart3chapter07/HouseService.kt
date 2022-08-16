package com.example.aoppart3chapter07

import retrofit2.Call
import retrofit2.http.GET

interface HouseService {
    @GET("v3/be724c87-4b08-43dc-8bf3-b117e5fcd3b3")
    fun getHouseList(): Call<HouseDTO>
}