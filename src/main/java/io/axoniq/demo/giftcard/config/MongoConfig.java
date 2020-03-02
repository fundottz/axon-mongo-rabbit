package io.axoniq.demo.giftcard.config;

import com.mongodb.MongoClient;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.extensions.mongo.DefaultMongoTemplate;
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.axonframework.spring.config.AxonConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {

  @Bean
  public EmbeddedEventStore eventStore(EventStorageEngine storageEngine,
      AxonConfiguration configuration) {
    return EmbeddedEventStore.builder()
        .storageEngine(storageEngine)
        .messageMonitor(configuration.messageMonitor(EventStore.class, "eventStore"))
        .build();
  }

  // The `MongoEventStorageEngine` stores each event in a separate MongoDB document
  @Bean
  public EventStorageEngine storageEngine(MongoClient client) {
    return MongoEventStorageEngine
        .builder().mongoTemplate(DefaultMongoTemplate.builder().mongoDatabase(client).build())
        .build();
  }
}
