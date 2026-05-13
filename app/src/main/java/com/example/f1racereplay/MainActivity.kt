package com.example.f1racereplay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.f1racereplay.data.F1Repository
import com.example.f1racereplay.data.RetrofitClient
import com.example.f1racereplay.ui.RaceCanvas
import com.example.f1racereplay.ui.ReplayViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Initialize our Back-end engine
        val api = RetrofitClient.instance
        val repository = F1Repository(api)
        val viewModel = ReplayViewModel(repository)

        // 2. Start the data stream (using 44 for Hamilton as an example)
        viewModel.startRaceReplay(44)

    }
}