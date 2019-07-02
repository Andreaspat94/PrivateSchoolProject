/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.AssignmentDao.printAssignments;
import static dao.CourseDao.printCourses;
import entities.Assignment;
import entities.AssignmentsPerCourse;
import entities.Course;
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
public class AssignmentsPerCourseDao extends Dao {

    static AssignmentsPerCourseDao acdao = new AssignmentsPerCourseDao();
    private final String getAllAssignmentsPerCourse = "select ac.acid as ID, ac.cid as Course_ID,\n"
            + "ac.aid as Assignment_ID\n"
            + "from assignmentspercourse as ac, assignment as a, course as c, direction as d, length as l,description as des, headline as h, submission as sub\n"
            + "where ac.cid = c.cid and ac.aid = a.aid and c.cstream = d.did and c.ctype = l.lid and a.atitle = h.hid \n"
            + "and a.adescription = des.deid and a.asubtime = sub.subid\n"
            + "order by ID";

    private final String getAssignmentsByCourse = "select ac.acid as ID, ac.cid as Course_ID,\n"
            + "ac.aid as Assignment_ID\n"
            + "from assignmentspercourse as ac, assignment as a, course as c, direction as d, length as l,description as des, headline as h, submission as sub\n"
            + "where ac.cid = c.cid and ac.aid = a.aid and c.cstream = d.did and c.ctype = l.lid and a.atitle = h.hid \n"
            + "and a.adescription = des.deid and a.asubtime = sub.subid and c.cid = ?\n"
            + "order by ID";
    private final String insertAssignmentsToCourse = "insert into assignmentspercourse(cid,aid) values (?,?)";

    @Override
    protected void closeConnections(ResultSet rs, Statement st) {
        super.closeConnections(rs, st);
    }

    @Override
    protected Connection getConnection() {
        return super.getConnection();
    }

    public List<AssignmentsPerCourse> getAssignmentsPerCourse() {
        List<AssignmentsPerCourse> list = new ArrayList();
        try {

            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(getAllAssignmentsPerCourse);
            while (rs.next()) {

                Assignment a = getAssignment(rs.getInt(3));
                Course c = getCourse(rs.getInt(2));
                AssignmentsPerCourse ac = new AssignmentsPerCourse(rs.getInt(1), c, a);
                list.add(ac);
            }
            closeConnections(rs, st);
        } catch (SQLException ex) {
            Logger.getLogger(StudentsPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<AssignmentsPerCourse> getAssignmentsByCourseId(Course c) {
        List<AssignmentsPerCourse> list = new ArrayList();
        try {
            PreparedStatement pst = getConnection().prepareStatement(getAssignmentsByCourse);
            pst.setInt(1, c.getCcode());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                AssignmentsPerCourse ac = new AssignmentsPerCourse();
                ac.setAcid(rs.getInt(1));

                Assignment a = getAssignment(rs.getInt(3));
                Course co = getCourse(rs.getInt(2));
                ac.setCourse(co);
                ac.setAssignment(a);

                list.add(ac);
            }
            closeConnections(rs, pst);
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentsPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public boolean insertAssignmentToCourse(int cid, int aid) {
        boolean inserted = false;
        try {
            PreparedStatement pst = getConnection().prepareStatement(insertAssignmentsToCourse);
            pst.setInt(1, cid);
            pst.setInt(2, aid);

            int result = pst.executeUpdate();
            if (result > 0) {
                inserted = true;
                System.out.println("Assignment inserted into course successfully");
            }
            pst.close();
            conn.close();
        } catch (SQLException ex) {
            inserted = false;
            System.out.println(ex.getLocalizedMessage());

        }

        return inserted;
    }

    public static void insertAssignmentToCourse() {
        try {
            System.out.println("\n-->Insert fields ");

            System.out.println("Please enter a number of assignment from the list : ");
            printAssignments();
            int aid = scanner.nextInt();
            System.out.println("Please enter a number of course from the list : ");
            printCourses();
            int cid = scanner.nextInt();
            boolean saved = acdao.insertAssignmentToCourse(cid, aid);

        } catch (InputMismatchException | DateTimeParseException ex) {
            System.out.println(ex.getLocalizedMessage());
            System.out.println("Try again...");
        }
        message();
    }

    public static void printAssignmentsPerCourse() {
        System.out.println("\n-->Printing Assignments per course...");
        List<AssignmentsPerCourse> aclist = acdao.getAssignmentsPerCourse();
        for (AssignmentsPerCourse ac : aclist) {
            System.out.println(ac);
        }
    }

    @Override
    protected Assignment getAssignment(int aid) {
        return super.getAssignment(aid);
    }

    @Override
    protected Course getCourse(int ccode) {
        return super.getCourse(ccode);
    }
}
