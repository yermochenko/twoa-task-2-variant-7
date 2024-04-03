package by.vsu.twoa.dao;

import by.vsu.twoa.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
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
}
