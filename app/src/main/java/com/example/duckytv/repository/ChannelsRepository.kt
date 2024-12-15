package com.example.duckytv.repository

import android.util.Log
import com.example.duckytv.data.DataOrException
import com.example.duckytv.models.Channel
import com.example.duckytv.network.KoolApi
import javax.inject.Inject

class ChannelsRepository @Inject constructor(private val api: KoolApi) {
    suspend fun getChannels(): DataOrException<Channel, Boolean, Exception> {
        val response = try {
            api.getChannels()
        } catch (e: Exception){
            return DataOrException(e = e)
        }
        return DataOrException(data = response)
    }

}