/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Description;
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
public class DescriptionDao extends Dao {

    private final String insertDescriptions = "insert into description(dename) values(?)";
    private final String getDescriptionById = "select * from description where deid=?";
    private final String getDescriptionIdByName = "select deid from description where dename=? ";

    @Override
    protected void closeConnections(ResultSet rs, Statement st) {
        super.closeConnections(rs, st);
    }

    @Override
    protected Connection getConnection() {
        return super.getConnection();
    }

    public boolean insertDescription(Description d) {
        boolean inserted = false;
        try {
            PreparedStatement pst = getConnection().prepareStatement(insertDescriptions);
            pst.setString(1, d.getDename());

            int result = pst.executeUpdate();
            if (result > 0) {
                inserted = true;
                System.out.println("Description inserted successfully");
            }
            pst.close();
            conn.close();
        } catch (SQLException ex) {
            inserted = false;
            System.out.println(ex.getLocalizedMessage());

        }
        return inserted;
    }

    public Description getDescriptionById(int deid) {
        Description d = null;
        try {
            PreparedStatement pst = getConnection().prepareStatement(getDescriptionById);
            pst.setInt(1, deid);
            ResultSet rs = pst.executeQuery();
            rs.next();

            d = new Description(rs.getInt(1), rs.getString(2));
            closeConnections(rs, pst);

        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return d;
    }

    public int getDescriptionId(String name) {
        int d = 0;
        try {
            PreparedStatement pst = getConnection().prepareStatement(getDescriptionIdByName);
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            rs.next();

            d = rs.getInt(1);
            closeConnections(rs, pst);

        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return d;
    }

}
