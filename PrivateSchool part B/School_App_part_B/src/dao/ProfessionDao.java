/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Profession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author George
 */
public class ProfessionDao extends Dao {

    private final String insertProfessions = "insert into profession values(?)";
    private final String getProfessionById = "select * from profession where pid=?";
    private final String getProfessionIdByName = "select pid from profession where pname=? ";

    @Override
    protected void closeConnections(ResultSet rs, Statement st) {
        super.closeConnections(rs, st);
    }

    @Override
    protected Connection getConnection() {
        return super.getConnection();
    }

    public boolean insertProfession(Profession p) {
        boolean inserted = false;
        try {
            PreparedStatement pst = getConnection().prepareStatement(insertProfessions);

            pst.setString(1, p.getPname());

            int result = pst.executeUpdate();
            if (result > 0) {
                inserted = true;
                System.out.println("Pofession inserted successfully");
            }
            pst.close();
            conn.close();
        } catch (SQLException ex) {
            inserted = false;
            System.out.println(ex.getLocalizedMessage());

        }
        return inserted;
    }

    public Profession getProfessionById(int pid) {
        Profession p = null;
        try {
            PreparedStatement pst = getConnection().prepareStatement(getProfessionById);
            pst.setInt(1, pid);
            ResultSet rs = pst.executeQuery();
            rs.next();

            p = new Profession(rs.getInt(1), rs.getString(2));
            closeConnections(rs, pst);

        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return p;
    }

    public int getProfessionId(String name) {
        int p = 0;
        try {
            PreparedStatement pst = getConnection().prepareStatement(getProfessionIdByName);
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            rs.next();

            p = rs.getInt(1);
            closeConnections(rs, pst);

        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return p;
    }
}
