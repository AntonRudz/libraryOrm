package telran.persons.dto;

public interface PersonsApiConstants {
	//Methods
String ADD_PERSON="/person/add";
String GET_PERSON="/person/get";
String UPDATE_NAME="/person/name/update";
String REMOVE_PERSON="/person/remove";
String GET_PERSONS_DATES = "/persons/dates/get";
String GET_PERSONS_NAME="/persons/name/get";
//Parameters
String PERSON_ID="id";
String BIRTHDATE_FROM="from";
String BIRTHDATE_TO="to";
String NAME="name";

}
