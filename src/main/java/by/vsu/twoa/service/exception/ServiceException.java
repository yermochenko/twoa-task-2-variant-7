package by.vsu.twoa.service.exception;

public class ServiceException extends Exception {
	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}
}
