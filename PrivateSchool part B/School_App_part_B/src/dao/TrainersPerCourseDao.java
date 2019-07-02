/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.CourseDao.printCourses;
import static dao.TrainerDao.printTrainers;
import entities.Course;
import entities.Trainer;
import entities.TrainersPerCourse;
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
public class TrainersPerCourseDao extends Dao {

    static TrainersPerCourseDao tcdao = new TrainersPerCourseDao();
    private final String getAllTrainersPerCourse = "select tc.tcid as ID,c.cid as Course_ID, \n"
            + "tc.tid as Trainer_ID\n"
            + "from trainerspercourse as tc, trainer as t, course as c, direction as d, length as l, profession as p\n"
            + "where tc.cid = c.cid and tc.tid = t.tid and c.cstream = p.pid and c.ctype = l.lid and t.tsubject = d.did\n"
            + "order by ID";
    private final String insertTrainersToCourse = "insert into trainerspercourse(cid,tid) values (?,?)";

    @Override
    protected void closeConnections(ResultSet rs, Statement st) {
        super.closeConnections(rs, st); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Connection getConnection() {
        return super.getConnection(); //To change body of generated methods, choose Tools | Templates.
    }

    public List<TrainersPerCourse> getTrainersPerCourse() {
        List<TrainersPerCourse> list = new ArrayList();
        try {

            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(getAllTrainersPerCourse);
            while (rs.next()) {

                Trainer t = getTrainer(rs.getInt(3));
                Course c = getCourse(rs.getInt(2));
                TrainersPerCourse tc = new TrainersPerCourse(rs.getInt(1), c, t);
                list.add(tc);
            }
            closeConnections(rs, st);
        } catch (SQLException ex) {
            Logger.getLogger(StudentsPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public boolean insertTrainerToCourse(int cid, int tid) {
        boolean inserted = false;
        try {
            PreparedStatement pst = getConnection().prepareStatement(insertTrainersToCourse);
            pst.setInt(1, cid);
            pst.setInt(2, tid);

            int result = pst.executeUpdate();
            if (result > 0) {
                inserted = true;
                System.out.println("Trainer inserted into course successfully");
            }
            pst.close();
            conn.close();
        } catch (SQLException ex) {
            inserted = false;
            System.out.println(ex.getLocalizedMessage());

        }

        return inserted;
    }

    public static void insertTrainerToCourse() {
        try {
            System.out.println("\n-->Insert fields ");

            System.out.println("Please enter a number of trainer from the list : ");
            printTrainers();
            int tid = scanner.nextInt();
            System.out.println("Please enter a number of course from the list : ");
            printCourses();
            int cid = scanner.nextInt();
            boolean saved = tcdao.insertTrainerToCourse(cid, tid);

        } catch (InputMismatchException | DateTimeParseException ex) {
            System.out.println(ex.getLocalizedMessage());
            System.out.println("Try again...");
        }
        message();
    }

    private Trainer getTrainer(int tid) {
        TrainerDao tdao = new TrainerDao();
        Trainer t = tdao.getTrainerById(tid);
        return t;
    }

    public static void printTrainersPerCourse() {
        System.out.println("\n-->Printing Trainers per course...");
        List<TrainersPerCourse> tclist = tcdao.getTrainersPerCourse();
        for (TrainersPerCourse tc : tclist) {
            System.out.println(tc);
        }
    }

    @Override
    protected Course getCourse(int ccode) {
        return super.getCourse(ccode);
    }

}
