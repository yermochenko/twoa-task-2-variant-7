package by.vsu.twoa.service.exception;

public class TaskNotExistsException extends ServiceException {
	public TaskNotExistsException(Integer userId) {
		super(String.format("Task with ID = %d is not exists", userId));
	}
}
