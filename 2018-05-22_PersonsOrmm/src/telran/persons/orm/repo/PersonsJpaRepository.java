package telran.persons.orm.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.persons.crud.PersonCrud;

public interface PersonsJpaRepository extends
JpaRepository<PersonCrud, Integer> {

	List<PersonCrud> findByBirthDateBetween(LocalDate from,
			LocalDate to);
	List<PersonCrud> findByName(String name);

}
