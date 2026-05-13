package com.example.f1racereplay.ui

import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.f1racereplay.data.CarLocation
import com.example.f1racereplay.data.F1Repository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.abs

class ReplayViewModel(private val repository: F1Repository) : ViewModel() {

    // Bounds for track scaling
    var minX = 0.0
    var maxX = 0.0
    var minY = 0.0
    var maxY = 0.0

    // Static track outline
    val trackPathPoints = mutableListOf<CarLocation>()

    // Synchronized grid positions: Map<DriverNumber, CurrentLocation>
    val gridPositions = mutableStateMapOf<Int, CarLocation>()

    fun startMultiCarReplay(driverIds: List<Int>) {
        viewModelScope.launch {
            val allTelemetry = mutableMapOf<Int, List<CarLocation>>()

            // 1. Fetch data for all drivers
            driverIds.forEach { id ->
                val data = repository.getTelemetryForDriver(id)
                if (data.isNotEmpty()) {
                    allTelemetry[id] = data

                    // Set track bounds based on the first available data
                    if (trackPathPoints.isEmpty()) {
                        trackPathPoints.addAll(data)
                        minX = data.minOf { it.x }
                        maxX = data.maxOf { it.x }
                        minY = data.minOf { it.y }
                        maxY = data.maxOf { it.y }
                    }
                }
            }

            if (allTelemetry.isEmpty()) return@launch

            // 2. Playback Synchronization
            // Use the first driver's timestamps as the master clock
            val masterTimeline = allTelemetry.values.first()

            for (moment in masterTimeline) {
                val currentTimestamp = moment.date

                allTelemetry.forEach { (driverId, telemetry) ->
                    // Find the telemetry point closest to the master timestamp
                    val closestPoint = telemetry.minByOrNull {
                        // Simple string comparison for timestamps or hash matching
                        abs(it.date.hashCode() - currentTimestamp.hashCode())
                    }
                    if (closestPoint != null) {
                        gridPositions[driverId] = closestPoint
                    }
                }
                delay(100) // 10Hz Refresh Rate
            }
        }
    }
}