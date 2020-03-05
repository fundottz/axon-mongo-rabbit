package com.example.taskmanager.command;

import com.example.taskmanager.CompleteTask;
import com.example.taskmanager.CreateTask;
import io.swagger.annotations.Api;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api
@Slf4j
@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskCommandController {

  private final CommandGateway commandGateway;

  @PostMapping
  public void create(@RequestBody CreateTask cmd) {
    commandGateway.send(cmd).thenAccept(r-> log.info("asd {}", r));
  }

  @PutMapping("/{id}/completed")
  public void create(@PathVariable String id) {
    commandGateway.sendAndWait(new CompleteTask(id), 5, TimeUnit.SECONDS);
  }
}
