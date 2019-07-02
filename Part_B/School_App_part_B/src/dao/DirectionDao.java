/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Direction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DirectionDao extends Dao {

    public static DirectionDao ddao = new DirectionDao();
    private final String insertDirections = "insert into direction(dname) values(?)";
    private final String getDirectionById = "select * from direction where did=?";
    private final String getDirectionIdByName = "select did from direction where dname=? ";

    @Override
    protected void closeConnections(ResultSet rs, Statement st) {
        super.closeConnections(rs, st);
    }

    @Override
    protected Connection getConnection() {
        return super.getConnection();
    }

    public boolean insertDirection(Direction d) {
        boolean inserted = false;
        try {
            PreparedStatement pst = getConnection().prepareStatement(insertDirections);
            pst.setString(1, d.getDname());

            int result = pst.executeUpdate();
            if (result > 0) {
                inserted = true;
                System.out.println("Direction inserted successfully");
            }
            pst.close();
            conn.close();
        } catch (SQLException ex) {
            inserted = false;
            System.out.println(ex.getLocalizedMessage());

        }
        return inserted;
    }

    public Direction getDirectionById(int did) {
        Direction d = null;
        try {
            PreparedStatement pst = getConnection().prepareStatement(getDirectionById);
            pst.setInt(1, did);
            ResultSet rs = pst.executeQuery();
            rs.next();

            d = new Direction(rs.getInt(1), rs.getString(2));
            closeConnections(rs, pst);

        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return d;
    }

    public int getDirectionId(String name) {
        int d = 0;
        try {
            PreparedStatement pst = getConnection().prepareStatement(getDirectionIdByName);
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            rs.next();

            d = rs.getInt(1);
            closeConnections(rs, pst);

        } catch (SQLException ex) {
            //           System.out.println(ex.getLocalizedMessage());
            System.out.println("Id not found\ninserting right now...");
        }
        return d;
    }
}
