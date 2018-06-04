package telran.persons.dto;

public class IdName {
public int id;
public String name;
public IdName(int id, String name) {
	super();
	this.id = id;
	this.name = name;
}
public IdName() {}
public int getId() {
	return id;
}
public String getName() {
	return name;
}

}
