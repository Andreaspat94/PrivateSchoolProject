/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Trainer;
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
import static school_app_part_b.School_App_part_B.printMenu;
import static school_app_part_b.School_App_part_B.scanner;

/**
 *
 * @author George
 */
public class TrainerDao extends Dao {

    static TrainerDao tdao = new TrainerDao();
    private final String getAllTrainers = "select tid as ID, tfname as First_Name,tlname as Last_Name,pname as Subject from trainer,profession\n"
            + "where trainer.tsubject = profession.pid";
    private final String getTrainerById = "select tid as ID, tfname as First_Name,tlname as Last_Name,pname as Subject from trainer,profession\n"
            + "where trainer.tsubject =profession.pid and tid= ?";
    private final String updateTrainer = "UPDATE trainer SET tfname = ?,tlname=?,tsubject=? WHERE tid = ?";
    private final String insertTrainers = "insert into trainer(tfname,tlname,tsubject) values(?,?,?) ";
    private final String deleteTrainer = "DELETE FROM trainer WHERE tid = ?";

    @Override
    protected void closeConnections(ResultSet rs, Statement st) {
        super.closeConnections(rs, st);
    }

    @Override
    protected Connection getConnection() {
        return super.getConnection();
    }

    public List<Trainer> getTrainers() {
        List<Trainer> list = new ArrayList();

        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(getAllTrainers);
            while (rs.next()) {

                Trainer t = new Trainer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                list.add(t);
            }
            closeConnections(rs, st);
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return list;
    }

    public Trainer getTrainerById(int tid) {
        Trainer t = null;
        try {
            PreparedStatement pst = getConnection().prepareStatement(getTrainerById);
            pst.setInt(1, tid);
            ResultSet rs = pst.executeQuery();
            rs.next();

            t = new Trainer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
            closeConnections(rs, pst);

        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }

    public boolean insertTrainer(Trainer t) {
        boolean inserted = false;
        try {
            PreparedStatement pst = getConnection().prepareStatement(insertTrainers);

            pst.setString(1, t.getTfname());
            pst.setString(2, t.getTlname());
            pst.setInt(3, new ProfessionDao().getProfessionId(t.getTsubject()));

            int result = pst.executeUpdate();
            if (result > 0) {
                inserted = true;
                System.out.println("Trainer inserted successfully");
            }
            pst.close();
            conn.close();
        } catch (SQLException ex) {
            inserted = false;
            System.out.println(ex.getLocalizedMessage());

        }
        return inserted;
    }

    public static void insertTrainer() {
        try {
            System.out.println("\n-->Insert fields ");

            System.out.println("Please enter first name of trainer: ");
            String fname = scanner.next();
            System.out.println("Please enter last name of trainer: ");
            String lname = scanner.next();
            System.out.println("Please enter subject of trainer as it appears on the list(example : Java): ");
            String subject = scanner.next();

            boolean saved = tdao.insertTrainer(new Trainer(fname, lname, subject));

        } catch (InputMismatchException ex) {
            System.out.println(ex.getLocalizedMessage());
            System.out.println("Try again...");
        }
        message();
    }

    public void updateTrainer(Trainer oldTrainer) {
        try {
            PreparedStatement pst = getConnection().prepareStatement(updateTrainer);
            pst.setString(1, oldTrainer.getTfname());
            pst.setString(2, oldTrainer.getTlname());
//            pst.setString(3, oldTrainer.getTsubject());
            pst.setInt(3, new ProfessionDao().getProfessionId(oldTrainer.getTsubject()));
            pst.setInt(4, oldTrainer.getTid());
            int result = pst.executeUpdate();
            if (result > 0) {
                System.out.println("Trainer updated successfully");
            } else {
                System.out.println("Trainer not updated");
            }
            pst.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }

    }

    public static void updateTrainer() {
        try {
            System.out.println("\n-->Update fields ");
            System.out.println("Please enter ID of the trainer you want to update: ");
            Trainer old = tdao.getTrainerById(scanner.nextInt());
            System.out.println("Please enter first name of trainer: ");
            old.setTfname(scanner.next());
            System.out.println("Please enter last name of trainer: ");
            old.setTlname(scanner.next());
            System.out.println("Please enter subject of trainer as it appears on the list(example : Java): ");
            old.setTsubject(scanner.next());

            tdao.updateTrainer(old);

        } catch (InputMismatchException | DateTimeParseException ex) {
            System.out.println(ex.getLocalizedMessage());
            System.out.println("Try again...");
        }
        message();
    }

    public void deleteTrainer(Trainer t) {
        try {
            PreparedStatement pst = getConnection().prepareStatement(deleteTrainer);
            pst.setInt(1, t.getTid());
            int result = pst.executeUpdate();
            if (result > 0) {
                System.out.println("Trainer deleted successfully");
            } else {
                System.out.println("Trainer not deleted");
            }
            pst.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }

    }

    public static void deleteTrainer() {
        try {
            System.out.println("Please insert ID of the trainer you want to delete: ");
            Trainer t = tdao.getTrainerById(scanner.nextInt());
            tdao.deleteTrainer(t);
        } catch (InputMismatchException ex) {
            System.out.println(ex.getLocalizedMessage());
            System.out.println("Back to menu...");
            printMenu();
        }
        message();
    }

    public static void printTrainers() {
        System.out.println("\n-->Printing all trainers...");
        List<Trainer> trainerList = tdao.getTrainers();
        for (Trainer t : trainerList) {
            System.out.println(t);
        }
    }
}
