package services.factory;

import dao.AuthorDao;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import services.finderbyid.BookFinderById;
import services.finderbyid.FinderById;
import services.finderbyid.LibrarianFinderById;
import services.finderbyid.VisitorFinderById;

public final class FinderByIdFactory {
    private static final Logger LOGGER = LogManager.getLogger(FinderByIdFactory.class);
    private static FinderByIdFactory instance;

    private FinderByIdFactory() {
    }

    public static FinderByIdFactory getInstance() {
        if (instance == null) {
            instance = new FinderByIdFactory();
        }
        return instance;
    }

    public FinderById createFinder(String type) {
        FinderById finder = null;
        switch (type) {
            case "book":
                finder = new BookFinderById();
                break;
            case "librarian":
                finder = new LibrarianFinderById();
                break;
            case "visitor":
                finder = new VisitorFinderById();
                break;
            default:
                LOGGER.log(Level.ERROR, "Неизвестный тип finder");
                throw new RuntimeException("Неизвестный тип finder");
        }
        return finder;
    }
}
