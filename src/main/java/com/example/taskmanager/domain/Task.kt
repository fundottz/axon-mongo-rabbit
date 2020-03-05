package com.example.taskmanager.domain;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import com.example.taskmanager.CompleteTask;
import com.example.taskmanager.CreateTask;
import com.example.taskmanager.TaskCompleted;
import com.example.taskmanager.TaskCreated;
import com.example.taskmanager.TaskType;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

@Slf4j
@Aggregate
public class Task {

  @AggregateIdentifier
  private String id;
  private TaskType type;
  private LocalDateTime created;
  private String description;
  private UUID assignee;
  private boolean isComplete;

  public Task() {
    log.debug("empty constructor invoked");
  }

  @CommandHandler
  public Task(CreateTask cmd) {
    log.debug("Handle command {}", cmd);

    apply(new TaskCreated(cmd.getId(), LocalDateTime.now(), cmd.getDescription(), cmd.getType(),
        cmd.getAssignee()));
  }

  @CommandHandler
  public void handle(CompleteTask cmd) {
    log.debug("Handle command {}", cmd);
    if (isComplete) {
      throw new IllegalArgumentException("Already completed");
    }
    apply(new TaskCompleted(cmd.getId()));
  }

  @EventSourcingHandler
  public void on(TaskCreated evt) {
    log.debug("Applying {}", evt);

    this.id = evt.getId();
    this.type = evt.getType();
    this.created = evt.getCreated();
    this.description = evt.getDescription();
    this.assignee = evt.getAssignee();
  }

  @EventSourcingHandler
  public void on(TaskCompleted evt) {
    log.debug("Applying {}", evt);

    this.isComplete = true;
    log.debug("Task {} completed", evt.getId());
  }
}
