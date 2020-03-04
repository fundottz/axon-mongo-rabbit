package com.example.taskmanager.controller

import com.example.taskmanager.TaskType
import com.example.taskmanager.query.TaskQueryController
import com.example.taskmanager.query.TaskView
import com.example.taskmanager.query.TaskViewProjector
import com.example.taskmanager.query.TaskViewRepository
import com.fasterxml.jackson.databind.ObjectMapper
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import java.time.LocalDateTime
import java.util.*

//@SpringBootTest
//@AutoConfigureMockMvc
@ExtendWith(MockKExtension::class)
@WebMvcTest(TaskQueryController::class, TaskViewProjector::class)
internal class TaskQueryControllerTest {

    @TestConfiguration
    open class TaskControllerTestConfig {

        @Bean
        open fun repository() = mockk<TaskViewRepository>()
    }

    @Autowired
    lateinit var repository: TaskViewRepository

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

        every { repository.findById(any()) } returns Optional.of(expected)

        mvc.get("/api/tasks/{id}", id)
                .andExpect {
                    status { isOk }
                    content { json(mapper.writeValueAsString(expected)) }
                }
    }
}
