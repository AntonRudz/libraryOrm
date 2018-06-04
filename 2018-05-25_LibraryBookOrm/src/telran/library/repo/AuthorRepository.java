package telran.library.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.library.entities.Author;

public interface AuthorRepository extends
JpaRepository<Author, String> {

}
