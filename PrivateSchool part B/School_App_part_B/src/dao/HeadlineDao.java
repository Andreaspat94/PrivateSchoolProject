/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Headline;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author George
 */
public class HeadlineDao extends Dao {

    private final String insertHeadlines = "insert into headline(hname) values(?)";
    private final String getHeadlineById = "select * from headline where hid=?";
    private final String getHeadlineIdByName = "select hid from headline where hname=? ";

    @Override
    protected void closeConnections(ResultSet rs, Statement st) {
        super.closeConnections(rs, st);
    }

    @Override
    protected Connection getConnection() {
        return super.getConnection();
    }

    public boolean insertHeadline(Headline h) {
        boolean inserted = false;
        try {
            PreparedStatement pst = getConnection().prepareStatement(insertHeadlines);
            pst.setString(1, h.getHname());

            int result = pst.executeUpdate();
            if (result > 0) {
                inserted = true;
                System.out.println("Headline inserted successfully");
            }
            pst.close();
            conn.close();
        } catch (SQLException ex) {
            inserted = false;
            System.out.println(ex.getLocalizedMessage());

        }
        return inserted;
    }

    public Headline getHeadlineById(int hid) {
        Headline h = null;
        try {
            PreparedStatement pst = getConnection().prepareStatement(getHeadlineById);
            pst.setInt(1, hid);
            ResultSet rs = pst.executeQuery();
            rs.next();

            h = new Headline(rs.getInt(1), rs.getString(2));
            closeConnections(rs, pst);

        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return h;
    }

    public int getHeadlineId(String name) {
        int h = 0;
        try {
            PreparedStatement pst = getConnection().prepareStatement(getHeadlineIdByName);
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            rs.next();

            h = rs.getInt(1);
            closeConnections(rs, pst);

        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return h;
    }
}
