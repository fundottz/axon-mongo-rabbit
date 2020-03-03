package com.example.taskmanager;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskViewRepository extends MongoRepository<TaskView, String> {

}
