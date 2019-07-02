/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Objects;

/**
 *
 * @author George
 */
public class Direction {

    private int did;
    private String dname;

    public Direction() {
    }

    public Direction(int did, String dname) {
        this.did = did;
        this.dname = dname;
    }

    public Direction(String dname) {
        this.dname = dname;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + this.did;
        hash = 61 * hash + Objects.hashCode(this.dname);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Direction other = (Direction) obj;
        if (this.did != other.did) {
            return false;
        }
        if (!Objects.equals(this.dname, other.dname)) {
            return false;
        }
        return true;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    @Override
    public String toString() {
        return "Direction_Id: " + did + ", Direction_Name: " + dname + '}';
    }

}
