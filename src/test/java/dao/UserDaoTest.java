package dao;

import model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDaoTest {
    private static UserDao userDao;

    @BeforeAll
    public static void init() {
        userDao = new UserDao();
    }

    @Test
    public void getAllUserDaoTrue() {
        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(new User(1, "admin", "admin", true));
        expectedUsers.add(new User(2, "user", "user", false));
        expectedUsers.add(new User(3, "user1", "user1", false));
        List<User> actualUsers = userDao.getAll();
        assertEquals(expectedUsers, actualUsers);
    }
}
