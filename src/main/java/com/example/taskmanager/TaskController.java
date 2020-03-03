package com.example.taskmanager;

import io.swagger.annotations.Api;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController("tasks")
@RequiredArgsConstructor
public class TaskController {

  private final CommandGateway commandGateway;
  private final QueryGateway queryGateway;

  @PostMapping
  public void create(CreateTask cmd) {
    commandGateway.sendAndWait(cmd, 5, TimeUnit.SECONDS);
  }

  @PutMapping("/{id}/completed")
  public void create(@PathVariable String id) {
    commandGateway.sendAndWait(new CompleteTask(id), 5, TimeUnit.SECONDS);
  }
}
