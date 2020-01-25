package api.innov.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class InvalidRequestParamsException extends Exception implements ExceptionMapper<MissingFileException> {
	
	private static final long serialVersionUID = 1L;

	public InvalidRequestParamsException() {
		super("Invalid or missing request parameters");
	}

	public InvalidRequestParamsException(String string) {
		super(string);
	}

	@Override
	public Response toResponse(MissingFileException exception) {
		return Response.status(500).entity("Invalid or missing request parameters").type("text/plain").build();
	}

}
