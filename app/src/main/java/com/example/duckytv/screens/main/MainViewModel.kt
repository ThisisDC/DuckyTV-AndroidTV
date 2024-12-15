package com.example.duckytv.screens.main

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.duckytv.data.DataOrException
import com.example.duckytv.models.Channel
import com.example.duckytv.repository.ChannelsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: ChannelsRepository)
    : ViewModel() {

    private val _countries = MutableStateFlow<Set<String>>(setOf())
    val countries = _countries.asStateFlow()

    suspend fun getChannels()
            : DataOrException<Channel, Boolean, Exception> {

        val contriesSet = mutableSetOf("")
        repository.getChannels().data.forEach{
            contriesSet.add(it.name)
        }
        _countries.value = contriesSet

        return repository.getChannels()
    }
}