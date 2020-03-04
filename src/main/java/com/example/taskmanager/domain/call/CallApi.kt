package com.example.taskmanager

import java.time.Duration
import java.time.LocalDateTime
import java.util.*

data class CreateCallCommand(
        val id: String,
        val from: UUID,
        val to: UUID,
        val description: String,
        val phoneNumber: String
)

data class PlaceCallCommand(val id: String)
data class EndCallCommand(val id: String)

data class CallCreatedEvent(
        val id: String,
        val from: UUID,
        val to: UUID,
        val description: String,
        val phoneNumber: String
)

data class CallPlaced(val id: String, val timestamp: LocalDateTime)
data class CallEnded(val id: String, val timestamp: LocalDateTime, val duration: Duration)

data class CallProjection(
        val date: LocalDateTime,
        val duration: Duration
)
