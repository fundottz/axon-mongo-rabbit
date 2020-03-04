package com.example.taskmanager

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

enum class TaskType {
    CALL,
    TASK
}

data class CreateTask(
        @TargetAggregateIdentifier val id: String,
        val description: String,
        val type: TaskType = TaskType.TASK,
        val assignee: UUID
)

data class ModifyTask(
        @TargetAggregateIdentifier val id: String,
        val description: String,
        val dueDate: LocalDate
)

data class DeleteTask(@TargetAggregateIdentifier val id: String)
data class CompleteTask(@TargetAggregateIdentifier val id: String)

data class TaskCreated(
        val id: String,
        val created: LocalDateTime,
        val description: String,
        val type: TaskType,
        val assignee: UUID
)

data class TaskCompleted(val id: String)
