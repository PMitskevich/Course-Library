package services.finderbyname;

import dao.BookDao;
import model.Book;

import java.util.List;

public class BookFinderByName implements FinderByName<Book> {
    @Override
    public Book getByName(String name) {
        List<Book> books = new BookDao().getAll();
        Book book = null;

        for (Book anotherBook: books)
            if (anotherBook.getName().equals(name)) {
                book = anotherBook;
                break;
            }

        return book;
    }
}
