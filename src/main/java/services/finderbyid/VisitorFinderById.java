package services.finderbyid;

import dao.VisitorDao;
import model.Visitor;

import java.util.List;

public class VisitorFinderById implements FinderById {
    @Override
    public Long getById(long id) {
        List<Visitor> visitors = new VisitorDao().getAll();
        long visitorId = 0;

        for (Visitor visitor: visitors)
            if (visitor.getUserId() == id) {
                visitorId = visitor.getVisitorId();
                break;
            }

        return visitorId;
    }
}
