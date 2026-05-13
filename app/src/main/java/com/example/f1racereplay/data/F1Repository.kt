package com.example.f1racereplay.data

class F1Repository(private val api: OpenF1Service) {

    suspend fun getDrivers(): List<Driver> {
        return try {
            api.getDrivers()
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getTelemetryForDriver(driverNumber: Int): List<CarLocation> {
        return try {
            // We can add filtering or caching logic here later
            api.getLocationData(driverNumber = driverNumber)
        } catch (e: Exception) {
            emptyList()
        }
    }
}