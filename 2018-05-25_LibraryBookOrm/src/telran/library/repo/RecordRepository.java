package telran.library.repo;




import org.springframework.data.jpa.repository.JpaRepository;
import telran.library.entities.Record;

public interface RecordRepository extends JpaRepository<Record, Integer> {
Record findByReaderIdAndBookIsbnAndReturnDateNull(int readerId,long isbn);

}
