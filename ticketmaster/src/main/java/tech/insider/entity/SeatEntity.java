package tech.insider.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class SeatEntity extends PanacheEntityBase {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   public long id;

   @ManyToOne
   @JoinColumn(name = "event_id")
   public EventEntity event;

   public String name;

   @Enumerated(EnumType.STRING)
   public SeatStatus status;

   public SeatEntity(){
   }

   public SeatEntity(EventEntity event, String name, SeatStatus status) {
      this.event = event;
      this.name = name;
      this.status = status;
   }
}
