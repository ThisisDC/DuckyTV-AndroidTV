package com.example.duckytv.data

class DataOrException<T, Boolean, E: Exception>(
    var data: List<T> = listOf(),
    var loading: Boolean? = null,
    var e: E? = null
)