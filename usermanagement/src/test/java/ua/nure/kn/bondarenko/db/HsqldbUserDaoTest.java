package ua.nure.kn.bondarenko.db;

import java.util.Collection;
import java.util.Date;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;

import ua.nure.kn.bondarenko.User;

public class HsqldbUserDaoTest extends DatabaseTestCase {
	/**
	 * Constants FIND_ID, UPDATE_ID, DELETE_ID are used in tests such as:
	 *testFind(), testUpdateById(), testDeleteById()
	 */
	
	private static final Long FIND_ID = 1000L;
	private static final Long UPDATE_ID = 1001L;
	private static final Long DELETE_ID = 1002L;
	private HsqldbUserDao dao;
	private ConnectionFactory connectionFactory;
	
	
	@Override
	protected void setUp() throws Exception {
				super.setUp();
				dao=new HsqldbUserDao(connectionFactory);
	}

	public void testCreate(){
		User user= new User(); 
		user.setFirstName("John");
		user.setLastName("Doe");
		user.setDate(new Date());
		assertNull(user.getId());
		User createdUser;
		try {
			createdUser = dao.create(user);
			assertNotNull(createdUser);
			assertNotNull(createdUser.getId());
			assertEquals(user.getFullName(), createdUser.getFullName());
			assertEquals(user.getAge(), createdUser.getAge());
		} catch (DatabaseException e) {
			fail(e.toString());
		}
		
		
	}
	
	
	public void testFindAll(){		
		try {
			Collection collection = dao.findAll();
			assertNotNull("Collection is null", collection);
            assertEquals("Collection size.", 4, collection.size());
		} catch (DatabaseException e) {
			fail(e.toString());
		}
	}
	
	public void testFind(){		
        try {
            User user = dao.find(FIND_ID);
            assertNotNull(user);
            assertEquals("Search user by id", FIND_ID, user.getId());
        } catch (DatabaseException e) {
            fail(e.toString());
        }
		
	}
	
	public void testUpdateById(){		
        try {
            
			User user = dao.find(UPDATE_ID);
			assertNotNull(user);
			
			user.setFirstName("John");
			dao.update(user);
			User updatedUser = dao.find(UPDATE_ID);
            assertNotNull( updatedUser);
            assertEquals(user.getFirstName(), updatedUser.getFirstName());
        } catch (DatabaseException e) {
            fail(e.toString());
        }
		
	}
	
	public void testDeleteById(){		
		User user = null;
        try {
            
			user = dao.find(DELETE_ID);
			assertNotNull(user);
			dao.delete(user);
			User deletedUser = dao.find(DELETE_ID);
            assertNotNull(deletedUser);            
        } catch (DatabaseException e) {
            fail(e.toString());
        }
		
	}
	
	

	@Override
	protected IDatabaseConnection getConnection() throws Exception {
		connectionFactory=new ConnectionFactoryImpl(
				"org.hsqldb.jdbcDriver",
				"jdbc:hsqldb:file:db/usermanagement",
				"sa",
				"");
		return new DatabaseConnection(connectionFactory.createConnection());
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		IDataSet dataSet=new XmlDataSet(getClass().getClassLoader().getResourceAsStream("usersDataSet.xml"));
		return dataSet;
	}

}
