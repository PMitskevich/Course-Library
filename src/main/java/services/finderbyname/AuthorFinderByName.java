package services.finderbyname;

import dao.AuthorDao;
import model.Author;

import java.util.List;

public class AuthorFinderByName implements FinderByName<Author> {
    @Override
    public Author getByName(String authorName) {
        List<Author> authors = new AuthorDao().getAll();
        Author author = null;

        for (Author anotherAuthor: authors)
            if (anotherAuthor.getFullName().equals(authorName)) {
                author = anotherAuthor;
                break;
            }

        return author;
    }
}
