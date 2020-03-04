package com.example.taskmanager.command;

import com.example.taskmanager.CompleteTask;
import com.example.taskmanager.CreateTask;
import io.swagger.annotations.Api;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskCommandController {

  private final CommandGateway commandGateway;
  private final QueryGateway queryGateway;

  // todo: generate id on backend
  @PostMapping
  public void create(CreateTask cmd) {
    commandGateway.sendAndWait(cmd, 5, TimeUnit.SECONDS);
  }

  @PutMapping("/{id}/completed")
  public void create(@PathVariable String id) {
    commandGateway.sendAndWait(new CompleteTask(id), 5, TimeUnit.SECONDS);
  }
}
