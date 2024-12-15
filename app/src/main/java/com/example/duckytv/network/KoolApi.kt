package com.example.duckytv.network

import com.example.duckytv.models.Channel
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface KoolApi {
    @GET(value = "channels")
    suspend fun getChannels(
    ): List<Channel>
}