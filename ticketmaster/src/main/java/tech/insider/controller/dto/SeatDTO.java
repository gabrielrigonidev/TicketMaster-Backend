package tech.insider.controller.dto;

import tech.insider.entity.SeatEntity;

public record SeatDTO(Long seatId, String name, String status) {

   public static SeatDTO fromEntity(SeatEntity seatEntity) {
      return new SeatDTO(
              seatEntity.id,
              seatEntity.name,
              seatEntity.status.name());
   }
}
