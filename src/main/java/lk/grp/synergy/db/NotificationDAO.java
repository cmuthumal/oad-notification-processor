package lk.grp.synergy.db;

import lk.grp.synergy.model.Notification;

import javax.naming.NamingException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

/**
 * Created by CM on 2017-02-13.
 */
public class NotificationDAO {

    public NotificationDAO() {

    }

    public boolean addNotification(Notification n) throws SQLException, NamingException, ClassNotFoundException {
        String sql = "INSERT INTO notification (`message`, `channel`, `to`, `scheduled_time`," +
                "`delivered_time`, `student_id`) VALUES (?,?,?,?,?,?);";
        boolean updated = false;
        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement pstm = con.prepareStatement(sql)
        ) {
            pstm.setString(1, n.getMessage());
            pstm.setInt(2, n.getChannel());
            pstm.setString(3, n.getTo());
            pstm.setTimestamp(4, Timestamp.valueOf(n.getScheduledTime()));
            pstm.setTimestamp(5, null);
            pstm.setInt(6, n.getStudentID());

            updated = pstm.executeUpdate() == 1;
        }
        return updated;
    }
}
