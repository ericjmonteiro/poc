package br.com.wareline.application.controller;

import br.com.wareline.application.exception.CustomException;
import br.com.wareline.domain.service.UserService;
import br.com.wareline.domain.vo.UserVO;
import br.com.wareline.infrastructure.database.entity.UserEntity;
import io.quarkus.elytron.security.common.BcryptUtil;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;

@Path("/user")
@SecurityScheme(securitySchemeName = "Basic Auth", type = SecuritySchemeType.HTTP, scheme = "basic")
public class UserController {

  @Inject UserService service;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @RolesAllowed({"USER", "ADMIN"})
  @APIResponses(
      value =
          @APIResponse(
              responseCode = "200",
              description = "Success",
              content =
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = UserEntity.class))))
  public List<UserEntity> findAll() {
    return service.findAll();
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Operation(summary = "Adds a user", description = "Creete user with role")
  @PermitAll
  @APIResponses(
      value =
          @APIResponse(
              responseCode = "200",
              description = "Success",
              content =
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = UserEntity.class))))
  public UserEntity save(@Valid UserVO vo) throws CustomException {

    final UserEntity entity =
        UserEntity.builder()
            .ueername(vo.getUsername())
            .role(vo.getRole())
            .password(BcryptUtil.bcryptHash(vo.getPassword()))
            .age(vo.getAge())
            .build();
    return service.save(entity);
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/{name}")
  @RolesAllowed({"USER", "ADMIN"})
  public Response getByName(@PathParam("name") String name) {

    return Response.ok(service.findByName(name)).build();
  }
}
