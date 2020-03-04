package com.example.taskmanager.query;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskViewRepository extends MongoRepository<TaskView, String> {

}
