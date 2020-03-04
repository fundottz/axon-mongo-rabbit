package com.example.taskmanager.query

import com.example.taskmanager.FetchTaskQuery
import com.example.taskmanager.TaskCompleted
import com.example.taskmanager.TaskCreated
import org.axonframework.eventhandling.EventHandler
import org.axonframework.queryhandling.QueryHandler
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class TaskViewProjector(private val repository: TaskViewRepository) {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    @EventHandler
    fun on(event: TaskCreated) {
        val projectedView = TaskView(
                event.id,
                event.type,
                event.created,
                event.description,
                event.assignee,
                false)

        log.debug("Projecting {} into {}", event, projectedView)

        repository.save(projectedView)
    }

    @EventHandler
    fun on(event: TaskCompleted) {
        log.debug("Projecting {}", event)

        repository.findById(event.id)
                .ifPresentOrElse(
                        { task: TaskView ->
                            task.isComplete = true
                            repository.save(task)
                        }
                )
                { throw IllegalArgumentException("Task not found " + event.id) }
    }

    @QueryHandler
    fun handle(query: FetchTaskQuery): TaskView {
        log.debug("Queried {}", query)

        return repository.findById(query.taskId)
                .orElseThrow { IllegalArgumentException("Task not found " + query.taskId) }
    }
}