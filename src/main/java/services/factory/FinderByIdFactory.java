package services.factory;

import services.finderbyid.BookFinderById;
import services.finderbyid.FinderById;
import services.finderbyid.LibrarianFinderById;
import services.finderbyid.VisitorFinderById;

public final class FinderByIdFactory {
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
                throw new RuntimeException("Неизвестный тип finder");
        }
        return finder;
    }
}
