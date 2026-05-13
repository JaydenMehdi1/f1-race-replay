package com.example.f1racereplay.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.geometry.Offset

@Composable
fun RaceCanvas(viewModel: ReplayViewModel) {
    val grid = viewModel.gridPositions
    val outline = viewModel.trackPathPoints

    Canvas(modifier = Modifier.fillMaxSize()) {
        val padding = 100f
        val drawWidth = size.width - (padding * 2)
        val drawHeight = size.height - (padding * 2)

        val dataWidth = viewModel.maxX - viewModel.minX
        val dataHeight = viewModel.maxY - viewModel.minY

        // Maintain aspect ratio
        val scale = if (dataWidth > 0 && dataHeight > 0) {
            minOf(drawWidth / dataWidth, drawHeight / dataHeight).toFloat()
        } else 1f

        // 1. Draw Track Outline
        if (outline.isNotEmpty()) {
            val trackPath = Path()
            outline.forEachIndexed { index, pt ->
                val x = ((pt.x - viewModel.minX) * scale).toFloat() + padding
                val y = size.height - (((pt.y - viewModel.minY) * scale).toFloat() + padding)
                if (index == 0) trackPath.moveTo(x, y) else trackPath.lineTo(x, y)
            }
            drawPath(
                path = trackPath,
                color = Color.DarkGray,
                style = Stroke(width = 3f)
            )
        }

        // 2. Draw Every Active Car
        grid.forEach { (driverNumber, loc) ->
            val screenX = ((loc.x - viewModel.minX) * scale).toFloat() + padding
            val screenY = size.height - (((loc.y - viewModel.minY) * scale).toFloat() + padding)

            // Dynamic color based on driver number
            val carColor = when (driverNumber) {
                1 -> Color(0xFF1E5BB0) // Red Bull Blue
                44 -> Color(0xFF00D2BE) // Mercedes Silver/Teal
                4 -> Color(0xFFFF8700) // McLaren Orange
                16 -> Color(0xFFDC0000) // Ferrari Red
                else -> Color.White
            }

            drawCircle(
                color = carColor,
                radius = 12f,
                center = Offset(screenX, screenY)
            )
        }
    }
}