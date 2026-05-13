package com.example.f1racereplay.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

object RetrofitClient {
    private const val BASE_URL = "https://api.openf1.org/"

    private val json = Json {
        ignoreUnknownKeys = true // Prevents crashes if the API adds new fields
        coerceInputValues = true
    }

    val instance: OpenF1Service by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(OpenF1Service::class.java)
    }
}