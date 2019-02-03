package es.uma.health.kids.domain.model.patient;

import es.uma.health.kids.domain.model.shared.ResourceDoesNotExistException;

public class PatientDoesNotExistException extends ResourceDoesNotExistException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PatientDoesNotExistException() {
		super();
	}

	public PatientDoesNotExistException(String message) {
		super(message);
	}

	public PatientDoesNotExistException(Throwable cause) {
		super(cause);
	}

	public PatientDoesNotExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public PatientDoesNotExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
