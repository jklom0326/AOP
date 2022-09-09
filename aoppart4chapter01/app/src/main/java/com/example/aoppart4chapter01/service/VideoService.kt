package com.example.aoppart4chapter01.service

import com.example.aoppart4chapter01.model.VideoDto
import retrofit2.Call
import retrofit2.http.GET

interface VideoService {

    @GET("v3/069f3065-a50c-4b73-9701-3aa30b473e40")
    fun listVideos(): Call<VideoDto>
}