package by.vsu.twoa.config;

import by.vsu.twoa.dao.TaskDao;
import by.vsu.twoa.service.ServiceException;
import by.vsu.twoa.service.TaskService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ServiceFactory implements AutoCloseable {
	private static final Logger log = LogManager.getLogger(ServiceFactory.class);

	private static String jdbcUrl;
	private static String jdbcUser;
	private static String jdbcPassword;

	public static void init(String jdbcDriver, String jdbcUrl, String jdbcUser, String jdbcPassword) throws ClassNotFoundException {
		Class.forName(jdbcDriver);
		ServiceFactory.jdbcUrl = jdbcUrl;
		ServiceFactory.jdbcUser = jdbcUser;
		ServiceFactory.jdbcPassword = jdbcPassword;
	}

	private TaskService taskService;
	public TaskService getTaskService() throws ServiceException {
		if(taskService == null) {
			taskService = new TaskService();
			taskService.setTaskDao(getTaskDao());
		}
		return taskService;
	}

	private TaskDao taskDao;
	public TaskDao getTaskDao() throws ServiceException {
		if(taskDao == null) {
			taskDao = new TaskDao();
			taskDao.setConnection(getConnection());
		}
		return taskDao;
	}

	private Connection connection;
	public Connection getConnection() throws ServiceException {
		if(connection == null) {
			try {
				connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
			} catch(SQLException e) {
				log.fatal("Can't connect to database", e);
				throw new ServiceException(e);
			}
		}
		return connection;
	}

	@Override
	public void close() {
		try { connection.close(); } catch(SQLException ignored) {}
	}
}
