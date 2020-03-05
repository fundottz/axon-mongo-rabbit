package com.example.taskmanager.controller

import com.example.taskmanager.CreateTask
import com.example.taskmanager.TaskType
import com.fasterxml.jackson.databind.ObjectMapper
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.axonframework.commandhandling.gateway.CommandGateway
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.util.*
import java.util.concurrent.CompletableFuture

@SpringBootTest
@ExtendWith(MockKExtension::class)
@AutoConfigureMockMvc
//@WebMvcTest(TaskCommandController::class)
class TaskCommandControllerTest {

    @Autowired
    lateinit var mvc: MockMvc

    @Autowired
    lateinit var gateway: CommandGateway

    @Autowired
    lateinit var mapper: ObjectMapper

    @TestConfiguration
    open class TaskCommandControllerTestConfig {

        @Bean
        open fun repository() = mockk<CommandGateway>()
    }

    @Test
    fun `should create new task`() {
        val id = UUID.randomUUID()
        val cmd = CreateTask(id.toString(), "do something", TaskType.TASK, id)

        every { gateway.send(cmd) } returns CompletableFuture.completedFuture(id);

        val req = post("/api/tasks")
                .content(mapper.writeValueAsString(cmd))
                .contentType(MediaType.APPLICATION_JSON)

        mvc.perform(req).andExpect { MockMvcResultMatchers.status().isOk }
    }
}
