package by.vsu.twoa.service;

import by.vsu.twoa.dao.DaoException;
import by.vsu.twoa.dao.TaskDao;
import by.vsu.twoa.domain.Task;
import by.vsu.twoa.service.exception.ServiceException;

import java.util.List;

public class TaskService {
	private TaskDao taskDao;

	public void setTaskDao(TaskDao taskDao) {
		this.taskDao = taskDao;
	}

	public List<Task> findAll() throws ServiceException {
		try {
			return taskDao.readAll();
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}
}
