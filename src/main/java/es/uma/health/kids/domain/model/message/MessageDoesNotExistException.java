package es.uma.health.kids.domain.model.message;

import es.uma.health.kids.domain.model.shared.ResourceDoesNotExistException;

public class MessageDoesNotExistException extends ResourceDoesNotExistException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MessageDoesNotExistException(String message) {
		super(message);
	}

	public MessageDoesNotExistException(Throwable cause) {
		super(cause);
	}

	public MessageDoesNotExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public MessageDoesNotExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
