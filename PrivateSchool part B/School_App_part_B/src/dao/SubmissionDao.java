/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Submission;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

/**
 *
 * @author George
 */
public class SubmissionDao extends Dao {

    public static SubmissionDao subdao = new SubmissionDao();
    private final String insertSubmissions = "insert into submission(subdate) values(?)";
    private final String getSubmissionById = "select * from submission where subid=?";
    private final String getSubmissionByName = "select subid from submission where subdate=? ";

    @Override
    protected void closeConnections(ResultSet rs, Statement st) {
        super.closeConnections(rs, st);
    }

    @Override
    protected Connection getConnection() {
        return super.getConnection();

    }

    @Override
    protected Date convertToDate(LocalDate dateToConvert) {
        return super.convertToDate(dateToConvert);
    }

    public boolean insertSubmission(Submission s) {
        boolean inserted = false;
        try {
            PreparedStatement pst = getConnection().prepareStatement(insertSubmissions);

            pst.setDate(1, convertToDate(s.getSubdate()));

            int result = pst.executeUpdate();
            if (result > 0) {
                inserted = true;
                System.out.println("Submission Period inserted successfully");
            }
            pst.close();
            conn.close();
        } catch (SQLException ex) {
            inserted = false;
            System.out.println(ex.getLocalizedMessage());

        }
        return inserted;
    }

    public Submission getSubmissionById(int subid) {
        Submission s = null;
        try {
            PreparedStatement pst = getConnection().prepareStatement(getSubmissionById);
            pst.setInt(1, subid);
            ResultSet rs = pst.executeQuery();
            rs.next();

            s = new Submission(rs.getInt(1), rs.getDate(2).toLocalDate());
            closeConnections(rs, pst);

        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return s;
    }

    public int getSubmissionId(LocalDate date) {
        int p = 0;
        try {
            PreparedStatement pst = getConnection().prepareStatement(getSubmissionByName);
            pst.setDate(1, convertToDate(date));
            ResultSet rs = pst.executeQuery();
            rs.next();

            p = rs.getInt(1);
            closeConnections(rs, pst);

        } catch (SQLException ex) {
//           System.out.println(ex.getLocalizedMessage());
            System.out.println("Id not found\ninserting right now...");
        }
        return p;
    }
}
