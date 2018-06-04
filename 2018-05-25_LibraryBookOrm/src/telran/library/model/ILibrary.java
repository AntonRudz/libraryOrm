package telran.library.model;


import java.util.List;

import telran.library.dto.AuthorDto;
import telran.library.dto.BookDto;
import telran.library.dto.LibraryReturnCode;
import telran.library.dto.ReaderDto;
import telran.library.dto.ReturnBookData;

public interface ILibrary {
LibraryReturnCode addAuthor(AuthorDto author);
LibraryReturnCode addBook(BookDto book);
LibraryReturnCode pickBook(int readerId, long isbn, String pickDate);
LibraryReturnCode addReader(ReaderDto reader);
LibraryReturnCode returnBook(ReturnBookData returnBook);
List<ReaderDto>  getReadersDelayingBooks();//readers  delaying  books  
List<AuthorDto>  getBookAuthors(long isbn);//authors  of  a  given  book  
List<BookDto>  getAuthorBooks(String  authorName); //books  written  by  agiven  author
}
