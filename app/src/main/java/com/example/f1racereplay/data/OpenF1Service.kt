package com.example.f1racereplay.data

import retrofit2.http.GET
import retrofit2.http.Query

interface OpenF1Service {
    // Fetches car positions. We use session_key=latest to get the most recent race.
    @GET("v1/location")
    suspend fun getLocationData(
        @Query("session_key") sessionKey: String = "latest",
        @Query("driver_number") driverNumber: Int? = null
    ): List<CarLocation>

    @GET("v1/drivers")
    suspend fun getDrivers(
        @Query("session_key") sessionKey: String = "latest"
    ): List<Driver>
}