package lk.grp.synergy.db;

import lk.grp.synergy.model.Notification;
import lk.grp.synergy.model.NotificationReq;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by CM on 2017-02-13.
 */
public class NotificationReqDAO {

    public NotificationReqDAO() {

    }

    public ArrayList<NotificationReq> getPendingNotificationReqs() throws SQLException, NamingException, ClassNotFoundException {
        ArrayList<NotificationReq> notifications = new ArrayList();
        String sql = "SELECT * FROM notification_req WHERE processed IS NULL;";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement prStmt = con.prepareStatement(sql)
        ) {
            ResultSet resultSet = prStmt.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String courseCode = resultSet.getString("course_code");
                    String msg = resultSet.getString("msg");
                    int processed = resultSet.getInt("processed");

                    notifications.add(new NotificationReq(id, courseCode, msg, processed));
                }
            }
        }

        return notifications;
    }

    public boolean setNotificationReqs(NotificationReq n) throws SQLException, NamingException, ClassNotFoundException {
        String sql = "UPDATE notification_req SET processed=? WHERE id=?;";
        boolean updated = false;
        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement pstm = con.prepareStatement(sql)
        ) {
            pstm.setInt(1, 1);
            pstm.setInt(2, n.getID());

            updated = pstm.executeUpdate() == 1;
        }
        return updated;
    }
}
