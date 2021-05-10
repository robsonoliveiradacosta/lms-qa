package lms.exception;

import java.time.OffsetDateTime;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ResourceNotFoundExceptionHandler implements ExceptionMapper<ResourceNotFoundException> {

	@Override
	public Response toResponse(ResourceNotFoundException e) {
		ApiError appError = new ApiError(Response.Status.NOT_FOUND.getStatusCode(), e.getMessage(),
				OffsetDateTime.now());
		return Response.status(Response.Status.NOT_FOUND).entity(appError).build();
	}

}
