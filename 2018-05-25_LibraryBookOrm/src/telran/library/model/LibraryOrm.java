package telran.library.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.library.dto.*;
import telran.library.entities.*;
import telran.library.repo.*;

@Service
public class LibraryOrm implements ILibrary {
	@Autowired
	BookRepository books;
	@Autowired
	AuthorRepository authors;
	@Autowired
	ReaderRepository readers;
	@Autowired
	RecordRepository records;
	static DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Override
	@Transactional

	public LibraryReturnCode addAuthor(AuthorDto author) {
		if (authors.existsById(author.name))
			return LibraryReturnCode.AUTHOR_ALREADY_EXISTS;
		Author authorJpa = new Author(author.name, author.country);
		authors.save(authorJpa);
		return LibraryReturnCode.OK;
	}

	@Override
	@Transactional
	public LibraryReturnCode addBook(BookDto book) {
		if (books.existsById(book.isbn))
			return LibraryReturnCode.BOOK_ALREADY_EXISTS;
		List<Author> authorsJpa = getAuthorsJpa(book.authorNames);
		if (authorsJpa.size() < book.authorNames.size())
			return LibraryReturnCode.NO_AUTHOR;
		Book bookJpa = new Book(book.isbn, book.amount, book.title, book.cover, book.pickPeriod, authorsJpa);
		books.save(bookJpa);
		return LibraryReturnCode.OK;
	}

	private List<Author> getAuthorsJpa(List<String> authorNames) {
		List<Author> res = new ArrayList<>();
		for (String name : authorNames) {
			if (authors.existsById(name)) {
				Author authorJpa = authors.findById(name).get();
				res.add(authorJpa);
			}

		}
		return res;
	}

	@Override
	@Transactional
	public LibraryReturnCode pickBook(int readerId, long isbn, String pickDate) {
		Reader reader = readers.findById(readerId).orElse(null);
		if (reader == null)
			return LibraryReturnCode.NO_READER;
		Book book = books.findById(isbn).get();
		if (book == null)
			return LibraryReturnCode.NO_BOOK;
		int amount = book.getAmount();
		if (amount == 0)
			return LibraryReturnCode.ALL_BOOKS_IN_USE;
		Record readerRecord = records.findByReaderIdAndBookIsbnAndReturnDateNull(readerId, isbn);
		if (readerRecord != null)
			return LibraryReturnCode.READER_NO_RETURNED_BOOK;

		LocalDate pick;
		try {
			pick = LocalDate.parse(pickDate, format);
		} catch (Exception e) {
			return LibraryReturnCode.WRONG_DATE_FORMAT;
		}

		records.save(new Record(pick, reader, book));

		book.setAmount(amount - 1);
		// books.save(book);

		return LibraryReturnCode.OK;
	}

	@Override
	@Transactional
	public LibraryReturnCode addReader(ReaderDto reader) {
		if (readers.existsById(reader.id))
			return LibraryReturnCode.READER_ALREADY_EXISTS;
		readers.save(new Reader(reader.id, reader.name, reader.birthYear, reader.phoneNumber));
		return LibraryReturnCode.OK;
	}

	@Override
	@Transactional
	public LibraryReturnCode returnBook(ReturnBookData returnBook) {
		Record returnRecord = records.findByReaderIdAndBookIsbnAndReturnDateNull(returnBook.readerId,
				returnBook.bookIsbn);
		if (returnRecord == null)
			return LibraryReturnCode.NO_RECORD_FOR_RETURN;
		Book book = returnRecord.getBook();
		int amount = book.getAmount();
		LocalDate returnDate;
		try {
			returnDate = LocalDate.parse(returnBook.returnDate, format);
		} catch (Exception e) {
			return LibraryReturnCode.WRONG_DATE_FORMAT;
		}
		returnRecord.setReturnDate(returnDate);
		int timeInUse = (int) ChronoUnit.DAYS.between(returnRecord.getPickDate(), returnDate);
		if (timeInUse > book.getPickPeriod())
			returnRecord.setDelayDays(timeInUse - book.getPickPeriod());
		// records.save(returnRecord);
		book.setAmount(amount + 1);
		// books.save(book);
		return LibraryReturnCode.OK;
	}

	@Override
	@Transactional
	public List<AuthorDto> getBookAuthors(long isbn) {
		if (!books.existsById(isbn))
			return null;
		return books.findById(isbn).get().getAuthors().stream().map(Author::getAuthorDto).collect(Collectors.toList());

	}

	@Override
	@Transactional
	public List<BookDto> getAuthorBooks(String authorName) {
		if (!authors.existsById(authorName))
			return null;
		return authors.findById(authorName).get().getBooks().stream().map(Book::getBookDto)
				.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public List<ReaderDto> getReadersDelayingBooks() {
		LocalDate current=LocalDate.now();
		//Retrieving books that have been picked and not returned
		Stream <Book> booksPicked=records
			.findByReturnDateNull()
			.map(Record::getBook).distinct();
		Stream <Reader> readersDelaying=
				booksPicked.flatMap(b->records
						.findByBookIsbnAndReturnDateNullAndPickDateBefore
				(b.getIsbn(),
				current.minusDays(b.getPickPeriod())))
				.map(Record::getReader).distinct();
		return toListReaderDto(readersDelaying);
	}

	private List<ReaderDto> toListReaderDto(Stream <Reader> readers) {
		return readers.map(r->r.getReaderDto())
				.collect(Collectors.toList());
	}



	@Override
	@Transactional
	public List<BookDto> getMostPopularBooks(int yearFrom, int yearTo) {
		Long maxCount = records.getMaxCountBooks(yearFrom, yearTo);
		List<BookDto> popularBooks = records.getBookIsbnPopular(yearFrom, yearTo, maxCount).stream()
				.map(i -> books.findById(i).get()).map(b -> b.getBookDto()).collect(Collectors.toList());

		return popularBooks;
	}

	@Override
	public List<ReaderDto> getMostActiveReaders() {
		Long maxCount=records.getMaxCountReaders();
		List<ReaderDto> activReaders=records.getActiveReadersId(maxCount)
				.stream().map(i->readers.findById(i).get())
				.map(r->r.getReaderDto()).collect(Collectors.toList());
		return activReaders;
	}

}
