package es.uma.health.kids.domain.model.user;

import es.uma.health.kids.domain.model.shared.ResourceDoesNotExistException;

public class DoctorDoesNotExistException extends ResourceDoesNotExistException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DoctorDoesNotExistException(String message) {
		super(message);
	}

	public DoctorDoesNotExistException(Throwable cause) {
		super(cause);
	}

	public DoctorDoesNotExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public DoctorDoesNotExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
