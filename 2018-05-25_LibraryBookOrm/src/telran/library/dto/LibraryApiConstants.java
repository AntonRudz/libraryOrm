package telran.library.dto;

public interface LibraryApiConstants {
String ADD_BOOK="/book/add";
String ADD_AUTHOR="/author/add";
String ADD_READER="/reader/add";
String PICK_BOOK="/book/pick";
String RETURN_BOOK="/book/return";
String GET_BOOK_AUTHORS="/book/authors/get";
String GET_AIUTHOR_BOOKS="/author/books/get";
String GET_READERS_DELAING_BOOKS="/reader/book/delay/get";

String BOOK_ISBN="isbn";
String AUTHOR_NAME="name";
String GET_MOST_POPULAR_BOOKS = "/book/getpopular";
String YEAR_FROM = "yearFrom";
String YEAR_TO = "yearTo";
String GET_MOST_ACTIVE_READERS = "/reader/mostactive";
}
