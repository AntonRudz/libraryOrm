package telran.library.dto;

public class ReturnBookData {
public int readerId;
public long bookIsbn;
public String returnDate;

public ReturnBookData() {
	
}

public ReturnBookData(int readerId, long bookIsbn, String returnDate) {
	super();
	this.readerId = readerId;
	this.bookIsbn = bookIsbn;
	this.returnDate = returnDate;
}

public int getReaderId() {
	return readerId;
}

public long getBookIsbn() {
	return bookIsbn;
}

public String getReturnDate() {
	return returnDate;
}

}
