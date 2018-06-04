package telran.persons.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import telran.persons.dao.*;
import telran.persons.dto.*;
@SpringBootApplication
@ComponentScan(basePackages="telran.persons.dao")
@EnableJpaRepositories("telran.persons.orm.repo")
@EntityScan("telran.persons.crud")
@RestController
public class PersonsRestController {
@Autowired
	IPersonsDao persons;
@GetMapping(value=PersonsApiConstants.GET_PERSONS_DATES)
List<Person> getPersonsDates(@RequestParam(name=
PersonsApiConstants.BIRTHDATE_FROM)
		String from, @RequestParam(name=
				PersonsApiConstants.BIRTHDATE_TO)
String to)
{
	return persons.getPersonsByDates(LocalDate.parse(from),
			LocalDate.parse(to));
}
@GetMapping(value=PersonsApiConstants.GET_PERSONS_NAME)
List<Person> getPersonsName(@RequestParam(name=
PersonsApiConstants.NAME )String name)
{
	return persons.getPersonsByName(name);
}
@GetMapping(value=PersonsApiConstants.GET_PERSON)
Person getPerson(@RequestParam(name=PersonsApiConstants.PERSON_ID) int id) {
	return persons.getPerson(id);
}
@PostMapping(value=PersonsApiConstants.ADD_PERSON)
boolean addPerson(@RequestBody Person person) {
	return persons.addPerson(person);
}
@PostMapping(value=PersonsApiConstants.REMOVE_PERSON)
boolean removePerson(@RequestBody int id) {
	return persons.removePerson(id);
}
@PostMapping(value=PersonsApiConstants.UPDATE_NAME)
boolean updateAddress(@RequestBody IdName idName) {
	return persons.updateName(idName.id, idName.name);
}
	public static void main(String[] args) {
		SpringApplication.run(PersonsRestController.class, args);

	}

}
