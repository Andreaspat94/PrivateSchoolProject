/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Assignment;
import entities.Course;
import entities.Student;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author George
 */
public class Dao {

    protected final String URL = "jdbc:mysql://localhost:3306/privateschool?serverTimezone=UTC";
    protected final String USERNAME = "root";
    protected final String PASS = "13391339";
    protected Connection conn;

    protected Connection getConnection() {
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASS);
        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

    protected void closeConnections(ResultSet rs, Statement st) {
        try {
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected LocalDate dateValidation(String date) {
        if (date != null) {
            LocalDate DateOfBirth = LocalDate.parse(date);
            return DateOfBirth;
        }
        return null;
    }

    protected boolean courseDateValidation(String sdate, String edate) {
        if (dateValidation(sdate) != null && dateValidation(edate) != null) {              // isws einai perito logo oti h sql elegxei gia null
            LocalDate startingDate = LocalDate.parse(sdate);
            LocalDate endingDate = LocalDate.parse(edate);

            if (endingDate.isAfter(startingDate)) {
                return true;
            }
        }
        return false;
    }

    protected Date convertToDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    protected Student getStudent(int sid) {
        StudentDao sdao = new StudentDao();
        Student s = sdao.getStudentById(sid);
        return s;
    }

    protected Course getCourse(int ccode) {
        CourseDao cdao = new CourseDao();
        Course c = cdao.getCourseById(ccode);
        return c;
    }

    protected Assignment getAssignment(int aid) {
        AssignmentDao adao = new AssignmentDao();
        Assignment a = adao.getAssignmentById(aid);
        return a;
    }
}
