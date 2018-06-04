package telran.library.entities;
import javax.persistence.*;

import telran.library.dto.*;
import java.util.*;
import java.util.stream.Collectors;
@Table(name="books")
@Entity
public class Book {
	@Id
	long isbn;
	int amount;
	String title;
	@Enumerated(EnumType.STRING)
	Cover cover;
	int pickPeriod;
	@ManyToMany
	List<Author> authors;
	@OneToMany(mappedBy="book")
	List<Record> records;
	public Book() {}
	public Book(long isbn, int amount, String title, Cover cover, int pickPeriod, List<Author> authors) {
		super();
		this.isbn = isbn;
		this.amount = amount;
		this.title = title;
		this.cover = cover;
		this.pickPeriod = pickPeriod;
		this.authors = authors;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getPickPeriod() {
		return pickPeriod;
	}
	public void setPickPeriod(int pickPeriod) {
		this.pickPeriod = pickPeriod;
	}
	public long getIsbn() {
		return isbn;
	}
	public String getTitle() {
		return title;
	}
	public Cover getCover() {
		return cover;
	}
	public List<Author> getAuthors() {
		return authors;
	}
	
	public BookDto getBookDto() {
		List<String> authorNames=authors.stream().map(Author::getName).collect(Collectors.toList());
		return new BookDto(isbn, title, amount, authorNames, cover, pickPeriod);
	}

}

