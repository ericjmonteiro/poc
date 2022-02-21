package br.com.wareline.application.exception;

import br.com.wareline.application.exception.user.UserNotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

@Provider
@Slf4j
public class PocExceptionHandler implements ExceptionMapper<RuntimeException> {

  @Override
  public Response toResponse(RuntimeException exception) {
    log.info("Exception occurred {}", exception.getMessage());

    if (exception instanceof UserNotFoundException) {
      return Response.status(Response.Status.NOT_FOUND)
          .entity(new ErrorResponseBody(exception.getMessage()))
          .build();
    }
    return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
        .entity(new ErrorResponseBody(exception.getMessage()))
        .build();
  }

  public static final class ErrorResponseBody {

    private final String message;

    public ErrorResponseBody(String message) {
      this.message = message;
    }

    public String getMessage() {
      return message;
    }
  }
}
