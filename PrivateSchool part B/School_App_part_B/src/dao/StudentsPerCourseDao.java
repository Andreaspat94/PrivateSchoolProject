/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.CourseDao.printCourses;
import static dao.StudentDao.printStudents;
import entities.Course;
import entities.Student;
import entities.StudentsPerCourse;
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
public class StudentsPerCourseDao extends Dao {

    static StudentsPerCourseDao scdao = new StudentsPerCourseDao();
    private final String getAllStudentsPerCourse = "select sc.scid as ID, sc.sid as Student_ID,\n"
            + "		sc.cid as Course_ID\n"
            + "		from studentsPerCourse as sc, student as s, course as c, direction as d, length as l\n"
            + "		where c.cstream = d.did and c.ctype = l.lid and sc.sid = s.sid and sc.cid = c.cid\n"
            + "		order by ID";
    private final String moreThanOneCourse = "select sc.sid as Student_ID, sc.sid as Student_ID,sc.cid as Course_ID\n"
            + "		from studentsPerCourse as sc, student as s, course as c, direction as d, length as l\n"
            + "		where c.cstream = d.did and c.ctype = l.lid and sc.sid = s.sid and sc.cid = c.cid \n"
            + "        group by sc.sid having count(*) > 1\n"
            + "		order by sc.sid";
    private final String insertStudentsToCourse = "insert into studentspercourse(sid,cid) values (?,?)";

    @Override
    protected void closeConnections(ResultSet rs, Statement st) {
        super.closeConnections(rs, st);
    }

    @Override
    protected Connection getConnection() {
        return super.getConnection();
    }

    public List<StudentsPerCourse> getStudentsPerCourse() {
        List<StudentsPerCourse> list = new ArrayList();
        try {

            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(getAllStudentsPerCourse);
            while (rs.next()) {

                Student s = getStudent(rs.getInt(2));
                Course c = getCourse(rs.getInt(3));
                StudentsPerCourse sc = new StudentsPerCourse(rs.getInt(1), s, c);
                list.add(sc);
            }
            closeConnections(rs, st);
        } catch (SQLException ex) {
            Logger.getLogger(StudentsPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<StudentsPerCourse> getStudentsWithManyCourses() {
        List<StudentsPerCourse> list = new ArrayList();
        try {

            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(moreThanOneCourse);
            while (rs.next()) {
                Student s = getStudent(rs.getInt(2));
                Course c = getCourse(rs.getInt(3));
                StudentsPerCourse sc = new StudentsPerCourse(rs.getInt(1), s, c);
                list.add(sc);
            }
            closeConnections(rs, st);
        } catch (SQLException ex) {
            Logger.getLogger(StudentsPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static void printStudentsPerCourse() {
        System.out.println("\n-->Printing all students per course...");
        List<StudentsPerCourse> sclist = scdao.getStudentsPerCourse();
        for (StudentsPerCourse sc : sclist) {
            System.out.println(sc);
        }
    }

    public static void printStudentsWithManyCourses() {
        System.out.println("\n-->Printing students with more than one course...");
        List<StudentsPerCourse> manyCourseslist = scdao.getStudentsWithManyCourses();
        for (StudentsPerCourse sc : manyCourseslist) {
            System.out.println(sc);
        }
    }

    @Override
    protected Course getCourse(int ccode) {
        return super.getCourse(ccode);
    }

    @Override
    protected Student getStudent(int sid) {
        return super.getStudent(sid);
    }

    public boolean insertStudentToCourse(int sid, int cid) {
        boolean inserted = false;
        try {
            PreparedStatement pst = getConnection().prepareStatement(insertStudentsToCourse);
            pst.setInt(1, sid);
            pst.setInt(2, cid);

            int result = pst.executeUpdate();
            if (result > 0) {
                inserted = true;
                System.out.println("Student inserted into course successfully");
            }
            pst.close();
            conn.close();
        } catch (SQLException ex) {
            inserted = false;
            System.out.println(ex.getLocalizedMessage());

        }

        return inserted;
    }

    public static void insertStudentToCourse() {
        try {
            System.out.println("\n-->Insert fields ");

            System.out.println("Please enter a number of student from the list : ");
            printStudents();
            int sid = scanner.nextInt();
            System.out.println("Please enter a number of course from the list : ");
            printCourses();
            int cid = scanner.nextInt();
            boolean saved = scdao.insertStudentToCourse(sid, cid);

        } catch (InputMismatchException | DateTimeParseException ex) {
            System.out.println(ex.getLocalizedMessage());
            System.out.println("Try again...");
        }
        message();
    }
}
