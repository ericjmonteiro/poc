package br.com.wareline.application.controller;

import br.com.wareline.domain.service.UserService;
import br.com.wareline.domain.vo.UserVO;
import br.com.wareline.infrastructure.database.entity.UserEntity;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
public class UserController {

  @Inject UserService service;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response get() {
    return Response.ok(service.findAll()).build();
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response save(UserVO vo) {

    final UserEntity entity = UserEntity.builder().name(vo.getName()).age(vo.getAge()).build();
    return Response.ok(service.save(entity)).build();
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/{name}")
  public Response getByName(@PathParam("name") String name) {

    return Response.ok(service.findByName(name)).build();
  }
}
