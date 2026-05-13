package com.example.f1racereplay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.f1racereplay.data.F1Repository
import com.example.f1racereplay.data.RetrofitClient
import com.example.f1racereplay.ui.RaceCanvas
import com.example.f1racereplay.ui.ReplayViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize Backend components
        val apiService = RetrofitClient.instance
        val repository = F1Repository(apiService)
        val viewModel = ReplayViewModel(repository)

        // Request telemetry for specific drivers (Max, Lewis, Lando, Charles)
        viewModel.startMultiCarReplay(listOf(1, 44, 4, 16))
    }
}