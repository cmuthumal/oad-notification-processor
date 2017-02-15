package lk.grp.synergy.control;

import lk.grp.synergy.db.StudentDAO;
import lk.grp.synergy.model.Student;
import javax.naming.NamingException;
import java.sql.SQLException;

/**
 * Created by isuru on 1/14/17.
 */
public class StudentController {
    private StudentDAO studentDAO;

    public StudentController() {
        studentDAO = new StudentDAO();
    }

    public Student getStudentById(int studentId) throws SQLException, NamingException, ClassNotFoundException {
        return studentDAO.getStudentById(studentId);
    }
}
