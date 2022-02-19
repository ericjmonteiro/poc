package br.com.wareline.application.exception;

import br.com.wareline.domain.vo.ErrorMessageVO;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Provider
@Slf4j
public class PocException implements ExceptionMapper<CustomException> {

  @ConfigProperty(name = "message.user.error.notfound")
  String userNotFound;

  @Override
  public Response toResponse(CustomException exception) {
    log.info("Exception occurred {}", exception.getMessage());

    if (exception.getMessage().equalsIgnoreCase(userNotFound)) {
      return Response.status(Response.Status.NOT_FOUND)
          .entity(new ErrorMessageVO(exception.getMessage(), false))
          .build();
    } else {

      return Response.status(Response.Status.BAD_REQUEST)
          .entity(new ErrorMessageVO(exception.getMessage(), false))
          .build();
    }
  }
}
