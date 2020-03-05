package com.example.taskmanager.domain

import com.example.taskmanager.*
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.LocalDateTime
import java.util.*

@Aggregate
class Task {

    @AggregateIdentifier
    private lateinit var id: String
    private lateinit var type: TaskType
    private lateinit var created: LocalDateTime
    private lateinit var description: String
    private lateinit var assignee: UUID
    private var isComplete = false

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    constructor() {
        log.debug("empty constructor invoked")
    }

    @CommandHandler
    constructor(cmd: CreateTask) {
        log.debug("Handle command {}", cmd)

        AggregateLifecycle.apply(
                TaskCreated(cmd.id!!, LocalDateTime.now(), cmd.description, cmd.type, cmd.assignee))
    }

    @CommandHandler
    fun handle(cmd: CompleteTask) {
        log.debug("Handle command {}", cmd)

        require(!isComplete) { "Already completed" }

        AggregateLifecycle.apply(TaskCompleted(cmd.id))
    }

    @EventSourcingHandler
    fun on(evt: TaskCreated) {
        log.debug("Applying {}", evt)
        id = evt.id
        type = evt.type
        created = evt.created
        description = evt.description
        assignee = evt.assignee
    }

    @EventSourcingHandler
    fun on(evt: TaskCompleted) {
        log.debug("Applying {}", evt)

        isComplete = true

        log.debug("Task {} completed", evt.id)
    }
}
