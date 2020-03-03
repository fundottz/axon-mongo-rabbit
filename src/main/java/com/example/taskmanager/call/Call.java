package com.example.taskmanager.call;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import com.example.taskmanager.CallCreatedEvent;
import com.example.taskmanager.CreateCallCommand;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

@Slf4j
@Aggregate
public class Call {

  @AggregateIdentifier
  private String id;

  private UUID from;
  private UUID to;
  private String phoneNumber;
  private LocalDateTime dueDatetime;
  private String description;

  @CommandHandler
  public Call(CreateCallCommand cmd) {

    log.debug("Handling {} for call {}", cmd, cmd.getId());

    apply(new CallCreatedEvent(cmd.getId(), cmd.getFrom(), cmd.getTo(),
        cmd.getDescription(), cmd.getPhoneNumber()));
  }

  @EventSourcingHandler
  public void on(CallCreatedEvent event) {
    log.debug("Applied {}", event);
    id = event.getId();
    from = event.getFrom();
    to = event.getTo();
    phoneNumber = event.getPhoneNumber();
    description = event.getDescription();
  }
}
