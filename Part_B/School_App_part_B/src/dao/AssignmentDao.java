/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Assignment;
import entities.Description;
import entities.Headline;
import entities.Submission;
import java.sql.Connection;
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
public class AssignmentDao extends Dao {

    static AssignmentDao adao = new AssignmentDao();
    private final String getAllAssignments = "select a.aid as ID, h.hname as Title, d.dename as Description, s.subdate as Submission_Date,a.aoralMark as Oral_Mark, a.atotalMark as Total_Mark\n"
            + "from assignment as a,description as d,headline as h,submission as s\n"
            + "where a.atitle = h.hid and a.adescription = d.deid and a.asubtime = s.subid\n"
            + "order by ID";
    private final String getAssignmentById = "select a.aid as ID, h.hname as Title, d.dename as Description, s.subdate as Submission_Date,a.aoralMark as Oral_Mark, a.atotalMark as Total_Mark\n"
            + "from assignment as a,description as d,headline as h,submission as s\n"
            + "where a.atitle = h.hid and a.adescription = d.deid and a.asubtime = s.subid and a.aid = ?\n"
            + "order by ID";
    private final String updateAssignment = "UPDATE assignment SET atitle = ?,adescription=?,asubtime=?,aoralMark=?,atotalMark=? WHERE aid = ?";
    private final String insertAssignments = "insert into assignment(atitle,adescription,asubtime,aoralMark,atotalMark) values (?,?,?,?,?)";
    private final String deleteAssignment = "DELETE FROM assignment WHERE aid = ?";

    @Override
    protected void closeConnections(ResultSet rs, Statement st) {
        super.closeConnections(rs, st);
    }

    @Override
    protected Connection getConnection() {
        return super.getConnection();
    }

