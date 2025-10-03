package tech.insider.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import tech.insider.controller.dto.CreateEventDTO;
import tech.insider.controller.dto.EventDTO;
import tech.insider.service.EventService;

import java.util.List;

@Path("/events")
public class EventController {

   private final EventService eventService;

   public EventController(EventService eventService) {
      this.eventService = eventService;
   }

   @GET
   public List<EventDTO> getEvents() {
      return eventService.findAllEvents();
   }

   @POST
   public Response createEvent(CreateEventDTO dto) {
      var body = eventService.createEvent(dto);
      return Response.ok(body).build();
   }

   @GET
   @Path("/{id}")
   public Response getEvenet(Long id) {

      var event = eventService.findById(id);

      return event.isPresent()
              ? Response.ok(event.get()).build()
              : Response.status(Response.Status.NOT_FOUND).build();
   }
}
