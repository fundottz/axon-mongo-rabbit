package com.example.taskmanager.query;

import com.example.taskmanager.FetchTaskQuery;
import com.example.taskmanager.TaskCompleted;
import com.example.taskmanager.TaskCreated;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TaskViewProjector {

  private final TaskViewRepository repository;

  @EventHandler
  public void on(TaskCreated event) {
    TaskView projectedView = new TaskView(event.getId(), event.getType(), event.getCreated(),
        event.getDescription(),
        event.getAssignee(), false);

    log.debug("Projecting {} into {}", event, projectedView);

    repository.save(projectedView);
  }

  @EventHandler
  public void on(TaskCompleted event) {
    log.debug("Projecting {}", event);

    repository.findById(event.getId()).ifPresentOrElse(
        task -> {
          task.setComplete(true);
          repository.save(task);
        },
        () -> {
          throw new IllegalArgumentException("Task not found " + event.getId());
        });
  }

  @QueryHandler
  public TaskView handle(FetchTaskQuery query) {
    return repository.findById(query.getTaskId())
        .orElseThrow(() -> new IllegalArgumentException("Task not found " + query.getTaskId()));
  }
}
