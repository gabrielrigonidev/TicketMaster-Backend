package tech.insider.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.UUID;

@Entity
public class TicketEntity extends PanacheEntityBase {

   @Id
   @GeneratedValue
   public Long id;

   public UUID externalId;

   @ManyToOne
   @JoinColumn(name = "seat_id")
   public SeatEntity seat;

   @ManyToOne
   @JoinColumn(name = "booking_id")
   public BookingEntity booking;

}
