package tech.insider.controller.dto;

import java.util.Set;

public record CreateBookingDTO(Long userId,
                               Long eventId,
                               Set<ReserveSeatDTO> seats){
}
