package telran.persons.dao;

import java.time.LocalDate;
import java.util.List;

import telran.persons.dto.Person;

public interface IPersonsDao {
boolean addPerson(Person person);
Person getPerson(int id);
boolean updateName(int id,String newName);
boolean removePerson(int id);
List<Person> getPersonsByDates(LocalDate from,LocalDate to);
List<Person> getPersonsByName(String name);
}
