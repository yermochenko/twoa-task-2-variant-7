package by.vsu.twoa.dao;

import by.vsu.twoa.domain.Task;
import by.vsu.twoa.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskDao extends BaseDao<Task> {
	public List<Task> readByOwner(Integer ownerId) throws DaoException {
		String sql = "SELECT \"id\", \"owner_id\", \"name\", \"created\" FROM \"task\" WHERE \"owner_id\" = ?";
		List<Task> tasks = new ArrayList<>();
		readWithCriteria(sql, statement -> statement.setInt(1, ownerId), tasks::add);
		return tasks;
	}

	@Override
	protected String select() {
		return "SELECT \"id\", \"owner_id\", \"name\", \"created\" FROM \"task\" WHERE \"id\" = ?";
	}

	@Override
	protected Task extractEntity(ResultSet resultSet) throws SQLException {
		Task task = new Task();
		task.setId(resultSet.getInt("id"));
		task.setOwner(new User());
		task.getOwner().setId(resultSet.getInt("owner_id"));
		task.setName(resultSet.getString("name"));
		task.setCreated(new java.util.Date(resultSet.getDate("created").getTime()));
		return task;
	}
}
