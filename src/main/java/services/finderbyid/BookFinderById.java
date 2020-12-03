package services.finderbyid;

import dao.BookDao;
import dao.LendingDao;
import model.Book;
import model.Lending;

import java.util.List;

public class BookFinderById implements FinderById {
    @Override
    public Long getById(long id) {
        LendingDao lendingDao = new LendingDao();
        return null;
    }

    public Book getByLending(Lending lending) {
        long bookId = lending.getBookId();
        BookDao bookDao = new BookDao();
        List<Book> books = bookDao.getAll();
        for (Book book: books) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }
        return null;
    }
}
