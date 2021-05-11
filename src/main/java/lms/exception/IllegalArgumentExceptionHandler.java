package lms.exception;

import java.time.OffsetDateTime;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class IllegalArgumentExceptionHandler implements ExceptionMapper<IllegalArgumentException> {

	@Override
	public Response toResponse(IllegalArgumentException e) {
		ApiError appError = new ApiError(Response.Status.CONFLICT.getStatusCode(), e.getMessage(),
				OffsetDateTime.now());
		return Response.status(Response.Status.CONFLICT).entity(appError).build();
	}

}
