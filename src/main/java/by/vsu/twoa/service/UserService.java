package by.vsu.twoa.service;

import by.vsu.twoa.dao.DaoException;
import by.vsu.twoa.dao.UserDao;
import by.vsu.twoa.domain.User;
import by.vsu.twoa.service.exception.ServiceException;
import by.vsu.twoa.service.exception.UserNotAuthenticatedException;

public class UserService {
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public User authenticate(String login, String password) throws ServiceException {
		try {
			return userDao.readByLoginAndPassword(login, password).orElseThrow(() -> new UserNotAuthenticatedException(login, password));
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	public void register(User user) throws ServiceException {
		try {
			Integer id = userDao.create(user);
			user.setId(id);
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}
}
