package telran.library.repo;




import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import telran.library.dto.ReaderDto;
import telran.library.entities.Record;

public interface RecordRepository extends JpaRepository<Record, Integer> {
Record findByReaderIdAndBookIsbnAndReturnDateNull(int readerId,long isbn);
@Query(value="select count(*) from bookrecords join books"
		+ " on book_isbn=isbn join readers"
		+ " on reader_id=readers.id where birth_year between :from and :to"
		+ " group by book_isbn order by count(*) desc limit 1",nativeQuery=true)
Long getMaxCountBooks(@Param("from")int from,@Param("to") int to);
@Query("select book.isbn from Record"
		+ " where reader.birthYear between :from and :to"
		+ " group by book.isbn having count(*)=:count")
List<Long> getBookIsbnPopular(@Param("from")int from,
		@Param("to") int to,@Param("count") Long count);
Stream<Record> findByReturnDateNull();
Stream<Record> findByBookIsbnAndReturnDateNullAndPickDateBefore(long isbn,LocalDate pickDelayed);
@Query(value="select count(*) from bookrecords "
		+ "group by reader_id order by count(*) desc limit 1", nativeQuery=true)
Long getMaxCountReaders();
@Query("select reader.id from Record "
		+ "group by reader.id having count(*)=:count")
List<Integer> getActiveReadersId(@Param("count")Long count);


}
