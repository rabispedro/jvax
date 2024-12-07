package com.jvax.configs.factories;

import java.sql.Connection;
import java.sql.SQLException;

import com.jvax.configs.DatabaseConnectionConfig;
import com.jvax.daos.DoacaoDao;
import com.jvax.daos.DoadorDao;

public class DaoFactory {
	private Connection connection = null;

	public DoacaoDao createDoacaoDao() {
		if (this.connection == null) {
			this.openConnection();
		}
		
		return new DoacaoDao(this.connection);
	}

	public DoadorDao createDoadorDao() {
		if (this.connection == null) {
			this.openConnection();
		}
		
		return new DoadorDao(this.connection);
	}

	public void openConnection() {
		if (this.connection == null) {
			this.connection = DatabaseConnectionConfig.getConnection();
		}
	}

	public void closeConnection() {
		if (this.connection != null) {
			try {
				this.connection.close();
				this.connection = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void beginTransaction() throws SQLException {
		if (this.connection != null) {
			this.connection.setAutoCommit(false);
		}
	}

	public void commitTransaction() throws SQLException {
		try {
			this.connection.commit();
		} finally {
			this.connection.setAutoCommit(true);
		}
	}

	public void rollbackTransaction() throws SQLException {
		try {
			this.connection.rollback();
		} finally {
			this.connection.setAutoCommit(true);
		}
	}
}
