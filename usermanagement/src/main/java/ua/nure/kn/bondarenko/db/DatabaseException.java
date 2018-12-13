package package main.java.ua.kn.bondarenko.db;

import java.sql.SQLException;

public class DatabaseException extends Exception {

	public DatabaseException(Exception e) {
		super(e);
	}

	public DatabaseException(String string) {
		super(string);
	}

}
