packagepackage main.java.ua.kn.bondarenko.db;

import java.sql.Connection;

public interface ConnectionFactory {
	Connection createConnection()throws DatabaseException;

}
