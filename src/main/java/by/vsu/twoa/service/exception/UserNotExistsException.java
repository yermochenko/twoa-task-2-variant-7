package by.vsu.twoa.service.exception;

public class UserNotExistsException extends ServiceException {
	public UserNotExistsException(Integer userId) {
		super(String.format("User with ID = %d is not exists", userId));
	}
}
