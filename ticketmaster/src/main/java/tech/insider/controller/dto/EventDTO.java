package tech.insider.controller.dto;

import tech.insider.entity.EventEntity;

public record EventDTO(Long id, String name, String description) {

   public static EventDTO fromEntity(EventEntity entity) {
      return new EventDTO(entity.id, entity.name, entity.description);
   }
}
