package tech.insider.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import tech.insider.controller.dto.CreateEventDTO;
import tech.insider.controller.dto.EventDTO;
import tech.insider.entity.EventEntity;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class EventService {

   public List<EventDTO> findAllEvents(){
      return EventEntity.listAll()
              .stream()
              .map(EventEntity.class::cast)
              .map(EventDTO::fromEntity)
              .toList();
   }

   @Transactional
   public EventDTO createEvent(CreateEventDTO dto){
      var entity = dto.toEntity();
      entity.persist();
      return EventDTO.fromEntity(entity);
   }

   public Optional<EventDTO> findById(long id){
      return EventEntity.findByIdOptional(id)
              .map(EventEntity.class::cast)
              .map(EventDTO::fromEntity);

   }
}
