package com.example.taskmanager.query;

import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRelatedEventsViewRepository extends
    MongoRepository<UserRelatedEventsView, String> {

  UserRelatedEventsView findFirstByUser(UUID user);
}
