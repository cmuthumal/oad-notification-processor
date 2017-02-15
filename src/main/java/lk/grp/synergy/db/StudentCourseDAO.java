package lk.grp.synergy.db;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by isuru on 1/30/17.
 */
public class StudentCourseDAO {

    public List<String> getCourseList(int studentId) throws SQLException, NamingException, ClassNotFoundException {
        String sql = "SELECT course_code FROM student_course WHERE student_id=?";
        ArrayList<String> courseCodes = new ArrayList<>();

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement pstm = con.prepareStatement(sql)
        ) {
            pstm.setInt(1, studentId);
            ResultSet resultSet = pstm.executeQuery();
            while (resultSet != null && resultSet.next()) {
                String courseCode = resultSet.getString("course_code");
                courseCodes.add(courseCode);
            }
        }

        return courseCodes;
    }

    public List<Integer> getStudentsList(String courseCode) throws SQLException, NamingException, ClassNotFoundException {
        String sql = "SELECT student_id FROM student_course WHERE course_code=?";
        ArrayList<Integer> students = new ArrayList<>();

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement pstm = con.prepareStatement(sql)
        ) {
            pstm.setString(1, courseCode);
            ResultSet resultSet = pstm.executeQuery();
            while (resultSet != null && resultSet.next()) {
                int studentID = resultSet.getInt("student_id");
                students.add(studentID);
            }
        }

        return students;
    }

    public String getEmail(String courseCode) throws SQLException, NamingException, ClassNotFoundException {
        String sql = "SELECT email FROM student WHERE student_id IN " +
                "(SELECT student_id FROM student_course WHERE course_code=?);";
        String email = "";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement pstm = con.prepareStatement(sql)
        ) {
            pstm.setString(1, courseCode);
            ResultSet resultSet = pstm.executeQuery();
            while (resultSet != null && resultSet.next()) {
                email = resultSet.getString("email");

            }
        }

        return email;
    }
}
