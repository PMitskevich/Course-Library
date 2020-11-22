package services.finderbyid;

import dao.LibrarianDao;
import model.Librarian;

import java.util.List;

public class LibrarianFinderById implements FinderById {
    @Override
    public Long getById(long id) {
        List<Librarian> librarians = new LibrarianDao().getAll();
        long librarianId = 0;

        for (Librarian librarian: librarians)
            if (librarian.getUserId() == id) {
                librarianId = librarian.getLibrarianId();
                break;
            }

        return librarianId;
    }
}
