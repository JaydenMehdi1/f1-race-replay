package com.example.f1racereplay.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RaceCanvas(viewModel: ReplayViewModel) {
    val positions = viewModel.currentPositions

    Canvas(modifier = Modifier.fillMaxSize()) {
        val padding = 50f // Keep the track away from the very edge of the screen

        // Calculate the usable draw area
        val drawWidth = size.width - (padding * 2)
        val drawHeight = size.height - (padding * 2)

        // Find the range of the F1 data
        val dataWidth = viewModel.maxX - viewModel.minX
        val dataHeight = viewModel.maxY - viewModel.minY

        // Determine the scale factor to maintain aspect ratio
        val scale = minOf(drawWidth / dataWidth, drawHeight / dataHeight).toFloat()

        positions.forEach { car ->
            // Normalizing: (Current - Min) / (Max - Min) * ScreenSize
            // We use the same 'scale' for both to avoid stretching the track
            val screenX = ((car.x - viewModel.minX) * scale).toFloat() + padding

            // Note: F1 Y-coordinates usually increase upwards,
            // but Android Y-coordinates increase downwards, so we invert it.
            val screenY = size.height - (((car.y - viewModel.minY) * scale).toFloat() + padding)

            drawCircle(
                color = Color.Red,
                radius = 12f,
                center = androidx.compose.ui.geometry.Offset(screenX, screenY)
            )
        }
    }
}