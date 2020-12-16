package dao;

import model.Author;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthorDaoTest {
    private static AuthorDao authorDao;

    @BeforeAll
    public static void init() {
        authorDao = new AuthorDao();
    }

    @Test
    public void getAllAuthorDaoTest() {
        List<Author> expectedAuthors = new ArrayList<>();
        expectedAuthors.add(new Author(1, "Александр", "Пушкин", "Сергеевич"));
        expectedAuthors.add(new Author(2, "Михаил", "Лермонтов", "Юрьевич"));
        expectedAuthors.add(new Author(3, "Лев", "Толстой", "Николаевич"));
        List<Author> actualAuthors = authorDao.getAll();
        assertEquals(expectedAuthors, actualAuthors);
    }
}
