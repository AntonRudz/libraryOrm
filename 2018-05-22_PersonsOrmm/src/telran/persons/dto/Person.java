package telran.persons.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Person {
int id;
String name;
LocalDate birthdate;
public Person(int id, String name, LocalDate birthdate) {
	super();
	this.id = id;
	this.name = name;
	this.birthdate = birthdate;
}
public Person() {}
@Override
public String toString() {
	return "Person [id=" + id + ", name=" + name + ", birthdate=" + birthdate + "]";
}
public int getId() {
	return id;
}
public String getName() {
	return name;
}
public LocalDate getBirthdate() {
	return birthdate;
}

}
