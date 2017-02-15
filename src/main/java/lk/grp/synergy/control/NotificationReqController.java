package lk.grp.synergy.control;

import lk.grp.synergy.db.NotificationReqDAO;
import lk.grp.synergy.model.NotificationReq;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by CM on 2017-02-13.
 */
public class NotificationReqController {
    private NotificationReqDAO notificationDAO;

    public NotificationReqController() {
        notificationDAO = new NotificationReqDAO();
    }

    public ArrayList<NotificationReq> getPendingNotificationReqs() throws SQLException, NamingException, ClassNotFoundException {
        return notificationDAO.getPendingNotificationReqs();
    }

    public boolean setNotificationReqs(NotificationReq n) throws SQLException, NamingException, ClassNotFoundException {
        return notificationDAO.setNotificationReqs(n);
    }
}
