/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.EndingPeriod;
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
public class EndingPeriodDao extends Dao {

    public static EndingPeriodDao epdao = new EndingPeriodDao();
    private final String insertPeriods = "insert into eperiod(epdate) values(?)";
    private final String getPeriodById = "select * from eperiod where epid=?";
    private final String getPeriodByName = "select epid from eperiod where epdate=? ";

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

    public boolean insertEndingPeriod(EndingPeriod p) {
        boolean inserted = false;
        try {
            PreparedStatement pst = getConnection().prepareStatement(insertPeriods);

            pst.setDate(1, convertToDate(p.getEpdate()));

            int result = pst.executeUpdate();
            if (result > 0) {
                inserted = true;
                System.out.println("Ending Period inserted successfully");
            }
            pst.close();
            conn.close();
        } catch (SQLException ex) {
            inserted = false;
            System.out.println(ex.getLocalizedMessage());

        }
        return inserted;
    }

    public EndingPeriod getEndingPeriodById(int epid) {
        EndingPeriod p = null;
        try {
            PreparedStatement pst = getConnection().prepareStatement(getPeriodById);
            pst.setInt(1, epid);
            ResultSet rs = pst.executeQuery();
            rs.next();

            p = new EndingPeriod(rs.getInt(1), rs.getDate(2).toLocalDate());
            closeConnections(rs, pst);

        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return p;
    }

    public int getEndingPeriodId(LocalDate date) {
        int p = 0;
        try {
            PreparedStatement pst = getConnection().prepareStatement(getPeriodByName);
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
