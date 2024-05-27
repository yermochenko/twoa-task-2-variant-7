package by.vsu.twoa.dao;

import by.vsu.twoa.domain.Task;
import by.vsu.twoa.domain.User;
import by.vsu.twoa.geometry.Point;
import by.vsu.twoa.geometry.Triangle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskDao extends BaseDao<Task> {
	public List<Task> readByOwner(Integer ownerId) throws DaoException {
		String sql = "SELECT \"id\", \"owner_id\", \"name\", \"created\", \"x_1\", \"y_1\", \"x_2\", \"y_2\", \"x_3\", \"y_3\" FROM \"task\" WHERE \"owner_id\" = ?";
		List<Task> tasks = new ArrayList<>();
		readWithCriteria(sql, statement -> statement.setInt(1, ownerId), tasks::add);
		return tasks;
	}

	@Override
	protected String select() {
		return "SELECT \"id\", \"owner_id\", \"name\", \"created\", \"x_1\", \"y_1\", \"x_2\", \"y_2\", \"x_3\", \"y_3\" FROM \"task\" WHERE \"id\" = ?";
	}

	@Override
	protected String insert() {
		return "INSERT INTO \"task\" (\"owner_id\", \"name\", \"created\", \"x_1\", \"y_1\", \"x_2\", \"y_2\", \"x_3\", \"y_3\") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	}

	@Override
	protected Task extractEntity(ResultSet resultSet) throws SQLException {
		Task task = new Task();
		task.setId(resultSet.getInt("id"));
		task.setOwner(new User());
		task.getOwner().setId(resultSet.getInt("owner_id"));
		task.setName(resultSet.getString("name"));
		task.setCreated(new java.util.Date(resultSet.getDate("created").getTime()));
		double x1 = resultSet.getDouble("x_1");
		double y1 = resultSet.getDouble("y_1");
		Point vertex1 = new Point(x1, y1);
		double x2 = resultSet.getDouble("x_2");
		double y2 = resultSet.getDouble("y_2");
		Point vertex2 = new Point(x2, y2);
		double x3 = resultSet.getDouble("x_3");
		double y3 = resultSet.getDouble("y_3");
		Point vertex3 = new Point(x3, y3);
		task.setTriangle(new Triangle(vertex1, vertex2, vertex3));
		return task;
	}

	@Override
	protected void fillInsertedEntity(PreparedStatement statement, Task task) throws SQLException {
		statement.setInt(1, task.getOwner().getId());
		statement.setString(2, task.getName());
		statement.setDate(3, new java.sql.Date(task.getCreated().getTime()));
		statement.setDouble(4, task.getTriangle().getVertex1().getX());
		statement.setDouble(5, task.getTriangle().getVertex1().getY());
		statement.setDouble(6, task.getTriangle().getVertex2().getX());
		statement.setDouble(7, task.getTriangle().getVertex2().getY());
		statement.setDouble(8, task.getTriangle().getVertex3().getX());
		statement.setDouble(9, task.getTriangle().getVertex3().getY());
	}
}
