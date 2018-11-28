package package main.java.ua.kn.bondarenko.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

import ua.nure.kn156.hondak.User;

 class HsqldbUserDao implements UserDAO {
	 
	 /**
		 * Constants INSERT_QUERY, DELETE_QUERY, UPDATE_QUERY, FIND_QUERY are used 
		 * for inserting, deleting, updating and selecting data in DB users table
		 */

	private static final String DELETE_USERS_QUERY = "DELETE FROM users WHERE id = ?";
	private static final String UPDATE_QUERY = "UPDATE users SET firstname = ?, lastname = ?, dateofbirth = ? WHERE id = ?";
	private static final String SELECT_USERS_BY_ID = "SELECT id, firstname, lastname, dateofbirth FROM users WHERE id = ?";
	private static final String SELECT_ALL_QUERY = "SELECT id, firstname, lastname, dateofbirth FROM users";
	private static final String INSERT_QUERY = "INSERT INTO users (firstname, lastname, dateofBirth) VALUES (?, ?, ?) ";
	private ConnectionFactory connectionFactory;

	public HsqldbUserDao() {
		
	}
	
	public HsqldbUserDao(ConnectionFactory connectionFactory) {
		this.connectionFactory=connectionFactory;
	}
	
	
	

	public ConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}




	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}




	@Override
	public User create(User user) throws DatabaseException {
		try{
			Connection connection=connectionFactory.createConnection();
			PreparedStatement statement=connection
					.prepareStatement(INSERT_QUERY);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setDate(3, new Date(user.getDate().getTime()));
			int n=statement.executeUpdate();
			if(n!=1){
				throw new DatabaseException("Number of inserted rows"+n);
			}
			CallableStatement callableStatement=connection
					.prepareCall("call IDENTITY()");
			ResultSet keys=callableStatement.executeQuery();
			User insertedUser=new User(user);
			if(keys.next()){
				insertedUser.setId(keys.getLong(1));
				
			}
			keys.close();
			callableStatement.close();
			connection.close();
			return insertedUser;
		}catch(DatabaseException e){
			throw e;
			
		}catch(SQLException e){
			throw new DatabaseException(e);
		}
		
	}

	@Override
	public User find(Long id) throws DatabaseException {		
		try {
			Connection connection = connectionFactory.createConnection();
			PreparedStatement statement = connection.prepareStatement(SELECT_USERS_BY_ID);
			statement.setLong(1, id);
			ResultSet rs=statement.executeQuery();
			User user = new User();
			while (rs.next()) {				
				user.setId(rs.getLong(1));
				user.setFirstName(rs.getString(2));
				user.setLastName(rs.getString(3));
				user.setDate(rs.getDate(4));
				
			}
			rs.close();
			statement.close();
			connection.close();
			return user;
		} catch(DatabaseException e) {
			throw e;
		} catch(SQLException e) {
			throw new DatabaseException(e);
		} 
		
	}

	@Override
	public void update(User user) throws DatabaseException {		
		try {
			Connection connection=connectionFactory.createConnection();
			PreparedStatement statement=connection
					.prepareStatement(UPDATE_QUERY);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setDate(3, new Date(user.getDate().getTime()));
			statement.setLong(4, user.getId());
			int n=statement.executeUpdate();
			if(n!=1){
				throw new DatabaseException("Number of inserted rows"+n);
			}
			connection.close();
			statement.close();
		} catch(DatabaseException e) {
			throw e;
		} catch(SQLException e) {
			throw new DatabaseException(e);
		} 
		

	}

	@Override
	public void delete(User user) throws DatabaseException {
		try {
			Connection connection=connectionFactory.createConnection();
			PreparedStatement statement=connection
					.prepareStatement(DELETE_USERS_QUERY);
			statement.setLong(1, user.getId());
			int n=statement.executeUpdate();
			if(n!=1){
				throw new DatabaseException("Number of inserted rows"+n);
			}
			connection.close();
			statement.close();
		} catch(DatabaseException e) {
			throw e;
		} catch(SQLException e) {
			throw new DatabaseException(e);
		} 

	}
	
      
	
	@Override
	public Collection<User> findAll() throws DatabaseException {
		Collection<User> result = new LinkedList<User>();
		try {
			Connection connection = connectionFactory.createConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY);
			User user;
			while (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getLong(1));
				user.setFirstName(resultSet.getString(2));
				user.setLastName(resultSet.getString(3));
				user.setDate(resultSet.getDate(4));
				result.add(user);
			}
			
			resultSet.close();
			statement.close();
			connection.close();
		} catch(DatabaseException e) {
			throw e;
		} catch(SQLException e) {
			throw new DatabaseException(e);
		}
		return result;
	}
	
	
}
