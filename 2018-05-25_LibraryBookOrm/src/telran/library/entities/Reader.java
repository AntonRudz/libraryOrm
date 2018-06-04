package telran.library.entities;
import java.util.List;

import javax.persistence.*;

import telran.library.dto.ReaderDto;
@Table(name="readers")
@Entity
public class Reader {
@Id
int id;
String name;
int birthYear;
long phoneNumber;
@OneToMany(mappedBy="reader")
List<Record> records;
public Reader() {
	
}

public Reader(int id, String name, int birthYear, long phoneNumber) {
	super();
	this.id = id;
	this.name = name;
	this.birthYear = birthYear;
	this.phoneNumber = phoneNumber;
}


public int getId() {
	return id;
}

public String getName() {
	return name;
}
public List<Record> getRecords() {
	return records;
}
public long getPhoneNumber() {
	return phoneNumber;
}
public void setPhoneNumber(long phoneNumber) {
	this.phoneNumber = phoneNumber;
}
public int getBirthYear() {
	return birthYear;
}
public ReaderDto getReaderDto() {
	return new ReaderDto(id, name, birthYear, phoneNumber);
}
}
