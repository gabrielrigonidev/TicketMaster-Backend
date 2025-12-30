package tech.insider.controller;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import tech.insider.controller.dto.BookingResponseDTO;
import tech.insider.controller.dto.CreateBookingDTO;
import tech.insider.service.BookingService;

@Path("/bookings")
public class BookingController {

   private final BookingService bookingService;

   public BookingController(BookingService bookingService) {
      this.bookingService = bookingService;
   }

   @POST
   public Response createBooking(CreateBookingDTO dto) {
      var bookingId = bookingService.createBooking(dto);
      return Response.ok(new BookingResponseDTO(bookingId)).build();
   }
}
