package es.uma.health.kids.domain.model.user;

import es.uma.health.kids.domain.model.shared.ResourceDoesNotExistException;

public class PatientResponsibleDoesNotExistException extends ResourceDoesNotExistException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PatientResponsibleDoesNotExistException(String message) {
		super(message);
	}

	public PatientResponsibleDoesNotExistException(Throwable cause) {
		super(cause);
	}

	public PatientResponsibleDoesNotExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public PatientResponsibleDoesNotExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
