package com.example.taskmanager.query

import com.example.taskmanager.FetchTaskQuery
import io.swagger.annotations.Api
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Api
@RestController
@RequestMapping("/api/tasks")
class TaskQueryController(private val taskViewProjector: TaskViewProjector) {

    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): TaskView {
        return taskViewProjector.handle(FetchTaskQuery(id))
    }
}


