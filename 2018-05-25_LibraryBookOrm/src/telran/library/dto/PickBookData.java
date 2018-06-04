package telran.library.dto;


public class PickBookData {
//TODO all fields required for picking book
//(readerId, isbn, pickDate (better to use string ISO with parsing on server)
	public int readerId;
	public long isbn;
	public String pickDate;
	public PickBookData() {
		
	}
	public PickBookData(int readerId, long isbn, String pickDate) {
		super();
		this.readerId = readerId;
		this.isbn = isbn;
		this.pickDate = pickDate;
	}
	public long getReaderId() {
		return readerId;
	}
	public long getIsbn() {
		return isbn;
	}
	public String getPickDate() {
		return pickDate;
	}
	
}
