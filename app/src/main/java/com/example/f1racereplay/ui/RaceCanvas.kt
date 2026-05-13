package com.example.f1racereplay.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RaceCanvas(viewModel: ReplayViewModel) {
    // This list is observed; when it changes, the Canvas re-draws
    val positions = viewModel.currentPositions

    Canvas(modifier = Modifier.fillMaxSize()) {
        // We need a way to map F1 coordinates (huge) to Screen pixels (small)
        // For now, we use a simple hardcoded scale.
        // Later, we can calculate this dynamically based on track limits.
        val scale = 0.05f
        val offsetX = size.width / 2
        val offsetY = size.height / 2

        positions.forEach { car ->
            // Map the X and Y
            val screenX = (car.x.toFloat() * scale) + offsetX
            val screenY = (car.y.toFloat() * scale) + offsetY

            // Draw the car as a simple dot for now
            drawCircle(
                color = Color.Red,
                radius = 10f,
                center = androidx.compose.ui.geometry.Offset(screenX, screenY)
            )
        }
    }
}