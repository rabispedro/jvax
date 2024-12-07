package com.jvax.configs;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnectionConfig {
	private DatabaseConnectionConfig() {}

	public static Connection getConnection() {
		String driverName = "jdbc:postgresql";
		String host = "localhost";
		String port = "5445";
		String databaseName = "db_doacao_sangue";
		String user = "myuser";
		String password = "secret";

		StringBuilder sb = new StringBuilder(driverName);
		sb.append("://");
		sb.append(host);
		sb.append(":");
		sb.append(port);
		sb.append("/");
		sb.append(databaseName);

		String classDriver = "org.postgresql.Driver";

		Connection connection = null;

		try {
			Class.forName(classDriver);
			connection = DriverManager.getConnection(sb.toString(), user, password);
		} catch (Exception e) {
			System.out.print("Message: ");
			System.out.println(e.getMessage());
			System.out.print("Cause: ");
			System.out.println(e.getCause());

			e.printStackTrace();
		}
		
		return connection;
	}
}
