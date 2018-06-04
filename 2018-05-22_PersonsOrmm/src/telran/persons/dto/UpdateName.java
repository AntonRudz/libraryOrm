package telran.persons.dto;

public class UpdateName {
String newName;
int id;
public UpdateName(String newName, int id) {
	super();
	this.newName = newName;
	this.id = id;
}
public String getNewName() {
	return newName;
}
public int getId() {
	return id;
}
public UpdateName() {
	
}
}
