package lms.exception;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import lms.exception.ApiError.Field;

@Provider
public class ConstraintViolationExceptionHandler implements ExceptionMapper<ConstraintViolationException> {

	@Override
	public Response toResponse(ConstraintViolationException e) {
		List<Field> fields = e.getConstraintViolations().stream()
				.map(cv -> new ApiError.Field(getName(cv), cv.getMessage())).collect(Collectors.toUnmodifiableList());

		ApiError appError = new ApiError(Response.Status.BAD_REQUEST.getStatusCode(), "informe os campos obrigatórios!",
				OffsetDateTime.now(), fields);

		return Response.status(Response.Status.BAD_REQUEST).entity(appError).build();
	}

	private String getName(ConstraintViolation<?> constraintViolation) {
		return StreamSupport.stream(constraintViolation.getPropertyPath().spliterator(), false)
				.reduce((first, second) -> second).orElse(null).getName();
	}

}
