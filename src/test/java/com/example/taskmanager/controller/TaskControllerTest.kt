package com.example.taskmanager.controller

import com.example.taskmanager.CreateTask
import com.example.taskmanager.TaskType
import com.example.taskmanager.query.TaskView
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import java.time.LocalDateTime
import java.util.*

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerTest {

    @Autowired
    lateinit var mvc: MockMvc
    @Autowired
    lateinit var mapper: ObjectMapper

    @Test
    fun `should return task by id`() {
        val id = "e54e6cc0"
        val expected = TaskView(
                id, TaskType.TASK, LocalDateTime.now(),
                "do smth", UUID.randomUUID(), false)

        mvc.get("/api/tasks/{id}", id)
                .andExpect {
                    status { isOk }
                    content { json(mapper.writeValueAsString(expected)) }
                }
    }

    @Test
    fun `should create new task`() {
        val req = CreateTask(null, "do something", TaskType.TASK, UUID.randomUUID())
        mvc.post("/api/tasks", req)
                .andExpect { status { isOk } }
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
