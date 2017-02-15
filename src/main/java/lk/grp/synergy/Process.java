package lk.grp.synergy;

import lk.grp.synergy.control.NotificationController;
import lk.grp.synergy.control.NotificationReqController;
import lk.grp.synergy.control.StudentController;
import lk.grp.synergy.control.StudentCourseController;
import lk.grp.synergy.model.Notification;
import lk.grp.synergy.model.NotificationReq;
import lk.grp.synergy.model.Student;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Process {

    public static void main(String[] args) {
        while (true){
            processNotifications();
        }
    }

    private static void processNotifications() {
        NotificationController notificationController;
        NotificationReqController notificationReqController;
        StudentCourseController studentCourseController;
        StudentController studentController;

        ArrayList<NotificationReq> requestsList = null;
        ArrayList<Integer> studentIDList = null;

        notificationController = new NotificationController();
        notificationReqController = new NotificationReqController();
        studentCourseController = new StudentCourseController();
        studentController = new StudentController();

        try {
            requestsList = notificationReqController.getPendingNotificationReqs();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        for (int j = 0; j < requestsList.size(); j++) {
            NotificationReq nr = requestsList.get(j);
            try {
                studentIDList = (ArrayList<Integer>) studentCourseController.getStudentsList(nr.getCourseCode());
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (NamingException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < studentIDList.size(); i++) {
                int notID = 0;
                int studentID = studentIDList.get(i);
                String msg = nr.getCourseCode() + " " + nr.getMsg();
                int channel = 1;
                String to = "";

                try {
                    to = studentController.getStudentById(studentID).getEmail();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (NamingException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                LocalDateTime schTime = LocalDateTime.now();
                LocalDateTime delTime = null;

                Notification n = new Notification(notID, studentID, msg, channel, to, schTime, delTime);
                System.out.println(n.getMessage());

                try {
                    notificationController.addNotification(n);
                    notificationReqController.setNotificationReqs(nr);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (NamingException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
