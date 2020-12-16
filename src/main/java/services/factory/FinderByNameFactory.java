package services.factory;

import dao.AuthorDao;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import services.finderbyname.AuthorFinderByName;
import services.finderbyname.BookFinderByName;
import services.finderbyname.FinderByName;
import services.finderbyname.PublishingFinderByName;

public final class FinderByNameFactory {
    private static final Logger LOGGER = LogManager.getLogger(FinderByNameFactory.class);
    private static FinderByNameFactory instance;

    private FinderByNameFactory() {
    }

    public static FinderByNameFactory getInstance() {
        if (instance == null) {
            instance = new FinderByNameFactory();
        }
        return instance;
    }

    public static FinderByName createFinder(String type) {
        FinderByName finder = null;
        switch (type) {
            case "author":
                finder = new AuthorFinderByName();
                break;
            case "book":
                finder = new BookFinderByName();
                break;
            case "publishing":
                finder = new PublishingFinderByName();
                break;
            default:
                LOGGER.log(Level.ERROR, "Неизвестный тип finder");
                throw new RuntimeException("Неизвестный тип finder");
        }
        return finder;
    }
}
