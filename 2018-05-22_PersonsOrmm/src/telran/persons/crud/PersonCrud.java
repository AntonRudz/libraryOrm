package telran.persons.crud;

import java.time.LocalDate;

import javax.persistence.*;

import telran.persons.dto.Person;
@Table(name="persons22")
@Entity
public class PersonCrud {
@Id
	private int id;
private String name;
public void setName(String name) {
	this.name = name;
}
private LocalDate birthDate;
public PersonCrud(Person person) {
	id=person.getId();
	name=person.getName();
	birthDate=person.getBirthdate();
}
public PersonCrud() {}
public int getId() {
	return id;
}
public String getName() {
	return name;
}
public LocalDate getBirthDate() {
	return birthDate;
}
public Person getPerson() {
	Person res=new Person(id, name, birthDate);
	return res;
}
}
