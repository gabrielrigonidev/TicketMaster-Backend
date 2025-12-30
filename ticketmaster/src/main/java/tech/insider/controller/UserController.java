package tech.insider.controller;

import java.util.List;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import tech.insider.entity.UserEntity;

@Path("/users")
public class UserController {
   @POST
   @Transactional
   public void createUser(){
      var user = new UserEntity();
      user.username = "gabriel";
      user.email = "rigonigabriel12@gmail.com";
      user.password = "123";
      user.persist();
   }

   @GET
   public List<UserEntity> getUsers(){
      return UserEntity.listAll();
   }
}
