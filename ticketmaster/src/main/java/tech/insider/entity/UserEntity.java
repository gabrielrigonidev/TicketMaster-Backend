package tech.insider.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class UserEntity extends PanacheEntityBase {

   @Id
   @GeneratedValue
   public long id;

   public String username;

   public String email;

   public String password;

   public UserEntity() {}
}
