/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.AssignmentsPerCourseDao.printAssignmentsPerCourse;
import static dao.StudentDao.printStudents;
import entities.Assignment;
import entities.Course;
import entities.Student;
import entities.StudentAssignments;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static school_app_part_b.School_App_part_B.message;
import static school_app_part_b.School_App_part_B.scanner;

/**
 *
 * @author George
 */
public class StudentAssignmentsDao extends Dao {

    static StudentAssignmentsDao sadao = new StudentAssignmentsDao();
    private final String getAllAssignmentsPerStudent = "select sa.said as ID, s.sid as St_ID,\n"
            + "a.aid as Ass_ID,\n"
            + "ac.cid as Course_ID,sa.samark as Total_Mark \n"
            + "\n"
            + "from student as s,assignment as a, assignmentsPerCourse as ac, studentassignments as sa,course as c,headline as h, description as des,\n"
            + "submission as sub, direction as dir, length as l\n"
            + "\n"
            + "where s.sid = sa.sid and ac.acid = sa.acid  and ac.cid = c.cid and a.aid = ac.aid  and a.atitle = h.hid \n"
            + "and a.adescription = des.deid and a.asubtime = sub.subid and dir.did = c.cstream and l.lid = c.ctype \n"
            + "order by sa.said";
    private final String insertAssignmentsToStudent = "insert into studentassignments(acid,sid,samark) values (?,?,?)";

    @Override
    protected void closeConnections(ResultSet rs, Statement st) {
        super.closeConnections(rs, st);
    }

    @Override
    protected Connection getConnection() {
        return super.getConnection();
    }

    public List<StudentAssignments> getStudentAssignments() {
        List<StudentAssignments> list = new ArrayList();
        try {
            Connection con = getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(getAllAssignmentsPerStudent);
            while (rs.next()) {

                Student s = getStudent(rs.getInt(2));
                Course c = getCourse(rs.getInt(4));
                Assignment a = getAssignment(rs.getInt(3));
                StudentAssignments sa = new StudentAssignments(rs.getInt(1), s, a, c, rs.getInt(5));
                list.add(sa);
            }
            closeConnections(rs, st);
        } catch (SQLException ex) {
            Logger.getLogger(StudentAssignmentsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public boolean insertAssignmentToStudent(int acid, int sid, int samark) {
        boolean inserted = false;
        try {
            PreparedStatement pst = getConnection().prepareStatement(insertAssignmentsToStudent);
            pst.setInt(1, acid);
            pst.setInt(2, sid);
            pst.setInt(3, samark);

            int result = pst.executeUpdate();
            if (result > 0) {
                inserted = true;
                System.out.println("Assignment inserted into student successfully");
            }
            pst.close();
            conn.close();
        } catch (SQLException ex) {
            inserted = false;
            System.out.println(ex.getLocalizedMessage());

        }

        return inserted;
    }

    public static void insertAssignmentToStudent() {
        try {
            System.out.println("\n-->Insert fields ");

            System.out.println("Please enter a number of student from the list : ");
            printStudents();
            int sid = scanner.nextInt();
            System.out.println("Please enter a number of assignment per course from the list : ");
            printAssignmentsPerCourse();
            int acid = scanner.nextInt();
            System.out.println("Please enter total mark of student: ");

            int samark = scanner.nextInt();

            boolean saved = sadao.insertAssignmentToStudent(acid, sid, samark);

        } catch (InputMismatchException | DateTimeParseException ex) {
            System.out.println(ex.getLocalizedMessage());
            System.out.println("Try again...");
        }
        message();
    }

    public static void printStudentAssignmentsPerCourse() {
        System.out.println("\n-->Printing assignments per student per course...");
        List<StudentAssignments> salist = sadao.getStudentAssignments();
        for (StudentAssignments sa : salist) {
            System.out.println(sa);
        }
    }
}
