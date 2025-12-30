package tech.insider.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import tech.insider.controller.dto.CreateBookingDTO;
import tech.insider.controller.dto.ReserveSeatDTO;
import tech.insider.entity.BookingEntity;
import tech.insider.entity.BookingStatus;
import tech.insider.entity.SeatEntity;
import tech.insider.entity.SeatStatus;
import tech.insider.entity.TicketEntity;
import tech.insider.entity.UserEntity;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class BookingService {

   @Transactional
   public Long createBooking(CreateBookingDTO dto) {
      Set<Long> seatId = getSeatsId(dto);
      var seats = findSeats(seatId);

      BookingEntity bookingEntity = buildBookingEntity(dto);
      bookingEntity.persist();

      createTickets(seats, bookingEntity);
      updateSeats(seats);
      return bookingEntity.id;
   }

   private static Set<SeatEntity> findSeats(Set<Long> seatId){
      return SeatEntity.find("id in ?1", seatId)
              .stream()
              .map(SeatEntity.class::cast)
              .peek(s -> {
                 if(s.status == SeatStatus.BOOKED){
                    throw new RuntimeException("Seat already booked");
                 }
              })
              .collect(Collectors.toSet());
   }

   private static Set<Long> getSeatsId(CreateBookingDTO dto){
      return dto.seats()
              .stream()
              .map(ReserveSeatDTO::seatId)
              .collect(Collectors.toSet());
   }

   private static BookingEntity buildBookingEntity(CreateBookingDTO dto) {
      BookingEntity booking = new BookingEntity();

      booking.bookedAt = Instant.now();
      booking.status = BookingStatus.PENDING;
      booking.user = UserEntity.findById(dto.userId());
      return booking;
   }

   private static void createTickets(Set<SeatEntity> seats, BookingEntity bookingEntity) {
      TicketEntity ticketEntity =  new TicketEntity();
      seats.forEach(seat -> {
         ticketEntity.seat = seat;
         ticketEntity.externalId = UUID.randomUUID();
         ticketEntity.booking = bookingEntity;
         ticketEntity.persist();
      });
   }

   private static void updateSeats(Set<SeatEntity> seats) {
      seats.stream()
              .peek(seat -> seat.status = SeatStatus.BOOKED)
              .forEach(seat -> seat.persist());
   }
}
