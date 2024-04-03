package by.vsu.twoa.service.exception;

public class UserNotAuthenticatedException extends ServiceException {
	public UserNotAuthenticatedException(String login, String password) {
		super(String.format("User with login = \"%s\" and password = \"%s\" is not authenticated", login, password));
	}
}
