package telran.library.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.library.entities.Reader;

public interface ReaderRepository extends JpaRepository<Reader, Integer> {

}
