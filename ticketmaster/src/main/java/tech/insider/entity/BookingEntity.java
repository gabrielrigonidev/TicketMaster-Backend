package tech.insider.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
public class BookingEntity extends PanacheEntityBase {

   @Id
   @GeneratedValue
   public long id;

   @ManyToOne
   @JoinColumn(name = "user_id")
   public UserEntity user;

   @Enumerated(EnumType.STRING)
   public BookingStatus status;

   public Instant bookedAt;

   @CreationTimestamp
   public Instant createdAt;

   @UpdateTimestamp
   public Instant updatedAt;

}
