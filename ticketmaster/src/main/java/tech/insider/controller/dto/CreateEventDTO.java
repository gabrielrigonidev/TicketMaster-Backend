package tech.insider.controller.dto;

import tech.insider.entity.EventEntity;

public record CreateEventDTO(String name, String description, EventSettingDTO settings) {

   public EventEntity toEntity() {
      return new EventEntity(name, description);
   }
}
