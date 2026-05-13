package com.example.f1racereplay.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.f1racereplay.data.CarLocation
import com.example.f1racereplay.data.F1Repository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ReplayViewModel(private val repository: F1Repository) : ViewModel() {

    // Thread-safe list that the UI will observe
    var currentPositions = mutableStateListOf<CarLocation>()
        private set

    var minX = 0.0
    var maxX = 0.0
    var minY = 0.0
    var maxY = 0.0
    fun startRaceReplay(driverNumber: Int) {
        viewModelScope.launch {
            // 1. Fetch the data from the back-end we built
            val telemetry = repository.getTelemetryForDriver(driverNumber)

            // Calculate the bounding box of the track
            if (telemetry.isNotEmpty()) {
                minX = telemetry.minOf { it.x }
                maxX = telemetry.maxOf { it.x }
                minY = telemetry.minOf { it.y }
                maxY = telemetry.maxOf { it.y }
            }

            // 2. Play through the telemetry points
            for (point in telemetry) {
                // Update the state
                currentPositions.clear()
                currentPositions.add(point)

                // 3. Control the replay speed (100ms = 10 updates per second)
                delay(100)
            }
        }
    }
}