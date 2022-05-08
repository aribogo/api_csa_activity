package api.gft.message.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ ExceptionHandlerEx.class })
	public ResponseEntity<ApiError> handleContentMissing(ExceptionHandlerEx ex, WebRequest request) {

		String error = "Not all fields were complete";

		ApiError apiError = new ApiError(HttpStatus.PRECONDITION_FAILED, ex.getMessage(), error);

		return new ResponseEntity<ApiError>(apiError, new HttpHeaders(), apiError.getStatus());
	}

}
