package com.example.taskmanager.query;

import com.example.taskmanager.TaskCreated;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;

@Slf4j
public class UserRelatedEventsProjector {

  UserRelatedEventsViewRepository repository;

  @EventHandler
  public void on(TaskCreated event) {
    UUID assignee = event.getAssignee();
    UserRelatedEventsView firstByUser = repository.findFirstByUser(assignee);
    firstByUser.getEvents().add(event.getId());
  }
}
