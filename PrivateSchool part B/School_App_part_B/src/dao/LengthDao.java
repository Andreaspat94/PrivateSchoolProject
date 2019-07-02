/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Length;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author George
 */
public class LengthDao extends Dao {

    private final String insertLengths = "insert into length(lname) values(?)";
    private final String getLengthById = "select * from length where lid=?";
    private final String getLengthIdByName = "select lid from length where lname=? ";

    @Override
    protected void closeConnections(ResultSet rs, Statement st) {
        super.closeConnections(rs, st);
    }

    @Override
    protected Connection getConnection() {
        return super.getConnection();
    }

    public boolean insertLength(Length l) {
        boolean inserted = false;
        try {
            PreparedStatement pst = getConnection().prepareStatement(insertLengths);

            pst.setString(1, l.getLname());

            int result = pst.executeUpdate();
            if (result > 0) {
                inserted = true;
                System.out.println("Length inserted successfully");
            }
            pst.close();
            conn.close();
        } catch (SQLException ex) {
            inserted = false;
            System.out.println(ex.getLocalizedMessage());

        }
        return inserted;
    }

    public Length getLengthById(int lid) {
        Length l = null;
        try {
            PreparedStatement pst = getConnection().prepareStatement(getLengthById);
            pst.setInt(1, lid);
            ResultSet rs = pst.executeQuery();
            rs.next();

            l = new Length(rs.getInt(1), rs.getString(2));
            closeConnections(rs, pst);

        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return l;
    }

    public int getLengthId(String name) {
        int l = 0;
        try {
            PreparedStatement pst = getConnection().prepareStatement(getLengthIdByName);
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            rs.next();

            l = rs.getInt(1);
            closeConnections(rs, pst);

        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return l;
    }
}
