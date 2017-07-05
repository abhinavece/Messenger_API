package org.api.heapdev.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.api.heapdev.messenger.model.ErrorMessage;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException ex) {
		ErrorMessage errorMessage = new ErrorMessage("Msg not found", 404, "http://www.heapdev.com/error/404/description");
		return Response.status(Status.NOT_FOUND)
						.entity(errorMessage)
						.build();
	}
}
