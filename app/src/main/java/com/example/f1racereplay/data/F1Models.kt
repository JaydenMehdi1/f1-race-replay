package com.example.f1racereplay.data

import kotlinx.serialization.Serializable

@Serializable
data class CarLocation(
    val driver_number: Int,
    val x: Double,
    val y: Double,
    val date: String
)

@Serializable
data class Driver(
    val driver_number: Int,
    val full_name: String,
    val team_name: String,
    val team_colour: String
)
@Serializable
data class RaceState(
    val timestamp: String,
    val carPositions: Map<Int, CarLocation> // Key is driver_number
)