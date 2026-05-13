package com.example.f1racereplay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.f1racereplay.data.F1Repository
import com.example.f1racereplay.data.RetrofitClient
import com.example.f1racereplay.ui.ReplayViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Manual setup for now
        val apiService = RetrofitClient.instance
        val repository = F1Repository(apiService)
        val viewModel = ReplayViewModel(repository)

        // Start the replay for a specific driver (e.g., 44 for Hamilton)
        viewModel.startRaceReplay(44)


    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

}