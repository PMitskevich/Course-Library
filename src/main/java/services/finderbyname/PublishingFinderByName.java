package services.finderbyname;

import dao.PublishingDao;
import model.Publishing;

import java.util.List;

public class PublishingFinderByName implements FinderByName<Publishing> {
    @Override
    public Publishing getByName(String name) {
        List<Publishing> publishings = new PublishingDao().getAll();
        Publishing publishing = null;

        for (Publishing anotherPublishing: publishings)
            if (anotherPublishing.getPublishingName().equals(name)) {
                publishing = anotherPublishing;
                break;
            }

        return publishing;
    }
}
