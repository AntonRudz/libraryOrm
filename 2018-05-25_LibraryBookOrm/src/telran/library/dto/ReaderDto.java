package telran.library.dto;

public class ReaderDto {
public int id;
public String name;
public int birthYear;
public long phoneNumber;
public ReaderDto() {
	
}

public ReaderDto(int id, String name, int birthYear, long phoneNumber) {
	super();
	this.id = id;
	this.name = name;
	this.birthYear = birthYear;
	this.phoneNumber = phoneNumber;
}

public long getId() {
	return id;
}
public String getName() {
	return name;
}

public int getBirthYear() {
	return birthYear;
}

public void setBirthYear(int birthYear) {
	this.birthYear = birthYear;
}

public long getPhoneNumber() {
	return phoneNumber;
}

public void setPhoneNumber(long phoneNumber) {
	this.phoneNumber = phoneNumber;
}

}
