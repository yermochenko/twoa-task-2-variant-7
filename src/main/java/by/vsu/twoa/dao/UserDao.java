package by.vsu.twoa.dao;

import by.vsu.twoa.domain.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Optional;

public class UserDao extends BaseDao<User> {
	public Optional<User> readByLoginAndPassword(String login, String password) throws DaoException {
		String sql = "SELECT \"id\", \"first_name\", \"middle_name\", \"last_name\", \"birthday\", \"login\", \"password\" FROM \"user\" WHERE \"login\" = ? AND \"password\" = ?";
		ValueHolder<User> value = new ValueHolder<>();
		readWithCriteria(sql, statement -> {
			statement.setString(1, login);
			statement.setString(2, password);
		}, value::setValue);
		return value.getValue();
	}

	@Override
	protected String select() {
		return "SELECT \"id\", \"first_name\", \"middle_name\", \"last_name\", \"birthday\", \"login\", \"password\" FROM \"user\" WHERE \"id\" = ?";
	}

	@Override
	protected String insert() {
		return "INSERT INTO \"user\" (\"first_name\", \"middle_name\", \"last_name\", \"birthday\", \"login\", \"password\") VALUES (?, ?, ?, ?, ?, ?)";
	}

	@Override
	protected User extractEntity(ResultSet resultSet) throws SQLException {
		User user = new User();
		user.setId(resultSet.getInt("id"));
		user.setFirstName(resultSet.getString("first_name"));
		user.setMiddleName(resultSet.getString("middle_name"));
		user.setLastName(resultSet.getString("last_name"));
		user.setBirthday(new java.util.Date(resultSet.getDate("birthday").getTime()));
		user.setLogin(resultSet.getString("login"));
		user.setPassword(resultSet.getString("password"));
		return user;
	}

	@Override
	protected void fillInsertedEntity(PreparedStatement statement, User user) throws SQLException {
		statement.setString(1, user.getFirstName());
		if(user.getMiddleName() != null) {
			statement.setString(2, user.getMiddleName());
		} else {
			statement.setNull(2, Types.VARCHAR);
		}
		statement.setString(3, user.getLastName());
		statement.setDate(4, new java.sql.Date(user.getBirthday().getTime()));
		statement.setString(5, user.getLogin());
		statement.setString(6, user.getPassword());
	}
}
