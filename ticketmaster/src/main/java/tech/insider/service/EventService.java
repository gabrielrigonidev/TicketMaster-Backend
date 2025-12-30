package tech.insider.service;

import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import tech.insider.controller.dto.ApiListDTO;
import tech.insider.controller.dto.CreateEventDTO;
import tech.insider.controller.dto.EventDTO;
import tech.insider.controller.dto.PaginationDTO;
import tech.insider.controller.dto.SeatDTO;
import tech.insider.entity.EventEntity;
import tech.insider.entity.SeatEntity;
import tech.insider.entity.SeatStatus;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class EventService {

   public ApiListDTO<EventDTO> findAll(int page, int pageSize){
      var query = EventEntity.findAll()
              .page(Page.of(page, pageSize));

      var totalPages = query.pageCount();
      var totalElements = query.count();
      List<EventDTO> events = query.stream()
              .map(EventEntity.class::cast)
              .map(EventDTO::fromEntity)
              .toList();

      return new ApiListDTO<>(
              events,
              new PaginationDTO(page, pageSize, totalPages, totalElements));
   }

   @Transactional
   public EventDTO createEvent(CreateEventDTO dto){
      var eventEntity = dto.toEntity();

      eventEntity.persist();

      SeatEntity entitySeat;
      for(int c=0; c<dto.settings().numberOfSeats(); c++){
         var seatName = "S" +c ;
         entitySeat = new SeatEntity(eventEntity, seatName, SeatStatus.AVAILABLE);
         entitySeat.persist();
      }

      return EventDTO.fromEntity(eventEntity);
   }

   public Optional<EventDTO> findById(long id){
      return EventEntity.findByIdOptional(id)
              .map(EventEntity.class::cast)
              .map(EventDTO::fromEntity);
   }

   public ApiListDTO<SeatDTO> findAllSeats(Long eventId, Integer page, Integer pageSize){
      // TODO tratar exception via ExceptionHandler
      var event = EventEntity.findByIdOptional(eventId).orElseThrow();

      var query = SeatEntity.find("event", event)
              .page(Page.of(page, pageSize));

      var totalPages = query.pageCount();
      var totalElements = query.count();
      var events = query.stream()
              .map(SeatEntity.class::cast)
              .map(SeatDTO::fromEntity)
              .toList();

      return new ApiListDTO<>(
              events,
              new PaginationDTO(page, pageSize, totalPages, totalElements));
   }
}
