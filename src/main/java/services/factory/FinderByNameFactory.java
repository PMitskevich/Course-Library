package services.factory;

import services.finderbyname.AuthorFinderByName;
import services.finderbyname.BookFinderByName;
import services.finderbyname.FinderByName;
import services.finderbyname.PublishingFinderByName;

public final class FinderByNameFactory {
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
                throw new RuntimeException("Неизвестный тип finder");
        }
        return finder;
    }
}
