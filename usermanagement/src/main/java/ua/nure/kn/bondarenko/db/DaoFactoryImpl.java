package package main.java.ua.kn.bondarenko.db;

public class DaoFactoryImpl extends DaoFactory {

	@Override
	public UserDAO getUserDao(){
		UserDAO result=null;
		try{
			Class clazz =Class.forName(properties.getProperty(USER_DAO));
			result=(UserDAO) clazz.newInstance();
			result.setConnectionFactory(getConnectionFactory());
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return result;
	}
}
