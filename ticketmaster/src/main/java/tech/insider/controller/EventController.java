package tech.insider.controller;

import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import tech.insider.controller.dto.*;
import tech.insider.service.EventService;

import java.util.List;

@Path("/events")
public class EventController {

   private final EventService eventService;

   public EventController(EventService eventService) {
      this.eventService = eventService;
   }

   @GET
   public ApiListDTO<EventDTO> getEvents(@QueryParam("page") @DefaultValue("0") Integer page,
                                      @QueryParam("pageSize") @DefaultValue("10")  Integer pageSize) {
      return eventService.findAll(page, pageSize);
   }

   @POST
   public Response createEvent(CreateEventDTO dto) {
      var body = eventService.createEvent(dto);
      return Response.ok(body).build();
   }

   @GET
   @Path("/{id}")
   public Response getEvent(@PathParam("id") Long id) {
      var event = eventService.findById(id);
      return event.isPresent()
              ? Response.ok(event.get()).build()
              : Response.status(Response.Status.NOT_FOUND).build();
   }

   @GET
   @Path("/{id}/seats")
   public ApiListDTO<SeatDTO> getSeats(@PathParam("id") Long id,
                                       @QueryParam("page") @DefaultValue("0") Integer page,
                                       @QueryParam("pageSize") @DefaultValue("10")  Integer pageSize) {

      return eventService.findAllSeats(id, page, pageSize);
   }

}
