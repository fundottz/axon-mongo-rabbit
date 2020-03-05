package com.example.taskmanager.query

import com.example.taskmanager.TaskType
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.*

@Document
class TaskView(
        @Id
        private val id: String,
        private val type: TaskType,
        private val created: LocalDateTime,
        private var description: String,
        var assignee: UUID,
        var isComplete: Boolean
)
