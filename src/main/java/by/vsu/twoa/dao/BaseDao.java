package by.vsu.twoa.dao;

import by.vsu.twoa.domain.Entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

abstract public class BaseDao<T extends Entity> {
	private Connection connection;

	protected Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
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
			try { Objects.requireNonNull(resultSet).close(); } catch(SQLException ignored) {}
			try { Objects.requireNonNull(statement).close(); } catch(SQLException ignored) {}
		}
	}

	abstract protected T extractEntity(ResultSet resultSet) throws SQLException;

	protected interface SearchCriteriaFiller {
		void fillSearchCriteria(PreparedStatement statement) throws SQLException;
	}

	protected interface FoundEntityHandler<E extends Entity> {
		void handleFoundEntity(E entity);
	}
}
