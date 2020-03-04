package com.example.taskmanager.query

import com.example.taskmanager.FetchTaskQuery
import io.swagger.annotations.Api
import org.axonframework.queryhandling.QueryGateway
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.CompletableFuture

@Api
@RestController
@RequestMapping("/api/tasks")
class TaskQueryController(private val queryGateway: QueryGateway) {

    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): CompletableFuture<TaskView> {
        return queryGateway.query(FetchTaskQuery(id), TaskView::class.java)
    }
}


