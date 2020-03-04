package com.example.taskmanager.query;

import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRelatedEventsView {

  private UUID user;
  private Set<String> events;
}
