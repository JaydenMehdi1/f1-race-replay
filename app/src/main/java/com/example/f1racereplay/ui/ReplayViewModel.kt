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

    fun startRaceReplay(driverNumber: Int) {
        viewModelScope.launch {
            // 1. Fetch the data from the back-end we built
            val telemetry = repository.getTelemetryForDriver(driverNumber)

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