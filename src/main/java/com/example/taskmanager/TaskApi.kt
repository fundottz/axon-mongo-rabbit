package com.example.taskmanager

import org.axonframework.modelling.command.AggregateIdentifier
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

enum class TaskType {
    CALL,
    TASK
}

data class CreateTask(
        @AggregateIdentifier val id: String,
        val description: String,
        val type: TaskType = TaskType.TASK,
        val assignee: UUID
)

data class ModifyTask(
        @AggregateIdentifier val id: String,
        val description: String,
        val dueDate: LocalDate
)

data class DeleteTask(@AggregateIdentifier val id: String)
data class CompleteTask(@AggregateIdentifier val id: String)

data class TaskCreated(
        val id: String,
        val created: LocalDateTime,
        val description: String,
        val type: TaskType,
        val assignee: UUID
)

data class TaskCompleted(val id: String)
