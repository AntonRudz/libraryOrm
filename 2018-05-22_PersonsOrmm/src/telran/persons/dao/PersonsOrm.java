package telran.persons.dao;



import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.persons.crud.PersonCrud;
import telran.persons.dto.Person;
import telran.persons.orm.repo.PersonsJpaRepository;
@Service
public class PersonsOrm implements IPersonsDao {
@Autowired
	PersonsJpaRepository repository;
	@Override
	@Transactional
	public boolean addPerson(Person person) {
		if(repository.existsById(person.getId()))
			return false;
		repository.save(new PersonCrud(person));
		return true;
	}

	@Override
	@Transactional(readOnly=true)
	public Person getPerson(int id) {
		PersonCrud res=repository.findById(id).orElse(null);
		return res==null?null:res.getPerson();
		
	}

	@Override
	@Transactional
	public boolean updateName(int id, String newName) {
		PersonCrud person=repository.findById(id).orElse(null);
		if(person==null)
			return false;
		person.setName(newName);
		return true;
	}

	@Override
	@Transactional
	public boolean removePerson(int id) {
		if(!repository.existsById(id))
		 return false;
		repository.deleteById(id);
		return true;
	}

	@Override
	public List<Person> getPersonsByDates(LocalDate from, LocalDate to) {
		List<PersonCrud> persons=
		repository.findByBirthDateBetween(from,to);
		return toListPerson(persons);
	}
	

	private List<Person> toListPerson(List<PersonCrud> persons) {
		return persons.stream().map(PersonCrud::getPerson)
				.collect(Collectors.toList());
	}

	@Override
	public List<Person> getPersonsByName(String name) {
		
		return toListPerson(repository.findByName(name));
	}

}
