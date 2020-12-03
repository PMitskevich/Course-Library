package services.statistics;

import dao.BookDao;
import model.Book;
import model.Lending;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Statistics {
    private static final BookDao bookDao = new BookDao();
    private List<Book> books;

    public Statistics() {
        books = bookDao.getAll();
    }

    public Map<String, Integer> createStatisticsByGenres(List<Lending> lendings) {
        Map<String, Integer> genresAndAmount = new HashMap<>();
        for (Lending lending : lendings) {
            String genre = getGenreById(lending.getBookId());
            if (genresAndAmount.containsKey(genre)) {
                int booksAmount = genresAndAmount.get(genre);
                booksAmount += lending.getLendQuantity();
                genresAndAmount.replace(genre, booksAmount);
            }
            else {
                genresAndAmount.put(genre, lending.getLendQuantity());
            }
        }
        return genresAndAmount;
    }

    public String getGenreById(long bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                return book.getGenre();
            }
        }
        return null;
    }
}
