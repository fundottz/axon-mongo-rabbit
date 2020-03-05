package com.example.taskmanager.controller

import com.example.taskmanager.CreateTask
import com.example.taskmanager.TaskType
import com.example.taskmanager.command.TaskCommandController
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.axonframework.commandhandling.gateway.CommandGateway
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import java.util.*

@ExtendWith(MockKExtension::class)
@WebMvcTest(TaskCommandController::class)
class TaskCommandControllerTest {

    @Autowired
    lateinit var mvc: MockMvc

    @TestConfiguration
    open class TaskCommandControllerTestConfig {

        @Bean
        open fun repository() = mockk<CommandGateway>()
    }

    @Test
    fun `should create new task`() {
        val req = CreateTask(null, "do something", TaskType.TASK, UUID.randomUUID())
        mvc.post("/api/tasks", req)
                .andExpect { status { isOk } }
    }

}