    public List<Assignment> getAssignments() {
        List<Assignment> list = new ArrayList();

        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(getAllAssignments);
            while (rs.next()) {
                LocalDate subDate = LocalDate.parse(rs.getString(4));
                Assignment a = new Assignment(rs.getInt(1), rs.getString(2), rs.getString(3), subDate, rs.getInt(5), rs.getInt(6));
                list.add(a);

            }
            closeConnections(rs, st);
        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Assignment getAssignmentById(int ccode) {
        Assignment a = null;
        try {
            PreparedStatement pst = getConnection().prepareStatement(getAssignmentById);
            pst.setInt(1, ccode);
            ResultSet rs = pst.executeQuery();
            rs.next();
            LocalDate subDate = LocalDate.parse(rs.getString(4));
            a = new Assignment(rs.getInt(1), rs.getString(2), rs.getString(3), subDate, rs.getInt(5), rs.getInt(6));
            closeConnections(rs, pst);

        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }

    public static void printAssignments() {
        System.out.println("\n-->Printing all assignments...");
        List<Assignment> assignmentList = adao.getAssignments();
        for (Assignment a : assignmentList) {
            System.out.println(a);
        }
    }

    public boolean insertAssignment(Assignment a) {
        boolean inserted = false;
        try {
            PreparedStatement pst = getConnection().prepareStatement(insertAssignments);
            /*(1)*/

 /*(2)*/ int h = new HeadlineDao().getHeadlineId(a.getAtitle());
            if (h == 0) {
                boolean head = new HeadlineDao().insertHeadline(new Headline(a.getAtitle()));
            }
            pst.setInt(1, new HeadlineDao().getHeadlineId(a.getAtitle()));

            int d = new DescriptionDao().getDescriptionId(a.getAdescription());
            if (d == 0) {
                /*(3)*/ boolean dir = new DescriptionDao().insertDescription(new Description(a.getAdescription()));
            }
            pst.setInt(2, new DescriptionDao().getDescriptionId(a.getAdescription()));

            int sub = new SubmissionDao().getSubmissionId(a.getAsubtime());
            if (sub == 0) {
                boolean subm = new SubmissionDao().insertSubmission(new Submission(a.getAsubtime()));
            }
            /*(4)*/ pst.setInt(3, new SubmissionDao().getSubmissionId(a.getAsubtime()));

            /*(5)*/ pst.setInt(4, a.getAoralMark());
            /*(6)*/ pst.setInt(5, a.getAtotalMark());

            int result = pst.executeUpdate();
            if (result > 0) {
                inserted = true;
                System.out.println("Assignment inserted successfully");
            }
            pst.close();
            conn.close();
        } catch (SQLException ex) {
            inserted = false;
            System.out.println(ex.getLocalizedMessage());

        }

        return inserted;
    }

    public static void insertAssignment() {
        try {
            System.out.println("\n-->Insert fields ");

            System.out.println("Please enter title of assignment: ");
            String title = scanner.next();
            System.out.println("Please enter description of assignment: ");
            String description = scanner.next();
            System.out.println("Please enter submission date(yyyy-MM-dd): ");
            LocalDate subdate = LocalDate.parse(scanner.next());
            System.out.println("Please enter oral mark of assignment: ");
            int oral = scanner.nextInt();
            System.out.println("Please enter total mark of assignment: ");
            int total = scanner.nextInt();
            boolean saved = adao.insertAssignment(new Assignment(title, description, subdate, oral, total));

        } catch (InputMismatchException | DateTimeParseException ex) {
            System.out.println(ex.getLocalizedMessage());
            System.out.println("Try again...");
        }
        message();
    }

    public void updateAssignment(Assignment oldAssignment) {
        try {
            PreparedStatement pst = getConnection().prepareStatement(updateAssignment);

            int h = new HeadlineDao().getHeadlineId(oldAssignment.getAtitle());
            if (h == 0) {
                boolean head = new HeadlineDao().insertHeadline(new Headline(oldAssignment.getAtitle()));
            }
            pst.setInt(1, new HeadlineDao().getHeadlineId(oldAssignment.getAtitle()));

            int d = new DescriptionDao().getDescriptionId(oldAssignment.getAdescription());
            if (d == 0) {
                /*(3)*/ boolean dir = new DescriptionDao().insertDescription(new Description(oldAssignment.getAdescription()));
            }
            pst.setInt(2, new DescriptionDao().getDescriptionId(oldAssignment.getAdescription()));

            int sub = new SubmissionDao().getSubmissionId(oldAssignment.getAsubtime());
            if (sub == 0) {
                boolean subm = new SubmissionDao().insertSubmission(new Submission(oldAssignment.getAsubtime()));
            }
            /*(4)*/ pst.setInt(3, new SubmissionDao().getSubmissionId(oldAssignment.getAsubtime()));

            /*(5)*/ pst.setInt(4, oldAssignment.getAoralMark());
            /*(6)*/ pst.setInt(5, oldAssignment.getAtotalMark());

            pst.setInt(6, oldAssignment.getAid());
            int result = pst.executeUpdate();
            if (result > 0) {
                System.out.println("Assignment updated successfully");
            } else {
                System.out.println("Assignment not updated");
            }
            pst.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }

    }

    public static void updateAssignment() {
        try {
            System.out.println("\n-->Update fields ");
            System.out.println("Please enter ID of the assignment you want to update: ");
            Assignment old = adao.getAssignmentById(scanner.nextInt());
            System.out.println("Please enter title of assignment: ");
            old.setAtitle(scanner.next());
            System.out.println("Please enter description of Assignment: ");
            old.setAdescription(scanner.next());
            System.out.println("Please enter submission date of assignment(yyy-MM-dd): ");
            old.setAsubtime(LocalDate.parse(scanner.next()));
            System.out.println("Please enter oral mark of assignment: ");
            old.setAoralMark(scanner.nextInt());
            System.out.println("Please enter oral mark of assignment: ");
            old.setAoralMark(scanner.nextInt());

            adao.updateAssignment(old);

        } catch (InputMismatchException | DateTimeParseException ex) {
            System.out.println(ex.getLocalizedMessage());
            System.out.println("Try again...");
        }
        message();
    }

    public void deleteAssignment(Assignment a) {
        try {
            PreparedStatement pst = getConnection().prepareStatement(deleteAssignment);
            pst.setInt(1, a.getAid());
            int result = pst.executeUpdate();
            if (result > 0) {
                System.out.println("Assignment deleted successfully");
            } else {
                System.out.println("Assignment not deleted");
            }
            pst.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }

    }

    public static void deleteAssignment() {
        try {
            System.out.println("Please insert ID of the assignment you want to delete: ");
            Assignment a = adao.getAssignmentById(scanner.nextInt());
            adao.deleteAssignment(a);
        } catch (InputMismatchException ex) {
            System.out.println(ex.getLocalizedMessage());
            System.out.println("Back to menu...");
            printMenu();
        }
        message();
    }
}
