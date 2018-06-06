package telran.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.*;

import telran.library.model.ILibrary;
import telran.library.dto.*;
@SpringBootApplication
@RestController
@ComponentScan("telran.library.model")
@EnableJpaRepositories("telran.library.repo")
@EntityScan("telran.library.entities")
public class LibraryRestControllerAppl {
@Autowired
ILibrary library;
@PostMapping(value=LibraryApiConstants.ADD_BOOK)
LibraryReturnCode addBook(@RequestBody BookDto book) {
	return library.addBook(book);
}
@PostMapping(value=LibraryApiConstants.ADD_AUTHOR)
LibraryReturnCode addAuthor(@RequestBody AuthorDto author) {
	return library.addAuthor(author);
}
	
@PostMapping(value=LibraryApiConstants.ADD_READER)
LibraryReturnCode addReader(@RequestBody ReaderDto reader) {
	return library.addReader(reader);
}
@PostMapping(value=LibraryApiConstants.PICK_BOOK)
LibraryReturnCode pickBook(@RequestBody PickBookData data) {
	return library.pickBook(data.readerId, data.isbn, data.pickDate);
}
@PostMapping(value=LibraryApiConstants.RETURN_BOOK)
LibraryReturnCode returnBook(@RequestBody ReturnBookData data) {
	return library.returnBook(data);
}
@GetMapping(value=LibraryApiConstants.GET_BOOK_AUTHORS)
List<AuthorDto> getBookAuthors(@RequestParam(name=LibraryApiConstants.BOOK_ISBN) long isbn){
	return library.getBookAuthors(isbn);
}
@GetMapping(value=LibraryApiConstants.GET_AIUTHOR_BOOKS)
List<BookDto> getAuthorBooks(@RequestParam(name=LibraryApiConstants.AUTHOR_NAME) String authorName){
	return library.getAuthorBooks(authorName);
}
@GetMapping(value=LibraryApiConstants.GET_READERS_DELAING_BOOKS)
List<ReaderDto> getReadersDelaingBooks(){
	return library.getReadersDelayingBooks();
}
@GetMapping(value=LibraryApiConstants.GET_MOST_POPULAR_BOOKS)
List<BookDto> getMostPopularBooks(@RequestParam(name=LibraryApiConstants.YEAR_FROM)int yearFrom
		,@RequestParam(name=LibraryApiConstants.YEAR_TO)int yearTo){
	return library.getMostPopularBooks(yearFrom, yearTo);
}
@GetMapping(value=LibraryApiConstants.GET_MOST_ACTIVE_READERS)
List<ReaderDto> getMostActiveReaders(){
	return library.getMostActiveReaders();
}
public static void main(String[] args) {
	SpringApplication.run
	(LibraryRestControllerAppl.class, args);

}
}
