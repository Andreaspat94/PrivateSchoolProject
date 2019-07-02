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
public class Description {

    private int deid;
    private String dename;

    public Description() {
    }

    public Description(int deid, String dename) {
        this.deid = deid;
        this.dename = dename;
    }

    public Description(String dename) {
        this.dename = dename;
    }

    public int getDeid() {
        return deid;
    }

    public void setDeid(int deid) {
        this.deid = deid;
    }

    public String getDename() {
        return dename;
    }

    public void setDename(String dename) {
        this.dename = dename;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.deid;
        hash = 89 * hash + Objects.hashCode(this.dename);
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
        final Description other = (Description) obj;
        if (this.deid != other.deid) {
            return false;
        }
        if (!Objects.equals(this.dename, other.dename)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "description_id: " + deid + ", description_name: " + dename + '}';
    }

}
