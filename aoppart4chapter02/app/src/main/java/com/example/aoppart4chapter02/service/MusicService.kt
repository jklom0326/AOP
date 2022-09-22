package com.example.aoppart4chapter02.service

import retrofit2.Call
import retrofit2.http.GET

interface MusicService {

    @GET("/v3/ef882dfa-8f52-4b46-a9e8-1bdf9e904973")
    fun listMusics() : Call<MusicDto>
}