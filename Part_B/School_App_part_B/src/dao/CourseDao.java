/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Course;
import entities.Direction;
import entities.EndingPeriod;
import entities.StartingPeriod;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static school_app_part_b.School_App_part_B.message;
import static school_app_part_b.School_App_part_B.printMenu;
import static school_app_part_b.School_App_part_B.scanner;

/**
 *
 * @author George
 */
public class CourseDao extends Dao {

    static CourseDao cdao = new CourseDao();
    private final String getAllCourses = "select c.cid as ID, c.ctitle as Title, d.dname as Stream, l.lname as Type, s.spdate as Starting_Date, e.epdate as Ending_Date\n"
            + "from course as c , direction as d,length as l,speriod as s,  eperiod as e\n"
            + "where c.cstream = d.did and c.ctype = l.lid and c.cstart = s.spid and c.cend = e.epid\n"
            + "order by ID";
    private final String getCourseById = "select c.cid as ID, c.ctitle as Title, d.dname as Stream, l.lname as Type, s.spdate as Starting_Date, e.epdate as Ending_Date\n"
            + "from course as c , direction as d,length as l,speriod as s,  eperiod as e\n"
            + "where c.cstream = d.did and c.ctype = l.lid and c.cstart = s.spid and c.cend = e.epid and c.cid =?\n"
            + "order by ID";
    private final String updateCourse = "UPDATE course SET ctitle = ?,cstream=?,ctype=?,cstart=?,cend=? WHERE cid = ?";
    private final String insertCourses = "insert into course(ctitle,cstream,ctype,cstart,cend) values (?,?,?,?,?)";
    private final String deleteCourse = "DELETE FROM course WHERE cid = ?";

    @Override
    protected void closeConnections(ResultSet rs, Statement st) {
        super.closeConnections(rs, st);
    }

    @Override
    protected Connection getConnection() {
        return super.getConnection();
    }

    public List<Course> getCourses() {
        List<Course> list = new ArrayList();

        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(getAllCourses);
            while (rs.next()) {
                if (courseDateValidation(rs.getString(5), rs.getString(6))) {
                    LocalDate startingDate = LocalDate.parse(rs.getString(5));
                    LocalDate endingDate = LocalDate.parse(rs.getString(5));

                    Course c = new Course(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), startingDate, endingDate);
                    list.add(c);
                }
            }
            closeConnections(rs, st);
        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    public Course getCourseById(int ccode) {
        Course c = null;
        try {
            PreparedStatement pst = getConnection().prepareStatement(getCourseById);
            pst.setInt(1, ccode);
            ResultSet rs = pst.executeQuery();
            rs.next();
            if (courseDateValidation(rs.getString(5), rs.getString(6))) {
                LocalDate startingDate = LocalDate.parse(rs.getString(5));
                LocalDate endingDate = LocalDate.parse(rs.getString(5));
                c = new Course(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), startingDate, endingDate);
                closeConnections(rs, pst);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    public boolean insertCourse(Course c) {
        boolean inserted = false;
        try {
            PreparedStatement pst = getConnection().prepareStatement(insertCourses);
            /*(1)*/ pst.setString(1, c.getCtitle());

            int d = new DirectionDao().getDirectionId(c.getCstream());
            if (d == 0) {
                boolean dir = new DirectionDao().insertDirection(new Direction(c.getCstream()));

            }
            /*(3)*/ pst.setInt(2, new DirectionDao().getDirectionId(c.getCstream()));

            /*(4)*/ pst.setInt(3, new LengthDao().getLengthId(c.getCtype()));

            int sp = new StartingPeriodDao().getStartingPeriodId(c.getCstart());
            if (sp == 0) {
                boolean sper = new StartingPeriodDao().insertStartingPeriod(new StartingPeriod(c.getCstart()));

            }
            /*(5)*/ pst.setInt(4, new StartingPeriodDao().getStartingPeriodId(c.getCstart()));

            int ep = new EndingPeriodDao().getEndingPeriodId(c.getCend());
            if (ep == 0) {
                boolean eper = new EndingPeriodDao().insertEndingPeriod(new EndingPeriod(c.getCend()));

            }
            /*(6)*/ pst.setInt(5, new EndingPeriodDao().getEndingPeriodId(c.getCend()));

            int result = pst.executeUpdate();
            if (result > 0) {
                inserted = true;
                System.out.println("Course inserted successfully");
            }
            pst.close();
            conn.close();
        } catch (SQLException ex) {
            inserted = false;
            System.out.println(ex.getLocalizedMessage());

        }

        return inserted;
    }

    public static void insertCourse() {
        try {
            System.out.println("\n-->Insert fields ");

            System.out.println("Please enter title of course: ");
            String title = scanner.next();
            System.out.println("Please enter stream of course: ");
            String stream = scanner.next();
            System.out.println("Please enter type of course(Use underscore): ");
            String type = scanner.next();
            System.out.println("Please enter starting date(yyyy-MM-dd): ");
            LocalDate sdate = LocalDate.parse(scanner.next());
            System.out.println("Please enter ending date(yyyy-MM-dd): ");
            LocalDate edate = LocalDate.parse(scanner.next());
            boolean saved = cdao.insertCourse(new Course(title, stream, type, sdate, edate));

        } catch (InputMismatchException | DateTimeParseException ex) {
            System.out.println(ex.getLocalizedMessage());
            System.out.println("Try again...");
        }
        message();
    }

    public void deleteCourse(Course c) {
        try {
            PreparedStatement pst = getConnection().prepareStatement(deleteCourse);
            pst.setInt(1, c.getCcode());
            int result = pst.executeUpdate();
            if (result > 0) {
                System.out.println("Course deleted successfully");
            } else {
                System.out.println("Course not deleted");
            }
            pst.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }

    }

    public static void deleteCourse() {
        try {
            System.out.println("Please insert ID of the course you want to delete: ");
            Course c = cdao.getCourseById(scanner.nextInt());
            cdao.deleteCourse(c);
        } catch (InputMismatchException ex) {
            System.out.println(ex.getLocalizedMessage());
            System.out.println("Back to menu...");
            printMenu();
        }
        message();
    }

    public static void printCourses() {
        System.out.println("\n-->Printing all courses...");
        List<Course> courseList = cdao.getCourses();
        for (Course c : courseList) {
            System.out.println(c);
        }
    }

    @Override
    protected boolean courseDateValidation(String sdate, String edate) {
        return super.courseDateValidation(sdate, edate);
    }

    @Override
    protected Date convertToDate(LocalDate dateToConvert) {
        return super.convertToDate(dateToConvert);
    }

}
