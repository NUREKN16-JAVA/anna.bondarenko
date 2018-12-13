package main.java.ua.kn.bondarenko
import java.util.Calendar;
import java.util.Date;

public class User {
	private Long id;
	private String firstName;
	private String lastName;
	private Date date;

	public User(User user) {
		
		id=user.getId();
		firstName=user.getFirstName();
		lastName=user.getLastName();
		date=user.getDate();
	}
public User(){
	
}
public User(Long id, String firstName, String lastName, Date date ){
	this.id=id;
	this.firstName=firstName;
	this.lastName=lastName;
	this.date=date;
	
}
	public User(String firstName, String lastName, Date date) {
		this.firstName=firstName;
		this.lastName=lastName;
		this.date=date;
}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getFullName() {

		if (getLastName() == null || getFirstName() == null){
			throw new IllegalArgumentException("firstName or lastName is NULL");
		}
		return new StringBuilder(getLastName()).append(",").append(getFirstName()).toString();
	}

	public long getAge() {
		Calendar calendar = Calendar.getInstance();
		long currentYear = calendar.get(Calendar.YEAR);
		long currentDay = calendar.get(Calendar.DAY_OF_YEAR);
		calendar.setTime(getDate());
		long yearOfBirth = calendar.get(Calendar.YEAR);
		long dayOfBirth = calendar.get(Calendar.DAY_OF_YEAR);

		long age = currentYear - yearOfBirth;
		if (currentDay < dayOfBirth) {
			return age - 1;
		} else {
			return age;
		}
	}
	
	@Override
	  public int hashCode() {
	    if (this.getId()==null) {
	      return 0;
	    }
	    return this.getId().hashCode();
	  }
	
	  @Override
	  public boolean equals(Object obj) {
	    if (obj == null) {
	      return false;
	    }
	    if (this== obj) {
	      return true;
	    }
	    if (this.getId()==null && ((User) obj).getId()==null){
	      return true;
	    }
	    return this.getId().equals(((User) obj).getId());
	  }


}
