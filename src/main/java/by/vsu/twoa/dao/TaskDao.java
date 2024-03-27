package by.vsu.twoa.dao;

import by.vsu.twoa.domain.Task;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskDao extends BaseDao<Task> {
	public List<Task> readAll() throws DaoException {
		String sql = "SELECT \"id\", \"name\", \"created\" FROM \"task\"";
		List<Task> tasks = new ArrayList<>();
		readWithCriteria(sql, null, tasks::add);
		return tasks;
	}

	@Override
	protected Task extractEntity(ResultSet resultSet) throws SQLException {
		Task task = new Task();
		task.setId(resultSet.getInt("id"));
		task.setName(resultSet.getString("name"));
		task.setCreated(new java.util.Date(resultSet.getDate("created").getTime()));
		return task;
	}
}
