package com.example.taskmanager.query;

import com.example.taskmanager.TaskType;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@Data
public class TaskView {

  @Id
  private String id;
  private TaskType type;
  private LocalDateTime created;
  private String description;
  private UUID assignee;
  private boolean isComplete;
}
