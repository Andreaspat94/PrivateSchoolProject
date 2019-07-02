/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Student;
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

public class StudentDao extends Dao {

    private static StudentDao stdao = new StudentDao();
    private final String getAllStudents = "select sid as ID, sfname as First_Name, slname as Last_Name, sdob as Date_Of_Birth,sfees as Tuition_Fees from student;";
    private final String getStudentById = "select sid as ID, sfname as First_Name, slname as Last_Name, sdob as Date_Of_Birth,sfees as Tuition_Fees from student where sid=?";
    private final String insertStudent = "insert into student(sfname,slname,sfees,sdob) values (?,?,?,?)";
    private final String updateStudent = "UPDATE student SET sfname = ?,slname=?,sfees=?,sdob=? WHERE sid = ?";
    private final String deleteStudent = "DELETE FROM student WHERE sid = ?";

    @Override
    protected void closeConnections(ResultSet rs, Statement st) {
        super.closeConnections(rs, st);
    }

    @Override
    protected Connection getConnection() {
        return super.getConnection();
    }

    public List<Student> getStudents() {
        List<Student> list = new ArrayList();

        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(getAllStudents);
            while (rs.next()) {

                LocalDate DateOfBirth = dateValidation(rs.getString(4));

                Student s = new Student(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(5), DateOfBirth);
                list.add(s);
            }
            closeConnections(rs, st);
        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Student getStudentById(int sid) {
        Student s = null;
        try {
            PreparedStatement pst = getConnection().prepareStatement(getStudentById);
            pst.setInt(1, sid);
            ResultSet rs = pst.executeQuery();
            rs.next();

            LocalDate DateOfBirth = LocalDate.parse(rs.getString(4));

            s = new Student(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(5), DateOfBirth);
            closeConnections(rs, pst);

        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

    public boolean insertStudent(Student s) {
        boolean inserted = false;
        try {
            PreparedStatement pst = getConnection().prepareStatement(insertStudent);

            pst.setString(1, s.getSfname());
            pst.setString(2, s.getSlname());
            pst.setInt(3, s.getSfees());

            pst.setDate(4, convertToDate(s.getSdob()));
            int result = pst.executeUpdate();
            if (result > 0) {
                inserted = true;
                System.out.println("Student inserted successfully");
            }
            pst.close();
            conn.close();
        } catch (SQLException ex) {
            inserted = false;
            System.out.println(ex.getLocalizedMessage());

        }

        return inserted;
    }

    public static void insertStudent() {
        try {
            System.out.println("\n-->Insert fields ");

            System.out.println("Please enter first name of student: ");
            String fname = scanner.next();
            System.out.println("Please enter last name of student: ");
            String lname = scanner.next();
            System.out.println("Please enter tuition fees: ");
            int fees = scanner.nextInt();
            System.out.println("Please enter date of birth(yyyy-MM-dd): ");
            LocalDate date = LocalDate.parse(scanner.next());
            boolean saved = stdao.insertStudent(new Student(fname, lname, fees, date));

        } catch (InputMismatchException | DateTimeParseException ex) {
            System.out.println(ex.getLocalizedMessage());
            System.out.println("Try again...");
        }
        message();
    }

    public void updateStudent(Student oldStudent) {
        try {
            PreparedStatement pst = getConnection().prepareStatement(updateStudent);
            pst.setString(1, oldStudent.getSfname());
            pst.setString(2, oldStudent.getSlname());
            pst.setInt(3, oldStudent.getSfees());
            pst.setDate(4, convertToDate(oldStudent.getSdob()));
            pst.setInt(5, oldStudent.getSid());
            int result = pst.executeUpdate();
            if (result > 0) {
                System.out.println("Customer updated successfully");
            } else {
                System.out.println("Customer not updated");
            }
            pst.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }

    }

    public static void updateStudent() {
        try {
            System.out.println("\n-->Update fields ");
            System.out.println("Please enter ID of the student you want to update: ");
            Student old = stdao.getStudentById(scanner.nextInt());
            System.out.println("Please enter first name of student: ");
            old.setSfname(scanner.next());
            System.out.println("Please enter last name of student: ");
            old.setSlname(scanner.next());
            System.out.println("Please enter tuition fees of student: ");
            old.setSfees(scanner.nextInt());
            System.out.println("Please enter date of birth of student(yyyy-MM-dd): ");
            old.setSdob(LocalDate.parse(scanner.next()));
            stdao.updateStudent(old);
        } catch (InputMismatchException | DateTimeParseException ex) {
            System.out.println(ex.getLocalizedMessage());
            System.out.println("Try again...");
        }
        message();
    }

    public void deleteStudent(Student s) {
        try {
            PreparedStatement pst = getConnection().prepareStatement(deleteStudent);
            pst.setInt(1, s.getSid());
            int result = pst.executeUpdate();
            if (result > 0) {
                System.out.println("Student deleted successfully");
            } else {
                System.out.println("Student not deleted");
            }
            pst.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }

    }

    public static void deleteStudent() {
        try {
            System.out.println("Please insert ID of the student you want to delete: ");
            Student s = stdao.getStudentById(scanner.nextInt());
            stdao.deleteStudent(s);
        } catch (InputMismatchException ex) {
            System.out.println(ex.getLocalizedMessage());
            System.out.println("Back to menu...");
            printMenu();
        }
        message();
    }

    @Override
    protected LocalDate dateValidation(String date) {
        return super.dateValidation(date);
    }

    @Override
    protected Date convertToDate(LocalDate dateToConvert) {
        return super.convertToDate(dateToConvert);
    }

    public static void printStudents() {
        System.out.println("\n-->Printing all students...");
        List<Student> studentList = stdao.getStudents();
        for (Student s : studentList) {
            System.out.println(s);
        }
    }

}
