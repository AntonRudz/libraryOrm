package telran.library.entities;
import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.groups.Default;

@Table(name="bookrecords")
@Entity
public class Record {
@Id
@GeneratedValue
private int id;
LocalDate pickDate;
LocalDate returnDate;
int delayDays;
@ManyToOne
Reader reader;
@ManyToOne
Book book;


public Record() {
	
}
public Record( LocalDate pickDate, Reader reader, Book book) {
	super();
	this.pickDate = pickDate;
	this.reader = reader;
	this.book = book; 
}
public LocalDate getPickDate() {
	return pickDate;
}
public Reader getReader() {
	return reader;
}
public Book getBook() {
	return book;
}
public LocalDate getReturnDate() {
	return returnDate;
}
public void setReturnDate(LocalDate returnDate) {
	this.returnDate = returnDate;
}
public int getDelayDays() {
	return delayDays;
}
public void setDelayDays(int delayDays) {
	this.delayDays = delayDays;
}



}
