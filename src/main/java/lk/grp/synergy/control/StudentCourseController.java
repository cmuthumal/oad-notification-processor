package lk.grp.synergy.control;

import lk.grp.synergy.db.NotificationReqDAO;
import lk.grp.synergy.db.StudentCourseDAO;
import lk.grp.synergy.model.NotificationReq;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CM on 2017-02-13.
 */
public class StudentCourseController {
    private StudentCourseDAO studentCourseDAO;

    public StudentCourseController() {
        studentCourseDAO = new StudentCourseDAO();
    }

    public List<String> getCourseList(int studentId) throws SQLException, NamingException, ClassNotFoundException {
        return studentCourseDAO.getCourseList(studentId);
    }

    public List<Integer> getStudentsList(String courseCode) throws SQLException, NamingException, ClassNotFoundException {
        return studentCourseDAO.getStudentsList(courseCode);
    }

    public String getEmail(String courseCode) throws SQLException, NamingException, ClassNotFoundException {
        return studentCourseDAO.getEmail(courseCode);
    }
}
