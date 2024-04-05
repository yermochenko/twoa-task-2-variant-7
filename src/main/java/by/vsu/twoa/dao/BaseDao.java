package by.vsu.twoa.dao;

import by.vsu.twoa.domain.Entity;

import java.sql.*;
import java.util.Objects;
import java.util.Optional;

abstract public class BaseDao<T extends Entity> {
	private Connection connection;

	protected Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public Integer create(T entity) throws DaoException {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = getConnection().prepareStatement(insert(), Statement.RETURN_GENERATED_KEYS);
			fillInsertedEntity(statement, entity);
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			resultSet.next();
			return resultSet.getInt(1);
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { Objects.requireNonNull(resultSet).close(); } catch(NullPointerException | SQLException ignored) {}
			try { Objects.requireNonNull(statement).close(); } catch(NullPointerException | SQLException ignored) {}
		}
	}

	public Optional<T> read(Integer id) throws DaoException {
		ValueHolder<T> value = new ValueHolder<>();
		readWithCriteria(select(), statement -> statement.setInt(1, id), value::setValue);
		return value.getValue();
	}

	protected void readWithCriteria(String sql, SearchCriteriaFiller criteria, FoundEntityHandler<T> handler) throws DaoException {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = getConnection().prepareStatement(sql);
			if(criteria != null) {
				criteria.fillSearchCriteria(statement);
			}
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				handler.handleFoundEntity(extractEntity(resultSet));
			}
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { Objects.requireNonNull(resultSet).close(); } catch(NullPointerException | SQLException ignored) {}
			try { Objects.requireNonNull(statement).close(); } catch(NullPointerException | SQLException ignored) {}
		}
	}

	abstract protected String select();
	abstract protected String insert();

	abstract protected T extractEntity(ResultSet resultSet) throws SQLException;
	abstract protected void fillInsertedEntity(PreparedStatement statement, T entity) throws SQLException;

	protected interface SearchCriteriaFiller {
		void fillSearchCriteria(PreparedStatement statement) throws SQLException;
	}

	protected interface FoundEntityHandler<E extends Entity> {
		void handleFoundEntity(E entity);
	}

	protected static class ValueHolder<V> {
		private V value;

		public void setValue(V value) {
			this.value = value;
		}

		public Optional<V> getValue() {
			return Optional.ofNullable(value);
		}
	}
}
