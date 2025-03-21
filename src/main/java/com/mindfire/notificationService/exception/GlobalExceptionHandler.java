package com.mindfire.notificationService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.mail.MessagingException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * Handles exceptions related to email messaging failures.
	 * <p>
	 * This method catches {@link MessagingException} and returns a
	 * {@link ProblemDetail} with a status code of 500 (Internal Server Error) and
	 * the exception message as the detail.
	 * </p>
	 *
	 * @param ex the {@link MessagingException} that occurred during email
	 *           processing
	 * @return a {@link ProblemDetail} response containing the HTTP status and error
	 *         message
	 */
	@ExceptionHandler(MessagingException.class)
	public ProblemDetail handleMessagingException(MessagingException ex) {
		return ProblemDetail.forStatusAndDetail(HttpStatus.valueOf(500), "Fail to send Email");
	}

	/**
	 * Handles runtime exceptions thrown in the application.
	 *
	 * @param ex the runtime exception that was thrown
	 * @return a {@link ProblemDetail} representing the error response with HTTP
	 *         status 500
	 */
	@ExceptionHandler(RuntimeException.class)
	public ProblemDetail handleRuntimeException(RuntimeException ex) {
		return ProblemDetail.forStatusAndDetail(HttpStatus.valueOf(500), ex.getMessage());
	}

	/**
	 * Handles general exceptions thrown in the application.
	 * <p>
	 * This method catches all exceptions of type {@link Exception} and returns a
	 * {@link ProblemDetail} with a status code of 500 (Internal Server Error) and
	 * the exception message as the detail.
	 * </p>
	 *
	 * @param exception the exception that was thrown
	 * @return a {@link ProblemDetail} representing the error response with HTTP
	 *         status 500
	 */
	@ExceptionHandler(Exception.class)
	public ProblemDetail handleException(Exception exception) {
		return ProblemDetail.forStatusAndDetail(HttpStatus.valueOf(500), exception.getMessage());
	}

}
